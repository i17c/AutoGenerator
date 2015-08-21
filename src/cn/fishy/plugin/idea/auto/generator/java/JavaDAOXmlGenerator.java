package cn.fishy.plugin.idea.auto.generator.java;

import cn.fishy.plugin.idea.auto.constant.GenerateType;
import cn.fishy.plugin.idea.auto.domain.Code;
import cn.fishy.plugin.idea.auto.domain.Setting;
import cn.fishy.plugin.idea.auto.generator.BaseGenerator;
import cn.fishy.plugin.idea.auto.generator.DAOXmlGenerator;
import cn.fishy.plugin.idea.auto.storage.SettingManager;
import cn.fishy.plugin.idea.auto.util.NameUtil;

import java.util.Map;

/**
 * User: duxing
 * Date: 2015-08-17 00:39
 */
public class JavaDAOXmlGenerator extends BaseGenerator implements DAOXmlGenerator {

    @Override
    public Code getCode() {
        return Code.JAVA;
    }

    @Override
    public GenerateType generateType() {
        return GenerateType.DAOXml;
    }

    @Override
    public String generate(String daoClassName, String daoImplClassName) {
        Map<String,Object> map = initMap();
        map.put("daoClassName",daoClassName);
        map.put("daoPropertyName", NameUtil.lowFirst(daoClassName));
        map.put("daoImplClassName",daoImplClassName);
        Setting setting = SettingManager.get();
        map.put("encoding", setting.getEncoding());
        return generate("tpl/java/daoxml.ftl", map);
    }
}
