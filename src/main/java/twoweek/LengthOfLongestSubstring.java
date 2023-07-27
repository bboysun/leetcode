package twoweek;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: Darryl
 * @Description: 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *         示例 1:
 *         输入: "abcabcbb"
 *         输出: 3
 *         解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *         示例 2:
 *         输入: "bbbbb"
 *         输出: 1
 *         解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *         示例 3:
 *         输入: "pwwkew"
 *         输出: 3
 *         解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *              请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * @Date: created in 2020/4/7 21:23
 */

public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        String strs = "bbbbb";
        //System.out.println(solution(strs));
        System.out.println(solution2(strs));

        System.out.println(findMaxLength(strs));
        System.out.println(findMaxLength2(strs));
    }

    /**
     * 暴力枚举
     * 有待优化
     * 执行用时 :1575 ms, 在所有 Java 提交中击败了5.01%的用户
     * 内存消耗 :40.2 MB, 在所有 Java 提交中击败了5.04%的用户
     *
     * @param strs
     * @return
     */
    public static int solution(String strs) {
        int size = 0;
        for (int i = 0; i < strs.length(); i++) {
            List<Character> temp = new ArrayList<Character>();
            temp.add(strs.charAt(i));
            for (int j = i + 1; j < strs.length(); j++) {
                if (temp.indexOf(strs.charAt(j)) >= 0) {
                    break;
                } else {
                    temp.add(strs.charAt(j));
                }
            }
            if (temp.size() > size) {
                size = temp.size();
            }
        }
        return size;
    }


    /**
     * 滑动窗口：
     * 我们通过set集合作为一个滑动窗口[i,j)，
     * 起初i=j，我们向右滑动j索引，
     * 当j索引数据不存在set集合中就会继续向右滑动，
     * 直到j索引数据存在set集合中，此时我们就找到了从索引i开头的子集。
     * 那么，如果我们依次对i索引数据都这样做，最终就会找到我们想要的结果。
     *
     * @param strs
     * @return
     */
    public static int solution2(String strs) {
        int length = strs.length();
        int res = 0;
        int i = 0, j = 0;
        Set<Character> set = new HashSet<Character>();
        while (i < length && j < length) {
            if (!set.contains(strs.charAt(j))) {
                set.add(strs.charAt(j++));
                res = Math.max(res, j - i);
            } else {
                set.remove(strs.charAt(i++));
            }
        }
        return res;
    }







    // 嵌套循环，依次循环当前位置（当前位置加1递增），以当前位置为基础对比后面的字串中最大不重复
    public static int findMaxLength(String str) {
        int max = 0;
        for (int i=0; i<str.length(); i++) {
            List<Character> temp = new ArrayList<Character>();
            temp.add(str.charAt(i));
            for (int j = i + 1; j<str.length(); j++) {
                if (temp.indexOf(str.charAt(j)) >= 0) {
                    break;
                } else {
                    temp.add(str.charAt(j));
                }
            }
            max = Math.max(max, temp.size());
        }
        return max;
    }

    public static int findMaxLength2(String str) {
        int max = 0;
        // 定义一个滑动窗口 set
        Set<Character> set = new HashSet<Character>();
        // 滑动窗口的开始位置为[0，0],
        // i是窗口左边位置，j是窗口右边位置，
        // 右边位置找到最大长度时，i向后滑动一个位置，
        // 右边位置没有找到最大长度时，j向后滑动。
        // 如此反复滑动，最终找到全局最优解
        int i=0,j=0;
        while (i < str.length() && j < str.length()) {
            if (set.contains(str.charAt(j))) {
                // 找到局部最优解
                max = Math.max(max, set.size());
                // 滑动窗口开始位置向后移动1位，并且滑动窗口结束位置回归到开始位置
                i++;
                j = i;
                // 窗口清空
                set.clear();
            } else {
                // 未找到局部最优解，滑动窗口结束位置向后移动1位，并将字符暂存到窗口中
                set.add(str.charAt(j));
                j++;
            }
        }
        return max;
    }


}
