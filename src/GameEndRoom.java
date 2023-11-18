import javax.swing.*;
import java.awt.*;

public class GameEndRoom extends JPanel {
    private JScrollPane playerScroll;//플레이어 스크롤
    private JScrollPane displayScroll;//그림 스크롤
    private JTextArea t_display;//그림 그린 것들이 뜨는 곳
    private Room room;
    GameEndRoom(JFrame f){

        Main.init(this);

        //스크롤
        playerScroll = new JScrollPane(createPlayerPanel(),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
    public JPanel createPlayerPanel(){
        JPanel t = new JPanel(new GridLayout(0,1,0,5));//0을 입력하면 제한없이 받는 거임.
        //플레이어는 아래로 계속 뜨게끔 만들 거임.
        t.setBackground(new Color(77,34,146));

        JLabel player = Main.NewLabel("플레이어 인원 "+room.getUsers().size()+"/8",23);
        player.setForeground(new Color(191,179,215));
        player.setBackground(new Color(77,37,148));
        t.add(player);

        for(int i=0;i<15;i++){
            JLabel temp = Main.NewLabel("비어 있음",18,new Color(76,41,160));
            temp.setForeground(new Color(119,70,224));
            t.add(temp);
        }
        return t;
    }

}
