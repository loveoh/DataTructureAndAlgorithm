package sqlsession;

import config.Configuration;
import config.MappedStatement;
import executor.CaceExecutor;
import executor.SimpleExecutor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @ClassName DefaultSqlSession
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/22 18:31
 * @Version 1.0
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statementId, Object param) {
        List<Object> objects = selectList(statementId, param);
        if (!CollectionUtils.isEmpty(objects) && objects.size() == 1){
            return (T) objects.get(0);
        }
        return null;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object param) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statementId);
        CaceExecutor caceExecutor = new CaceExecutor(new SimpleExecutor());
        return caceExecutor.query(mappedStatement,configuration,param);
    }
}
