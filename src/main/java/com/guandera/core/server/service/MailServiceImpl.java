package com.guandera.core.server.service;

import java.util.Properties;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.guandera.core.client.service.EmailService;

public class MailServiceImpl implements EmailService, Serializable {

	private static final long serialVersionUID = 6997095638234553392L;

	public void enviarMensaje(String para, String asunto, String mensaje) {

		boolean enviado = false;

		Properties props = new Properties();
		Session mailSession = Session.getDefaultInstance(props, null);

		try {

			MimeMessage mimeMessage = new MimeMessage(mailSession);
			mimeMessage.setSubject(asunto);
			mimeMessage.setText(mensaje);
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(para));

			Transport.send(mimeMessage);

		} catch (AddressException e) {
			e.printStackTrace();

		} catch (MessagingException e) {
			e.getCause();
		}
		enviado = true;
	}

	public void sendMail(String sendEmailFrom, String sendMailTo, String recipientName, String messageSubject,
			String messageText) {
		Properties prop = new Properties();
		Session session = Session.getDefaultInstance(prop, null);
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(sendEmailFrom, "Sistema Integral Administraci�n Escolar"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(sendMailTo, "Mr./Ms. " + recipientName));
			msg.setSubject(messageSubject);
			msg.setText(messageText);
			Transport.send(msg);
			System.out.println("Successfull Delivery.");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}
}
