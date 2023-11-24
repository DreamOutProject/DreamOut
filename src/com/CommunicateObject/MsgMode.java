package com.CommunicateObject;

public class MsgMode extends ObjectMsg{
    private int currentMode;
    public MsgMode(int mode){this.currentMode = mode;}
    @Override
    public void setMsgMode(int mode) {this.currentMode = mode;}
    @Override
    public int getMsgMode() {return this.currentMode;}
}
