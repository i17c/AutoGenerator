package cn.fishy.plugin.idea.auto.constant;

import cn.fishy.plugin.idea.auto.domain.Code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: duxing
 * Date: 2015-08-15 16:41
 */
public class ImportMapHolder {
    public static Map<String,String> importMap = new HashMap<String, String>();
    public static List<String> javaIgnoreTypeList = new ArrayList<String>();
    static{
        importMap.put("Date", "java.util.Date");
        importMap.put("Long", "java.lang.Long");
        importMap.put("Integer", "java.lang.Integer");
        importMap.put("BigDecimal", "java.math.BigDecimal");
        javaIgnoreTypeList.add("Long");
        javaIgnoreTypeList.add("Integer");
    }

    public static String getImport(String type,Code code){
        String r = importMap.get(type);
        if(code.equals(Code.JAVA) && javaIgnoreTypeList.contains(type)){
            return null;
        }
        return r;
    }

}
