package cn.fishy.plugin.idea.auto.generator;

import cn.fishy.plugin.idea.auto.domain.Column;
import cn.fishy.plugin.idea.auto.generator.scala.ScalaBaseDAOGenerator;
import cn.fishy.plugin.idea.auto.generator.scala.ScalaBaseQueryGenerator;
import cn.fishy.plugin.idea.auto.generator.scala.ScalaBoGenerator;
import cn.fishy.plugin.idea.auto.generator.scala.ScalaDAOXmlGenerator;
import cn.fishy.plugin.idea.auto.generator.scala.ScalaDaoGenerator;
import cn.fishy.plugin.idea.auto.generator.scala.ScalaDaoImplGenerator;
import cn.fishy.plugin.idea.auto.generator.scala.ScalaDoGenerator;
import cn.fishy.plugin.idea.auto.generator.scala.ScalaManagerGenerator;
import cn.fishy.plugin.idea.auto.generator.scala.ScalaManagerImplGenerator;
import cn.fishy.plugin.idea.auto.generator.scala.ScalaPersistenceXmlGenerator;
import cn.fishy.plugin.idea.auto.generator.scala.ScalaQueryGenerator;
import cn.fishy.plugin.idea.auto.generator.scala.ScalaSQLMapConfigXmlGenerator;
import cn.fishy.plugin.idea.auto.generator.scala.ScalaSqlmapGenerator;
import cn.fishy.plugin.idea.auto.generator.scala.ScalaTransferGenerator;

import java.util.List;

/**
 * User: duxing
 * Date: 2015.08.13 23:33
 */
public class ScalaGenerator extends CodeAbstractGenerator {
    @Override
    public String space() {
        return "  ";
    }

    @Override
    public String generateDO(String doClassName, List<Column> columnList) {
        return new ScalaDoGenerator().generate(doClassName, columnList);
    }

    @Override
    public String generateDAO(String doClassName, String queryClassName, String daoClassName, Column primaryKeyColumn) {
        return new ScalaDaoGenerator().generate(doClassName, queryClassName,daoClassName,primaryKeyColumn);
    }

    @Override
    public String generateDAOImpl(String doClassName, String queryClassName, String daoClassName, String daoImplClassName, Column primaryKeyColumn, String tableName) {
        return new ScalaDaoImplGenerator().generate(doClassName, queryClassName,daoClassName,daoImplClassName, primaryKeyColumn,tableName);
    }

    @Override
    public String generateQuery(String queryClassName, List<Column> columnQueryList) {
        return new ScalaQueryGenerator().generate(queryClassName, columnQueryList);
    }

    @Override
    public String generateBO(String boClassName, List<Column> columnList) {
        return new ScalaBoGenerator().generate(boClassName, columnList);
    }

    @Override
    public String generateManager(String objClassName, String queryClassName, String managerClassName, Column primaryKeyColumn) {
        return new ScalaManagerGenerator().generate(objClassName, queryClassName,managerClassName,primaryKeyColumn);
    }

    @Override
    public String generateManagerImpl(String doClassName, String boClassName, String queryClassName, String transferClassName, String daoClassName, String managerClassName, String managerImplClassName,Column primaryKeyColumn) {
        return new ScalaManagerImplGenerator().generate(doClassName, boClassName, queryClassName,transferClassName, daoClassName, managerClassName, managerImplClassName, primaryKeyColumn);
    }

    @Override
    public String generateTransfer(String doClassName, String boClassName, String transferClassName, List<Column> columnList) {
        return new ScalaTransferGenerator().generate(doClassName, boClassName, transferClassName, columnList);
    }

    @Override
    public String generateSqlmap(String tableName, Column primaryKeyColumn, String doClassName, String daoClassName, List<Column> columnList, List<Column> columnQueryList) {
        return new ScalaSqlmapGenerator().generate(tableName, primaryKeyColumn, doClassName, daoClassName, columnList, columnQueryList);
    }

    @Override
    public String generateBaseDAO() {
        return new ScalaBaseDAOGenerator().generate();
    }

    @Override
    public String generateBaseQuery() {
        return new ScalaBaseQueryGenerator().generate();
    }

    @Override
    public String generateDAOXml(String daoClassName, String daoImplClassName) {
        return new ScalaDAOXmlGenerator().generate(daoClassName,daoImplClassName);
    }

    @Override
    public String generateSQLMapConfigXml(String tableName) {
        return new ScalaSQLMapConfigXmlGenerator().generate(tableName);
    }

    @Override
    public String generatePersistenceXml(String tableName) {
        return new ScalaPersistenceXmlGenerator().generate(tableName);
    }
}
