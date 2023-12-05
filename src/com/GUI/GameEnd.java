package com.GUI;

import com.CommunicateObject.MOD;
import com.CommunicateObject.Room;
import com.Main.Main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static com.CommunicateObject.MODE.ROOM_VIEW;
import static com.CommunicateObject.MODE.SUCCESSED;

public class GameEnd extends RootPanel{
    public Main main;
    public JPanel Center;
    public JPanel leftSide;
    public GameEnd(Main main){
        this.main = main;
        readData();//서버에서 해당 방 데이터 읽어오기
        Center = new JPanel(new BorderLayout());
        Center.setBounds(100,55,1100,550);

        leftSide = leftSide();
        Center.add(leftSide,BorderLayout.WEST);


        add(Center);
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
    public JPanel leftSide() {
        JPanel t= new JPanel(new GridLayout(0,1));
        JLabel l_player = new JLabel("플레이어 인원 " + main.room.getParticipant().size() + " / " + main.room.getRoomSize());
        t.add(l_player);
        int total = main.room.getParticipant().size();
        for(Integer id:main.room.getParticipant()){
            JLabel player = new JLabel("ID : " + id);
            t.add(player);
            total--;
        }
        for(int i=0;i<total;i++){
            JLabel player = new JLabel("비어있는 자리");
            t.add(player);
        }
        return t;
    }
}
