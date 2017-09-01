Spring Cloud
========================================
#### Spring Cloud为开发人员提供了快速构建分布式系统中一些常见模式的工具（例如配置管理，服务发现，断路器，智能路由，微代理，控制总线，一次性令牌，全局锁，领导选举，分布式会话，集群状态）。
##### [Spring Cloud官网](http://projects.spring.io/spring-cloud/): http://projects.spring.io/spring-cloud/
##### [Spring Cloud中文网](https://springcloud.cc/): https://springcloud.cc/
##### [Spring Cloud中国社区](http://docs.springcloud.cn/): http://docs.springcloud.cn/
##### 注：支持需要 git、jdk、maven。
|名称|模块|说明
|---|---|---
|服务消费者Feign|api-member_feign|Feign是一种声明式、模板化的HTTP客户端
|服务消费者Ribbon|api-member_ribbon|Ribbon是一个基于HTTP和TCP客户端的负载均衡器
|Swagger接口文档|common-api-doc|接口页面文档化和强大的页面测试功能来调试每个RESTful API
|通用实体类|common-entity|通用实体类模块(分层领域模型规约)
|通用工具类|common-util|通用工具类模块
|微服务分布式配置中心|config-center|Spring cloud Config Server 微服务分布式配置中心
|MySQL数据库|data-mysql|MySQL分支MariaDB关系型数据库
|监控中心|monitor-center|Spring cloud监控中心
|Maven父管理|parent|Maven的顶级父统一管理maven模板和版本
|注册发现中心|registry-center|Spring cloud Eureka Registry Server 微服务注册发现中心
|网管|service-gateway| Zuul 是在云平台上提供动态路由,监控,弹性,权限,安全等边缘服务
|服务提供者模块|service-member|基于Spring Boot实现的MySQL、Druid、MyBatis等，服务提供者
### 使用说明
#### 1.使用parent编译maven的各个子模块，然后：
##### 注：先，修改parent/pom.xml下registry-center.addresss的地址，为自定义
```
在本地启动，各个@SpringBootApplication的类
在linux服务器，则启动各个模块: nohup java -jar *.jar & 
启动顺序:
registry-center
config-center
service-member
api-member_feign
其他的可以在registry和config后，并行启动，除了提供和消费依赖外。
```
#### 2.查看注册中心，启动registry-center后即可查看:（附录图片）
#### 3.监控中心图（附录图片）
#### 4.swagger文档(附录图片)
# 注：各个细节在，各个pom.xml和@SpringBootApplication都有说明~~~
![注册中心](http://loveshare.oss-cn-shanghai.aliyuncs.com/universal/image/github/spring-cloud/2.jpg)
# [监控中心](http://loveshare.oss-cn-shanghai.aliyuncs.com/universal/image/github/spring-cloud/3.jpg)
![监控中心](http://loveshare.oss-cn-shanghai.aliyuncs.com/universal/image/github/spring-cloud/3.jpg)
![监控中心](http://loveshare.oss-cn-shanghai.aliyuncs.com/universal/image/github/spring-cloud/4.jpg)
![swagger文档](http://loveshare.oss-cn-shanghai.aliyuncs.com/universal/image/github/spring-cloud/1.jpg)
