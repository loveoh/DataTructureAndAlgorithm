package sqlnode.iface;

import sqlnode.DynamicContext;

/**
 * @ClassName SqlNode
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/21 13:09
 * @Version 1.0
 */
public interface SqlNode {

    void apply(DynamicContext dynamicContext);



}
