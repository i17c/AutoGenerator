package cn.fishy.plugin.idea.auto.util;

import cn.fishy.plugin.idea.auto.domain.Column;
import cn.fishy.plugin.idea.auto.domain.SqlInfo;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * User: duxing
 * Date: 2015.08.12 2:13
 */
public class SqlAnaly {

    public static String cleanSql(String sql) {
        sql = sql.replaceAll("\\r", "");
        sql = sql.replaceAll("\\n\\n", "\n");
        sql = fixComment(sql);
        sql = fixColumnType(sql);
        sql = sql.replaceAll(",", "\n");
        System.out.println(sql);
        return sql;
    }

    private static String fixColumnType(String sql) {
        String regStr = "\\s+[a-z]*\\s*\\([^'\\)]*,[^'\\)]*\\)";
        java.util.regex.Matcher mr = Pattern.compile(regStr, Pattern.CASE_INSENSITIVE + Pattern.DOTALL).matcher(sql);
        while (mr.find()) {
            String r = mr.group().replaceAll(",", "-");
            sql = sql.replace(mr.group(), r);
        }
        return sql;
    }

    private static String fixComment(String sql) {
        //对于
        String regStr = "comment\\s+'([^']*,[^']*)'";
        java.util.regex.Matcher mr = Pattern.compile(regStr, Pattern.CASE_INSENSITIVE + Pattern.DOTALL).matcher(sql);
        while (mr.find()) {
            String r = mr.group().replaceAll(",", "-");
            sql = sql.replace(mr.group(), r);
        }
        regStr = "key\\s+[^(]*\\s+\\([^)]+,[^)]+\\)";
        java.util.regex.Matcher mr1 = Pattern.compile(regStr, Pattern.CASE_INSENSITIVE + Pattern.DOTALL).matcher(sql);
        while (mr1.find()) {
            String r = mr1.group().replaceAll(",", "-");
            sql = sql.replace(mr1.group(), r);
        }
        return sql;
    }



    public static List<String> analyColumnLine(String sql) {
        String regStr = "create\\s*table\\s*(?:if\\s*not\\s*exists)*\\s*[^\\(]*\\s*\\((.*)\\)\\s*[^\\s\\)]*\\s*(?:(ENGINE|comment)*)";
        List<String> matches = Matcher.match(regStr, 1, sql);
        if (matches != null && matches.size() == 1) {
            String lines = matches.get(0);
            lines = lines.replaceAll("\\s+\\n\\s*", "\n");
            String[] la = lines.split("\n");
            return Arrays.asList(la);
        }
        return null;
    }

    public static String analyTableName(String sql){
        String regStr = "create\\s+table\\s*(?:if\\s*not\\s*exists)*(([^\\(]+))\\s*\\(";
        List<String> matches = Matcher.match(regStr, sql);
        if(matches!=null&&matches.size()==2){
            return matches.get(1).replaceAll("`|'|\"|\\s","");
        }
        return "table";
    }
    public static String analyPrimaryKey(String sql){
        String regStr = "primary\\s+key\\s+\\(([^\\)]*)\\)";
        List<String> matches = Matcher.match(regStr, 1, sql);
        if(matches!=null&&matches.size()==1){
            return matches.get(0).replaceAll("`|'|\"|\\s","");
        }
        return null;
    }


    public static Column getColumn(String line) {
        String regStrP = "[`]*([^`\\s]+)[`]*\\s+(\\S+)\\s*[unsigned\\s]*[not\\s]*[null\\s]*[default\\s]*.*[auto_increament\\s]*\\s*(?:comment\\s*'([^']*)')*";
        String regStr = "comment\\s+'(([^']*))'";
        List<String> l = Matcher.match(regStrP, line.trim());
        List<String> l2 = Matcher.match(regStr, line);
        if(l!=null && l.size()==3){
            Column c = new Column(l.get(1),l.get(2));
            if(l2!=null && l2.size()==2){
                c.setComment(l2.get(1));
            }else{
                c.setComment(l.get(1));
            }
            return c;
        }else{
            return null;
        }
    }


    public static void main(String[] args) throws Exception {
        String s = "`airports` text";
        String regStrP = "[`]*([^`\\s]+)[`]*\\s+(\\S+)\\s*[unsigned\\s]*[not\\s]*[null\\s]*[default\\s]*.*[auto_increament\\s]*\\s*(?:comment\\s*'([^']*)')*";
        List<String> l = Matcher.match(regStrP, s);
        System.out.println(l);
        if(l!=null && l.size()==3){
            Column c = new Column(l.get(1),l.get(2));
        }else{
        }
    }

    public static boolean isKeyLine(String line) {
        String regStr = "\\s*((primary|unique)\\s+)*key\\s+";
        List<String> l = Matcher.match(regStr, line);
        if(l!=null && l.size()>0 && !"`".equals(line.trim().substring(0, 1))){
            return true;
        }else{
            return false;
        }
    }


    public static void main2(String[] args) throws Exception {
        String s = "CREATE TABLE `transaction` (  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号',   `app` varchar(20) NOT NULL COMMENT '应用',   `model` varchar(20) NOT NULL COMMENT '应用内的模块',   `key` varchar(50) NOT NULL COMMENT '事务自定义标记',   `resume_bean_name` varchar(50) NOT NULL COMMENT '事务补偿当前对象',   `resume_method` varchar(255) NOT NULL COMMENT '事务补偿方法',   `resume_parameter` longtext NOT NULL COMMENT '事务方法参数',   `exception` varchar(50) NOT NULL COMMENT '异常标记串',   `exception_info` varchar(255) NOT NULL COMMENT '异常信息',   `exception_stack` longtext NOT NULL COMMENT '异常堆栈',   `step` tinyint(4) NOT NULL COMMENT '事务进度',   `status` tinyint(4) NOT NULL COMMENT '事务状态',   `mark` varchar(32) NOT NULL COMMENT '当前事务标记,为各种信息md5结果',   `gmt_create` datetime NOT NULL COMMENT '创建时间',   `gmt_modified` datetime NOT NULL COMMENT '修改时间',   `is_deleted` tinyint(4) NOT NULL COMMENT '记录是否逻辑删除',   PRIMARY KEY (`id`),   UNIQUE KEY `mark_2` (`mark`),   KEY `mark` (`mark`),   KEY `app` (`app`,`model`,`key`,`mark`),   KEY `key` (`key`) ) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='事务记录表' AUTO_INCREMENT=2 ;";
        SqlInfo si = analy(s);
        List<Column> list = si.getColumnList();
        List<Column[]> l = new ArrayList<Column[]>();
        for(Column c:list){
            l.add(new Column[]{c});
            System.out.println(c);
        }
        Column[][] s1 = l.toArray(new Column[l.size()][]);
        System.out.println(s1);
    }
	
    public static void main12(String[] args) throws Exception {
        String s = "create table dis_push_item_detail (id bigint unsigned not null comment '主键',     gmt_create datetime not null comment '创建时间',     gmt_modified datetime not null comment '修改时间',     mark_id bigint unsigned not null comment '商品Id(长度18位内，以99开头的纯数字)',     topic_title varchar(255) not null comment '专题标题',     push_time datetime not null comment '投放时间(yyyy-mm-dd hh：mm：ss)',     url varchar(512) not null comment '主题详情链接地址',     img varchar(512) not null comment '主题图片地址',     user_img varchar(512) not null comment '所属人图片地址',     shop_title varchar(128) not null comment '店铺名称',     user_id bigint unsigned not null comment '商家id',     shop_type varchar(50) not null comment '店铺类型',     shop_url varchar(512) not null comment '店铺地址',     shop_logo varchar(512) not null comment '店铺logo',     shop_desc varchar(256) comment '店铺描述',     topic_id bigint unsigned not null comment '投放主题id',     primary key (id),     unique key mark_id (mark_id)   ) comment='发现主题商品推送详情';";
        SqlInfo si = analy(s);
        List<Column> list = si.getColumnList();
        List<Column[]> l = new ArrayList<Column[]>();
        for(Column c:list){
            l.add(new Column[]{c});
            System.out.println(c);
        }
        Column[][] s1 = l.toArray(new Column[l.size()][]);
        System.out.println(s1);
    }

    public static void main1(String[] args) throws Exception {
        String s = "gmt_create datetime not null comment '创建时间'";
        Column l = getColumn(s);
        System.out.println(l);
    }

    public static SqlInfo analy(String sql) {
        sql = cleanSql(sql);
        List<String> lines = analyColumnLine(sql);
        List<Column> colList = new ArrayList<Column>();
        if(lines!=null && lines.size()>0){
            for(String line:lines){
                if(StringUtils.isNotBlank(line)) {
                    line = line.trim();
                    if (!isKeyLine(line)) {
                        try {
                            Column c = getColumn(line);
                            if(c!=null) {
                                colList.add(c);
                            }
                        }catch (Exception e){}
                    }
                }
            }
        }
        String k = analyPrimaryKey(sql);
        String n = analyTableName(sql);
        return new SqlInfo(n,k,colList);
    }
}
