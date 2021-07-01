package sqlnode;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DynamicContext
 * @Description  封装sql信息和入参信息
 * @Author za-yaowei
 * @Date 2021/6/21 15:48
 * @Version 1.0
 */
public class DynamicContext {
    // sql信息
    private StringBuilder sb = new StringBuilder();

    private Map<String,Object> bindings = new HashMap<>();

    public DynamicContext(Object param) {
        bindings.put("param",param);
    }

    public String getSql (){
        return sb.toString();
    }

    public void appendSql(String sql){
        sb.append(sql).append(" ");
    }

    public Map<String,Object> getBindings(){
        return bindings;
    }

}
