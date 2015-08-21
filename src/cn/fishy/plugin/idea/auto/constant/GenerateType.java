package cn.fishy.plugin.idea.auto.constant;

import cn.fishy.plugin.idea.auto.storage.Env;

/**
 * User: duxing
 * Date: 2015.08.14 1:25
 */

public enum GenerateType {
    DO("DO","DO","dal/dataobject/","dal.dataobject",TypePath.SRC),
    BO("BO","BO","bo/","bo",TypePath.SRC),
    Query("Query","Query","dal/query/","dal.query",TypePath.SRC),
    SQLMap("SQLMap","_sqlmap","dal/sqlmap/","dal.dataobject",TypePath.RESOURCES),
    DAO("DAO","DAO","dal/dao/","dal.dao",TypePath.SRC),
    DAOImpl("DAOImpl","DAOImpl","dal/dao/impl/","dal.dao.impl",TypePath.SRC),
    Manager("Manager","Manager","manager/","manager",TypePath.SRC),
    ManagerImpl("ManagerImpl","ManagerImpl","manager/impl/","manager.impl",TypePath.SRC),
    Transfer("Transfer","Transfer","transfer/","transfer",TypePath.SRC),
    BaseDAO("BaseDAO","BaseDAO","dal/dao/","dal.dao",TypePath.SRC),
    BaseQuery("BaseQuery","BaseQuery","dal/query/","dal.query",TypePath.SRC),
    DAOXml("DAO.Xml","dao-sample","dal/","dal.dao.impl",TypePath.RESOURCES),
    SQLMapConfigXml("SQLMap-Config.Xml","sqlmap-config-sample","dal/","dal",TypePath.RESOURCES),
    PersistenceXml("Persistence.Xml","persistence-sample","dal/","dal.dao",TypePath.RESOURCES),
    ALL("All","All","","",null);
    private String name;
    private String suffix;
    private String path;
    private String pkg;
    private TypePath typePath;

    GenerateType(String name, String suffix, String path, String pkg,TypePath typePath) {
        this.name = name;
        this.suffix = suffix;
        this.path = path;
        this.pkg = pkg;
        this.typePath = typePath;
    }

    public static GenerateType get(String name){
        if(name==null)return null;
        for(GenerateType c: GenerateType.values()){
            if(name.toLowerCase().equals(c.getName().toLowerCase())){
                return c;
            }
        }
        return null;
    }

    public static GenerateType getBySuffix(String suffix){
        if(suffix==null)return null;
        for(GenerateType c: GenerateType.values()){
            if(c.getSuffix().toLowerCase().equals(suffix.toLowerCase())){
                return c;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getPath() {
        if("/".equals(Env.sp)){ //mac linux
            return path;
        }else{ //widows
            return path.replaceAll("\\/","\\"+Env.sp);
        }
    }

    public String getPkg() {
        return pkg;
    }

    public TypePath getTypePath() {
        return typePath;
    }
}
