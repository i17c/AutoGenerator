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

public class ${className} implements ${managerClassName}{

    private ${daoClassName} ${daoPropertyName};

    public void set${daoClassName}(${daoClassName} ${daoPropertyName}) {
        this.${daoPropertyName} = ${daoPropertyName};
    }

    /**
     * insert one data
     *
     * @param ${objPropertyName} object
     * @return primaryKey ${primaryKeyType}
     * @throws Exception exception
     */
    public ${primaryKeyType} insert(${objClassName} ${objPropertyName}) throws Exception{
        <#if managerUseBO>
        return ${daoPropertyName}.insert(${transferClassName}.toDO(${objPropertyName}));
        <#else>
        return ${daoPropertyName}.insert(${objPropertyName});
        </#if>
    }

    /**
     * update data
     *
     * @param ${objPropertyName} object
     * @return update num
     * @throws Exception exception
     */
    public int update(${objClassName} ${objPropertyName}) throws Exception{
        <#if managerUseBO>
        return ${daoPropertyName}.update(${transferClassName}.toDO(${objPropertyName}));
        <#else>
        return ${daoPropertyName}.update(${objPropertyName});
        </#if>
    }

    /**
     * get an obj
     *
     * @param ${primaryKeyName} key
     * @return do obj
     * @throws Exception exception
     */
    public ${objClassName} get(${primaryKeyType} ${primaryKeyName}) throws Exception{
        <#if managerUseBO>
        return ${transferClassName}.toBO(${daoPropertyName}.get(${primaryKeyName}));
        <#else>
        return ${daoPropertyName}.get(${primaryKeyName});
        </#if>
    }

    /**
     * query a list
     *
     * @param ${queryPropertyName} query
     * @return List
     * @throws Exception exception
     */
    public List<${objClassName}> getList(${queryClassName} ${queryPropertyName}) throws Exception{
        <#if managerUseBO>
        return ${transferClassName}.toBOList(${daoPropertyName}.getList(${queryPropertyName}));
        <#else>
        return ${daoPropertyName}.getList(${queryPropertyName});
        </#if>
    }

    /**
     * query count
     *
     * @param ${queryPropertyName} query
     * @return Integer
     * @throws Exception exception
     */
    public Integer getCount(${queryClassName} ${queryPropertyName}) throws Exception{
        return ${daoPropertyName}.getCount(${queryPropertyName});
    }

    /**
     * logic delete one line
     *
     * @param ${primaryKeyName} key
     * @return delete num
     * @throws Exception exception
     */
    public int delete(${primaryKeyType} ${primaryKeyName}) throws Exception{
        return ${daoPropertyName}.delete(${primaryKeyName});
    }

}