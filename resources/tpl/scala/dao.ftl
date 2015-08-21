package ${package}

<#list importList as import>
import ${import}
</#list>

/**
 * ${className}
 *
 * User: ${user}
 * Date: ${today}
 * Generate by ${autoName}
 * Powered by duxing@Taobao
 */

trait ${className} extends BaseDAO{


    /**
     * insert one data
     *
     * @param ${objPropertyName} object
     * @return primaryKey ${primaryKeyType}
     * @throws Exception exception
     */
    @throws(classOf[Exception])
    def insert(${objPropertyName}: ${objClassName}): ${primaryKeyType}

    /**
     * update data
     *
     * @param ${objPropertyName} object
     * @return update num
     * @throws Exception exception
     */
    @throws(classOf[Exception])
    def update(${objPropertyName}: ${objClassName}): Int

    /**
     * get an obj
     *
     * @param ${primaryKeyName} key
     * @return obj obj
     * @throws Exception exception
     */
    @throws(classOf[Exception])
    def get(${primaryKeyName}: ${primaryKeyType}): ${objClassName}
    
    /**
     * query a list
     *
     * @param ${queryPropertyName} query
     * @return List
     * @throws Exception exception
     */
    @throws(classOf[Exception])
    def getList(${queryPropertyName}: ${queryClassName}): util.List[${objClassName}]

    /**
     * query count
     *
     * @param ${queryPropertyName} query
     * @return Integer
     * @throws Exception exception
     */
    @throws(classOf[Exception])
    def getCount(${queryPropertyName}: ${queryClassName}): Int

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
    def delete(${primaryKeyName}: ${primaryKeyType}): Int

}