package test.framework.dbutil;

import org.junit.Test;

import java.util.List;

public class Testdbutils {
    @Test
    public void userDaoTest() {
        Users user =null;
        UserDao dao = new UserDao();
        user = dao.findUserById(1);
        System.out.println(user.getUserName());
    }
    @Test
    public void findTest(){
        List<Users> list = new UserDao().find();
        for(Users l:list){
            System.out.println(l.getUserName());
        }
    }
    @Test
    public void findById(){
        Object[] array = new UserDao().findUserById2(1);
        for(Object a:array){
            System.out.println(a);
        }
    }
}
