package com.Game;
import com.Main.Main;
import com.Room.GameEndRoom;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class TimeThread extends Thread {

    int insertTime=5;
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
        while(round!=3){
            try {
                while(time!=-1){//시간 설정
                    timeBar.setValue(time);
                    timeBar.setString(time+"초");
                    Thread.sleep(1000);//1초 기다리고
                    time--;
                }
            } catch (InterruptedException ignored) {}
            Game.point.clear();//다 비우고
            MainFrame.revalidate();//재설정
            MainFrame.repaint(); // 메인 화면 다시 그려주기
            saveToPicture(savePanel);

            round++; //다음 라운드로 가기
            insertTime-=2; //시간 다시 넣기
            time=insertTime;

            client.setText("Round : "+round+"/8"); //시간 설정
            client.repaint();
            client.revalidate();
        }
        Main.Transition_go(new GameEndRoom(MainFrame));
    }

    private void saveToPicture(JLabel savePanel) {
        BufferedImage image = new BufferedImage(savePanel.getWidth(), savePanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.createGraphics();
        savePanel.print(g);
        g.dispose();
        try{
            ImageIO.write(image,"png", new File("test.png"));
            JOptionPane.showMessageDialog(MainFrame, "파일저장 성공");
        }catch(Exception ignored){}

    }
}
