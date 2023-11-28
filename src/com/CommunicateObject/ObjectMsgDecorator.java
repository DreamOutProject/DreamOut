package com.CommunicateObject;

import java.io.Serializable;

public class ObjectMsgDecorator extends ObjectMsg implements Serializable{
    public ObjectMsgDecorator(ObjectMsg obj){
        this.obj = obj;
    }
    @Override
    public void setMsgMode(int mode) {
        obj.setMsgMode(mode);
    }

    @Override
    public int getMsgMode() {
        return obj.getMsgMode();
    }
}
