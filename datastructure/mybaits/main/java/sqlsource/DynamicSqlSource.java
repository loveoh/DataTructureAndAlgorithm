package sqlsource;

import sqlnode.DynamicContext;
import sqlnode.MixedSqlNode;
import sqlnode.iface.SqlNode;
import sqlsource.iface.SqlSource;

/**
 * @ClassName DynamicSqlSource
 * @Description 封装带有${} 和动态sql标签的sql信息
 * @Author za-yaowei
 * @Date 2021/6/21 17:37
 * @Version 1.0
 */
public class DynamicSqlSource implements SqlSource {

    private SqlNode sqlNode;

    public DynamicSqlSource(MixedSqlNode mixedSqlNode) {
        this.sqlNode = mixedSqlNode;
    }

    /**
     * 动态sql，每次获取的时候都要重新拼接
     * @param param
     * @return
     */
    @Override
    public BoundSql getBoundSql(Object param) {
        DynamicContext context = new DynamicContext(param);
        // 将SqlNode处理成一条SQL语句
        sqlNode.apply(context);
        // 该SQL语句，此时还包含#{}，不包含${}
        String sql = context.getSql();
        // 通过SqlSourceParser去解析SqlSource中的#{}
        SqlSourceParser sqlSourceParser = new SqlSourceParser();
        // 将解析的结果，最终封装成StaticSqlSource
        SqlSource sqlSource = sqlSourceParser.parse(sql);
        // 调用StaticSqlSource的方法
        return sqlSource.getBoundSql(param);

    }
}
