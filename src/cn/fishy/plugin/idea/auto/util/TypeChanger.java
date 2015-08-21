package cn.fishy.plugin.idea.auto.util;

import java.util.HashMap;

/**
 * User: duxing
 * Date: 2015.08.13 23:26
 */
public class TypeChanger {

    public static HashMap<String, String> typeMap = new HashMap<String, String>();
    static {
        /**
         * mysql
         */
        typeMap.put("TINYINT", "Integer");
        typeMap.put("SMALLINT", "Integer");
        typeMap.put("MEDIUMINT", "Integer");
        typeMap.put("INT", "Long");
        typeMap.put("BIGINT", "Long");
        typeMap.put("DECIMAL", "BigDecimal");
        typeMap.put("FLOAT", "Float");
        typeMap.put("DOUBLE", "Double");
        typeMap.put("REAL", "Float");
        typeMap.put("BIT", "Boolean");
        typeMap.put("BOOLEAN", "Boolean");
        typeMap.put("SERIAL", "Long");
        typeMap.put("DATE", "Date");
        typeMap.put("DATETIME", "Date");
        typeMap.put("TIMESTAMP", "Long");
        typeMap.put("TIME", "String");
        typeMap.put("YEAR", "Integer");
        typeMap.put("CHAR", "String");
        typeMap.put("VARCHAR", "String");
        typeMap.put("TINYTEXT", "String");
        typeMap.put("TEXT", "String");
        typeMap.put("MEDIUMTEXT", "String");
        typeMap.put("LONGTEXT", "String");
        typeMap.put("BINARY", "String");
        typeMap.put("VARBINARY", "String");
        //有待考验,blob里面一般存储字节流
        typeMap.put("TINYBLOB", "Object");
        typeMap.put("MEDIUMBLOB", "Object");
        typeMap.put("BLOB", "Object");
        typeMap.put("LONGBLOB", "Object");
        typeMap.put("ENUM", "String");
        typeMap.put("SET", "String");
        /**
         * oracle
         */
        typeMap.put("NUMBER", "Long");
        typeMap.put("RAW", "String");
    }

    public static String clean(String typeStr){
        return typeStr.replaceAll("\\(.*\\)","").toUpperCase();
    }

    public static String getType(String typeStr){
        String type = typeMap.get(typeStr);
        if(type==null)type="String";
        return type;
    }
}
