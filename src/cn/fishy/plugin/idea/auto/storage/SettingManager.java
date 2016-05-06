package cn.fishy.plugin.idea.auto.storage;

import cn.fishy.plugin.idea.auto.domain.Code;
import cn.fishy.plugin.idea.auto.domain.Encoding;
import cn.fishy.plugin.idea.auto.domain.Language;
import cn.fishy.plugin.idea.auto.domain.Setting;
import cn.fishy.plugin.idea.auto.storage.domain.PluginConfig;
import cn.fishy.plugin.idea.auto.storage.domain.PluginProjectConfig;

/**
 * User: duxing
 * Date: 2015.08.12 1:31
 */
public class SettingManager {
    public static Setting get(){
        PluginConfig pluginConfig = PluginConfigHolder.getPluginConfig();
        PluginProjectConfig pluginProjectConfig = PluginProjectConfigHolder.getPluginProjectConfig();
        Setting setting = new Setting();
        if(pluginConfig!=null){
            setting.setAuthor(pluginConfig.author);
            setting.setLanguage(pluginConfig.language);
            setting.setCode(pluginConfig.code);
            setting.setEncoding(pluginConfig.encoding);
            setting.setTplUseCustom(pluginConfig.tplUseCustom);
            setting.setTplPath(pluginConfig.tplPathCustom);
        }else {
            setting.setAuthor("duxing //change at Setting Tab");
            setting.setLanguage(Language.EN.getName());
            setting.setCode(Code.JAVA.getName());
            setting.setEncoding(Encoding.UTF8.getName());
        }
        if(pluginProjectConfig!=null){
            setting.setCode(pluginProjectConfig.code);
            setting.setEncoding(pluginProjectConfig.encoding);
            setting.setPath(pluginProjectConfig.path);
            setting.setDaoLogicDelete(pluginProjectConfig.daoLogicDelete);
            setting.setDaoUseSequence(pluginProjectConfig.daoUseSequence);
            setting.setManagerUseBO(pluginProjectConfig.managerUseBO);
            setting.setPagerQuery(pluginProjectConfig.pagerQuery);
            setting.setOverwrite(pluginProjectConfig.overwrite);
            setting.setGenerateBase(pluginProjectConfig.generateBase);
        }
        return setting;
    }

    public static void applyApp(Setting setting){
        PluginConfig pluginConfig = PluginConfigHolder.getPluginConfig();
        if(pluginConfig!=null){
            pluginConfig.author = setting.getAuthor();
            pluginConfig.language = setting.getLanguage();
            pluginConfig.encoding = setting.getEncoding();
            pluginConfig.code = setting.getCode();
            pluginConfig.tplPathCustom = setting.getTplPath();
            pluginConfig.tplUseCustom = setting.getTplUseCustom();
        }
    }

    public static void applyPrj(Setting setting){
        PluginProjectConfig pluginProjectConfig = PluginProjectConfigHolder.getPluginProjectConfig();
        if(pluginProjectConfig!=null){
            if(setting.getEncoding()!=null){pluginProjectConfig.encoding = setting.getEncoding();}
            if(setting.getCode()!=null){pluginProjectConfig.code = setting.getCode();}
            if(setting.getPath()!=null){pluginProjectConfig.path = setting.getPath();}
            if(setting.isManagerUseBO()!=null){pluginProjectConfig.managerUseBO = setting.isManagerUseBO();}
            if(setting.isDaoLogicDelete()!=null){pluginProjectConfig.daoLogicDelete = setting.isDaoLogicDelete();}
            if(setting.isDaoUseSequence()!=null){pluginProjectConfig.daoUseSequence = setting.isDaoUseSequence();}
            if(setting.isPagerQuery()!=null){pluginProjectConfig.pagerQuery = setting.isPagerQuery();}
            if(setting.isOverwrite()!=null){pluginProjectConfig.overwrite = setting.isOverwrite();}
            if(setting.isGenerateBase()!=null){pluginProjectConfig.generateBase = setting.isGenerateBase();}
        }
    }

    public static void applySwitch(Boolean managerUseBO,Boolean daoLogicDelete,Boolean daoUseSequence,Boolean pagerQuery,Boolean overwrite,Boolean generateBase) {
        PluginProjectConfig pluginProjectConfig = PluginProjectConfigHolder.getPluginProjectConfig();
        if(pluginProjectConfig!=null){
            if(managerUseBO!=null){pluginProjectConfig.managerUseBO = managerUseBO;}
            if(daoLogicDelete!=null){pluginProjectConfig.daoLogicDelete = daoLogicDelete;}
            if(daoUseSequence!=null){pluginProjectConfig.daoUseSequence = daoUseSequence;}
            if(pagerQuery!=null){pluginProjectConfig.pagerQuery = pagerQuery;}
            if(overwrite!=null){pluginProjectConfig.overwrite = overwrite;}
            if(generateBase!=null){pluginProjectConfig.generateBase = generateBase;}
        }
    }

    public static void applyPrjPath(String path){
        PluginProjectConfig pluginProjectConfig = PluginProjectConfigHolder.getPluginProjectConfig();
        if(pluginProjectConfig!=null){
            pluginProjectConfig.path = path;
        }
    }

    public static void applyPrjCode(String code){
        PluginProjectConfig pluginProjectConfig = PluginProjectConfigHolder.getPluginProjectConfig();
        if(pluginProjectConfig!=null){
            pluginProjectConfig.code = code;
        }
    }
    public static void applyPrjEncoding(String encoding){
        PluginProjectConfig pluginProjectConfig = PluginProjectConfigHolder.getPluginProjectConfig();
        if(pluginProjectConfig!=null){
            pluginProjectConfig.encoding = encoding;

        }
    }

}
