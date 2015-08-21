package cn.fishy.plugin.idea.auto.generator;

import cn.fishy.plugin.idea.auto.domain.Column;

import java.util.List;

/**
 * User: duxing
 * Date: 2015.08.13 23:59
 */
public interface DoGenerator {
    public String generate(String className,List<Column> columnList);
}
