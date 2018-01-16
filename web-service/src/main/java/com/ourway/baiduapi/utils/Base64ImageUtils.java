package com.ourway.baiduapi.utils;


import com.ourway.baiduapi.constants.BaiDuApiInfo;
import com.ourway.baiduapi.dto.InfoDTO;
import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zhangwenchao on 2017/9/29.
 * 本地或者网络图片资源转为Base64字符串
 */
public class Base64ImageUtils {
    /**
     * @param imgURL 网络资源位置
     * @return Base64字符串
     * @Title: GetImageStrFromUrl
     * @Description: 将一张网络图片转化成Base64字符串
     */
    public static String getImageStrFromUrl(String imgURL) {
        byte[] data = null;
        try {
            // 创建URL
            URL url = new URL(imgURL);
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();
            data = new byte[inStream.available()];
            inStream.read(data);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }

    public static void main(String[] args) throws Exception {

        InfoDTO image = getImageStrFromPath("D:/xinfeng/xxxx/1/1.png");
        System.out.println(image.getValue());
//        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate?access_token="+BaiDuApiInfo.TOKEN;
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Content-Type", "application/x-www-form-urlencoded");
//        Map<String, String> bodys = new HashMap<>();
//        bodys.put("image", image);
//        bodys.put("recognize_granularity", "false");
//        bodys.put("detect_direction", "false");
//        bodys.put("vertexes_location", "false");
//        bodys.put("probability", "false");
//        CloseableHttpResponse response =  HttpClientUtils.doHttpsPost(url,headers,bodys);
//        String result=HttpClientUtils.toString(response);
//        System.out.println(result);
    }

    /**
     * 将图片转换成base64格式进行存储
     * 注：此方法生成的base64比直接用file的长很多，暂时不知道什么原因
     *
     * @param imagePath
     * @return
     */
    public static String encodeToString(String imagePath) throws IOException {
        String type = StringUtils.substring(imagePath, imagePath.lastIndexOf(".") + 1);
        BufferedImage image = ImageIO.read(new File(imagePath));
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();
            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }


    /**
     * <p>方法:getImageStrFromPath TODO返回base64图片编码</p>
     * <ul>
     * <li> @param imgPath TODO</li>
     * <li>@return com.ourway.baiduapi.dto.InfoDTO  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/1/16 11:15  </li>
     * </ul>
     */
    public static InfoDTO getImageStrFromPath(String imgPath) {
        // 读取图片字节数组
        byte[] data=null;
        try {
            InfoDTO dealPic = confirmPic(imgPath);
            if (dealPic.getCode() == BaiDuApiInfo.FAILED_CODE) {
                return dealPic;
            }
            InputStream in  = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return InfoDTO.error("图片处理失败IO异常" + e);
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return InfoDTO.success(encoder.encode(data));
    }

    /**
     * 判读图片大小
     *
     * @param imgWidth
     * @param imgHeight
     * @return
     */
    private static InfoDTO dealSize(int imgWidth, int imgHeight) {
        if (imgWidth < BaiDuApiInfo.SMALLEST_SIZE) {
            return InfoDTO.error(BaiDuApiInfo.TOOLITTLE_WIDTH);
        }
        if (imgWidth > BaiDuApiInfo.BIGEST_SIZE) {
            return InfoDTO.error(BaiDuApiInfo.TOOMUCH_WIDTH);
        }
        if (imgHeight < BaiDuApiInfo.SMALLEST_SIZE) {
            return InfoDTO.error(BaiDuApiInfo.TOOLITTLE_HEIGHT);
        }
        if (imgHeight > BaiDuApiInfo.BIGEST_SIZE) {
            return InfoDTO.error(BaiDuApiInfo.TOOMUCH_HEIGHT);
        }
        return InfoDTO.success(BaiDuApiInfo.SUCCESS_RESULT);
    }

    public static String getImageStrFromPath(String imgPath, String type) {
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgPath);

            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println(data.length);
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        String valif = encoder.encode(data);
        System.out.println(valif);
        return valif;
    }

    /**
     * @param imgStr
     * @param imgFilePath 图片文件名，如“E:/tmp.jpg”
     * @return
     * @Title: GenerateImage
     * @Description: base64字符串转化成图片
     */
    public static boolean saveImage(String imgStr, String imgFilePath) {
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static InfoDTO confirmPic(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return InfoDTO.error(BaiDuApiInfo.NO_FILES);
        } else {
            String fileName = file.getName();// 文件原名称
            String type = fileName.indexOf(BaiDuApiInfo.SPILT_POINT) != -1 ? fileName.substring(fileName.lastIndexOf(BaiDuApiInfo.SPILT_POINT) + 1, fileName.length()) : null;
            if (type != null) {
                if (BaiDuApiInfo.PIC_GIF.equals(type.toUpperCase()) || BaiDuApiInfo.PIC_PNG.equals(type.toUpperCase()) || BaiDuApiInfo.PIC_JPG.equals(type.toUpperCase())) {
                    BufferedImage bufferedImg = ImageIO.read(file);
                    int imgWidth = bufferedImg.getWidth();
                    int imgHeight = bufferedImg.getHeight();
                    InfoDTO picDeal = dealSize(imgWidth, imgHeight);
                    if (picDeal.getCode() == BaiDuApiInfo.FAILED_CODE) {
                        return picDeal;
                    } else {
                        return InfoDTO.success(BaiDuApiInfo.SUCCESS_RESULT);
                    }
                } else {
                    return InfoDTO.error(BaiDuApiInfo.NOT_PICTURE);
                }
            }
            return InfoDTO.error(BaiDuApiInfo.NOT_PICTURE);
        }
    }
}
