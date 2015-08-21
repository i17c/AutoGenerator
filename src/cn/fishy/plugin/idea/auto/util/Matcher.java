package cn.fishy.plugin.idea.auto.util;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 内容匹配类
 * User: duxing
 * Date: 2015.07.26 23:38
 */
public class Matcher {

    public static List<String> match(String regString, int index, String content){
        List<String> r = new ArrayList<String>();
        java.util.regex.Matcher mr = Pattern.compile(regString,Pattern.DOTALL+Pattern.CASE_INSENSITIVE).matcher(content);
        while (mr.find()) {
            r.add(mr.group(index));
        }
        return r;
    }

    public static List<String> match(String regString, String content){
        List<String> r = new ArrayList<String>();
        java.util.regex.Matcher mr = Pattern.compile(regString,Pattern.CASE_INSENSITIVE + Pattern.DOTALL).matcher(content);
        outer: while (mr.find()) {
            inner : for(int i=0;i<mr.groupCount();i++) {
                r.add(mr.group(i));
            }
        }
        return r;
    }


    public static void main(String[] args) throws Exception {
        String s = "ssssss<script>asedads</script>asdasdas";
        String filter = "<script.*?</script>";
        java.util.regex.Matcher mr = Pattern.compile(filter).matcher(s);
        s = mr.replaceAll("");
        System.out.println(s);
    }

    public static String filter(List<String> filterRegList, String content) {
        if(filterRegList!=null && filterRegList.size()>0) {
            for (String filter : filterRegList) {
                java.util.regex.Matcher mr = Pattern.compile(filter).matcher(content);
                content = mr.replaceAll("");
            }
        }
        return content;
    }

    public static String getMain(String mainReg, String content) {
        if(StringUtils.isNotBlank(mainReg)) {
            java.util.regex.Matcher mr = Pattern.compile(mainReg).matcher(content);
            content = mr.group();
        }
        return content;
    }
}
