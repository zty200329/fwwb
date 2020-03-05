package com.waibao.demo.util;


import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
/**
 * @Author: zty
 * @Date: 2020/2/14 14:50
 */
@Component
public class FileUtil {

    /**
     * 获取指定目录下的所有文件名
     *
     * @param obj
     * @return
     */
    public static ArrayList<File> getListFiles(Object obj) {
        File directory = null;
        if (obj instanceof File) {
            directory = (File) obj;
        } else {
            directory = new File(obj.toString());
        }
        ArrayList<File> files = new ArrayList<File>();
        if (directory.isFile()) {
            files.add(directory);
            return files;
        } else if (directory.isDirectory()) {
            File[] fileArr = directory.listFiles();
            for (int i = 0; i < fileArr.length; i++) {
                File fileOne = fileArr[i];
                files.addAll(getListFiles(fileOne));
            }
        }
        return files;
    }
 }