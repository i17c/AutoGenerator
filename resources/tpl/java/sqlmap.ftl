<?xml version="1.0" encoding="${encoding}" ?>
<!--  Generate by ${autoName} Powered by duxing@Taobao -->
<!DOCTYPE sqlMap PUBLIC "-//ibatis.apache.org//DTD SQL Map 2.0//EN" "http://ibatis.apache.org/dtd/sql-map-2.dtd" >
<sqlMap namespace="${daoClassName}">

    <typeAlias alias="${doClassName}" type="${package}.${doClassName}"/>

    <resultMap id="result${doClassName}Map" class="${package}.${doClassName}">
        <#list columnList as column>
        <result property="${column.property}" column="${column.name}"/>
        </#list>
    </resultMap>

    <sql id="allCols">
    <#list columnList as column>
        <#if column_index==0>         <#else>        ,</#if>`${column.name}`
    </#list>
    </sql>

    <!--
           开发负责人：${user}
           功能说明：插入单条数据
           前台或后台：后台
           频率：每天1000以下
           表大小：10W
           分表数量：无
           是否有缓存：无
    -->
    <insert id="insert" parameterClass="${doClassName}">
        <![CDATA[
	    INSERT INTO `${tableName}`(
	    ]]>
        <include refid="allCols"/>
        <![CDATA[
        )VALUES(
<#list columnList as column>
        <#if column_index==0>         <#else>        ,</#if><#if column.name=="GMT_MODIFIED" || column.name=="GMT_CREATE"|| column.name=="GMT_CREATED">now()<#elseif column.name=="IS_DELETED" ||  column.name=="DELETED">0<#else>#${column.property}#</#if>
</#list>
        );
        ]]>
        <#if daoUseSequence>

        <#else>
        <selectKey resultClass="${primaryKeyColumn.type}" keyProperty="${primaryKeyColumn.property}" >
            SELECT @@IDENTITY AS ${primaryKeyColumn.name}
        </selectKey>
        </#if>
    </insert>

    <!--
           开发负责人：${user}
           功能说明：更新单条数据
           前台或后台：后台
           频率：每天1000以下
           表大小：10W
           分表数量：无
           是否有缓存：无
    -->
    <update id="update">
        <![CDATA[
        UPDATE `${tableName}` SET <#if haveGmtModified>`GMT_MODIFIED` = now()<#else>`${primaryKeyColumn.name}` = #${primaryKeyColumn.property}#;</#if>
        ]]>
        <dynamic>
<#list columnList as column>
    <#if column.name=="GMT_MODIFIED"||column.name=="GMT_CREATE"||column.name=="GMT_CREATED"||column.name==primaryKeyColumn.name>
    <#else>
            <isNotNull property="${column.property}" prepend=" , ">
                `${column.name}` = #${column.property}#
            </isNotNull>
    </#if>
</#list>
        </dynamic>
        <![CDATA[
        WHERE `${primaryKeyColumn.name}` = #${primaryKeyColumn.property}#;
    	]]>
    </update>

    <!--
           开发负责人：${user}
           功能说明：查询单条数据
           前台或后台：后台
           频率：每天1000以下
           表大小：10W
           分表数量：无
           是否有缓存：无
        -->
    <select id="select" resultMap="result${doClassName}Map">
        <![CDATA[
        SELECT
        ]]>
        <include refid="allCols"/>
        <![CDATA[
        FROM `${tableName}` WHERE `${primaryKeyColumn.name}` = #${primaryKeyColumn.property}#;
        ]]>
    </select>

    <!--
           开发负责人：${user}
           功能说明：查询多条数据
           前台或后台：后台
           频率：每天1000以下
           表大小：10W
           分表数量：无
           是否有缓存：无
           可能的条件组合:
           1.
    -->
    <select id="queryList" resultMap="result${doClassName}Map">
        <![CDATA[
        SELECT
        ]]>
        <include refid="allCols"/>
        <![CDATA[
        FROM `${tableName}` WHERE  <#if haveIsDeleted>`IS_DELETED` = 0<#elseif haveDeleted>`DELETED` = 0<#else>1</#if>
        ]]>
        <dynamic>
<#list columnQueryList as column>
    <#if column.name=="IS_DELETED"||column.name=="DELETED"||column.name=="GMT_MODIFIED"||column.name=="GMT_CREATE"||column.name=="GMT_CREATED"||column.name==primaryKeyColumn.name>
    <#else>
            <isNotNull property="${column.property}" prepend=" AND ">
                `${column.name}` = #${column.property}#
            </isNotNull>
    </#if>
</#list>
        </dynamic>
        <#if pagerQuery>
        <![CDATA[
        ORDER BY `${primaryKeyColumn.name}` DESC
        LIMIT #startRow#,#limit#;
        ]]>
        </#if>

    </select>

    <!--
           开发负责人：${user}
           功能说明：查询条数
           前台或后台：后台
           频率：每天1000以下
           表大小：10W
           分表数量：无
           是否有缓存：无
           可能的条件组合:
           1.
    -->
    <select id="queryCount" resultClass="java.lang.Integer">
        <![CDATA[
        SELECT
        COUNT(*)
        FROM `${tableName}` WHERE  1
        ]]>
        <dynamic>
<#list columnQueryList as column>
            <isNotNull property="${column.property}" prepend=" AND ">
                `${column.name}` = #${column.property}#
            </isNotNull>
</#list>
        </dynamic>
    </select>

    <!--
           开发负责人：${user}
           功能说明：物理删除单条数据
           前台或后台：后台
           频率：每天100以下
           表大小：10W
           分表数量：无
           是否有缓存：无
    -->
<#if daoLogDelete>
    <update id="delete">
        UPDATE `${tableName}` SET `${deleteKey}` = '1' WHERE `${primaryKeyColumn.name}` = #${primaryKeyColumn.property}#;
    </update>
<#else>
    <delete id="delete">
        DELETE FROM `${tableName}`  WHERE `${primaryKeyColumn.name}` = #${primaryKeyColumn.property}#;
    </delete>
</#if>

</sqlMap>
