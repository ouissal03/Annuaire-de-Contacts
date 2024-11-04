import java.sql.*;

public class DB_Connection {
    public String url = "jdbc:mysql://localhost:3307/Projet_Contact";
    public Connection con = null;

    public void Connecter() {
        if (con == null) {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, "root", "");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void Deconnecter() {
        if (con != null)
            try {
                con.close();
                con = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}