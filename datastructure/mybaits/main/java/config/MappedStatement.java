package config;

import lombok.Data;
import sqlsource.iface.SqlSource;

/**
 * @ClassName MappedStatement
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/20 23:13
 * @Version 1.0
 */
@Data
public class MappedStatement {

    private String statementId;

    private Class<?> parameterType;

    private Class<?> resultType;

    private  String statementType;

    // 保存可以
    private SqlSource sqlSource;

    public MappedStatement(String statementId, Class<?> parameterType, Class<?> resultType, String statementType, SqlSource sqlSource) {
        this.statementId = statementId;
        this.parameterType = parameterType;
        this.resultType = resultType;
        this.statementType = statementType;
        this.sqlSource = sqlSource;
    }
}
