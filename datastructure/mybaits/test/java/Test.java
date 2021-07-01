import config.XMLConfigrationBuilder;
import io.Resource;
import pojo.User;
import sqlsession.SqlSession;
import sqlsession.SqlSessionFactory;
import sqlsession.SqlSessionFactoryBuilder;
import uitls.DocumentUtils;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName Test
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/21 18:29
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {
        InputStream resourceAsReader = Resource.getResourceAsStream("mybatis-config.xml");
//        XMLConfigrationBuilder builder = new XMLConfigrationBuilder();
//        builder.parse(resourceAsReader);

        User user = new User();
        user.setId(50);
        user.setMobile("18755110851");
        user.setUsername("chenjiaming");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsReader);
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        List<Object> test = sqlSession.selectList("test.findUserById", user);

        System.out.println(test.get(0));
    }
}
