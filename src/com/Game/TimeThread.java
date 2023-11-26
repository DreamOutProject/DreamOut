package com.Game;
import com.CommunicateObject.MsgMode;
import com.CommunicateObject.ObjectMsg;
import com.CommunicateObject.Picture;
import com.Main.Main;
import com.Room.GameEndRoom;
import com.Room.GameStartRoom;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class TimeThread extends Thread {

    int insertTime=60;
    int time=insertTime;
    int round = 1;
    private final JProgressBar timeBar;
    private final JLabel client;
    private final JFrame MainFrame;
    private final JLabel savePanel;
    public TimeThread(JFrame f, JProgressBar timeBar, JLabel client,JLabel p) {
        this.timeBar=timeBar;
        this.client = client;
        MainFrame = f;
        savePanel = p;
    }

    @Override
    public void run() {
        super.run();
        int Roomsize = Main.room.getUsers().size();
        while(round!=Roomsize){
            try {
                while(time!=-1){//시간 설정
                    timeBar.setValue(time);
                    timeBar.setString(time+"초");
                    Thread.sleep(1000);//1초 기다리고
                    time--;
                }
            } catch (InterruptedException ignored) {}

            saveToPicture(savePanel);//현재까지 그린 것 저장

            Game.point.clear();//다 비우고
            MainFrame.revalidate();//재설정
            MainFrame.repaint(); // 메인 화면 다시 그려주기
            int currentRound = round;
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    //해당하는 곳에서는 그림을 제대로 저장해주기 위해 쓰레드를 만들고 그림이 다 만들어지면 스레드를 종료한다.
                    String path = new File("").getAbsolutePath()+"src/com/screenshot/" + currentRound + ".png";
                    JLabel temp = new JLabel(new ImageIcon(path));
                    Game.picture.add(temp);

                    //Main.out.writeObject();
                }
            };



            round++; //다음 라운드로 가기
            insertTime-=15; //시간 다시 넣기
            time=insertTime;

            client.setText("Round : "+round+"/" + Roomsize); //시간 설정
            client.repaint();
            client.revalidate();


        }
        // ObejctMsg temp = new Picture(new MsgMode(ObjectMsg.PICTURE_MODE),Game.picture));
        // out.writeObject(temp); //서버로 사진 파일 보내기
        Main.Transition_go(new GameEndRoom(MainFrame));// 화면 전환해주기
    }

    private void saveToPicture(JLabel savePanel) {
        BufferedImage image = new BufferedImage(savePanel.getWidth(), savePanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.createGraphics();
        savePanel.print(g);
        g.dispose();
        try{
            String path = new File("").getAbsolutePath()+"src/com/screenshot";
            File filePath = new File(path);
            if(!filePath.exists())filePath.mkdir();
            String alpha="/"+round + ".png";
            filePath = new File(path+alpha);
            ImageIO.write(image,"png", filePath);
        }catch(Exception ignored){}
    }
}
