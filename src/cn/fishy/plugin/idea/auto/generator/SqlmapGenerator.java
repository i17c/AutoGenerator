package cn.fishy.plugin.idea.auto.generator;

import cn.fishy.plugin.idea.auto.domain.Column;

import java.util.List;

/**
 * User: duxing
 * Date: 2015.08.13 1:37
 */
public interface SqlmapGenerator {
    public String generate(String tableName, Column primaryKeyColumn, String doClassName, String daoClassName, List<Column> columnList, List<Column> columnQueryList);
}
