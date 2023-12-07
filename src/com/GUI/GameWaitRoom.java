package com.GUI;

import com.CommunicateObject.MOD;
import com.CommunicateObject.Room;
import com.Logic.GameWaitLogic;
import com.Main.Main;
import com.Ui.Colors;
import com.Ui.Fonts;
import com.Ui.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLOutput;

import static com.CommunicateObject.MODE.*;

//게임 시작
//옆에 방 데이터를 계속 실시간으로 바꿔줘야 한다.
public class GameWaitRoom extends RootPanel{
    public Main main;
    public int NumberGame=1;
    public JPanel leftSide,rightSide,Center;
    public JLabel player;

    JButton secondGame = new JButton();
    JButton firstGame = new JButton();
    public GameWaitLogic logic;
    public GameWaitRoom(Main main){
        this.main = main;

        Center = new JPanel(new BorderLayout());
        Center.setBounds(80,55,1120,550);


        leftSide = new JPanel(new GridLayout(0,1,0,5));
        leftSide.setPreferredSize(new Dimension(250,500));
        rightSide =new JPanel(new BorderLayout());
        rightSide.setPreferredSize(new Dimension(780,500));



        Center.add(leftSide,BorderLayout.WEST);
        Center.add(rightSide,BorderLayout.CENTER);


        logic = new GameWaitLogic(main,leftSide,rightSide,Center,firstGame,secondGame);
        logic.readData();//서버에서 해당 방 데이터 읽어오기

        leftSide();
        rightSide();
        logic.ButtonEnable();

        add(Center);
    }

    public void rightSide() {
        firstGame.setText("<HTML><h3>1. 스피드 게임 </h3>" +
                "<ol><li>60초 45초 빠르게 줄여가는 시간 속에서 해당 주제에 대한 그림을 그려보세요!<BR>" +
                "촉박해진 시간 속에 다양한 그림들을 맛볼 수 있을 것입니다.</li></ol></HTML>");
        firstGame.setOpaque(true);

        secondGame.setText("<html><H3>2. 그림 이어가기 게임</H3>" +
                "<ol><li>그림 -> 그림에 대한 주제 -> 그림 -> .. -> 그림에 대한 답</li>" +
                "<li>위에 순서대로 처음 순서는 그림을 그리게 되고, 다음 사람은 그림에 대한 정답으로 주제를 떠올립니다.</li>" +
                "<li>그럼 다음 순서는 앞 사람이 떠올린 주제에 따라 다시 그림을 그립니다.</li>" +
                "<li>마지막 순서는 그림에 대한 답을 정합니다.</li>" +
                "<li>최종적으로 처음 주제를 정한 것과 답이 일치하는지 확인해봅시다.</li></ol></html>");
        secondGame.setOpaque(true);
        secondGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(main.room.getAdminId() != main.ID.getId())return;
                logic.secondGameChoice();
                logic.Message(2);
            }
        });

        firstGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                if(main.room.getAdminId() != main.ID.getId())return;
                logic.firstGameChoice();
                logic.Message(1);
            }
        });

        switch (main.room.getGamecategory()){
            case 1->{
                firstGame.setBackground(Colors.GameMouseHover);
                secondGame.setBackground(Colors.GameMouserOut);
            }
            case 2->{
                secondGame.setBackground(Colors.GameMouseHover);
                firstGame.setBackground(Colors.GameMouserOut);
            }
        }

        JPanel center = new JPanel(new GridLayout(0,2));
        center.add(firstGame);
        center.add(secondGame);

        JPanel south = setSouthPanel();
        rightSide.add(center,BorderLayout.CENTER);
        rightSide.add(south,BorderLayout.SOUTH);

    }

    private JPanel setSouthPanel() {
        JPanel south = new JPanel(new GridLayout(0,1));
        if(main.room.getAdminId()==main.ID.getId()){//로그인 한 정보가 어드민이라면?
            JButton startButton = new JButton("게임 시작하기");
            startButton.setPreferredSize(new Dimension(780,60));
            startButton.setFont(Fonts.ShowFont);
            startButton.setBackground(Colors.Button);
            startButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    logic.GameStartmessage();
                }
            });
            south.add(startButton);
        }else{
            JLabel waitLabel = new JLabel("방장의 선택을 기다립니다.");
            south.add(waitLabel);
        }
        return south;
    }


    public void leftSide() {
        JLabel l_player = new JLabel("플레이어 인원 " + main.room.getParticipant().size() + " / " + main.room.getRoomSize());
        l_player.setFont(Fonts.ShowFont);
        l_player.setHorizontalAlignment(JLabel.CENTER);
        leftSide.setBackground(Colors.PURPLE);
        leftSide.add(l_player);

        int total = main.room.getRoomSize();
        for(Integer id:main.room.getParticipant()) {
            JPanel p = new JPanel(new GridLayout(0,1));
            if(id == main.ID.getId()){
                player = new JLabel("ID : " + id+"(ME)");
            }
            else {
                player = new JLabel("ID : " + id);
            }
            player.setHorizontalAlignment(JLabel.CENTER);
            p.add(player);
            player.setFont(Fonts.ShowFont);
            leftSide.add(p);
            p.setBackground(Colors.PURPLE1);
            total--;
        }
        for(int i=0;i<total;i++){
            JPanel p = new JPanel(new GridLayout(0,1));
            JLabel player = new JLabel("비어있음");
            player.setHorizontalAlignment(JLabel.CENTER);
            p.add(player);
            p.setBackground(Colors.PURPLE1);
            player.setFont(Fonts.ShowFont);
            leftSide.add(p);

        }
    }

    public void reapainting(){
        logic.readData();//다시 읽고
        Center.remove(leftSide);//일단 왼쪽 거 없애고
        leftSide.removeAll();//전부 지워준 뒤에
        leftSide();
        Center.add(leftSide,BorderLayout.WEST);//다시 추가
        main.presentPanel.revalidate();
        main.presentPanel.repaint();//메인을 다시 그리기
    }
}
