<?xml version="1.0" encoding="${encoding}" standalone="no"?>
<!--  Generate by ${autoName} Powered by duxing@Taobao -->
<!DOCTYPE sqlMapConfig PUBLIC "-//iBATIS.com//DTD SQL Map Config 2.0//EN"
        "http://www.ibatis.com/dtd/sql-map-config-2.dtd">
<sqlMapConfig>
    <settings cacheModelsEnabled="false" enhancementEnabled="false" lazyLoadingEnabled="false" maxRequests="3000"
              maxSessions="3000" maxTransactions="3000" useStatementNamespaces="true"/>

    <sqlMap resource="dal/sqlmap/${tableName}_sqlmap.xml"/>

</sqlMapConfig>
