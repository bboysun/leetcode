/**
 * @Author: darrylsun
 * @Description: 给一个有序的数组，统计这个数组中去重后的个数；保证空间复杂度 O(1)
 * 如：数组：[1,3,3,6,8,9,9]
 * 输出：5
 * @Date: 2023/8/9
 */
public class CountRemoveDuplicated {

    public static void main(String[] args) {
        int[] intArr = {1,3,3,4,6,7,8,8,9,9,9};
        System.out.println(countRemoveDuplicated(intArr));
    }

    /**
     * 统计数组去重后总个数
     * @param intArr
     * @return
     */
    public static int countRemoveDuplicated(int[] intArr) {
        int i = 0, j = 1;
        for ( ; j < intArr.length; ) {
            if (intArr[i] != intArr[j]) {
                // 用于下一轮的比较
                intArr[i+1] = intArr[j];
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i + 1;
    }
}
