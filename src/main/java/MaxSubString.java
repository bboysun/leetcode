import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: Darryl
 * @Description: 找到一个字符串中最大子串的长度
 * @Date: 2023/11/26
 */
public class MaxSubString {

	public static void main(String[] args) {
		List<String> strings = Arrays.asList("p", "w", "w", "k", "e", "w");
		int maxSubStr = findMaxSubStr(strings);
		System.out.println(maxSubStr);

		System.out.println(findMaxSubStr2(strings));

		System.out.println(lengthOfLongestSubstring2(strings));
	}

	/**
	 * 暴力破解
	 * @param strList
	 * @return
	 */
	public static int findMaxSubStr(List<String> strList) {
		if (strList == null || strList.size() == 0) {
			return 0;
		}
		Set<String> setStr = new HashSet<>();
		int res = 0;
		for (int i=0; i<strList.size(); i++) {
			setStr.add(strList.get(i));
			for (int j=i+1; j<strList.size(); j++) {
				if (setStr.contains(strList.get(j))) {
					res = setStr.size() > res ? setStr.size() : res;
					setStr.clear();
				}
				setStr.add(strList.get(j));
			}
		}
		return res;
	}


	/**
	 * 优化，降低时间复杂度
	 * @param strings
	 * @return
	 */
	public static int findMaxSubStr2(List<String> strings) {
		if (strings == null || strings.size() == 0) {
			return 0;
		}

		Set<String> cache = new HashSet<>();
		int res = 0;
		int end = 0;
		while (end < strings.size()) {
			if (cache.contains(strings.get(end))) {
				cache.clear();
			} else {
				cache.add(strings.get(end));
				res = cache.size() > res ? cache.size() : res;
				end++;
			}
		}
		return res;
	}


	public static int findMaxSubStr3(String str) {
		if (str == null && str.length() == 0) {
			return 0;
		}
		Set<Character> cache = new HashSet<>();
		int length = str.length();
		int res = 0;
		for (int i=0; i<length; i++) {
			if (!cache.contains(str.charAt(i))) {
				cache.add(str.charAt(i));
				res = cache.size() > res ? cache.size() : res;
			} else {
				cache.clear();
			}
		}
		return res;
	}

	public static Integer lengthOfLongestSubstring2(List<String> s) {
		int maxLength = 0;
		// 左指针
		int leftPoint = 0;
		// 右指针
		int rightPoint = 0;
		// 用于判断重复的Set集合(窗口)
		Set<String> set = new HashSet<>();
		while (leftPoint < s.size() && rightPoint < s.size()) {
			if (!set.contains(s.get(rightPoint))) {
				// 不存在重复字符时将字符存储入set，并将右边指针向后移动一位
				set.add(s.get(rightPoint++));
				maxLength = Math.max(maxLength, rightPoint - leftPoint);
			} else {
				// 存在重复元素，则将左边指针向右移动一位(删除与当前相同的字符)
				set.remove(s.get(leftPoint++));
			}
		}

		return maxLength;
	}
}
