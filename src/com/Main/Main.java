package com.Main;

import com.CommunicateObject.*;
import com.CommunicateObject.User;
import com.Game.*;
import com.Login.StartLogin;
import com.Room.*;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;


public class Main {
    private static JFrame frame;
    protected static Socket s;//소켓
    public static ObjectOutputStream out;
    public static ObjectInputStream in;
    public static User my = null;//로그인 안 했을 때
    private static RoomPanel presentRoom;
    public static Room room = null;
    public static void Transition_go(RoomPanel panel){
        frame.getContentPane().removeAll();
        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
        presentRoom = panel;
    }
    static public JLabel NewLabel(String insertMsg,int size){//빈 라벨
        return NewLabel(insertMsg,size,new Color(202,190,224));
    }
    static public JLabel NewLabel(String insertMsg,int size,Color color){
        JLabel t = new JLabel(insertMsg);
        t.setFont(new Font("맑은 고딕",Font.BOLD,size));
        t.setHorizontalAlignment(JLabel.CENTER);
        t.setBackground(color);
        t.setOpaque(true);
        return t;
    }

    public static void test(){
        try {

            s = new Socket();
            InetSocketAddress IP = new InetSocketAddress("172.30.1.47",54321);
            s.connect(IP);
            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());
            //new reapaintThread().start();
        } catch (IOException ignored) {}
    }
    Main(){
        test();//테스트 서버 접속
        frame = new JFrame("DreamOut");
        frame.setSize(1280,720);
        Transition_go(new StartLogin(frame));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
    public static void main(String[] args) {new Main();}



    static class reapaintThread extends Thread{
        JFrame frame;
        Socket paintSocket;
        ObjectInputStream in;
        reapaintThread(){
            frame=Main.frame;
            try{
                paintSocket = new Socket("localhost",54321);
                in = new ObjectInputStream(paintSocket.getInputStream());
            }catch(IOException ignored){}
        }

        @Override
        public void run() {
            super.run();
            //해당하는 곳에서는 repaint명령이 떨어지면 계속 화면을 그려줘야 된다.
            while(true){
                try{
                    ObjectMsg msg =(ObjectMsg) in.readObject();
                    if(msg.getMsgMode() == ObjectMsg.REPAINT_MODE){//화면 다시 그려주기

                        presentRoom.repaint();
                        frame.revalidate();
                        frame.repaint();
                        System.out.println("화면을 다시 그렸습니다.");
                    }
                }catch (IOException | ClassNotFoundException e){
                    System.err.println("잘못된 데이터를 불러왔습니다.");
                    break;
                }
            }
        }
    }
}
