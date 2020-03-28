package oneweek;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Darryl
 * @Description: 有一个环形的巴士站，每个巴士站都有一个油桶，油桶的油量是不一样的。
 * 巴士从一个巴士站到下一个巴士站所消耗的油量也是不一样的。
 * 巴士一开始邮箱是空的，而且邮箱的容量是无限的。需要找到巴士从哪个巴士站触发能刚好走一圈。
 *
 * 假设每个巴士站的邮量[5,2,4,1,7]
 * 每个巴士站到下一个巴士站所消耗的油量是[4,1,3,2,6]
 * 那么从1巴士站和3巴士站均可走一圈。
 * @Date: created in 2020/3/28 12:43
 */
public class Gas {

    public static void main(String[] args) {
        int[] gas = {5,2,4,1,7};
        int[] cost = {4,1,5,2,6};
        List<Integer> result = soluation(gas, cost);
        for (Integer val : result)
            System.out.print(" " + val + " ");
    }


    private static List<Integer> soluation(int[] gas, int[] cost) {
        List<Integer> result = new ArrayList<Integer>();
        List<Integer> startBusStation = new ArrayList<Integer>();
        //先找到能够始发的巴士站
        for (int i=0; i<gas.length; i++) {
            if (gas[i] - cost[i] > 0)
                startBusStation.add(i);
        }

        for (Integer index : startBusStation) {
            int start = index + 1 == gas.length ? 0 : index + 1;
            int end = index - 1 < 0 ? gas.length - 1 : index - 1;
            int saveGas = gas[index] - cost[index];

            while (start != end) {
                saveGas = saveGas + gas[start] - cost[start];
                if (saveGas < 0)
                    break;
                 else {
                    start = start + 1 == gas.length ? 0 : start + 1;
                }
            }
            if (saveGas >= 0)
                result.add(index);
        }
        return result;
    }

}
