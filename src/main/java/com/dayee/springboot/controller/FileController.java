package com.dayee.springboot.controller;

import com.dayee.springboot.PO.JsonData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
//@RestController
@PropertySource({"classpath:application.properties"})
public class FileController {
    /**
     * 使用@Controller时,直接返回页面名就可以，无需视图解析器
     * @return
     */
    @RequestMapping(value = "/api/v1/gopage")
    public Object index(){
        return "index";
    }

    /**
     * 注意：当使用@RestController时，需要配合ModelAndView视图解析器
     * @return
     */
    @RequestMapping(value = "/api/v1/gopage2")
    public ModelAndView  index2(){

        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

//    private static final String filePath = "D:/springboot_workspace/src/main/resources/static/image/";

//    private static final String filePath = "D:/image/";

    @Value("${web.file.path}")
    private  String filePath ;

    @RequestMapping("/upload")
    @ResponseBody
    public JsonData upload(@RequestParam("head_img")MultipartFile file, HttpServletRequest request){
        String name = request.getParameter("name");
        System.out.println("用户名为："+ name);
        System.out.println("文件路径为1："+ filePath);
        if(file.getSize()>1){

        }
        //获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("上传的文件名为："+ fileName);

        //获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("文件的后缀名为："+ suffixName);

        //文件上传的路径
        fileName = UUID.randomUUID()+suffixName;
        System.out.println("转换后的名称："+ fileName);
        File dest = new File(filePath+fileName);

        try {
            file.transferTo(dest);
            return  new JsonData(0,fileName,"上传成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JsonData(-1,null,"上传失败");
    }
}
