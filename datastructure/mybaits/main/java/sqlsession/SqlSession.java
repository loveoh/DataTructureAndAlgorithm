package sqlsession;

import java.util.List;

/**
 * @ClassName SqlSession
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/21 19:31
 * @Version 1.0
 */
public interface SqlSession {

    <T> T selectOne(String statementId,Object param);

    <T> List<T> selectList(String statementId, Object param);
}
