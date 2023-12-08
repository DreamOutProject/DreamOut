package com.Logic;

import com.CommunicateObject.MOD;
import com.CommunicateObject.Room;
import com.GUI.GamingRoom;
import com.GUI.WaitRoom;
import com.Main.Main;
import com.Ui.Colors;

import javax.swing.*;
import java.io.IOException;

import static com.CommunicateObject.MODE.*;

public class GameWaitLogic {
    public Main main;
    public int NumberGame=1;
    public JPanel leftSide;
    public JPanel rightSide;
    public JPanel Center;
    JButton secondGame;
    JButton firstGame;
    public GameWaitLogic(Main main,JPanel left,JPanel right,JPanel center,JButton f,JButton s){
        this.main = main;
        this.leftSide = left;
        this.rightSide = right;
        this.Center = center;
        this.firstGame =f;
        this.secondGame = s;
    }
    public void ButtonEnable(){
        if(main.room.getAdminId()!= main.ID.getId()){//현재 방의 정보가 다르다면 동적으로 화면 설정이 불가능하도록 만들기
            firstGame.setEnabled(false);
            secondGame.setEnabled(false);
        }
    }
    public void readData(){
        try{
            MOD mod = new Room(main.room);//방 보기
            mod.setMod(ROOM_VIEW);
            main.MainOutput.writeObject(mod);
            MOD receive = (MOD)main.MainInput.readObject();
            if(receive.getMOD() == SUCCESSED){
                main.room = new Room((Room)receive);
            }else throw new ClassNotFoundException();
        } catch (IOException e) {
            System.out.println("대기방 데이터를 제대로 보내지 못했습니다. ");
        } catch (ClassNotFoundException e) {
            System.out.println("대기방 데이터를 제대로 받지 못했습니다.");
            main.transition(new WaitRoom(main));//굳.
        }
    }
    public void firstGameChoice(){
        firstGame.setBackground(Colors.GameMouseHover);
        secondGame.setBackground(Colors.GameMouserOut);
        NumberGame = 1;//1번 게임
    }
    public void secondGameChoice(){
        secondGame.setBackground(Colors.GameMouseHover);
        firstGame.setBackground(Colors.GameMouserOut);
        NumberGame = 2;//1번 게임
    }
    public void Message(int num){
        MOD outMsg = new MOD(FAILED);
        switch (num){
            case 1->{
                outMsg.setMod(GAME_ONE_CHOICE);
            }
            case 2->{
                outMsg.setMod(GAME_TWO_CHOICE);
            }
        }
        try {
            main.MainOutput.writeObject(outMsg);
        }catch (IOException ex) {
            System.out.println("2번을 선택했다고 서버로 보내지 못 했습니다.");
        }
    }
    public void GameStartmessage(){
        MOD outMsg = new MOD(GAME_START_MODE);
        main.isrepaint=false;
        try {
            main.MainOutput.writeObject(outMsg);//같은 방 아이들에게 데이터 파일 나눠주세요
        } catch (IOException ex) {
            System.out.println("게임 시작메세지를 제대로 보내지 못했습니다.");
        }
        main.isrepaint=true;
    }
}
