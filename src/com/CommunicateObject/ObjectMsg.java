package com.CommunicateObject;

import java.io.Serializable;

public abstract class ObjectMsg implements Serializable {
    public static final int MSG_MODE = 0x01;//메세지만 보낼 때
    public static final int USER_MODE = 0x02;//유저만 보낼 떄
    public static final int ROOM_MODE = 0x03;// 방 들어갈 때
    public static final int PICTURE_MODE = 0x04;//사진 데이터 주고 받을 때
    public static final int REPAINT_MODE = 0x05;//현재 그림을 다시 그리라고 보내줄 때
    public static final int LOGIN_MODE = 0x06; //로그인 모드
    public static final int REGISTER_MODE = 0x07;//회원가입 모드
    public static final int ROOM_MAKE_MODE = 0x08;//방만들기 모드
    public static final int ENDING_START_MODE = 0x09;//방장이 엔딩화면 시작을 눌렀을 때
    public static final int ENDING_NEXT_MODE = 0x10;//다음으로 넘기고
    public static final int ENDING_PREV_MODE = 0x11;//엔딩 이전 앨범
    public static final int SUCESSED = 0x12; // 성공
    public static final int FAILED = 0x13; // 실패
    public static final int ROOM_VIEW = 0x14;//방 보이기
    public static final int GAME_START_MODE = 0x15;//게임 시작
    public static final int TEMP = 0xA;

    public abstract void setMsgMode(int mode);
    public abstract int getMsgMode();
}
