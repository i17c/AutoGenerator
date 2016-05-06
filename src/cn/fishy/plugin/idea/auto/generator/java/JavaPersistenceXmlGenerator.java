package cn.fishy.plugin.idea.auto.generator.java;

import cn.fishy.plugin.idea.auto.constant.GenerateType;
import cn.fishy.plugin.idea.auto.domain.Code;
import cn.fishy.plugin.idea.auto.domain.Setting;
import cn.fishy.plugin.idea.auto.generator.BaseGenerator;
import cn.fishy.plugin.idea.auto.generator.PersistenceXmlGenerator;
import cn.fishy.plugin.idea.auto.storage.SettingManager;
import cn.fishy.plugin.idea.auto.util.NameUtil;

import java.util.Map;

/**
 * User: duxing
 * Date: 2015-08-17 00:39
 */
public class JavaPersistenceXmlGenerator extends BaseGenerator implements PersistenceXmlGenerator {

    @Override
    public Code getCode() {
        return Code.JAVA;
    }

    @Override
    public GenerateType generateType() {
        return GenerateType.PersistenceXml;
    }

    @Override
    public String generate(String tableName) {
        Map<String,Object> map = initMap();
        map.put("tableName",tableName);
        map.put("tablePropertyName", NameUtil.propertyName(tableName));
        Setting setting = SettingManager.get();
        map.put("encoding", setting.getEncoding());
        return generate(map);
    }
}
