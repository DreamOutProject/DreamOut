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
        StringMsg data = (StringMsg) Main.in.readObject();

        int dataInt = Integer.parseInt(data.getMsg());

        for(int i = 1; i<=dataInt; i++){
            p = new JPanel();
            p.setPreferredSize(new Dimension(450,100));
            p.setMaximumSize(new Dimension(450, 100));

            exroom = (Room) Main.in.readObject();

            room1 = new JButton("방번호:"+ exroom.getAdminId()+ "방인원: "+exroom.getUsers().size()+"/"+exroom.getRoomSize());
            room1.setPreferredSize(new Dimension(450,100));
            room1.setEnabled(true);
            room1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);

                        exroom.setMsgMode(ObjectMsg.ROOM_MODE);

                        try {

                            Main.out.writeObject(new User(exroom, Main.my.getId(),Main.my.getPw()));
                            ObjectMsg x = (ObjectMsg) Main.in.readObject();
                            if(Objects.equals(x.getMsgMode(), ObjectMsg.SUCESSED)){
                                Main.Transition_go(new GameRoom(f));
                            }
                            else if(Objects.equals(x.getMsgMode(), ObjectMsg.FAILED)){
                                JOptionPane.showMessageDialog(WaitRoom.this, "방 입장에 실패했습니다");
                            }

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }

                }
            });

            p.add(room1);
            t.add(p);
        }
        } catch (ClassNotFoundException | IOException ignored){}

        add(scroll);
        add(mkroom);

        addActionListener(f);
        setSize(1280,720);
        setVisible(true);


    }
    //방버튼을 누르면 GameRoom으로 이
    public void addActionListener(JFrame f){
        mkroom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //방 인원수
                String[] peopleNum = {"4","5","6","7","8","9","10","11","12"};
                 choice= (String) JOptionPane.showInputDialog(WaitRoom.this,"게임 인원 수", "방만들기", JOptionPane.PLAIN_MESSAGE,null,peopleNum,peopleNum[0]);

                int a = Main.my.getId();
                if(choice !=null) {
                    //방 만들기를 하면 생기는 패널
                    try {

                        ObjectMsg outMsg = new MsgMode(ObjectMsg.ROOM_MAKE_MODE);
                        exroom = new Room(outMsg, a, a, Integer.parseInt(choice));
                        Main.out.writeObject(new User(exroom, Main.my.getId(), Main.my.getPw()));


                        ObjectMsg roomxo = (MsgMode) Main.in.readObject();

                        if (roomxo.getMsgMode() == ObjectMsg.SUCESSED) {
                            Main.Transition_go(new GameRoom(f));
                        } else {

                        }
                    } catch (Exception exx){

                    }

                }
            }
        });

    }
}