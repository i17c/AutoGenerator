package cn.fishy.plugin.idea.auto.util;

import cn.fishy.plugin.idea.auto.constant.GenerateType;
import org.apache.commons.lang.StringUtils;

/**
 * 名称工具类
 * User: duxing
 * Date: 2015.08.13 23:17
 */
public class NameUtil {
    public static String toWords(String name) {
        return name.replaceAll("_", " ");
    }

    public static String formatName(String name) {
        return upFirstAll(toWords(name.toLowerCase())).replaceAll(" ", "");
    }

    public static String upFirstAll(String s) {
        s = s.trim().replaceAll("\\s", " ");
        String[] sa = s.split(" ");
        String r = "";
        for (String o : sa) {
            if (StringUtils.isNotBlank(o)) {
                r += upFirst(o);
            }
        }
        return r;
    }

    public static String upFirst(String o) {
        return o.replaceFirst(o.substring(0, 1), o.substring(0, 1).toUpperCase());
    }

    public static String lowFirst(String o) {
        return o.replaceFirst(o.substring(0, 1), o.substring(0, 1).toLowerCase());
    }

    public static String propertyName(String s) {
        return lowFirst(formatName(s));
    }

    public static String name(String s, GenerateType generateType){
        if(generateType.equals(GenerateType.SQLMap)){
            return s.toLowerCase()+generateType.getSuffix();
        }else if(generateType.equals(GenerateType.BaseDAO) || generateType.equals(GenerateType.BaseQuery)){
            return generateType.getSuffix();
        }else{
            return formatName(s) + generateType.getSuffix();
        }
    }

}
