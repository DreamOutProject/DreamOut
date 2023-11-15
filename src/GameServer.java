import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImageFilter;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
/*
*  1. 현재 접속 인원 알려주기
*  2. 현재 시간
*  3.
* */

public class GameServer extends JFrame {
    private ServerSocket SS;//ServerSocket;
    private final int Port;
    private final Vector<ClientManage> clients;
    private Thread temp;
    GameServer(int port) {//현재 프레임에 서버 시작 버튼 만들기
        clients = new Vector<>();
        setSize(500,300);
        JButton b_start = new JButton("서버켜기");
        JButton b_stop = new JButton("서버끄기");
        b_start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //마우스 클릭 됐을 때 서버 시작
                StartServer();
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                StartServer();
            }
        });
        b_stop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                StopServer();
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                StopServer();
            }
        });
        this.Port = port;
        Date date = new Date();



        add(b_start, BorderLayout.WEST);
        add(b_stop,BorderLayout.EAST);
        setVisible(true);
    }

    public void StopServer() {
        try {
            temp=null;//ss가 닫혔으니 더이상 그만 받아.
            Thread.sleep(500);
            if(SS!=null) SS.close();
            SS = null;
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void StartServer() {
        try{
            if(SS==null){
                SS=new ServerSocket(this.Port);
                JOptionPane.showMessageDialog(this, "서버가 시작되었습니다.");
                temp = new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        while (true) {
                            try {
                                new ClientManage(SS.accept()).start();//접속자 받기
                            } catch (IOException ignored) {break;}
                        }
                        JOptionPane.showMessageDialog(GameServer.this, "서버가 닫혔습니다.", "", JOptionPane.ERROR_MESSAGE);
                    }
                };
                temp.start();
            }
        }catch(IOException ignored){}
    }

    public static void main(String[] args) {
        int port = 54321;
        new GameServer(port);
    }

    class ClientManage extends Thread{
        PrintWriter out;
        BufferedReader in;
        String msg;
        Socket socket;
        ClientManage(Socket sc){
            socket = sc;
            clients.add(this);//현재 클래스를 계속 추가해준다
        }
        @Override
        public void run() {
            super.run();
            try {
                out = new PrintWriter(socket.getOutputStream(),true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.println("접속허가");//접속을 허가하는 것
            } catch (IOException ignored) {}
        }
        //시간 알려주기
        public String getTime(){
            return new Date().toString();
        }
    }
}
