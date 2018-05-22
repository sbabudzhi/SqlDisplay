package ru.babudzhi;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
@WebServlet("/bd")
public class SqlDisplayServlet extends HttpServlet {

    String name1 = "";
    String name2 = "";
    String name3 = "";

    private static final String CREATE_QUERY = "CREATE TABLE IF NOT EXISTS TEST123 (name3 VARCHAR(45), name1 VARCHAR(45), name2 varchar (45))";
    private String DATA_QUERY = "";
    private static final String DELETE_QUERY =  "DROP TABLE IF EXISTS TEST123";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* возвращается ссылка на сессию для текущего пользователя (если сессия еще не существует, то она при этом создается) */
        response.setContentType("text/html;charset=utf8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();

        if(request.getParameter("firstName")!=null){
            name1 = new String(request.getParameter("firstName").getBytes("iso-8859-1"), "utf8");
            session.setAttribute("name1", name1);
        }

        if(request.getParameter("middleName")!=null){
            name2 = new String(request.getParameter("middleName").getBytes("iso-8859-1"), "utf8");
            session.setAttribute("name2", name2);}

        if(request.getParameter("lastName")!=null) {
            name3 = new String(request.getParameter("lastName").getBytes("iso-8859-1"), "utf8");
            session.setAttribute("name3", name3);
        }

        DATA_QUERY = "INSERT INTO TEST123 VALUES ('" + name3 + "','" + name1 + "','" + name2 + "')";

        try {
            DriverManager.registerDriver(new org.h2.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection db = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "")) {
            try (Statement dataQuery = db.createStatement()) {

                if(session.getAttribute("dropTable").equals(true))
                dataQuery.execute(DELETE_QUERY);

                dataQuery.execute(CREATE_QUERY);
                dataQuery.execute(DATA_QUERY);
            }
        } catch (SQLException ex) {
            System.out.println("Database connection failure: "
                    + ex.getMessage());
        }
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

    }


}
