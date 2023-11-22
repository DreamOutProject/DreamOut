package com.Login;

import com.CommunicateObject.ObjectMsg;
import com.CommunicateObject.StringMsg;
import com.CommunicateObject.User;
import com.Room.WaitRoom;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;


public class StartLogin extends JPanel{
    private JButton b_login, b_signup;
    private Font f1;
    private JLabel a;
    private JTextField email;
    private JPasswordField pw;
    private JLabel Jlabel,lab1,lab2;
    private User user;
    private Socket socket;

    private ObjectInputStream in;
    private ObjectOutputStream out;


    StartLogin(JFrame frame) throws IOException {


        buildGUI();
        setLayout(null);
        setBackground(new Color(115,52,211));
        setSize(1280,720);
        setVisible(true);
        connectToServer();
        addActionListener(frame);



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
                    try {
                        // user 정보 서버로 보내기
                        out.writeObject(new User(
                                new StringMsg("회원가입"),
                                Integer.parseInt(email.getText()),
                                Integer.parseInt(String.valueOf(pw.getPassword()))
                        ));

                        // 서버에서 user 정보 읽어오기
                        ObjectMsg response = (ObjectMsg) in.readObject();

                        if (response instanceof StringMsg && response.getMsg().equals("회원가입 성공")) {
                            JOptionPane.showMessageDialog(
                                    StartLogin.this,
                                    "회원 가입 성공!"
                            );
                        } else {
                            JOptionPane.showMessageDialog(
                                    StartLogin.this,
                                    "이미 존재하는 이메일입니다"
                            );
                        }
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
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
                else {
                    try {
                        // 서버에게 로그인 보내기
                        out.writeObject(new User(
                                new StringMsg("로그인"),
                                Integer.parseInt(email.getText()),
                                Integer.parseInt(String.valueOf(pw.getPassword()))
                        ));

                        // 서버에서 로그인 정보 가져오기
                        ObjectMsg response = (ObjectMsg) in.readObject();

                        if (response instanceof StringMsg responseMsg) {
                            if (responseMsg.getMsg().equals("아이디 없음")) {
                                JOptionPane.showMessageDialog(
                                        StartLogin.this,
                                        "존재하지 않는 이메일입니다"
                                );
                            } else if (responseMsg.getMsg().equals("비밀번호 미입력")) {
                                JOptionPane.showMessageDialog(
                                        StartLogin.this,
                                        "비밀번호를 입력하세요"
                                );
                            } else if (responseMsg.getMsg().equals("비밀번호 불일치")) {
                                JOptionPane.showMessageDialog(
                                        StartLogin.this,
                                        "비밀번호가 일치하지 않습니다"
                                );
                            } else if (responseMsg.getMsg().equals("로그인 성공")) {
                                setVisible(false);
                                mainFrame.getContentPane().removeAll();
                                mainFrame.add(new WaitRoom(mainFrame));
                                mainFrame.revalidate();
                                mainFrame.repaint();
                            }
                        }
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
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
    //소켓 연결
    public void connectToServer() throws IOException {
        socket = new Socket();
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

    }

}