package Main.src;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.*;

import java.rmi.UnknownHostException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class StartLogin extends JPanel{
	private JButton b_login, b_signup;
	private Font f1;
	private JLabel a;
	private JTextField email,pw;
	private JLabel Jlabel;
	

	public StartLogin() {
		/*final int LABEL_WIDTH = 100;
		final int LABEL_HEIGHT = 100;
		int xPosition = 100;
		int yPosition = 100;*/
		setLayout(null);
		buildGUI();
    
		//Jlabel = new JLabel("좌표");

        setSize(1280,720);
        
        
        
        setVisible(true);
        

        
        /*Jlabel.setBounds(xPosition, yPosition, LABEL_WIDTH, LABEL_HEIGHT);
        add(Jlabel);
        System.out.println("좌표값:"+Jlabel.getBounds());
        addEventListener();*/
       
        
        
        
        
    }
	
	public void buildGUI(){
		add(createNamePanel());
		add(createLoginPanel());
        add(createButtonPanel());
       
    }
	
	
	public JPanel createNamePanel(){
        JPanel t = new JPanel();
       

        f1 = new Font("바탕",Font.ITALIC, 60);
        a = new JLabel("DreamOut");
        a.setHorizontalAlignment(JLabel.CENTER);
        
        a.setFont(f1);
        t.setBounds(426, 20, 426,100);
        t.add(a);
        
        
        
        return t;
    }
	
	public JPanel createLoginPanel() {
		JPanel t = new JPanel(new GridLayout(2,2,5,5));
		 t.setBorder(new TitledBorder(new LineBorder(Color.black,1)));
		
		JLabel lab1 = new JLabel("이메일");
		email = new JTextField(20);
		
		JLabel lab2 = new JLabel("비밀번호");
		pw = new JTextField(20);
		
		t.setBounds(210,300,290,150);
		
		t.add(lab1);
		t.add(email);
		t.add(lab2);
		t.add(pw);
		
		
		return t;
	}
	
	public JPanel createButtonPanel() {
		JPanel t = new JPanel(new GridLayout(2,1,5,5));
		 t.setBorder(new TitledBorder(new LineBorder(Color.black,1)));

		t.setBounds(800,300,270,150);
		
		 b_signup = new JButton("회원가입");
		 b_login = new JButton("로그인");
		 
		 t.add(b_signup);
		 t.add(b_login);
		
		return t;
	}
	
	/*private void addEventListener() {
		this.addMouseListener(this);
	}*/
	
	public void mouseClicked(MouseEvent e) {
		Rectangle r = Jlabel.getBounds();
		e.getX();
		e.getY();
		System.out.println("(x,y): "+e.getX()+e.getY());
		Jlabel.setLocation(e.getX()-r.width,e.getY()-r.height);
	}
	
	
}
