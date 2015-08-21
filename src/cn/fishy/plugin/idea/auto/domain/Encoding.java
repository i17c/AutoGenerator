package cn.fishy.plugin.idea.auto.domain;

/**
 * User: duxing
 * Date: 2015.08.12 2:09
 */
public enum Encoding {
    UTF8("UTF-8"),GBK("GBK");
    private String name;

    Encoding(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Encoding get(String name){
        if(name==null)return null;
        for(Encoding e:Encoding.values()){
            if(e.getName().equals(name)){
                return e;
            }
        }
        return null;
    }
}
