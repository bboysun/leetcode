import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: darrylsun
 * @Description:
 * @Date: 2023/11/23
 */
public class CrossJoin {

    public static void main(String[] args) {
        List<String> A = Arrays.asList("a","b","c");
        List<String> B = Arrays.asList("d","e");
        List<String> C = Arrays.asList("f");


        List<List<String>> list = Arrays.asList(A, B, C);
        List<List<String>> res = crossJoin(list);
        System.out.println();
    }

    public static List<List<String>> crossJoin(List<List<String>> list) {
        List<List<String>> res = new ArrayList<>();
        get(list, 0, res, new ArrayList<>());
        return res;
    }

    /**
     * 递归处理
     * @param originList
     * @param index
     * @param res
     * @param cache
     */
    private static void get(List<List<String>> originList, int index, List<List<String>> res, List<String> cache) {
        List<String> origin = originList.get(index);
        for (int i=0; i<origin.size(); i++) {
            List<String> childCache = new ArrayList<>(cache);
            childCache.add(origin.get(i));
            if (index == originList.size() - 1) {
                res.add(childCache);
                continue;
            }
            get(originList, index + 1, res, childCache);
        }
    }

}
