import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    private static JFrame frame;
    static void init(JPanel t){
        t.setLayout(null);//패널 자유롭게 셋팅하기
        t.setBackground(new Color(115,52,211));
        JLabel Logo =new JLabel("DREAM OUT");
        Logo.setLocation(15,5);
        Logo.setFont(new Font("궁서체",Font.ITALIC,30));
        Logo.setSize(200,110);
        t.add(Logo);
        t.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(e.getPoint());
            }
        });
        t.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                System.out.println(e.getPoint());
            }
        });
    }
    static public JLabel NewLabel(String insertMsg,int size){//빈 라벨
        return NewLabel(insertMsg,size,new Color(202,190,224));
    }
    static public JLabel NewLabel(String insertMsg,int size,Color color){
        JLabel t = new JLabel(insertMsg);
        t.setFont(new Font("맑은 고딕",Font.BOLD,size));
        t.setHorizontalAlignment(JLabel.CENTER);
        t.setBackground(color);
        t.setOpaque(true);
        return t;
    }
    protected static JFrame getFrame() {
    	return frame;
    }
    static public JPanel createPlayerPanel(){
        JPanel t = new JPanel(new GridLayout(0,1,0,5));//0을 입력하면 제한없이 받는 거임.
        //플레이어는 아래로 계속 뜨게끔 만들 거임.
        t.setBackground(new Color(77,34,146));

        JLabel player = NewLabel("플레이어 인원 0/8",23);
        player.setForeground(new Color(191,179,215));
        player.setBackground(new Color(77,37,148));
        t.add(player);

        for(int i=0;i<15;i++){
            JLabel temp = NewLabel("비어 있음",18,new Color(76,41,160));
            temp.setForeground(new Color(119,70,224));
            t.add(temp);
        }
        return t;
    }


    Main(){
        frame = new JFrame("DreamOut");
        frame.setSize(1280,720);
        frame.add(new StartLogin(frame));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    public static void main(String[] args) {new Main();}
}
