package lesson_02;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DanishIsland {

    private String name;
    private double circumference;
    private double area;
    private int addresses;
    private int addrPerKm2;

    public DanishIsland(String name, double circumference, double area,
                        int addresses, int addrPerKm2) {
        super();
        this.name = name;
        this.circumference = circumference;
        this.area = area;
        this.addresses = addresses;
        this.addrPerKm2 = addrPerKm2;
    }

    public String getName() {
        return name;
    }

    public double getCircumference() {
        return circumference;
    }

    public double getArea() {
        return area;
    }

    public int getAddresses() {
        return addresses;
    }

    public int getAddrPerKm2() {
        return addrPerKm2;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        builder.append("\t");
        builder.append(circumference);
        builder.append("\t");
        builder.append(area);
        builder.append("\t");
        builder.append(addresses);
        builder.append("\t");
        builder.append(addrPerKm2);
        builder.append("\n");
        return builder.toString();
    }

}

/**
 *
 * @author erso
 */
class DanishIslandFileReader {

    private File inFile;
    private List<DanishIsland> islandList;

    public DanishIslandFileReader(String fName) {
        inFile = new File(fName);
    }

    private void readFile() throws FileNotFoundException {
        islandList = new ArrayList<DanishIsland>();
        Scanner scan = null;
        String line;
        String[] tokens;

        String name = "";
        double circ;
        double area;
        int addr;
        int adkm;

        // OPGAVEN:
        // Laes filen en linje ad gangen. Split linjen paa mellemrums tegnet.
        // Konverter de enkelte vaerdier til typerne der skal bruges i DanishIsland.
        // Opret et objekt for hver linje og tilfoej det til listen.
        // Vaer omhyggelig med at fange exceptions og faa lukke Scanner og fil
        File fileReader = new File(DanishIslandFileReader.class.getClassLoader().getResource("lesson_02/islands.txt").getFile());

        Scanner scn = new Scanner(new FileInputStream(fileReader));
        while (scn.hasNextLine()) {
            String[] input = scn.nextLine().split(" ");

            DanishIsland a = new DanishIsland(input[0], Double.parseDouble(input[1]), Double.parseDouble(input[2]), Integer.parseInt(input[3]), Integer.parseInt(input[4]));
            islandList.add(a);
        }
        scn.close();
    }

    public List<DanishIsland> getList(){
        return islandList;
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        DanishIslandFileReader fr = new DanishIslandFileReader("islands.txt");
        //DanishIslandFileReader fr = new DanishIslandFileReader("Islands_komma.txt");
        fr.readFile();


        System.out.println("Result:\n" + fr.getList());

    }


}
