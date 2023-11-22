package com.Room;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class WaitRoom extends JPanel{
    private JButton mkroom, startGame,room,room1,room2,room3;
    private JScrollPane scroll;
    private String choice;
    private JTextArea t_display;
    private JPanel t, p,l,m,k;
    private int button_num =-1;


    public WaitRoom(JFrame frame) {

        //게임시작 버튼
        startGame = new JButton("게임 시작");
        startGame.setBounds(930,50,105,30);
        //방만들기 버튼
        mkroom = new JButton("방만들기");
        mkroom.setBounds(1055,50,105,30);

        //대기방 스크롤
        t = new JPanel(new GridLayout(0,2,5,10));
        scroll = new JScrollPane(t);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(165,100,1000,530);

        p = new JPanel();
        p.setPreferredSize(new Dimension(450,100));
        p.setMaximumSize(new Dimension(450, 100));


        room = new JButton("1번방");
        room.setPreferredSize(new Dimension(450,100));
        room.setEnabled(false);
        room.addMouseListener(new clicked());

        l = new JPanel();
        l.setPreferredSize(new Dimension(450,100));
        l.setMaximumSize(new Dimension(450, 100));


        room1 = new JButton("2번방");
        room1.setPreferredSize(new Dimension(450,100));
        room1.setEnabled(false);
        room1.addMouseListener(new clicked());

        k = new JPanel();
        k.setPreferredSize(new Dimension(450,100));
        k.setMaximumSize(new Dimension(450, 100));


        room2 = new JButton("3번방");
        room2.setPreferredSize(new Dimension(400,100));
        room2.setEnabled(false);
        room2.addMouseListener(new clicked());

        m = new JPanel();
        m.setPreferredSize(new Dimension(450,100));
        m.setMaximumSize(new Dimension(450, 100));

        room3 = new JButton("4번방");
        room3.setPreferredSize(new Dimension(450,100));
        room3.setEnabled(false);
        room3.addMouseListener(new clicked());


        p.add(room);
        l.add(room1);
        k.add(room2);
        m.add(room3);

        t.add(p);
        t.add(l);
        t.add(k);
        t.add(m);

        add(startGame);
        add(scroll);
        add(mkroom);

        addmouseListener(frame);
        addActionListener(frame);
        setSize(1280,720);
        setVisible(true);


    }
    //방버튼을 누르면 GameRoom으로 이동
    public void addmouseListener(JFrame f){
        room.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(button_num != -1) {
                    f.getContentPane().removeAll();
                    f.add(new GameRoom(f));
                    f.revalidate();
                    f.repaint();
                }
            }
        });
        room1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(button_num != -1) {
                    f.getContentPane().removeAll();
                    f.add(new GameRoom(f));
                    f.revalidate();
                    f.repaint();
                }
            }
        });
        room2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(button_num != -1) {
                    f.getContentPane().removeAll();
                    f.add(new GameRoom(f));
                    f.revalidate();
                    f.repaint();
                }
            }
        });
        room3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(button_num != -1) {
                    f.getContentPane().removeAll();
                    f.add(new GameRoom(f));
                    f.revalidate();
                    f.repaint();
                }
            }
        });
    }
    public void addActionListener(JFrame f){
        mkroom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //방 인원수
                String[] peopleNum = {"4","5","6","7","8","9","10"};
                choice = (String)JOptionPane.showInputDialog(WaitRoom.this,"게임 인원 수", "방만들기",JOptionPane.PLAIN_MESSAGE,null,peopleNum,peopleNum[0]);

                //방 만들기를 하면 생기는 패널

				/*JPanel p = new JPanel();
				p.setPreferredSize(new Dimension(450,100));
				p.setMaximumSize(new Dimension(400, 100));

				JTextArea room = new JTextArea();
				room.setPreferredSize(new Dimension(400,100));
				JButton choice = new JButton("선택");
				choice.addMouseListener(new clicked());

				p.add(room);
				p.add(choice);
				t.add(p);
				t.revalidate();
				t.repaint();*/
            }
        });
        startGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(button_num != -1) {
                    f.getContentPane().removeAll();
                    f.add(new GameRoom(f));
                    f.revalidate();
                    f.repaint();
                }
            }
        });

    }

    class clicked extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            System.out.println("지금 클릭되었습니다.");
            JButton temp = (JButton) e.getComponent();


            if(temp.getText().charAt(1)== 'h') {//2번 클릭인지 확인
                button_num = 2;
            }else {
                button_num = 1;
            }
        }
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            System.out.println("지금 클릭되었습니다.");
            JButton temp = (JButton) e.getComponent();


            if(temp.getText().charAt(1)== 'h') {//2번 클릭인지 확인
                button_num = 2;
            }else {
                button_num = 1;
            }
        }
    }

}