package com.CommunicateObject;

public class IntMsg extends ObjectMsgDecorator{
    private Integer number;
    public IntMsg(ObjectMsg obj,int number) {
        super(obj);
        this.number = number;
    }
    public void setNumber(int number){
        this.number = number;
    }
    public Integer getNumber(){return this.number;}
}
