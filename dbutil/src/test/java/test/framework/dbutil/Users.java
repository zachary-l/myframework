package test.framework.dbutil;

import org.framework.dbutil.Column;

public class Users {
    @Column("U_ID")
    private int uid;
    @Column("U_NAME")
    private String userName;
    @Column("U_AGE")
    private int age;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Users{" +
                "uid=" + uid +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
