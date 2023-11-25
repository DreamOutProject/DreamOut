package com.Room;
import com.Game.Drawing;
import com.Game.Game;
import com.Ui.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class GameStartRoom extends RoomPanel {
    private int GameMode; // 1이면 1과 2로 게임이 나뉨
    private Drawing drawing;
    public GameStartRoom(JFrame f,int GameMode){
        int WIDTH = 900;
        this.GameMode = GameMode;//현재 게임 모드를 갖고 온다.
        //진행바
        JProgressBar timeBar = new JProgressBar(JProgressBar.HORIZONTAL,0, 100);
        timeBar.setFont(new Font("맑은 고딕",Font.BOLD,30));
        timeBar.setStringPainted(true);
        timeBar.setOpaque(true);
        timeBar.setBackground(Color.WHITE);
        timeBar.setBounds(210,40, WIDTH,50);
        timeBar.setValue(5);
        timeBar.setString(5+"초");
        timeBar.setForeground(Color.GRAY);


        //client
        JLabel client = new JLabel("Round : 1/8");
        client.setFont(new Font("Courier", Font.BOLD,11));
        client.setOpaque(true);
        client.setBackground(Color.lightGray);
        client.setBounds(1030,110,80,50);

        //display
        JLabel t_display = new JLabel(new ImageIcon("testing.jpg")){
            @Override
            public void paintComponent(Graphics t) {
                super.paintComponent(t);
                Graphics2D t2 = (Graphics2D) t;
                if(Game.point!=null){
                    for(int i=2;i<Game.point.size();i++){
                        if(Game.point.get(i)==null)continue;
                        else if(Game.point.get(i-1)==null)continue;
                        t2.setColor(Game.point.get(i).getColor());
                        t2.setStroke(new BasicStroke(Game.point.get(i).getWidth()));
                        t2.drawLine(Game.point.get(i).getPoint().x,Game.point.get(i).getPoint().y, Game.point.get(i-1).getPoint().x,Game.point.get(i-1).getPoint().y);
                    }
                }
            }
        };
        drawing = new Drawing(t_display,client);
        t_display.setBounds(210,110,WIDTH,450);
        t_display.addMouseListener(drawing);
        t_display.addMouseMotionListener(drawing);
        t_display.setOpaque(true);
        t_display.setBackground(Color.white);


        //글자를 입력할 수 있고 없고.
        JTextField t_input = new JTextField();
        if(this.GameMode == 2){//2번 게임이므로
            t_input.setEnabled(false);//입력칸을 쓸 수 없게 만든다.
        }
        t_input.setBounds(210,580,WIDTH,55);
        t_input.setHorizontalAlignment(JTextField.RIGHT);
        t_input.setFont(new Font("맑은 고딕",Font.BOLD,23));

        //L_pallete
        JPanel l_palette = initPalette();
        l_palette.setBounds(1150,110,100,450);
        l_palette.setOpaque(true);
        l_palette.setBackground(Color.white);


        add(timeBar);
        add(client);
        add(t_display);
        add(t_input);
        add(l_palette);
        new Game(f,timeBar,client,t_display);
    }

    public JPanel initPalette() {
        JPanel temp = new JPanel(new GridLayout(0,2));
        JLabel RED = colorMake(Color.RED);
        JLabel RED_BETWEEN_ORANGE = colorMake(Colors.REDBETWEENORANGE);
        JLabel ORANGE = colorMake(Color.orange);
        JLabel YELLOW = colorMake(Color.yellow);
        JLabel LIGHT_GREEN = colorMake(Colors.LIGHTGREEN);
        JLabel GREEN = colorMake(Color.GREEN);
        JLabel LIGHT_BLUE = colorMake(Colors.LIGHTBLUE);
        JLabel BLUE = colorMake(Color.blue);
        JLabel INDIGO = colorMake(Colors.INDIGO);
        JLabel PURPLE = colorMake(Colors.PURPLE);
        JLabel BLACK = colorMake(Color.BLACK);
        JLabel word = new JLabel("선 굵기: ");
        JTextField number_input = new JTextField(4);
        number_input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){//엔터키를 눌렀을 때
                    drawing.setWidth(Integer.parseInt(number_input.getText()));
                }
            }
        });
        number_input.setText(10+"");
        temp.add(word);
        temp.add(number_input);
        temp.add(RED);
        temp.add(RED_BETWEEN_ORANGE);
        temp.add(ORANGE);
        temp.add(YELLOW);
        temp.add(LIGHT_GREEN);
        temp.add(GREEN);
        temp.add(LIGHT_BLUE);
        temp.add(BLUE);
        temp.add(INDIGO);
        temp.add(PURPLE);
        temp.add(BLACK);
        return temp;
    }
    public JLabel colorMake(Color color){
        JLabel temp = new JLabel();
        temp.setOpaque(true);
        temp.setBackground(color);
        temp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                drawing.setCurrentColor(color);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                drawing.setCurrentColor(color);
            }
        });
        return temp;
    }
}
