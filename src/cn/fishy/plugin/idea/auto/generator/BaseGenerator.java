package cn.fishy.plugin.idea.auto.generator;

import cn.fishy.plugin.idea.auto.constant.GenerateType;
import cn.fishy.plugin.idea.auto.constant.ImportMapHolder;
import cn.fishy.plugin.idea.auto.domain.Code;
import cn.fishy.plugin.idea.auto.domain.Column;
import cn.fishy.plugin.idea.auto.domain.Setting;
import cn.fishy.plugin.idea.auto.storage.SettingManager;
import cn.fishy.plugin.idea.auto.util.PathHolder;
import cn.fishy.plugin.idea.auto.util.TemplateUtil;
import org.apache.commons.httpclient.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: duxing
 * Date: 2015-08-15 16:19
 */
public abstract class BaseGenerator {

    public static List<String> deleteKeyList = new ArrayList<String>();
    static{
        deleteKeyList.add("IS_DELETE");
        deleteKeyList.add("IS_DELETED");
        deleteKeyList.add("DELETED");
    }

    public String generate(String tpl, Map<String, Object> map) {
        return TemplateUtil.parse(TemplateUtil.getTemplate(tpl), map);
    }
    
    public abstract Code getCode();

    public abstract GenerateType generateType();

    public List<String> getImportList(List<Column> columnList, boolean objectClass) {
        return getImportList(columnList,objectClass,false);
    }

    public List<String> getImportList(List<Column> columnList, boolean objectClass, boolean addList) {
        List<String> importList = new ArrayList<String>();
        if(objectClass){
            importList.add("java.io.Serializable");
        }
        if(columnList!=null && columnList.size()>0) {
            for (Column c : columnList) {
                if(c!=null) {
                    String i = ImportMapHolder.getImport(c.getType(), getCode());
                    if (i != null && !importList.contains(i)) {
                        importList.add(i);
                    }
                }
            }
        }
        if(addList){
            if (Code.JAVA.equals(getCode())){
                importList.add("java.util.List");
                if(GenerateType.Transfer.equals(generateType())){
                    importList.add("java.util.ArrayList");
                }
            }else{
                importList.add("java.util");
            }
        }
        return importList;
    }

    public List<String> getImportList(Column column, boolean objectClass) {
        return getImportList(column,objectClass,false);
    }

    public List<String> getImportList(Column column, boolean objectClass, boolean addList) {
        List<Column> l = new ArrayList<Column>();
        l.add(column);
        return getImportList(l,objectClass,addList);
    }

    public Map<String,Object> initMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Setting setting = SettingManager.get();
        map.put("user",setting.getAuthor());
        map.put("autoName","auto"+ generateType().getName());
        map.put("today", DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        map.put("package", PathHolder.pkg(generateType()));
        return map;
    }
}
