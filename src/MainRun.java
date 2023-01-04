import javax.swing.*;

/**
 * @author :hjt
 * @date : 2023/1/3
 */
public class MainRun extends JFrame {
    public UserLogin userLogin;
    public MainRun(){
        userLogin = new UserLogin();
        this.add(userLogin);
        userLogin.setBounds(0,0,1000,1000);
        this.setTitle("四则运算考试系统");
        this.setSize(1000,1000);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
//    public Test(){
//        registerUser = new RegisterUser();
//        this.add(registerUser);
//        registerUser.setBounds(0,0,1000,1000);
//        this.setTitle("四则运算考试系统");
//        this.setSize(1000,1000);
//        this.setLocationRelativeTo(null);
//        this.setResizable(false);
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setVisible(true);
//    }

    public static void main(String[] args) {
        MainRun userLogin = new MainRun();
    }
}
