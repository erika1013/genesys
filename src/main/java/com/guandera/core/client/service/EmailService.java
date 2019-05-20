package com.guandera.core.client.service;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface EmailService {

	public void enviarMensaje(String para, String asunto, String mensaje);

	public void sendMail(String sendEmailFrom, String sendMailTo, String recipientName, String messageSubject,
			String messageText);

}
