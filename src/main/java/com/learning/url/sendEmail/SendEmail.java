package com.learning.url.sendEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    @Autowired
    private JavaMailSender mailSender;

    private String usermail;
    private String url;

    public SendEmail(JavaMailSender mailSender, String usermail, String url) {
        this.mailSender = mailSender;
        this.usermail = usermail;
        this.url = url;
    }

    public void sendEmailToValidEmailUser(String useremail, String url) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String htmlMsg = "<p>Hello,</p>"
                +"<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + url + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";
        helper.setText(htmlMsg, true); // Use this or above line.
        helper.setTo(useremail);
        helper.setSubject("Here's the link to reset your password");
        helper.setFrom("nyembe.mirhyne@gmail.com");
        mailSender.send(mimeMessage);
    }


    public void sendEmailToConfirmModifUser(String useremail,String url,String name) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String htmlMsg = "<p>Hello, </p>"+ name
                +"<p>You have successfully reset your password.</p>"
                + "<p>Click the link below to connect to your account</p>"
                + "<p><a href=\"" + url + "\">Connect to your account</a></p>"
                + "<br>"
                + "<p>Ignore this email and delete it if you have not made the request.</p>";
        helper.setText(htmlMsg, true); // Use this or above line.
        helper.setTo(useremail);
        helper.setSubject("Password has been reset Successfully");
        helper.setFrom("nyembe.mirhyne@gmail.com");
        mailSender.send(mimeMessage);
    }


}
