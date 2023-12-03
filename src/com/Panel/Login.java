package com.Panel;

import com.Main.Main;

import javax.swing.*;
import java.awt.*;

public class Login extends RootPanel{
    public Main main;
    public JButton login;
    public JButton register;
    public JTextField id;
    public JTextField pw;
    public Login(Main main){
        //회원 가입 시
        this.main = main;

        JPanel leftSide = leftSide();
        JPanel rightSide = rightSide();

        add(leftSide);
        add(rightSide);

        new LoginLogic(main,login,register,id,pw);
    }
    public JPanel leftSide(){
        JPanel t = new JPanel(new GridLayout(0,2));
        t.setBounds(200,265,220,100);
        JLabel ids = new JLabel("ID : ");
        JLabel pws = new JLabel("PW : ");

        id = new JTextField();
        pw = new JTextField();

        t.add(ids);
        t.add(id);
        t.add(pws);
        t.add(pw);
        return t;
    }
    public JPanel rightSide(){
        JPanel t = new JPanel(new GridLayout(0,1));
        t.setBounds(850,232,220,100);
        register = new JButton("회원가입");
        login = new JButton("로그인");

        t.add(register);
        t.add(login);
        return t;
    }

}
