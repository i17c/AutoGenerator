package ${package}

import scala.beans.BeanProperty
import java.util
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
@SerialVersionUID(-1L)
class ${className} <#if pagerQuery>extends BaseQuery<#else>extends Serializable</#if>{

    <#list columnList as column>
    /**
     * ${column.comment}
     */
    @BeanProperty var ${column.property}: ${column.type} = _
    </#list>

}