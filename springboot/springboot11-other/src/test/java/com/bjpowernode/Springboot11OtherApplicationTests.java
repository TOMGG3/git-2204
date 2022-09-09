package com.bjpowernode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot11OtherApplicationTests {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送基本内容
     */
    @Test
    void testSend() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 发件人邮箱
        simpleMailMessage.setFrom("28718422@qq.com");
        // 收件人邮箱
        simpleMailMessage.setTo("28718422@qq.com");
        // 邮件主题
        simpleMailMessage.setSubject("这是一个测试邮件");
        // 邮件内容
        simpleMailMessage.setText("测试内容");
        javaMailSender.send(simpleMailMessage);
    }

    /**
     * 测试发送复杂内容，例如图片和附件等
     */
    @Test
    void testSend2() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // 创建一个邮件工具，可以发送附件
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"utf-8");
        mimeMessageHelper.setFrom("28718422@qq.com");
        mimeMessageHelper.setTo("28718422@qq.com");
        mimeMessage.setSubject("这是一个携带了图片和附件的邮件");
        //拼接内容参数
        StringBuilder sb = new StringBuilder();
        sb.append("<html> <body> <h1 style='color:red'>springboot 测试邮件发送复杂格式o</h1>");
        sb.append("<p style='color:blue;font-size:16px'>哈哈哈</p>");
        sb.append("<p style='text-align:center'>居中</p>");
        //如果要插入图片src='cid:picture'
        sb.append("<img src='cid:picture'/> </body></html>");
        //设置内容，可以被html解析
        mimeMessageHelper.setText(sb.toString(), true);
        // 从本地磁盘中读取到图片 站位到内容中去
        mimeMessageHelper.addInline("picture",new File("d:\\pic\\bb.jpg"));
        // 添加附件
        mimeMessageHelper.addAttachment("SpringBoot.doc",new File("D:\\course\\04SSM\\springboot\\document\\SpringBoot.doc"));
        javaMailSender.send(mimeMessage);
    }

}
