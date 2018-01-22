package com.learn.springboot.email;

import java.io.File;

import javax.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SendEmail {

	@Autowired
	private JavaMailSender mailSender;
	
	/**
	 * 
	 * @Title: sendSimpleMail   
	 * @Description: 纯文本邮件发送
	 * @throws Exception           
	 * @throws
	 */
	public void sendSimpleMail() throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("wangcongming2018@qq.com");
		message.setTo("wcmingchina@163.com");
		message.setSubject("主题：简单邮件");
		message.setText("测试邮件内容");
		log.info("=============简单邮件测试=========");
		mailSender.send(message);
		log.info("++++++发送成功===========");
	}
	
	/**
	 * 
	 * @Title: sendAttachmentsMail   
	 * @Description: 带附件的邮件 
	 * @throws Exception           
	 * @throws
	 */
	public void sendAttachmentsMail() throws Exception {
		log.info("=============带附件的邮件测试=========");
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("wangcongming2018@qq.com");
		helper.setTo("wcmingchina@163.com");
		helper.setSubject("主题：有附件");
		helper.setText("有附件的邮件");
		FileSystemResource file = new FileSystemResource(new File("F:/test_data/photo/08dc910ce2dd.jpg"));
		helper.addAttachment("F:/test_data/photo/9e3df8dcd100baa1706f8e964c10b912c8fc2e58.jpg", file);
		helper.addAttachment("F:\\test_data\\text\\test1.docx", file);
		mailSender.send(mimeMessage);
		log.info("++++++发送成功===========");
	}
	
	/**
	 * 
	 * @Title: sendInlineMail   
	 * @Description:  
	 * @throws Exception           
	 * @throws
	 */
	public void sendInlineMail() throws Exception {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("wangcongming2018@qq.com");
		helper.setTo("wcmingchina@163.com");
		helper.setSubject("主题：嵌入静态资源");
		helper.setText("<html><body><img src=\"cid:08dc910ce2dd\" ></body></html>", true);

		FileSystemResource file = new FileSystemResource(new File("F:/test_data/photo/08dc910ce2dd.jpg"));
		helper.addInline("08dc910ce2dd", file);

		mailSender.send(mimeMessage);
		log.info("++++++发送成功===========");
		log.warn("++++++发送成功===========");
		log.error("++++++发送成功===========");

	}
}
