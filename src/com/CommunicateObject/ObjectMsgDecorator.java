package com.CommunicateObject;

public class ObjectMsgDecorator extends ObjectMsg {
    private ObjectMsg obj;
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
