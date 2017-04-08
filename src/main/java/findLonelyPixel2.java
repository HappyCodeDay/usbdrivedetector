import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by raliclo on 04/03/2017.
 */
public class findLonelyPixel2 {

    static Queue<ArrayList> queue1 = new ArrayDeque<>();
    static ArrayList all = new ArrayList<>();

    public static int findBlackPixel(char[][] picture, int N) {
        int ans = 0;
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[0].length; j++) {
                if (picture[i][j] == 'B') {
                    if (doublecheck(i, j, picture, N)) {
                        ans++;
                    }
                }
            }
        }

        if (!doublecheck2(picture)) {
            ans--;
        }

        return ans;
    }


    public static boolean doublecheck(int i, int j, char[][] picture, int N) {
        ArrayList<Pair> row = new ArrayList<>();
        ArrayList<Pair> col = new ArrayList<>();
        for (int ii = 0; ii < picture.length; ii++) {
            if (picture[ii][j] == 'B') {
                row.add(new Pair(ii, j));
            }
        }
        for (int jj = 0; jj < picture[0].length; jj++) {
            if (picture[i][jj] == 'B') {
                col.add(new Pair(i, jj));
            }
        }
        if (col.size() == N && row.size() == N) {
            all.add(new Pair(i, j).toString());
//            System.out.print(picture[i][j]);
//                System.out.println(i + " " + j);
//            System.out.print(row + " " + i);
//            System.out.println(col + " " + j);
            ArrayList spcol = new ArrayList();
            for (int k = 0; k < col.size(); k++) {
                spcol.add(col.get(k).getValue());
            }
            queue1.add(spcol);
        }
        return false;
    }

    public static boolean doublecheck2(char[][] picture) {
        System.out.println(all);
        all.forEach((a) -> {
            String[] tmp = a.toString().split("=");
            System.out.print(picture[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[1])]);
        });
//        System.out.println();

        Set data = queue1.stream().distinct()
                .collect(Collectors.toSet());
        System.out.println(data);
        return true;
    }

    public static void main(String[] args) {

        char[][] info = new char[][]{
                "WBWBBW".toCharArray(),
                "WBWBBW".toCharArray(),
                "WBWBBW".toCharArray(),
                "WWBWBW".toCharArray()
        };

        System.out.println("ANS:" + findBlackPixel(info, 3));
////
//        char[][] info2 = new char[][]{
//                "WBWBBW".toCharArray(),
//                "WBWBBW".toCharArray(),
//                "WBWBBW".toCharArray(),
//                "BWBWWB".toCharArray()
//        };
//        System.out.println(findBlackPixel(info2, 1));
//
//        char[][] info3 = new char[][]{
//                "B".toCharArray()
//        };
//
//        System.out.println(findBlackPixel(info3, 1));
//
//        char[][] info4 = new char[][]{
//                "WBW".toCharArray()
//        };
//
//        System.out.println(findBlackPixel(info4, 1));
//
//        char[][] info5 = new char[][]{
//                "WBWBBW".toCharArray(),
//                "WBWBBW".toCharArray(),
//                "WBWBBW".toCharArray(),
//                "BWBWWB".toCharArray()
//        };
//
//        System.out.println(findBlackPixel(info5, 1));
//        char[][] info6 = new char[][]{
//                "BWBWWB".toCharArray()
//        };
//
//        System.out.println(findBlackPixel(info6, 1));
        // 601100 9;

        char[][] info7 = new char[][]{
                "WBWBBW".toCharArray(),
                "BWBWWB".toCharArray(),
                "WBWBBW".toCharArray(),
                "BWBWWB".toCharArray(),
                "WWWBBW".toCharArray(),
                "BWBWWB".toCharArray()
        };
        System.out.println("ANS:" + findBlackPixel(info7, 3)); // 9

    }
}
