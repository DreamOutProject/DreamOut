import javax.swing.*;
import java.awt.*;

public class GameStartRoom extends JPanel {
    private JLabel timeBar;//시간 진행률
    private JTextArea t_display;//그림 그리는 판
    private JTextField t_input;//글자를 입력할 수 있고 없고.
    private JLabel l_pallete;//팔렛트
    private JLabel client;
    
    private int round;

    private final int WIDTH = 900;

    GameStartRoom(JFrame f){
        Main.init(this);
        this.round=1;//처음 라운드는 1이다.
        //진행바
        timeBar = new JLabel("");
        timeBar.setOpaque(true);
        timeBar.setBackground(Color.WHITE);
        timeBar.setLocation(210,40);
        timeBar.setSize(WIDTH,40);


        //display
        t_display = new JTextArea();
        t_display.setLocation(210,110);
        t_display.setSize(WIDTH,450);
        t_display.setEnabled(false);


        //input
        t_input = new JTextField();
        //t_input.setEnabled(false);
        t_input.setSize(WIDTH,55);
        t_input.setLocation(210,580);
        t_input.setHorizontalAlignment(JTextField.RIGHT);
        t_input.setFont(new Font("맑은 고딕",Font.BOLD,23));

        //L_pallete
        l_pallete = new JLabel("");
        l_pallete.setLocation(1150,110);
        l_pallete.setSize(100,450);
        l_pallete.setOpaque(true);
        l_pallete.setBackground(Color.white);



        //client

        client = new JLabel("Round : "+round+"/8");
        client.setFont(new Font("Courier", Font.BOLD,11));
        client.setOpaque(true);
        client.setBackground(Color.lightGray);
        client.setLocation(1030,110);
        client.setSize(80,50);


        add(timeBar);
        add(client);
        add(t_display);
        add(t_input);
        add(l_pallete);
    }
    
}
