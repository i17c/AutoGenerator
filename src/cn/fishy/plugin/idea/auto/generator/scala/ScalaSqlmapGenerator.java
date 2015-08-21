package cn.fishy.plugin.idea.auto.generator.scala;

import cn.fishy.plugin.idea.auto.domain.Code;
import cn.fishy.plugin.idea.auto.generator.java.JavaSqlmapGenerator;

/**
 * User: duxing
 * Date: 2015.08.13 1:34
 */
public class ScalaSqlmapGenerator extends JavaSqlmapGenerator {
    @Override
    public Code getCode() {
        return Code.SCALA;
    }

}
