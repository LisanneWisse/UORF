package nl.antondenhoed.rna.algorithms;

import nl.antondenhoed.rna.beans.SubSequence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton den Hoed on 6/12/2016.
 */
public class Sequences {

    public static List<Integer> findSubSequence(String sequence, String subSequence, Integer cdsStart) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < (cdsStart != null ? cdsStart - 1 : sequence.length());) {
            if (sequence.substring(i, Math.min(sequence.length(), i + subSequence.length())).equals(subSequence)) {
                result.add(i + 1);
            }
            i++;
        }
        return result;
    }

    public static List<Integer> findKozakStartCandidates(String sequence, String subSequence, Integer cdsStart) {
        List<Integer> seqs = findSubSequence(sequence, subSequence, cdsStart);

        List<Integer> result = new ArrayList<>();
        for (Integer seq : seqs) {
            if (seq > 3 && (sequence.charAt(seq - 3 - 1) == 'A' || sequence.charAt(seq - 3 - 1) == 'G') && (sequence.charAt(seq + 3 - 1) == 'A' || sequence.charAt(seq + 3 - 1) == 'G')) {
                result.add(seq);
            }
        }
        return result;
    }

    public static List<SubSequence> findSubSequences(String sequence, String startCodon, List<String> stopCodons, Integer cdsStart) {
        List<SubSequence> result = new ArrayList<>();

        List<Integer> startPositions = findSubSequence(sequence, startCodon, cdsStart);
        System.out.println(startPositions);

        for (Integer startPosition : startPositions) {
            boolean proceed = true;
            for (int i = startPosition - 1; i < sequence.length() && proceed; i += 3) {
                if (stopCodons.contains(sequence.substring(i, Math.min(sequence.length(), i + 3)))) {
                    proceed = false;

                    result.add(new SubSequence(startPosition, i + 1 + 3, sequence.substring(startPosition - 1, i + 3)));
                }
            }
        }

        return result;
    }

    public static List<SubSequence> findKozakSequences(String sequence, String startCodon, List<String> stopCodons, Integer cdsStart) {
        List<SubSequence> result = new ArrayList<>();

        List<Integer> startPositions = findKozakStartCandidates(sequence, startCodon, cdsStart);
        System.out.println(startPositions);

        for (Integer startPosition : startPositions) {
            boolean proceed = true;
            for (int i = startPosition - 1; i < sequence.length() && proceed; i += 3) {
                if (stopCodons.contains(sequence.substring(i, Math.min(sequence.length(), i + 3)))) {
                    proceed = false;

                    result.add(new SubSequence(startPosition, i + 1 + 3, sequence.substring(startPosition - 1, i + 3)));
                }
            }
        }

        return result;
    }
}
