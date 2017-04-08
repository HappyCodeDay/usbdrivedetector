package FashionShow;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by raliclo on 08/04/2017.
 */
public class FashionShow {

    static class MyHelper {


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
        for (int i = 1; i <= max; i++) {
            System.out.print("Case #" + i + ": ");
            writer.append("Case #" + i + ": ");
            String[] caseD = input.get(next).split(" ");
            int matrixSize = Integer.parseInt(caseD[0]);
            int starRoles = Integer.parseInt(caseD[1]);
            max += starRoles;
            next += matrixSize;
            String[][] dataNode = new String[matrixSize][matrixSize];
            for (int j = 0; j < starRoles; j++) {
                String[] starLoc = input.get(next + j).split(" ");
                String starType = starLoc[0];
                int starX = Integer.parseInt(starLoc[1]);
                int starY = Integer.parseInt(starLoc[2]);
                dataNode[starX][starY] = starType;
            }

        }
        writer.close();
    }
}
