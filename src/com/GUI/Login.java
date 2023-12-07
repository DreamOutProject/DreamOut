package com.GUI;

import com.Logic.LoginLogic;
import com.Main.Main;
import com.Ui.Colors;
import com.Ui.Fonts;

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
            t.setOpaque(false);
            JLabel name = new JLabel("Dream Out");
            name.setForeground(Colors.BackColor);
            name.setFont(Fonts.title_font);
            t.add(name);
            return t;
    }

    public JPanel leftSide(){
        JPanel t = new JPanel(new GridLayout(0,2,0,5));
        t.setBounds(200,365,220,100);
        t.setBackground(Colors.BackColor);
        t.setOpaque(false);
        JLabel ids = new JLabel("ID : ");
        JLabel pws = new JLabel("PW : ");


        id = new JTextField();
        pw = new JTextField();

        ids.setFont(Fonts.font);
        pws.setFont(Fonts.font);
        ids.setForeground(Colors.BackColor);
        pws.setForeground(Colors.BackColor);
        id.setFont(Fonts.font);
        pw.setFont(Fonts.font);

        t.add(ids);
        t.add(id);
        t.add(pws);
        t.add(pw);
        return t;
    }
    public JPanel rightSide(){
        JPanel t = new JPanel(new GridLayout(0,1,0,5));
        t.setBounds(850,365,220,100);
        t.setOpaque(false);
        register = new JButton("회원가입");
        register.setFont(Fonts.ShowFont);
        register.setBackground(Colors.PURPLE);
        register.setOpaque(true);
        login = new JButton("로그인");
        login.setFont(Fonts.ShowFont);
        login.setBackground(Colors.PURPLE);
        login.setOpaque(true);

        t.add(register);
        t.add(login);
        return t;
    }

}
