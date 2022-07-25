package dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> selectAll();
    //判断账号是否存在
    @Select("SELECT * FROM user WHERE email = #{email}")
    User selectUserByEmail(@Param("email") String email);
    @Select("SELECT * FROM user WHERE phone = #{phone}")
    User selectUserByPhone(@Param("phone") Integer phone);
    /*
    用户注册
     */
    @Select("INSERT INTO user (email,password) VALUES (#{email},#{password}) ")
    User addUser(@Param("email") String email, @Param("password") String password);
    /*
    用户登录
     */
    @Select("SELECT * FROM user WHERE email = #{email} AND password = #{password}")
    User select(@Param("email") String email, @Param("password") String password);
    /*
    修改密码
     */
    @Select("UPDATE user SET password = #{password} WHERE email = #{email}")
    void changePassword(@Param("email") String email, @Param("password") String password);
}
