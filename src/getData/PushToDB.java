package getData;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PushToDB {

    public void push( List<List<String>> mightyList){

        //unwrap the list
        for(List<String> list: mightyList) {

        }
        //generate the sql insert statements for every course
        //add to batch


        try (
            //1: allocate a database connection object
            Connection connection = DriverManager.getConnection("jdbc:derby:test_db;create=false");

            //2: Allocate a statement object in the connection
            Statement statement = connection.createStatement();
         ){
            //3: & 4: Execute a sql update via executeUpdate()
            // which returns an int indicating the nubmer of rows affected.
            // increase the price by 7% and qty by 1 for id=1001

            int returnCode;
            returnCode = statement.executeUpdate("create table test_table3 (id int primary key, name varchar(20))");
            System.out.println(returnCode + " records affected.");

            returnCode = statement.executeUpdate(
                    "insert into test_table3 values (1, 'one'), (2, 'two')");
            System.out.println(returnCode + " records affected.");

            ResultSet rset = statement.executeQuery("select * from test_table3");
            while(rset.next()) {
                System.out.println(rset.getInt("id") + ", " + rset.getString("name"));
            }
        } catch(SQLException ex ) {
            ex.printStackTrace();
        }
        //5: close the resources - done automatically by try-with-resources

        //6: shutdown the derby
        try{
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch( SQLException ex ) {
            if( ex.getSQLState().equals("XJ015")) {
                System.out.println("Successfully shutdown!");
            }
        }

    }

}
