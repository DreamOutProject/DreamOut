package com.CommunicateObject;

public class ObjectMsgDecorator extends ObjectMsg {
    private ObjectMsg obj;
    public ObjectMsgDecorator(ObjectMsg obj){
        this.obj = obj;
    }
    @Override
    public String getMsg(){return obj.getMsg();}
    @Override
    public void setMsg(String msg){obj.setMsg(msg);}
}
