package twoweek;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Darryl
 * @Description:
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * @Date: created in 2020/4/7 21:23
 */

public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        String strs = "abcabcbb";
        System.out.println(solution(strs));
    }

    /**
     * 有待优化
     * 执行用时 :1575 ms, 在所有 Java 提交中击败了5.01%的用户
     * 内存消耗 :40.2 MB, 在所有 Java 提交中击败了5.04%的用户
     * @param strs
     * @return
     */
    public static int solution(String strs) {
        int size = 0;
        for (int i=0; i<strs.length(); i++) {
            List<Character> temp = new ArrayList<Character>();
            temp.add(strs.charAt(i));
            for (int j=i+1; j<strs.length(); j++) {
                if (temp.indexOf(strs.charAt(j)) >= 0) {
                    break;
                } else {
                    temp.add(strs.charAt(j));
                }
            }
            if (temp.size() > size)
                size = temp.size();
        }
        return size;
    }
}
