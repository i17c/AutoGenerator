package cn.fishy.plugin.idea.auto.util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.Map;

/**
 * User: duxing
 * Date: 2015.08.15 1:50
 */
public class TemplateUtil {

    public static String encoding = "UTF-8";

    public static Template getTemplate(String templatePath) {
        try {
            Configuration cfg = new Configuration();
            cfg.setClassForTemplateLoading(TemplateUtil.class, "/");
            return cfg.getTemplate(templatePath,cfg.getLocale(), encoding, true);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String parse(Template template, Map data) {
        try {
            StringWriter sw = new StringWriter();
            template.process(data, sw);
            return sw.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
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
