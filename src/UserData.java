import java.util.ArrayList;
import java.util.Vector;

public class UserData {

	private Vector<User> user;
	
	public UserData() {
		user = new Vector<User>();
	}
	//사용자 추가
	public void addUser(User us) {
		user.add(us);
		
	}
	//사용자 정보 가져오기
	public User getUser(String id) {
		return user.get(user.indexOf(new User(id)));
	}
	//아이디 중복 확인
	public boolean isIdOverlab(String id) {
		return user.contains(new User(id));
	}
	//회원 유무 판별
	public boolean contains(User us) {
		return user.contains(us);
	}
}
