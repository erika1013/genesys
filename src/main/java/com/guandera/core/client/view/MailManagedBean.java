package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.guandera.core.client.service.EmailService;
import com.guandera.core.server.service.MailServiceImpl;

@ManagedBean(name = "mailMB")
@SessionScoped
public class MailManagedBean extends BaseManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;

	private EmailService emailServicio;

	private String para;
	private String asunto;
	private String mensaje;
	private String estadoMensaje = "";

	public MailManagedBean() {
		emailServicio = new MailServiceImpl();

	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getEstadoMensaje() {
		return estadoMensaje;
	}

	public String enviarCorreo() {

		emailServicio.sendMail("delram92@guandera.co", para, "recipientName", asunto, mensaje);

		return "EnviarCorreo";

	}

	public String enviarCorreo2() {

		// emailServicio.sendMail("fredijavier@guadera.co",
		// "fredijavier@gmail.com", "recipientName", "messageSubject",
		// "messageText");

		// emailServicio.enviarMensaje(para, asunto, mensaje);

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		// Properties prop = System.getProperties();
		// Session session = Session.getInstance(prop, null);

		try {
			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress("delram92@gmail.com.com"));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress("delram92@gmail.com"));
			msg.setSubject("Prueba enviar correo");
			msg.setText("World");

			Transport.send(msg);
		} catch (Exception e) {

			System.out.println("Error enviando correo");
			error("mensaje no enviado");
		}

		return "EnviarCorreo";

	}

}
