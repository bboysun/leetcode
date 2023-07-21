import org.apache.commons.lang3.StringUtils;

/**
 * @Author: darrylsun
 * @Description: 类似 string indexof，找到字符串A中匹配字符串B的角标位置(第一次匹配的位置)
 * 例子：
 * string A： ABCBBFR
 * string B: BCB
 * 结果: 1
 *
 * @Date: 2023/7/21
 */
public class StringIndexOf {

    public static void main(String[] args) {
        String str = "ABDCBBCBD";
        String pattern = "BCB";
        int i = str.indexOf(pattern);
        System.out.println(i);

        int i1 = defaultFind(str, pattern);
        System.out.println(i1);

        int i2 = hashFind(str, pattern);
        System.out.println(i2);
    }

    /**
     * 暴力查找，类似滑动窗口，每次挪动一个位置，进行全匹配
     * @param str
     * @param pattern
     * @return
     */
    public static int defaultFind(String str, String pattern) {
        if (str == null || str == "" || pattern == null || pattern == "") {
            return 0;
        }
        int res = 0;
        for (int i=0; i<str.length(); i++) {
            boolean successFlag = true;
            for (int j=0; j<pattern.length(); j++) {
                if (!Character.valueOf(str.charAt(i+j)).equals(Character.valueOf(pattern.charAt(j)))) {
                    successFlag = false;
                    break;
                }
            }
            if (successFlag) {
                res = i;
                break;
            }
        }
        return res;
    }

    /**
     * 每个字串进行模式比较前先计算hash，当hash一致时再逐个比对（防止hash冲突）
     * @param str
     * @param pattern
     * @return
     */
    public static int hashFind(String str, String pattern) {
        if (str == null || str == "" || pattern == null || pattern == "") {
            return 0;
        }
        int res = 0;
        int patternHash = hashString(pattern);
        for (int i=0; i<str.length(); i++) {
            String substring = StringUtils.substring(str, 0, pattern.length());
            int strHash = hashString(substring);
            if (patternHash != strHash) {
                continue;
            }
            boolean successFlag = true;
            for (int j=0; j<pattern.length(); j++) {
                if (!Character.valueOf(str.charAt(i+j)).equals(Character.valueOf(pattern.charAt(j)))) {
                    successFlag = false;
                    break;
                }
            }
            if (successFlag) {
                res = i;
                break;
            }
        }
        return res;
    }

    public static int kmpFind(String str, String pattern) {
        // TODO
        return 0;
    }

    private static int hashString(String str) {
        int res=0;
        for (int i=0; i<str.length(); i++) {
            if (Character.valueOf(str.charAt(i)).equals('A')) {
                res = res + 1;
            }
            if (Character.valueOf(str.charAt(i)).equals('B')) {
                res = res + 2;
            }
            if (Character.valueOf(str.charAt(i)).equals('C')) {
                res = res + 3;
            }
            if (Character.valueOf(str.charAt(i)).equals('D')) {
                res = res + 4;
            }
            if (Character.valueOf(str.charAt(i)).equals('E')) {
                res = res + 5;
            }
        }
        return res;
    }
}
