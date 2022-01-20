package 小项目.房屋出租.src.view;

import 小项目.房屋出租.src.domain.House;
import 小项目.房屋出租.src.service.HouseService;
import 小项目.房屋出租.src.utils.Utility;

public class HouseView {

    private boolean loop = true;//控制显示菜单

    private HouseService houseService = new HouseService(10);//设置数组的大小为10

    //显示主菜单
    public void mainMenu() {
        do {
            System.out.println("\n=============房屋出租系统菜单=============");
            System.out.println("\t\t\t1.新增房屋");
            System.out.println("\t\t\t2.查找房源");
            System.out.println("\t\t\t3.删除房屋信息");
            System.out.println("\t\t\t4.修改房屋信息");
            System.out.println("\t\t\t5.房屋列表");
            System.out.println("\t\t\t6.退    出");
            System.out.println("请输入你的选择(1-6)");

            //接收用户选择的菜单
            char key = Utility.readChar();
            switch (key) {
                case '1' -> addHouse();
                case '2' -> findHouse();
                case '3' -> delHouse();
                case '4' -> update();
                case '5' -> listHouse();
                case '6' -> exit();
                default -> System.out.println("输入的有误，请查证后输入");
            }
        } while (loop);
    }


    //编写listHouses()显示房屋列表
    public void listHouse() {
        System.out.println("====================================================");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
        House[] houses = houseService.list();
        for (House house : houses) {
            if (house == null) break;
            System.out.println(house);
        }
        System.out.println("======================房屋显示完毕======================");
    }


    //编写addHouse()接收输入，创建House对象，调用add方法
    public void addHouse() {
        System.out.println("======================添加房屋======================");
        System.out.print("姓名：");
        String name = Utility.readString(8);
        System.out.print("电话：");
        String phone = Utility.readString(12);
        System.out.print("地址：");
        String address = Utility.readString(16);
        System.out.print("月租：");
        double rent = Utility.readDouble();
        System.out.print("状态：");
        String state = Utility.readString(3);

        //创建一个新的House对象，注意id是系统分配，无法自行输入
        House newHouse = new House(0, name, phone, address, rent, state);

        if (houseService.add(newHouse)) {
            System.out.println("==========添加房屋成功==========");
        } else {
            System.out.println("==========添加房屋失败==========");
        }
    }

    //编写delHouse()接收输入的id，调用Service的del方法
    public void delHouse() {
        System.out.println("======================添加房屋======================");
        System.out.println("请输入待删除房屋的编号(-1退出)");
        int delId = Utility.readInt();
        if (delId == -1) {
            System.out.println("==================放弃删除房屋信息==================");
            return;
        }
        System.out.println("请确认是否删除(Y/N)，小心选择");
        //必须输入y/n，否则无法结束循环
        char choice = Utility.readConfirmSelection();
        if (choice == 'Y') {
            if (houseService.del(delId)) {
                System.out.println("==================删除房屋信息成功==================");
            } else {
                System.out.println("编号不存在，删除失败");
            }
        } else {
            System.out.println("==================放弃删除房屋信息==================");
        }
    }

    public void exit() {
        System.out.println("请输入确认退出");
        char c = Utility.readConfirmSelection();
        if (c == 'Y') {
            loop = false;
        }
    }

    //根据id查找房屋信息
    public void findHouse() {
        System.out.println("======================查找房屋======================");
        System.out.println("请输入需要查找的id：");
        int findId = Utility.readInt();
        House house = houseService.findById(findId);
        if (house != null) {
            System.out.println(house);
        } else {
            System.out.println("未查找到相应的信息");
        }
    }

    //根据id修改房屋信息
    public void update() {
        System.out.println("======================修改房屋信息======================");
        System.out.println("请输入待修改房屋编号(-1表示退出)");
        int updateId = Utility.readInt();
        if (updateId == -1) {
            System.out.println("====================放弃修改房屋信息====================");
            return;
        }
        House house = houseService.findById(updateId);
        if (house == null) {
            System.out.println("==================所修改的房屋信息不存在==================");
            return;
        }

        //修改房屋信息
        System.out.print("姓名(" + house.getName() + "):");
        String name = Utility.readString(8, house.getName());
        house.setName(name);

        System.out.print("电话(" + house.getPhone() + "):");
        String phone = Utility.readString(12, house.getPhone());
        house.setPhone(phone);

        System.out.print("地址(" + house.getAddress() + "):");
        String address = Utility.readString(16, house.getAddress());
        house.setAddress(address);

        System.out.print("租金(" + house.getRent() + "):");
        double rent = Utility.readDouble(house.getRent());
        house.setRent(rent);

        System.out.print("状态(" + house.getState() + "):");
        String state = Utility.readString(8, house.getState());
        house.setState(state);

        System.out.println("==================修改房屋信息成功==================");
    }
}
