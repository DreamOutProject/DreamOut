public class ObjectMsg {
    private User user;
    private String msg;
    ObjectMsg(User u){
        new ObjectMsg(u,null);//U만 보낼 떄
    }
    ObjectMsg(String msg){
        new ObjectMsg(null,msg);
    }
    ObjectMsg(User u, String msg){
        this.user = u;
        this.msg = msg;
    }
    public String getMsg(){return this.msg;}
    public User getUser(){return this.user;}

}
