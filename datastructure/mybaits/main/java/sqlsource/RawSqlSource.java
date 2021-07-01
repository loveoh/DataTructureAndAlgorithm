package sqlsource;

import sqlnode.DynamicContext;
import sqlnode.MixedSqlNode;
import sqlsource.iface.SqlSource;

/**
 * @ClassName RawSqlSource
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/21 17:44
 * @Version 1.0
 */
public class RawSqlSource implements SqlSource {

    private SqlSource sqlSource;

    /**
     * 非动态sql 只需要在构造的时候创建一次就好。
     * @param mixedSqlNode
     */
    public RawSqlSource(MixedSqlNode mixedSqlNode) {
        DynamicContext context = new DynamicContext(null);
        // 将SqlNode处理成一条SQL语句
        mixedSqlNode.apply(context);
        // 该SQL语句，此时还包含#{}，不包含${}
        String sql = context.getSql();
        // 通过SqlSourceParser去解析SqlSource中的#{}
        SqlSourceParser sqlSourceParser = new SqlSourceParser();
        // 将解析的结果，最终封装成StaticSqlSource
        sqlSource = sqlSourceParser.parse(sql);
    }


    @Override
    public BoundSql getBoundSql(Object param) {
        return sqlSource.getBoundSql(param);
    }
}
