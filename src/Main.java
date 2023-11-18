import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main {
    private static JFrame frame;
    protected static Socket s;//소켓
    protected static ObjectOutputStream out;
    protected static ObjectInputStream in;
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

    public static void test(){
        try {
            s = new Socket("localhost",54321);
            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());
            out.writeObject(new ObjectMsg(new User(12,12),"회원가입"));
            in.readObject();
            new reapaintThread().start();
        } catch (IOException | ClassNotFoundException ignored) {}
    }
    Main(){
        test();//테스트 서버 접속
        frame = new JFrame("DreamOut");
        frame.setSize(1280,720);
        frame.add(new GameStartRoom(frame));
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setResizable(false);

    }

    public static void main(String[] args) {new Main();}

    static class reapaintThread extends Thread{
        JFrame frame;
        Socket paintSocket;
        ObjectInputStream in;
        reapaintThread(){
            frame=Main.frame;
            try{
                paintSocket = new Socket("localhost",54321);
                in = new ObjectInputStream(paintSocket.getInputStream());

            }catch(IOException ignored){}
        }

        @Override
        public void run() {
            super.run();
            //해당하는 곳에서는 repaint명령이 떨어지면 계속 화면을 그려줘야 된다.

            while(true){
                try{
                    ObjectMsg msg =(ObjectMsg) in.readObject();
                    if(msg.getMsg()==null);
                    else if(msg.getMsg().equals("repaint")){//화면 다시 그려주기
                        frame.revalidate();
                        frame.repaint();
                        System.out.println("화면을 다시 그렸습니다.");
                    }
                }catch (IOException | ClassNotFoundException e){
                    System.err.println("잘못된 데이터를 불러왔습니다.");
                    break;
                }
            }
        }
    }
}
