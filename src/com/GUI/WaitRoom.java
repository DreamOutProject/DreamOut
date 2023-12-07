package com.GUI;

import com.CommunicateObject.Room;
import com.Logic.WaitLogic;
import com.Main.Main;
import com.Ui.Colors;
import com.Ui.Fonts;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class WaitRoom extends RootPanel {
    public Main main;
    public JPanel roomshowPanel;
    public JScrollPane scroll;
    private WaitLogic logic;
    public WaitRoom(Main main){//서버로부터 현재 있는 방들 달라고 하자.
        this.main = main;
        roomshowPanel = new JPanel(new GridLayout(0,2,20,20));
        roomshowPanel.setBackground(Colors.PURPLE);

        scroll = new JScrollPane(roomshowPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        scroll.setBounds(100,55,1100,550);
        scroll.setBackground(Colors.PURPLE);

        JButton makeRoom = new JButton("방 만들기");
        makeRoom.setFont(Fonts.makeRoom);
        makeRoom.setBackground(Colors.MainColor);
        makeRoom.setForeground(Colors.WHITE);

        makeRoom.setBounds(1100,20,100,30);


        logic = new WaitLogic(main,makeRoom,roomshowPanel);
        logic.readRoomdata();

        makeRoom.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                logic.roomMake();
            }
        });


        add(scroll);
        add(makeRoom);
    }
    public void repainTing(){
        roomshowPanel.removeAll();//전부 지우고
        logic.readRoomdata();//데이터 다시 읽어오고 띄우고
        main.presentPanel.revalidate();//다시 그리기 해도 됨
        main.presentPanel.repaint();//다시 그리기
    }
}
