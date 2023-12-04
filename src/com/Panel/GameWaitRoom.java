package com.Panel;

import com.CommunicateObject.MOD;
import com.CommunicateObject.Room;
import com.Main.Main;
import com.Ui.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static com.CommunicateObject.MODE.*;

//게임 시작
//옆에 방 데이터를 계속 실시간으로 바꿔줘야 한다.
public class GameWaitRoom extends RootPanel{
    public Main main;
    public int NumberGame=1;
    public JPanel leftSide;
    public JPanel rightSide;
    public JPanel Center;
    public GameWaitRoom(Main main){
        this.main = main;
        readData();//서버에서 해당 방 데이터 읽어오기


        Center = new JPanel(new BorderLayout());
        Center.setBounds(100,55,1100,550);
        leftSide = leftSide();
        rightSide = rightSide();
        Center.add(leftSide,BorderLayout.WEST);
        Center.add(rightSide,BorderLayout.CENTER);

        add(Center);
    }

    public JPanel rightSide() {
        JPanel t= new JPanel(new BorderLayout());
        JButton firstGame = new JButton("<HTML><h3>1. 스피드 게임 </h3>" +
                "<ol><li>60초 45초 빠르게 줄여가는 시간 속에서 해당 주제에 대한 그림을 그려보세요!<BR>" +
                "촉박해진 시간 속에 다양한 그림들을 맛볼 수 있을 것입니다.</li></ol></HTML>");
        firstGame.setOpaque(true);

        JButton secondGame = new JButton("<html><H3>2. 그림 이어가기 게임</H3>" +
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
                secondGame.setBackground(Colors.GameMouseHover);
                firstGame.setBackground(Colors.GameMouserOut);
                NumberGame = 2;//1번 게임
                //2번을 클릭했다고 서버로 보내주기
                MOD outMsg = new MOD(GAME_TWO_CHOICE);//2번 클릭했습니다.
                try {
                    main.MainOutput.writeObject(outMsg);
                }catch (IOException ex) {
                    System.out.println("2번을 선택했다고 서버로 보내지 못 했습니다.");
                }
            }
        });

        firstGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                firstGame.setBackground(Colors.GameMouseHover);
                secondGame.setBackground(Colors.GameMouserOut);
                NumberGame = 1;//1번 게임
                MOD outMsg = new MOD(GAME_ONE_CHOICE);//2번 클릭했습니다.
                try {
                    main.MainOutput.writeObject(outMsg);
                }catch (IOException ex) {
                    System.out.println("2번을 선택했다고 서버로 보내지 못 했습니다.");
                }
            }
        });
        firstGame.setBackground(Colors.GameMouseHover);//처음에 default로 이 아이가 선택

        if(main.room.getAdminId()!= main.ID.getId()){//현재 방의 정보가 다르다면 동적으로 화면 설정이 불가능하도록 만들기
            firstGame.setEnabled(false);
            secondGame.setEnabled(false);
        }

        JPanel center = new JPanel(new GridLayout(0,2));
        center.add(firstGame);
        center.add(secondGame);

        JPanel south = setSouthPanel();
        t.add(center,BorderLayout.CENTER);
        t.add(south,BorderLayout.SOUTH);
        return t;
    }

    private JPanel setSouthPanel() {
        JPanel south = new JPanel(new GridLayout(0,1));
        if(main.room.getAdminId()==main.ID.getId()){//로그인 한 정보가 어드민이라면?
            JButton startButton = new JButton("게임 시작하기");
            startButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    MOD outMsg = new MOD(GAME_START_MODE);
                    main.isrepaint=false;
                    try {
                        main.MainOutput.writeObject(outMsg);
                        main.transition(new GamingRoom(main));
                    } catch (IOException ex) {
                        System.out.println("게임 시작메세지를 제대로 보내지 못했습니다.");
                    }
                    main.isrepaint=true;
                }
            });
            south.add(startButton);
        }else{
            JLabel waitLabel = new JLabel("방장의 선택을 기다립니다.");
            south.add(waitLabel);
        }
        return south;
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
    public void reapainting(){
        readData();//다시 읽고
        Center.remove(leftSide);//일단 왼쪽 거 없애고
        leftSide.removeAll();//전부 지워준 뒤에
        leftSide = leftSide();//다시 넣어주고
        Center.add(leftSide,BorderLayout.WEST);//다시 추가
        main.presentPanel.revalidate();
        main.presentPanel.repaint();//메인을 다시 그리기
    }
}
