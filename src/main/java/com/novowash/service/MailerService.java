package com.novowash.service;

import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.novowash.model.Mail;
import com.novowash.utils.GenUtilities;

@Service("mailService")
public class MailerService {

	Logger logger = Logger.getLogger(MailerService.class);

	@Autowired JavaMailSender mailSender;
	@Autowired Session session;
	
	@Autowired
	  private Environment env;
	
	
	public void sendEmail(final Mail mail) {

		if (GenUtilities.isValidEmail(mail.getMailTo())) {
			Thread mailThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						/*MimeMessage mimeMessage = mailSender.createMimeMessage();
						MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
						mimeMessageHelper.setSubject(mail.getMailSubject());
						//mimeMessageHelper.setFrom(fqcDBProperties.getProperty(0l, "mail.from.acct"));
						mimeMessageHelper.setTo(mail.getMailTo());
						mimeMessageHelper.setText(mail.getMailContent(), true);
						mimeMessageHelper.setSentDate(new Date());
						mailSender.send(mimeMessageHelper.getMimeMessage());*/
						
						 Message messageobj = new MimeMessage(session);
						 messageobj.setFrom(new InternetAddress(env.getProperty("mail.username")));
						   messageobj.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mail.getMailTo()));
						   messageobj.setSubject(mail.getMailSubject());
						   messageobj.setContent(mail.getMailContent(),"text/html");
						   messageobj.setSentDate(new Date());
						   Transport.send(messageobj);
						   logger.info("email sent successfully....");
					} catch (MessagingException e) {
						logger.error("MailerService : Error sending Email ", e);
					}
				}
			});
			mailThread.start();

		} else {
			logger.warn("MailerService :Invalid Email " + mail.getMailTo() + "Couldn't send email.");
		}

	}

	

}
