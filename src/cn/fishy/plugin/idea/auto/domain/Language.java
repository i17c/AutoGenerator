package cn.fishy.plugin.idea.auto.domain;

/**
 * User: duxing
 * Date: 2015.08.12 2:09
 */
public enum Language {
    CHS("Chinese"),EN("English");
    private String name;

    Language(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
