package config;

import nodehandler.NodeHandler;
import sqlnode.IfSqlNode;
import sqlnode.MixedSqlNode;
import sqlnode.StaticTextSqlNode;
import sqlnode.TextSqlNode;
import sqlnode.iface.SqlNode;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import org.springframework.util.StringUtils;
import sqlsource.DynamicSqlSource;
import sqlsource.RawSqlSource;
import sqlsource.iface.SqlSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName XMlScriptBuilder
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/21 12:44
 * @Version 1.0
 */
public class XMlScriptParser {

    private boolean isDynamic = false;
    /**
     *  不同的sqlNode 使用不同的nodeHandler去处理。
     *  此处使用key-value 来保存，通过key 去判断到底使用哪个nodeHandler去处理
     */
    private Map<String , NodeHandler> handlerMap = new HashMap<>();

    public XMlScriptParser() {
        initHandlerMap();
    }

    private void initHandlerMap() {
        // 初始化 if 节点的解析器
        handlerMap.put("if",new IfNodeHandler());
        // 此处也可以初始化 where、for等sql节点的handler
    }

    public SqlSource parScriptNode(Element selectElement) {
        MixedSqlNode mixedSqlNode = parseDynamicTags(selectElement);
        SqlSource sqlSource = null;
        // 判断sql中是否含有 ${}，如果含有则是动态sql
        if (isDynamic){
            sqlSource = new DynamicSqlSource(mixedSqlNode);
        }else {
            sqlSource= new RawSqlSource(mixedSqlNode);
        }
        return sqlSource;
    }

    /**
     * 解析动态sql标签，将解析出来的sqlNode存入到mixedSqlNodd 中
     * @param selectElement
     * @return
     */
    private MixedSqlNode parseDynamicTags(Element selectElement) {
        List<SqlNode> conent = new ArrayList<>();
        // 获取所有节点数量，包括文本节点
        int nodeCount = selectElement.nodeCount();
        for (int i = 0; i < nodeCount; i++) {
            // 按照顺序获取每一个node节点
            Node node = selectElement.node(i);
            // 表示文本节点
            if(node instanceof Text){
                // 获取文本节点的sql内容
                String sqlText = node.getText().trim();
                if(StringUtils.isEmpty(sqlText)){
                    continue;
                }
                TextSqlNode textSqlNode = new TextSqlNode(sqlText);
                if (textSqlNode.isDynamic()){
                    isDynamic = true;
                    conent.add(textSqlNode);
                }else {
                    conent.add(new StaticTextSqlNode(sqlText));
                }
                // 处理动态sql节点
            }else if (node instanceof Element){
                // 此处需要通过不同的sql节点handler去处理
                Element nodeToHandler = (Element) node;
                String name = nodeToHandler.getName();
                NodeHandler nodeHandler = handlerMap.get(name);
                nodeHandler.apply(nodeToHandler,conent);
                isDynamic = true;
            }
        }
        return new MixedSqlNode(conent);
    }

    /**
     * 专门来解析if标签的标签处理器
     *
     * @author 灭霸詹
     *
     */
    class IfNodeHandler implements NodeHandler {

        /**
         * nodeToHandler：if标签
         */

        @Override
        public void apply(Element nodeHandler, List<SqlNode> content) {
            String test = nodeHandler.attributeValue("test");

            MixedSqlNode parseDynamicTags = parseDynamicTags(nodeHandler);

            content.add(new IfSqlNode(test, parseDynamicTags));
        }
    }
}
