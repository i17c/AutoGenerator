package cn.fishy.plugin.idea.auto.storage.domain;

import java.io.Serializable;

/**
 * User: duxing
 * Date: 2015.8.11
 */

public class PluginConfig implements Serializable {
    private static final long serialVersionUID = -2408231700721356773L;

    public String author = "duxing //change at Setting Tab";
    public String language = "english";
    public String code = "JAVA";
    public String encoding = "UTF-8";

    //扩展模板管理内容
    /**
     * 是否使用自定义的模板
     */
    public Boolean tplUseCustom = false;
    /**
     * 模板自定义目录
     */
    public String tplPathCustom = "";
}
