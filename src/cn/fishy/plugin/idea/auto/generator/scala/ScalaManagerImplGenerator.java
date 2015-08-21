package cn.fishy.plugin.idea.auto.generator.scala;

import cn.fishy.plugin.idea.auto.domain.Code;
import cn.fishy.plugin.idea.auto.generator.java.JavaManagerImplGenerator;

/**
 * User: duxing
 * Date: 2015.08.13 1:35
 */
public class ScalaManagerImplGenerator extends JavaManagerImplGenerator {
    @Override
    public Code getCode() {
        return Code.SCALA;
    }

}
