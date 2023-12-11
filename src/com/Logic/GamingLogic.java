package com.Logic;

import com.CommunicateObject.MOD;
import com.CommunicateObject.Picture;
import com.GUI.GameEnd;
import com.GUI.GamingRoom;
import com.Main.Main;
import com.Ui.Fonts;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static com.CommunicateObject.MODE.*;

public class GamingLogic extends Thread{

    public Main main;
    public JProgressBar bar;
    public int TIME=15;//현재 시간
    public int initTime =15;
    public boolean wait=true;//
    public JPanel draw;
    public int round;
    public Picture Data;
    public int totalRound;
    public int randomAlpha;
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
        if(main.room.getGamecategory()==1)((GamingRoom)main.presentPanel).prevPicture.setVisible(false);
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
            if(main.room.getGamecategory()==2){//라운드에 따라 그림 활성화 비활성화 하기
                if(round%2==0){
                    ((GamingRoom)main.presentPanel).drawPanel.setVisible(false);
                    ((GamingRoom)main.presentPanel).word.setVisible(true);
                }
                else {
                    ((GamingRoom)main.presentPanel).drawPanel.setVisible(true);
                    ((GamingRoom)main.presentPanel).word.setVisible(false);
                }
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
            clearPanel();
            if(this.totalRound==round)break;
            nextRound();
        }
        try{
            MOD outMsg = new MOD(TEMP);
            main.MainOutput.writeObject(outMsg);
            MOD receive = (MOD)main.MainInput.readObject();
            System.out.println("여기가 안 왔어요");
            if(receive.getMOD() == SUCCESSED){
                main.transition(new GameEnd(main));
            }
        } catch (IOException e) {
            System.out.println("제대로 게임 끝 데이터를 보내지 못 했습니다.");
        } catch (ClassNotFoundException e) {
            System.out.println("다음 페이지로 넘어가는 데이터 못 받음");
        }
    }

    public void clearPanel() {
        if(main.presentPanel instanceof GamingRoom){
            ((GamingRoom) main.presentPanel).D.ClearData();//데이터 초기화 해주기
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
            randomAlpha = (int) ((Math.random()*10000)%1000);
            String alpha="/"+curRound + randomAlpha  +".png";//랜덤으로 형성해야된다.
            filePath = new File(path+alpha);
            ImageIO.write(image,"png", filePath);
            ImageIcon t = new ImageIcon(image);
            Image s = t.getImage();
            t = new ImageIcon(s.getScaledInstance(150,150,Image.SCALE_SMOOTH));
            JLabel pictureLabel = new JLabel(t);
            if(main.room.getGamecategory()==2){
                if(round%2==0){
                    pictureLabel = new JLabel(((GamingRoom)main.presentPanel).word.getText());
                    pictureLabel.setHorizontalAlignment(JLabel.CENTER);
                }
            }
            pictureLabel.setFont(Fonts.ShowFont);
            Data.setPicture(pictureLabel,curRound-1);//그림 파일 셋팅
        }catch(Exception ignored){}
    }
    public void sendMessage(int cur){//해당하는 그림파일 서버로 보내기
        String path = new File("").getAbsolutePath()+"/src/com/screenshot/"+cur+ randomAlpha+".png";
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
        initTime-=7;
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

    public void Pictureshow() {
        //이전 그림이 있었다면 그걸 볼 수 있게끔 만들어야 함.
        if(round!=1){//1라운드가 아니라면 볼 수 있음
            JFrame sub  = new JFrame("사진만 띄워주기");
            sub.setLayout(new GridLayout(0,1));
            JLabel insertimg = Data.getFiles().get(round-2);
            sub.add(insertimg);
            sub.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    super.windowClosed(e);
                    sub.dispose();
                }
            });
            sub.setSize(500,500);
            sub.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null,"이전 그림이 없습니다.","실패",JOptionPane.ERROR_MESSAGE);
        }
    }
}
