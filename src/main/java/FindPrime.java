/**
 * @Author: darrylsun
 * @Description: 给指定整数范围内查找范围内的素数的个数；
 * 素数是只能被1和自身整数的数就是素数（0，1除外）
 * 如：给100，返回25（表示有25个素数）
 * @Date: 2023/8/2
 */
public class FindPrime {

    public static void main(String[] args) {
        System.out.println(baoli(100));
    }

    /**
     * 暴力穷举范围内的素数的个数
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
     * @param x
     * @return
     */
    private static boolean isPrime(int x) {
       for (int i = 2; i < x; i++) {
           if (x % i == 0) {
               return false;
           }
       }
       return true;
    }
}
