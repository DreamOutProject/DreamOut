package com.Logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

public class Drawing extends JPanel implements MouseListener,MouseMotionListener{
    private Vector<MYDATA> Point;//여기에 있는 지점들을 통해 그림을 그리게 된다.
    //어느 지점에서 굵기나 색깔 지정
    private Color curColor;
    public Drawing(){
        Point = new Vector<>();//각 지점들 연결
        curColor = Color.black;//초기 칼라는 검은색이다.
        addMouseListener(this);//
        addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {//그림 호출됐을 시에
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
        for(int i=1;i<Point.size();i++){
            if(Point.get(i).getP() == null)continue;
            else if(Point.get(i-1).getP() == null)continue;
            else{
                //해당 색으로 색칠하기
                Color c = Point.get(i).getC();
                g2.setColor(c);
                Point nx = Point.get(i).getP();
                Point x = Point.get(i-1).getP();
                g2.drawLine(nx.x,nx.y,x.x,x.y);
            }
        }
    }

    public void setCurColor(Color c){this.curColor=c;}//색깔 지정해주기

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        Point.add(new MYDATA(null,null));
        Point.add(new MYDATA(e.getPoint(),this.curColor));
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point.add(new MYDATA(e.getPoint(),this.curColor));
        Point.add(new MYDATA(null,null));
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        Point.add(new MYDATA(e.getPoint(),this.curColor));
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    static class MYDATA{
        private Point p;
        private Color c;
        public MYDATA(Point p,Color c){
            this.p = p;
            this.c = c;
        }

        public Color getC() {return c;}

        public Point getP() {return p;}

        public void setC(Color c) {this.c = c;}

        public void setP(Point p) {this.p = p;}
    }
}
