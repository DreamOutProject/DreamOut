package com.Game;

import javax.swing.*;
import java.util.Vector;

/*
*  1. 그림 그리는 것 해결
*  2. 입력 받기
*  3. 시간초 흐르기
*  4.
* */
public class Game{//
    private final JFrame mainFrame;
    public static Vector<tuple>point = null;
    protected static Vector<JLabel>picture;
    public Game(JFrame f,JProgressBar timeBar,JLabel client,JLabel p){
        mainFrame = f;//메인 프레임 넣어주기
        point = new Vector<>();
        picture = new Vector<>();
        //시간 흐르기
        new TimeThread(f,timeBar,client,p).start();
    }
}
