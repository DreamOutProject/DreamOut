package com.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Drawing extends MouseAdapter {
    private JLabel t_display;
    private JLabel client;
    private Color currentColor;
    private int width;
    public Drawing(JLabel d,JLabel temp){
        t_display = d;
        client = temp;
        width =10;//고정으로 10
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        Game.point.add(null);//널은 처음을 구분하기 위해
        Game.point.add(new tuple(e.getPoint(),currentColor,width));
        t_display.repaint();
        client.repaint();
    }
    public void setCurrentColor(Color color){this.currentColor = color;}
    public void setWidth(int width){this.width = width;}

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        Game.point.add(new tuple(e.getPoint(),currentColor,width));
        t_display.repaint();
        client.repaint();
    }
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        Game.point.add(null);//널은 처음을 구분하기 위해
        Game.point.add(new tuple(e.getPoint(),currentColor,width));
        t_display.repaint();
        client.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        client.repaint();
    }
}
