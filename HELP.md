# Getting Started




@Repository的作用：
这是因为该注解的作用不只是将类识别为Bean，同时它还能将所标注的类中抛出的数据访问异常封装为 Spring 的数据访问异常类型。
Spring本身提供了一个丰富的并且是与具体的数据访问技术无关的数据访问异常结构，用于封装不同的持久层框架抛出的异常，使得异常独立于底层的框架。

@EnableDiscoveryClient和@EnableEurekaClient共同点就是：都是能够让注册中心能够发现，扫描到改服务。
不同点：@EnableEurekaClient只适用于Eureka作为注册中心，@EnableDiscoveryClient 可以是其他注册中心。

1、@Service用于标注业务层组件
2、@Controller用于标注控制层组件(如struts中的action)
3、@Repository用于标注数据访问组件，即DAO组件.
4、@Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。

@RestController注解，相当于@Controller+@ResponseBody两个注解的结合，返回json数据不需要在方法前面加@ResponseBody注解了，但使用@RestController这个注解，就不能返回jsp,html页面，视图解析器无法解析jsp,html页面
@Controller 注解，在对应的方法上，视图解析器可以解析return 的jsp,html页面，并且跳转到相应页面, 若返回json等内容到页面，则需要加@ResponseBody注解


------------------------------------------------------------------------------------------------------------------------
## mongodb 的注解
@Id - 文档的唯一标识，在mongodb中为ObjectId，它是唯一的，通过时间戳+机器标识+进程ID+自增计数器（确保同一秒内产生的Id不会冲突）构成。

@Document - 把一个java类声明为mongodb的文档，可以通过collection参数指定这个类对应的文档。@Document(collection=“mongodb”) mongodb对应表

@DBRef - 声明类似于关系数据库的关联关系。ps：暂不支持级联的保存功能，当你在本实例中修改了DERef对象里面的值时，单独保存本实例并不能保存DERef引用的对象，它要另外保存，如下面例子的Person和Account。

@Indexed - 声明该字段需要索引，建索引可以大大的提高查询效率。

@CompoundIndex - 复合索引的声明，建复合索引可以有效地提高多字段的查询效率。

@GeoSpatialIndexed - 声明该字段为地理信息的索引。

@Transient - 映射忽略的字段，该字段不会保存到mongodb。

@PersistenceConstructor - 声明构造函数，作用是把从数据库取出的数据实例化为对象。该构造函数传入的值为从DBObject中取出的数据
------------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------------------
## spring中的ResponseEntity理解
https://www.cnblogs.com/flypig666/p/11729757.html
------------------------------------------------------------------------------------------------------------------------


使用nacos替代eureka为服务的注册中心

2.使用nacos替代apollo为服务的配置中心

3.引入使用sentinel替换掉hystrix，引入sentinel-dashboard

4.使用jetcache作两级缓存，优化缓存性能

5.网关启动时加载数据库中的路由到redis缓存


Q: 服务器间如何定交互接口
Q: 服务如何部署
Q: 服务器的异常如何处理（发生异常如何写，com.velen.etl.stream.service 的之类的）
A:
Q: yarn, flink 的地址配置？
Q: 使用组件的规定
Q:


------------------------------------------------------------------------------------------------------------------------
##基础配置说明

- application.yml 文件主要配置第三方连接及配置信息。
- bootstrap.yml 文件主要配置服务基本信息（端口，服务名称），注册中心地址等。
------------------------------------------------------------------------------------------------------------------------
+##组件
+｜ 组件类型 | 组件 |
+| --- | --- |
+|

------------------------------------------------------------------------------------------------------------------------
##MAVE发布
### SNAPSHOT
'''
mvn clean deploy
'''
### RELEASE
'''
'''



------------------------------------------------------------------------------------------------------------------------
## nacos 配置
| 配置项 | 键 | 默认值 | 描述 |
| --- | --- | --- | --- |
| 服务器地址 | spring.cloud.nacos.discovery.server-addr |  | nacos注册中心地址 |
| 服务名 | spring.cloud.nacos.discovery.com.velen.etl.stream.service | spring.application.name | 服务名 |
| 权重 | spring.cloud.nacos.discovery.weight | 1 | 值从1到100，值越大，重量越大 |
| IP | spring.cloud.nacos.discovery.ip |  | ip address to registry，最高优先级 |
| 网络接口 | spring.cloud.nacos.discovery.network-interface |  | 未配置IP时，注册的IP地址为网络接口对应的IP地址。如果未配置此项，则默认采用第一个网络接口的地址。 |
| 端口 | spring.cloud.nacos.discovery.port | -1 | 注册端口，无需配置即可自动检测 |
| namesapce | spring.cloud.nacos.discovery.namespace |  | 开发环境（dev、pro等）|
| accesskey | spring.cloud.nacos.discovery.access-key | | |
| secretkey | spring.cloud.nacos.discovery.secret-key | | |
| 元数据 | spring.cloud.nacos.discovery.metadata |  | 扩展数据，使用Map格式配置 |
| 日志名称 | spring.cloud.nacos.discovery.log-name | | |
| 端点 | spring.cloud.nacos.discovery.endpoint |  | 服务的域名，通过该域名可以动态获取服务器地址。 |
| 集成功能区 | ribbon.nacos.enabled |  true | |
| --- | --- | --- | --- |
------------------------------------------------------------------------------------------------------------------------

### dataflow

java -Dserver.port=8123 \
     -Dhttp.path-pattern=/data \
     -Dspring.cloud.stream.bindings.output.destination=sensorData \
     -jar http-source-rabbit-1.3.1.RELEASE.jar

java -Dserver.port=8090 \
 -Dspring.cloud.stream.bindings.input.destination=sensorData \
 -Dspring.cloud.stream.bindings.output.destination=normalizedSensorData \
 -jar transformer-0.0.1-SNAPSHOT.jar

java -Dlog.level=WARN \
     -Dspring.cloud.stream.bindings.input.destination=normalizedSensorData \
     -jar log-sink-rabbit-1.3.1.RELEASE.jar


stream create --name httpIngest --definition "http --server.port=8123 --path-pattern=/data | transformer --server.port=8090 | log --level=WARN" --deploy

------------------------------------------------------------------------------------------------------------------------

### auto configuration 如何实现的



------------------------------------------------------------------------------------------------------------------------
### 学习资料
https://blog.csdn.net/yzy199391/article/details/88551718



### 测试数据
2019-11-14 12:28:59.087 INFO   cwy-online [@175ba54f-655c-4bd4-875c-29506b72ceae] (inner-executor1-1) (com.storm.net.log4jConfig.TxDBLog) {"logname":"PlayerLogin","GameSvrId":"300","dtEventTime":"2019-11-14 12:28:59","vGameAppid":"1109920778","PlatID":0,"iZoneAreaID":100,"vopenid":"2584A6E8DDC8B10CD5BE66FE7B76E63F","Level":1,"PlayerFriendsNum":0,"ClientVersion":"1.3.390.11216","SystemSoftware":"iOS 12.0","SystemHardware":"iPhone7,2","TelecomOper":"","Network":"wifi","ScreenWidth":1334,"ScreenHight":750,"Density":326.0,"LoginChannel":8,"vRoleID":"184588875003953054","vRoleName":"克罗德","CpuHardware":"arm64","Memory":88888,"GLRender":"Metal","GLVersion":"Metal","DeviceId":"F8390CC1-4405-43DE-8D15-C29B89F20366","vClientIP":"/192.168.1.152","ClientBaseVersion":"1.3.390.11216","CpuFrequency":0,"CpuCount":0,"GLDevice":"Apple A8 GPU","Account":"lc01","iMoney":88964,"Cash":88889,"IslandNum":0,"HeroNum":8,"UnlockSkillNum":0,"SuitNum":8,"PartNum":0,"OrdinaryMotionNum":4,"ExclusiveMotionNum":0,"TagNum":0,"TutorialNum":0,"ItemId1":102,"ItemCount1":0,"ItemId2":103,"ItemCount2":0,"ItemId3":104,"ItemCount3":0,"ItemId4":105,"ItemCount4":0,"ItemId5":3501,"ItemCount5":0,"ItemId6":3502,"ItemCount6":0,"ItemId7":9999,"ItemCount7":0,"ItemId8":3503,"ItemCount8":0,"ItemId9":3504,"ItemCount9":0,"ItemId10":401,"ItemCount10":0,"ItemId11":3505,"ItemCount11":0,"ItemId12":402,"ItemCount12":0,"ItemId13":3506,"ItemCount13":0,"ItemId14":403,"ItemCount14":0,"ItemId15":405,"ItemCount15":0,"ItemId16":406,"ItemCount16":0,"ItemId17":407,"ItemCount17":0,"ItemId18":408,"ItemCount18":0,"ItemId19":409,"ItemCount19":0,"ItemId20":301,"ItemCount20":0,"ItemId21":302,"ItemCount21":0}
.*\(com.storm.net.log4jConfig.TxDBLog\).*(\{"logname":"(.+?)",.*\})
