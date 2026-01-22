[toc]

# Learn

https://spring.io/projects/spring-boot#samples



> spring特性使用，spring特性原理源码，spring面试题

# sprinboot不是什么？

- Springboot不是应用服务器
- springboot不是javaEE之类的规范
- springboot不是代码生成器

# springboot是什么？

- 方便的创建可独立运行的程序
- 直接内嵌tomcat、jetty等服务器
- 简化了项目的构建配置
- 为spring及第三方库提供自动配置
- 提供了生产级特性
- 无需生成代码或进行xml配置

# springboot四大特性：

- 自动配置 auto configuration
- 起步依赖  starter dependency
- 命令行界面 springboot CLI   
- actuator  监控相关





# springboot init:

start.spring.io

- 查看maven依赖

  ``` xml
  <dependency>
  			<groupId>org.springframework.boot</groupId>
  			<artifactId>spring-boot-starter-web</artifactId>
  		</dependency>
  		<dependency>
  			<groupId>org.springframework.boot</groupId>
  			<artifactId>spring-boot-starter-actuator</artifactId>
  		</dependency>
  		<dependency>
  			<groupId>org.springframework.boot</groupId>
  			<artifactId>spring-boot-starter-data-redis</artifactId>
  		</dependency>
  		<dependency>
  			<groupId>org.springframework.boot</groupId>
  			<artifactId>spring-boot-starter-web</artifactId>
  		</dependency>
  ```

- maven打包

  ``` python
  mvn clean package -Dmaven.skip.test
  java -jar hello-spring...jar
  ```

- 如何引用自己的parent

``` xml
<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-dependencies</artifactId>
				<version>2.6.6</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

```
# springboot配置演示

- 引入数据库驱动h2
- 引入jdbc 依赖 spring-boot-jdbc-starter
- 获取 Datasource bean，打印信息
- 可以通过actuator/beans 查看bean



# 直接配置所需要的bean

- 数据源配置
  - DataSource(根据选择的连接池配置)
- 事务配置
  - PlatformTransactionManager
  - TransactionTemplate
- 操作可选 
  - JdbcTemplate

# spring boot 做了哪些配置？
- 配置了DataSource：DataSourceAutoConfiguration
- 配置了TransactionManager：DataSourceTransactionManagerAutoConfiguration
- 配置了JdbcTemplate： jdbctemplateautoconfiguration
- 符合条件时才进行配置

# hikariCP 数据库连接池

为什么这么快
- 字节码级别的优化（使用javaassit）
- 其他大量的优化
	- 使用FastStatementList代码ArrayList
	- 实现了CurrentBag实现了无锁集合
	- 代理类优化（比如使用invokestatic代替了invokevirtual）

# 在springboot中的配置

## Spring boot 2.x

- 默认使用HikariCP
- 配置了spring.datasource.hikari.x

## Spring boot 1.x

- 默认使用tomcat，需要移除tomcat-jdbc的依赖


# Durid应用

Durid连接池是阿里巴巴开源的数据库连接池项目。Durid是为监控而生，内置强大的监控功能，监控功能不影响性能。功能强大，能防止sql注入，内因logging能分析hack的行为。  --Alibaba Durid官网

## 优势
- 能经受住阿里巴巴大促的考验。
- 提供了详细的监控界面
- 提供了exception sorter，对主流的数据库的返回码都提供了支持。
- 防止sql注入
- 内置加密配置
- 众多扩展点，方便定制。
https://github.com/druid-io

## filter配置
- 用于定制sql操作的各种环节
- 可以继承filterEventAdapter方便的实现filter
- 修改META-INFO/durid-filter.properties增加filter配置
## 密码加密

## SQL防注入


## 连接池选择的一些考量的点
- 功能
- 性能
- 可用性 
- 扩展性：加密、trace
- 运维

# spring jdbc操作类

spring-jdbc
- core jdbcTemplate等相关的核心操作类和接口
- datasource 数据源相关的辅助类
- object 将基本的jdbc封装成对象
- support 错误码等其他的辅助工具



## 事务

- 编程式事务
  - TransactionTemplate
    - TransactionCallback
    - TransactionCallbackWithoutResult
  - PlateformTransactionManager
    - 传入TransactionDefinition进行定义 

- 声明式事务

<img src="/Users/haibingzhang/d/weiyundata/typoradoc/images/image-20220403164537108.png" alt="image-20220403164537108" style="zoom:50%;" />



## 基于注解的配置方式



### 开启事务注解方式

- @EnableTransactionManager
- <tx:annotation-driven>

### @Transactional

- TrsansactionManager
- propogation
- isolation
- timeout
- readonly
- 如何判断回滚





# 面试题

## 1、springboot 的starter 的启动原理是什么

- ① Spring Boot 在启动时会去classpath中中寻找 resources/META-INF/spring.factories 文件
- ② 根据 spring.factories 配置加载 AutoConfigure 类
- ③ 根据 @Conditional 注解的条件，进行自动配置并将 Bean 注入 Spring Context

## 2、springboot 是如何找到配置类的

这个主要是涉及jar包中资源的读取，让我们看下这个流程。

**主要的路径是下面这三个注解：**

```
SpringBootApplication ->
    EnableAutoConfiguration -> 
      AutoConfigurationImportSelector org.springframework.boot.autoconfigure.AutoConfigurationImportSelector#selectImports 
      org.springframework.boot.autoconfigure.AutoConfigurationImportSelector#getAutoConfigurationEntry 
      org.springframework.boot.autoconfigure.AutoConfigurationImportSelector#getCandidateConfigurations 
      org.springframework.core.io.support.SpringFactoriesLoader#loadFactoryNames 
      org.springframework.core.io.support.SpringFactoriesLoader#loadSpringFactories Enumeration<URL> urls = classLoader.getResources(FACTORIES_RESOURCE_LOCATION); 
      public static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";
```



## 3、springboot starter 的bean 是怎么加载到容器的

在springboot 加载到config的时候，可以在config中通过@bean进行容器注册，这个bean就会加载到容器

**这里主要要说几个特殊的注解，@ConditionalOnXXX**

- @ConditionalOnBean：当容器中有指定Bean的条件下进行实例化。
- @ConditionalOnMissingBean：当容器里没有指定Bean的条件下进行实例化。



# problem

## 1."Error:java: invalid source release: 11 in Intellij"

``` text
Error:java: invalid target release: 11 in Intellij
```

解决方案：

- pom.xml  -> java.version | <java.version>1.8</java.version>

- 2 versions：
  File -> Settings -> Build, Execution, Deployment -> Compiler -> Java Compiler ->> Project bytecode and Per-module bytecode.  
  Intellij IDEA -> Preferences -> Build, Execution, Deployment -> Compiler -> Java Compiler ->> Project bytecode and Per-module bytecode -> Target Bbytecode version. 

- File -> Project Structure -> Project -> Project SDK and Project laguage level

- File -> Project Structure -> Modules -> Module SDK
  
  
