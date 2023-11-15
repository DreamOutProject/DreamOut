import javax.swing.*;
import java.awt.*;

public class GameEndRoom extends JPanel {
    private JScrollPane playerScroll;//플레이어 스크롤
    private JScrollPane displayScroll;//그림 스크롤
    private JTextArea t_display;//그림 그린 것들이 뜨는 곳

    GameEndRoom(JFrame f){
            Main.init(this);


            //스크롤
            playerScroll = new JScrollPane(Main.createPlayerPanel(),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            playerScroll.setSize(300,400);
            playerScroll.setLocation(250, 160);



            //사진들
            t_display = new JTextArea();
            t_display.setEnabled(false);
            //스크린 스크롤
            displayScroll = new JScrollPane(t_display,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            displayScroll.setLocation(550,160);
            displayScroll.setSize(660,400);
            displayScroll.setBackground(Color.WHITE);

            //temp

            JLabel temp= new JLabel("test");
            temp.setSize(150,75);
            temp.setLocation(1020,450);
            temp.setOpaque(true);
            temp.setBackground(Color.cyan);

            add(temp);
            add(playerScroll);
            add(displayScroll);

    }


}
