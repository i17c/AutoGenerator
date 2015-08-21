package cn.fishy.plugin.idea.auto.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * User: duxing
 * Date: 2015.08.14 1:25
 */
public enum ColumnEnum {
    No("No",0),
    DO("DO",1),
    Query("Query",2),
    Column("Column",3),
    DBType("DBType",4),
    CodeType("CodeType",5),
    ClassProperty("ClassProperty",6),
    Primary("Primary",7),
    Comment("Comment",8);
    private String name;
    private int order;

    ColumnEnum(String name, int order) {
        this.name = name;
        this.order = order;
    }

    public static List<String> getColumnNameList(){
        List<String> list = new ArrayList<String>();
        for(ColumnEnum c : ColumnEnum.values()){
            list.add(c.getName());
        }
        return list;
    }

    public static String[] getColumnNames(){
        List<String> list = getColumnNameList();
        return list.toArray(new String[list.size()]);
    }

    public static ColumnEnum get(String name){
        if(name==null)return null;
        for(ColumnEnum c:ColumnEnum.values()){
            if(name.toLowerCase().equals(c.getName().toLowerCase())){
                return c;
            }
        }
        return null;
    }

    public static ColumnEnum getByOrder(int order){
        for(ColumnEnum c:ColumnEnum.values()){
            if(order==c.getOrder()){
                return c;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }
}
