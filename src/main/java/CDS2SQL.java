import java.io.*;

/**
 * Created by Anton den Hoed on 6/12/2016.
 */
public class CDS2SQL {
    public static void main(String[] args) throws IOException {
        String filename = "E:\\UORF\\cds.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)))) {
            String line;

            File outputFile = new File("E:\\UORF\\cds.sql");
            FileWriter fw = new FileWriter(outputFile);
            BufferedWriter bw = new BufferedWriter(fw);

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                bw.write("UPDATE rna SET cds_start = " + parts[2] + ", cds_stop = " + parts[3] + " WHERE gi = '" + parts[0] + "' AND ref = '" + parts[1] + "';\r\n");
            }

            bw.close();
            fw.close();
        }
    }
}
