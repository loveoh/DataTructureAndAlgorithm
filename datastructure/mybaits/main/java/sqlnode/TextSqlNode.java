package sqlnode;

import sqlnode.iface.SqlNode;
import uitls.GenericTokenParser;
import uitls.OgnlUtils;
import uitls.SimpleTypeRegistry;
import uitls.TokenHandler;

/**
 * @ClassName TextSqlNode
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/21 17:55
 * @Version 1.0
 */
public class TextSqlNode implements SqlNode {

    private String textSql;

    public TextSqlNode(String textSql) {
        this.textSql = textSql;
    }

    @Override
    public void apply(DynamicContext dynamicContext) {
//先处理${}，将处理之后的SQL语句，追加到context中
        GenericTokenParser tokenParser = new GenericTokenParser("${", "}", new BindingTokenParser(dynamicContext));
        String sql = tokenParser.parse(textSql);
        dynamicContext.appendSql(sql);
    }

    /**
     * 判断sql是否是动态sql
     * @param
     * @return
     */
    public boolean isDynamic(){
        if (textSql.indexOf("${") > -1){
            return true;
        }
        return false;
    }
    private static class BindingTokenParser implements TokenHandler {
        private DynamicContext context;

        public BindingTokenParser(DynamicContext context) {
            this.context = context;
        }

        /**
         * expression：比如说${username}，那么expression就是username username也就是Ognl表达式
         */
        @Override
        public String handleToken(String expression) {
            Object paramObject = context.getBindings().get("_parameter");
            if (paramObject == null) {
                // context.getBindings().put("value", null);
                return "";
            } else if (SimpleTypeRegistry.isSimpleType(paramObject.getClass())) {
                // context.getBindings().put("value", paramObject);
                return String.valueOf(paramObject);
            }

            // 使用Ognl api去获取相应的值
            Object value = OgnlUtils.getValue(expression, context.getBindings());
            String srtValue = value == null ? "" : String.valueOf(value);
            return srtValue;
        }

    }
}
