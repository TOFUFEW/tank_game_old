package Assignment2;

import Assignment2.logic.TankManager;

public class Main {
    public static void main(String[] args) {
        TankManager manager = new TankManager(10, 10, 4, 4);
        System.out.println(manager.getMap());
    }
}
