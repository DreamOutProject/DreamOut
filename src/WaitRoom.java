
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class WaitRoom extends JPanel{
	private JButton mkroom, startGame;
	private JScrollPane scroll;
	private String choice;
	private JTextArea t_display;
	private JPanel t;
	private int button_num =-1;

	
	WaitRoom(JFrame frame) {
		Main.init(this);

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

		add(startGame);
		add(scroll);
		add(mkroom);

		addActionListener(frame);
        setSize(1280,720);
        setVisible(true);


    }

	public void addActionListener(JFrame f){
		mkroom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//방 인원수
				String[] peopleNum = {"1","2","3","4","5","6","7","8"};
				choice = (String)JOptionPane.showInputDialog(WaitRoom.this,"게임 인원 수", "방만들기",JOptionPane.PLAIN_MESSAGE,null,peopleNum,peopleNum[0]);

				//방 만들기를 하면 생기는 패널

				JPanel p = new JPanel();
				p.setPreferredSize(new Dimension(450,100));
				p.setMaximumSize(new Dimension(450, 100));
				p.setBorder(new TitledBorder(new LineBorder(Color.black,1)));
				//JTextArea room = new JTextArea();
				JButton choice = new JButton("선택");
				choice.addMouseListener(new clicked());


				p.add(choice);
				t.add(p);
				t.revalidate();
				t.repaint();

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
