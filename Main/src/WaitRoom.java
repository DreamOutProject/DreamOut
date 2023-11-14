package Main.src;
import java.awt.*;
import javax.swing.*;


public class WaitRoom extends JPanel{
	private JLabel Jlabel;
	private JButton mkroom;
	private JTextPane t_display1,t_display2;
	private JScrollPane scroll;
	
	
	WaitRoom() {
		Main.init(this);
		
		//방만들기 버튼
		mkroom = new JButton("방만들기");
		mkroom.setBounds(1055,50,105,30);
		
		//대기방 스크롤
		scroll = new JScrollPane();
		scroll.setBounds(165,100,1000,530);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		add(mkroom);
		add(scroll);
		
		

        setSize(1280,720);
        setVisible(true);

    }
	
}
