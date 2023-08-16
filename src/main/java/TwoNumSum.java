import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Darryl
 * @Description: 给定一个不重复的数组，同时给定一个 target 值，寻找这个数组中两个数之和等于 target 值的对应的 index 值
 * 如：数组[1,3,5,2,9,8]  target值：8
 * 则输出 [1,2]
 * @Date: 2023/08/15
 */
public class TwoNumSum {

	public static String solution(int[] arrInt, int target) {
		// 很容易想到的就是暴力算法
		for (int i = 0; i < arrInt.length; i++) {
			// 这里有个优化点，j 可以从 i + 1 位置开始寻找即可
			for (int j = i + 1; j < arrInt.length; j++) {
				if (target == arrInt[i] + arrInt[j]) {
					return i + "," + j;
				}
			}
		}
		return "-1,-1";
	}

	// 考虑如何降低时间复杂度，当然可以利用空间复杂度
	public static String solution2(int[] arrInt, int target) {
		// 利用一个map，存储数组对应的值和角标
		Map<Integer, Integer> cache = new HashMap<>();
		// 逆向思维，用 target - 数组中的值 = 数组中的另一个值
		for (int i = 0; i < arrInt.length; i++) {
			if (cache.keySet().contains(target - arrInt[i])) {
				return cache.get(target - arrInt[i]) + "," + i;
			}
			cache.put(arrInt[i], i);
		}
		return "-1,-1";
	}

	/**
	 * 假设数据是有序的，考虑用二分法
	 * 如：[1,3,5,7,8,9] target: 10
	 * 输出：0,5
	 */
	public static String solution3(int[] arrInt, int target) {
		for (int i = 0; i < arrInt.length; i++) {
			int start = i;
			int end = arrInt.length - 1;
			// 二分法查找的条件是，当搜索范围的起始位和终止位不重合表示需要继续二分搜索
			while (start <= end) {
				int mid = (end - start) / 2 + start;
				if (arrInt[i] + arrInt[mid] == target) {
					return i + "," + mid;
				} else if (arrInt[i] + arrInt[mid] > target) {
					end = mid - 1;
				} else {
					start = mid + 1;
				}
			}
		}
		return "-1,-1";
	}

	/**
	 * 假设数据是有序的，还有一种思路是双指针
	 * 如：[1,3,5,7,8,9] target: 13
	 * 输出：2，4
	 */
	public static String twoPoint(int[] arrInt, int target) {
		// 双指针，类似二分查找，一个是最左指针，一个是最有指针，如果两个指针和 > target，右指针左移
		int left = 0;
		int right = arrInt.length - 1;
		while (left < right) {
			if (arrInt[left] + arrInt[right] == target) {
				return left + "," + right;
			} else if (arrInt[left] + arrInt[right] > target) {
				right--;
			} else {
				left++;
			}
		}
		return "-1,-1";
	}

	public static void main(String[] args) {
		int[] intArr = {1, 3, 5, 2, 9, 8};
		int target = 8;
		System.out.println(solution(intArr, target));
		System.out.println(solution2(intArr, target));


		int[] ints = {1,3,5,7,8,9};
		int target1 = 10;
		System.out.println(solution3(ints, target1));
		int target2 = 13;
		System.out.println(twoPoint(ints, target2));
	}
}
