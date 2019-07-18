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
