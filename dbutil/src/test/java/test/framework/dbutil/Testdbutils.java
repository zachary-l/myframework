package test.framework.dbutil;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void findByIdTest(){
        Object[] array = new UserDao().findUserById2(1);
        for(Object a:array){
            System.out.println(a);
        }
    }
    @Test
    public void executeBatch(){
        Object[][] obj = new Object[3][2];
        obj[0][0]="one";
        obj[0][1]=18;
        obj[1][0]="two";
        obj[1][1]=20;
        obj[2][0]="three";
        obj[2][1]=50;
        UserDao userDao = new UserDao();
        int[] row = userDao.executeBatch(obj);
        for(int r:row){
            System.out.println(r);
        }

    }
    @Test
    public void putTest(){
           final Map<Class<?>, Object> primitiveDefaults = new HashMap<Class<?>, Object>();

            primitiveDefaults.put(Integer.TYPE, Integer.valueOf(0));
            primitiveDefaults.put(Short.TYPE, Short.valueOf((short) 0));
            primitiveDefaults.put(Byte.TYPE, Byte.valueOf((byte) 0));
            primitiveDefaults.put(Float.TYPE, Float.valueOf(0f));
            primitiveDefaults.put(Double.TYPE, Double.valueOf(0d));
            primitiveDefaults.put(Long.TYPE, Long.valueOf(0L));
            primitiveDefaults.put(Boolean.TYPE, Boolean.FALSE);
            primitiveDefaults.put(Character.TYPE, Character.valueOf((char) 0));
            for(Class<?> c:primitiveDefaults.keySet()){
                System.out.println(c);
                System.out.println(primitiveDefaults.get(c));
            }

    }
    @Test
    public void test(){
        Object[][] obj = new Object[3][2];
        System.out.println(obj.length);

    }
}
