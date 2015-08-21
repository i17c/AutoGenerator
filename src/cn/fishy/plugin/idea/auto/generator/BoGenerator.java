package cn.fishy.plugin.idea.auto.generator;

import cn.fishy.plugin.idea.auto.domain.Column;

import java.util.List;

/**
 * User: duxing
 * Date: 2015.08.13 1:36
 */
public interface BoGenerator {
    public String generate(String boClassName, List<Column> columnList);
}
