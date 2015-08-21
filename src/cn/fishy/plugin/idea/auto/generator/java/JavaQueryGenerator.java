package cn.fishy.plugin.idea.auto.generator.java;

import cn.fishy.plugin.idea.auto.constant.GenerateType;

/**
 * User: duxing
 * Date: 2015.08.13 1:32
 */
public class JavaQueryGenerator extends JavaDoGenerator {
    @Override
    public GenerateType generateType() {
        return GenerateType.Query;
    }
}
