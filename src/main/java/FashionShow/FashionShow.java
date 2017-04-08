package FashionShow;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by raliclo on 08/04/2017.
 */
public class FashionShow {

    static class MyHelper {
        String[][] datanode;

        int rating;

        MyHelper(String[][] dataNode) {
            datanode = dataNode;
        }


        void printData() {
            System.out.println("\n[Info] DataMatrix:");
            for (int i = 0; i < datanode.length; i++) {
                for (int j = 0; j < datanode[i].length; j++) {
                    if (datanode[i][j] == null) {
                        datanode[i][j] = ".";
                    }
                    System.out.print(datanode[i][j]);
                    if (j == datanode[i].length - 1) {
                        System.out.println();
                    }
                }
            }
        }

        int rateData() {
            rating = 0;
            for (int i = 0; i < datanode.length; i++) {
                for (int j = 0; j < datanode[i].length; j++) {
                    if (datanode[i][j] == null) {
                        datanode[i][j] = ".";
                    }
                    switch (datanode[i][j]) {
                        case "x":
                            rating += 1;
                            break;
                        case "+":
                            rating += 1;
                            break;
                        case ".":
                            rating += 0;
                            break;
                        case "o":
                            rating += 2;
                            break;
                    }
                }

            }
            System.out.println("[Info] Rating:" + rating);
            return rating;
        }

        void optimizeRating(String[][] datanode) {

        }

        void ruleCheck(String[][] datanode) {

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> input = reader.lines().collect(Collectors.toCollection(ArrayList::new));
        int lines = Integer.parseInt(input.get(0));
        File file = new File("result.txt");
        file.delete();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        int max = lines;
        int next = 1;
        int count = 1;
        for (; next < max; next++) {
            String[] caseD = input.get(next).split(" ");
            int matrixSize = Integer.parseInt(caseD[0]);
            int starRoles = Integer.parseInt(caseD[1]);
            String[][] dataNode = new String[matrixSize][matrixSize];
            for (int j = 1; j <= starRoles; j++) {
                String[] starLoc = input.get(next + j).split(" ");
                String starType = starLoc[0];
                int starX = Integer.parseInt(starLoc[1]) - 1;
                int starY = Integer.parseInt(starLoc[2]) - 1;
                dataNode[starX][starY] = starType;
            }
            max += matrixSize;
            next += starRoles;
            count++;
            MyHelper helper = new MyHelper(dataNode);
            helper.printData();
            helper.rateData();

            System.out.print("Case #" + count + ": ");
            writer.append("Case #" + count + ": ");
        }
        writer.close();
    }
}
