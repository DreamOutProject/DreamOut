package com.Logic;

import com.CommunicateObject.MOD;
import com.CommunicateObject.Picture;
import com.GUI.GameEnd;
import com.Main.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.CommunicateObject.MODE.*;

public class GamingLogic extends Thread{

    public Main main;
    public JProgressBar bar;
    public int TIME=3;//현재 시간
    public int initTime =3;
    public boolean wait=true;//
    public JPanel draw;
    public int round;
    public Picture Data;
    public int totalRound;
    public GamingLogic(Main main, JProgressBar bar,JPanel draw){
        this.main = main;//메인 갖고 오기
        this.bar = bar;//bar 설정하기
        this.draw = draw;
        round=1;
        totalRound = main.room.getParticipant().size();
    }
    @Override
    public void run() {
        super.run();
        //1. 서버로 그림 정보 달라고 하자.
        //2. 해당 시간 동안 게임 진행
        //3. 게임이 종료되면 그린 사진 파일 저장하기
        //4. 저장된 그림 파일 서버로 보내기
        //5. 라운드가 끝나지 않았다면 다시 1번부터 반복
        while(true){
            try{
                MOD outMsg = new MOD(PICTURE_INFO);//사진 정보 주세요
                main.MainOutput.writeObject(outMsg);
                Data = (Picture) main.MainInput.readObject();//그림 파일 갖고 오고
            } catch (IOException e) {
                System.out.println("데이터를 제대로 읽지 못했습니다.");
            } catch (ClassNotFoundException e) {
                System.out.println("캐스팅 에러가 났습니다.");
            }
            while(TIME!=-1){
                try{
                    setTime(TIME);
                    sleep(1000);//1초마다 시간이 줄어듦
                } catch (InterruptedException e) {
                    System.out.println("시간이 흐르지 않습니다.");
                }
                TIME--;
            }
            savePanel(round);
            sendMessage(round);//그렸던 사진 서버로 보내주기
            waiting();
            if(this.totalRound==round)break;
            nextRound();
        }
        nextRound();
        try{
            MOD outMsg = new MOD(TEMP);
            main.MainOutput.writeObject(outMsg);
            MOD receive = (MOD)main.MainInput.readObject();
            if(receive.getMOD() == SUCCESSED){
                main.transition(new GameEnd(main));
            }
        } catch (IOException e) {
            System.out.println("제대로 게임 끝 데이터를 보내지 못 했습니다.");
        } catch (ClassNotFoundException e) {
            System.out.println("다음 페이지로 넘어가는 데이터 못 받음");
        }
    }

    private void waiting() {
        try{
            MOD outMsg = new MOD(WAITING);
            main.MainOutput.writeObject(outMsg);
        } catch (IOException e) {
            System.out.println("기다린다 메세지 제대로 못 보냄");
        }
        try{
            MOD receive = (MOD)main.MainInput.readObject();
            if(receive.getMOD() == SUCCESSED){
                System.out.println("모든 사람들이 다 기다리기 성공");
            }
        } catch (IOException e) {
            System.out.println("기다리기 끝 메세지를 못 읽음");
        } catch (ClassNotFoundException e) {
            System.out.println("캐스팅 불가요  ㅋㅋ");
        }
    }

    public void savePanel(int curRound){//그림 파일 다운로드
        BufferedImage image = new BufferedImage(draw.getWidth(), draw.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.createGraphics();
        draw.print(g);
        g.dispose();
        try{
            String path = new File("").getAbsolutePath()+"/src/com/screenshot";
            File filePath = new File(path);
            if(!filePath.exists())filePath.mkdir();
            String alpha="/"+curRound + main.socket.getPort()+".png";
            filePath = new File(path+alpha);
            ImageIO.write(image,"png", filePath);
            ImageIcon t = new ImageIcon(image);
            JLabel pictureLabel = new JLabel(t);
            Data.setPicture(pictureLabel,curRound-1);//그림 파일 셋팅
        }catch(Exception ignored){}
    }
    public void sendMessage(int cur){//해당하는 그림파일 서버로 보내기
        String path = new File("").getAbsolutePath()+"/src/com/screenshot/"+cur+ main.socket.getPort()+".png";
        File filePath = new File(path);
        if(!filePath.exists())return;//없으면 일단 하질 말자
        try {
            MOD outMsg = new Picture(Data);
            outMsg.setMod(PICTURE_MODE);//그림 데이터 다시 보내기
            main.MainOutput.writeObject(outMsg);//서버로 데이터 보내기
            Data=null;
        } catch (IOException e) {
            System.out.println("그림 사진을 서버로 제대로 보내지 못 했습니다.");
        }
    }
    public void setTime(int Time){this.bar.setValue(Time);this.bar.setString(Time+"초");}
    public void nextRound(){
        round++;//라운드 다음
        initTime-=1;
        TIME=initTime;
        if(main.room.getAdminId()==main.ID.getId()){//서버만 다음 라운드라고 말하기
            try{
                MOD outMsg = new MOD(NEXT_ROUND);//다음 라운드
                main.MainOutput.writeObject(outMsg);
            } catch (IOException e) {
                System.out.println("해당하는 다음 라운드로 넘어가는 메세지를 못 보냈습니다.");
            }
        }
    }
}
