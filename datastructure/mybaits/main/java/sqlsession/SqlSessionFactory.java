package sqlsession;

/**
 * @ClassName SqlSessionFactory
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/21 19:34
 * @Version 1.0
 */
public interface SqlSessionFactory {
    SqlSession openSqlSession();
}
