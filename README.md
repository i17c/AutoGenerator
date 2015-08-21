# AutoGenerator


### 插件说明

It's a tool for auto generate Data Persistence Layer by Create Table SQL.

What is "Data Persistence Layer", just contain DO, Query, DAO, Manager, And sqlmap ...

I designed it just for java and scala . other languages maybe later ...

The generate code will use ibatis and <a href="https://github.com/alibaba/tb_tddl" target="_blank">tddl</a> to access database.

If you don't like, change it your self. of course, you also can send other framework to me .

This tool will appeared at "Tools" menu and "Go To" menu,  shortcut key is: ctrl+shift+\

本工具根据建表语句自动创建数据持久层. 

数据持久层包含DO, Query, DAO, Manager, sqlmap 等 

本工具只设计为生成java和scala代码, 也许以后也会有更多的代码支持 

生成的代码默认使用了ibatis和 tddl, 如果你不喜欢, 请自行生成后替换. 

当然, 也可以反馈给我, 其他更好的建议. 

本工具将在"Tools"菜单中和"Go To"菜单中会出现, 快捷键: ctrl+shift+\ 

### 插件功能

插件实现的功能是可以生成 DO, DAO, DAOImpl, Query, Manager, ManagerImpl, BO, Transfer, sqlMap等等... 

而你需要做的就是快捷键ctrl+alt+\ (mac可能为mac+alt+\) 唤起插件后 填入sql -> analy -> 选择正确的输出目录 -> 生成

![](https://plugins.jetbrains.com/files/7918/screenshot_15213.png)
![](https://plugins.jetbrains.com/files/7918/screenshot_15214.png)

其中, 在输出目录选择的时候, 请选择要生成代码的biz目录.  生成的路径对应关系如下
![](https://plugins.jetbrains.com/files/7918/screenshot_15218.png)

| 文件 | 生成目录 |
| ---- | ---- |
| DO | ${outpath}/dal/dataobject/ | 
| Query | ${outpath}/dal/query/ | 
| DAO | ${outpath}/dal/dao/ | 
| DAOImpl | ${outpath}/dal/dao/impl/ | 
| Manager| ${outpath}/manager/ | 
| Manager| ${outpath}/manager/impl/ | 
| BO| ${outpath}/bo/ | 
| Transfer | ${outpath}/transfer/ | 
| BaseDAO| ${outpath}/dal/dao/ | 
| BaseQuery| ${outpath}/dal/query/ | 
| sqlmap | ${resource_path}/dal/sqlmap/ |
| dao-xml-sample | ${resource_path}/dal/ |
| sqlmap-config-xml-sample | ${resource_path}/dal/ |
| persistence-sample |  ${resource_path}/dal/ |

其中resource_path为自动分析outpath分析得到, 如果生成的不对, 还请见谅.

其中对于do bo query对象的生成上 相应的复选框可以选择要关注的字段

![](https://plugins.jetbrains.com/files/7918/screenshot_15219.png)
![](https://plugins.jetbrains.com/files/7918/screenshot_15221.png)

在这个之前, 你可以在setting标签页设置输出代码的author
![](https://plugins.jetbrains.com/files/7918/screenshot_15222.png)

插件安装方式可以下载后以本地方式安装 ( settings -> plugins -> Browse repositories... ), 或者去idea的插件库下载 ( settings -> plugins -> Browse repositories...) 

插件安装后, 你可以在两个地方找到插件

1. Tools菜单栏

2. 右键Go To 菜单

具体screenshot可以查看源码中的相应目录.

插件有任何问题请提bug给我. 麻烦啦 [https://github.com/i17c/AutoGenerator/issues](https://github.com/i17c/AutoGenerator/issues)


相关链接: [Webx开发IDEA插件 - Webx Link (Webx框架下开发java和vm模板快速切换)](https://github.com/i17c/WebxLink)


php lite-sample version : [auto](https://github.com/i17c/auto)
