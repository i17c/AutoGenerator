package cn.fishy.plugin.idea.auto.generator.java;

import cn.fishy.plugin.idea.auto.constant.GenerateType;

/**
 * User: duxing
 * Date: 2015.08.13 1:32
 */
public class JavaBoGenerator extends JavaDoGenerator {

    @Override
    public GenerateType generateType() {
        return GenerateType.BO;
    }
}
