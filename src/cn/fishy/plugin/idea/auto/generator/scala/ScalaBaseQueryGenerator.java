package cn.fishy.plugin.idea.auto.generator.scala;

import cn.fishy.plugin.idea.auto.domain.Code;
import cn.fishy.plugin.idea.auto.generator.java.JavaBaseQueryGenerator;

/**
 * User: duxing
 * Date: 2015.08.14 23:25
 */
public class ScalaBaseQueryGenerator extends JavaBaseQueryGenerator {
    @Override
    public Code getCode() {
        return Code.SCALA;
    }

}
