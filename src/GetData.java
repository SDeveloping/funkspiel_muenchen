import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class GetData {
    public GetData(){
    }
    public ArrayList<String> get_all_names(){
        ArrayList<String> all_names = new ArrayList<String>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/funkspiel_muenchen", "root", "");
            System.out.println("Con =" + con);
            Statement stmt = con.createStatement();
            String sql = "SELECT u_name FROM users";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next()){
                //rs.next();
                all_names.add(rs.getString("u_name"));
                System.out.println(all_names);
            }

            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return all_names;
    }
    public ArrayList<String> get_all_emails(){
        ArrayList<String> all_emails = new ArrayList<String>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/funkspiel_muenchen", "root", "");
            System.out.println("Con =" + con);
            Statement stmt = con.createStatement();
            String sql = "SELECT u_email FROM users";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next()){
                //rs.next();
                all_emails.add(rs.getString("u_email"));
                System.out.println(all_emails);
            }

            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return all_emails;
    }
    public ArrayList<String> get_email(String username) {
        ArrayList<String> email = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/funkspiel_muenchen", "root", "");
            System.out.println("Con =" + con);
            Statement stmt = con.createStatement();
            String sql = "SELECT u_email FROM users WHERE u_name ='" + username + "'";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next()){
                //rs.next();
                email.add(rs.getString("u_email"));
                System.out.println(email);
            }

            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return email;
    }
    public ArrayList<Date> get_regDate(String username) {
        ArrayList<Date> regDate = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/funkspiel_muenchen", "root", "");
            System.out.println("Con =" + con);
            Statement stmt = con.createStatement();
            String sql = "SELECT regDate FROM users WHERE u_name ='" + username + "'";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next()){
                //rs.next();
                regDate.add(rs.getDate("regDate"));
                System.out.println(regDate);
            }

            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return regDate;
    }
    public ArrayList<String> get_rank(String username) {
        ArrayList<String> rank = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/funkspiel_muenchen", "root", "");
            System.out.println("Con =" + con);
            Statement stmt = con.createStatement();
            String sql = "SELECT rang_id FROM users WHERE u_name ='" + username + "'";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next()){
                //rs.next();
                rank.add(rs.getString("rang_id"));
                System.out.println(rank);
            }

            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return rank;
    }
    public ArrayList<String> get_dispo(String username) {
        ArrayList<String> dispo = new ArrayList<>();
        boolean is_dispo = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/funkspiel_muenchen", "root", "");
            System.out.println("Con =" + con);
            Statement stmt = con.createStatement();
            String sql = "SELECT is_dispo FROM users WHERE u_name ='" + username + "'";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next()){
                //rs.next();
                dispo.add(rs.getString("is_dispo"));
                System.out.println(dispo);
            }

            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return dispo;
    }
}
