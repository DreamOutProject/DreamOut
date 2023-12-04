package com.CommunicateObject;

import java.io.Serializable;
import java.util.Objects;

public class User extends MOD implements Serializable {
    private int id;
    private int pw;
    public User(int id, int pw){
        this.id = id;
        this.pw = pw;
    }
    public User(User t){
        this.id = t.getId();
        this.pw = t.getPw();
    }

    public int getId(){return this.id;}
    public void setId(int id){this.id =id;}

    public void setPw(int pw){this.pw=pw;}
    public int getPw(){return this.pw;}

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User t){
            return this.id == t.getId() && this.pw == t.getPw();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pw);
    }

    @Override
    public String toString() {
        return "아이디는 : " + id + "비밀번호는 : " + pw +"입니다.";
    }
}
