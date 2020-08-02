#  读我

## mybatis 二级缓存
可以去userDao.xml 文件下学习

``` xml
<mapper namespace="com.pb.weixin.dao.IUserDao">

	<!-- 开启二级缓存  ，当前namespace 下所有的查询都会走缓存
	eviction:表示是缓存策略存哪里？ 是硬盘还是内存
	flushInterval:周期刷新频率： 600000毫秒（60秒）  就是每60秒刷新一次缓存
	size: 内存大小512
	readOnly 是否只读
	-->
	<cache eviction="FIFO" flushInterval="60000" size="512" readOnly="true">
	</cache>
	
	<resultMap id="userMap" type="com.pb.weixin.vo.User">
	
	如果想指定一个查询不走缓存，可以配置useCache="false" 使其不适用缓存
	<select id="getUsers" resultMap="userMap" useCache="false">
        select * from t_user t <include refid="queryByCondition"></include>
    </select>
```