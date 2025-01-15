package controlador;

import modelo.publicacion;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/publicacion")
public class publicar extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Configuración de la conexión a la base de datos
    private static final String DB_URL = "jdbc:mysql://localhost:3306/soundsphare?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "jensen.dw24";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String content = request.getParameter("content");
        String username = "JuanPerez"; // Usuario que inició sesión
        String date = java.time.LocalDateTime.now().toString();

        // Insertar la publicación en la base de datos
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String insertQuery = "INSERT INTO publicacion (username, content, date) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, content);
                preparedStatement.setString(3, date);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ServletException("Error al insertar la publicación en la base de datos", e);
        }

        // Recuperar todas las publicaciones de la base de datos
        List<publicacion> posts = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String selectQuery = "SELECT username, content, date FROM publicacion ORDER BY date DESC";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String dbUsername = resultSet.getString("username");
                    String dbContent = resultSet.getString("content");
                    String dbDate = resultSet.getString("date");
                    posts.add(new publicacion(dbUsername, dbContent, dbDate));
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Error al recuperar las publicaciones de la base de datos", e);
        }

        // Enviar las publicaciones a la página JSP
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    public void init() throws ServletException {
        // Cargar el controlador JDBC de MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("Error al cargar el controlador JDBC de MySQL", e);
        }
    }
}
