package ${package}

<#list importList as import>
import ${import}
</#list>
import org.springframework.stereotype.Repository

/**
 * ${className}
 *
 * User: ${user}
 * Date: ${today}
 * Generate by ${autoName}
 * Powered by duxing@Taobao
 */

<#--@ChildOf(parent = "baseDAO")-->
@Repository
class ${className} extends BaseDAO with ${daoClassName}{

    private val NAMESPACE: String = "${daoClassName}."

    /**
     * insert one data
     *
     * @param ${doPropertyName} object
     * @return primaryKey ${primaryKeyType}
     * @throws Exception exception
     */
    @throws(classOf[Exception])
    override def insert(${doPropertyName}: ${doClassName}): ${primaryKeyType} = {
        <#if daoUseSequence>
        val ${primaryKeyName} = this.getNextId("${tableName}")
        ${doPropertyName}.set${primaryKeyNameAtMethod}(${primaryKeyName})
        insert(NAMESPACE + "insert", ${doPropertyName})
        ${primaryKeyName}
        <#else>
        insert(NAMESPACE + "insert", ${doPropertyName})
        </#if>
     }

    /**
     * update data
     *
     * @param ${doPropertyName} object
     * @return update num
     * @throws Exception exception
     */
    @throws(classOf[Exception])
    override def update(${doPropertyName}: ${doClassName}): Int = {
        update(NAMESPACE + "update", ${doPropertyName})
    }
    /**
     * get an obj
     *
     * @param ${primaryKeyName} key
     * @return do obj
     * @throws Exception exception
     */
    @throws(classOf[Exception])
    override def get(${primaryKeyName}: ${primaryKeyType}): ${doClassName} = {
        queryForObject(NAMESPACE + "select", ${primaryKeyName}).asInstanceOf[(${doClassName})]
    }

    /**
     * query a list
     *
     * @param ${queryPropertyName} query
     * @return List
     * @throws Exception exception
     */
    @SuppressWarnings("unchecked")
    @throws(classOf[Exception])
    override def getList(${queryPropertyName}: ${queryClassName}): util.List[${doClassName}] = {
        queryForList(NAMESPACE + "queryList", ${queryPropertyName}).asInstanceOf[util.List[${doClassName}]]
    }

    /**
     * query count
     *
     * @param ${queryPropertyName} query
     * @return Integer
     * @throws Exception exception
     */
    @throws(classOf[Exception])
    override def getCount(${queryPropertyName}: ${queryClassName}): Int = {
        queryForObject(NAMESPACE + "queryCount", ${queryPropertyName}).asInstanceOf[Int]
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
    @throws(classOf[Exception])
    override def delete(${primaryKeyName}: ${primaryKeyType}): Int = {
        <#if daoLogicDelete>
        update(NAMESPACE + "delete", ${primaryKeyName})
        <#else>
        delete(NAMESPACE + "delete", ${primaryKeyName})
        </#if>
    }

}