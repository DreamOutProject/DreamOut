package com.Game;
import com.Main.Main;
import com.Room.GameEndRoom;
import com.Room.GameStartRoom;

import javax.swing.*;
import java.awt.*;

public class TimeThread extends Thread {

    int insertTime=5;
    int time=insertTime;
    int round = 1;
    private final JProgressBar timeBar;
    private final JLabel client;
    private final JFrame MainFrame;
    public TimeThread(JFrame f, JProgressBar timeBar, JLabel client) {
        this.timeBar=timeBar;
        this.client = client;
        MainFrame = f;
    }

    @Override
    public void run() {
        super.run();
        while(round!=3){
            try {
                while(time!=-1){
                    timeBar.setValue(time);
                    timeBar.setString(time+"초");
                    Thread.sleep(1000);//1초 기다리고
                    time--;
                }
            } catch (InterruptedException ignored) {}

            round++;
            insertTime-=2;
            client.setText("Round : "+round+"/8");
            time=insertTime--;
            client.repaint();
            client.revalidate();

        }
        Main.Transition_go(new GameEndRoom(MainFrame));
    }
}
