package cn.fishy.plugin.idea.auto.generator.java;

import cn.fishy.plugin.idea.auto.constant.GenerateType;
import cn.fishy.plugin.idea.auto.domain.Code;
import cn.fishy.plugin.idea.auto.domain.Column;
import cn.fishy.plugin.idea.auto.domain.Setting;
import cn.fishy.plugin.idea.auto.generator.BaseGenerator;
import cn.fishy.plugin.idea.auto.generator.ManagerGenerator;
import cn.fishy.plugin.idea.auto.storage.SettingManager;
import cn.fishy.plugin.idea.auto.util.NameUtil;
import cn.fishy.plugin.idea.auto.util.PathHolder;

import java.util.List;
import java.util.Map;

/**
 * User: duxing
 * Date: 2015.08.13 1:35
 */
public class JavaManagerGenerator extends BaseGenerator implements ManagerGenerator {
    @Override
    public String generate(String objClassName, String queryClassName, String managerClassName, Column primaryKeyColumn) {
        Setting setting = SettingManager.get();
        List<String> importList = getImportList(primaryKeyColumn, false, true);
        importList.add(PathHolder.impt(GenerateType.Query, queryClassName));
        if(setting.isManagerUseBO()) {
            importList.add(PathHolder.impt(GenerateType.BO, objClassName));
        }else{
            importList.add(PathHolder.impt(GenerateType.DO, objClassName));
        }
        Map<String,Object> map = initMap();
        map.put("className", managerClassName);
        map.put("objClassName", objClassName);
        map.put("objPropertyName", NameUtil.lowFirst(objClassName));
        map.put("queryClassName", queryClassName);
        map.put("queryPropertyName", NameUtil.lowFirst(queryClassName));
//        map.put("primaryKeyColumn", primaryKeyColumn);
        try{
            map.put("primaryKeyName", primaryKeyColumn.getProperty());
            map.put("primaryKeyType", primaryKeyColumn.getType());
        }catch (Exception e){
            map.put("primaryKeyName", "id");
            map.put("primaryKeyType", "Long");
        }
        map.put("importList", importList);
        return generate("tpl/"+getCode().getTplPath()+"/manager.ftl", map);
    }

    @Override
    public Code getCode() {
        return Code.JAVA;
    }

    @Override
    public GenerateType generateType() {
        return GenerateType.Manager;
    }
}
