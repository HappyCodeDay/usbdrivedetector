import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by raliclo on 04/03/2017.
 */
public class findPairs {


    public static int findPairs(int[] nums, int k) {
        int ans = 0;

        if (k == 0) {
            int[] ans2 = new int[1];
            HashMap<Integer, Integer> count = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (count.containsKey(nums[i])) {
                    count.put(nums[i], count.get(nums[i]) + 1);

                } else {
                    count.putIfAbsent(nums[i], 1);
                }
            }
            count.forEach((a, b) -> {
                if (b > 1) {
                    ans2[0]++;
                }
            });

            return ans2[0];
//            System.out.println(count);
//            System.out.println(ans2[0]);
        }

        IntStream data = Arrays.stream(nums).distinct().sorted();
        List<Integer> cdata = data.mapToObj(i -> i).collect(Collectors.toList());
//        System.out.println(cdata);
        for (int i = 0; i < cdata.size(); i++) {
            for (int j = i + 1; j < cdata.size(); j++) {
                if ((cdata.get(j) - cdata.get(i)) == k) {
//                    System.out.println(cdata.get(j) + " " + cdata.get(i) + " " + k);
                    ans++;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] info = {3, 1, 4, 1, 5};
        System.out.println(findPairs(info, 2));

        int[] info2 = {1, 2, 3, 4, 5};
        System.out.println(findPairs(info2, 1));

        int[] info3 = {1, 3, 1, 5, 4};
        System.out.println(findPairs(info3, 0));

        int[] info4 = {1, 2, 3, 4, 5};
        System.out.println(findPairs(info4, 0));

        int[] info5 = {1, 1, 1, 2, 2};
        System.out.println(findPairs(info5, 0));
    }
}
