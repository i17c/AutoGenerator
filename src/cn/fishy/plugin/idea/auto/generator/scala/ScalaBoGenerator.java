package cn.fishy.plugin.idea.auto.generator.scala;

import cn.fishy.plugin.idea.auto.constant.GenerateType;

/**
 * User: duxing
 * Date: 2015.08.13 1:32
 */
public class ScalaBoGenerator extends ScalaDoGenerator{
    @Override
    public GenerateType generateType() {
        return GenerateType.BO;
    }
}
