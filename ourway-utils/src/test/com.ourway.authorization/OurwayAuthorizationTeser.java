package com.ourway.authorization;

import java.util.ArrayList;

/**
 * Created by CuiL on 2017-06-28.
 */
public class OurwayAuthorizationTeser {

    public static void main(String[] args) {
        OurwayAuthorization ourwayAuthorization = new OurwayAuthorization();
//        try {
//            ourwayAuthorization.ReadCsrFile("D://ourway.crt");
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
/*
        try {
            ourwayAuthorization.ReadKeyStore("D://ourway.keystore","OurwayPass123!@#","ourway");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
*/
//        ourwayAuthorization.ReadKeyInfo();
//
//        try {
//            ourwayAuthorization.initCsr("D://ourway.crt");
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String str ="";
//        try {
////            String str = OurwayAuthorization.sign("测试的原文","D://ourway.keystore","OurwayPass123!@#");
//            str = OurwayAuthorization.sign("测试的原文","D://ourway.keystore",
//                    "OurwayPass123!@#","www.ourwaysoft.com","ourwayPass123!@#");
//            System.out.printf(str);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            System.out.println(OurwayAuthorization.getPublickKey("D://ourway.crt"));
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            OurwayAuthorization.verify("测试的原文",str,"D://ourway.crt");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        OurwayAuthorizationTeser ourwayAuthorizationTeser = new OurwayAuthorizationTeser();
        ourwayAuthorizationTeser.authorAuthorInfo();
        //读取文件内JSON授权信息

        //私钥加密JSON并附加到JSON的一个属性内

        //发与客户，客户服务器读取JSON，重组JSON串，根据签名值验证重组的JSON是否合法

    }

    public AuthorInfo createAuthorInfo(){
        AuthorInfo authorInfo = new AuthorInfo();
        ArrayList<CompanyInfo> companyInfos = new ArrayList<>();
        ArrayList<LanguageInfo> languageInfos = new ArrayList<>();
        CompanyInfo companyInfo = new CompanyInfo();
        LanguageInfo languageInfo = new LanguageInfo();
        companyInfos.clear();
        for(int i = 0; i <= 9; i++){
            companyInfo.setCompCode("公司编号0"+i);
            companyInfo.setCompName("公司名称0"+i);
            companyInfo.setCompEndError("公司0"+i+"到期！");
            companyInfo.setCompEndWarmingDate("2017-01-0"+i+" 11:12:13");
            companyInfo.setCompMaxUser(""+i);
            companyInfo.setCompTimeBegin("2016-12-0"+i+" 23:59:59");
            companyInfo.setCompTimeEnd("2017-02-0"+i+" 11:12:13");
            companyInfo.setCompNo("10"+i);
            companyInfo.setCompEndWarming("公司0"+i+"系统即将到期！");
            languageInfos.clear();
            for(int j =0 ; j<=2 ; j++){
                languageInfo.setLanCode("公司编号0"+i);
                languageInfo.setLanName("公司名称0"+i);
                languageInfo.setLanEndError("公司0"+i+"到期！");
                languageInfo.setLanEndWarmingDate("2017-01-0"+i+" 11:12:13");
                languageInfo.setLanMaxUser(""+i);
                languageInfo.setLanTimeBegin("2016-12-0"+i+" 23:59:59");
                languageInfo.setLanTimeEnd("2017-02-0"+i+" 11:12:13");
                languageInfo.setLanEndWarming("公司0"+i+"系统即将到期！");
                languageInfos.add(languageInfo);
            }
            companyInfo.setLanguageInfoList(languageInfos);
            companyInfos.add(companyInfo);
        }
        authorInfo.setSysCode("集团编号0");
        authorInfo.setSysName("集团名称0");
        authorInfo.setSysEndError("集团到期！");
        authorInfo.setSysEndWarmingDate("2017-01-0 11:12:13");
        authorInfo.setSysMaxUser("100");
        authorInfo.setSysTimeBegin("2016-12-0 23:59:59");
        authorInfo.setSysTimeEnd("2017-02-0 11:12:13");
        authorInfo.setSysNo("10");
        authorInfo.setSysEndWarming("集团系统即将到期！");
        authorInfo.setCompanyInfoList(companyInfos);
        authorInfo.setLanguageInfos(languageInfos);

        return authorInfo;

    }

    public AuthorInfo authorAuthorInfo(){
        AuthorInfo authorInfo = createAuthorInfo();
        System.out.println(authorInfo.toString());
        return authorInfo;
    }
}
