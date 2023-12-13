import java.util.Stack;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: darrylsun
 * @Description: 字符串解析器
 * 比如：
 * input: 2[ab], output: ab ab
 * input: 2[d3[xc]], output:dxcxcxc dxcxcxc
 * @Date: 2023/12/13
 */
public class StringParse {

    public static void main(String[] args) {
        String test1 = "2[ab]";
        String parse1 = parse(test1);
        String test2 = "2[d3[xc]]";
        String parse2 = parse(test2);
        System.out.println(parse1);
        System.out.println(parse2);
    }

    public static String parse(String str) {
        Stack<String> stackCache = new Stack<>();
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        for (int i=0; i<str.length(); i++) {
            Character c = str.charAt(i);
            if (c.equals(']')) {
                StringBuilder unit = new StringBuilder();
                StringBuilder count = new StringBuilder();
                popUnit(stackCache, unit);
                popCount(stackCache, count);
                String unitStr = unit.toString();
                Long num = Long.valueOf(count.toString());
                String subString = buildSubString(unitStr, num);
                stackCache.push(subString);
            } else {
                pushCharacter(c, stackCache);
            }
        }

        return new StringBuilder(stackCache.pop()).reverse().toString();
    }

    private static String buildSubString(String unitStr, Long num) {
        StringBuilder res = new StringBuilder();
        for (int i=0; i<num; i++) {
            res.append(unitStr);
        }
        return res.toString();
    }

    private static void pushCharacter(Character c, Stack<String> stackCache) {
        stackCache.push(c.toString());
    }

    public static void popUnit(Stack<String> cache, StringBuilder unit) {
        if (cache.isEmpty()) {
            return;
        }
        String pop = cache.pop();
        if (pop.equals("[")) {
            return;
        } else {
            unit.append(pop);
            popUnit(cache, unit);
        }
    }

    public static void popCount(Stack<String> cache, StringBuilder count) {
        if (cache.isEmpty()) {
            return;
        }
        String pop = cache.pop();
        if (!isNumeric(pop)) {
            cache.push(pop);
            return;
        } else {
            count.append(pop);
            popCount(cache, count);
        }
    }

    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
}
