/**
 * @Author: darrylsun
 * @Description: 给定一个数组 [1,2,3,4]，不能取连续角标的数据，找到该数组中最大值
 * 例子：
 * [1,2,3,4] -> 6
 * [7,2,3,5] -> 12
 *
 * @Date: 2023/7/21
 */
public class MaxMoney {

    /**
     * 首先想到是的 递归，从右往左，起始位置 i，每次递归 arr[i] + arr[i-2] 和 arr[i-1] 返回最大值
     * @return
     */
    public static int maxMoney1(int[] money, int index) {
        if (money == null) {
            return 0;
        }
        if (index < 0) {
            return 0;
        }
        return Math.max((money[index] + maxMoney1(money, index - 2)), maxMoney1(money, index - 1));
    }

    /**
     * 动态规划，需要一个额外的数组记录每个小路径下的最优值
     * @param money
     * @return
     */
    public static int maxMoney2(int[] money) {
        if (money == null || money.length == 0) {
            return 0;
        }
        if (money.length == 1) {
            return money[0];
        }
        if (money.length == 2) {
            return Math.max(money[0], money[1]);
        }
        int[] res = new int[money.length];
        res[0] = money[0];
        res[1] = Math.max(money[0], money[1]);
        for (int i=2; i<money.length; i++) {
            res[i] = Math.max(res[i-1], res[i-2] + money[i]);
        }
        return res[res.length - 1];
    }

    public static void main(String[] args) {
        int[] money = {7,2,3,5};
        int res = maxMoney1(money, money.length - 1);
        System.out.println(res);

        int res2 = maxMoney2(money);
        System.out.println(res2);


        int[] money2 = {1,2,3,5};
        int res3 = maxMoney1(money2, money2.length - 1);
        System.out.println(res3);

        int res4 = maxMoney2(money2);
        System.out.println(res4);
    }
}
