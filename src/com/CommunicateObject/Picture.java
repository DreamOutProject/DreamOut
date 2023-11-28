package com.CommunicateObject;

import javax.swing.*;
import java.io.Serializable;
import java.util.Vector;

public class Picture extends ObjectMsgDecorator implements Serializable{
    private Vector<JLabel>picture;//누구 유저의 그림앨범
    public Picture(ObjectMsg obj,Vector<JLabel> insert ) {
        super(obj);
        this.obj = obj;
        picture = insert;
    }
    public Vector<JLabel> getPicture(){return this.picture;}
    public void addPicture(JLabel picture){this.picture.add(picture);}

    @Override
    public String toString() {
        return "현재 그림의 사이즈는" + picture.size() + "입니다.";
    }
}
