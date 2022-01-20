package 小项目.房屋出租.src.service;


import 小项目.房屋出租.src.domain.House;

/**
 * HouseService.java<==>类
 * 1.响应HouseView的调用
 * 2.完成对房屋的各种操作(增删查改) c(create)r(read)u(update)d(delete)
 */


public class HouseService {

    private House[] houses;//保存House对象
    private int houseNums = 0;//记录当前有多少个房屋信息
    private int idCounter = 1;//记录当前id增长到哪个值

    public HouseService(int size) {
        //new houses
        houses = new House[size];
//        houses[0] = new House(1, "小明", "234", "香坊", 234, "已出租");
//        houseNums++;
    }

    //list方法返回houses
    public House[] list() {
        return houses;
    }

    //add方法，添加新对象，返回Boolean
    public boolean add(House newHouse) {

        //扩容
        if (houseNums == houses.length) {

            House[] temp = new House[houses.length + 1];
            for (int i = 0; i < houses.length; i++) {
                temp[i] = houses[i];
            }
            houses = temp;
//              暂不考虑数组扩容
//            System.out.println("数组已满，不能再添加了");
//            return false;
        }


        //把newHouse对象加入列
        houses[houseNums++] = newHouse;
        idCounter++;
        newHouse.setId(idCounter);
        return true;
    }

    //del方法，删除一个房屋信息
    public boolean del(int delId) {
        //找到对应的下标
        int index = -1;
        for (int i = 0; i < houseNums; i++) {
            if (delId == houses[i].getId()) {
                index = i;

            }
        }
        if (index == -1) {//delId在数组之中不存在
            return false;
        }

        for (int i = index; i < houseNums - 1; i++) {
            houses[i] = houses[i + 1];
        }
        houses[--houseNums] = null;//将最后一个置为空
        return true;
    }

    //find方法返回House对象或者null
    public House findById(int findId) {
        for (int i = 0; i < houseNums; i++) {
            if (houses[i].getId() == findId) {
                return houses[i];
            }
        }
        return null;
    }
}
