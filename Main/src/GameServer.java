import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
/*
*  1. 현재 접속 인원 알려주기
*  2. 현재 시간
*  3.
* */

public class GameServer extends JFrame {
    private ServerSocket SS;//ServerSocket;
    private int Port;
    GameServer(int port) {//현재 프레임에 서버 시작 버튼 만들기
        JButton b_start = new JButton("서버 시작하기");
        b_start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //마우스 클릭 됐을 때 서버 시작
                StartServer();
            }
        });

        this.Port = port;
        Date date = new Date();
        System.out.println(date);
    }

    public void StartServer() {
        try{
            SS=new ServerSocket(this.Port);
            System.out.println("서버가 시작되었습니다.");
        }catch(IOException ignored){}
        while(true){
            try {
                Socket s = SS.accept();
                OutputStream out = s.getOutputStream();//해당 소켓으로 입력들 뿌려줘야 됨.
                BufferedWriter o = (O utputStvreamWriter)out;
            } catch (IOException e) {
                System.out.println("비이상적으로 서버가 시작되지 않았습니다.");
                break;
            }
        }
    }

    public static void main(String[] args) {
        int port = 54321;
        new GameServer(port);
    }
}
