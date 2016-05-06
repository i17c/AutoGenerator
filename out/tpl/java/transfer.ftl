package ${package};

<#list importList as import>
import ${import};
</#list>

/**
 * ${transferClassName}
 *
 * User: ${user}
 * Date: ${today}
 * Generate by ${autoName}
 * Powered by duxing@Taobao
 */

public class ${transferClassName}{

    public static ${boClassName} toBO(${doClassName} d) {
        if (d == null) return null;
        ${boClassName} bo = new ${boClassName}();
        <#list columnList as column>
        bo.set${column.key}(d.get${column.key}());
        </#list>
        return bo;
    }

    public static ${doClassName} toDO(${boClassName} bo) {
        if (bo == null) return null;
        ${doClassName} d = new ${doClassName}();
        <#list columnList as column>
        d.set${column.key}(bo.get${column.key}());
        </#list>
        return d;
    }

    public static List<${boClassName}> toBOList(List<${doClassName}> doList){
        if(doList == null) {
            return new ArrayList<${boClassName}>();
        }
        List<${boClassName}> boList = new ArrayList<${boClassName}>();
        for(${doClassName} d : doList) {
            if(d != null) {
                boList.add(${transferClassName}.toBO(d));
            }
        }
        return boList;
    }

    public static List<${doClassName}> toDOList(List<${boClassName}> boList){
        if(boList == null) {
            return new ArrayList<${doClassName}>();
        }
        List<${doClassName}> doList = new ArrayList<${doClassName}>();
        for(${boClassName} bo : boList) {
            if(bo != null) {
                doList.add(${transferClassName}.toDO(bo));
            }
        }
        return doList;
    }

}