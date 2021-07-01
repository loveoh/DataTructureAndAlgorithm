package executor;

import config.Configuration;
import config.MappedStatement;
import executor.iface.Executor;

import java.util.List;

/**
 * @ClassName CaceExecutor
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/22 18:52
 * @Version 1.0
 */
public class CaceExecutor implements Executor {
    private Executor delegate;

    public CaceExecutor(Executor delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object params) {
        // 处理二级缓存
        return delegate.query(mappedStatement,configuration,params);
    }
}
