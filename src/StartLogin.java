
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class StartLogin extends JPanel{
	private JButton b_login, b_signup;
	private Font f1;
	private JLabel a;
	private JTextField email;
	private JPasswordField pw;
	private JLabel Jlabel,lab1,lab2;
	private UserData user;

	StartLogin(JFrame frame) {

		user = new UserData();

		buildGUI();
		setLayout(null);
		setBackground(new Color(115,52,211));
        setSize(1280,720);
        setVisible(true);
        addActionListener(frame);



    }

	public UserData getUsers() {
		return user;
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
		pw = new JPasswordField(20);

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


	public void addActionListener(JFrame mainFrame) {

		b_signup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//정보가 비어있을 때
				if(isBlank()) {
					JOptionPane.showMessageDialog(
                            StartLogin.this,
                            "모든 정보를 입력하세요"
                    );
				}
				//모두 입력했을 때
				else {
					//email이 중복일 때
					if(user.isIdOverlab(email.getText())) {

						JOptionPane.showMessageDialog(
                                StartLogin.this,
                                "이미 존재하는 이메일입니다"
                        );
						email.requestFocus();
					}
					//가입 성공
					else {

						user.addUser(new User(
							email.getText(),
							String.valueOf(pw.getPassword())
								));
						JOptionPane.showMessageDialog(
                                StartLogin.this,
                                "회원 가입 성공!"
                        );
					}
				}

			}
		});

		b_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//아이디가 비었을 때
				if(email.getText().isEmpty()) {
					JOptionPane.showMessageDialog(
                            StartLogin.this,
                            "이메일을 입력하세요"
                    );

				}
				//아이디가 있을 때
				else if(user.contains(new User(email.getText()))) {
					//비밀번호가 비었을 때
					if(String.valueOf(pw.getPassword()).isEmpty()) {
						JOptionPane.showMessageDialog(
                                StartLogin.this,
                                "비밀번호를 입력하세요"
                        );
					}
					//비밀번호가 일치 X
					else if(!user.getUser(email.getText()).getpw().equals(String.valueOf(pw.getPassword()))) {
						JOptionPane.showMessageDialog(
                                StartLogin.this,
                                "비밀번호가 일치하지 않습니다"
                        );
					}
					//모두 입력 완료
					else {
						setVisible(false);
						mainFrame.getContentPane().removeAll();
						mainFrame.add(new WaitRoom());
						mainFrame.revalidate();
						mainFrame.repaint();
					}
				}
				//존재하지 않는 이메일일 때
				else {
					JOptionPane.showMessageDialog(
                            StartLogin.this,
                            "존재하지 않는 이메일입니다"
                    );
				}

			}
		});

	}

	public boolean isBlank() {
		boolean a = false;
		if(email.getText().isEmpty()) {
			email.requestFocus();
			return true;
		}
		if(String.valueOf(pw.getPassword()).isEmpty()) {
			pw.requestFocus();
			return true;
		}
		return a;
	}

}
