package oneweek;

/**
 * @Auther: Darryl
 * @Description: 糖果屋，小明同学在一排糖果屋的大街上买糖果，小明同学相邻的糖果屋不能都进去买糖。每个糖果屋有不一样数量的糖果。
 * 请问最后，小明能买到多少糖果。
 * 假设糖果屋是一个数组[1,2,7,1]
 * 那么需要输出最多的糖果是：8
 * @Date: created in 2020/3/25 22:18
 */
public class FindCandy {

    public static void main(String[] args) {
        int[] candyHome = {1,2,7,1};
        System.out.println(maxCandy(candyHome));
    }

    private static int maxCandy(int[] candyHome){
        if (candyHome.length == 0)
            return 0;
        if (candyHome.length == 1)
            return candyHome[0];
        if (candyHome.length == 2)
            return candyHome[0] > candyHome[1] ? candyHome[0] : candyHome[1];

        // 我们可以使用动态规划来实现，max[n]表示在n个糖果屋中小明能够买到最多的糖果数。
        // 那么假设有一排糖果屋需要小明去买糖果，按照规则，小明在这排糖果屋买糖可以有两种方式：
        // 1. 假设小明选了最后一个糖果屋（n）的糖果，那么按照规则，小明只能在n-2个糖果屋中买最多的糖果，
        //    再加上第n个糖果屋的糖果，最终就是小明买到最多的糖果。
        // 2. 假设小明选了倒数第二个糖果屋（n-1）的糖果，那么按照规则，小明只能在n-3个糖果屋中买最多的糖果，
        //    再加上第n-1个糖果屋的糖果，最终就是小明买到最多的糖果。
        // 归纳上面的思路，我们可以用公式来表示这个关系：max[n] = (candyHome[n-1] + max[n-2]) + (candyHome[n-2] + max[n-3])
        // 接下来，我们就用代码来走一波咯

        // 用来记录每一层的结果
        int[] res = new int[candyHome.length+1];
        res[1] = candyHome[0];
        res[2] = candyHome[0] > candyHome[1] ? candyHome[0] : candyHome[1];
        for (int i=3; i<=candyHome.length; i++) {
            res[i] = res[i-2] + candyHome[i-1] > res[i-3] + candyHome[i-2] ? res[i-2] + candyHome[i-1] : res[i-3] + candyHome[i-2];
        }
        return res[candyHome.length];
    }
}
