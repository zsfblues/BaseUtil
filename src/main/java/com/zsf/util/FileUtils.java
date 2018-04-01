package com.zsf.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public final class FileUtils {

    private FileUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 将文本文件中的内容读入到buffer中
     * @param buffer
     * @param filePath
     * @throws IOException
     */
    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        // 用来保存每行读取的内容
        String line = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        // 如果 line 为空说明读完了
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        is.close();
    }

    public static String getFileSize(File file){
        String size = "";
        if(file.exists() && file.isFile()){
            long fileS = file.length();
            DecimalFormat df = new DecimalFormat("#.00");
            if (fileS < 1024) {
                size = df.format((double) fileS) + "BT";
            } else if (fileS < 1048576) {
                size = df.format((double) fileS / 1024) + "KB";
            } else if (fileS < 1073741824) {
                size = df.format((double) fileS / 1048576) + "MB";
            } else {
                size = df.format((double) fileS / 1073741824) +"GB";
            }
        }else if(file.exists() && file.isDirectory()){
            size = "";
        }else{
            size = "0BT";
        }
        return size;
    }
}
