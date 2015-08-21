package cn.fishy.plugin.idea.auto.generator;

import cn.fishy.plugin.idea.auto.domain.Column;

/**
 * User: duxing
 * Date: 2015.08.13 1:36
 */
public interface ManagerGenerator {
    public String generate(String objClassName, String queryClassName, String managerClassName, Column primaryKeyColumn);
}
