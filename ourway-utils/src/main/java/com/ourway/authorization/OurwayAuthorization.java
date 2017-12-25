package com.ourway.authorization;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.*;
import java.security.spec.KeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import static java.lang.System.in;
import static java.lang.System.setOut;

/**
 * Created by CuiL on 2017-06-27.
 */
public class OurwayAuthorization {

    KeyStore keyStore;
    public Certificate ourwayCrt;
    public X509Certificate ourwayCsr;


    /**
    *<p>方法:initCsr 以cre文件初始化相关证书 </p>
     * <ul>
     * <li> @param cerFilePath TODO</li>
     * <li>@return boolean  </li>
     * <li>@author CuiLiang </li>
     * <li>@date 2017-06-29 15:17  </li>
     * </ul>
    */
    boolean initCsr(String cerFilePath) throws CertificateException, IOException {
        // 获取X509Certificate的对象
        // 从命令行中读入需要验证的证书文件
        CertificateFactory of = CertificateFactory.getInstance("x.509");
        FileInputStream in = new FileInputStream(cerFilePath);
        java.security.cert.Certificate ceof = of.generateCertificate(in);
        System.out.println(ceof.getPublicKey().getEncoded() + "\t"
                + ceof.getEncoded());
        in.close();
        ourwayCsr = (X509Certificate) ceof;
        System.out.println("版本号:" + ourwayCsr.getVersion());
        System.out.println("序列号:" + ourwayCsr.getSerialNumber().toString(16));
        System.out.println("主体名：" + ourwayCsr.getSubjectDN());
        System.out.println("签发者：" + ourwayCsr.getIssuerDN());
        System.out.println("有效期：" + ourwayCsr.getNotBefore());
        System.out.println("终止期：" + ourwayCsr.getNotAfter());
        System.out.println("签名算法：" + ourwayCsr.getSigAlgName());
        return true;
    }

    /**
    *<p>方法:getPrivateKey 读取私钥  返回PrivateKey </p>
    *<ul>
    *<li> @param path 包含私钥的证书路径</li>
    *<li> @param password 私钥证书密码</li>
    *<li>@return java.security.PrivateKey  返回私钥PrivateKey</li>
    *<li>@author CuiLiang </li>
    *<li>@date 2017-07-03 10:53  </li>
    *</ul>
    */
    private static PrivateKey getPrivateKey(String path, String password)
            throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
            IOException, UnrecoverableKeyException {
        KeyStore ks = KeyStore.getInstance("JKS");
        FileInputStream fis = new FileInputStream(path);
        char[] nPassword = null;
        if ((password == null) || password.trim().equals("")) {
            nPassword = null;
        } else {
            nPassword = password.toCharArray();
        }
        ks.load(fis, nPassword);
        fis.close();
        Enumeration<String> en = ks.aliases();
        String keyAlias = null;
        if (en.hasMoreElements()) {
            keyAlias = (String) en.nextElement();
        }
        return (PrivateKey) ks.getKey(keyAlias, nPassword);
    }
    /**
    *<p>方法:getPrivateKey 获取私钥 </p>
    *<ul>
     *<li> @param path keystore路径</li>
     *<li> @param storePwd store密码</li>
     *<li> @param keyAlias 证书别名</li>
     *<li> @param keyPwd 证书密码</li>
    *<li>@return java.security.PrivateKey  </li>
    *<li>@author CuiLiang </li>
    *<li>@date 2017-07-03 10:50  </li>
    *</ul>
    */
    private static PrivateKey getPrivateKey(String path, String storePwd, String keyAlias, String keyPwd)
            throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
            IOException, UnrecoverableKeyException {
        KeyStore ks = KeyStore.getInstance("JKS");
        FileInputStream fis = new FileInputStream(path);
        char[] nPassword = null;
        if ((storePwd == null) || storePwd.trim().equals("")) {
            nPassword = null;
        } else {
            nPassword = storePwd.toCharArray();
        }
        ks.load(fis, nPassword);
        fis.close();
        Enumeration<String> en = ks.aliases();
        return (PrivateKey) ks.getKey(keyAlias, keyPwd.toCharArray());
    }

    /**
    *<p>方法:sign 私钥签名 </p>
    *<ul>
     *<li> @param plainText 待签名字符串</li>
     *<li> @param path 签名私钥路径</li>
     *<li> @param password 签名私钥密码</li>
    *<li>@return java.lang.String  返回签名后的字符串</li>
    *<li>@author CuiLiang </li>
    *<li>@date 2017-07-03 10:51  </li>
    *</ul>
    */
    public static String sign(String plainText,String path,String password)
            throws Exception {
        /*
         * MD5加密
         */
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(plainText.getBytes("utf-8"));
        byte[] digestBytes = md5.digest();
        /*
         * 用私钥进行签名 RSA
         * Cipher负责完成加密或解密工作，基于RSA
         */
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        //ENCRYPT_MODE表示为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, getPrivateKey(path, password));
        //加密
        byte[] rsaBytes = cipher.doFinal(digestBytes);
        //Base64编码
        return Base64.encode(rsaBytes);
//        return Base64.byteArrayToBase64(rsaBytes);
    }
    /**
     *<p>方法:sign 私钥签名 </p>
     *<ul>
     *<li> @param plainText 待签名字符串</li>
     *<li> @param path 签名私钥路径</li>
     *<li> @param password 签名私钥密码</li>
     *<li>@return java.lang.String  返回签名后的字符串</li>
     *<li>@author CuiLiang </li>
     *<li>@date 2017-07-03 10:51  </li>
     *</ul>
     */
    public static String sign(String plainText,String storePath,String storePwd, String keyAlias, String keyPwd)
            throws Exception {
        /*
         * MD5加密
         */
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(plainText.getBytes("utf-8"));
        byte[] digestBytes = md5.digest();
        /*
         * 用私钥进行签名 RSA
         * Cipher负责完成加密或解密工作，基于RSA
         */
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        //ENCRYPT_MODE表示为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, getPrivateKey(storePath, storePwd, keyAlias, keyPwd));
        //加密
        byte[] rsaBytes = cipher.doFinal(digestBytes);
        //Base64编码
        return Base64.encode(rsaBytes);
//        return Base64.byteArrayToBase64(rsaBytes);
    }
    /**
    *<p>方法:getPublickKey 根据公钥n、e生成公钥 </p>
    *<ul>
     *<li> @param modulus 公钥n串</li>
     *<li> @param publicExponent 公钥e串</li>
    *<li>@return java.security.PublicKey  返回公钥PublicKey</li>
    *<li>@author CuiLiang </li>
    *<li>@date 2017-07-03 10:52  </li>
    *</ul>
    */
    public static PublicKey getPublickKey(String modulus, String publicExponent)
            throws Exception {
        KeySpec publicKeySpec = new RSAPublicKeySpec(
                new BigInteger(modulus, 16), new BigInteger(publicExponent, 16));
        KeyFactory factory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = factory.generatePublic(publicKeySpec);
        return publicKey;
    }

    /**
     * 读取公钥cer
     * @param path .cer文件的路径  如：c:/abc.cer
     * @return  base64后的公钥串
     * @throws IOException
     * @throws CertificateException
     */
    public static PublicKey getPublickKey(String path) throws CertificateException, IOException {
        CertificateFactory of = CertificateFactory.getInstance("x.509");
        FileInputStream fis = new FileInputStream(path);
        java.security.cert.Certificate ceof = of.generateCertificate(fis);
        System.out.println(ceof.getPublicKey().getEncoded() + "\t"
                + ceof.getEncoded());
        fis.close();
        X509Certificate ourwayCsr = (X509Certificate) ceof;
        PublicKey publicKey = ourwayCsr.getPublicKey();
        return publicKey;
    }

    /**
     * 用公钥证书进行验签
     * @param message  签名之前的原文
     * @param cipherText  签名
     * @param pubKeyn 公钥n串
     * @param pubKeye 公钥e串
     * @return boolean 验签成功为true,失败为false
     * @throws Exception
     */
    public static boolean verify(String message, String cipherText,String pubKeyn,
                                 String pubKeye) throws Exception {
        Cipher c4 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        // 根据密钥，对Cipher对象进行初始化,DECRYPT_MODE表示解密模式
        c4.init(Cipher.DECRYPT_MODE, getPublickKey(pubKeyn,pubKeye));
        // 解密
        byte[] desDecTextBytes = c4.doFinal(Base64.decode(cipherText));
//        byte[] desDecTextBytes = c4.doFinal(Base64.base64ToByteArray(cipherText));
        // 得到前置对原文进行的MD5
//        String md5Digest1 = Base64.byteArrayToBase64(desDecTextBytes);
        String md5Digest1 = Base64.encode(desDecTextBytes);
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(message.getBytes("utf-8"));
        byte[] digestBytes = md5.digest();
        // 得到原文的MD5
//        String md5Digest2 = Base64.byteArrayToBase64(digestBytes);
        String md5Digest2 = Base64.encode(digestBytes);
        // 验证签名
        if (md5Digest1.equals(md5Digest2)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 用公钥证书进行验签
     * @param message  签名之前的原文
     * @param cipherText  签名
     * @param pubPath 公钥路径
     * @return boolean 验签成功为true,失败为false
     * @throws Exception
     */
    public static boolean verify(String message, String cipherText,String pubPath) throws Exception {
        Cipher c4 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        // 根据密钥，对Cipher对象进行初始化,DECRYPT_MODE表示解密模式
        c4.init(Cipher.DECRYPT_MODE, getPublickKey(pubPath));
        // 解密
        byte[] desDecTextBytes = c4.doFinal(Base64.decode(cipherText));
//        byte[] desDecTextBytes = c4.doFinal(Base64.base64ToByteArray(cipherText));
        // 得到前置对原文进行的MD5
//        String md5Digest1 = Base64.byteArrayToBase64(desDecTextBytes);
        String md5Digest1 = Base64.encode(desDecTextBytes);
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(message.getBytes("utf-8"));
        byte[] digestBytes = md5.digest();
        // 得到原文进行的MD5
//        String md5Digest2 = Base64.byteArrayToBase64(digestBytes);
        String md5Digest2 = Base64.encode(digestBytes);
        System.out.println(md5Digest1);
        System.out.println(md5Digest2);
        // 验证签名
        if (md5Digest1.equals(md5Digest2)) {
            return true;
        } else {
            return false;
        }
    }














    /**
    *<p>方法:校验时间是否是证书合法时间 TODO </p>
    *<ul>
     *<li> @param cld 待校验时间</li>
    *<li>@return int  0合法  -1待校验时间早于证书开始时间 1待校验时间晚于证书结束时间</li>
    *<li>@author CuiLiang </li>
    *<li>@date 2017-06-29 15:15  </li>
    *</ul>
    */
    int checkDateLegal(Calendar cld){
        Date d = cld.getTime();
        try {
            // 检验证书
            ourwayCsr.checkValidity(d);
            return 0;
        }
        // 若证书在指定日期已经过期，则产生CertificateExpiredException异常，在cath语句中作相关处理
        catch (CertificateExpiredException e) {
            return -1;
        }
        // 若证书在指定日期尚未生效，则产生CertificateNorYetValidException异常，在cath语句中作相关处理
        catch (CertificateNotYetValidException e) {
            return 1;
        }
    }

    /**
    *<p>方法:从csr文件读入证书 TODO </p>
    *<ul>
     *<li> @param filePath TODO</li>
    *<li>@return boolean  </li>
    *<li>@author CuiLiang </li>
    *<li>@date 2017-06-28 15:19  </li>
    *</ul>
    */
    boolean ReadCsrFile(String filePath) throws CertificateException, FileNotFoundException {
        CertificateFactory cf=CertificateFactory.getInstance("X.509");
        FileInputStream in=new FileInputStream(filePath);
        this.ourwayCrt=cf.generateCertificate(in);
        if (this.ourwayCrt != null){
            return true;
        }else{
            return false;
        }
    }

    /**
    *<p>方法:从keyStrone文件内读取证书 TODO </p>
    *<ul>
    *<li> @param filePath keystroe文件名</li>
    *<li> @param pwd keystroe密码</li>
    *<li> @param alias 证书别名</li>
    *<li>@return boolean  </li>
    *<li>@author CuiLiang </li>
    *<li>@date 2017-06-28 15:20  </li>
    *</ul>
    */
    boolean ReadKeyStore(String filePath,String stroePwd,String alias) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
        FileInputStream in=new FileInputStream(filePath);
        keyStore=KeyStore.getInstance("JKS");
        keyStore.load(in,stroePwd.toCharArray());
        ourwayCrt=keyStore.getCertificate(alias);//alias为条目的别名
        if (this.ourwayCrt != null){
            return true;
        }else{
            return false;
        }
    }

    /**
    *<p>方法:读取证书信息 TODO </p>
    *<ul>
     *<li> @param  TODO</li>
    *<li>@return java.lang.String  </li>
    *<li>@author CuiLiang </li>
    *<li>@date 2017-06-29 14:27  </li>
    *</ul>
    */
    String ReadKeyInfo(){
        System.out.println("输出证书信息:\n"+this.ourwayCrt.toString());
        PublicKey pk=this.ourwayCrt.getPublicKey();
        byte [] pkenc=pk.getEncoded();
        System.out.println("公钥");
        for(int i=0;i<pkenc.length;i++)System.out.print(pkenc[i]+",");
        return ourwayCrt.toString();
    }

    int GetCsrDate() throws CertificateException, FileNotFoundException {
//        CertificateFactory cf=CertificateFactory.getInstance("X.509");
//        FileInputStream in1=new FileInputStream("aa.crt");
//        FileInputStream in2=new FileInputStream("user.csr");
//        java.security.cert.Certificate c1=cf.generateCertificate(in1);
//        X509Certificate t=(X509Certificate)c1;
//        in2.close();
////（b）获取日期
//        Date TimeNow=new Date();
////（c）检验有效性
//        try{
//            t.checkValidity(TimeNow);
//            System.out.println("OK");
//        }catch(CertificateExpiredException e){ //过期
//            System.out.println("Expired");
//            System.out.println(e.getMessage());
//        }catch((CertificateNotYetValidException e){ //尚未生效
//            System.out.println("Too early");
//            System.out.println(e.getMessage());}
        return 0;
    }

}
