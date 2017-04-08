package TidyNumbers;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by raliclo on 07/04/2017.
 */
public class TidyNumbers {

    static class MyHelper {
        String zeroFilter(String info) {
            int[] infoInts = Arrays.stream(info.split(""))
                    .mapToInt(a -> Integer.parseInt(a))
                    .toArray();
            for (int i = 0; i < infoInts.length - 1; i++) {
                if (infoInts[i] == 0) {
                    StringBuilder infoDowngrade = new StringBuilder();
                    infoDowngrade.append(infoInts[0]);
                    for (int j = 0; j < infoInts.length - 1; j++) {
                        infoDowngrade.append("0");
                    }
                    return infoDowngrade.toString();
                }
            }
            return info;
        }

        ;

        boolean isDecay(String info) {
            int[] infoInts = Arrays.stream(info.split(""))
                    .mapToInt(a -> Integer.parseInt(a))
                    .toArray();
            for (int i = 0; i < infoInts.length - 1; i++) {
                if (infoInts[i + 1] < infoInts[i]) {
                    return true;
                }
            }
            return false;
        }

        String findAns(BufferedWriter writer, String info) throws IOException {
            Long number = Long.parseLong(info);
            boolean flag = isDecay(info);
            while (flag) {
                number = Long.parseLong(zeroFilter(number.toString()));
                number--;
                flag = isDecay(number.toString());
            }
            writer.append(number.toString() + "\n");
            return String.valueOf(number);
        }
    }

    public static void main(String[] args) throws IOException {
//
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> input = reader.lines().collect(Collectors.toCollection(ArrayList::new));
        System.out.println(input.size());
        int lines = Integer.parseInt(input.get(0));
        File file = new File("TidyNumbers.txt");
        file.delete();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (int i = 1; i <= lines; i++) {
            String data = input.get(i);
            MyHelper helper = new MyHelper();
            System.out.print("Case #" + i + ": ");
            writer.append("Case #" + i + ": ");
            System.out.println(helper.findAns(writer, data));
        }
        writer.close();
    }
}
