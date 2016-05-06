package cn.fishy.plugin.idea.auto.generator.java;

import cn.fishy.plugin.idea.auto.constant.GenerateType;
import cn.fishy.plugin.idea.auto.domain.Code;
import cn.fishy.plugin.idea.auto.domain.Column;
import cn.fishy.plugin.idea.auto.generator.BaseGenerator;
import cn.fishy.plugin.idea.auto.generator.TransferGenerator;
import cn.fishy.plugin.idea.auto.util.PathHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: duxing
 * Date: 2015.08.13 1:35
 */
public class JavaTransferGenerator extends BaseGenerator implements TransferGenerator {
    @Override
    public String generate(String doClassName, String boClassName, String transferClassName, List<Column> columnList) {
        Map<String,Object> map = initMap();
        map.put("doClassName",doClassName);
        map.put("boClassName", boClassName);
        map.put("transferClassName", transferClassName);
        map.put("columnList", columnList);
        List<String> importList = getImportList(new ArrayList<Column>(), false, true);
        importList.add(PathHolder.impt(GenerateType.DO, doClassName));
        importList.add(PathHolder.impt(GenerateType.BO, boClassName));
        map.put("importList", importList);
        return generate(map);
    }

    @Override
    public Code getCode() {
        return Code.JAVA;
    }

    @Override
    public GenerateType generateType() {
        return GenerateType.Transfer;
    }
}
