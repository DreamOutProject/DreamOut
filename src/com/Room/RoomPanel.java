package com.Room;

import com.Main.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class RoomPanel extends JPanel {
    public RoomPanel(){
        setLayout(null);//패널 자유롭게 셋팅하기
        setBackground(Colors.MainColor);

        JLabel Logo =new JLabel("DREAM OUT");
        Logo.setBounds(15,5,200,110);
        Logo.setFont(new Font("궁서체",Font.ITALIC,30));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(e.getPoint());
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                System.out.println(e.getPoint());
            }
        });
        add(Logo);//추가
    }
}
