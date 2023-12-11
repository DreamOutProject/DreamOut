package com.Logic;

import com.CommunicateObject.MOD;
import com.CommunicateObject.Picture;
import com.GUI.GameEnd;
import com.Main.Main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Vector;

import static com.CommunicateObject.MODE.*;

public class GameEndLogic extends Thread{
    public Main main;
    public Vector<Picture>allData;//모든 사진 데이터
    public GameEnd GameEnd;
    public int index = 0;//처음에 인덱스는 0으로 설정한다.
    public GameEndLogic(Main main, GameEnd GameEnd,Vector<Picture>D){
        this.main = main;
        this.GameEnd = GameEnd;
        allData = D;
        //모든 사진 데이터 다운 받았음.
    }
    public void albumStart(){
        //시작 버튼을 눌렀을 때 현재 인덱스에 맞는 앨범을 화면에 띄워준다.
        GameEnd.showData(index);
        init();
        GameEnd.rightSide.remove(GameEnd.albumStart);//이 버튼 삭제해야 된다.
        GameEnd.rightSide.add(GameEnd.South, BorderLayout.SOUTH);
        GameEnd.rightSide.repaint();
        GameEnd.rightSide.revalidate();
    }
    public void init(){
        if(index == allData.size()-1){
            GameEnd.South.remove(GameEnd.next);//지우기
            GameEnd.South.add(GameEnd.returnWait);//돌아가는 것을 붙이기
            GameEnd.South.revalidate();
            GameEnd.South.repaint();
        }
        GameEnd.prev.setEnabled(false);
    }
    public void nextPage(){
        if(this.index!=allData.size()-1){
            this.index++;
        }else{
            GameEnd.South.remove(GameEnd.next);//지우기
            GameEnd.South.add(GameEnd.returnWait);//돌아가는 것을 붙이기
            GameEnd.South.revalidate();
            GameEnd.South.repaint();
        }
        GameEnd.showData(this.index);
    }
    public void prevPage(){
        if(this.index!=0){
            this.index--;
        }else{
            GameEnd.prev.setEnabled(false);
        }
        GameEnd.South.remove(GameEnd.returnWait);
        GameEnd.South.add(GameEnd.next);
        GameEnd.South.revalidate();
        GameEnd.South.repaint();
        GameEnd.showData(this.index);
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
            int j=0;
            while((receive = (MOD)main.MainInput.readObject()).getMOD() != SUCCESSED){
                Picture data = (Picture)receive;
                for(int i=0;i<data.getFiles().size();i++){
                    System.out.println(j+"의 "+i+"번째가 : " + data.getFiles().get(i)+"입니다.");
                }
                allData.add(data);
            }
        } catch (IOException e) {
            System.out.println("모든 사진 데이터를 제대로 읽지 못했습니다.");
        } catch (ClassNotFoundException e) {
            System.out.println("사진 데이터들을 제대로 캐스팅하지 못했습니다. ");
        }
    }

    public void returnWait() {
        //서버로 모든 아이들이 돌아간다고 말해두기
        try{
            MOD outMsg = new MOD(RETURN_GAMEROOM);
            main.MainOutput.writeObject(outMsg);
        } catch (IOException e) {
            System.out.println("방으로 돌아가라고 말 못함 ㅋㅋ");
        }
    }
}
