import pojo.User;
import service.UserService;

public class MysqlTest {
    private static UserService userService=new UserService();
    public static void main(String[] args) {
        User user = new User();
        user.setEmail("11111111111");
        user.setPassword("0000000000");
       //userService.addUer(user);
        userService.changePassword(user);
    }
}
