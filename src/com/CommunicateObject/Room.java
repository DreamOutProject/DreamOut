package com.CommunicateObject;

import java.util.Vector;

public class Room extends ObjectMsgDecorator {
    private Integer roomId;//방 번호
    private Integer adminId;//방장 아이디
    private final Vector<User>users;//방 안에 있는 사람들
    private Integer roomSize;//방 최대 생성

    public Room(ObjectMsg obj,Integer roomId , Integer adminId,Integer roomSize){
        super(obj);
        users = new Vector<>();
        this.roomId = roomId;
        this.adminId = adminId;
        this.roomSize = roomSize;
    }
    public int getAdminId() {return adminId;}
    public int getRoomId(){return roomId;}
    public Vector<User> getUsers(){return users;}
    public int getRoomSize(){return roomSize;}
    public boolean addUser(User u){//성공적으로 추가됐을 때는 true
        //아닐 때는 false를 던져줌
        if(users.size()==roomSize)return false;
        users.add(u);
        return true;
    }
}
