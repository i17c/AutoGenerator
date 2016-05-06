package cn.fishy.plugin.idea.auto.ui;

import cn.fishy.plugin.idea.auto.constant.ColumnEnum;
import cn.fishy.plugin.idea.auto.constant.GenerateType;
import cn.fishy.plugin.idea.auto.constant.TypePath;
import cn.fishy.plugin.idea.auto.domain.Code;
import cn.fishy.plugin.idea.auto.domain.Column;
import cn.fishy.plugin.idea.auto.domain.Encoding;
import cn.fishy.plugin.idea.auto.domain.Language;
import cn.fishy.plugin.idea.auto.domain.Setting;
import cn.fishy.plugin.idea.auto.domain.SqlInfo;
import cn.fishy.plugin.idea.auto.domain.TemplateConfig;
import cn.fishy.plugin.idea.auto.generator.GeneratorAdaptor;
import cn.fishy.plugin.idea.auto.storage.Env;
import cn.fishy.plugin.idea.auto.storage.PluginProjectConfigHolder;
import cn.fishy.plugin.idea.auto.storage.SettingManager;
import cn.fishy.plugin.idea.auto.storage.domain.PluginProjectConfig;
import cn.fishy.plugin.idea.auto.util.FileWriter;
import cn.fishy.plugin.idea.auto.util.SqlAnaly;
import cn.fishy.plugin.idea.auto.util.TemplateUtil;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.table.JBTable;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static cn.fishy.plugin.idea.auto.constant.GenerateType.ALL;
import static cn.fishy.plugin.idea.auto.constant.GenerateType.get;

public class GeneratorMainDialog extends JDialog {
    private static final long serialVersionUID = -930983678109055869L;
    /**
     * overall
     */
    private JPanel contentPane;
//    private JButton BTN_exit;
    private JTextPane TEXT_msg;


    /**
     * input
     */
    private JTabbedPane TAB_PANEL;
    private JPanel PN_input;
    private JTextArea INPUT_sql;
    private JButton BTN_analy;

    /**
     * generator
     */
    private JPanel PN_generator;
    private JButton BTN_query;
    private JButton BTN_dao;
    private JButton BTN_daoImpl;
    private JButton BTN_bo;
    private JButton BTN_transfer;
    private JButton BTN_manager;
    private JButton BTN_managerImpl;
    private JButton BTN_sqlmap;
    private JButton BTN_do;
    private JButton BTN_all;

    private JComboBox SELECT_code;
    private JComboBox SELECT_encoding;
    private JTextField INPUT_path;
    private JButton BTN_selPath;

    private JCheckBox CHK_overwrite;
    private JCheckBox CHK_generateBase;

    private JCheckBox CHK_daoLogicDel;
    private JCheckBox CHK_daoUseSeq;
    private JCheckBox CHK_managerUseBO;
    private JCheckBox CHK_pageQuery;

    private JScrollPane PANEL_c;
    private JTextField INPUT_tableName;
    private JButton BTN_basedao;
    private JButton BTN_basequery;

    /**
     * settings
     */
    private JPanel PN_settings;

    private JLabel TEXT_name;
    private JLabel TEXT_encoding;
    private JLabel TEXT_code;

    private JRadioButton RADIO_languageChs;
    private JRadioButton RADIO_languageEn;

    private JRadioButton RADIO_encodeUTF8;
    private JRadioButton RADIO_encodeGBK;
    private JRadioButton RADIO_codeJava;
    private JRadioButton RADIO_codeScala;
    private JButton BTN_apply;
    private JTextField INPUT_author;
    private JLabel TEXT_info;

    private JCheckBox CHK_useCustomTpl;
    private JTextField INPUT_tplPath;
    private JLabel TEXT_useCustomTpl;
    private JLabel TEXT_customTplPath;
    private JButton BTN_selTplPath;

    /**
     * Notice
     */
    private JButton BTN_go;
    private JTextField INPUT_tddl_url;
    private JButton BTN_DAOXml;
    private JButton BTN_SQLMapConfigXml;
    private JButton BTN_PersistenceXml;

    /**
     * about
     */
    private JLabel LABEL_about;
    private JButton BTN_tplInit;


    private Column primaryColumn;
    private final FileChooserDescriptor chooseFolderOnlyDescriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor();
    //样式
    EditorColorsScheme colorScheme;
    Color background;
    JBTable jtable;
    static String[] colNames = ColumnEnum.getColumnNames(); //表头信息
    String path;
    private static final Logger logger = Logger.getInstance(GeneratorMainDialog.class);

    public GeneratorMainDialog() {
        colorScheme = EditorColorsManager.getInstance().getGlobalScheme();
        background = colorScheme.getDefaultBackground();
        PluginProjectConfig pluginProjectConfig = PluginProjectConfigHolder.getPluginProjectConfig();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(BTN_analy);
        setBackground(background);
//        INPUT_sql.setBackground(background);
//        TAB_PANEL.setBackground(background);
//        INPUT_sql.setText(new String(sql.getBytes(Charset.forName("UTF-8")), getIDECharset()));
        if(pluginProjectConfig!=null) {
            INPUT_sql.setText(pluginProjectConfig.sql);
        }
        final Setting setting = SettingManager.get();

        applySettings(setting,false);
        showTplSelect();

/*
        BTN_exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
*/

        //step1 - analy sql
        BTN_analy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAnaly();
            }
        });

        //step3 - apply settings
        BTN_apply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onApply();
            }
        });

        ActionListener act = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onGeneratorClick(e);
            }
        };
        BTN_query.addActionListener(act);
        BTN_do.addActionListener(act);
        BTN_dao.addActionListener(act);
        BTN_daoImpl.addActionListener(act);
        BTN_bo.addActionListener(act);
        BTN_manager.addActionListener(act);
        BTN_managerImpl.addActionListener(act);
        BTN_transfer.addActionListener(act);
        BTN_sqlmap.addActionListener(act);

        BTN_all.addActionListener(act);

        BTN_basedao.addActionListener(act);
        BTN_basequery.addActionListener(act);
        BTN_DAOXml.addActionListener(act);
        BTN_SQLMapConfigXml.addActionListener(act);
        BTN_PersistenceXml.addActionListener(act);

        BTN_selPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFolderOnlyDescriptor.setTitle("Select Path");
                chooseFolderOnlyDescriptor.setDescription("Select Path To Generate, generally we choose the biz path to generate");
                VirtualFile file = FileChooser.chooseFile(chooseFolderOnlyDescriptor, Env.project, null);
                if (file != null) {
                    INPUT_path.setText(file.getPath());
                    TEXT_msg.setText("");
                    SettingManager.applyPrjPath(file.getPath());
                    System.out.println(file.getPath());
                }
            }
        });

        BTN_selTplPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFolderOnlyDescriptor.setTitle("Select Path");
                chooseFolderOnlyDescriptor.setDescription("Select The Path To Save The Custom Templates");
                VirtualFile file = FileChooser.chooseFile(chooseFolderOnlyDescriptor, Env.project, null);
                if (file != null) {
                    INPUT_tplPath.setText(file.getPath());
                    System.out.println(file.getPath());
                }
            }
        });

        BTN_tplInit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tplPath = INPUT_tplPath.getText();
                boolean r = checkTplPath(tplPath);
                if(r){
                    initTemplates(tplPath);
                }
            }
        });

        CHK_useCustomTpl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTplSelect();
            }
        });

        SELECT_code.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = (String) SELECT_code.getSelectedItem();
                SettingManager.applyPrjCode(code);
            }
        });

        SELECT_encoding.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String encoding = (String) SELECT_encoding.getSelectedItem();
                SettingManager.applyPrjEncoding(encoding);
            }
        });

        //checkbox的save
        ActionListener saveSwitches = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applySwitch();
            }
        };
        CHK_daoLogicDel.addActionListener(saveSwitches);
        CHK_daoUseSeq.addActionListener(saveSwitches);
        CHK_pageQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGenerateBase();
                applySwitch();
            }
        });
        CHK_overwrite.addActionListener(saveSwitches);
        CHK_generateBase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGenerateBase();
                applySwitch();
            }
        });
        CHK_managerUseBO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkManagerUseBO();
                applySwitch();
            }
        });
        BTN_go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(
                            new java.net.URI(INPUT_tddl_url.getText()));
                }catch (Exception e1){}
            }
        });
        //step2 - generate codes


// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);



    }

    private void initTemplates(String tplPath) {
        for(Code c:Code.values()) {
            if(!tplPath.endsWith("/")){
                tplPath+="/";
            }
            String path = tplPath + c.getTplPath();
            System.out.println(path);
            checkThePath(path);
            for (GenerateType gt : GenerateType.values()) {
                if(gt.equals(GenerateType.ALL))continue;
                String name = gt.getName().toLowerCase();
                String file = path + name + TemplateConfig.TEMPLATE_EXT;
                String content = null;
                try {
                    content = TemplateUtil.getOriginTplContent(c, gt);
                    if(content==null)content="";
                    File f1 = new File(file);
                    if(f1.exists()) {
                        String c1 = FileUtil.loadFile(f1,"UTF-8");
                        if(stringEqual(c1,content)){
                            continue;
                        }else{
                            renameFile(f1,0);
                        }
                    }
                    FileUtil.writeToFile(f1,content);
                } catch (Exception e) {
                    logger.error("ERROR TO SAVE FILE: "+ file + ", content: "+content,e);
                }
            }
            TEXT_msg.setText("tpl inited!");
        }
    }

    private boolean stringEqual(String c1, String content) {
//       return  c1.replaceAll("\\s","").equals(content.replaceAll("\\s",""));
       return  c1.equals(content);
    }

    private void renameFile(File f1, int i) {
        String p1 = f1.getPath();
        File f2 = new File(p1+"_bak"+i);
        if(f2.exists()){
            i=i+1;
            renameFile(f1,i);
        }else{
            boolean r = f1.renameTo(f2);
            if(!r){
                logger.error("RENAME FILE ERROR:"+f2.getPath());
            }
        }
    }

    private void checkThePath(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                f.mkdirs();
            }
        }catch (Exception e){
            logger.error("mkdirs ERROR",e);
        }
    }

    private boolean checkTplPath(String tplPath) {
        if(tplPath==null || tplPath.trim().equals("")){
            TEXT_msg.setText("please set the custom tpl path!");
            return false;
        }else{
            File file = new File(tplPath);
            boolean r = file.isDirectory();
            if(!r){
                TEXT_msg.setText("the custom tpl path is not a directory!");
            }
            return r;
        }
    }

    private void showTplSelect() {
        boolean r = CHK_useCustomTpl.isSelected();
        TEXT_customTplPath.setVisible(r);
        BTN_selTplPath.setVisible(r);
        INPUT_tplPath.setVisible(r);
        BTN_tplInit.setVisible(r);
    }

    private void checkManagerUseBO() {
        boolean r = CHK_managerUseBO.isSelected();
        BTN_bo.setVisible(r);
        BTN_transfer.setVisible(r);
    }

    private void checkGenerateBase() {
        boolean r = CHK_generateBase.isSelected();
        BTN_basedao.setVisible(r);
        BTN_basequery.setVisible(r&&CHK_pageQuery.isSelected());
    }
    private void applySwitch() {
        SettingManager.applySwitch(CHK_managerUseBO.isSelected(), CHK_daoLogicDel.isSelected(), CHK_daoUseSeq.isSelected(), CHK_pageQuery.isSelected(), CHK_overwrite.isSelected(), CHK_generateBase.isSelected());
    }

    private void onGeneratorClick(ActionEvent e) {
        String c = e.getActionCommand();  //Generate DO
        String[] a = c.split(" "); // ["Generate","DO"]
        if(a.length==2){
            String t = a[1];
            generate(t);
        }else if(a.length==1){
            if(a[0].endsWith("-sample.xml")){
                a[0] = a[0].replace("-sample","");
            }
            generate(a[0]);
        }
    }
    //targetCode == "DO", "BO", "SQLMap" ....
    private void generate(String targetCode) {
        GenerateType gt = get(targetCode);
        if(gt==null){
            TEXT_msg.setText("can't recognize your action!");
            return;
        }
        if(jtable==null){
            TEXT_msg.setText("please input sql at \"Input\" TAB and click analy!");
            TAB_PANEL.setSelectedIndex(0);
            return;
        }
        if(!checkOutputPath()){
            TEXT_msg.setText("please set output path!");
            return;
        }
        String code = (String)SELECT_code.getSelectedItem();
        String encoding = (String)SELECT_encoding.getSelectedItem();
        //获取表格中的数据
        List<Column> doColumnList = getDOColumns();
        List<Column> queryColumnList = getQueryColumns();
        String tableName = INPUT_tableName.getText();
        if(tableName==null||tableName.trim().equals("")){
            TEXT_msg.setText("table name need set!");
            return;
        }

        if(StringUtils.isBlank(code) || StringUtils.isBlank(encoding)){
            TEXT_msg.setText("code and encoding need to select!");
            return;
        }

        if("DO".equals(targetCode)){
            if(doColumnList.size()>0){

            }else{
                TEXT_msg.setText("please choose DO columns!");
                return;
            }
        }
        if("Query".equals(targetCode)){
            if(doColumnList.size()>0){

            }else{
                TEXT_msg.setText("please choose Query columns!");
                return;
            }
        }

        //先保存关于prj的配置
        Setting settingSave = new Setting(Encoding.get(encoding), Code.get(code), path);
        settingSave.setSwitch(CHK_managerUseBO.isSelected(), CHK_daoLogicDel.isSelected(), CHK_daoUseSeq.isSelected(), CHK_pageQuery.isSelected(), CHK_overwrite.isSelected(), CHK_generateBase.isSelected());
        SettingManager.applyPrj(settingSave);
        Setting setting = SettingManager.get();
        Env.encodeFrom = Env.getProjectCharset();
        Env.encodeTo = Charset.forName(encoding);
        GeneratorAdaptor generatorAdaptor = new GeneratorAdaptor(tableName,path,code);
        generatorAdaptor.setColumnList(doColumnList, queryColumnList);
        generatorAdaptor.setPrimaryColumn(primaryColumn);


        boolean r = false;
        String rs = "";
        switch(gt){
            case DO:
                r =generatorAdaptor.generateDO();
                break;
            case Query :
                r =generatorAdaptor.generateQuery();
                break;
            case DAO:
                r =generatorAdaptor.generateDAO();
                break;
            case DAOImpl:
                r =generatorAdaptor.generateDAOImpl();
                break;
            case BO:
                r =generatorAdaptor.generateBO();
                break;
            case Transfer:
                r =generatorAdaptor.generateTransfer();
                break;
            case Manager:
                r =generatorAdaptor.generateManager();
                break;
            case ManagerImpl:
                r =generatorAdaptor.generateManagerImpl();
                break;
            case SQLMap:
                r =generatorAdaptor.generateSqlmap();
                break;
            case BaseDAO:
                r =generatorAdaptor.generateBaseDAO();
                break;
            case BaseQuery:
                r =generatorAdaptor.generateBaseQuery();
                break;
            case DAOXml:
                r =generatorAdaptor.generateDAOXml();
                break;
            case PersistenceXml:
                r =generatorAdaptor.generatePersistenceXml();
                break;
            case SQLMapConfigXml:
                r =generatorAdaptor.generateSQLMapConfigXml();
                break;
            case ALL:
                r =generatorAdaptor.generateDO();
                rs = getE(rs, r, GenerateType.DO.getName());
                r =generatorAdaptor.generateQuery();
                rs = getE(rs, r, GenerateType.Query.getName());
                r =generatorAdaptor.generateDAO();
                rs = getE(rs, r, GenerateType.DAO.getName());
                r =generatorAdaptor.generateDAOImpl();
                rs = getE(rs, r, GenerateType.DAOImpl.getName());
                r =generatorAdaptor.generateManager();
                rs = getE(rs, r, GenerateType.Manager.getName());
                r =generatorAdaptor.generateManagerImpl();
                rs = getE(rs, r, GenerateType.ManagerImpl.getName());
                r =generatorAdaptor.generateSqlmap();
                rs = getE(rs, r, GenerateType.SQLMap.getName());
                if(setting.isManagerUseBO()){
                    r =generatorAdaptor.generateBO();
                    rs = getE(rs, r, GenerateType.BO.getName());
                    r =generatorAdaptor.generateTransfer();
                    rs = getE(rs, r, GenerateType.Transfer.getName());
                }
                if(setting.isGenerateBase()){
                    r =generatorAdaptor.generateBaseDAO();
                    rs = getE(rs, r, GenerateType.BaseDAO.getName());
                    if(setting.isPagerQuery()) {
                        r = generatorAdaptor.generateBaseQuery();
                        rs = getE(rs, r, GenerateType.BaseQuery.getName());
                    }
                }
                break;
        }
        if(gt.equals(ALL)){
            TEXT_msg.setText("generate ALL done"+(!rs.equals("")?", But generate "+rs+" error!":""));
        }else {
            TEXT_msg.setText("generate " + (TypePath.RESOURCES.equals(gt.getTypePath())&&!gt.equals(GenerateType.SQLMap)?gt.getSuffix()+".xml":gt.getName()) + " " + (r ? "success" : "error" + ":" + FileWriter.getMsg()) + "!");
        }
    }

    private String getE(String rs, boolean r, String str) {
        if(!r){
            if(StringUtils.isNotBlank(rs)){
                rs+=", ";
            }
            rs+=str;
        }
        return rs;
    }


    private String getBody(String content){
        return "<html><body>"+content+"</body></html>";
    }
    private List<Column> getDOColumns() {
        return getColumns(ColumnEnum.DO);
    }

    private List<Column> getQueryColumns() {
        return getColumns(ColumnEnum.Query);
    }

    private List<Column> getColumns(ColumnEnum columnEnum) {
        List<Column> list = new ArrayList<Column>();
        TableModel tm = jtable.getModel();
        int count = tm.getRowCount();
        for(int i=0;i<count;i++){
            Boolean a = (Boolean) tm.getValueAt(i, columnEnum.getOrder());
            if(a){
                try {
                    Column c = new Column();
                    c.setName(((String) tm.getValueAt(i, ColumnEnum.Column.getOrder())).toUpperCase());
                    c.setProperty((String) tm.getValueAt(i, ColumnEnum.ClassProperty.getOrder()));
                    c.setType((String) tm.getValueAt(i, ColumnEnum.CodeType.getOrder()));
                    c.setIsPrimaryKey("Y".equals((String) tm.getValueAt(i, ColumnEnum.Primary.getOrder())));
                    String comt = (String) tm.getValueAt(i, ColumnEnum.Comment.getOrder());
                    c.setComment(StringUtils.isBlank(comt)?c.getProperty():comt);
                    if(c.isPrimaryKey()){
                        primaryColumn = c;
                    }
                    list.add(c);
                }catch (Exception e){
                    //ignore
                }
            }
        }
        return list;
    }

    public boolean checkOutputPath(){
        path = INPUT_path.getText();
        File f = new File(path);
        return f.isDirectory();
    }


    private void applySettings(Setting setting , boolean isSetting) {
        INPUT_path.setText(setting.getPath());
        if(setting.getCode().equals(Code.SCALA.getName())){
            if(!isSetting){
                RADIO_codeJava.setSelected(false);
                RADIO_codeScala.setSelected(true);
            }
            SELECT_code.setSelectedItem("SCALA");
        }else{
            if(!isSetting) {
                RADIO_codeJava.setSelected(true);
                RADIO_codeScala.setSelected(false);
            }
            SELECT_code.setSelectedItem("JAVA");
        }

        if(setting.getEncoding().equals(Encoding.GBK.getName())){
            if(!isSetting) {
                RADIO_encodeUTF8.setSelected(false);
                RADIO_encodeGBK.setSelected(true);
            }
            SELECT_encoding.setSelectedItem("GBK");
        }else{
            if(!isSetting) {
                RADIO_encodeUTF8.setSelected(true);
                RADIO_encodeGBK.setSelected(false);
            }
            SELECT_encoding.setSelectedItem("UTF-8");
        }

        if(Language.CHS.getName().equals(setting.getLanguage())){
            if(!isSetting) {
                RADIO_languageEn.setSelected(false);
                RADIO_languageChs.setSelected(true);
            }
        }else{
            if(!isSetting) {
                RADIO_languageEn.setSelected(true);
                RADIO_languageChs.setSelected(false);
            }
        }
        if(setting.getTplUseCustom()!=null){
            CHK_useCustomTpl.setSelected(setting.getTplUseCustom());
        }
        if(setting.getTplPath()!=null && !setting.getTplPath().equals("")) {
            INPUT_tplPath.setText(setting.getTplPath());
        }
        if(!isSetting) {
            INPUT_author.setText(setting.getAuthor());
            if(setting.isOverwrite()!=null){CHK_overwrite.setSelected(setting.isOverwrite());}
            if(setting.isGenerateBase()!=null){CHK_generateBase.setSelected(setting.isGenerateBase());}
            if(setting.isDaoLogicDelete()!=null){CHK_daoLogicDel.setSelected(setting.isDaoLogicDelete());}
            if(setting.isDaoUseSequence()!=null){CHK_daoUseSeq.setSelected(setting.isDaoUseSequence());}
            if(setting.isManagerUseBO()!=null){CHK_managerUseBO.setSelected(setting.isManagerUseBO());}
            if(setting.isPagerQuery()!=null){CHK_pageQuery.setSelected(setting.isPagerQuery());}
            checkGenerateBase();
            checkManagerUseBO();
        }
    }

    private void onAnaly() {
        String text = INPUT_sql.getText();
        if(StringUtils.isBlank(text+"".trim())){
            TEXT_msg.setText("no sql detected!");
        }

        SqlInfo sqlInfo  = SqlAnaly.analy(text);
        if(sqlInfo.isValid()) {
            PluginProjectConfig pluginProjectConfig = PluginProjectConfigHolder.getPluginProjectConfig();
            if(pluginProjectConfig!=null){
                pluginProjectConfig.sql = text;
            }
            showNextTab(sqlInfo);
            TAB_PANEL.setSelectedIndex(1);
            TEXT_msg.setText("");
        }else{
            TEXT_msg.setText("no sql detected or sql is not supported!");
        }
    }

    private void showNextTab(SqlInfo sqlInfo) {
        List<Column> list = sqlInfo.getColumnList();
        int num = 0;
        List<Object[]> list2 = new ArrayList<Object[]>();
        if(list!=null && list.size()>0) {
            for (Column c : list) {
                if(c!=null) {
                    List<Object> list1 = new ArrayList<Object>();
                    num++;
                    list1.add(num);
                    list1.add(Boolean.TRUE);
                    list1.add(Boolean.TRUE);
                    list1.add(c.getName());
                    list1.add(c.getTypeStr());
                    list1.add(c.getType());
                    list1.add(c.getProperty());
                    list1.add(c.isPrimaryWithSet(sqlInfo.getPrimaryKey()));
                    list1.add(c.getComment());
                    list2.add(list1.toArray(new Object[]{list1.size()}));
                }
            }
            PANEL_c.setSize((int) PANEL_c.getSize().getWidth(), (int) PANEL_c.getSize().getHeight() * list.size() / 4);
            Object[][] data = list2.toArray(new Object[][]{new Object[]{list2.size()}});

            TableModel model = new DefaultTableModel(data, colNames) {
                private static final long serialVersionUID = -7378767624104445787L;

                public boolean isCellEditable(int rowIndex, int colIndex) {
                    if (colIndex == 0 || colIndex == 3 || colIndex == 4 || colIndex == 7) { //相应的列不可编辑
                        return false;
                    }
                    return true;
                }
            };
            jtable = new JBTable(model);
            jtable.setBackground(background);
            jtable.getTableHeader().setReorderingAllowed(false);
            //No.列尝试不同的前景色
            TableColumn tableColumn = jtable.getColumn(ColumnEnum.No.getName());
            DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
//        cellRender.setForeground(colorScheme.getDefaultForeground());
            cellRender.setHorizontalAlignment(SwingConstants.CENTER);
            tableColumn.setCellRenderer(cellRender);
            tableColumn.setPreferredWidth(30);
            //DO列是checkbox
            TableColumn tableColumnDO = jtable.getColumn(ColumnEnum.DO.getName());
            tableColumnDO.setCellEditor(new CheckBoxCellEditor());
            tableColumnDO.setCellRenderer(new CheckBoxRenderer());
            tableColumnDO.setPreferredWidth(30);

            //Query列是checkbox
            TableColumn tableColumnBO = jtable.getColumn(ColumnEnum.Query.getName());
            tableColumnBO.setCellRenderer(new CheckBoxRenderer());
            tableColumnBO.setCellEditor(new CheckBoxCellEditor());
            tableColumnBO.setPreferredWidth(30);
            TableColumn tableColumnPrimary = jtable.getColumn(ColumnEnum.Primary.getName());
            DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
            cellRender1.setHorizontalAlignment(SwingConstants.CENTER);
            tableColumnPrimary.setPreferredWidth(20);
            tableColumnPrimary.setCellRenderer(cellRender1);

            PANEL_c.getViewport().add(jtable);

            INPUT_tableName.setText(sqlInfo.getTableName());
        }

    }


    class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
        private static final long serialVersionUID = 1L;
        Border border = new EmptyBorder(1, 2, 1, 2);
        public CheckBoxRenderer() {
            super();
            setOpaque(true);
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override public Component getTableCellRendererComponent(
                JTable  table,
                Object  value,
                boolean isSelected,
                boolean hasFocus,
                int     row,
                int     column) {
            if (value instanceof Boolean) {
                setSelected((Boolean) value);
                // setEnabled(table.isCellEditable(row, column));
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }
            return this;
        }
    }

    class CheckBoxCellEditor extends AbstractCellEditor implements TableCellEditor {
        private static final long serialVersionUID = 1L;
        protected JCheckBox checkBox;
        public CheckBoxCellEditor() {
            checkBox = new JCheckBox();
            checkBox.setHorizontalAlignment(SwingConstants.CENTER);
            // checkBox.setBackground( Color.white);
        }
        @Override public Object getCellEditorValue() {
            return checkBox.isSelected();
        }
        @Override public Component getTableCellEditorComponent(
                JTable  table,
                Object  value,
                boolean isSelected,
                int     row,
                int     column) {
            checkBox.setSelected((Boolean) value);
            return checkBox;
        }
    }

    private void onApply() {
        String author = INPUT_author.getText();
        Encoding encoding = Encoding.UTF8;
        if(RADIO_encodeGBK.isSelected()){
            encoding = Encoding.GBK;
        }
        Code code = Code.JAVA;
        if(RADIO_codeScala.isSelected()){
            code = Code.SCALA;
        }
        Setting settingSave = new Setting(author,encoding,code);
        boolean useTpl = CHK_useCustomTpl.isSelected();
        settingSave.setTplUseCustom(useTpl);
        if(useTpl) {
            String tp = INPUT_tplPath.getText();
            if(tp==null || tp.trim().equals("")){
                TEXT_msg.setText("please set the custom tpl path!");
            }else{
                settingSave.setTplPath(tp);
            }
        }

        SettingManager.applyApp(settingSave);
        TEXT_msg.setText("apply success!");

//        applySettings(settingSave, true);
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }
    public static void main(String[] args) throws Exception {
        GeneratorMainDialog dialog = new GeneratorMainDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

}
