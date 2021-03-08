package com.healthhelper.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller //返回一个页面，用Controller
public class UploadController {

    @Value("${business.host}")
    String imageHost;

    @GetMapping(value = "/upload")
    public String upload(){
        return "upload";
    }

    @PostMapping(value = "/upload")
    @ResponseBody
    public String upload(@RequestParam("userpic") MultipartFile file){

        if(file!=null&&file.getOriginalFilename()!=null){
            //获取文件原名
            String originalFilename = file.getOriginalFilename();//xxx.png
            //获取文件扩展名
            String extend = originalFilename.substring(originalFilename.lastIndexOf("."));//.png
            //重新生成唯一的文件名
            String newName = UUID.randomUUID().toString();
            //保存文件
            File file1 = new File("D:"+File.separator+"upload",newName+extend);
            try {
                file.transferTo(file1);
                return imageHost+newName+extend;
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        return null;
    }
}
