package com.ourway.sys.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by CC on 2017/7/7.
 */
public class WriteUtils {
    /**
     * 写入内容到文件
     *
     * @param
     * @param filename
     * @return
     */
    public static boolean writeContent(String c,
                                       String filename, boolean isAppend) {
        File f = new File("c:/files/");
        if (!f.exists()) {
            f.mkdirs();
        }
        try {
            FileOutputStream fos = new FileOutputStream("c:/files/"
                    + File.separator + filename, isAppend);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            writer.write(c);
            writer.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
