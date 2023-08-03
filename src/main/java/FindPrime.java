/**
 * @Author: darrylsun
 * @Description: 给指定整数范围内查找范围内的素数的个数；
 *         素数是只能被1和自身整数的数就是素数（0，1除外）
 *         如：给100，返回25（表示有25个素数）
 * @Date: 2023/8/2
 */
public class FindPrime {

    public static void main(String[] args) {
        System.out.println(baoli(100));
        System.out.println(eAlgorithm(100));
        System.out.println(eAlgorithm2(100));
    }

    public static int eAlgorithm(int n) {
        int count = 0;
        // 数据元素 false -- 素数；true -- 合数
        boolean[] isNotPrime = new boolean[n];
        // 0 和 1 排除，从 2 开始
        for (int i = 2; i < n; i++) {
            if (!isNotPrime[i]) {
                count++;
                // 遍历标记合数，因为都存在两个数的乘积
                for (int j = 2 * i; j < n; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }
        return count;
    }


    /**
     * 可优化的遍历次数
     * @param n
     * @return
     */
    public static int eAlgorithm2(int n) {
        int count = 0;
        // 数据元素 false -- 素数；true -- 合数
        boolean[] isNotPrime = new boolean[n];
        // 0 和 1 排除，从 2 开始
        for (int i = 2; i < n; i++) {
            if (!isNotPrime[i]) {
                count++;
                // 遍历标记合数，因为都存在两个数的乘积，优化这里，不再是从2开始递增求积，可以从i开始
                for (int j = i * i; j < n; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }
        return count;
    }

    /**
     * 暴力穷举范围内的素数的个数
     *
     * @param n
     * @return
     */
    public static int baoli(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 校验当前数是否为素数
     *
     * @param x
     * @return
     */
    private static boolean isPrime(int x) {
        // 这里优化了，迭代次数
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}
