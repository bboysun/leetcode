package heap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @Author: darrylsun
 * @Description: 获取当前运行java进程的初始化对内存大小
 * @Date: 2023/07/29
 */
public class Testheap {

    public static void main(String[] args) {
        //返回 JVM 堆大小
        long initalMemory = Runtime.getRuntime().totalMemory() / 1024 /1024;
        //返回 JVM 堆的最大内存
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 /1024;

        System.out.println("-Xms : "+initalMemory + "M");
        System.out.println("-Xmx : "+maxMemory + "M");

        List<OOMObject> res = new ArrayList<>();
        while (true) {
            UUID uuid = UUID.randomUUID();
            OOMObject oomObject = new OOMObject(uuid.toString());
            res.add(oomObject);
        }

    }

    static class OOMObject {
        private String name;

        public OOMObject(String name) {
            this.name = name;
        }
    }
}
