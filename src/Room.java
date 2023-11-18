import java.io.Serializable;
import java.util.Vector;

public class Room implements Serializable {
    private Integer roomId;//방 번호
    private Integer adminId;//방장 아이디
    private final Vector<User>users;//방 안에 있는 사람들
    private Integer roomSize;//방 최대 생성
    Room(Integer roomId, Integer adminId, Integer roomSize){
        users=new Vector<>();
        this.adminId = adminId;
        this.roomId = roomId;
        this.roomSize = roomSize;
    }
    Room(Integer roomId){this(roomId,null,null);}

    
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

    
    public boolean equals(Room obj) {
        return this.adminId.equals(obj.adminId) && this.roomId.equals(obj.roomId);
    }

    @Override
    public boolean equals(Object obj) {
        Room o = (Room)obj;
        return this.roomId.equals(o.getRoomId());
    }

    @Override
    public String toString() {
        return " adminId : " + adminId + " roomId : " + roomId;
    }
}
