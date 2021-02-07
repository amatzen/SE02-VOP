package lesson_02;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class CamelCaseWriter {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File(CamelCaseWriter.class.getClassLoader().getResource("lesson_02/InputFile.txt").getFile());

        Scanner scn = new Scanner(new FileInputStream(input));
        String output = "";

        while (scn.hasNextLine()) { // Each line
            String[] li = scn.nextLine().split(" ");

            li[0] = li[0].toLowerCase(Locale.ROOT);

            int x = li.length;
            for (int i = 1; i<li.length; i++) {
                li[i] = li[i].toLowerCase(Locale.ROOT);

                String firstLetter = String.valueOf(li[i].charAt(0))
                        .toUpperCase(Locale.ROOT);

                li[i] = firstLetter + li[i].substring(1);
            }

            for (int i = 0; i<li.length; i++) {
                output += li[i];
            }

            output += "\n";
        }

        System.out.println(output);

        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter("OutputFile.txt", false));

            fw.write(output);
            fw.close();
        } catch (IOException e) {
            System.out.println("hej");
            e.printStackTrace();
        }
    }


}
