package com.CommunicateObject;

import java.io.Serializable;
import java.util.Vector;

public class Room extends MOD implements Serializable {
    private int roomId;
    private int adminId;
    private int roomSize;
    private Vector<Integer>participant;
    public Room(int roomId,int roomSize){//방 정보만 받는다.
        this.roomId=roomId;
        this.adminId=roomId;
        this.roomSize = roomSize;
        participant = new Vector<>();
    }

    public Room(Room t){
        this.roomId = t.getRoomId();
        this.adminId = t.getAdminId();
        this.roomSize = t.roomSize;
        this.participant = new Vector<>(t.getParticipant());
    }

    public void setRoomId(int roomId) {this.roomId = roomId;}
    public void setAdminId(int adminId){this.adminId=adminId;}
    public void setParticipant(Vector<Integer>participant){this.participant = new Vector<>(participant);}

    public int getRoomId(){return this.roomId;}
    public int getAdminId(){return this.adminId;}
    public Vector<Integer> getParticipant(){return this.participant;}
    public int getRoomSize(){return this.roomSize;}
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
    public String toString() {
        return "방 아이디는 : " + roomId + "방장은 : " + adminId +"입니다.";
    }
}
