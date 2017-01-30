package nl.antondenhoed.rna.beans;

/**
 * Created by Anton den Hoed on 6/12/2016.
 */
public class FNABean {
    private Integer id;
    private String gi;
    private String ref;
    private String name;
    private String sequence;
    private Integer cdsStart;
    private Integer cdsStop;

    public static FNABean fromIdLine(String line) {
        String[] parts = line.replace(">", "").split("\\|");

        FNABean result = new FNABean();
        result.setGi(parts[1].trim());
        result.setRef(parts[3].trim());
        result.setName(parts[4].trim());
        return result;
    }

    public String toInsertQuery() {
        return "INSERT INTO rna (gi,ref,name,sequence) VALUES ('" + gi + "', '" + ref + "', '" + name.replace("\'", "\\'") + "', '" + sequence + "');\r\n";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGi() {
        return gi;
    }

    public void setGi(String gi) {
        this.gi = gi;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Integer getCdsStart() {
        return cdsStart;
    }

    public void setCdsStart(Integer cdsStart) {
        this.cdsStart = cdsStart;
    }

    public Integer getCdsStop() {
        return cdsStop;
    }

    public void setCdsStop(Integer cdsStop) {
        this.cdsStop = cdsStop;
    }

    @Override
    public String toString() {
        return ">gi|" + gi + "|ref|" + ref + "|" + name;
    }
}
