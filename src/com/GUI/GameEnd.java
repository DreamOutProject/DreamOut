package com.GUI;

import com.CommunicateObject.MOD;
import com.CommunicateObject.Picture;
import com.CommunicateObject.Room;
import com.Logic.GameEndLogic;
import com.Main.Main;
import com.Ui.Colors;
import com.Ui.Fonts;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Vector;

import static com.CommunicateObject.MODE.ROOM_VIEW;
import static com.CommunicateObject.MODE.SUCCESSED;

public class GameEnd extends RootPanel{
    public Main main;
    public JPanel Center;
    public JPanel leftSide;
    public JLabel player;
    public JPanel rightSide;
    public GameEndLogic logic;
    public Vector<Picture>AllData = new Vector<>();
    public GameEnd(Main main){
        this.main = main;
        readData();//서버에서 해당 방 데이터 읽어오기
        Center = new JPanel(new BorderLayout());
        Center.setBounds(100,55,1100,550);

        leftSide = leftSide();
        rightSide = new JPanel(new GridLayout(0,1));
        rightSide.setBackground(Colors.PURPLE);
        JScrollPane scroll = new JScrollPane(rightSide,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Center.add(leftSide,BorderLayout.WEST);
        Center.add(scroll,BorderLayout.CENTER);

        add(Center);
        logic = new GameEndLogic(main,this,AllData);
        logic.start();
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
        JPanel t= new JPanel(new GridLayout(0,1,0,10));
        t.setPreferredSize(new Dimension(250,500));
        JPanel p = new JPanel(new GridLayout(0,1));
        for(Integer id:main.room.getParticipant()){
            if(id == main.ID.getId()){
                player = new JLabel("ID : " + id+"(ME)");
            }
            else {
                player = new JLabel("ID : " + id);
            }
            player.setHorizontalAlignment(JLabel.CENTER);
            player.setFont(Fonts.ShowFont);
            p.setBackground(Colors.PURPLE1);
            p.add(player);
            t.add(p);
        }
        return t;
    }
    public void showData(){
        for(Picture data:AllData){//모든 유저의 그림앨범
            Vector<JLabel> t = data.getFiles();
            for(JLabel d:t){
                if(d==null)continue;
                System.out.println("파일 추가");
                d.setBackground(Colors.WHITE);
                rightSide.add(d);

            }
        }
        rightSide.repaint();
        rightSide.revalidate();
    }
}
