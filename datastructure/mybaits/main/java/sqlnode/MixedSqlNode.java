package sqlnode;

import sqlnode.iface.SqlNode;

import java.util.List;

/**
 * @ClassName MixedSqlNode
 * @Description  用来统一保存 解析出来的sqlNode，方便管理
 * @Author za-yaowei
 * @Date 2021/6/21 17:28
 * @Version 1.0
 */
public class MixedSqlNode implements SqlNode {

    private List<SqlNode> sqlNodes;


    public MixedSqlNode(List<SqlNode> sqlNodes) {
        this.sqlNodes = sqlNodes;
    }

    @Override
    public void apply(DynamicContext dynamicContext) {
        for (SqlNode sqlNode :sqlNodes){
            sqlNode.apply(dynamicContext);
        }
    }
}
