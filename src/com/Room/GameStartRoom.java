package com.Room;
import com.Game.Drawing;
import com.Game.Game;

import javax.swing.*;
import java.awt.*;

public class GameStartRoom extends RoomPanel {
    public GameStartRoom(JFrame f){
        int WIDTH = 900;
        //진행바
        JProgressBar timeBar = new JProgressBar(JProgressBar.HORIZONTAL,0, 100);
        timeBar.setFont(new Font("맑은 고딕",Font.BOLD,30));
        timeBar.setStringPainted(true);
        timeBar.setOpaque(true);
        timeBar.setBackground(Color.WHITE);
        timeBar.setBounds(210,40, WIDTH,50);
        timeBar.setValue(5);
        timeBar.setString(5+"초");
        timeBar.setForeground(Color.GRAY);




        //client
        JLabel client = new JLabel("Round : 1/8");
        client.setFont(new Font("Courier", Font.BOLD,11));
        client.setOpaque(true);
        client.setBackground(Color.lightGray);
        client.setBounds(1030,110,80,50);

        //display
        JLabel t_display = new JLabel(new ImageIcon("testing.jpg")){
            @Override
            public void paintComponent(Graphics t) {
                super.paintComponent(t);
                if(Game.point!=null)
                    for(int i=2;i<Game.point.size();i++){
                        if(Game.point.get(i)==null)continue;
                        else if(Game.point.get(i-1)==null)continue;
                        t.drawLine(Game.point.get(i).x,Game.point.get(i).y,Game.point.get(i-1).x,Game.point.get(i-1).y);
                    }
            }
        };
        Drawing temp = new Drawing(t_display,client);
        t_display.setBounds(210,110,WIDTH,450);
        t_display.addMouseListener(temp);
        t_display.addMouseMotionListener(temp);
        t_display.setOpaque(true);
        t_display.setBackground(Color.white);


        //글자를 입력할 수 있고 없고.
        JTextField t_input = new JTextField();
        //t_input.setEnabled(false);
        t_input.setBounds(210,580,WIDTH,55);
        t_input.setHorizontalAlignment(JTextField.RIGHT);
        t_input.setFont(new Font("맑은 고딕",Font.BOLD,23));

        //L_pallete
        JLabel l_pallete = new JLabel("");
        l_pallete.setBounds(1150,110,100,450);
        l_pallete.setOpaque(true);
        l_pallete.setBackground(Color.white);


        add(timeBar);
        add(client);
        add(t_display);
        add(t_input);
        add(l_pallete);
        new Game(f,timeBar,client,t_display);
    }
}
