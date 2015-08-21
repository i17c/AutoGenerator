package cn.fishy.plugin.idea.auto.generator;

import cn.fishy.plugin.idea.auto.domain.Column;

import java.util.List;

/**
 * User: duxing
 * Date: 2015.08.13 1:37
 */
public interface ManagerImplGenerator {
    public String generate(String doClassName, String boClassName, String queryClassName, String transferClassName, String daoClassName, String managerClassName, String managerImplClassName, Column primaryKeyColumn);
}
