import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Enumeration;

/**
 * @author :hjt
 * @date : 2023/1/3
 */
public class UserLogin extends JPanel  {
    public MainRun test;

    private JLabel label_welcome;

    public JLabel label = new JLabel();
    private JLabel label_id;            //label 提示用户名输入
    private JTextField tf_id;            //用于接收用户名的输入框

    private JLabel label_pw;
    private JTextField tf_pw;

    private JButton bt_id;  //登录
    private JButton zc_id;  //注册


    private final Box base = Box.createVerticalBox();//盒子
    private final Box box1 = Box.createHorizontalBox();
    private final Box box2 = Box.createHorizontalBox();
    private final Box box3 = Box.createHorizontalBox();
    private final Box box4 = Box.createHorizontalBox(); //登录名称
    private final Box box5 = Box.createHorizontalBox(); //用户注册按钮名称


    private String ID ="";





    public UserLogin() {

        label.setText("四则运算系统登录页面");
        label.setFont(new Font("微软雅黑",1,32));
        label.setForeground(Color.BLUE);

        Font f1 = new Font("宋体", Font.PLAIN, 20);
        InitGlobalFont(f1);
        Font f2 = new Font("楷体", Font.PLAIN, 30);

        label_id = new JLabel("用户名:");
        tf_id = new JTextField(20);
        label_pw = new JLabel("密码  :");
        tf_pw = new JTextField(20);

        bt_id = new JButton("用户登录");
        /**登录后逻辑处理*/
        bt_id.addActionListener(new Myaction());

        zc_id = new JButton("用户注册");
        zc_id.addActionListener(new Myaction1());



        this.label_welcome = new JLabel("四则运算考试系统");

        box4.add(label);
        box1.add(label_id);
        box1.add(tf_id);
        box2.add(label_pw);
        box2.add(tf_pw);
        box3.add(bt_id);
        box3.add(zc_id);

        base.add(box4);
        base.add(Box.createVerticalStrut(40));
        base.add(box1);
        base.add(Box.createVerticalStrut(40));
        base.add(box2);
        base.add(Box.createVerticalStrut(40));
        base.add(box3);
        base.add(Box.createVerticalStrut(40));
        base.add(box3);
        base.add(Box.createVerticalStrut(40));
        this.add(base);



    }


    private void InitGlobalFont(Font font) {                    //这个用来设置部件的全局字体
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }

    class Myaction extends JFrame implements ActionListener{

        /***
         * 按钮处理逻辑
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            String getID = tf_id.getText();                         //获得账号，密码
            String getPW = tf_pw.getText();
            if(getID.equals("") )
                showWarningDialog("用户名不能为空");
            else {
                File file=new File("account.txt");         //创建account文件，存储账号和密码
                if(!file.exists())
                {
                    try {
                        file.createNewFile();
                    } catch (IOException ex) {
                        // TODO Auto-generated catch block
                        showWarningDialog("创建账户文件accont.txt失败");
                    }
                }
                FileReader fileReader;                   //读取account中的文件，比对账号和密码
                try {
                    fileReader = new FileReader("account.txt");
                    BufferedReader br = new BufferedReader(fileReader);
                    String line,password = null;
                    boolean flag = false;
                    while ((line = br.readLine()) != null) {
                        String[] str = line.split(" ");          //将每行的信息拆分成账号和密码
                        if(str[0].equals(getID)) {
                            flag = true;
                            password = str[1];
                            break;
                        }
                    }
                    br.close();
                    fileReader.close();

                    if(!flag){                                  //如果用户不存在，则创建该用户，即向文件中写入信息
                        showWarningDialog("用户名不存在，已为自动跳转到注册页面注册！");
                        TestRegisterUser testRegisterUser = new TestRegisterUser();
//                        FileWriter fileWriter = new FileWriter("account.txt",true);
//                        BufferedWriter bw = new BufferedWriter(fileWriter);
//                        bw.write(getID+" "+getPW+"\n");
//                        bw.close();
//                        fileWriter.close();
                    }
                    else{
                        if(password.equals(getPW))              //如果账号存在且密码真确，则登录成功
                        {
                            ID = getID;
                            showWarningDialog("欢迎您，"+ ID +"\n");
                            dispose();
                            /**登录成功后的逻辑处理*/
                            MainPanel mainPanel = new MainPanel();
                        }
                        else                                    //密码不正确，提示输入错误
                            showWarningDialog("密码输入错误!");
                    }
                } catch (IOException ex) {
                    showWarningDialog("读取账号库文件失败");
                }



                ID = getID;

            }



        }
    }

    /***
     * 用户注册逻辑处理
     */
    class Myaction1 extends JFrame implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {

                dispose();
                //跳转到注册页面
            TestRegisterUser testRegisterUser = new TestRegisterUser();


        }
    }



    /***
     * 弹窗设置
     * @param message
     */
    private void showWarningDialog(String message){                         //弹出一个对话框
        final JDialog dialog = new JDialog(test, "警告", true);
        // 设置对话框的宽高
        dialog.setSize(300, 150);
        // 设置对话框大小不可改变
        dialog.setResizable(false);
        // 设置对话框相对显示的位置
        dialog.setLocationRelativeTo(test);

        // 创建一个标签显示消息内容
        JLabel messageLabel = new JLabel(message);

        // 创建一个按钮用于关闭对话框
        JButton okBtn = new JButton("确定");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 关闭对话框
                dialog.dispose();
            }
        });

        JPanel panel = new JPanel();

        // 添加组件到面板
        panel.add(messageLabel);
        panel.add(okBtn);

        // 设置对话框的内容面板
        dialog.setContentPane(panel);
        // 显示对话框
        dialog.setVisible(true);

    }
}
