package cn.fishy.plugin.idea.auto.generator;

import cn.fishy.plugin.idea.auto.domain.Column;

/**
 * User: duxing
 * Date: 2015.08.13 1:31
 */
public interface DaoImplGenerator {
    public String generate(String doClassName, String queryClassName, String daoClassName, String daoImplClassName, Column primaryKeyColumn, String tableName);
}
