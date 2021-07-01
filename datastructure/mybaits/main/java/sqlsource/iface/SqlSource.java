package sqlsource.iface;

import sqlsource.BoundSql;

/**
 * @ClassName SqlSource
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/21 11:05
 * @Version 1.0
 */
public interface SqlSource {

    /**
     *  根据参数获取JDBC 能执行的sql语句
     * @param param
     * @return
     */
    BoundSql getBoundSql(Object param);
}
