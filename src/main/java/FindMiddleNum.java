import java.util.Arrays;

/**
 * @Author: darrylsun
 * @Description: 寻找一个数组的中间值元素，中间值的特征是，中间值的左右的所有数的和等于右边的所有数的和
 * 如果一个数组中存在多个这样的中间值，那么取左边第一个的中间值元素
 * 给一个数组如：[3,9,5,1,11,6]
 * 返回输出：5
 * @Date: 2023/8/10
 */
public class FindMiddleNum {

    public static int findMiddle(int[] intArr) {
        // 先求和
        int sum = Arrays.stream(intArr).sum();
        if (sum == 0) {
            return intArr[0];
        }
        int leftTotal = 0;

        for (int i = 0; i < intArr.length - 1; i++) {
            leftTotal += intArr[i];
            if ((sum - leftTotal - intArr[i + 1]) == leftTotal) {
                return intArr[i + 1];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] intArr = {-1,3,9,-9,11};
        System.out.println(findMiddle(intArr));
    }
}
