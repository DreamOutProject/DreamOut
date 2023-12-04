package com.Panel;

import com.CommunicateObject.MOD;
import com.CommunicateObject.Room;
import com.Main.Main;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static com.CommunicateObject.MODE.*;

public class WaitRoom extends RootPanel {
    public Main main;
    public JPanel roomshowPanel;
    public WaitRoom(Main main){//서버로부터 현재 있는 방들 달라고 하자.
        this.main = main;
        roomshowPanel = new JPanel(new GridLayout(0,4));
        roomshowPanel.setBounds(100,55,1100,550);
        readRoomdata();

        JButton makeRoom = new JButton("방 만들기");
        makeRoom.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                String roomsize =JOptionPane.showInputDialog(null, "방 사이즈를 입력해주세요","방 사이즈 입력");
                MOD outMsg = new Room(main.ID.getId(),Integer.parseInt(roomsize));
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
                        JOptionPane.showMessageDialog(null,"방 만들기 실패하였습니다.","실패",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    System.out.println("방 만들기 메세지를 보내지 못했습니다.");
                } catch (ClassNotFoundException ex) {
                    System.out.println("방 만들기 메세지를 제대로 받지 못했습니다.");
                }
            }
        });
        makeRoom.setBounds(1150,20,75,30);
        add(roomshowPanel);
        add(makeRoom);
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
        JButton rooms = new JButton(i +1 +"번 방 방장 : " + Roomdata.getAdminId() + "인원 : " + Roomdata.getParticipant().size() + "/" + Roomdata.getRoomSize());
        rooms.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                MOD msg = new Room(Roomdata);
                try {
                    msg.setMod(ROOM_MODE);//방 들어가기
                    main.MainOutput.writeObject(msg);//내가 몇 번 방에 들어갈게
                    MOD receive = (MOD) main.MainInput.readObject();
                    main.isrepaint=false;
                    if(receive.getMOD() == SUCCESSED){//성공적으로 방 정보 받음
                        main.room = new Room((Room)main.MainInput.readObject());
                        main.transition(new GameWaitRoom(main));
                    }else{
                        JOptionPane.showMessageDialog(null,"방에 접근할 수 없습니다.","실패",JOptionPane.ERROR_MESSAGE);
                    }
                    main.isrepaint=true;
                } catch (IOException ex) {
                    System.out.println("데이터를 못 보냈습니다.");
                } catch (ClassNotFoundException ex) {
                    System.out.println("방 데이터를 읽지 못했습니다.");
                }
            }
        });
        return rooms;
    }
    public void repainTing(){
        roomshowPanel.removeAll();//전부 지우고
        readRoomdata();//데이터 다시 읽어오고 띄우고
        main.presentPanel.revalidate();//다시 그리기 해도 됨
        main.presentPanel.repaint();//다시 그리기
    }
}
