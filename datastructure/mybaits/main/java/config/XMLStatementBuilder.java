package config;

import org.dom4j.Element;
import org.springframework.util.StringUtils;
import sqlsource.iface.SqlSource;

/**
 * @ClassName XMLStatementBuilder
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/21 12:26
 * @Version 1.0
 */
public class XMLStatementBuilder {

    private Configuration configuration;

    public XMLStatementBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parseStatement(Element selectElement, String namespace) {
        String id = selectElement.attributeValue("id");
        if (StringUtils.isEmpty(id)){
            return;
        }
        // sql的唯一id
        String statementId = namespace +"." + id;
        String parameterType = selectElement.attributeValue("parameterType");
        Class<?> parameterClass = resolveType(parameterType);
        String reslutType = selectElement.attributeValue("resultType");
        Class<?> resultClass = resolveType(reslutType);
        String statementType = selectElement.attributeValue("statementType");
        statementType = StringUtils.isEmpty(selectElement) ? "prepared" : statementType;
        // 解析sql语句，并保存在sqlSource中
       SqlSource sqlSource = createSqlSource(selectElement);
       MappedStatement mappedStatement = new MappedStatement(statementId,parameterClass,resultClass,statementType,sqlSource);
       // 将mappedStatement 保存到全局配置文件中。
       configuration.addMappedStatement(statementId,mappedStatement);
    }


    private SqlSource createSqlSource(Element selectElement) {
        XMlScriptParser xMlScriptBuilder = new XMlScriptParser();
        // 解析sql语句
        SqlSource sqlSource = xMlScriptBuilder.parScriptNode(selectElement);
        return sqlSource;
    }

    private Class<?> resolveType(String parameterType) {

        try {
            Class<?> aClass = Class.forName(parameterType);
            return aClass;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
