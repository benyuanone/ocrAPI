package com.ourway.base.utils;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

/**
 * 附件发送邮件
 * Created by jackson on 2017/11/28.
 */
public class SendMailWithLink {
    private JavaMailSender mailSender;
    private SimpleMailMessage simpleMailMessage;

    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String title, String mailTo, String dear, String content, String[] files) throws Exception {

        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            simpleMailMessage.setTo(mailTo);
            helper.setFrom(simpleMailMessage.getFrom());
            helper.setTo(simpleMailMessage.getTo());
            helper.setSubject(title);
            helper.setText(String.format(
                    simpleMailMessage.getText(), dear, content));

            for (int i = 0; i < files.length; i++) {
                FileSystemResource file = new FileSystemResource(files[i]);
                helper.addAttachment(file.getFilename(), file);
            }
        } catch (Exception e) {
            throw new Exception();
        }
        mailSender.send(message);
    }
}
