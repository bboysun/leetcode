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

	public static void main(String[] args) {
		int[] arrInt = {-2,-1,1,2,3,4,-5};
		// solution 依赖数据排序，事件复杂度最好也就 O(NlogN)
		System.out.println(solution(arrInt));
	}
}
