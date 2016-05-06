package cn.fishy.plugin.idea.auto.generator;

import cn.fishy.plugin.idea.auto.constant.TypePath;
import cn.fishy.plugin.idea.auto.domain.Setting;
import cn.fishy.plugin.idea.auto.storage.Env;
import cn.fishy.plugin.idea.auto.constant.GenerateType;
import cn.fishy.plugin.idea.auto.domain.Code;
import cn.fishy.plugin.idea.auto.domain.Column;
import cn.fishy.plugin.idea.auto.storage.SettingManager;
import cn.fishy.plugin.idea.auto.util.FileWriter;
import cn.fishy.plugin.idea.auto.util.NameUtil;
import cn.fishy.plugin.idea.auto.util.PathHolder;

import java.util.List;

/**
 * User: duxing
 * Date: 2015.08.14 0:17
 */
public class GeneratorAdaptor {
    private String tableName;
    private String path;
    private String code;
    private Column primaryColumn;
    private List<Column> doColumnList;
    private List<Column> queryColumnList;
    private CodeAbstractGenerator generator;


    public GeneratorAdaptor() {
    }

    public GeneratorAdaptor(String tableName,String path, String code) {
        if(!path.endsWith(Env.sp) && !path.endsWith("/")){
            path+="/";
        }
        path = path.replaceAll("\\"+Env.sp,"/");
        this.tableName = tableName;
        this.code = code;
        this.path = path;
        PathHolder.init(path,Code.get(code));
        if(code.equals("JAVA")){
            setGenerator(new JavaGenerator());
        }else if(code.equals("SCALA")){
            setGenerator(new ScalaGenerator());
        }
    }

    public void setPrimaryColumn(Column primaryColumn) {
        this.primaryColumn = primaryColumn;
    }

    public void setColumnList(List<Column> doColumnList, List<Column> queryColumnList) {
        this.doColumnList = changeEncoding(doColumnList);
        this.queryColumnList = changeEncoding(queryColumnList);
    }

    private List<Column> changeEncoding(List<Column> columnList) {
        return columnList;
    }



    private String path(GenerateType generateType) {
        String ph = PathHolder.resourcesPath+generateType.getPath();
        if(TypePath.SRC.equals(generateType.getTypePath())) {
            ph =path+generateType.getPath();
        }else if(TypePath.RESOURCES.equals(generateType.getTypePath())){
            ph = PathHolder.resourcesPath+generateType.getPath();
        }
        return ph;
    }

    public static String getJavaPath(String scalaPath) {
        String markScala = "/src/main/scala";
        String markJava = "/src/main/java";
        int i = scalaPath.indexOf(markScala);
        if(i>-1){
            return scalaPath.replace(markScala, markJava);
        }else{
            return scalaPath.replaceAll("\\/scala\\/", "/java/");
        }
    }
    public boolean generateBaseDAO() {
        String name = GenerateType.BaseDAO.getSuffix();
        String path = path(GenerateType.BaseDAO);
        String s = generator.generateBaseDAO();
        if(Code.SCALA.equals(Code.get(code))){
            path = getJavaPath(path);
            name = name + Code.JAVA.getExt();
        }else{
            name = addExt(name);
        }
        return FileWriter.write(path,name,r(s));
    }

    public boolean generateBaseQuery() {
        String name = GenerateType.BaseQuery.getSuffix();
        String path = path(GenerateType.BaseQuery);
        String s = generator.generateBaseQuery();
        if(Code.SCALA.equals(Code.get(code))){
            path = getJavaPath(path);
            name = name + Code.JAVA.getExt();
        } else{
            name = addExt(name);
        }
        return FileWriter.write(path,name,r(s));
    }

    public boolean generateDO(){
        String name = name(GenerateType.DO);
        String path = path(GenerateType.DO);
        String s = generator.generateDO(name, getDoColumnList());
        return FileWriter.write(path,addExt(name),r(s));
    }

    public boolean generateDAO(){
        String name = name(GenerateType.DAO);
        String path = path(GenerateType.DAO);
        String s = generator.generateDAO(name(GenerateType.DO), name(GenerateType.Query), name, primaryColumn);
        return FileWriter.write(path, addExt(name), r(s));
    }
    public boolean generateDAOImpl(){
        String name = name(GenerateType.DAOImpl);
        String path = path(GenerateType.DAOImpl);
        String s = generator.generateDAOImpl(name(GenerateType.DO), name(GenerateType.Query), name(GenerateType.DAO), name, primaryColumn, tableName);
        return FileWriter.write(path,addExt(name),r(s));
    }
    public boolean generateQuery(){
        String name = name(GenerateType.Query);
        String path = path(GenerateType.Query);
        String s = generator.generateQuery(name, getQueryColumnList());
        return FileWriter.write(path,addExt(name),r(s));
    }
    public boolean generateBO(){
        String name = name(GenerateType.BO);
        String path = path(GenerateType.BO);
        String s = generator.generateBO(name, getDoColumnList());
        return FileWriter.write(path,addExt(name),r(s));
    }
    public boolean generateManager(){
        String name = name(GenerateType.Manager);
        String path = path(GenerateType.Manager);
        Setting setting = SettingManager.get();
        String s = generator.generateManager(setting.isManagerUseBO()?name(GenerateType.BO) : name(GenerateType.DO), name(GenerateType.Query), name, primaryColumn);
        return FileWriter.write(path,addExt(name),r(s));
    }
    public boolean generateManagerImpl(){
        String name = name(GenerateType.ManagerImpl);
        String path = path(GenerateType.ManagerImpl);
        String s = generator.generateManagerImpl(name(GenerateType.DO), name(GenerateType.BO), name(GenerateType.Query), name(GenerateType.Transfer), name(GenerateType.DAO), name(GenerateType.Manager), name, primaryColumn);
        return FileWriter.write(path,addExt(name),r(s));
    }
    public boolean generateTransfer(){
        String name = name(GenerateType.Transfer);
        String path = path(GenerateType.Transfer);
        String s = generator.generateTransfer(name(GenerateType.DO), name(GenerateType.BO), name(GenerateType.Transfer), doColumnList);
        return FileWriter.write(path, addExt(name), r(s));
    }
    public boolean generateSqlmap(){
        String name = name(GenerateType.SQLMap);
        String path = path(GenerateType.SQLMap);
        String s = generator.generateSqlmap(tableName, primaryColumn, name(GenerateType.DO), name(GenerateType.DAO), doColumnList, queryColumnList);
        return FileWriter.write(path, addExtXml(name),r(s));
    }

    public boolean generateDAOXml() {
        String name = GenerateType.DAOXml.getSuffix();
        String path = path(GenerateType.DAOXml);
        String s = generator.generateDAOXml(name(GenerateType.DAO),name(GenerateType.DAOImpl));
        return FileWriter.write(path,addExtXml(name),r(s));
    }

    public boolean generateSQLMapConfigXml() {
        String name = GenerateType.SQLMapConfigXml.getSuffix();
        String path = path(GenerateType.SQLMapConfigXml);
        String s = generator.generateSQLMapConfigXml(tableName);
        return FileWriter.write(path,addExtXml(name),r(s));
    }

    public boolean generatePersistenceXml() {
        String name = GenerateType.PersistenceXml.getSuffix();
        String path = path(GenerateType.PersistenceXml);
        String s = generator.generatePersistenceXml(tableName);
        return FileWriter.write(path,addExtXml(name),r(s));
    }

    public String name(GenerateType type) {
        return NameUtil.name(this.tableName, type);
    }
    public String addExt(String name) {
        return name + Code.get(code).getExt();
    }

    public String addExtXml(String name) {
        return name + ".xml";
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDoColumnList(List<Column> doColumnList) {
        this.doColumnList = doColumnList;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setQueryColumnList(List<Column> queryColumnList) {
        this.queryColumnList = queryColumnList;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCode() {
        return code;
    }

    public List<Column> getDoColumnList() {
        return doColumnList;
    }

    public Column getPrimaryColumn() {
        return primaryColumn;
    }

    public List<Column> getQueryColumnList() {
        return queryColumnList;
    }

    public String getTableName() {
        return tableName;
    }

    public String getPath() {
        return path;
    }

    public CodeAbstractGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(CodeAbstractGenerator generator) {
        this.generator = generator;
    }


    public byte[] r(String s){
        if(s==null)return null;
        return s.getBytes(Env.encodeTo);
    }

    public String s(String s){
        if(s==null)return null;
        return new String(s.getBytes(Env.encodeTo),Env.encodeFrom);
    }

    public static void main(String[] args) throws Exception {
        String s = "/Users/project/ska/src/main/scala/com/taobao/biz";
        System.out.println(getJavaPath(s));
        s = "/Users/project/ska/src1/main/scala/com/taobao/biz";
        System.out.println(getJavaPath(s));
    }

}
