package com.app.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmailService {
	
	public EmailService() {
		System.out.println("in EmailService");
	}

	
	public boolean SendEmail(String subject, String message,String to) {
		
		
		boolean flag=false;
		String from ="nitesh.ghule2012@gmail.com";
		String host="smtp.gmail.com";
		
		Properties properties =System.getProperties();
		
		
		System.out.println("PROPERTIES"+properties);
		
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		
		Session session =Session.getInstance(properties, new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication("nitesh.ghule2012@gmail.com", "Ghule@1994");
	
			}
		});
		
		session.setDebug(true);
		
		MimeMessage  m =new MimeMessage(session);
		try {
			m.setFrom(from);
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(subject);
			m.setText(message);
		Transport.send(m);
			
			
			
			System.out.println("Send Success....");
			
			
			flag=true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag; 
		
	}
	

	
}
