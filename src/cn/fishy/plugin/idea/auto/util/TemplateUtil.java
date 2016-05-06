package cn.fishy.plugin.idea.auto.util;

import cn.fishy.plugin.idea.auto.constant.GenerateType;
import cn.fishy.plugin.idea.auto.domain.Code;
import cn.fishy.plugin.idea.auto.domain.TemplateConfig;
import cn.fishy.plugin.idea.auto.storage.PluginConfigHolder;
import cn.fishy.plugin.idea.auto.storage.domain.PluginConfig;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.io.FileUtil;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.jetbrains.annotations.Nullable;


import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * User: duxing
 * Date: 2015.08.15 1:50
 */
public class TemplateUtil {

    public static String encoding = "UTF-8";
    private static final Logger logger = Logger.getInstance(TemplateUtil.class);

    public static String parseByVm(String vmTplPath, Map<String,Object> contextMap) {
        PluginConfig pluginConfig = PluginConfigHolder.getPluginConfig();
        String tplContent = null;
        if(pluginConfig!=null && pluginConfig.tplUseCustom){
            try {
                tplContent =  FileUtil.loadFile(new File(vmTplPath),"UTF-8");
            } catch (IOException e) {
                logger.error("ERROR to get content:"+vmTplPath,e);
            }
        }
        if(tplContent==null && vmTplPath.startsWith(TemplateConfig.TEMPLATE_DIR)){
            try {
                tplContent = getOriginTplContent(vmTplPath);
            } catch (Exception e) {
                logger.error("ERROR to get origin content:"+vmTplPath,e);
            }
        }
        tplContent = parse(tplContent,contextMap);
        return tplContent;
    }

    @Nullable
    public static String getOriginTplContent(String vmTplPath) {
        return ResourceUtil.load(vmTplPath,encoding,TemplateUtil.class);
    }

    @Nullable
    public static String getOriginTplContent(Code code, GenerateType generateType) {
        return ResourceUtil.load(TemplateConfig.getOriginTemplate(code,generateType),encoding,TemplateUtil.class);
    }

    public static String parse(String tplContent, Map<String,Object> contextMap) {
        VelocityEngine velocityEngine = new VelocityEngine();
        VelocityContext context = new VelocityContext();
        for(String k : contextMap.keySet()){
            context.put(k,contextMap.get(k));
        }
        StringWriter sw = new StringWriter();
        try {
            velocityEngine.evaluate(context, sw, "", tplContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    public static void main(String[] args) throws Exception {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("className","test");
        map.put("pagerQuery",false);
        String str = parseByVm("templates/java/sqlmap.vm", map);
        System.out.println(str);
        /**
         *     private final static Template workspaceTpl;

         workspaceTpl = TemplateUtil.getTemplate("workspace.ftl");

         public static String generateContent(Workspace workspace) throws Exception {
         if (workspaceTpl == null || workspace == null) return null;
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("deployList", workspace.getDeploy());
         map.put("pluginList", workspace.getPlugin());
         Date date = new Date();
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
         map.put("dateTime", df.format(date));
         map.put("authors", PluginConfig.AUTHORS);
         return TemplateUtil.parse(workspaceTpl, map);
         }

         */
    }

}
