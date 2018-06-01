package ru.babudzhi;

import org.springframework.stereotype.Service;
import ru.babudzhi.person.Person;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceBD {
    private static final String CREATE_QUERY = "CREATE TABLE IF NOT EXISTS TEST123 (name3 VARCHAR(45), name1 VARCHAR(45), name2 varchar (45))";
    private String DATA_QUERY = "";
    private static final String DELETE_QUERY = "DROP TABLE IF EXISTS TEST123";
    public List<String> listResults;


    public void controller(boolean dropTable, Person pers) throws ServletException, IOException {
        DATA_QUERY = "INSERT INTO TEST123 VALUES ('" + pers.getName3() + "','" + pers.getName1() + "','" + pers.getName2() + "')";
        try {
            DriverManager.registerDriver(new org.h2.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection db = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "")) {
            try (Statement dataQuery = db.createStatement()) {
                if (dropTable == true) dataQuery.execute(DELETE_QUERY);
                dataQuery.execute(CREATE_QUERY);
                if (pers.getName1() != null && pers.getName2() != null && pers.getName3() != null)
                    dataQuery.execute(DATA_QUERY);
            }
            PreparedStatement query = db.prepareStatement("SELECT * FROM TEST123");
            ResultSet rs = query.executeQuery();

            listResults = convertToList(rs);
        } catch (SQLException ex) {
            System.out.println("Database connection failure: "
                    + ex.getMessage());
        }
    }

    protected List convertToList(ResultSet rs) throws SQLException {
        List<String> arrayList = new ArrayList<>();
        while (rs.next()) {
            String str;
            str = String.format("%s %s %s ", rs.getString(1), rs.getString(2), rs.getString(3));
            arrayList.add(str);
        }
        return arrayList;
    }

    public List<String> getListResults() {
        if(!(listResults.isEmpty()))
            return listResults;
        else
            return null;
    }

}
