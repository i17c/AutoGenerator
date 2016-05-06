package cn.fishy.plugin.idea.auto.domain;

/**
 * User: duxing
 * Date: 2015.08.12 2:28
 */
public class Setting {
    //作者
    private String author;
    //语言
    private String language;
    //编码
    private String encoding;
    //输出代码类型
    private String code;
    //输出路径
    private String path;
    /**
     * 模板路径
     */
    private String tplPath;
    /**
     * 是否使用自定义模板
     */
    private Boolean tplUseCustom;

    public Boolean daoLogicDelete;
    public Boolean managerUseBO;
    public Boolean daoUseSequence;
    public Boolean pagerQuery;
    public Boolean overwrite;
    public Boolean generateBase;

    public Setting() {
    }

    public Setting(String author, Encoding encoding, Code code) {
        this.author = author;
        this.encoding = encoding.getName();
        this.code = code.getName();
    }

    public Setting(Encoding encoding, Code code, String path) {
        this.encoding = encoding.getName();
        this.code = code.getName();
        this.path = path;
    }

    public void setSwitch(Boolean managerUseBO, Boolean daoLogicDelete, Boolean daoUseSequence, Boolean pagerQuery, Boolean overwrite, Boolean generateBase) {
        this.managerUseBO = managerUseBO;
        this.daoLogicDelete = daoLogicDelete;
        this.daoUseSequence = daoUseSequence;
        this.pagerQuery = pagerQuery;
        this.overwrite = overwrite;
        this.generateBase = generateBase;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean isDaoLogicDelete() {
        return daoLogicDelete;
    }

    public void setDaoLogicDelete(Boolean daoLogicDelete) {
        this.daoLogicDelete = daoLogicDelete;
    }

    public Boolean isManagerUseBO() {
        return managerUseBO;
    }

    public void setManagerUseBO(Boolean managerUseBO) {
        this.managerUseBO = managerUseBO;
    }

    public Boolean isDaoUseSequence() {
        return daoUseSequence;
    }

    public void setDaoUseSequence(Boolean daoUseSequence) {
        this.daoUseSequence = daoUseSequence;
    }

    public Boolean isPagerQuery() {
        return pagerQuery;
    }

    public void setPagerQuery(Boolean pagerQuery) {
        this.pagerQuery = pagerQuery;
    }

    public Boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(Boolean overwrite) {
        this.overwrite = overwrite;
    }

    public Boolean isGenerateBase() {
        return generateBase;
    }

    public void setGenerateBase(Boolean generateBase) {
        this.generateBase = generateBase;
    }

    public String getTplPath() {
        return tplPath;
    }

    public void setTplPath(String tplPath) {
        this.tplPath = tplPath;
    }

    public Boolean getTplUseCustom() {
        return tplUseCustom;
    }

    public void setTplUseCustom(Boolean tplUseCustom) {
        this.tplUseCustom = tplUseCustom;
    }
}
