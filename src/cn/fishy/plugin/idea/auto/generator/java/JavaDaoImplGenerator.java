package cn.fishy.plugin.idea.auto.generator.java;

import cn.fishy.plugin.idea.auto.constant.GenerateType;
import cn.fishy.plugin.idea.auto.domain.Code;
import cn.fishy.plugin.idea.auto.domain.Column;
import cn.fishy.plugin.idea.auto.domain.Setting;
import cn.fishy.plugin.idea.auto.generator.BaseGenerator;
import cn.fishy.plugin.idea.auto.generator.DaoImplGenerator;
import cn.fishy.plugin.idea.auto.storage.SettingManager;
import cn.fishy.plugin.idea.auto.util.NameUtil;
import cn.fishy.plugin.idea.auto.util.PathHolder;

import java.util.List;
import java.util.Map;

/**
 * User: duxing
 * Date: 2015.08.13 1:31
 */
public class JavaDaoImplGenerator extends BaseGenerator implements DaoImplGenerator {
    @Override
    public String generate(String doClassName, String queryClassName, String daoClassName, String daoImplClassName, Column primaryKeyColumn, String tableName) {
        Map<String,Object> map = initMap();
        map.put("className",daoImplClassName);
        map.put("doClassName",doClassName);
        map.put("queryClassName", queryClassName);
        map.put("daoClassName", daoClassName);
        map.put("daoImplClassName", daoImplClassName);
        map.put("doPropertyName", NameUtil.lowFirst(doClassName));
        map.put("queryPropertyName", NameUtil.lowFirst(queryClassName));
//        map.put("primaryKeyColumn", primaryKeyColumn);
        try{
            map.put("primaryKeyName", primaryKeyColumn.getProperty());
            map.put("primaryKeyNameAtMethod", NameUtil.upFirst(primaryKeyColumn.getProperty()));
            map.put("primaryKeyType", primaryKeyColumn.getType());
        }catch (Exception e){
            map.put("primaryKeyName", "id");
            map.put("primaryKeyType", "Long");
        }
        map.put("tableName", tableName);
        List<String> importList = getImportList(primaryKeyColumn, false, true);
        importList.add(PathHolder.impt(GenerateType.DO, doClassName));
        importList.add(PathHolder.impt(GenerateType.DAO, daoClassName));
        importList.add(PathHolder.impt(GenerateType.Query, queryClassName));
        importList.add(PathHolder.impt(GenerateType.BaseDAO, GenerateType.BaseDAO.getName()));
        map.put("importList", importList);
        Setting setting = SettingManager.get();
        map.put("daoUseSequence", setting.isDaoUseSequence());
        map.put("daoLogicDelete", setting.isDaoLogicDelete());
        return generate(map);
    }

    @Override
    public Code getCode() {
        return Code.JAVA;
    }

    @Override
    public GenerateType generateType() {
        return GenerateType.DAOImpl;
    }
}
