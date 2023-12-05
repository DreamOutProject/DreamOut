package com.Panel;

import com.Main.Main;
import com.Ui.Colors;

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

        JPanel title = title();
        JPanel leftSide = leftSide();
        JPanel rightSide = rightSide();

        add(title);
        add(leftSide);
        add(rightSide);

        new LoginLogic(main,login,register,id,pw);
    }

    public JPanel title(){
            JPanel t = new JPanel();
            t.setBounds(300,50,600,200);
            t.setBackground(Colors.BackColor);
            Font font = new Font("바탕",Font.ITALIC,100);
            JLabel name = new JLabel("Dream Out");
            name.setForeground(Colors.ForeColor);
            name.setFont(font);
            t.add(name);
            return t;
    }

    public JPanel leftSide(){
        JPanel t = new JPanel(new GridLayout(0,2));
        t.setBounds(200,365,220,100);
        t.setBackground(Colors.BackColor);
        JLabel ids = new JLabel("ID : ");
        JLabel pws = new JLabel("PW : ");

        Font id_font = new Font("바탕",Font.ITALIC,20);
        Font pw_font = new Font("바탕",Font.ITALIC,20);

        id = new JTextField();
        pw = new JTextField();

        ids.setFont(id_font);
        pws.setFont(pw_font);
        ids.setForeground(Colors.ForeColor);
        pws.setForeground(Colors.ForeColor);

        t.add(ids);
        t.add(id);
        t.add(pws);
        t.add(pw);
        return t;
    }
    public JPanel rightSide(){
        JPanel t = new JPanel(new GridLayout(0,1));
        t.setBounds(850,365,220,100);
        register = new JButton("회원가입");
        login = new JButton("로그인");

        t.add(register);
        t.add(login);
        return t;
    }

}
