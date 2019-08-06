# Springboot
### 注解
SpringbootApplication中注解\
@SpringBootApplication=@ComponentScan + @SpringBootConfiguration + @EnableAutoConfiguration\
注意：SpringBootApplication类还有几个方法，可以排除特定的包名或者类，也可以扫描指定的类或者包名\
 
    1.@SpringBootConfiguration包含@Configuration，标注当前是配置类，会把@Bean标注的方法纳入spring容器中，实例名就是方法名
      @Configuration标注在类上，相当于把类当做spring的xml配置文件中的<beans>，配置spring容器(应用上下文)
      @Bean标注在方法上，相当于spring配置文件中的<bean>，作用为注册bean对象
    2.@ComponentScan 可以通过该注解指定扫描某些包下含有如下注解(@Component、@Service、 @Repository、 @Controller、@Entity)
      的均注册为spring beans
    3.@EnableAutoConfiguration 根据定义在classpath下的类，自动生成bean,并加载到Spring的Context中
  
@RestController = @Controller + @ResponseBody


#### Get请求
1.单一参数@RequestMapping(path="/{id}",method=RequestMethod.GET)\
 1)public Object findUser(@PathVariables String id){}\
 2)public Object findUser(@PathVariables String id,@PathVariables String name){}
 
2.springboot注解的好处\
 1)Get请求：@GetMapping = @RequestMapping(method = RequestMethod.GET)\
 2)POST请求：@PostMapping = @RequestMapping(method = RequestMethod.POST)\
 3)PUT请求：@PutMapping = @RequestMapping(method = RequestMethod.PUT)\
 4)DELETE请求：@DeleteMapping = @RequestMapping(method = RequestMethod.DELETE)
 
3.@RequestParam(defaultValue= "0",name="abc",required=true)\
 1)public Object getUser(@RequestParam(defaultValue="0",name="page") int from){}\
 2)public Object getUser(@RequestParam(defaultValue="0",name="page") int from,@RequestParam(defaultValue="1",name="page2") int to){}
 
4.@RequestBody 请求映射实体类\
 1)public Object saveUser(@RequestBody User user){}
 
5.@RequestHeader 请求头,鉴定权限\
 1)public Object getHeader(@RequestHeader("access_token") String accessToken,String id){}
 
6.HttpServletRequest从request.getParameter("")获取\
 1)public Object getRequest(HttpServletRequest request){}

### 常用Json框架和注解
javaBean转换成Json，性能:Jackson>FastJson>Gson>Json-lib (测试方法：循环序列化，百万次，次数达到一定才可以看出差异)\
jackson处理相关自动：\
  指定字段不返回：@JsonIgnore\
  指定日期格式：@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",locale="zh",timezone="GMT+8")\
  空字段不返回：@JsonInclude(Include.NON_NULL)\
  指定别名：@JsonProperty

### 目录资源结构
1.目录讲解：src/main/java:存放代码\
           src/main/resources\
             static:存放静态文件，如css,js,image\
             template:存放静态页面,jsp,html,tpl\
             config:存放配置文件.application.properties
             
2.同个文件的加载顺序\
Springboot会挨个从里面查找\
  META/resources > resources > static > public
  
3.返回页面引入依赖thymeleaf,模板引擎，查找的是templates文件夹下的静态文件
        
      <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>  
        
注意：在返回页面的时候，使用@RestController注解时，需要配合视图解析器ModelAndView mv = new ModelAndView("index");
否则返回不了页面。如果是@Controller，直接return "index"即可。

4.css文件,图片文件返回,资源文件在springboot默认加载的文件夹static,resources目录下,可以直接访问,默认进去查找,只要输入里面的子目录,css、images等\
spring.resources.static-locations这个配置配置的就是springboot直接访问页面或者资源的查找路径

5.默认配置

    spring.resources.static-locations=classpath:/META-INF/resources/,
    classpath:/resources/,classpath:/static,classpath:/public/(,classpath:/test/  可自定义读取文件夹位置)

6.静态资源文件存储在CDN(访问量大，耗时，一般与java分离，静态资源服务器)

7.文件上传：MultiPartFile
   
    MultiPartFile源自SpringMVC,MultiPartFile对象的transferTo方法用于文件保存，效率和操作比FileOutStream
    更高效和方便。

8.Jar包方式运行web项目的文件上传和访问处理\
  1.文件大小的限制，放在启动类里SpringBootApplication类里，其实只要有@Configuration注解的类就行
  
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //设置单个文件最大
        factory.setMaxFileSize("102400KB");//硬编码 后期可以放在配置文件里
        //设置总上传文件大小
        factory.setMaxRequestSize("102400000KB");
        return factory.createMultipartConfig();
    }
    
  2.maven project将springboot项目打包install,打成jar包放在target路径下,cmd执行启动jar包：java -jar springboot-0.0.1-SNAPSHOT.jar启动。\
  如果提示no main manifest attribute, in jar包里，找不到启动类入口,需要在pom.xml里添加依赖,生成manifest文件
  
      <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
     </build>
     
### 热部署 
1.添加依赖

         <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>

2.不被热部署的文件\
  1）/META-INF/maven,/META-INF/resources,/resources,/static,/public,or /templates \
  2)指定文件不进行热部署 spring.devtools.restart.exclude=static/**,public** \
  3)手工触发重启 spring.devtools.restart.trigger-file=trigger.txt 改代码不重启，通过一个文本去控制
  
### springboot配置文件映射到属性和实体类
1.方式一：\
    Controller上注解：@PropertySource({"classpath:application.properties"})\
    增加字段属性:@Value("${test.name}")\
2.方式二：实体类配置\
    实体类加上注解@Component\
                 @PropertySource({"classpath:application.properties"})\ 
                 @ConfigurationProperties(prefix="test")  直接通过名称(需要一一对应)去映射，无需@Value注解，如果要加@Value("${test.name}")，可以名字不对应。

                
 ### springboot 单元测试
@RunWith(SpringRunner.class)\
@SpringBootTest(classes={SpringbootApplication.class})//启动整个项目

SpringBoot测试进阶之MocKMvc: \
  1)增加类注解 @RunWith(SpringRunner.class)\
              @AutoConfigureMockMvc\
              @SpringBootTest(class={SpringBootApplication.class})\
  2)相关API\
           perform:执行一个RequestBuilder请求\
           andExcept:添加ResultMatcher -> MockMvcResultMatcher验证规则\
           andReturn:最后返回相应的MvcResult-> Response
           
### SpringBoot 异常处理 
 1.异常注解介绍\
  @ControllerAdvice如果是返回Json格式，用@RestControllerAdvice就可以不加@ResponseBody
  
  //捕获全局异常，处理所有不可知的异常\
  @ExceptionHandler(value=Exception.class)
  
 2.自定义异常和页面跳转\
  放在templates下，需要配置application.properties路径，页面跳转还需要通过ModelAndView来实现。
    @ExceptionHandler(value=MyException.class)

### SpringBoot 项目启动方式以
1.IDE启动：run 'SpringBootApplication'\
2.JAR包：install项目，java -jar XXX.jar 方式启动\
3.WAR包：\
   1)首先pom文件添加打包方式，将jar修改为war

     <!-- 打包方式  -->
    <packaging>war</packaging>
    build标签添加构建项目的名称
    <finalName>Yaokai_SpringBoot</finalName>
      
   2)修改启动类,
   
    public class SpringbootApplication extends SpringBootServletInitializar{
      @Override
      protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootApplication.class);//class文件是当前类名，别写错。
      }
      
      public static void main(String[] args){
        SpringApplication.run(SpringbootApplication.class,args);
      }
    }
  3)打包，将项目war包放入tomcat的webapp文件夹下，启动tomcat

### Springboot过滤器Filter 
 1.优先级：值越小，优先级越高 Ordered.HIGHEST.PERCEDENCE、Ordered.LOWEST.PERCEDENCE \
 2.springboot启动默认的过滤器有：characterEncodingFilter、hiddenHttpMethodFilter、requestContextFilter等\
 3.自定义filter
 
    1.启动类增加@ServletComponentScan进行扫描
    2.新建一个Filter类,实现Filter接口
    3.@WebFilter标记该接口,被spring扫描，urlPattern：拦截规则，支持正则
    4.控制chain.doFilter方法的调用，来实现是否通过放行，不放行，web应用通过resp.sendRedirect("index.html")

### Springboot 原生servlet、Listener注解及应用
    1、原生Servlet
    1）@WebServlet(name="userServlet",urlPattern="/v1/api/test/customs")
    2）extends HttpServlet
    2、Listener
    1）@WebListener
    2) 监听器有多种，ServletRequestListener、ServletContextListener、HttpSessionListener,使用时实现不同接口即可
   
### Springboot 自定义拦截器Interceptor
     需要实现WebMvcConfigurer来添加自定义拦截器,注册拦截器
    1.@Configuration注解，implements WebMvcConfigurer(这个就是做web配置的)
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/api2/*/**");
        super.addInterceptors(registry);
    }
    2.自定义拦截器实现HandlerInterceptor，重写三个方法
    preHandle：在调用controller之前，通常用于权限校验等(HandlerInterceptor.super.preHandle())
    postHandle：在调用完controller之后，视图渲染之前(HandlerInterceptor.super.postHandle())如果controller异常，不会调用
    afterCompletion:整个完成之后，通常用于资源清理(HandlerInterceptor.super.afterCompletion())不管有没有异常，都会调用
    3.拦截器注意事项：
    1)先注册先拦截(WebMvcConfigurer里注册)
    2)配置拦截器@Configuration,拦截路径/*/,最后路径为/**,如上

执行顺序有点诡异：
    
    LoginInterceptor----->preHandle is running  -----先注册先执行
    SecondInterceptor------>preHandle is running
    in controller-----> controller is running 
    SecondInterceptor------>postHandle is running   --------但是controller执行完后,后注册的先执行，队列queue的概念？
    LoginInterceptor----->postHandle is running
    SecondInterceptor------>afterCompletion is running
    LoginInterceptor----->afterCompletion is running

过滤器和拦截器的顺序：Filter--> Interceptor-->controller执行--> Interceptor-->Filter

### Starter讲解
  1.模板引擎Thymeleaf：轻量级的模板引擎，可以直接在浏览器打开并且正确显示模板页面，html结尾，用法同freemarker
  
     #开发时关闭缓存，不然没法看到实时页面
     spring.thymeleaf.cache=false
     spring.thymeleaf.mode=HTML5
     #前缀
     spring.thymeleaf.prefix=classpath:/templates/
     #编码
     spring.thymeleaf.encoding=UTF-8
     #类型
     spring.thymeleaf.servlet.content-type=text/html
     #名称的后缀
     spring.thymeleaf.suffix=.html
 
### springboot整合Mybatis
OA系统：办公自动化\
OA系统比较喜欢用hibernate，ORM框架，互联网行业更多用Mybatis，半ORM，不提供对象和关系模型的直接映射\
1.导入Mybatis依赖：
  
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.2</version>
            <scope>runtime</scope>
        </dependency>
          <!-- MySQL的JDBC驱动包-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <!-- 引入第三方数据源-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.6</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.6</version>
        </dependency>        
2.application.properties配置

    #mysql数据库配置
    spring.datasource.url=jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC
    spring.datasource.username=root
    spring.datasource.password=password
    #注释掉下面的数据源，默认使用HikariDataSource数据源，这个属性用来切换数据源
    #spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
    #打印sql语句
    mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
注意：&serverTimezone=UTC这个用来标明时区

3.mapper开发：\
mapper包是数据库的操作，controller--> service --> mapper,所以开发的顺序，先开发mapper类，\
@Insert("INSERT INTO t_user_info(name,phone,create_time,age) VALUES (#{name},#{phone},#{createDate},#{age})")\
@Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")//keyProperty是javabean的属性，keyColumn是数据库字段\
取值用#{}而不用${}，因为#{}是预编译的，可以防止sql注入

4.service开发:\
5.Controller开发:\
6.启动类：
@MapperScan("com.dayee.springboot.mapper")扫描mapper包

7.CRUD增删改查
 
    @Insert("INSERT INTO t_user_info(name,age,create_time,phone) VALUES(#{name},#{age},#{create_time},#{phone})")
    @Options(useGeneratedKeys =true,keyProperty = "id",keyColumn = "id")
    int insert(User user);

    @Select("SELECT * FROM t_user_info")
    @Results({
            @Result(column = "create_time",property = "create_time")
    })
    List<User> getAll();

    @Select("SELECT * FROM t_user_info WHERE id = #{id}")
    @Results({
            @Result(column = "create_time",property = "create_time")
    })
    User findById(long id);

    @Update("UPDATE t_user_info SET name=#{name} WHERE id=#{id}")
    void update(User user);

    @Delete("DELETE FROM t_user_info WHERE id =#{id}")
    void delete(long userId);
    
8.事务处理\
一般分单机事务和分布式事务。事务一般放在service层,当然也不是不能放在其他层面。

       <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>5.1.8.RELEASE</version>
       </dependency>
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)//隔离级别和传播行为\
传播行为有7种：

    1 REQUIRED(0)
    需要事务，它是默认传播行为，如果当前存在事务，就沿用当前事务，否则新建一个事务运行子方法。
    2 SUPPORTS(1)
    支持事务，如果当前存在事务，就沿用当前事务，如果不存在，则继续采用无事务的方式运行子方法。
    3 MANDATORY(2)
    必须使用事务，如果当前没有事务，则会抛出异常，如果存在当前事务，就沿用当前事务。
    4 REQUIRES_NEW(3)
    无论当前事务是否存在，都会创建新事务运行方法，这样新事务就可以拥有新的锁和隔离级别等特性，与当前事务相互独立。
    5 NOT_SUPPORTED(4)
    不支持事务，当前存在事务时，将挂起事务，运行方法。
    6 NEVER(5)
    不支持事务，如果当前方法存在事务，则抛出异常，否则继续使用无事务机制运行。
    7 NESTED(6)
    在当前方法调用子方法时，如果子方法发生异常，只回滚子方法执行过的SQL，而不回滚当前方法的事务。

    常用的传播行为主要有三种：REQUIRED 、REQUIRES_NEW、 NESTED。

隔离级别有4种：分别为：未提交读、读写提交、可重复读和串行化

### springboot整合定时任务和异步任务
1.常见的定时任务：
       
       1、java自带的java.util.Timer类，配置比较麻烦，时间延后问题
       2、Quartz
       3、SpringBoot注解：1)启动类添加@EnableScheduling开启定时任务，自动扫描
                                2) 定时任务类添加@Component被容器扫描
                                3) 定时方法加@Scheduled(fixedRate=2000) 每2秒执行一次
                                fixedDelay上次执行延后2秒

2.异步任务
启动类添加@EnableAsync,@Async标注一个类或者方法,表面该类是异步类或者该方法是异步方法,注意不能直接写到Controller类里\
要获取异步任务结果，Future<String>返回AsyncResult<String>("执行完成"),Controller类里判断任务是否结束用task.isDone()


### springboot整合logback日志框架
日志级别：DEBUG < INFO < WARN < ERROR \ 
java -jar  xxx.jar --Debug 用jar包启动debug级别的日志 \

### springboot整合redis
常见redistemplate种类讲解和缓存实操(使用自动注入)

    1、注入模板
    @Autowired
    private StirngRedisTemplate strTplRedis
    2、类型String，List,Hash,Set,ZSet
    对应的方法分别是opsForValue()、opsForList()、opsForHash()、opsForSet()、opsForZSet()

### springboot整合elasticsearch
1.ES的应用场景：\
mysql:like,性能问题\
solr:针对企业 lucene\
elasticsearch:针对数据量特别大 PB TB\
特点：全文检索，结构化检索，数据统计、分析，接近实时处理，分布式搜索(可部署数百台服务器)，处理PB级别的数据	搜索纠错，自动完成\
			
    mysql：database     table                 rocord 
    es   : index	type（只能存在一个)    document

2.整合ES\

	<dependency>  
	     <groupId>org.springframework.boot</groupId>  
	     <artifactId>spring-boot-starter-data-elasticsearch</artifactId>  
	</dependency>  
	
Repository包下新建接口继承ElasticSearchRepository,类上添加注解@Document(indexName="blog",type="article"),注意要小写
同时，启动类要添加注解扫描Repository包下的类，@EnableElasticSearchRepositories(basePages="XXXX")，不然启动会报错！

	spring.data.elasticsearch.cluster-name=elasticsearch 
	spring.data.elasticsearch.cluster-nodes=localhost:9300 
	spring.data.elasticsearch.repositories.enabled=true 
	
QueryBuilder的使用：QueryBuilder queryBuilder = QueryBuilders.matchQuery("title","123");\
查看ES信息：
			
	查看索引信息：http://localhost:9200/_cat/indices?v
	查看某个索引库结构：http://localhost:9200/blog
	查看某个对象：http://localhost:9200/blog/article/1
	
### JMS(java message service JAVA消息服务)
JMS提供者：Apache ActiveMQ,RabbitMQ,KafKa,Notify,MetaQ,RocketMQ\
1.依赖	
	
	<!-- 整合消息队列ActiveMQ -->
        <dependency>  
            <groupId>org.springframework.boot</groupId>  
            <artifactId>spring-boot-starter-activemq</artifactId>  
        </dependency>  
	 <!-- 如果配置线程池则加入 -->
        <dependency>  
            <groupId>org.apache.activemq</groupId>  
            <artifactId>activemq-pool</artifactId>  
        </dependency>
此处有一个注意事项,springboot 2.1.1版本中,当application.properties中spring.activemq.pool.enabled=true时,
会使用JmsPoolConnectionFactory，并不在activemq-pool依赖中，需要引入如下依赖：见[链接1](https://blog.csdn.net/daibang2182/article/details/84971588)
	
	<dependency>
    	    <groupId>org.messaginghub</groupId>
            <artifactId>pooled-jms</artifactId>
	 </dependency>


