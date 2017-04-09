package Contests.LeetCode.Leet554_BrickWall;
//https://leetcode.com/contest/leetcode-weekly-contest-27/problems/brick-wall/

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by raliclo on 08/04/2017.
 */
public class Leet554_Solved {

    public static void main(String[] args) throws IOException {

        int[][] input = {
                {1, 2, 2, 1},
                {3, 1, 2},
                {1, 3, 2},
                {2, 4},
                {3, 1, 2},
                {1, 3, 1, 1}
        };
//        int[][] input = {
//                {1},
//                {1},
//                {1}
//        };

//        int[][] input = {
//                {2},
//                {2},
//                {2}
//        };


//        int[][] input = {
//                {1, 1},
//                {2},
//                {1, 1}
//        };

//        int[][] input = {
//                {100000000},
//                {100000000},
//                {100000000}
//        };
//        int[][] input = {
//                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
//        };

//        int[][] input = {
//                {7, 1, 2},
//                {3, 5, 1, 1},
//                {10}
//        };
        List rowrow = new ArrayList();

        for (int j = 0; j < input.length; j++) {
            List row = new ArrayList<>();
            for (int i = 0; i < input[j].length; i++) {
                row.add(input[j][i]);
            }
            rowrow.add(row);
        }
//        System.out.println(rowrow);

        System.out.println(leastBricks(rowrow));
    }

    public static int leastBricks(List<List<Integer>> wall) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (List<Integer> row : wall) {
            int sum = 0;
            for (int i = 0; i < row.size() - 1; i++) {
                sum += row.get(i);
                if (map.containsKey(sum))
                    map.put(sum, map.get(sum) + 1);
                else
                    map.put(sum, 1);
            }
        }
        int res = wall.size();
        for (int key : map.keySet())
            res = Math.min(res, wall.size() - map.get(key));
        return res;
    }
}

