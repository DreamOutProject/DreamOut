package com.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Drawing extends MouseAdapter {
    private JLabel t_display;
    private JLabel client;
    public Drawing(JLabel d,JLabel temp){
        t_display = d;
        client = temp;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        Game.point.add(null);//널은 처음을 구분하기 위해
        Game.point.add(e.getPoint());
        t_display.repaint();
        client.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        Game.point.add(e.getPoint());
        t_display.repaint();
        client.repaint();
    }
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        Game.point.add(null);//널은 처음을 구분하기 위해
        Game.point.add(e.getPoint());
        t_display.repaint();
        client.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        client.repaint();
    }
}
