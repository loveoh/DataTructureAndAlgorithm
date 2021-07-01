package sqlsession;

import config.Configuration;
import config.XMLConfigrationBuilder;

import java.io.InputStream;

/**
 * @ClassName SqlSessionFactoryBuilder
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/22 18:27
 * @Version 1.0
 */
public class SqlSessionFactoryBuilder {

    private Configuration configuration;

    public SqlSessionFactory build(InputStream inputStream) {
        XMLConfigrationBuilder xmlConfigrationBuilder = new XMLConfigrationBuilder();
        configuration = xmlConfigrationBuilder.parse(inputStream);
        return build();
    }

    private SqlSessionFactory build() {

        return new DefaultSqlSessionFactory(configuration);

    }
}
