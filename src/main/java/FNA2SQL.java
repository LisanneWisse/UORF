import nl.antondenhoed.rna.beans.FNABean;

import java.io.*;

/**
 * Created by Anton den Hoed on 6/12/2016.
 */
public class FNA2SQL {
    public static void main(String[] args) throws IOException {

        String filename = "E:\\UORF\\mouse.3.rna.fna";
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)))) {
            String line;
            FNABean temp = null;
            StringBuffer sequenceBuffer = new StringBuffer();

            File outputFile = new File("E:\\UORF\\mouse.3.rna.sql");
            FileWriter fw = new FileWriter(outputFile);
            BufferedWriter bw = new BufferedWriter(fw);

            while ((line = br.readLine()) != null) {
                if (line.startsWith(">")) {
                    if (temp != null) {
                        temp.setSequence(sequenceBuffer.toString());
                        bw.write(temp.toInsertQuery());
                    }
                    temp = FNABean.fromIdLine(line);
                    sequenceBuffer = new StringBuffer();
                } else {
                    sequenceBuffer.append(line);
                }
            }

            if (temp != null) {
                temp.setSequence(sequenceBuffer.toString());
                bw.write(temp.toInsertQuery());
            }
            bw.close();
        }
    }
}
