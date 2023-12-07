package com.Logic;

import com.CommunicateObject.MOD;
import com.CommunicateObject.Room;
import com.GUI.GameWaitRoom;
import com.GUI.WaitRoom;
import com.Main.Main;
import com.Ui.Colors;
import com.Ui.Fonts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static com.CommunicateObject.MODE.*;

public class WaitLogic {
    public JButton makeRoom;
    public JPanel roomshowPanel;
    public Main main;
    public WaitLogic(Main main , JButton b,JPanel s){
        makeRoom = b;
        this.main = main;
        this.roomshowPanel = s;
    }
    public void roomMake(){
        String roomsize1 =JOptionPane.showInputDialog(main.MainFrame, "방 사이즈를 입력해주세요","방 사이즈 입력");
        int roomsize;
        try{
            roomsize = Integer.parseInt(roomsize1);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(main.MainFrame,"숫자만 입력해주세요","실패",JOptionPane.ERROR_MESSAGE);
            return;
        }
        MOD outMsg = new Room(main.ID.getId(),roomsize);
        try {
            outMsg.setMod(ROOM_MAKE_MODE);
            main.MainOutput.writeObject(outMsg);

            MOD receive = (MOD)main.MainInput.readObject();
            main.isrepaint=false;
            if(receive.getMOD() == SUCCESSED){//성공하면 다음 룸으로 넘어가면 된다
                main.room = (Room)main.MainInput.readObject();//한 번 읽고 다음으로 넘겨줄까?
                if(main.room.getMOD() == SUCCESSED){
                    main.transition(new GameWaitRoom(main));
                    main.isrepaint=true;
                }
            }
            else{
                JOptionPane.showMessageDialog(main.MainFrame,"방 만들기 실패하였습니다.","실패",JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            System.out.println("방 만들기 메세지를 보내지 못했습니다.");
        } catch (ClassNotFoundException ex) {
            System.out.println("방 만들기 메세지를 제대로 받지 못했습니다.");
        }
    }
    public void readRoomdata(){
        MOD outMsg = new MOD(ROOM_INFO);
        try {
            main.MainOutput.writeObject(outMsg);
            MOD receive;
            int i=0;
            while((receive = (MOD)main.MainInput.readObject()).getMOD()!= SUCCESSED){
                JButton rooms = setJButton(i,(Room)receive);

                System.out.println(receive + "방 사이즈는 "+ ((Room) receive).getParticipant().size());
                roomshowPanel.add(rooms);
                i++;
            }
        } catch (IOException e) {
            System.out.println("방 정보를 달라고하지 못했습니다.");
        } catch (ClassNotFoundException e) {
            System.out.println("방 정보를 제대로 읽지 못했습니다.");
        }
    }
    private JButton setJButton(int i, Room Roomdata) {
        JButton rooms = new JButton(i +1 +"번방 방장 : " + Roomdata.getAdminId() + " 인원 : " + Roomdata.getParticipant().size() + "/" + Roomdata.getRoomSize());
        rooms.setFont(Fonts.ShowFont);
        rooms.setBackground(Colors.WHITE);
        rooms.setPreferredSize(new Dimension(400,200));
        rooms.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                RoomInto(Roomdata);
            }
        });
        return rooms;
    }


    public void RoomInto(Room r){
        MOD msg = new Room(r);
        try {
            msg.setMod(ROOM_MODE);//방 들어가기
            main.MainOutput.writeObject(msg);//내가 몇 번 방에 들어갈게
            MOD receive = (MOD) main.MainInput.readObject();
            main.isrepaint=false;
            if(receive.getMOD() == SUCCESSED){//성공적으로 방 정보 받음
                main.room = new Room((Room)main.MainInput.readObject());
                main.transition(new GameWaitRoom(main));
            }else{
                JOptionPane.showMessageDialog(main.MainFrame,"방에 접근할 수 없습니다.","실패",JOptionPane.ERROR_MESSAGE);
            }
            main.isrepaint=true;
        } catch (IOException ex) {
            System.out.println("데이터를 못 보냈습니다.");
        } catch (ClassNotFoundException ex) {
            System.out.println("방 데이터를 읽지 못했습니다.");
        }
    }
}
