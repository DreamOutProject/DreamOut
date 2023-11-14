import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameRoom extends JPanel {
    private JScrollPane scrollPane;//플레이어 스크롤 바
    private String firstsentence = "<HTML><h3>1. 스피드 게임 </h3>" +
            "<ol><li>60초 45초 빠르게 줄여가는 시간 속에서 해당 주제에 대한 그림을 그려보세요!<BR>" +
            "촉박해진 시간 속에 다양한 그림들을 맛볼 수 있을 것입니다.</li></ol></HTML>";
    private String secondsentence = "<html><H3>2. 그림 이어가기 게임</H3>" +
            "<ol><li>그림 -> 그림에 대한 주제 -> 그림 -> .. -> 그림에 대한 답</li>" +
            "<li>위에 순서대로 처음 순서는 그림을 그리게 되고, 다음 사람은 그림에 대한 정답으로 주제를 떠올립니다.</li>" +
            "<li>그럼 다음 순서는 앞 사람이 떠올린 주제에 따라 다시 그림을 그립니다.</li>" +
            "<li>마지막 순서는 그림에 대한 답을 정합니다.</li>" +
            "<li>최종적으로 처음 주제를 정한 것과 답이 일치하는지 확인해봅시다.</li></ol></html>";

    private JComboBox topic;
    String[] topics=  {"일상생활", "스포츠","전자기기","랜덤"};
    
    private int button_num = -1;
    GameRoom(JFrame frame){
    	Main.init(this);
        //주제 선택창
        topic = new JComboBox(topics);
        topic.setLocation(700,75);
        topic.setSize(350,80);

        //주제 라벨
        JLabel l_topic=new JLabel("주제 ->");
        l_topic.setFont(new Font("맑은 고딕",Font.BOLD,15));
        l_topic.setSize(100,30);
        l_topic.setLocation(650,100);

        //스크롤
        scrollPane = new JScrollPane(Main.createPlayerPanel(),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setSize(300,400);
        scrollPane.setLocation(250, 160);

        
        add(topic);
        add(scrollPane);
        add(l_topic);
        add(createRightPanel(frame));//게임 선택 패널
    }
	public JPanel createRightPanel(JFrame f) {
		JPanel t = new JPanel(new BorderLayout());
		t.setSize(500,400);
        t.setLocation(620, 160);
		JButton b_choice = new JButton("시작하기");
		b_choice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				
				if(button_num !=-1) {
					f.getContentPane().removeAll();
					f.add(new GameStartRoom(f));
					f.revalidate();
					f.repaint();
				}
			}
		});
        
        
        t.add(createGamePanel(),BorderLayout.CENTER);
        t.add(b_choice,BorderLayout.SOUTH);
		return t;
	}
	public JPanel createGamePanel(){
    	JPanel t = new JPanel(new GridLayout(1,2));
        

        JButton first = new JButton(firstsentence);
        JButton second= new JButton(secondsentence);
        first.setHorizontalAlignment(JButton.CENTER);
        second.setHorizontalAlignment(JButton.CENTER);
        first.addMouseListener(new clicked());
        second.addMouseListener(new clicked());
        t.add(first);
        t.add(second);
        return t;
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
