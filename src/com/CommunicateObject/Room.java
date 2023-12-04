package com.CommunicateObject;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

public class Room extends MOD implements Serializable {
    private int roomId;
    private int adminId;
    private int roomSize;
    private Vector<Integer>participant;
    private boolean isInto;
    public Room(int roomId,int roomSize){//방 정보만 받는다.
        this.roomId=roomId;
        this.adminId=roomId;
        this.roomSize = roomSize;
        isInto=true;//처음에는 들어갈 수 있음
        participant = new Vector<>();
    }

    public Room(Room t){
        this.roomId = t.getRoomId();
        this.adminId = t.getAdminId();
        this.roomSize = t.roomSize;
        this.isInto = t.isInto;
        this.participant = new Vector<>(t.getParticipant());
    }

    public void setRoomId(int roomId) {this.roomId = roomId;}
    public void setAdminId(int adminId){this.adminId=adminId;}
    public void setParticipant(Vector<Integer>participant){this.participant = new Vector<>(participant);}
    public void setInto(boolean status){this.isInto = status;}//해당 상태에 따라 들어갈 수 있는지 판단
    public boolean getInto(){return this.isInto;}//보내주기
    public int getRoomId(){return this.roomId;}
    public int getAdminId(){return this.adminId;}
    public int getRoomSize(){return this.roomSize;}
    public Vector<Integer> getParticipant(){return this.participant;}
    public boolean addUser(User u){
        int id = u.getId();
        for(Integer ID : participant){
            if(ID == id)return false;
        }
        participant.add(id);
        return true;
    }
    public boolean removeUser(User u){
        int id = u.getId();
        for(Integer ID : participant){
            if(ID == id){
                participant.remove(ID);
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Room r){
            return this.roomId == r.getRoomId() && this.adminId == r.getAdminId();
        }
        return false;
    }

    @Override
    public int hashCode() {//해시코드 생성!
        return Objects.hash(roomId, adminId, roomSize, participant);
    }

    @Override
    public String toString() {
        return "방 아이디는 : " + roomId + "방장은 : " + adminId +"입니다.";
    }
}
