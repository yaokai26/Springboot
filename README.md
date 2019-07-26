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

4.css文件,图片文件返回,资源文件在springboot默认加载的文件夹static,resources目录下,可以直接访问,默认进去查找,只要输入里面的子目录,css、images等

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
     需要实现WebMvcConfigurer来添加自定义拦截器
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
    1)先注册先拦截
    2)配置拦截器@Configuration,拦截路径/*/,最后路径为/** 如上
