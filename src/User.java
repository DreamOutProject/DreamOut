import java.io.Serializable;

public class User implements Serializable {
	private Integer id;
	private Integer pw;
	//나중에 업데이트 사항 private String nick;

	User(Integer id, Integer pw){
		this.id = id;
		this.pw = pw;
	}
	public int getId(){return this.id;}
	public int getPw(){return this.pw;}
	public boolean equals(User us) {//같다는 것 재정의
        return this.id.equals(us.getId()) && this.pw.equals(us.getPw());
    }
	public boolean IsPw(User u) {return this.pw.equals(u.getPw());}
	@Override
	public String toString() {return " id : " + id +  "pw : "+ pw;}
}
