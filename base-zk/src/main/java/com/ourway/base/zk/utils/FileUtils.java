package com.ourway.base.zk.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/12.
 */
public class FileUtils {


    public static void createFolder(String path) {
        File myFolderPath = new File(path);
        try {
            if (!myFolderPath.exists()) {
                myFolderPath.mkdir();
            }
        } catch (Exception e) {
            System.out.println("新建目录操作出错");
            e.printStackTrace();
        }
    }

    public static boolean isFileExist(String filePath) {
        File myFilePath = new File(filePath);
        try {
            if (!myFilePath.exists()) {
                return false;
            }

        } catch (Exception e) {
            System.out.println("新建文件操作出错");
            e.printStackTrace();
        }
        return true;
    }

    public static void createNewFile(String filePath, String content) {
        File myFilePath = new File(filePath);
        try {
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
            FileOutputStream writerStream = new FileOutputStream(myFilePath);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8"));
            writer.write(content);
            writer.close();
            writerStream.flush();
            writerStream.close();
        } catch (Exception e) {
            System.out.println("新建文件操作出错");
            e.printStackTrace();
        }
    }

    public static void delFiles(String filePath) {
        File myDelFile = new File(filePath);
        try {
            myDelFile.delete();
        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();
        }
    }

    public static List<File> listAllFileForAllFolder(String folder) {
        List<File> fileList = new ArrayList<File>();
        LinkedList<String> folderList = new LinkedList<String>();
        folderList.add(folder);
        while (folderList.size() > 0) {
            File file = new File(folderList.peek());
            folderList.removeLast();
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    folderList.add(files[i].getPath());
                } else {
                    fileList.add(files[i]);
                }
            }
        }
        return fileList;
    }

    public static List<File> listAllFileForOneFolder(String folder) {
        List<File> fileList = new ArrayList<File>();
        LinkedList<String> folderList = new LinkedList<String>();
        folderList.add(folder);
        while (folderList.size() > 0) {
            File file = new File(folderList.peek());
            folderList.removeLast();
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    continue;
                } else {
                    fileList.add(files[i]);
                }
            }
        }
        return fileList;
    }

    //删除包含指定名称的文件
    public static void delFilesBySubName(String path, String subName) {
        List<File> fileList = listAllFileForOneFolder(path);
        for (File file : fileList) {
            if (file.getName().contains(subName))
                delFiles(file.getAbsolutePath());
        }
    }
}
