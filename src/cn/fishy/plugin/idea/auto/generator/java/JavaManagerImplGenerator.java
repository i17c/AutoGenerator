package cn.fishy.plugin.idea.auto.generator.java;

import cn.fishy.plugin.idea.auto.constant.GenerateType;
import cn.fishy.plugin.idea.auto.domain.Code;
import cn.fishy.plugin.idea.auto.domain.Column;
import cn.fishy.plugin.idea.auto.domain.Setting;
import cn.fishy.plugin.idea.auto.generator.BaseGenerator;
import cn.fishy.plugin.idea.auto.generator.ManagerImplGenerator;
import cn.fishy.plugin.idea.auto.storage.SettingManager;
import cn.fishy.plugin.idea.auto.util.NameUtil;
import cn.fishy.plugin.idea.auto.util.PathHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: duxing
 * Date: 2015.08.13 1:35
 */
public class JavaManagerImplGenerator extends BaseGenerator implements ManagerImplGenerator {
    @Override
    public String generate(String doClassName, String boClassName, String queryClassName, String transferClassName, String daoClassName, String managerClassName, String managerImplClassName, Column primaryKeyColumn) {
        Setting setting = SettingManager.get();
        List<String> importList = getImportList(primaryKeyColumn,false,true);
        importList.add(PathHolder.impt(GenerateType.Manager,managerClassName));
        importList.add(PathHolder.impt(GenerateType.DAO,daoClassName));
        importList.add(PathHolder.impt(GenerateType.Query,queryClassName));
        Map<String,Object> map = initMap();
        map.put("className", managerImplClassName);
        map.put("doClassName", doClassName);
        map.put("boClassName", boClassName);
        map.put("managerClassName", managerClassName);
        map.put("managerPropertyName", NameUtil.lowFirst(managerClassName));
        map.put("managerUseBO", setting.isManagerUseBO());
        if(setting.isManagerUseBO()){
            map.put("objClassName",boClassName);
            map.put("objPropertyName", NameUtil.lowFirst(boClassName));
            importList.add(PathHolder.impt(GenerateType.BO, boClassName));
            importList.add(PathHolder.impt(GenerateType.Transfer, transferClassName));
        }else{
            map.put("objClassName",doClassName);
            map.put("objPropertyName", NameUtil.lowFirst(doClassName));
            importList.add(PathHolder.impt(GenerateType.DO, doClassName));
        }
        map.put("queryClassName", queryClassName);
        map.put("queryPropertyName", NameUtil.lowFirst(queryClassName));
        map.put("transferClassName", transferClassName);
        map.put("daoClassName", daoClassName);
        map.put("daoPropertyName", NameUtil.lowFirst(daoClassName));
        try{
            map.put("primaryKeyName", primaryKeyColumn.getProperty());
            map.put("primaryKeyType", primaryKeyColumn.getType());
        }catch (Exception e){
            map.put("primaryKeyName", "id");
            map.put("primaryKeyType", "Long");
        }

        map.put("importList", importList);
        return generate("tpl/"+getCode().getTplPath()+"/managerimpl.ftl", map);
    }

    @Override
    public Code getCode() {
        return Code.JAVA;
    }

    @Override
    public GenerateType generateType() {
        return GenerateType.ManagerImpl;
    }
}
