package threeweek;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Darryl
 * @Description: 给出一个32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 * 输入: 123
 * 输出: 321
 *  示例 2:
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * 输入: 120
 * 输出: 21
 *
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * @Date: 2020/04/19
 */
public class Reverse {

	public static void main(String[] args) {
		/*System.out.println(Integer.MIN_VALUE);
		System.out.println(cal(31));*/
		System.out.println(reverseToNum(102));
	}

	/**
	 * 利用数学对数字进行反转。
	 * 如：数字num弹出最后一位数字：pop = num % 10; num = num / 10
	 * 如：数字rev插入一个数字：rev * 10 + pop
	 *
	 * 需要注意的是，rev * 10 + pop 会造成内存溢出，我们测试发现
	 *
	 * int 类型是4个字节，也就是[-2^31, 2^31-1] 即 [-2147483648,2147483647]
	 *
	 * @param num
	 * @return
	 */
	private static int reverseToNum(int num) {
		// 弹出最后一位数字
		int res = 0;
		while (num != 0) {
			int pop = num % 10;
			num = num / 10;
			// 判断是否溢出
			if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && pop > 8))
				return 0;
			if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > 7))
				return 0;
			res = res * 10 + pop;
		}
		return res;
	}

	private static int cal(int times) {
		if (times == 0)
			return 1;
		return -2 * cal(times - 1);
	}

}
