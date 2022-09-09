package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
public class UploadController {

    /**
     * MultipartFile是接收上传文件的对象
     * 由SpringMVC提供的文件上传解析器，将上传文件封装成MultipartFile对象
     */
    @PostMapping("/upload")
    public String upload(MultipartFile file) throws IOException {

        //获取文件大小
        long fileSize = file.getSize();
        //获取文件原始名称
        String fileName = file.getOriginalFilename();
        System.out.println("文件大小:" + fileSize);
        System.out.println("文件名称:" + fileName);
        /**
         * 在传输文件之前的处理
         * 1.将文件信息写入指定的数据库
         * 2.文件改名 UUID
         * 3.分目录
         */
        String newFileName = UUID.randomUUID().toString().replace("-", "")
                            + fileName.substring(fileName.lastIndexOf("."));
        Date today = new Date();
        SimpleDateFormat s1 = new SimpleDateFormat("yyyy");
        String year = s1.format(today);
        SimpleDateFormat s2 = new SimpleDateFormat("MM");
        String month = s2.format(today);
        SimpleDateFormat s3 = new SimpleDateFormat("dd");
        String day = s3.format(today);
        File dist = new File("d:/pic/" + year + "/" + month + "/" + day);
        if(!dist.exists()){
            dist.mkdirs();
        }

        /**
         * 存储位置
         * 1.操作系统的文件系统
         * 2.文件服务器
         */
        //将上传文件存储指定的位置
        file.transferTo(new File(dist.getAbsolutePath() + "/" + newFileName));

        return "index";
    }

    @PostMapping("/uploads")
    public String uploads(MultipartFile[] file) throws IOException {
        for (MultipartFile multipartFile : file) {
            String fileName = multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File("d:/pic/" + fileName));
        }
        return "index";
    }

    /**
     * 文件下载
     * 1.设置响应头
     * 2.通过流实现文件传输
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) throws IOException {

        response.addHeader("content-disposition", "attachment;fileName=" + name);
        //IO流
        FileInputStream in = new FileInputStream("d:/pic/" + name);
        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024];
        while(in.read(buffer) != -1){
            out.write(buffer);
        }
        in.close();
        out.flush();
        out.close();
    }

    /**
     * 虚拟路径映射
     * tomcat的配置
     * server.xml中找到<Host>标签
     * 配置如下内容
     * <Context docBase="d:/pic/" path="/pp"/>
     */
}
