package io.dbc.github.sninventory.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*  public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection coo= DriverManager.getConnection("jdbc:mysql://localhost:3306/demo2","root","");
        PreparedStatement st;
      //  int ch=1;

        switch(2) {
            case 1: {
                 st = coo.prepareStatement("insert into student values (?,?)");
                st.setInt(1, 8);
                st.setString(2, "deepesh");
                st.executeUpdate();

            }

        case 2: {
                 st = coo.prepareStatement("update student set name where roll_no=48"
                 );
                st.setString(1,"nitya");
            st.executeUpdate();
        }

       case 3: {
                st = coo.prepareStatement("delete from student where roll_no=48 ");
           st.executeUpdate();
            }
           break;

     }

        st= coo.prepareStatement("select * from student");
        ResultSet rs=st.executeQuery();
        while(rs.next()){
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
        }

}}
*/
public class DatabaseConnection {

    public static Connection addConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sn-inventory", "root", "");
        System.out.println("Connected");
        return connection;
    }
}

