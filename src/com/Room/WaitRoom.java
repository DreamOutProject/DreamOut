package com.Room;

import com.CommunicateObject.*;
import com.Login.StartLogin;
import com.Main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;


public class WaitRoom extends RoomPanel{
    private JButton mkroom,room1;
    private JScrollPane scroll;
    private String choice;
    private JPanel t,p;
    private Room exroom = null;
    private int button_num =-1;
    private ObjectMsg q;



    public WaitRoom(JFrame f) {

        try{
        //방만들기 버튼
            mkroom = new JButton("방만들기");
            mkroom.setBounds(1055,50,105,30);
            t = new JPanel(new GridLayout(0,2,5,10));
            scroll = new JScrollPane(t);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setBounds(165,100,1000,530);
            Main.out.writeObject(new MsgMode(ObjectMsg.ROOM_VIEW));
            Main.out.flush();

            ObjectMsg data = (ObjectMsg) Main.in.readObject();
            IntMsg datas = (IntMsg)data;
            int dataInt = datas.getNumber();
            for(int i = 1; i<=dataInt; i++){
                p = new JPanel();
                p.setPreferredSize(new Dimension(450,100));
                p.setMaximumSize(new Dimension(450, 100));

                exroom = (Room) Main.in.readObject();
                room1 = new JButton("방번호:"+ exroom.getAdminId()+ "방인원: "+exroom.getUsers().size()+"/"+exroom.getRoomSize());
                room1.setPreferredSize(new Dimension(450,100));
                room1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        super.mousePressed(e);
                        Integer roomid = exroom.getRoomId();
                        User CurrentUser = Main.my;
                        CurrentUser.setMsgMode(ObjectMsg.ROOM_MODE);
                        try {
                            Main.out.writeObject(new IntMsg(CurrentUser,roomid));
                            Main.out.flush();
                            ObjectMsg x = (ObjectMsg) Main.in.readObject();
                            if(Objects.equals(x.getMsgMode(), ObjectMsg.SUCESSED)){
                                exroom.setMsgMode(ObjectMsg.ROOM_INFO);
                                Main.out.writeObject(exroom);
                                System.out.println("도달");
                                Main.room  = (Room)Main.in.readObject();
                                Main.Transition_go(new GameRoom(f));
                            }
                            else if(Objects.equals(x.getMsgMode(), ObjectMsg.FAILED)){
                                JOptionPane.showMessageDialog(WaitRoom.this, "방 입장에 실패했습니다");
                            }
                        } catch (IOException | ClassNotFoundException ignored) {
                            System.out.println(ignored +"메세지");
                        }
                    }
                });
                p.add(room1);
                t.add(p);
            }
        } catch (ClassNotFoundException | IOException ignored){
            System.out.println(ignored);
        }
        add(scroll);
        add(mkroom);
        addActionListener(f);
    }
    //방버튼을 누르면 GameRoom으로 이
    public void addActionListener(JFrame f){
        mkroom.addActionListener(e -> {
            //방 인원수
            String[] peopleNum = {"4","5","6","7","8","9","10","11","12"};
            choice= (String) JOptionPane.showInputDialog(WaitRoom.this,"게임 인원 수", "방만들기", JOptionPane.PLAIN_MESSAGE,null,peopleNum,peopleNum[0]);
            int a = Main.my.getId();
            if(choice !=null) {
                try {
                    ObjectMsg outMsg = new MsgMode(ObjectMsg.ROOM_MAKE_MODE);
                    IntMsg roomsize = new IntMsg(outMsg, Integer.parseInt(choice));
                    User userOut = new User(roomsize,Main.my.getId(),Main.my.getPw());
                    Main.out.writeObject(userOut);
                    Main.out.flush();
                    ObjectMsg inputs = (ObjectMsg) Main.in.readObject();
                    Main.room = (Room)inputs;
                    if(Main.room.getMsgMode() == ObjectMsg.SUCESSED){
                        Main.Transition_go(new GameRoom(f));
                    }
                } catch (Exception ignored){}
            }
        });

    }
}