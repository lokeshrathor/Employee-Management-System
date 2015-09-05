package com.employee.service.mailServices;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SimpleMailMessage simpleMailMessage;
	
	public void sendMail(String toMail, String dear, String content) throws MessagingException{
		
		//String emailTo = request.getParameter("email");
		
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setFrom(simpleMailMessage.getFrom());
		helper.setTo(simpleMailMessage.getTo());
		//helper.setTo(toMail);
		helper.setSubject(simpleMailMessage.getSubject());
		helper.setText(String.format(simpleMailMessage.getText(), dear, content));
		
		FileSystemResource file = new FileSystemResource("D:\\abc.txt");
		helper.addAttachment(file.getFilename(), file);
		mailSender.send(message);
		System.out.println("Mail send with attachment successfully to "+toMail);
		
	}

}
