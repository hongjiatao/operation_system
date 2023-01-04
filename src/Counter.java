/**
 * @author hjt
 * @date:2022/12/31
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 倒计时
 */
public class Counter {

    private JFrame frame;
    private JLabel jl0;

    private ScheduledThreadPoolExecutor scheduled;


    /**
     *
     * @description: 一分钟后
     * @return
     */
    private static Date getTime2() {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, +1);
        return cal.getTime();
    }




    public static void main(String[] args) throws ParseException {
//        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String format = dateFormat.format(getTime2());
//        System.out.println("一分钟后：" + dateFormat.format(getTime2()));
        //一分钟后
        new Counter().timer(getTime2());

    }




    public void timer(Date dateStr) {
        Date end = dateStr;
        scheduled.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long time = (end.getTime() - 1 - System.currentTimeMillis()) / 1000;
                if (time <= 0) {
                    stopTimer();
                    jl0.setText("倒计时结束");
                    return;
                }
                long hour = time / 3600;
                long minute = (time - hour * 3600) / 60;
                long seconds = time - hour * 3600 - minute * 60;
                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append("<html><br>距离").append(dateStr).append("还有<br><br>")
                stringBuilder.append("<html>")
                        .append(hour).append("时 ").append(minute).append("分 ").append(seconds).append("秒 ")
                        .append("</html>");

                jl0.setText(stringBuilder.toString());
            }
        }, 0, 1, TimeUnit.SECONDS);

    }

    /**
     * 停止定时器
     */
    private void stopTimer() {
        if (scheduled != null) {
            scheduled.shutdownNow();
            scheduled = null;
        }
    }

    /* 构造 实现界面的开发 GUI */
    public Counter() {
        scheduled = new ScheduledThreadPoolExecutor(2);
        init();
    }

    /* 组件的装配 */
    private void init() {
        frame = new JFrame("倒计时");
        jl0 = new JLabel();

        JPanel jp = new JPanel();
        jp.add(jl0);

        frame.add(jp);

        frame.setVisible(true);
        frame.setLocation(300, 400);
        frame.setSize(330, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }




}