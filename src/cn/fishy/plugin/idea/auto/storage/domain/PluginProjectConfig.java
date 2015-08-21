package cn.fishy.plugin.idea.auto.storage.domain;

import java.io.Serializable;

/**
 * User: duxing
 * Date: 2014.9.17
 */

public class PluginProjectConfig implements Serializable {
    private static final long serialVersionUID = -4385855355672890609L;

    public String code = "JAVA";
    public String encoding = "UTF-8";
    public String path;
    public boolean daoLogicDelete = true;
    public boolean managerUseBO = true;
    public boolean daoUseSequence = true;
    public boolean pagerQuery = true;
    public boolean overwrite = false;
    public Boolean generateBase = false;
}
