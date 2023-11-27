package com.CommunicateObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class MsgMode extends ObjectMsg  {
    private int currentMode;
    private static final Map<Integer,String> convert = new LinkedHashMap<>(){
        {
            put(1,"메세지를 보내중입니다.");
            put(2,"유저를 보냈습니다.");
            put(3,"방에 들어가려고 하고 있습니다.");
            put(4,"사진 데이터를 주고 받고 있습니다.");
            put(5,"그림을 다시 그려야 합니다.");
            put(6,"로그인하려고 하고 있습니다.");
            put(7,"회원가입하려고 하고 있습니다.");
            put(8,"방을 만들려고 하고 있습니다.");
            put(9,"앨범을 시작하려고 합니다.");
            put(10,"다음 앨범으로 넘깁니다.");
            put(11,"이전 앨범으로 돌아갑니다.");
            put(12,"접근에 성공하였습니다.");
            put(13,"접근에 실패하였습니다.");
            put(14,"게임을 시작하려고 합니다.");
            put(15,"방정보를 확인하려고 합니다.");
        }
    }
            ;
    public MsgMode(int mode){
        this.currentMode = mode;
    }
    @Override
    public void setMsgMode(int mode) {this.currentMode = mode;}
    @Override
    public int getMsgMode() {return this.currentMode;}


    @Override
    public String toString() {
        return convert.getOrDefault(currentMode,"예기치 못한 에러가 발생하였습니다.");
    }
    public static String ToString(int currentMode){
        return convert.getOrDefault(currentMode,"예기치 못한 에러가 발생하였습니다.");
    }

}
