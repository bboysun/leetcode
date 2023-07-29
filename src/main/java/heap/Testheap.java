package heap;

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

    }
}
