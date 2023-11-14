package Main.src;
import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;
/*
*  1. 현재 접속 인원 알려주기
*  2. 현재 시간
*  3.
* */

public class GameServer extends JFrame {
    private ServerSocket SS;//ServerSocket;
    private int Port;
    GameServer(int port) {
        try{
            SS=new ServerSocket(this.Port);
        }catch(IOException ignored){

        }

        this.Port = port;
        Date date = new Date();
        System.out.println(date);
    }
    public static void main(String[] args) {
        int port = 54321;
        new GameServer(port);
    }
}
