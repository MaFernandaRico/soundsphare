package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.publicacion;

public class DB{

    private static final String DB_URL = "jdbc:mysql://localhost:3306/soundsphare?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "jensen.dw24";

    // Método para insertar una nueva publicación
    public void insertarPublicacion(String username, String content, String date) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String insertQuery = "INSERT INTO publicacion (username, content, date) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, content);
                preparedStatement.setString(3, date);
                preparedStatement.executeUpdate();
            }
        }
    }

    // Método para obtener todas las publicaciones
    public List<publicacion> obtenerPublicaciones() throws SQLException {
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
        }
        return posts;
    }

    // Método para obtener el perfil de un usuario
    public perfil getUserProfile(int userId) {
        String username = null;
        String email = null;
        String registrationDate = null;
        String birthDate = null;
        List<String> posts = new ArrayList<>();
        List<String> friends = new ArrayList<>();
        int friendsCount = 0;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Obtener información personal
            String userInfoQuery = "SELECT nombreUsuario, correo, DATE_FORMAT(fechaRegistro, '%Y-%m-%d') AS fechaRegistro, DATE_FORMAT(fechaNacimiento, '%Y-%m-%d') AS fechaNacimiento FROM Usuarios WHERE idUsuario = ?";
            try (PreparedStatement stmt = connection.prepareStatement(userInfoQuery)) {
                stmt.setInt(1, userId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        username = rs.getString("nombreUsuario");
                        email = rs.getString("correo");
                        registrationDate = rs.getString("fechaRegistro");
                        birthDate = rs.getString("fechaNacimiento");
                    }
                }
            }

            // Obtener publicaciones (si existiera tabla `publicaciones`)
            String postsQuery = "SELECT contenido FROM publicaciones WHERE idUsuario = ?";
            try (PreparedStatement stmt = connection.prepareStatement(postsQuery)) {
                stmt.setInt(1, userId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        posts.add(rs.getString("contenido"));
                    }
                }
            }

            // Obtener amigos
            String friendsQuery = "SELECT nombre FROM amigos WHERE estado = 'aceptada' AND idUsuario = ?";
            try (PreparedStatement stmt = connection.prepareStatement(friendsQuery)) {
                stmt.setInt(1, userId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        friends.add(rs.getString("nombre"));
                    }
                }
            }

            friendsCount = friends.size();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new perfil(username, email, registrationDate, birthDate, friendsCount, posts, friends);
    }
}
