package com.CommunicateObject;

import javax.swing.*;
import java.util.Vector;

public class Picture extends ObjectMsgDecorator{
    private Vector<JLabel>picture;
    public Picture(ObjectMsg obj){
        super(obj);
        picture = new Vector<>();
    }
    public Vector<JLabel> getPicture(){return this.picture;}
    public void addPicture(JLabel picture){this.picture.add(picture);}
}
