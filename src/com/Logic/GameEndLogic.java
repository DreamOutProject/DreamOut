package com.Logic;

import com.CommunicateObject.MOD;
import com.CommunicateObject.Picture;
import com.GUI.GameEnd;
import com.Main.Main;

import javax.swing.*;
import java.io.IOException;
import java.util.Vector;

import static com.CommunicateObject.MODE.*;

public class GameEndLogic extends Thread{
    public Main main;
    public Vector<Picture>allData;//모든 사진 데이터
    public GameEnd GameEnd;
    public GameEndLogic(Main main, GameEnd GameEnd,Vector<Picture>D){
        this.main = main;
        this.GameEnd = GameEnd;
        allData = D;
        //모든 사진 데이터 다운 받았음.
    }
    public void testing(){
        GameEnd.showData();
    }

    @Override
    public void run() {
        super.run();
        try{
            MOD outMsg = new MOD(GAME_END);
            main.MainOutput.writeObject(outMsg);
        } catch (IOException e) {
            System.out.println("게임 끝났다고 메세지를 보내지 못했습니다.");
        }
        try{
            MOD receive;
            while((receive = (MOD)main.MainInput.readObject()).getMOD() != SUCCESSED){
                allData.add((Picture) receive);
            }
        } catch (IOException e) {
            System.out.println("모든 사진 데이터를 제대로 읽지 못했습니다.");
        } catch (ClassNotFoundException e) {
            System.out.println("사진 데이터들을 제대로 캐스팅하지 못했습니다. ");
        }
        testing();
    }
}
