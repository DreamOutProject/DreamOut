package com.Panel;

import com.Main.Main;
import com.Ui.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//게임방 UI
public class GamingRoom extends RootPanel{//어떤 게임인지 설정해줘야 됨.
    public Main main;
    public static final int MTIME = 0;
    public static final int XTIME = 60;
    public JProgressBar timeBar;
    public JPanel drawPanel;
    public JPanel pallete;
    public Drawing D = new Drawing();
    public GamingRoom(Main main){
        this.main = main;
        //1. 맨 위에 시간 지나는 progressbar
        timeBar = new JProgressBar(MTIME,XTIME);//어느 게임에 따라 시간 초가 달리 흐르나.?
        timeBar.setStringPainted(true);//몇 초로 나오게끔 설정 가능
        setTime(XTIME);
        timeBar.setBounds(150,60,950,40);//위치 설정

        //가운데 그림 그리는 패널
        drawPanel = D;//해당 패널은 추가적인 부수 기능이 들어감
        drawPanel.setBounds(150,150,950,450);
        //팔렛트 패널 하나 만들기
        pallete = setPallete();//선 굵기는 일단 신경 쓰지 말자
        pallete.setBounds(1150,150,100,450);

        add(timeBar);
        add(drawPanel);//일단 화면 보기
        add(pallete);
    }

    public JPanel setPallete() {
        JPanel t= new JPanel(new GridLayout(0,2));
        //해당하는 색깔들 넣어주기
        JLabel RED = setLabel(Color.RED);
        JLabel RED_ORANGE = setLabel(Colors.REDBETWEENORANGE);
        JLabel ORANGE = setLabel(Color.orange);
        JLabel YELLOW = setLabel(Color.yellow);
        JLabel LIGHTGREEN = setLabel(Colors.LIGHTGREEN);
        JLabel GREEN = setLabel(Color.GREEN);
        JLabel LIGHTBLUE = setLabel(Colors.LIGHTBLUE);
        JLabel BLUE = setLabel(Color.blue);
        JLabel INDIGO = setLabel(Colors.INDIGO);
        JLabel PURPLE = setLabel(Colors.PURPLE);
        JLabel BLACK =setLabel(Color.black);

        t.add(RED);
        t.add(RED_ORANGE);
        t.add(ORANGE);
        t.add(LIGHTGREEN);
        t.add(GREEN);
        t.add(LIGHTBLUE);
        t.add(BLUE);
        t.add(INDIGO);
        t.add(PURPLE);
        t.add(BLACK);
        return t;
    }
    public JLabel setLabel(Color color){
        JLabel t = new JLabel();
        t.setOpaque(true);t.setBackground(color);
        t.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                //마우스를 누르게 된다면
                //색깔이 변해야 된다.
                //해당 드로윙의 칼라가 변하면 된다.
                D.setCurColor(color);//현재 칼라로 설정
            }
        });
        return t;
    }
    public void setTime(int Time){timeBar.setValue(Time);timeBar.setString(Time+"초");}

    //시간 초가 흐르는 것도 만들어야 함.
}
