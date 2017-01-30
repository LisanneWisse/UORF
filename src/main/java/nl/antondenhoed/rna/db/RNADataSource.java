package nl.antondenhoed.rna.db;

import nl.antondenhoed.rna.beans.FNABean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton den Hoed on 6/12/2016.
 */
public class RNADataSource {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3307/fasta";

    private static final String USER = "root";
    private static final String PASS = "usbw";

    private Connection conn = null;

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<FNABean> findByGiRef(String gi, String ref) {
        return findWhere("gi = '" + gi + "' AND ref = '" + ref + "'");
    }

    public List<FNABean> findWhere(String where) {
        List<FNABean> result = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM rna WHERE " + where;
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                FNABean bean = new FNABean();
                bean.setId(rs.getInt("id"));
                bean.setGi(rs.getString("gi"));
                bean.setRef(rs.getString("ref"));
                bean.setName(rs.getString("name"));
                bean.setSequence(rs.getString("sequence"));
                bean.setCdsStart(rs.getInt("cds_start"));
                bean.setCdsStop(rs.getInt("cds_stop"));
                result.add(bean);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
