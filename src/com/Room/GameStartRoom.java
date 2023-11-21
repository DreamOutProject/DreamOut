package com.Room;
import com.Game.Drawing;
import com.Game.Game;
import com.Main.*;
import javax.swing.*;
import java.awt.*;

public class GameStartRoom extends RoomPanel {
    public GameStartRoom(JFrame f){
        super();

        //진행바
        JProgressBar timeBar = new JProgressBar(0, 60);
        timeBar.setFont(new Font("맑은 고딕",Font.BOLD,30));
        timeBar.setOpaque(true);
        timeBar.setStringPainted(true);
        timeBar.setBackground(Color.GREEN);
        int WIDTH = 900;
        timeBar.setBounds(210,40, WIDTH,50);
        timeBar.setValue(60);
        timeBar.setString("60초");


        //client
        JLabel client = new JLabel("Round : 1/8");
        client.setFont(new Font("Courier", Font.BOLD,11));
        client.setOpaque(true);
        client.setBackground(Color.lightGray);
        client.setLocation(1030,110);
        client.setSize(80,50);

        //display
        JPanel t_display = new JPanel(){
            @Override
            public void paintComponent(Graphics t) {
                super.repaint();
                for(int i=2;i<Game.point.size();i++){
                    if(Game.point.get(i)==null)continue;
                    else if(Game.point.get(i-1)==null)continue;
                    t.drawLine(Game.point.get(i).x,Game.point.get(i).y,Game.point.get(i-1).x,Game.point.get(i-1).y);
                }
            }
        };
        Drawing temp = new Drawing(t_display,client);
        t_display.setLocation(210,110);
        t_display.setSize(WIDTH,450);
        t_display.addMouseListener(temp);
        t_display.addMouseMotionListener(temp);



        //글자를 입력할 수 있고 없고.
        JTextField t_input = new JTextField();
        //t_input.setEnabled(false);
        t_input.setSize(WIDTH,55);
        t_input.setLocation(210,580);
        t_input.setHorizontalAlignment(JTextField.RIGHT);
        t_input.setFont(new Font("맑은 고딕",Font.BOLD,23));

        //L_pallete
        JLabel l_pallete = new JLabel("");
        l_pallete.setLocation(1150,110);
        l_pallete.setSize(100,450);
        l_pallete.setOpaque(true);
        l_pallete.setBackground(Color.white);



        add(timeBar);
        add(client);
        add(t_display);
        add(t_input);
        add(l_pallete);

        new Game(f,timeBar,client);
    }
}
