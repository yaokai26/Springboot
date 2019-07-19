# Springboot
### 注解
SpringbootApplication中注解\
@SpringBootApplication=@ComponentScan + @SpringBootConfiguration + @EnableAutoConfiguration\
@RestController = @Controller + @ResponseBody

#### 1、Get请求
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
  如果提示no main manifest attribute, in jar包里，找不到启动类入口,需要在pom.xml里添加依赖
  
      <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
     </build>
     
