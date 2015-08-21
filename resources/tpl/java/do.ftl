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

public class ${className} <#if pagerQuery>extends BaseQuery<#else>implements Serializable</#if>{
    private static final long serialVersionUID = -1L;

    <#list columnList as column>
    /**
     * ${column.comment}
     */
    private ${column.type} ${column.property};
    </#list>

    <#list columnList as column>
    /**
     * ${column.property} getter & setter
     */
    public ${column.type} get${column.key}() {
        return ${column.property};
    }
    public void set${column.key}(${column.type} ${column.property}){
        this.${column.property} = ${column.property};
    }

    </#list>
}