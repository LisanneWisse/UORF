package nl.antondenhoed.rna;

import com.sun.deploy.util.StringUtils;
import nl.antondenhoed.rna.algorithms.Sequences;
import nl.antondenhoed.rna.beans.FNABean;
import nl.antondenhoed.rna.beans.SubSequence;
import nl.antondenhoed.rna.db.RNADataSource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton den Hoed on 6/12/2016.
 */
public class FindUORF {
    public static void main(String[] args) throws IOException {
        RNADataSource ds = new RNADataSource();
        ds.connect();

        List<FNABean> items = ds.findWhere("cds_start IS NOT NULL");


        File outputFile = new File("E:\\UORF\\raw_kozak_ttg.txt");
        FileWriter fw = new FileWriter(outputFile);
        BufferedWriter bw = new BufferedWriter(fw);

        File outputFile2 = new File("E:\\UORF\\kozak_ttg.csv");
        FileWriter fw2 = new FileWriter(outputFile2);
        BufferedWriter bw2 = new BufferedWriter(fw2);

        List<String> stopCodons = new ArrayList<>();
        stopCodons.add("TAG");
        stopCodons.add("TAA");
        stopCodons.add("TGA");

        for (FNABean item : items) {
            System.out.println(item);
            bw.write(item + "\r\n");
            List<SubSequence> seqs = Sequences.findKozakSequences(item.getSequence(), "TTG", stopCodons, item.getCdsStart());


            List<String> lens = new ArrayList<>();
            boolean overlap = false;
            for (SubSequence seq : seqs) {
                System.out.println(seq);
                bw.write(seq + "\r\n");
                lens.add("" + seq.getSequence().length());

                if (seq.getStop() > item.getCdsStart()) {
                    overlap = true;
                }
            }

            bw2.write(item.getGi() + ";" + item.getRef() + ";" + item.getName() + ";" + item.getCdsStart() + ".." + item.getCdsStop() + ";" + seqs.size() + ";" + (overlap?"True":"False") + ";" + StringUtils.join(lens, ",") + "\r\n");
        }

        bw.close();
        bw2.close();

        ds.close();
    }
}
