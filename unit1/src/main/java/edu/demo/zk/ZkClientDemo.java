package main.java.edu.demo.zk;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.apache.zookeeper.CreateMode;

public class ZkClientDemo {
    public static void main(String[] args) {
        // 创建一个zk客户端
        ZkClient client = new ZkClient("localhost:2181");
        client.setZkSerializer((ZkSerializer) new MyZkSerializer());

        client.create("/zk", "456", org.apache.zookeeper.CreateMode.PERSISTENT);

        client.create("/zk/app6", "123", CreateMode.PERSISTENT);

        client.subscribeChildChanges("/zk/app6", new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println(parentPath + "子节点发生变化：" + currentChilds);

            }
        });

        client.subscribeDataChanges("/zk/app6", new IZkDataListener() {
            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println(dataPath + "节点被删除");
            }

            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println(dataPath + "发生变化：" + data);
            }
        });

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
