import java.util.Arrays;

/**
 * @Auther: Darryl
 * @Description: 给定一个数组，然后找到该数据中三个数乘积最大值
 * 如：[-2,-1,1,2,3,4,-5]
 * 结果输出：40
 * @Date: 2023/08/15
 */
public class ThreeNumProduct {

	public static int solution(int[] arrInt) {
		/**
		 * 中学学习的分类假设：
		 * 1。如果全是正数：则取数组中三个最大的数的乘积
		 * 2。如果全是负数：则取数组中三个最大的数的乘积
		 * 3。如果有正有负：则取数组中三个最大的数的乘积 和 两个最小的负数 + 一个最大的正数的乘积，两个乘积的取较大值
		 * 最终也就两种结果：1。三个最大的数据乘积；2。两个最小的负数 + 一个最大的正数乘积；
		 * 这里面的前提是我们排序好
		 */
		Arrays.sort(arrInt);
		int length = arrInt.length;
		int max = Math.max(arrInt[length - 1] * arrInt[length - 2] * arrInt[length - 3],
				arrInt[0] * arrInt[1] * arrInt[length - 1]);
		return max;
	}

	public static int solution2(int[] arrInt) {
		/**
		 * 一般求一个数组的最大值或者最小值，优先想到的就是线性扫描的思想
		 * 线性扫描的思想就是记录一个特别大（小）的临时变量，然后不停的和数组中的元素进行比较替换，从而取的最小（大）值
		 * 这道题简单分析就是找数组中的最大值和最小值的问题，然后进行比较
		 */
		int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
		int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
		for (int i = 0; i < arrInt.length; i++) {
			if (arrInt[i] < min1) {
				min2 = min1;
				min1 = arrInt[i];
			} else if (arrInt[i] < min2) {
				min2 = arrInt[i];
			}

			if (arrInt[i] > max1) {
				max3 = max2;
				max2 = max1;
				max1 = arrInt[i];
			} else if (arrInt[i] > max2) {
				max3 = max2;
				max2 = arrInt[i];
			} else if (arrInt[i] > max3) {
				max3 = arrInt[i];
			}
		}
		int max = Math.max(max1 * max2 * max3, min1 * min2 * max1);
		return max;
	}

	public static void main(String[] args) {
		int[] arrInt = {-2, -1, 1, 2, 3, 4, -5};
		// solution 依赖数据排序，事件复杂度最好也就 O(NlogN)
		System.out.println(solution(arrInt));

		System.out.println(solution2(arrInt));
	}
}
