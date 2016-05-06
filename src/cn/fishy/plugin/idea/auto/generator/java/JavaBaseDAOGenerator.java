package cn.fishy.plugin.idea.auto.generator.java;

import cn.fishy.plugin.idea.auto.constant.GenerateType;
import cn.fishy.plugin.idea.auto.domain.Code;
import cn.fishy.plugin.idea.auto.generator.BaseGenerator;

import java.util.Map;

/**
 * User: duxing
 * Date: 2015.08.14 23:23
 */
public class JavaBaseDAOGenerator extends BaseGenerator {

    @Override
    public Code getCode() {
        return Code.JAVA;
    }

    @Override
    public GenerateType generateType() {
        return GenerateType.BaseDAO;
    }
}
