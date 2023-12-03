package com.Main;



import javax.swing.*;

import com.CommunicateObject.*;
import com.Panel.Login;
import com.Panel.RootPanel;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main{
    public JFrame MainFrame;
    public Socket socket;
    public Room room =null;//현재 접속한 방

    public User ID=null;//내가 접속한 아이디
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public ObjectInputStream MainInput;
    public ObjectOutputStream MainOutput;

    public static final int port = 54321;
    public static final String IP = "172.30.1.5";
    public RootPanel presentPanel=null;
    public Thread repaint = null;
    public boolean isrepaint=false;
    public void transition(RootPanel nextPanel){
        presentPanel = nextPanel;//현재 패널을 갈아끼우기
        MainFrame.getContentPane().removeAll();//현재 화면정보 날리기
        MainFrame.setContentPane(presentPanel);//이 패널로 화면 정보 저장
    }
    public void serverConnect(){
        try {
            socket = new Socket(IP,port);
            MainOutput = new ObjectOutputStream(socket.getOutputStream());
            MainInput  = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("서버에 연결에 실패했습니다.");
        }
    }
    Main(){
        serverConnect();
        MainFrame = new JFrame("DreamOut");
        MainFrame.setSize(WIDTH,HEIGHT);
        MainFrame.setLayout(new GridLayout(0,1));//하나만 화면에 띄울 수 있도록 만든다;.
        transition(new Login(this));
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setResizable(false);
        MainFrame.setVisible(true);//화면에 보여주기
    }
    public static void main(String[] args) {new Main();}

}