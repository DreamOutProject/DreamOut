package com.Logic;

import com.CommunicateObject.MOD;
import com.CommunicateObject.MODE;
import com.CommunicateObject.User;
import com.Main.Main;
import com.Main.repaintThread;
import com.GUI.WaitRoom;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static com.CommunicateObject.MODE.*;
import static java.lang.Thread.sleep;

public class LoginLogic {
    public JTextField id;
    public JTextField pw;
    Main main;
    public LoginLogic(Main main, JButton login, JButton register, JTextField id, JTextField pw){
        //로그인 동작들을 여기서 시행
        this.main = main;
        this.id = id;
        this.pw = pw;
        register.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                //서버로 입력한 회원정보 보내기
                boolean flag = login(REGISTER_MODE);//
                if(flag){//가입 성공
                    JOptionPane.showMessageDialog(main.MainFrame,"성공적으로 가입되었습니다!","성공",JOptionPane.INFORMATION_MESSAGE);
                    //화면 넘기기 전 현재 가입 정보 main에 놔두기
                }else{
                    JOptionPane.showMessageDialog(main.MainFrame,"회원가입에 실패하였습니다.","실패",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                boolean flag = login(LOGIN_MODE);
                if(flag){//가입 성공
                    JOptionPane.showMessageDialog(main.MainFrame,"성공적으로 로그인되었습니다!","성공",JOptionPane.INFORMATION_MESSAGE);
                    //화면 넘기기 전 현재 가입 정보 main에 놔두기
                    int logID = Integer.parseInt(id.getText());
                    int logPW = Integer.parseInt(pw.getText());
                    main.ID = new User(logID,logPW);//아이디 결정 끝
                    main.repaint = new repaintThread(main);
                    main.repaint.start();
                    main.isrepaint=false;
                    main.transition(new WaitRoom(main));
                    main.isrepaint=true;
                }else{
                    JOptionPane.showMessageDialog(main.MainFrame,"로그인에 실패하였습니다.","실패",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
    public boolean login(MODE mod){
        boolean ret = false;
        int id;
        int pw;
        if(this.id.getText()==null)return ret;
        if(this.pw.getText()==null)return ret;
        try{
            id = Integer.parseInt(this.id.getText());
            pw = Integer.parseInt(this.pw.getText());
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"제대로 된 숫자를 입력해주세요","숫자확인",JOptionPane.ERROR_MESSAGE);
            return ret;
        }
        User user = new User(id,pw);
        user.setMod(mod);
        try{
            main.MainOutput.writeObject(user);//현재 아이디를 보내기
            MOD msg = (MOD)main.MainInput.readObject();//어떤 것인지 읽어봐야 되는데
            if(msg.getMOD() == SUCCESSED)ret =true;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }
}
