package nodehandler;


import sqlnode.iface.SqlNode;
import org.dom4j.Element;

import java.util.List;

/**
 * @ClassName NodeHandler
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/21 12:49
 * @Version 1.0
 */
public interface NodeHandler {

    void apply(Element nodeHandler, List<SqlNode> content);
}
