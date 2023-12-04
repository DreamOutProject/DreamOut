package com.Panel;

import com.Main.Main;

import javax.swing.*;

public class Game extends Thread{
    public Main main;
    public JProgressBar bar;
    public Game(Main main, JProgressBar bar){
        this.main = main;//메인 갖고 오기
        this.bar = bar;//bar 설정하기

        //게임의 초기 설정이 중요
    }

    @Override
    public void run() {
        super.run();
        //시간초가 흐르게 되고
        //1. 라운드가 흐른다.
        //2. 라운드가 끝나면 사진을 저장한다. 서버로 보낸다.
        //3. 새로운 라운드를 시작한다.
        //4. 해당하는 라운드가 끝나면 모든 사람들에게 게임이 끝났다고 말한다. 해당 방에 게임이
        //5.

    }
}
