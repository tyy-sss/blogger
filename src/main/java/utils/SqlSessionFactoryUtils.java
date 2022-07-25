package utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtils {

    private static SqlSessionFactory sqlSessionFactory;
    //初始静态化块， 类加载的时候就会加载
    static {
        try {
            //创建sqlSessionFactoryBuilder对象  , 该对象只是用一次，故只需在方法内的变量即可。
            SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
            //创建核心配置文件输入流
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //通过输入流创建SqlSessionFactory对象
            sqlSessionFactory = ssfb.build(inputStream);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    // 提供get方法，获取sqlSessionFactory
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
