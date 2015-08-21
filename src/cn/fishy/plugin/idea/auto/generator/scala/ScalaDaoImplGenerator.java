package cn.fishy.plugin.idea.auto.generator.scala;

import cn.fishy.plugin.idea.auto.domain.Code;
import cn.fishy.plugin.idea.auto.generator.java.JavaDaoImplGenerator;

/**
 * User: duxing
 * Date: 2015.08.13 1:31
 */
public class ScalaDaoImplGenerator extends JavaDaoImplGenerator {

    @Override
    public Code getCode() {
        return Code.SCALA;
    }

}
