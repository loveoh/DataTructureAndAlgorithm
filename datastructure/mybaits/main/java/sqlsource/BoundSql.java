package sqlsource;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BoundSql
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/21 11:06
 * @Version 1.0
 */
public class BoundSql {
    private String sql;

    private List<ParameterMapping> parameterMapping = new ArrayList<>();

    public BoundSql(String sql, List<ParameterMapping> parameterMapping) {
        this.sql = sql;
        this.parameterMapping = parameterMapping;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMapping() {
        return parameterMapping;
    }

    public void setParameterMapping(List<ParameterMapping> parameterMapping) {
        this.parameterMapping = parameterMapping;
    }
}
