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
import javax.swing.text.DefaultStyledDocument;


public class DreamOut2 extends JFrame implements MouseListener{
	private JLabel Jlabel;
	private JButton mkroom;
	private JTextPane t_display1,t_display2;
	private JScrollPane scroll;
	
	
	public DreamOut2() {
		/*final int LABEL_WIDTH = 100;
		final int LABEL_HEIGHT = 100;
		int xPosition = 100;
		int yPosition = 100;
		*/
    
		//Jlabel = new JLabel("좌표");
		//setLayout(null);
		//buildGUI();
		
		JPanel t = new JPanel();
		t.setLayout(null);
		mkroom = new JButton("방만들기");
		
		scroll = new JScrollPane();//t_display);
		mkroom.setBounds(1140,10,110,30);
		scroll.setBounds(0,40,1265,680);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		t.add(mkroom);
		t.add(scroll);
		t_display1 = new JTextPane();

		t_display1.setBounds(100,100,400,200);
		t.add(t_display1);
		Font f1 = new Font("바탕",Font.ITALIC, 20);
		Font f2 = new Font("바탕", Font.ITALIC, 15);
        JLabel a = new JLabel("133번방");
        a.setHorizontalAlignment(JLabel.CENTER);
        a.setOpaque(true); //라벨 색상 변경 위해서
        a.setBackground(Color.orange);
        a.setFont(f1);
		a.setBounds(0,0,100,200);
		JLabel a1 = new JLabel("id: guest1");
		a1.setFont(f2);
		a1.setBounds(110,0, 100, 50);
		t_display1.add(a);
		t_display1.add(a1);
		
		t_display2 = new JTextPane();

		t_display2.setBounds(600,100,400,200);
		t.add(t_display2);
        JLabel b = new JLabel("141번방");
        b.setHorizontalAlignment(JLabel.CENTER);
        b.setFont(f1);
		b.setBounds(0,0,100,200);
		b.setOpaque(true);
		b.setBackground(Color.yellow);
		JLabel b1 = new JLabel("id: guest2");
		b1.setFont(f2);
		b1.setBounds(110,0,100,50);
		t_display2.add(b);
		t_display2.add(b1);
		
		
		add(t);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280,720);
        setVisible(true);
        

        
        /*Jlabel.setBounds(xPosition, yPosition, LABEL_WIDTH, LABEL_HEIGHT);
        add(Jlabel);
        System.out.println("좌표값:"+Jlabel.getBounds());
        addEventListener();*/

    }
	
	private void addEventListener() {
	this.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
	Rectangle r = Jlabel.getBounds();
	e.getX();
	e.getY();
	System.out.println("(x,y): "+e.getX()+e.getY());
	Jlabel.setLocation(e.getX()-r.width,e.getY()-r.height);
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {

        new DreamOut2();
    }
}
