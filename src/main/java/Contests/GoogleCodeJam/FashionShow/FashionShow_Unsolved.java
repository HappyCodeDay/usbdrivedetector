package Contests.GoogleCodeJam.FashionShow;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Created by raliclo on 08/04/2017.
 */
public class FashionShow_Unsolved {

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
//            System.out.println(datanode);
        }

        boolean isRule1(TreeMap datanode) {
            int matrixSize = (int) datanode.get("matrixSize");
            if (matrixSize == 1) {
                return true;
            }
            boolean[] flag = new boolean[2];
            HashMap xValid = new HashMap<>();
            HashMap yValid = new HashMap<>();
            List specialPoint = new ArrayList<>();
            datanode.forEach((Object k, Object v) -> {
                if (k != "matrixSize") {
                    String[] locXY = k.toString().split("_");
                    int rowN = Integer.parseInt(locXY[0]);
                    int colN = Integer.parseInt(locXY[1]);
                    if (v.equals("+")) {
                        specialPoint.add(k);
                    }
                    for (int i = 0; i < matrixSize; i++) {
                        if (rowN == i) {
                            xValid.computeIfPresent(i, (kk, vv) -> (int) vv + 1);
                            xValid.putIfAbsent(i, 1);
                        }
                        if (colN == i) {
                            yValid.computeIfPresent(i, (kk, vv) -> (int) vv + 1);
                            yValid.putIfAbsent(i, 1);
                        }
                    }
                }
            });
//            System.out.println(specialPoint);
//            System.out.println(xValid);
            xValid.forEach((k, v) -> {
                        if ((int) v >= 2) {
                            specialPoint.forEach(
                                    (item) -> {
                                        if (item.toString().split("_")[0].equals(k)) ;
                                        flag[0] = true;
                                    }
                            );
                        }

                    }
            );
//            System.out.println(yValid);
            yValid.forEach((k, v) -> {
                        if ((int) v >= 2) {
                            specialPoint.forEach(
                                    (item) -> {
                                        if (item.toString().split("_")[1].equals(k)) ;
                                        flag[1] = true;
                                    }
                            );
                        }

                    }
            );
//            System.out.println(flag[0] + " " + flag[1]);
            return flag[0] && flag[1];
        }

        boolean isRule2(TreeMap datanode) {
            int matrixSize = (int) datanode.get("matrixSize");
            if (matrixSize == 1) {
                return true;
            }
            boolean flag = false;
            int count = 0;
            for (int i = 0; i < matrixSize; i++) {
                String index = i + "_" + (matrixSize - i - 1);
                if (datanode.containsKey(index)) {
                    count++;
                    if (datanode.get(index).equals("x")) {
                        flag = true;
                    }
                }
//                System.out.println(index + "/" + count + "/" + flag);
            }
            return count >= 2 ? flag : false;
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
            System.out.println("[Result] Rating:" + rating);
            return rating;
        }

        void optimizeRating(TreeMap datanode) {

        }

        boolean ruleCheck(TreeMap datanode) {
            return isRule1(datanode) && isRule2(datanode);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> input = reader.lines().collect(Collectors.toCollection(ArrayList::new));
        int lines = Integer.parseInt(input.get(0));
        File file = new File("FashionShow_Unsolved.txt");
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
//            helper.printData(dataNode);
            helper.rateData(dataNode);
//            System.out.println(helper.isRule2(dataNode));

            System.out.println(helper.isRule1(dataNode));
        }
        writer.close();
    }
}
