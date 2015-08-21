package cn.fishy.plugin.idea.auto.domain;

import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * User: duxing
 * Date: 2015.08.13 2:08
 */
public class SqlInfo {
    private String tableName;
    private String primaryKey;
    private List<Column> columnList;

    public SqlInfo() {
    }

    public SqlInfo(String tableName, String primaryKey, List<Column> columnList) {
        this.tableName = tableName;
        this.primaryKey = primaryKey;
        this.columnList = columnList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public boolean isValid() {
        return this.columnList!=null && this.columnList.size()>0 && StringUtils.isNotBlank(tableName);
    }
}
