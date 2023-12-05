package com.Main;

import com.CommunicateObject.MOD;
import com.CommunicateObject.User;
import com.GUI.GameWaitRoom;
import com.GUI.GamingRoom;
import com.GUI.WaitRoom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import static com.CommunicateObject.MODE.*;

public class repaintThread extends Thread{
    public ObjectInputStream InputStream;
    public ObjectOutputStream OutputStream;
    public Socket s;
    public Main main;
    public MOD receive;
    public repaintThread(Main main){
        this.main = main;
        try{
            s=new Socket(main.IP,main.port);
            OutputStream = new ObjectOutputStream(s.getOutputStream());
            InputStream = new ObjectInputStream(s.getInputStream());
            notifyThread();
        } catch (UnknownHostException e) {
            System.out.println("제대로 된 IP가 아닙니다.");
        } catch (IOException e) {
            System.out.println("비이상적으로 연결이 안 됐습니다.");
        }
    }
    public void notifyThread(){
        MOD outMsg = new User(main.ID);
        try {
            outMsg.setMod(REPAINT_NOTIFY);
            OutputStream.writeObject(outMsg);
            MOD receive = (MOD)InputStream.readObject();
            if(receive.getMOD() == SUCCESSED) System.out.println("클라이언트가 제대로 붙었습니다.");
            else throw new IOException();
        } catch (IOException e) {
            System.out.println("성공적으로 알리지 못했습니다.");
        } catch (ClassNotFoundException e) {
            System.out.println("알리는 과정에서 제대로 읽지 못했습니다.");
        }
    }
    @Override
    public void run() {
        super.run();
        while(true){
            try {
                receive = (MOD)InputStream.readObject();
                while(!main.isrepaint);//여길 벗어나면 현재 패널에 그림을 그려도 되는 것임 그럼 읽어놓은 데이터를 화면에 띄워주자.
                System.out.println(receive + "가 들어왔습니다.");
                if(main.presentPanel instanceof GameWaitRoom){
                    if(receive.getMOD() == REPAINT_MODE) {
                        System.out.println("게임방 다시그리시오.");
                        ((GameWaitRoom) main.presentPanel).reapainting();
                    }else if(receive.getMOD() == GAME_START_MODE){
                        System.out.println("읽었습니다.");
                        while(!main.isrepaint); // 다시그리기가 false일 때까지 돌다가
                        main.isrepaint = false;
                        main.transition(new GamingRoom(main));//다음 화면으로 넘겨주기
                        main.isrepaint = true;
                    }else if(receive.getMOD() == GAME_ONE_CHOICE){
                        ((GameWaitRoom) main.presentPanel).logic.firstGameChoice();
                    }else if(receive.getMOD() == GAME_TWO_CHOICE){
                        ((GameWaitRoom) main.presentPanel).logic.secondGameChoice();
                    }

                }else if(main.presentPanel instanceof  WaitRoom){
                    if(receive.getMOD() == REPAINT_MODE) {//그림 그리라고 하면
                        System.out.println("대기방 다시그리시오.");
                        ((WaitRoom) main.presentPanel).repainTing();
                    }
                }else if(main.presentPanel instanceof  GamingRoom){//게임 시작했음.
                    if(receive.getMOD() == NEXT_ROUND){
                        ((GamingRoom) main.presentPanel).logic.nextRound();
                    }
                }
            } catch (IOException e) {
                System.out.println("제대로 데이터를 읽지 못했습니다.");
                break;
            } catch (ClassNotFoundException e) {
                System.out.println("캐스팅이 제대로 안 됐습니다.");
                break;
            }
        }
    }
}
