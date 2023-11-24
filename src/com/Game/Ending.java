package com.Game;

import com.CommunicateObject.*;
import com.Main.Main;

import javax.swing.*;
import java.awt.*;


/*
*   현재 룸의 방장과 내가 접속한 아이디가 같다면 시작 버튼을 띄워준다.
*   시작을 누르면 해당 시작 버튼은 사라지며 방장의 그림패널부터 시작하여 화면에 floating 되게 된다.
*
*
* */

public class Ending {
    private JFrame mainFrame;//메인 프레임
    private Room presentConnectionRoom;
    private JPanel display;
    public Ending(JFrame f,Room r,JPanel display){
        mainFrame = f; // 일단 방장인지 알아내기
        //엔딩 때는 일단 첫 번째로
        presentConnectionRoom = r;//현재 접속중인 방
        this.display = display;
        showPicture();
    }
    public void showPicture(){//서버에서 받아온 그림을 화면에 띄워주기만 하면 된다.\
        final int HEIGHT  = 110;
        final int WIDTH = 300;
        JLabel temp = new JLabel();
        String path = "/Users/choejihun/Documents/GitHub/DreamOut/src/com/screenshot";

        for(int i=1;i<=2;i++){//라운드 수
            temp.setIcon(new ImageIcon(path+i+".png"));//키야~
            temp.setPreferredSize(new Dimension(WIDTH,HEIGHT));
            display.add(temp);
//            display.revalidate();
//            display.repaint();
            try {Thread.sleep(1000);} catch (InterruptedException ignored){}
        }
    }
}
