package cn.fishy.plugin.idea.auto.generator.java;

import cn.fishy.plugin.idea.auto.constant.GenerateType;
import cn.fishy.plugin.idea.auto.domain.Code;
import cn.fishy.plugin.idea.auto.domain.Column;
import cn.fishy.plugin.idea.auto.domain.Setting;
import cn.fishy.plugin.idea.auto.generator.BaseGenerator;
import cn.fishy.plugin.idea.auto.generator.DaoGenerator;
import cn.fishy.plugin.idea.auto.storage.SettingManager;
import cn.fishy.plugin.idea.auto.util.NameUtil;
import cn.fishy.plugin.idea.auto.util.PathHolder;

import java.util.List;
import java.util.Map;

/**
 * User: duxing
 * Date: 2015.08.13 1:29
 */
public class JavaDaoGenerator extends BaseGenerator implements DaoGenerator {
    @Override
    public String generate(String doClassName, String queryClassName, String daoClassName, Column primaryKeyColumn) {
        Map<String,Object> map = initMap();
        map.put("objClassName",doClassName);
        map.put("objPropertyName", NameUtil.lowFirst(doClassName));
        map.put("className", daoClassName);
        map.put("queryClassName", queryClassName);
        map.put("queryPropertyName", NameUtil.lowFirst(queryClassName));
        try{
            map.put("primaryKeyName", primaryKeyColumn.getProperty());
            map.put("primaryKeyType", primaryKeyColumn.getType());
        }catch (Exception e){
            map.put("primaryKeyName", "id");
            map.put("primaryKeyType", "Long");
        }
        List<String> importList = getImportList(primaryKeyColumn, false, true);
        importList.add(PathHolder.impt(GenerateType.DO,doClassName));
        importList.add(PathHolder.impt(GenerateType.Query,queryClassName));
//        importList.add(PathHolder.impt(GenerateType.BaseDAO, GenerateType.BaseDAO.getName()));
        map.put("importList", importList);
        Setting setting = SettingManager.get();
        map.put("daoLogicDelete", setting.isDaoLogicDelete());
        return super.generate(map);
    }

    @Override
    public Code getCode() {
        return Code.JAVA;
    }

    @Override
    public GenerateType generateType() {
        return GenerateType.DAO;
    }
}
