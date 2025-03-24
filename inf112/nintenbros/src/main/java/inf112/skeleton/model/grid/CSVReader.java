package inf112.skeleton.model.grid;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static List<List<Integer>> readCSV(String fileName) {
        List<List<Integer>> array2D = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                List<Integer> row = new ArrayList<>();
                for (String value : values) {
                    row.add(Integer.parseInt(value.trim()));  // Convert to int
                }
                array2D.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return array2D;  // Return the stored 2D list
    }
}
