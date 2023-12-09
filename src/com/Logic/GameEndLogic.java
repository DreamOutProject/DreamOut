package com.Logic;

import com.CommunicateObject.MOD;
import com.CommunicateObject.Picture;
import com.GUI.GameEnd;
import com.Main.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import static com.CommunicateObject.MODE.*;

public class GameEndLogic extends Thread{
    public Main main;
    public JButton album;
    public Vector<Picture>allData;//모든 사진 데이터
    public GameEnd GameEnd;
    public GameEndLogic(Main main, GameEnd GameEnd, Vector<Picture>D, JButton album){
        this.main = main;
        this.GameEnd = GameEnd;
        allData = D;
        this.album = album;
        //모든 사진 데이터 다운 받았음.
    }
//    public void testing(){
//        album.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                album.setVisible(false);
//                GameEnd.showData();
//            }
//        });
//
//    }
//    public void ButtonEnable(){
//        if(main.room.getAdminId()!= main.ID.getId()){//현재 방의 정보가 다르다면 동적으로 화면 설정이 불가능하도록 만들기
//            album.setEnabled(false);
//        }
//    }
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
