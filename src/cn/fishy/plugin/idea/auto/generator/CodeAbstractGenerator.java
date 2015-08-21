package cn.fishy.plugin.idea.auto.generator;

import cn.fishy.plugin.idea.auto.domain.Column;
import com.intellij.openapi.util.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * User: duxing
 * Date: 2015.08.13 23:33
 */
public abstract class CodeAbstractGenerator {

    public abstract String space();
    public String br(){
        return "\r\n";
    }

    public abstract String generateDO(String doClassName,List<Column> columnList);
    public abstract String generateDAO(String doClassName,String queryClassName,String daoClassName,Column primaryKeyColumn);
    public abstract String generateDAOImpl(String doClassName,String queryClassName,String daoClassName,String daoImplClassName,Column primaryKeyColumn,String tableName);
    public abstract String generateQuery(String queryClassName,List<Column> columnQueryList);
    public abstract String generateBO(String boClassName,List<Column> columnList);
    public abstract String generateManager(String objClassName,String queryClassName,String managerClassName,Column primaryKeyColumn);
    public abstract String generateManagerImpl(String doClassName,String boClassName,String queryClassName,String transferClassName,String daoClassName,String managerClassName,String managerImplClassName,Column primaryKeyColumn);
    public abstract String generateTransfer(String doClassName,String boClassName,String transferClassName,List<Column> columnList);
    public abstract String generateSqlmap(String tableName, Column primaryKeyColumn, String doClassName, String daoClassName, List<Column> columnList, List<Column> columnQueryList);
    public abstract String generateBaseDAO();
    public abstract String generateBaseQuery();
    public abstract String generateDAOXml(String daoClassName, String daoImplClassName);
    public abstract String generateSQLMapConfigXml(String tableName);
    public abstract String generatePersistenceXml(String tableName);

    public static void writeToFile(File file,String fileContent){
        try {
            FileUtil.writeToFile(file, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
