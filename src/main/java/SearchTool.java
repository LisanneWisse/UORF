import nl.antondenhoed.rna.algorithms.Sequences;
import nl.antondenhoed.rna.beans.FNABean;
import nl.antondenhoed.rna.beans.SubSequence;
import nl.antondenhoed.rna.db.RNADataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Anton den Hoed on 6/12/2016.
 */
public class SearchTool {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("GI: ");
        String gi = input.nextLine();

        System.out.print("REF: ");
        String ref = input.nextLine();

        RNADataSource ds = new RNADataSource();
        ds.connect();

        List<FNABean> items = ds.findByGiRef(gi, ref);

        List<String> stopCodons = new ArrayList<>();
        stopCodons.add("TAG");
        stopCodons.add("TAA");
        stopCodons.add("TGA");

        for (FNABean item : items) {
            System.out.println(item);
            List<SubSequence> subs = Sequences.findSubSequences(item.getSequence(), "ATG", stopCodons, item.getCdsStart());

            for (SubSequence sub : subs) {
                System.out.println(sub);
            }
        }

        ds.close();
    }

}
