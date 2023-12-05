package com.Logic;

import com.Main.Main;
import com.GUI.GameEnd;
import com.GUI.GamingRoom;

import javax.swing.*;

public class GamingLogic extends Thread{
    public Main main;
    public JProgressBar bar;
    public int TIME=10;//현재 시간
    public boolean wait=true;//
    public GamingLogic(Main main, JProgressBar bar){
        this.main = main;//메인 갖고 오기
        this.bar = bar;//bar 설정하기
        //init();
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
        while(TIME!=-1){
            try{
                setTime(TIME);
                sleep(1000);//1초마다 시간이 줄어듦
            } catch (InterruptedException e) {
                System.out.println("시간이 흐르지 않습니다.");
            }
            TIME--;
        }
        //그렸던 거 사진 저장
        savePanel();
        sendMessage();//그렸던 사진 서버로 보내주기
        while(wait){
            try{
                sleep(500);//계속 물어보면 과부하 될 까봐 0.5초마다 물어본다.
            } catch (InterruptedException e) {
                System.out.println("제대로 다른 사람들을 기다리지 못 하고 있습니다.");
            }
        }
        main.transition(new GameEnd(main));
    }

    public void savePanel(){//그림 파일 다운로드
        if(main.presentPanel instanceof GamingRoom temp){

        }
    }
    public void sendMessage(){//해당하는 그림파일 서버로 보내기

    }
    public void setTime(int Time){this.bar.setValue(Time);this.bar.setString(Time+"초");}
    public void nextRound(){
        this.wait  = false;//false로 둠으로써 다음으로 넘억기

    }
}
