package com.CommunicateObject;

public class StringMsg extends ObjectMsgDecorator{
    private String msg;
    public StringMsg(ObjectMsgDecorator obj,String msg){
        super(obj);
        this.msg = msg;
    }
    @Override
    public String getMsg() {
        if(msg==null)return"비어있는 메세지입니다.";
        return this.msg;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
}
