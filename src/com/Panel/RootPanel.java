package com.Panel;

import com.Main.Main;
import com.Ui.Colors;
import com.Ui.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class RootPanel extends JPanel {//모든 패널들이 가져야 하는 것들

    public RootPanel(){
        setLayout(null);//레이블 널이어야한다.
        setSize(Main.WIDTH,Main.HEIGHT);//크기
        setOpaque(true);
//        setBackground(Colors.BackColor);//배경 색깔
        repaint();
        testing();
    }
    public void paint(Graphics g){
        g.drawImage(Images.BackImage,0,0,null);
    }



    public void testing(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("x좌표 : " +e.getX());
                System.out.println("y좌표 : " +e.getY()+"\n");
            }
        });
    }
}
