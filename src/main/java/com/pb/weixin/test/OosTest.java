package com.pb.weixin.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.pb.weixin.service.ISongService;
import com.pb.weixin.utils.HelloOSS2;
import com.pb.weixin.vo.Song;

//阿里云oos存储


@Component
public class OosTest {

	
	@Autowired
	private ISongService songService;
	
	
	 static Logger logger = Logger.getLogger(OosTest.class);
	   
	    //1.配置oos的链接信息
	    // endpoint是访问OSS的域名。如果您已经在OSS的控制台上 创建了Bucket，请在控制台上查看域名。
	    // 如果您还没有创建Bucket，endpoint选择请参看文档中心的“开发人员指南 > 基本概念 > 访问域名”，
	    // 链接地址是：https://help.aliyun.com/document_detail/oss/user_guide/oss_concept/endpoint.html?spm=5176.docoss/user_guide/endpoint_region
	    // endpoint的格式形如“http://oss-cn-hangzhou.aliyuncs.com/”，注意http://后不带bucket名称，
	    // 比如“http://bucket-name.oss-cn-hangzhou.aliyuncs.com”，是错误的endpoint，请去掉其中的“bucket-name”。
	    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";

	    // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
	    // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
	    // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
	    private static String accessKeyId = "LTAI4GC82qmbXrEdSmrkWKkM";
	    private static String accessKeySecret = "quBoezD11bcghnuntxWQVX4KJWRIPu";

	    // Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
	    // Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
	    private static String bucketName = "pb-20191014";

	    // Object是OSS存储数据的基本单元，称为OSS的对象，也被称为OSS的文件。详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
	    // Object命名规范如下：使用UTF-8编码，长度必须在1-1023字节之间，不能以“/”或者“\”字符开头。
	    // Object其实就是你要创建的文件的名称  ，注意要带上文件的后缀名
	    private static String firstKey = "pbObj.txt";
	
	//@Test
	  //@Scheduled 这个注解是用于定时任务的
		//initialDelay 这个是当任务启动后，等多久执行方法（单位毫秒）
		//fixedDelay 每隔多久执行这个方法（单位毫秒）
//	@Scheduled(initialDelay=1000,fixedDelay=1000*100)
//	 @Scheduled(cron="0 26 03 06 06 *")   //15分03小时   06日  06月  
	public void oosGetAll() {
		// 日志配置，OSS Java SDK使用log4j记录错误信息。示例程序会在工程目录下生成“oss-demo.log”日志文件，默认日志级别是INFO。
        // 日志的配置文件是“conf/log4j.properties”，如果您不需要日志，可以没有日志配置文件和下面的日志配置。
        PropertyConfigurator.configure("conf/log4j.properties");

        logger.info("Started");

        // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/init.html?spm=5176.docoss/sdk/java-sdk/get-start
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        try {
        	//2.判断bucketName库是否创建
            // 判断Bucket是否存在。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
            if (ossClient.doesBucketExist(bucketName)) {
                System.out.println("您已经创建Bucket：" + bucketName + "。");
            } else {
                System.out.println("您的Bucket不存在，创建Bucket：" + bucketName + "。");
                // 创建Bucket。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
                // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
                ossClient.createBucket(bucketName);
            }

          //3.查看bucketName库的信息
            // 查看Bucket信息。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
            BucketInfo info = ossClient.getBucketInfo(bucketName);
            System.out.println("Bucket " + bucketName + "的信息如下：");
            System.out.println("\t数据中心：" + info.getBucket().getLocation());
            System.out.println("\t创建时间：" + info.getBucket().getCreationDate());
            System.out.println("\t用户标志：" + info.getBucket().getOwner());

          //4.向oss的Object对象文件写内容
            // 把字符串存入OSS，Object的名称为firstKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
            InputStream is = new ByteArrayInputStream("Hello OSS".getBytes());
            ossClient.putObject(bucketName, firstKey, is);   //想Object文件里面写入内容
            System.out.println("Object：" + firstKey + "存入OSS成功。");

         
            // 下载文件。详细请参看“SDK手册 > Java-SDK > 下载文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/download_object.html?spm=5176.docoss/sdk/java-sdk/manage_object
            OSSObject ossObject = ossClient.getObject(bucketName, firstKey);
            InputStream inputStream = ossObject.getObjectContent();
            StringBuilder objectContent = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String line = reader.readLine();
                if (line == null)
                    break;
                objectContent.append(line);
            }
            inputStream.close();
            System.out.println("Object：" + firstKey + "的内容是：" + objectContent);

            // 文件存储入OSS，Object的名称为fileKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
//            String fileKey = "README.md";    //这是另外一个object对象文件
//            //new File("README1.md")); 这个是读取当前项目的README1.md 的这个文件
//            ossClient.putObject(bucketName, fileKey, new File("README1.md"));
//            System.out.println("Object：" + fileKey + "存入OSS成功。");

  
            	
            //5.这个是查看oos库里面所有的文件
            // 查看Bucket中的Object。详细请参看“SDK手册 > Java-SDK > 管理文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_object.html?spm=5176.docoss/sdk/java-sdk/manage_bucket
           // ObjectListing objectListing = ossClient.listObjects(bucketName);
            
            
            String maker = "";
            boolean flag = true;
            
            List<OSSObjectSummary> data = new ArrayList<OSSObjectSummary>();
            do {
            	 ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
                 listObjectsRequest.setPrefix("resources/audio/music/");//指定下一级文件
                 listObjectsRequest.setMarker(maker);  //获取下一页的起始点，它的下一项
                 listObjectsRequest.setMaxKeys(100); //设置分页的页容量
                 listObjectsRequest.setDelimiter("/");//跳出递归循环，只去指定目录下的文件。使用它时 Prefix文件路径要以“/”结尾
                 ObjectListing objectListing = ossClient.listObjects(listObjectsRequest);

               //  Console.WriteLine("List objects succeeded");
                 
                 
             
                 List<OSSObjectSummary> objectSummary = objectListing.getObjectSummaries();
                 
                
                 System.out.println("您有以下Object：");
                 for (OSSObjectSummary object : objectSummary) {
                	 
//                	 System.out.println(object.getKey()+","+object.getETag());
                 	//before()或者after()方法的返回值为boolean类型
                 	Date lastModiyDate = object.getLastModified();
                 	
                 	String nowSr = "2020-06-06 00:00:00";

                 	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                 	Date now = format.parse(nowSr);
                 	
                 	if(lastModiyDate.after(now)) {
                 		data.add(object);
                 	}
                 	 maker = objectListing.getNextMarker();
                    // System.out.println("\t" + object.getKey());
                 }
                 flag = objectListing.isTruncated();   //全部执行完后，为false
            }while(flag);
           
            
//            for (OSSObjectSummary object : data) {
//            	System.out.println("\t" + object.getKey());
//            }
            
          //6.从oss库里面删除文件
            // 删除Object。详细请参看“SDK手册 > Java-SDK > 管理文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_object.html?spm=5176.docoss/sdk/java-sdk/manage_bucket
//            ossClient.deleteObject(bucketName, firstKey);
//            System.out.println("删除Object：" + firstKey + "成功。");
//            ossClient.deleteObject(bucketName, fileKey);
//            System.out.println("删除Object：" + fileKey + "成功。");
            
            /*
            //将获取的数据插入到数据库中 
            for (OSSObjectSummary object : data) {
            	String key = object.getKey();
            	String songName = key.substring(key.lastIndexOf("/")+1);
            	
            	Song song = new Song();
            	song.setSongName(songName);
//            	song.setImgUrl("郭德纲于谦1.png");
//            	if(songName.indexOf("单口") != -1) {
//            		song.setTypeId(19);
//            	}else {
//            		song.setTypeId(20);
//            	}
            	song.setTypeId(17);
            	song.setCreateDate(new Date());
            	
            	//添加数据
            	songService.addSong(song);
            	
            	//批量删除错误的数据
            	//songService.deleteSong(song);
            	
            }
            */
            
            
            
            

        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }

        logger.info("Completed");
	}
	
	
	
	
	@Test
	public void strTest() {
		String str ="resources/audio/music/郭德纲单口【枪毙任老道2】-320x240.mp3";
		System.out.println(str.substring(str.lastIndexOf("/")+1));
		
		
		
	}
}
