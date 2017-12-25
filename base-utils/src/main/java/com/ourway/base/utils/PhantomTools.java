package com.ourway.base.utils;

import com.ourway.base.CommonConstants;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by jackson on 2017/11/28.
 */
public class PhantomTools {
    private static final Logger _logger = Logger.getLogger(PhantomTools.class);

    private static final String _shellCommand = CommonConstants.SYSTEM_FILE_PATH + "/phantomjs.exe" + " " + CommonConstants.SYSTEM_FILE_PATH + "/rasterize.js ";

    private static String _file;
    private static String _size;
    private static String imgName;
    static {
        long hash = System.currentTimeMillis();
        _file = CommonConstants.IMGPATH + hash + ".png";
        imgName = CommonConstants.IMGPATH + hash + ".png";
    }
    /**
     * 构造截图类
     *
     * @parm hash 用于临时文件的目录唯一化
     */
//    public PhantomTools(int hash) {
//        _file = CommonConstants.IMGPATH + hash + ".png";
//    }

    /**
     * 构造截图类
     *
     * @param size 图片的大小，如800px*600px（此时高度会裁切），或800px（此时 高度最少=宽度*9/16，高度不裁切）
     * @parm hash 用于临时文件的目录唯一化
     */
//    public PhantomTools(int hash, String size) {
//        this(hash);
//        if (size != null)
//            _size = " " + size;
//    }

    /**
     * 将目标网页转为图片字节流
     *
     * @param url 目标网页地址
     * @return 图片地址
     */
    public static String getCutPageImg(String url) throws IOException {
        BufferedInputStream in = null;
        ByteArrayOutputStream out = null;
        File file = null;
        try {
            System.out.println(_shellCommand + url + " " + _file + (_size != null ? _size : ""));
            System.out.println("=========================");
            System.out.println("cutPageFilePath:=============" + _file);
            if (exeCmd(_shellCommand + url + " " + _file + (_size != null ? _size : ""))) {
                file = new File(_file);
                if (!file.exists()) {
                    file.mkdir();
                }
                out = new ByteArrayOutputStream();
                byte[] b = new byte[5120];
                in = new BufferedInputStream(new FileInputStream(file));
                int n;
                while ((n = in.read(b, 0, 5120)) != -1) {
                    out.write(b, 0, n);
                }
            }
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                _logger.error(e);
                return null;
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                _logger.error(e);
                return null;
            }
            if (null == file || !file.exists()) {
                return null;
            }
        }
        return imgName;
    }

    /**
     * 执行CMD命令
     */
    private static boolean exeCmd(String commandStr) {
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(commandStr);
            if (p.waitFor() != 0 && p.exitValue() == 1) {
                return false;
            }
        } catch (Exception e) {
            _logger.error(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    _logger.error(e);
                }
            }
        }
        return true;
    }
}
