package config;

import lombok.Data;
import org.dom4j.Element;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ClassName XMLMapperBuilder
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/21 12:02
 * @Version 1.0
 */
@Data
public class XMLMapperBuilder {

    private Configuration configuration;


    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 解析mapper文件
     *
     * @param rootElement
     */
    public void parse(Element rootElement) {
        String namespace = rootElement.attributeValue("namespace");
        if (StringUtils.isEmpty(namespace)){
            return;
        }
        List<Element> selectElements = rootElement.elements("select");
        for (Element selectElement : selectElements){
            XMLStatementBuilder xmlStatementBuilder = new XMLStatementBuilder(configuration);
            xmlStatementBuilder.parseStatement(selectElement,namespace);
        }
    }
}
