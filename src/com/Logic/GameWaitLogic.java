package com.Logic;

import com.CommunicateObject.MOD;
import com.CommunicateObject.Room;
import com.GUI.WaitRoom;
import com.Main.Main;
import com.Ui.Colors;

import javax.swing.*;
import java.io.IOException;

import static com.CommunicateObject.MODE.*;

public class GameWaitLogic {
    public Main main;
    public JComboBox<String> subject;
    public int NumberGame=1;
    public JPanel leftSide;
    public JPanel rightSide;
    public JPanel Center;
    JButton secondGame;
    JButton firstGame;
    public GameWaitLogic(Main main, JPanel left, JPanel right, JPanel center, JButton f, JButton s, JComboBox<String> subject){
        this.main = main;
        this.leftSide = left;
        this.rightSide = right;
        this.Center = center;
        this.firstGame =f;
        this.secondGame = s;
        this.subject = subject;
    }
    public void ButtonEnable(){
        if(main.room.getAdminId()!= main.ID.getId()){//현재 방의 정보가 다르다면 동적으로 화면 설정이 불가능하도록 만들기
            firstGame.setEnabled(false);
            secondGame.setEnabled(false);
            subject.setEnabled(false);
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
        main.room.setGamecategory(1);
    }
    public void secondGameChoice(){
        secondGame.setBackground(Colors.GameMouseHover);
        firstGame.setBackground(Colors.GameMouserOut);
        main.room.setGamecategory(2);
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

    public void back() {
        MOD outMsg = new MOD(RETURN_WAITROOM);
        main.isrepaint=false;
        try {
            main.MainOutput.writeObject(outMsg);//로비로 돌아갑니다.
            MOD receive= (MOD)main.MainInput.readObject();
            if(receive.getMOD()==SUCCESSED){
                main.transition(new WaitRoom(main));
            }
        } catch (IOException ex) {
            System.out.println("게임 시작메세지를 제대로 보내지 못했습니다.");
        } catch (ClassNotFoundException e) {
            System.out.println("캐스팅을 할 수 없습니다.");
        }
        main.isrepaint=true;
    }

    public void messge(Object item) {
        MOD outMsg = new MOD(CATEGORY_ONE);
        switch (item.toString()){
            case "포켓몬"->{
                outMsg.setMod(CATEGORY_TWO);
            }
            case "스포츠"->{
                outMsg.setMod(CATEGORY_THREE);
            }
            case "영화"->{
                outMsg.setMod(CATEGORY_FOUR);
            }
            case "자유 주제"->{
                outMsg.setMod(CATEGORY_FIVE);
            }
        }
        try {
            main.MainOutput.writeObject(outMsg);
        } catch (IOException e) {
            System.out.println("카테고리 메세지 못 보냄");
        }
    }
    public void settingSubject(String data){
        subject.setSelectedItem(data);
    }
    public void settingSubject(int data){
        switch (data){
            case 1->{
                settingSubject("일상 생활");
            }
            case 2->{
                settingSubject("포켓몬");
            }
            case 3->{
                settingSubject("스포츠");
            }
            case 4->{
                settingSubject("영화");
            }
            case 5->{
                settingSubject("자유 주제");
            }
        }
    }
}
