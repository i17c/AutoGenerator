package cn.fishy.plugin.idea.auto.generator.java;

import cn.fishy.plugin.idea.auto.constant.GenerateType;
import cn.fishy.plugin.idea.auto.domain.Code;
import cn.fishy.plugin.idea.auto.domain.Column;
import cn.fishy.plugin.idea.auto.domain.Setting;
import cn.fishy.plugin.idea.auto.generator.BaseGenerator;
import cn.fishy.plugin.idea.auto.generator.DoGenerator;
import cn.fishy.plugin.idea.auto.storage.SettingManager;
import cn.fishy.plugin.idea.auto.util.PathHolder;

import java.util.List;
import java.util.Map;

/**
 * User: duxing
 * Date: 2015.08.13 1:02
 */
public class JavaDoGenerator extends BaseGenerator implements DoGenerator {

    public String generate(String className, List<Column> columnList) {
        Map<String,Object> map = initMap();
        map.put("className",className);
        map.put("columnList", columnList);
        List<String> importList = getImportList(columnList, true);
        if(generateType().equals(GenerateType.Query)){
            Setting setting = SettingManager.get();
            map.put("pagerQuery",setting.isPagerQuery());
            importList.add(PathHolder.impt(GenerateType.BaseQuery, GenerateType.BaseQuery.getName()));
        }else{
            map.put("pagerQuery",false);
        }
        map.put("importList", importList);
        return super.generate(map);
    }

    @Override
    public Code getCode() {
        return Code.JAVA;
    }

    @Override
    public GenerateType generateType() {
        return GenerateType.DO;
    }
}
