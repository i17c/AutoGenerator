package ${package}

<#list importList as import>
import ${import}
</#list>
import scala.collection.JavaConversions._

/**
 * ${transferClassName}
 *
 * User: ${user}
 * Date: ${today}
 * Generate by ${autoName}
 * Powered by duxing@Taobao
 */

object ${transferClassName}{
    /**
     * change do to bo
     */
    def toBO(d: ${doClassName}): ${boClassName} = {
        if (d == null) return null
        val bo: ${boClassName} = new ${boClassName}()
        <#list columnList as column>
        bo.set${column.key}(d.get${column.key}())
        </#list>
        bo
    }

    /**
     * change bo to do
     */
    def toDO(bo: ${boClassName}): ${doClassName} = {
        if (bo == null) return null
        val d: ${doClassName} = new ${doClassName}()
        <#list columnList as column>
        d.set${column.key}(bo.get${column.key}())
        </#list>
        d
    }
    /**
     * change doList to boList
     */
    def toBOList(doList: util.List[${doClassName}]): util.List[${boClassName}] = {
        if(doList == null) {
            return new util.ArrayList[${boClassName}]()
        }
        val boList: util.List[${boClassName}] = new util.ArrayList[${boClassName}]()
        doList.foreach(d => {
            if(d != null) {
                boList.add(${transferClassName}.toBO(d))
            }
        })
        boList
    }
    /**
     * change boList to doList
     */
    def toDOList(boList: util.List[${boClassName}]): util.List[${doClassName}] = {
        if(boList == null) {
            return new util.ArrayList[${doClassName}]()
        }
        val doList: util.List[${doClassName}] = new util.ArrayList[${doClassName}]()
        boList.foreach(bo => {
            if(bo != null) {
                doList.add(${transferClassName}.toDO(bo))
            }
        })
        doList
    }

}