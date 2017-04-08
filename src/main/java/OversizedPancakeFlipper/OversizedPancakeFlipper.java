package OversizedPancakeFlipper;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by raliclo on 04/03/2017.
 */
public class OversizedPancakeFlipper {

    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> input = reader.lines().collect(Collectors.toCollection(ArrayList::new));
        System.out.println(input.size());
        int lines = Integer.parseInt(input.get(0));
        File file = new File("result.txt");
        file.delete();
        PrintWriter writer = new PrintWriter(file);

        for (int i = 1; i <= lines; i++) {
            String[] info = input.get(i).split(" ")[0].split("");
            int size = Integer.parseInt(input.get(i).split(" ")[1]);
            MyHelper helper = new MyHelper();
            helper.fliper(info, size, 0);
            System.out.print("Case #" + i + ": ");
            writer.append("Case #" + i + ": ");
            helper.writeAns(writer, file);
        }
        writer.close();
    }

    static class MyHelper {
        int counts;
        boolean failed;

        MyHelper() {
            counts = -1;
            failed = false;
        }

        public void fliper(String[] nums, int k, int begin) {
            this.counts++;
            int lastNeg;
            int i, j;
            for (i = begin; i < nums.length; i++) {
                lastNeg = i;
                if (nums[i].equals("-")) {
                    if ((i + k) > nums.length) {
                        failed = true;
                        return;
                    }
                    for (j = i; j < i + k; j++) {
                        if (nums[j].equals("+")) {
                            nums[j] = "-";
                        } else if (nums[j].equals("-")) {
                            nums[j] = "+";
                        }
                    }
                    fliper(nums, k, lastNeg);
                }
            }
        }

        void writeAns(PrintWriter writer, File file) {
            if (failed) {
                writer.append("IMPOSSIBLE\n");
                System.out.println("IMPOSSIBLE");
            } else {
                writer.append(counts + "\n");
                System.out.println(counts);
            }
        }

    }

}
