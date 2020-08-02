package com.pb.weixin.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;

@PropertySource("classpath:application.properties")
@Component
public class UploadImage {

	static Logger logger = Logger.getLogger(UploadImage.class);

	// 1.配置oos的链接信息
	// endpoint是访问OSS的域名。如果您已经在OSS的控制台上 创建了Bucket，请在控制台上查看域名。
	// 如果您还没有创建Bucket，endpoint选择请参看文档中心的“开发人员指南 > 基本概念 > 访问域名”，
	// 链接地址是：https://help.aliyun.com/document_detail/oss/user_guide/oss_concept/endpoint.html?spm=5176.docoss/user_guide/endpoint_region
	// endpoint的格式形如“http://oss-cn-hangzhou.aliyuncs.com/”，注意http://后不带bucket名称，
	// 比如“http://bucket-name.oss-cn-hangzhou.aliyuncs.com”，是错误的endpoint，请去掉其中的“bucket-name”。
	private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";

	// accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
	// 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
	// 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
	
	
	private static String accessKeyId ;
	
	private static String accessKeySecret;
	
	@Value("${aliyun.oss.accessKeyId}")
	public void setAccessKeyId(String accessKeyId) {
		UploadImage.accessKeyId = accessKeyId;
	}
	
	@Value("${aliyun.oss.accessKeySecret}")
	public void setAccessKeySecret(String accessKeySecret) {
		UploadImage.accessKeySecret = accessKeySecret;
	}


	// Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
	// Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
	private static String bucketName = "pb-20191014";

	// Object是OSS存储数据的基本单元，称为OSS的对象，也被称为OSS的文件。详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
	// Object命名规范如下：使用UTF-8编码，长度必须在1-1023字节之间，不能以“/”或者“\”字符开头。
	// Object其实就是你要创建的文件的名称 ，注意要带上文件的后缀名
	// private static String firstKey = "pbObj.txt";

	
//	@Scheduled(initialDelay=1000,fixedDelay=1000*100)
	public static void main(String[] args) {
		uploadImage();
	}

	public static void uploadImage() {
		// 日志配置，OSS Java SDK使用log4j记录错误信息。示例程序会在工程目录下生成“oss-demo.log”日志文件，默认日志级别是INFO。
		// 日志的配置文件是“conf/log4j.properties”，如果您不需要日志，可以没有日志配置文件和下面的日志配置。
		PropertyConfigurator.configure("conf/log4j.properties");

		logger.info("Started");

		// 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
		// 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/init.html?spm=5176.docoss/sdk/java-sdk/get-start
		OSSClient ossClient = new OSSClient(endpoint, UploadImage.accessKeyId, UploadImage.accessKeySecret);

		try {
			// 2.判断bucketName库是否创建
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

			// 3.查看bucketName库的信息
			// 查看Bucket信息。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
			// 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
			BucketInfo info = ossClient.getBucketInfo(bucketName);
			System.out.println("Bucket " + bucketName + "的信息如下：");
			System.out.println("\t数据中心：" + info.getBucket().getLocation());
			System.out.println("\t创建时间：" + info.getBucket().getCreationDate());
			System.out.println("\t用户标志：" + info.getBucket().getOwner());

			// 上传文件
			// 文件存储入OSS，Object的名称为fileKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
			// 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
			// String fileKey = "README.md"; //这是另外一个object对象文件
			// new File("README1.md")); 这个是读取当前项目的README1.md 的这个文件

			// System.out.println(System.getProperty("user.dir")); //这个是获取当前项目所在的跟目录
			String path = System.getProperty("user.dir") + "/src/main/resources/static/image";
			File file = new File(path);
			File[] tempList = file.listFiles();

			for (int i = 0; i < tempList.length; i++) {
				String fileKey = "资源/image/"; // 要存放图片的地方
				if (tempList[i].isFile()) {
					System.out.println(tempList[i].toString());
					String fullFileName = tempList[i].toString();

					String fileName = fullFileName.substring(fullFileName.lastIndexOf("\\") + 1);
					System.out.println(fileName);

					fileKey += fileName;
					ossClient.putObject(bucketName, fileKey, new File(fullFileName));
					
					// 当我们把所有的图片都已经上传完，我们应该把我们本地的图片删除掉
					delFile(new File(fullFileName));
				}
				if (tempList[i].isDirectory()) {
					// 这里就不递归了，
				}
			}

			

			// ossClient.putObject(bucketName, fileKey, new
			// File("src/main/resources/static/image"));
			// System.out.println("Object：" + fileKey + "存入OSS成功。");

			// 5.这个是查看oos库里面所有的文件
			// 查看Bucket中的Object。详细请参看“SDK手册 > Java-SDK > 管理文件”。
			// 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_object.html?spm=5176.docoss/sdk/java-sdk/manage_bucket
//            ObjectListing objectListing = ossClient.listObjects(bucketName);
//            List<OSSObjectSummary> objectSummary = objectListing.getObjectSummaries();
//            System.out.println("您有以下Object：");
//            for (OSSObjectSummary object : objectSummary) {
//                System.out.println("\t" + object.getKey());
//            }

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

	
	//删除文件
	private static boolean delFile(File file) {
		if (!file.exists()) {
			return false;
		}

		if (file.isFile()) {
			return file.delete();
		} else {
			File[] files = file.listFiles();
			for (File f : files) {
				delFile(f);
			}
			return file.delete();
		}
	}

}
