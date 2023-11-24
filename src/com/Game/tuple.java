package com.Game;

import java.awt.*;

public class tuple {
    private Point point;
    private Color color;
    private int width;
    public tuple(Point point, Color color,int width){
        this.color =color;
        this.point = point;
        this.width = width;
    }
    public Point getPoint(){return this.point;}
    public Color getColor(){return this.color;}
    public int getWidth() {return width;}

    public void setColor(Color color) {this.color = color;}
    public void setPoint(Point point){this.point=point;}
    public void setWidth(int width) {this.width = width;}
}
