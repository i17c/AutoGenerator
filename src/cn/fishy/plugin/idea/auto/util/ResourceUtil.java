package cn.fishy.plugin.idea.auto.util;


import com.intellij.openapi.diagnostic.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;

/**
 * User: duxing
 * Date: 2016-02-26 21:03
 */
public class ResourceUtil {
    private static final Logger logger = Logger.getInstance(ResourceUtil.class);

    /**
     * 读取utf-8的文本文件
     * @param textFilePath 文本类型文件相对于resources的路径
     * @return
     */
    public static String load(String textFilePath){
        return load(textFilePath, "utf-8");
    }

    /**
     * 读取文本文件
     * @param textFilePath 文本类型文件相对于resources的路径
     * @param charset 字符集 如gbk,utf-8
     * @return
     */
    public static String load(String textFilePath, String charset){
        if(textFilePath==null || textFilePath.equals(""))return null;
        if(textFilePath.startsWith("/"))textFilePath=textFilePath.substring(1,textFilePath.length());
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(textFilePath);
        return getStringFromInputStream(charset, stream);
    }

    /**
     * 读取文本文件
     * @param textFilePath 文本类型文件相对于resources的路径
     * @param charset 字符集 如gbk,utf-8
     * @param clz 资源相对类, 如使用此方法的类本身
     * @return
     */
    public static String load(String textFilePath, String charset, Class clz){
        if(textFilePath==null || textFilePath.equals(""))return null;
        if(!textFilePath.startsWith("/"))textFilePath="/"+textFilePath;
        InputStream stream = clz.getResourceAsStream(textFilePath);
        return getStringFromInputStream(charset, stream);
    }

    private static String getStringFromInputStream(String charset, InputStream stream) {
        StringBuilder str = new StringBuilder();
        try {
            LineNumberReader lineNr = new LineNumberReader(new InputStreamReader(stream,charset));
            long i=0;
            for (String line = lineNr.readLine(); line != null; line = lineNr.readLine()) {
                if(i>0)str.append("\n");
                str.append(line);
                i++;
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("Encoding error",e);
        } catch (IOException e) {
            logger.error("IO error",e);
        }
        return str.toString();
    }

}
