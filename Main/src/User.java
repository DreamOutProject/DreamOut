

import javax.sql.RowSetInternal;

public class User {
	private String id;
	private String pw;
	private String info;
	
	public User(String id, String pw){
		setid(id);
		setpw(pw);
		
	}
	public User(String id) {
		setid(id);
	}

	public void setid(String id) {
		this.id =id;
	}
	public String getid() {
		return id;
	}
	
	public void setpw(String pw) {
		this.pw =pw;
	}
	public String getpw() {
		return pw;
	}
	
	 @Override
	public boolean equals(Object obj) {
	     if(obj == null || !(obj instanceof User)) {
	         return false;
	     }
	     User temp = (User)obj;

	     return id.equals(temp.getid());
	}
	
	
}
