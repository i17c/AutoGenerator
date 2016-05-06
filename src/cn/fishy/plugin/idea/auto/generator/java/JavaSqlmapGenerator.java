package cn.fishy.plugin.idea.auto.generator.java;

import cn.fishy.plugin.idea.auto.constant.GenerateType;
import cn.fishy.plugin.idea.auto.domain.Code;
import cn.fishy.plugin.idea.auto.domain.Column;
import cn.fishy.plugin.idea.auto.domain.Setting;
import cn.fishy.plugin.idea.auto.generator.BaseGenerator;
import cn.fishy.plugin.idea.auto.generator.SqlmapGenerator;
import cn.fishy.plugin.idea.auto.storage.Env;
import cn.fishy.plugin.idea.auto.storage.SettingManager;

import java.util.List;
import java.util.Map;

/**
 * User: duxing
 * Date: 2015.08.13 1:34
 */
public class JavaSqlmapGenerator extends BaseGenerator implements SqlmapGenerator {

    @Override
    public String generate(String tableName, Column primaryKeyColumn, String doClassName, String daoClassName, List<Column> columnList, List<Column> columnQueryList) {
        Map<String,Object> map = initMap();
        map.put("tableName",tableName);
        map.put("primaryKeyColumn",primaryKeyColumn);
        map.put("doClassName",doClassName);
        map.put("daoClassName",daoClassName);
        map.put("haveGmtModified",haveKey(columnList, "GMT_MODIFIED"));
        map.put("haveIsDeleted",haveKey(columnList,"IS_DELETED"));
        map.put("haveDeleted",haveKey(columnList, "DELETED"));
        map.put("deleteKey",deleteKey(columnList));
        map.put("columnList",columnList);
        map.put("columnQueryList", columnQueryList);
        map.put("encoding", Env.encodeTo.name());
        Setting setting = SettingManager.get();
        map.put("daoLogDelete", setting.isDaoLogicDelete());
        map.put("daoUseSequence", setting.isDaoUseSequence());
        map.put("pagerQuery", setting.isPagerQuery());
        return generate(map);
    }

    private boolean haveKey(List<Column> columnList,String name) {
        if(columnList!=null && columnList.size()>0) {
            for (Column c : columnList) {
                if(c.getName().equals(name)){
                    return true;
                }
            }
        }
        return false;
    }

    private String deleteKey(List<Column> columnList) {
        if(columnList!=null && columnList.size()>0) {
            for (Column c : columnList) {
                if(deleteKeyList.contains(c.getName())){
                    return c.getName();
                }
            }
        }
        return "{DELETE_KEY}";
    }

    @Override
    public Code getCode() {
        return Code.JAVA;
    }

    @Override
    public GenerateType generateType() {
        return GenerateType.SQLMap;
    }
}
