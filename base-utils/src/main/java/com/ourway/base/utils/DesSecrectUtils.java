package com.ourway.base.utils;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;

public class DesSecrectUtils {
	private static String strDefaultKey = "zghzbckj"; // 默认加密字符串
	private Cipher encryptCipher = null;
	private Cipher decryptCipher = null;

	/**
	 * 默认构造方法，使用默认密钥
	 * 
	 * @throws Exception
	 */
	public DesSecrectUtils() throws Exception {
		this(strDefaultKey);
	}

	/**
	 * * 指定密钥构造方法 * *
	 * 
	 * @param strKey *
	 *            指定的密钥
	 * @param strKey
	 * @throws Exception
	 */
	public DesSecrectUtils(String strKey) throws Exception {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Key key = getKey(strKey.getBytes());
		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);
		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
	}

	/**
	 * <p>
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[] *
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程 * *
	 * 
	 * @param arrB *
	 *            需要转换的byte数组 *
	 * @return 转换后的字符串 *
	 * @throws Exception *
	 *             本方法不处理任何异常，所有异常全部抛出
	 *             </p>
	 * @author Jack Zhou
	 * @version $Id: Des.java,v 0.1 Feb 9, 2011 1:30:55 PM Jack Exp $
	 */
	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB) *
	 * 互为可逆的转换过程
	 * 
	 * @param strIn *
	 *            需要转换的字符串 *
	 * @return 转换后的byte数组 *
	 * @throws Exception *
	 *             本方法不处理任何异常，所有异常全部抛出
	 *             </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: Des.java,v 0.1 Feb 9, 2011 2:03:40 PM Jack Exp $
	 */
	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	/**
	 * <p>
	 * 加密字节数组 * *
	 * 
	 * @param arrB *
	 *            需加密的字节数组 *
	 * @return 加密后的字节数组
	 *         </p>
	 * @author Jack Zhou
	 * @version $Id: Des.java,v 0.1 Feb 9, 2011 2:32:50 PM Jack Exp $
	 */
	public byte[] encrypt(byte[] arrB) throws Exception {
		return encryptCipher.doFinal(arrB);
	}

	/**
	 * <p>
	 * 加密字符串 * *
	 * 
	 * @param strIn *
	 *            需加密的字符串 *
	 * @return 加密后的字符串
	 *         </p>
	 * @author Jack Zhou
	 * @version $Id: Des.java,v 0.1 Feb 9, 2011 2:33:04 PM Jack Exp $
	 */
	public String encrypt(String strIn) throws Exception {
		return byteArr2HexStr(encrypt(strIn.getBytes()));
	}

	/**
	 * <p>
	 * 解密字节数组 * *
	 * 
	 * @param arrB *
	 *            需解密的字节数组 *
	 * @return 解密后的字节数组
	 *         </p>
	 * @author Jack Zhou
	 * @version $Id: Des.java,v 0.1 Feb 9, 2011 2:33:20 PM Jack Exp $
	 */
	public byte[] decrypt(byte[] arrB) throws Exception {
		return decryptCipher.doFinal(arrB);
	}

	/**
	 * <p>
	 * 解密字符串 * *
	 * 
	 * @param strIn *
	 *            需解密的字符串 *
	 * @return 解密后的字符串
	 *         </p>
	 * @author Jack Zhou
	 * @version $Id: Des.java,v 0.1 Feb 9, 2011 2:33:32 PM Jack Exp $
	 */
	public String decrypt(String strIn) throws Exception {
		return new String(decrypt(hexStr2ByteArr(strIn)));
	}

	/**
	 * <p>
	 * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位 * *
	 * 
	 * @param arrBTmp *
	 *            构成该字符串的字节数组 *
	 * @return 生成的密钥 *
	 * @throws Exception
	 *             </p>
	 * @author Jack Zhou
	 * @version $Id: Des.java,v 0.1 Feb 9, 2011 2:33:51 PM Jack Exp $
	 */
	private Key getKey(byte[] arrBTmp) throws Exception {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];
		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
		return key;
	}

	public static void main(String[] args) {
		try {
			String test = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><params><imei>1234567890</imei ></params>";
			DesSecrectUtils des = new DesSecrectUtils("8a8181a72d9c776c012d9c77c5960003");// 自定义密钥
			System.out.println("加密前的字符：" + test);
			System.out.println("加密后的字符：" + des.encrypt(test));
			System.out.println("解密后的字符：" + des.decrypt(des.encrypt(test)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
