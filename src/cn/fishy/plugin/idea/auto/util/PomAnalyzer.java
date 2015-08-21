package cn.fishy.plugin.idea.auto.util;

import cn.fishy.plugin.idea.auto.domain.PomNamespaceContext;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: duxing
 * Date: 2015.07.26 23:51
 */
public class PomAnalyzer {

    private Document doc;
    private XPath xpath;

    public PomAnalyzer(String pomFilePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true); // never forget this!
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(pomFilePath);

            XPathFactory xPathFactory = XPathFactory.newInstance();
            xpath = xPathFactory.newXPath();
            xpath.setNamespaceContext(new PomNamespaceContext());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getValueByXPath(String xpathExpression) {
        String result = "";
        try {
            XPathExpression expr = xpath.compile(xpathExpression);
            Node node = (Node)expr.evaluate(this.doc, XPathConstants.NODE);
            if (node != null) {
                result = node.getTextContent();
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getPackaging() {
        return getValueByXPath("/ns:project/ns:packaging");
    }

    public String getOutputPath() {
        return getDirectory("/ns:project/ns:build/ns:outputDirectory", null, "target/classes");
    }

    public String getWarExplodedDirectory() {
        return getDirectory("/ns:project/ns:build/ns:plugins/ns:plugin/ns:configuration/ns:webappDirectory", "warExplodedDirectory");
    }


    public String getDirectory(String xpath, String backPropertyName) {
        return getDirectory(xpath, backPropertyName, null);
    }

    public String getDirectory(String xpath, String backPropertyName, String defaultValue) {
        String directoryPropertySet = null;
        String directory = getValueByXPath(xpath);
        //当可以搜索到路径位置
        if (StringUtils.isNotBlank(directory)) {
            //则查询是否是参数配置
            if (directory.contains("${")) {
                Pattern pattern = Pattern.compile("\\$\\{([^\\}]*)\\}");
                Matcher m = pattern.matcher(directory);
                if (m.find()) {
                    String propertyName = m.group(1);
                    directoryPropertySet = getValueByXPath("/ns:project/ns:properties/ns:" + propertyName);

                }
                if (StringUtils.isBlank(directoryPropertySet) && backPropertyName != null) {
                    directoryPropertySet = getValueByXPath("/ns:project/ns:properties/ns:" + backPropertyName);
                }
                if (StringUtils.isNotBlank(directoryPropertySet)) {
                    return directoryPropertySet;
                }
            } else {
                return directory;
            }
        } else {
            if (defaultValue != null) {
                return defaultValue;
            }
        }
        return defaultValue;
    }


    public String getResourceDirectory() {
        String resourceDirectory = getValueByXPath("/ns:project/ns:build/ns:Resources/ns:resource");
        if (StringUtils.isEmpty(resourceDirectory)) {
            resourceDirectory = "src/main/resources";
        }
        return resourceDirectory;
    }

}
