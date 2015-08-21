package cn.fishy.plugin.idea.auto.generator.scala;

import cn.fishy.plugin.idea.auto.domain.Code;
import cn.fishy.plugin.idea.auto.generator.java.JavaBaseDAOGenerator;

/**
 * User: duxing
 * Date: 2015.08.14 23:25
 */
public class ScalaBaseDAOGenerator extends JavaBaseDAOGenerator {

    @Override
    public Code getCode() {
        return Code.SCALA;
    }

}
