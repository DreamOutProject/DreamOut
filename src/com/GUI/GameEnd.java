package com.GUI;

import com.CommunicateObject.MOD;
import com.CommunicateObject.Picture;
import com.CommunicateObject.Room;
import com.Logic.GameEndLogic;
import com.Main.Main;
import com.Ui.Fonts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import static com.CommunicateObject.MODE.*;

public class GameEnd extends RootPanel{
    public Main main;
    public JPanel Center;
    public JPanel leftSide;
    public JPanel rightSide;
    public GameEndLogic logic;
    public Vector<Picture>AllData = new Vector<>();
    public JPanel showPicture;
    public JButton next;
    public JButton prev;
    public JButton returnWait;//
    public JButton albumStart;
    public JPanel South;
    public GameEnd(Main main){
        this.main = main;
        readData();//서버에서 해당 방 데이터 읽어오기
        Center = new JPanel(new BorderLayout());
        Center.setBounds(100,55,1100,550);

        leftSide = leftSide();
        rightSide = new JPanel(new BorderLayout());
        South = new JPanel(new GridLayout(0, 2));
        showPicture = new JPanel(new GridLayout(0,2));
        next = new JButton("다음 앨범으로 넘기기");
        prev = new JButton("이전 앨범으로 넘기기");
        next.addActionListener(e -> {
            logic.Messge(new MOD(ENDING_NEXT_MODE));
        });
        prev.addActionListener(e->{
            logic.Messge(new MOD(ENDING_PREV_MODE));
        });
        South.add(prev);
        South.add(next);

        returnWait = new JButton("방으로 돌아가기");
        returnWait.addActionListener(e->{
            logic.Messge(new MOD(RETURN_GAMEROOM));
        });
        //1. rightside에다가 버튼 만들기 ("앨범 시작하기")

        albumStart = new JButton("앨범 시작하기 ");
        albumStart.addActionListener(e -> {
            logic.Messge(new MOD(ENDING_START_MODE));
            //그리고 현재 버튼삭제 하고 다음으로 버튼 생기기
        });

        rightSide.add(showPicture,BorderLayout.CENTER);
        rightSide.add(albumStart,BorderLayout.SOUTH);

        Center.add(leftSide,BorderLayout.WEST);
        Center.add(rightSide,BorderLayout.CENTER);

        add(Center);
        logic = new GameEndLogic(main,this,AllData);
        logic.start();
        logic.ButtonEnable();
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
            System.out.println("대기방 데이터를 제대로 보내지 못했습니다.");
        } catch (ClassNotFoundException e) {
            System.out.println("대기방 데이터를 제대로 받지 못했습니다.");
            main.transition(new WaitRoom(main));//굳.
        }
    }
    public JPanel leftSide() {
        JPanel t= new JPanel(new GridLayout(0,1));
        for(Integer id:main.room.getParticipant()){
            JLabel player = new JLabel("ID : " + id);
            t.add(player);
        }
        return t;
    }
    public void showData(int index){
        showPicture.removeAll();
        System.out.println("여기까지는 왔어요");
        Vector<Integer>IDs = main.room.getParticipant();

        Picture data = AllData.get(index);
        Vector<JLabel>Files = data.getFiles();
        for(int i=0;i<Files.size();i++){
            int id =  IDs.get((index+i)%IDs.size());
            JLabel showId = new JLabel("ID : " + id);
            showId.setHorizontalAlignment(JLabel.CENTER);
            showId.setFont(Fonts.ShowFont);
            showPicture.add(showId);
            showPicture.add(Files.get(i));
        }
        showPicture.repaint();
        showPicture.revalidate();
        System.out.println("끝");
    }
}
