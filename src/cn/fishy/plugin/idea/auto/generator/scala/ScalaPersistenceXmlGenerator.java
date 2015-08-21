package cn.fishy.plugin.idea.auto.generator.scala;

import cn.fishy.plugin.idea.auto.domain.Code;
import cn.fishy.plugin.idea.auto.generator.java.JavaPersistenceXmlGenerator;


/**
 * User: duxing
 * Date: 2015-08-17 00:38
 */
public class ScalaPersistenceXmlGenerator extends JavaPersistenceXmlGenerator {

    @Override
    public Code getCode() {
        return Code.SCALA;
    }

}
