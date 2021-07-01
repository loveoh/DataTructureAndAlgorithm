package config;

import lombok.Data;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Configuration
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/20 23:10
 * @Version 1.0
 */
@Data
public class Configuration {

    private DataSource dataSource;

    // 保存所有的mappedStatement  key：mapper.xml 的namenspace的值 + sql语句的id
    private Map<String,MappedStatement> mappedStatement = new HashMap<>();

    public void  addMappedStatement(String mappedStatementId ,MappedStatement mappedStatement){
        this.mappedStatement.put(mappedStatementId,mappedStatement);
    }

    public MappedStatement getMappedStatement(String statementId){
        return  this.mappedStatement.get(statementId);
    }

}
