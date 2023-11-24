package com.Room;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

import com.CommunicateObject.*;
import com.Game.Ending;
import com.Main.*;
import com.Ui.Colors;
import com.Ui.Fonts;

public class GameEndRoom extends RoomPanel {
    private JScrollPane playerScroll;//플레이어 스크롤
    private JScrollPane displayScroll;//그림 스크롤
    private JPanel t_display;//그림 그린 것들이 뜨는 곳
    private Room room=new Room(null,12,12,12);
    private Vector<User>temp;
    public GameEndRoom(JFrame f){
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

        //오른쪽 패널
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBounds(550,160,660,400);

        //시작 버튼
        JButton startButton = new JButton("앨범 보기");
        startButton.setFont(Fonts.ShowFont);
        startButton.setOpaque(true);
        startButton.setBackground(Colors.GameMouseHover);
        startButton.setBounds(780,250,150,50);


        //사진들
        t_display = new JPanel(new GridLayout(0,2));
        t_display.setBackground(Color.WHITE);

        //스크린 스크롤
        displayScroll = new JScrollPane(t_display,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        displayScroll.setBounds(550,160,660,400);
        displayScroll.setBackground(Color.WHITE);

        rightPanel.add(displayScroll,BorderLayout.CENTER);

        JPanel south = new JPanel(new GridLayout(0,2));
        if(isAdmin()){//현재 접속한 아이디와 같을 때
            String word = "방장의 선택을 기다립니다. :)";
            JLabel insertSouth = new JLabel(word);
            insertSouth.setFont(Fonts.ShowFont);
            insertSouth.setHorizontalAlignment(JLabel.CENTER);
            south.add(insertSouth);
        }else{
            JButton prevButton = new JButton("이전 앨범으로 돌아가기");
            JButton nextButton = new JButton("다음 앨범으로 넘기기");//이전 버튼과 다음 버튼 둘 다 만들고 이전은 비활성화 하기

            nextButton.setFont(Fonts.ShowFont);
            prevButton.setFont(Fonts.ShowFont);

            prevButton.setEnabled(false);

            south.add(prevButton);
            south.add(nextButton);
        }
        rightPanel.add(south,BorderLayout.SOUTH);

        add(startButton);
        add(playerScroll);
        add(rightPanel);


        new Ending(f,room,t_display);
    }
    public boolean isAdmin(){
        return Main.my.getId() != room.getAdminId();
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
