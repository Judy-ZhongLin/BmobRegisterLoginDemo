package database;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
    private Integer age;
    private String gender;  //性别

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
