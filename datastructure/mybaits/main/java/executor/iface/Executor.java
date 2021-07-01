package executor.iface;

import config.Configuration;
import config.MappedStatement;

import java.util.List;

/**
 * @ClassName Executor
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/22 18:50
 * @Version 1.0
 */
public interface Executor {

    <T> List<T> query(MappedStatement mappedStatement, Configuration configuration,Object params);
}
