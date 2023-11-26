package com.Login;

import com.CommunicateObject.MsgMode;
import com.CommunicateObject.ObjectMsg;
import com.CommunicateObject.StringMsg;
import com.CommunicateObject.User;
import com.Main.Main;
import com.Room.GameRoom;
import com.Room.GameStartRoom;
import com.Room.RoomPanel;
import com.Room.WaitRoom;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Objects;
import javax.swing.*;


public class StartLogin extends RoomPanel {
    private JButton b_login, b_signup;
    private Font f1;
    private JLabel a;
    private JTextField email,pw;
    private JLabel Jlabel,lab1,lab2;
    private User user;




    public StartLogin(JFrame frame) {
        buildGUI();
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


    public void addActionListener(JFrame f) {


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
                        Main.out.writeObject(new User(
                                new MsgMode(ObjectMsg.REGISTER_MODE),
                                Integer.parseInt(email.getText()),
                                Integer.parseInt(pw.getText())
                                ));


                        // 서버에서 user 정보 읽어오기
                        ObjectMsg response = (ObjectMsg) Main.in.readObject();

                        if (response.getMsgMode() == ObjectMsg.SUCESSED) {
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
//                    //메인에 있는 예시의 유저로 로그인
//                    ObjectMsg t = new MsgMode(ObjectMsg.MSG_MODE);
//                    if ((Integer.parseInt(email.getText()) == Main.my.getId())&& (Integer.parseInt(pw.getText()) == Main.my.getId())) {
//                        Main.Transition_go(new WaitRoom(f));
//                    }



                    //서버에서 아이디, 비번 불러 줄 경우 아래 코드 활성화
                    try {
                        Main.my = new User(null,Integer.parseInt(email.getText()),Integer.parseInt(pw.getText()));
                        // 서버에게 로그인 보내기
                        Main.out.writeObject(new User(
                                new MsgMode(ObjectMsg.LOGIN_MODE),
                                Integer.parseInt(email.getText()),
                                Integer.parseInt(pw.getText())
                        ));

                        // 서버에서 로그인 정보 가져오기
                         ObjectMsg response = (ObjectMsg) Main.in.readObject();

                        if(Objects.equals(response.getMsgMode(), ObjectMsg.SUCESSED)){
                            Main.Transition_go(new WaitRoom(f));
                        }
                        else if(Objects.equals(response.getMsgMode(), ObjectMsg.FAILED)){
                            JOptionPane.showMessageDialog(StartLogin.this, "로그인에 실패했습니다");
                        }

                    } catch (IOException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
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
        if(String.valueOf(pw.getText()).isEmpty()) {
            pw.requestFocus();
            return true;
        }
        return a;
    }


}