package executor;

import config.Configuration;
import config.MappedStatement;
import sqlsource.BoundSql;
import sqlsource.ParameterMapping;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SimpleExecutor
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/22 19:09
 * @Version 1.0
 */
public class SimpleExecutor extends BaseExecutor {


    protected List<Object> queryFromDataBase(MappedStatement mappedStatement, Configuration configuration, Object params, BoundSql boundSql) {
        List<Object> result = new ArrayList<>();
        // 获取数据库连接
        try {
            Connection connection = getConnetion(configuration);
            String sql = boundSql.getSql();
            String statementType = mappedStatement.getStatementType();
            if ("prepared".equals(statementType)){
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                // 替换sql参数，将sql中的？替换称为对应的参数
                parameterize(preparedStatement,mappedStatement,boundSql,params);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet != null){
                    handlerResultSet(mappedStatement,result,resultSet);
                }
            }
        }catch (Exception e){

        }

        return result;
    }

    /**
     * 通过反射封装jdbc的返回结果
     * @param mappedStatement
     * @param result
     * @param resultSet
     * @throws Exception
     */
    private void handlerResultSet(MappedStatement mappedStatement, List<Object> result, ResultSet resultSet) throws Exception {

        Class<?> resultType = mappedStatement.getResultType();
        while (resultSet.next()){
            Object o = resultType.newInstance();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                String columnName = metaData.getColumnName(i+1);
                Field declaredField = resultType.getDeclaredField(columnName);
                declaredField.setAccessible(true);
                declaredField.set(o,resultSet.getObject(i + 1));
            }
            result.add(o);
        }
    }


    /**
     * sql参数解析替换
     * @param preparedStatement
     * @param mappedStatement
     * @param boundSql
     * @param params
     * @throws Exception
     */
    private void parameterize(PreparedStatement preparedStatement, MappedStatement mappedStatement, BoundSql boundSql, Object params) throws Exception {
        Class<?> parameterType = mappedStatement.getParameterType();
        if (parameterType == Integer.class){
            preparedStatement.setObject(1,params);
        }else if (parameterType == String.class){
            preparedStatement.setObject(1,params);
        }else if (parameterType == Map.class){

        }else {
            // 处理自定义对应的参数。
            List<ParameterMapping> parameterMapping = boundSql.getParameterMapping();
            for (int i = 0; i < parameterMapping.size(); i++) {
                // 获取sql语句中#{} 里面的占位符名称
                ParameterMapping p = parameterMapping.get(i);
                String name = p.getName();

                Field declaredField = parameterType.getDeclaredField(name);
                declaredField.setAccessible(true);
                Object value = declaredField.get(params);
                preparedStatement.setObject(i +1 ,value);
            }
        }
    }

    /**
     * 获取数据库连接
     * @param configuration
     * @return
     */
    private Connection getConnetion(Configuration configuration) {
        DataSource dataSource = configuration.getDataSource();
        try {
            Connection connection = dataSource.getConnection();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
