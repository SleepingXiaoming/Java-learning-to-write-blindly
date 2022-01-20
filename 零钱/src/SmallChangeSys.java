package 小项目.零钱通.src;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SmallChangeSys {

    public static void main(String[] args) {

        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        String key = "";
        String details = "------------------------零钱通明细------------------------";
        //3. 完成收益入账，完成功能驱动程序员增加新的变化和代码
        double money = 0;
        double balance = 0;//余额
        Date data = null;//data 是 java.util.Date类型，表示日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以用于日期格式化
        String note = "";
        do {
            System.out.println("\n========================零钱通菜单========================");
            System.out.println("\t\t\t\t\t1\t 零钱通明细");
            System.out.println("\t\t\t\t\t2\t 收益入账");
            System.out.println("\t\t\t\t\t3\t 消     费");
            System.out.println("\t\t\t\t\t4\t 退     出");


            System.out.println("请选择(1-4):");
            key = scanner.next();

            switch (key) {
                case "1":
                    System.out.println(details);
                    break;
                case "2":
                    System.out.print("收益入账金额：");
                    money = scanner.nextDouble();
                    //money的值应该进行校验
                    if (money <= 0) {
                        System.out.println("金额输入有误，请查证");
                        break;
                    }
                    //拼接收益入账信息到details
                    balance += money;
                    data = new Date();
                    details += "\n收益入账\t\t+" + money + "\t\t" + sdf.format(data) + "\t\t余额：" + balance;
                    break;
                case "3":
                    System.out.print("消费金额：");
                    money = scanner.nextDouble();
                    if (money > balance) {
                        System.out.println("余额不足，无法进行消费");
                        break;
                    } else if (money <= 0) {
                        System.out.println("输入有误，请查证");
                        break;
                    }
                    System.out.print("消费说明：");
                    note = scanner.next();
                    balance -= money;
                    data = new Date();
                    details += "\n" + note + "\t\t-" + money + "\t\t" + sdf.format(data) + "\t\t余额：" + balance;

                    break;
                case "4":
                    String tag = "";
                    loop = step4(tag);

            }
        } while (loop);
    }

    public static void menu() {

    }

    public static boolean step4(String choice) {
        while (true) {
            System.out.println("您确定要退出吗？y/n");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.next();
            if ("y".equals(choice) || "n".equals(choice)) {
                break;
            } else {
                System.out.println("输入有误");
            }
        }

        if ("y".equals(choice)) {
            System.out.println("----------------------退出零钱通系统----------------------");
            return false;
        }
        return true;
    }

}
