package com.CommunicateObject;

import javax.swing.*;
import java.io.Serializable;
import java.util.Vector;

public class Picture extends MOD implements Serializable {
    private Vector<JLabel>files;
    public Picture(){files = new Vector<>();}
    public Picture(int size){files = new Vector<>(size);}
    public Picture(Picture t){files = new Vector<>(t.getFiles());}
    public Vector<JLabel> getFiles(){return this.files;}
    public void setFiles(Vector<JLabel> files) {this.files = files;}
    public boolean addPicture(JLabel l){
        if (this.files==null)return false;
        return this.files.add(l);
    }
    public boolean setPicture(JLabel d,int index){
        if(d==null)return false;
        if(this.files.size()<=index)return false;
        this.files.set(index,d);//해당 데이터로 셋팅하자.
        return true;
    }
}
