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
    public String sql = "INPUT SQL HERE JUST LIKE BELOW  \n\n ================================================ \n\nCREATE TABLE `app_model` ( `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id for number',  `app` varchar(20) NOT NULL COMMENT 'application name',   `model` varchar(20) NOT NULL COMMENT 'application model',   PRIMARY KEY (`id`),    KEY `app` (`app`,`model`) ) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='application model table' AUTO_INCREMENT=2;\n" +
            "\n" +
            " ================================================ \n" +
            "\n";
}
