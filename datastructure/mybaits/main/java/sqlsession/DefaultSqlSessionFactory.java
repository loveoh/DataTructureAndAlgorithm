package sqlsession;

import config.Configuration;

/**
 * @ClassName DefaultSqlSessionFactory
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/22 18:30
 * @Version 1.0
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSqlSession() {

        return new DefaultSqlSession(configuration);
    }
}
