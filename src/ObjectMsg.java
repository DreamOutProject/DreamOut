import java.io.Serializable;

public class ObjectMsg implements Serializable {
    private Room room;
    private User user;
    private String msg;
    private Integer idx;
    ObjectMsg(User u){this(u,null,null,null);//U만 보낼 떄
    }
    ObjectMsg(String msg){this(null,msg,null,null);}
    ObjectMsg(User u, String msg){this(u,msg,null,null);}
    ObjectMsg(User u, String msg, Room room,Integer idx){
        this.user = u;
        this.msg = msg;
        this.room =room;
        this.idx = idx;
    }
    ObjectMsg(User u,String msg,Integer idx){this(u,msg,null,idx);}
    ObjectMsg(User u,String msg,Room room){this(u,msg,room,null);}
    ObjectMsg(Room room){this(null,null,room,null);}
    ObjectMsg(String msg, Room room){this(null,msg,room,null);}
    ObjectMsg(Integer idx){this(null,null,null,idx);}
    ObjectMsg(String msg, Integer idx){this(null,msg,null,idx);}
    public String getMsg(){
        if(msg == null)return "메세지 없음";
        return this.msg;
    }
    public User getUser(){return this.user;}
    public Room getRoom(){return this.room;}
    public Integer getIdx(){return this.idx;}
    @Override
    public String toString() {
        return "Room : " + room + " User : " + user + " Msg : " + msg;
    }
}
