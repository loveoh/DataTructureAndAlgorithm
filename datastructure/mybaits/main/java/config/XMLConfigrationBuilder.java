package config;

import io.Resource;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.util.StringUtils;
import uitls.DocumentUtils;

import java.io.InputStream;
import java.io.Reader;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName XMLConfigrationBuilder
 * @Description 解析全局配置文件。
 * @Author za-yaowei
 * @Date 2021/6/21 11:34
 * @Version 1.0
 */
public class XMLConfigrationBuilder {

    private Configuration configuration;

    public XMLConfigrationBuilder() {
        this.configuration = new Configuration();
    }

    public Configuration parse(InputStream inputStream){
        Document document = DocumentUtils.readDocument(inputStream);
        // 获取xml的根节点 <configuration>
        Element rootElement = document.getRootElement();
        parseConfiguration(rootElement);
        return configuration;

    }

    /**
     * 解析<configuration>节点下的内容
     * @param rootElement
     */
    private void parseConfiguration(Element rootElement) {
        Element environments = rootElement.element("environments");
        parseEnvironments(environments);

        Element mappers = rootElement.element("mappers");
        parseMappers(mappers);
    }

    /**
     * 解析<mappers>节点内容
     *
     * @param mappers
     */
    private void parseMappers(Element mappers) {
        List<Element> mapperElements = mappers.elements("mapper");
        for (Element mapper : mapperElements) {
            parseMapper(mapper);
        }

    }

    /**
     * 解析mapper.xml
     *
     * @param mapper
     */
    private void parseMapper(Element mapper) {
        String resource = mapper.attributeValue("resource");
        InputStream inputStream = Resource.getResourceAsStream(resource);
        Document document = DocumentUtils.readDocument(inputStream);
        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
        xmlMapperBuilder.parse(document.getRootElement());
    }


    /**
     * 解析<environments>节点内容
     *
     * @param environments
     */
    private void parseEnvironments(Element environments) {
        String aDefault = environments.attributeValue("default");
        if (StringUtils.isEmpty(aDefault)){
            return ;
        }
        List<Element> elements = environments.elements();
        for (Element  element : elements) {
            String id = element.attributeValue("id");
            if (id.equals(aDefault)){
                parseDataSource(element.element("dataSource"));
            }
        }

    }

    /**
     * 解析数据源
     * @param deElement
     */
    private void parseDataSource(Element deElement) {
        String type = deElement.attributeValue("type");
        if ("DBCP".equals(type)){
            BasicDataSource dataSource = new BasicDataSource();
            Properties properties = new Properties();
            List<Element> elements = deElement.elements();

            for (Element element : elements){
                String name = element.attributeValue("name");
                String value = element.attributeValue("value");
                properties.put(name,value);
            }

            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
            configuration.setDataSource(dataSource);
        }
    }
}
