package com.CommunicateObject;

import java.io.Serializable;

public abstract class ObjectMsg implements Serializable {
    public static final int MSG_MODE = 0x01;//메세지만 보낼 때
    public static final int USER_MODE = 0x02;//유저만 보낼 떄 , 로그인 , 회원가입할 때 사용
    public static final int ROOM_MODE = 0x03;// 방접근할 때 사용
    public static final int PICTURE_MODE = 0x04;//사진 보낼 때
    public static final int ROOM_USER_MODE = 0x05;

    public abstract void setMsgMode(int mode);
    public abstract int getMsgMode();
}
