package com.Room;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

import com.CommunicateObject.*;
import com.Main.*;
public class GameEndRoom extends RoomPanel {
    private JScrollPane playerScroll;//플레이어 스크롤
    private JScrollPane displayScroll;//그림 스크롤
    private JPanel t_display;//그림 그린 것들이 뜨는 곳
    private Room room=new Room(null,12,12,12);
    private Vector<User>temp;
    public GameEndRoom(JFrame f){
        super();
        temp = room.getUsers();
        temp.add(new User(null,12,12));
        temp.add(new User(null,123,123));
        temp.add(new User(null,1234,12));
        temp.add(new User(null,12322,123));
        temp.add(new User(null,122234,12));
        temp.add(new User(null,12234324,12));
        temp.add(new User(null,123234234,123));
        temp.add(new User(null,12323232,123));
        //스크롤
        playerScroll = new JScrollPane(createPlayerPanel(),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        playerScroll.setBounds(250,160,300,400);

        //사진들
        t_display = new JPanel(new GridLayout(0,2));

        t_display.setBackground(Color.GREEN);
        //스크린 스크롤
        displayScroll = new JScrollPane(t_display,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        displayScroll.setBounds(550,160,660,400);
        displayScroll.setBackground(Color.WHITE);

        add(playerScroll);
        add(displayScroll);
    }
    public JPanel createPlayerPanel(){
        JPanel t = new JPanel(new GridLayout(0,1,0,5));//0을 입력하면 제한없이 받는 거임.
        //플레이어는 아래로 계속 뜨게끔 만들 거임.
        t.setBackground(Colors.BackColor);
        t.setPreferredSize(new Dimension(300,400));

        JLabel player = Main.NewLabel("플레이어",23);
        player.setForeground(new Color(191,179,215));
        player.setBackground(new Color(77,37,148));
        t.add(player);


        Vector<User> temps = room.getUsers();
        for (int i=0;i<temps.size();i++){
            JLabel temp = Main.NewLabel(temps.get(i).getId()+"",18);
            t.add(temp);
        }
        return t;
    }

}
