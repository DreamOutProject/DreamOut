
import java.awt.*;
import javax.swing.*;


public class WaitRoom extends JPanel{
	private JButton mkroom;
	private JScrollPane scroll;
	
	
	WaitRoom() {
		Main.init(this);
		
		//방만들기 버튼
		mkroom = new JButton("방만들기");
		mkroom.setBounds(1055,50,105,30);
		
		//대기방 스크롤
		JPanel t = new JPanel(new GridLayout());
		scroll = new JScrollPane();
		t.add(scroll);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		t.setBounds(165,100,1000,530);
		
		t.add(scroll);
		add(t);
		
		
		add(mkroom);
		
		
		
		
		
		

        setSize(1280,720);
        setVisible(true);

    }
	
}
