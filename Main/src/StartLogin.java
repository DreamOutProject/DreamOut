package Main.src;
import java.awt.*;
import javax.swing.*;


public class StartLogin extends JPanel{
	private JButton b_login, b_signup;
	private Font f1;
	private JLabel a;
	private JTextField email,pw;
	private JLabel Jlabel,lab1,lab2;
	

	StartLogin() {

		buildGUI();
		setLayout(null);
		setBackground(new Color(115,52,211));
        setSize(1280,720);
        setVisible(true);
        

        
    }
	
	public void buildGUI(){
		add(createNamePanel());
		add(createLoginPanel());
        add(createButtonPanel());
       
    }
	
	
	public JPanel createNamePanel(){
        JPanel t = new JPanel();
        t.setBackground(new Color(115,52,211));
        f1 = new Font("바탕",Font.ITALIC, 60);
        a = new JLabel("DreamOut");
        a.setHorizontalAlignment(JLabel.CENTER);
        
        a.setFont(f1);
        t.setBounds(426, 40, 426,100);
        t.add(a);
        
        return t;
    }
	
	public JPanel createLoginPanel() {
		JPanel t = new JPanel(new GridLayout(3,2,10,10));
		t.setBackground(new Color(115,52,211));
		lab1 = new JLabel("이메일",Jlabel.RIGHT);
		lab1.setFont(new Font("바탕",Font.BOLD,20));
		email = new JTextField(20);
		
		lab2 = new JLabel("비밀번호",Jlabel.RIGHT);
		lab2.setFont(new Font("바탕",Font.BOLD,20));
		pw = new JTextField(20);
		
		t.setBounds(130,300,380,200);
		
		t.add(lab1);
		t.add(email);
		t.add(lab2);
		t.add(pw);
		
		
		return t;
	}
	
	public JPanel createButtonPanel() {
		JPanel t = new JPanel(new GridLayout(2,1,5,5));
		t.setBackground(new Color(115,52,211));
		
		t.setBounds(800,300,230,130);
		
		 b_signup = new JButton("회원가입");
		 b_login = new JButton("로그인");
		 
		 t.add(b_signup);
		 t.add(b_login);
		
		return t;
	}
	
}
