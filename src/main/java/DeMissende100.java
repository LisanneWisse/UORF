import nl.antondenhoed.rna.beans.FNABean;
import nl.antondenhoed.rna.db.RNADataSource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton den Hoed on 6/12/2016.
 */
public class DeMissende100 {
    public static void main(String[] args) {
        String filename = "E:\\UORF\\cds.csv";

        RNADataSource ds = new RNADataSource();
        ds.connect();

        List<String> dupes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

//                bw.write("UPDATE rna SET cds_start = " + parts[2] + ", cds_stop = " + parts[3] + " WHERE gi = '" + parts[0] + "' AND ref = '" + parts[1] + "';\r\n");


                List<FNABean> items = ds.findByGiRef(parts[0], parts[1]);

                if (items.isEmpty()) {
                    System.out.println(line);
                }

                if (dupes.contains(parts[0] + parts[1])) {
                    System.out.println("DUPE! " + line);
                } else {
                    dupes.add(parts[0] + parts[1]);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ds.close();
    }
}
