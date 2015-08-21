package cn.fishy.plugin.idea.auto.domain;

import cn.fishy.plugin.idea.auto.util.NameUtil;
import cn.fishy.plugin.idea.auto.util.TypeChanger;

/**
 * User: duxing
 * Date: 2015.08.12 3:13
 */
public class Column {

    private String name;
    private String type;
    private String typeStr;
    private String comment;
    private boolean isPrimaryKey;
    private String property;

    public Column() {
    }

    public Column(String name, String typeStr) {
        this.name = name;
        this.typeStr = TypeChanger.clean(typeStr);
        this.type = TypeChanger.getType(this.typeStr);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setIsPrimaryKey(boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public String toString(){
        String r = "{";
        if(name!=null){
            r+="\"name\"=\""+name+"\"";
        }
        if(property!=null){
            if(!r.equals("{")){r+=",";}
            r+="\"property\"=\""+property+"\"";
        }
        if(type!=null){
            if(!r.equals("{")){r+=",";}
            r+="\"type\"=\""+type+"\"";
        }
        if(typeStr!=null){
            if(!r.equals("{")){r+=",";}
            r+="\"typeStr\"=\""+typeStr+"\"";
        }
        if(comment!=null){
            if(!r.equals("{")){r+=",";}
            r+="\"comment\"=\""+comment+"\"";
        }
        if(isPrimaryKey){
            if(!r.equals("{")){r+=",";}
            r+="\"isPrimaryKey\"=\""+isPrimaryKey+"\"";
        }
        r+="}";
        return r;
    }


    public String getProperty() {
        if(property==null)property= NameUtil.propertyName(this.getName());
        return property;
    }

    public String getKey() {
        if(property==null)return null;
        return NameUtil.upFirst(property);
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String isPrimaryWithSet(String primary){
        this.isPrimaryKey = this.getName().toLowerCase().equals(primary.toLowerCase());
        return this.isPrimaryKey()?"Y":"";
    }

    public String isPrimary(){
        return this.isPrimaryKey()?"Y":"";
    }
}
