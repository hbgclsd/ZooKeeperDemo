package main.java.edu.dongnao.zk;

public class OrderCodeGeneratorSingle {

    static class InstanceHolder {
        private static OrderCodeGenerator instance = new OrderCodeGenerator();
    }

    public static OrderCodeGenerator getInstance() {
        return InstanceHolder.instance;
    }

}
