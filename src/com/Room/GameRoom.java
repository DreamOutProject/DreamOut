package com.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import com.Main.Main;
import com.CommunicateObject.*;
import com.Ui.Colors;
import com.Ui.Fonts;


public class GameRoom extends RoomPanel {
    private JScrollPane scrollPane;//플레이어 스크롤 바
	private JButton first;
	private JButton second;

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
	private final Vector<User> temp;
    public GameRoom(JFrame frame){
		temp = new Vector<>();
        //주제 선택창
        topic = new JComboBox<>(topics);
		topic.setBounds(700,100,350,40);

        //주제 라벨
        JLabel l_topic=new JLabel("주제 ->");
        l_topic.setFont(new Font("맑은 고딕",Font.BOLD,15));
        l_topic.setSize(100,30);
        l_topic.setLocation(650,100);

        //스크롤
        scrollPane = new JScrollPane(createPlayerPanel(),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setSize(300,400);
        scrollPane.setLocation(250, 160);


		add(topic);
        add(scrollPane);
        add(l_topic);
        add(createRightPanel(frame));//게임 선택 패널
    }
	public JPanel createRightPanel(JFrame f) {
		JPanel t = new JPanel(new BorderLayout());
		JPanel south  = new JPanel(new GridLayout(1,0,10,0));
		t.setSize(500,400);
        t.setLocation(620, 160);

		if(Main.my.getId() == Main.room.getAdminId()){//현재 접속한 클라이언트가 방장일 때
			JButton b_choice = new JButton("시작하기");//해당 버튼이 다른 것으로 띄워질 수도 있음
			b_choice.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					if(button_num !=-1) {
						Main.Transition_go(new GameStartRoom(f,button_num));
					}else{
						JOptionPane.showMessageDialog(GameRoom.this,"게임을 선택해주세요");
					}
				}
				public void mousePressed(MouseEvent e) {
					super.mousePressed(e);
					if(button_num !=-1) {
						Main.Transition_go(new GameStartRoom(f,button_num));
					}else{
						JOptionPane.showMessageDialog(GameRoom.this,"게임을 선택해주세요");
					}
				}
			});
			south.add(b_choice);
		}else{
			JLabel waitingHost = new JLabel("방장의 게임 셋팅과 게임 시작을 기다립니다. :)");
			waitingHost.setFont(Fonts.ShowFont);
			waitingHost.setHorizontalAlignment(JLabel.CENTER);
			south.add(waitingHost);
		}
        t.add(createGamePanel(),BorderLayout.CENTER);
        t.add(south,BorderLayout.SOUTH);
		return t;
	}
	public JPanel createPlayerPanel(){
		JPanel t = new JPanel(new GridLayout(0,1,0,5));//0을 입력하면 제한없이 받는 거임.
		t.setPreferredSize(new Dimension(300,500));
		//플레이어는 아래로 계속 뜨게끔 만들 거임.
		t.setBackground(new Color(77,34,146));
		JLabel player = Main.NewLabel("플레이어 인원 "+Main.room.getUsers().size()+"/"+Main.room.getRoomSize(),23);
		player.setForeground(new Color(191,179,215));
		player.setBackground(new Color(77,37,148));
		t.add(player);

		int total=Main.room.getRoomSize();
		for(int i=0;i<Main.room.getUsers().size();i++){
			StringBuilder insertMsg = new StringBuilder(Main.room.getUsers().get(i).getId()+"");
			if(Main.room.getUsers().get(i).getId() == Main.my.getId()){
				insertMsg.append("(ME)");
			}
			JLabel temp = Main.NewLabel(insertMsg.toString(),20);
			t.add(temp);
		}

		for(int i=0;i<total-Main.room.getUsers().size();i++){
			JLabel temp = Main.NewLabel("비어 있음",18,new Color(76,41,160));
			temp.setForeground(new Color(119,70,224));
			t.add(temp);
		}
		return t;
	}

	public JPanel createGamePanel(){
    	JPanel t = new JPanel(new GridLayout(1,2));
		//첫 번쨰 버튼
        first = new JButton(firstsentence);
		first.addMouseListener(new Clicked());
		first.setHorizontalAlignment(JButton.CENTER);
		first.setOpaque(true);
		first.setBackground(Colors.GameMouserOut);

		//두 번째 버튼
        second= new JButton(secondsentence);
        second.setHorizontalAlignment(JButton.CENTER);
        second.addMouseListener(new Clicked());
		second.setOpaque(true);
		second.setBackground(Colors.GameMouserOut);

        t.add(first);
        t.add(second);
        return t;
    }
    class Clicked extends MouseAdapter{
    	@Override
    	public void mouseClicked(MouseEvent e) {
    		super.mouseClicked(e);
    		JButton temp = (JButton) e.getComponent();
    		if(temp.getText().charAt(1)== 'h') {//2번 클릭인지 확인
    			button_num = 2;
				second.setBackground(Colors.GameMouseHover);
				first.setBackground(Colors.GameMouserOut);
    		}else {
    			button_num = 1;
				first.setBackground(Colors.GameMouseHover);
				second.setBackground(Colors.GameMouserOut);
    		}
    	}
    	@Override
    	public void mousePressed(MouseEvent e) {
    		super.mousePressed(e);
    		JButton temp = (JButton) e.getComponent();
    		if(temp.getText().charAt(1)== 'h') {//2번 클릭인지 확인
    			button_num = 2;
				second.setBackground(Colors.GameMouseHover);
				first.setBackground(Colors.GameMouserOut);
    		}else {
				first.setBackground(Colors.GameMouseHover);
				second.setBackground(Colors.GameMouserOut);
    			button_num = 1;
    		}
    	}
    }
}
