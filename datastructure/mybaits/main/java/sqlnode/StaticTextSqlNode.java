package sqlnode;

import sqlnode.iface.SqlNode;

/**
 * @ClassName StaticSqlNode
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/21 18:10
 * @Version 1.0
 */
public class StaticTextSqlNode implements SqlNode {

    private String sqlText;

    public StaticTextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContext dynamicContext) {
        dynamicContext.appendSql(sqlText);
    }
}
