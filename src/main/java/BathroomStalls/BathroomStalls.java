package BathroomStalls;

import java.io.*;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by raliclo on 07/04/2017.
 */
public class BathroomStalls {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> input = reader.lines().collect(Collectors.toCollection(ArrayList::new));
        int lines = Integer.parseInt(input.get(0));
        File file = new File("bathroomStalls.txt");
        file.delete();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        ExecutorService executor = Executors.newFixedThreadPool(lines);

        for (int i = 1; i <= lines; i++) {
            String[] data = input.get(i).split(" ");
            int numStalls = Integer.parseInt(data[0]); // get it from user input if you like
            int people = Integer.parseInt(data[1]);
            int[] box = new int[1];
            box[0] = i;
            Runnable runner = () -> {
                try {
                    MyHelper helper = new MyHelper(numStalls);
                    helper.fill(numStalls, people);
                    int[] pairLsRs = helper.getResult();
                    System.out.print("Case #" + box[0] + ": ");
                    System.out.println(pairLsRs[0] + " " + pairLsRs[1]);
                    writer.append("Case #" + box[0] + ": ");
                    writer.append(pairLsRs[0] + " " + pairLsRs[1] + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            executor.execute(runner);
//            String ans = helper.printStalls(helper.bs);
//            System.out.println(ans);
        }
        while (!executor.isTerminated()) {
        }
        writer.close();
        System.out.println("Finished all threads");

    }


    static class MyHelper {
        BitSet bs = new BitSet();
        int finalStall;
        String resultStr;
        int maxNumber;

        MyHelper(int numStalls) {
            maxNumber = numStalls;
        }

        public void fill(int numStalls, int people) {
            bs.set(0);
            bs.set(numStalls + 1);
            for (int i = 0; i < people; i++) {
                finalStall = nextStall(bs);
                bs.set(finalStall);
//                System.out.println(printStalls(bs));
            }
        }

        public int nextStall(BitSet bs) {
            int pos = bs.stream()
                    .boxed()
                    .max(Comparator.comparingInt(v -> bs.nextSetBit(v + 1) - v))
                    .get();
            return (pos + bs.nextSetBit(pos + 1)) / 2;
        }

        public String printStalls(BitSet bs) {
            resultStr = IntStream.range(1, bs.length() - 1)
                    .mapToObj(idx -> bs.get(idx) ? "X" : "_")
                    .collect(Collectors.joining());
            return resultStr;
        }


        public int getLs(BitSet bs, int finalStall) {
            // the number of empty stalls between S and the closest occupied stall
            // to the left
            int count = 0;
            for (int i = finalStall; i > 0; i--) {
                if (i - 1 < 0) {
                    return count;
                }
                if (bs.get(i)) {
//                if (resultStr.charAt(i - 1) == 'X') {
                    return count;
                }
                count++;
            }
            return count;
        }

        public int getRs(BitSet bs, int finalStall) {
            // the number of empty stalls between S and the closest occupied stall
            // to the right
            int count = 0;
            for (int i = finalStall; i <= maxNumber; i++) {
                if (i > maxNumber) {
                    return count;
                }
                if (bs.get(i)) {
                    return count;
                }
                count++;
            }
            return count;
        }

        public int[] getResult() {
            int[] pairLsRs = new int[2];
            int Ls = getLs(bs, finalStall - 1);
            int Rs = getRs(bs, finalStall + 1);
            int y = Math.max(Ls, Rs); // Max(Ls,Rs)
            int z = Math.min(Ls, Rs); // Min(Ls,Rs)
            pairLsRs[0] = y;
            pairLsRs[1] = z;
            return pairLsRs;
        }

    }
}
