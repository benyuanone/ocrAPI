package com.ourway.base.utils;

import com.ourway.base.CommonConstants;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Map;
import java.util.Properties;

/**
 * Created by jackson on 2017/11/28.
 */
public class SendMailUtil {

    /**
     * 带附件发送邮件
     * @param title  邮件标题
     * @param mailTo  邮件发送对象
     * @param name  邮件中的称呼
     * @param content  邮件内容
     * @param files  附件地址
     * @return
     */
    public static Map<String, Object> sendMailWithFile(String title, String mailTo, String name, String content, String[] files){
        /*申明SimpleMailMessage对象*/
        Map<String, Object> response = new HashedMap();
        if (TextUtils.isEmpty(title)){
            response.put("backCode", -1);
            response.put("errorMsg", "参数为空");
            return response;
        }
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(CommonConstants.mailFrom);
        mailMessage.setSubject(title);
        String text = "Dear %s:\nMail Content : %s";
        mailMessage.setText(text);
        /*声明 JavaMailSenderImpl对象*/
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setHost(CommonConstants.mailHost);
        mailSender.setUsername(CommonConstants.mailUsername);
        mailSender.setPassword(CommonConstants.mailPassword);
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", CommonConstants.mailSmtpAuth);
        properties.put("mail.debug", CommonConstants.mailDebug);
        properties.put("mail.smtp.timeout", CommonConstants.mailTimeOut);
        properties.put("mail.smtp.starttls.enable", CommonConstants.mailSmtpStarttlsEnable);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.port", CommonConstants.mailPort);
        properties.put("mail.smtp.socketFactory.fallback", false);
        mailSender.setJavaMailProperties(properties);
        SendMailWithLink mm = new SendMailWithLink();
        mm.setMailSender(mailSender);
        mm.setSimpleMailMessage(mailMessage);
        try {
            mm.sendMail(title, mailTo, name, content, files);
            response.put("backCode", 0);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.put("backCode", -1);
            response.put("errorMsg", "邮件发送失败");
            return response;
        }
    }

}
