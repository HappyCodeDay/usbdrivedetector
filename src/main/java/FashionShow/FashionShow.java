package FashionShow;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Created by raliclo on 08/04/2017.
 */
public class FashionShow {

    static class MyHelper {
        TreeMap datanode;
        TreeMap backup;

        int rating;

        MyHelper(TreeMap dataNode) {
            datanode = dataNode;
            backup = clone2D(dataNode);
        }

        TreeMap clone2D(TreeMap inputNode) {
            return (TreeMap) inputNode.clone();
        }

        void printData(TreeMap datanode) {
            System.out.println("\n[Info] DataMatrix:");
            int matrixSize = (int) datanode.get("matrixSize");
            for (int i = 0; i < matrixSize; i++) {
                for (int j = 0; j < matrixSize; j++) {
                    if (!datanode.containsKey(i + "_" + j)) {
//                        datanode.put((i + "_" + j), ".");
                        System.out.print(".");

                    } else {
                        System.out.print(datanode.get(i + "_" + j));
                    }
                    if (j == matrixSize - 1) {
                        System.out.println();
                    }
                }
            }
            System.out.println(datanode);
        }

        int rateData(TreeMap datanode) {
            rating = 0;
            datanode.forEach((Object k, Object v) -> {
                if (k != "matrixSize") {
                    switch ((String) v) {
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
            });
            System.out.println("[Info] Rating:" + rating);
            return rating;
        }

        void optimizeRating(TreeMap datanode) {

        }

        void ruleCheck(TreeMap datanode) {

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> input = reader.lines().collect(Collectors.toCollection(ArrayList::new));
        int lines = Integer.parseInt(input.get(0));
        File file = new File("FashionShow.txt");
        file.delete();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        int max = lines;
        int next = 1;
        int count = 0;
        for (; next < max; next++) {
            String[] caseD = input.get(next).split(" ");
            int matrixSize = Integer.parseInt(caseD[0]);
            int starRoles = Integer.parseInt(caseD[1]);
            TreeMap dataNode = new TreeMap<String, String>();
            dataNode.put("matrixSize", matrixSize);

            for (int j = 1; j <= starRoles; j++) {
                String[] starLoc = input.get(next + j).split(" ");
                String starType = starLoc[0];
                int starX = Integer.parseInt(starLoc[1]) - 1;
                int starY = Integer.parseInt(starLoc[2]) - 1;
                dataNode.put((starX + "_" + starY), starType);
            }
            max += matrixSize;
            next += starRoles;
            count++;
            System.out.print("Case #" + count + ": ");
            writer.append("Case #" + count + ": ");
            MyHelper helper = new MyHelper(dataNode);
            helper.printData(dataNode);
            helper.rateData(dataNode);
        }
        writer.close();
    }
}
