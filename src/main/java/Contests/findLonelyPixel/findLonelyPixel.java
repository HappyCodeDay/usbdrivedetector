package Contests.findLonelyPixel;

/**
 * Created by raliclo on 04/03/2017.
 */
public class findLonelyPixel {

    public static int findLonelyPixel(char[][] picture) {
        int ans = 0;
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[0].length; j++) {
                if (picture[i][j] == 'B') {
                    if (doublecheck(i, j, picture)) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public static boolean doublecheck(int i, int j, char[][] picture) {
        boolean ans = true;
        for (int jj = 0; jj < picture[0].length; jj++) {
            if (jj != j) {
                if (picture[i][jj] == 'B') {
                    return false;
                }
            }
        }
        for (int ii = 0; ii < picture.length; ii++) {
            if (ii != i) {
                if (picture[ii][j] == 'B') {
                    return false;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        char[][] info = new char[][]{
                "WWB".toCharArray(),
                "WBW".toCharArray(),
                "BWW".toCharArray()
        };

        System.out.println(findLonelyPixel(info));

    }
}
