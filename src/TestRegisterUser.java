import javax.swing.*;

/**
 * @author :hjt
 * @date : 2023/1/4
 */
public class TestRegisterUser extends JFrame {
    public RegisterUser registerUser;
    public TestRegisterUser(){
        registerUser = new RegisterUser();
        this.add(registerUser);
        this.setTitle("四则运算考试系统");
        this.setSize(1000,1000);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        TestRegisterUser testRegisterUser = new TestRegisterUser();
    }
}
