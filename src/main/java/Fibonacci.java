/**
 * @Auther: Darryl
 * @Description: 斐波那契数列(数列中前两位的值分别位0，1)，获取指定位置的 斐波那契值
 * 如：斐波那契数列：[0,1,1,2,3,5,8,13,21,34,55]
 * @Date: 2023/08/21
 */
public class Fibonacci {

	public static void main(String[] args) {
		System.out.println(cal(10));
	}


	/**
	 * 暴力递归 思想
	 * @param index
	 * @return
	 */
	public static int cal(int index) {
		if (index == 0) {
			return 0;
		}
		if (index == 1) {
			return 1;
		}
		return cal(index - 1) + cal(index - 2);
	}

}
