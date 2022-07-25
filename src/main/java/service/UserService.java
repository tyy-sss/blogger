package service;


import dao.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.User;
import utils.MD5;
import utils.SqlSessionFactoryUtils;

public class UserService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    //登录
    public User login(User user){
        SqlSession sqlSession=factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        user.setPassword(MD5.MD5Encode(user.getPassword(),"utf8"));
        User u = mapper.select(user.getEmail(), user.getPassword());
        sqlSession.close();
        return u;
    }
    //判断用户是否存在
    public User selectUer(User user) {
        SqlSession sqlSession=factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1;
        if(user.getEmail()==null){
            user1=mapper.selectUserByPhone(user.getPhone());
        }else{
            user1=mapper.selectUserByEmail(user.getEmail());
        }
        sqlSession.close();
        return user1;
    }
    //添加账号
    public void addUer(User user) {
        SqlSession sqlSession=factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        user.setPassword(MD5.MD5Encode(user.getPassword(),"utf8"));
        System.out.println(user.getPassword());
        mapper.addUser(user.getEmail(),user.getPassword());
        sqlSession.close();
    }
    //修改密码
    public void changePassword(User user){
        SqlSession sqlSession=factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        user.setPassword(MD5.MD5Encode(user.getPassword(),"utf8"));
        System.out.println(user.getPassword());
        mapper.changePassword(user.getEmail(),user.getPassword());
        sqlSession.close();
    }
}
