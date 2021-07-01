package executor;

import config.Configuration;
import config.MappedStatement;
import executor.iface.Executor;
import org.springframework.util.CollectionUtils;
import sqlsource.BoundSql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseExecutor
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/22 18:55
 * @Version 1.0
 */
public abstract class BaseExecutor implements Executor {

    private Map<String, List<Object>> oneLevelCace = new HashMap<>();

    @Override
    public <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object params) {
        BoundSql boundSql = mappedStatement.getSqlSource().getBoundSql(params);
        List<Object> objects = oneLevelCace.get(boundSql.getSql());
        if (!CollectionUtils.isEmpty(objects)){
            return (List<T>) objects;
        }
         objects = queryFromDataBase(mappedStatement,configuration,params,boundSql);
        return (List<T>) objects;
    }

    /**
     * 模板方法，父类不实现，子类自定义实现
     * @param mappedStatement
     * @param configuration
     * @param params
     * @param boundSql
     * @return
     */
    protected abstract List<Object> queryFromDataBase(MappedStatement mappedStatement, Configuration configuration, Object params, BoundSql boundSql);

}
