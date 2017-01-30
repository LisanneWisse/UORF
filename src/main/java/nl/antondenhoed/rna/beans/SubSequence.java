package nl.antondenhoed.rna.beans;

/**
 * Created by Anton den Hoed on 6/12/2016.
 */
public class SubSequence {
    private Integer start;
    private Integer stop;
    private String sequence;

    public SubSequence(Integer start, Integer stop, String sequence) {
        this.start = start;
        this.stop = stop;
        this.sequence = sequence;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getStop() {
        return stop;
    }

    public void setStop(Integer stop) {
        this.stop = stop;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "SubSequence(" + start + ", " + stop + ", #" + sequence.length() + ") = "+ sequence;
    }
}
