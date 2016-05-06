package cn.fishy.plugin.idea.auto.domain;

import cn.fishy.plugin.idea.auto.constant.GenerateType;
import cn.fishy.plugin.idea.auto.storage.PluginConfigHolder;
import cn.fishy.plugin.idea.auto.storage.domain.PluginConfig;
import com.intellij.notification.EventLog;
import com.intellij.openapi.diagnostic.Logger;

import java.io.File;

/**
 * User: duxing
 * Date: 2016-05-04 16:42
 */
public class TemplateConfig {

    private static final Logger logger = Logger.getInstance(TemplateConfig.class);

    public static final String TEMPLATE_EXT = ".vm";
    public static String TEMPLATE_DIR = "templates/";

    public static String getTemplatePath() {
        PluginConfig pluginConfig = PluginConfigHolder.getPluginConfig();
        if(pluginConfig!=null){
            if(pluginConfig.tplUseCustom && pluginConfig.tplPathCustom!=null && !pluginConfig.tplPathCustom.equals("")){
                File path = new File(pluginConfig.tplPathCustom);
                if(path.exists()){
                    if(!pluginConfig.tplPathCustom.endsWith("/"))pluginConfig.tplPathCustom+="/";
                    return pluginConfig.tplPathCustom;
                }else{
                    logger.error("path: "+ pluginConfig.tplPathCustom +" is not exist!");
                }
            }
        }
        return TEMPLATE_DIR;
    }

    public static String getTemplate(String tpl) {
        return getTemplatePath() + tpl + TEMPLATE_EXT;
    }

    public static String getTemplate(Code code, GenerateType generateType) {
        return getTemplatePath() + code.getTplPath() + generateType.getName().toLowerCase() + TEMPLATE_EXT;
    }

    public static String getOriginTemplate(Code code, GenerateType generateType) {
        return TEMPLATE_DIR + code.getTplPath() + generateType.getName().toLowerCase() + TEMPLATE_EXT;
    }
}
