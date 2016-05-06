package ${package};

<#list importList as import>
import ${import};
</#list>

/**
 * ${className}
 *
 * User: ${user}
 * Date: ${today}
 * Generate by ${autoName}
 * Powered by duxing@Taobao
 */

public class ${className} extends BaseDAO implements ${daoClassName}{

    private static final String NAMESPACE = "${daoClassName}.";

    /**
     * insert one data
     *
     * @param ${doPropertyName} object
     * @return primaryKey ${primaryKeyType}
     * @throws Exception exception
     */
     public ${primaryKeyType} insert(${doClassName} ${doPropertyName}) throws Exception{
        <#if daoUseSequence>
        ${primaryKeyType} ${primaryKeyName} = this.getNextId("${tableName}");
        ${doPropertyName}.set${primaryKeyNameAtMethod}(${primaryKeyName});
        insert(NAMESPACE + "insert", ${doPropertyName});
        return ${primaryKeyName};
        <#else>
        return insert(NAMESPACE + "insert", ${doPropertyName});
        </#if>
     }

    /**
     * update data
     *
     * @param ${doPropertyName} object
     * @return update num
     * @throws Exception exception
     */
    public int update(${doClassName} ${doPropertyName}) throws Exception{
        return update(NAMESPACE + "update", ${doPropertyName});
    }
    /**
     * get an obj
     *
     * @param ${primaryKeyName} key
     * @return do obj
     * @throws Exception exception
     */
    public ${doClassName} get(${primaryKeyType} ${primaryKeyName}) throws Exception{
        return (${doClassName}) queryForObject(NAMESPACE + "select", ${primaryKeyName});
    }

    /**
     * query a list
     *
     * @param ${queryPropertyName} query
     * @return List
     * @throws Exception exception
     */
    @SuppressWarnings("unchecked")
    public List<${doClassName}> getList(${queryClassName} ${queryPropertyName}) throws Exception{
        return (List<${doClassName}>) queryForList(NAMESPACE + "queryList", ${queryPropertyName});
    }

    /**
     * query count
     *
     * @param ${queryPropertyName} query
     * @return Integer
     * @throws Exception exception
     */
    public Integer getCount(${queryClassName} ${queryPropertyName}) throws Exception{
        return (Integer) queryForObject(NAMESPACE + "queryCount", ${queryPropertyName});
    }

    /**
    <#if daoLogicDelete>
     * logically delete one line
    <#else>
     * physically delete one line
    </#if>
     *
     * @param ${primaryKeyName} key
     * @return delete num
     * @throws Exception exception
     */
    public int delete(${primaryKeyType} ${primaryKeyName}) throws Exception{
        <#if daoLogicDelete>
        return update(NAMESPACE + "delete", ${primaryKeyName});
        <#else>
        return delete(NAMESPACE + "delete", ${primaryKeyName});
        </#if>
    }

}