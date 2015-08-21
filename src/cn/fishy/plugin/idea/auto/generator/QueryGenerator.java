package cn.fishy.plugin.idea.auto.generator;

import cn.fishy.plugin.idea.auto.domain.Column;

import java.util.List;

/**
 * User: duxing
 * Date: 2015.08.13 23:59
 */
public interface QueryGenerator {
    public String generate(String queryClassName, List<Column> columnQueryList);
}
