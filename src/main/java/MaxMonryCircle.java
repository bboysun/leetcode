/**
 * @Author: darrylsun
 * @Description: 给定一个数组 [1,2,3,4]，不能取连续角标的数据，找到该数组中最大值，这个数组是个环，收尾不能同时被选中
 * 例子：
 * [1,2,3,4] -> 6
 * [7,2,3,5] -> 10
 *
 * @Date: 2023/7/25
 */
public class MaxMonryCircle {


    public static void main(String[] args) {
        int[] moneyArr = {7,2,3,5};
        int money = getMoney(moneyArr, 0, moneyArr.length - 1);
        int money2 = getMoney(moneyArr, 1, moneyArr.length);
        System.out.println(Math.max(money, money2));
    }

    /**
     * 获取最大
     * @param moneyArr
     * @param start 开始位置
     * @param end 结束位置
     * @return
     */
    public static int getMoney(int[] moneyArr, int start, int end) {
        if (moneyArr == null || moneyArr.length == 0) {
            return 0;
        }
        if (moneyArr.length == 1) {
            return moneyArr[0];
        }
        if (moneyArr.length == 2) {
            return Math.max(moneyArr[0], moneyArr[1]);
        }

        int temp = moneyArr[start + 2];

        for (int i=start+2; i<end; i++) {
            temp = Math.max(temp + moneyArr[i-2], moneyArr[i-1]);
        }
        return temp;
    }

}
