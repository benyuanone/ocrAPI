package com.ourway;


import com.ourway.base.utils.SendMailUtil;

import java.io.IOException;

/**
 * Created by jackson on 2017/11/28.
 */
public class CutPageTest {

    public static void main(String[] args) throws IOException {
        /*剪切网页图片*/
//        PhantomTools.getCutPageImg("https://zfcg.baotou.gov.cn/");
        /*java发送邮件, 附件地址*/
        String[] files = new String[]{"C:\\Users\\jackson\\Desktop\\名宿备注.pdf", "C:\\Users\\jackson\\Desktop\\民宿接口文档.pdf"};
        SendMailUtil.sendMailWithFile("附件邮件测试","13588294068@163.com","jack", "这个是一个通过Spring框架发送的邮件小程序", files);
    }
}
