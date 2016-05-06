package cn.fishy.plugin.idea.auto.domain;

/**
 * User: duxing
 * Date: 2015.08.12 2:09
 */
public enum Code {
    JAVA("JAVA",".java","src/main/java","src/test/java","src/main/resources"),SCALA("SCALA",".scala","src/main/scala","src/test/scala","src/main/resources");
    private String name;
    private String ext;
    private String sign;
    private String signTest;
    private String resources;

    Code(String name,String ext,String sign,String signTest,String resources) {
        this.name = name;
        this.ext = ext;
        this.sign = sign;
        this.signTest = signTest;
        this.resources = resources;
    }

    public String getExt() {
        return ext;
    }

    public String getName() {
        return name;
    }

    public String getTplPath() {
        return name.toLowerCase()+"/";
    }

    public String getSign() {
        return sign;
    }

    public String getResources() {
        return resources;
    }

    public static Code get(String c){
        if(c==null)return null;
        for(Code code:Code.values()){
            if(code.getName().toLowerCase().equals(c.toLowerCase())){
                return code;
            }
        }
        return null;
    }

    public String getSignTest() {
        return signTest;
    }
}
