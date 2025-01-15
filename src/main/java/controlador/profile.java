package controlador;

import modelo.perfil;
import modelo.DB;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/profile")
public class profile extends HttpServlet {
    private DB userService = new DB();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el parámetro "tab" y establecer valor por defecto
        String tab = request.getParameter("tab");
        if (tab == null || tab.isEmpty()) {
            tab = "publicaciones";
        }

        // Validar y convertir el parámetro "userId"
        String userIdParam = request.getParameter("userId");
        if (userIdParam == null || userIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El parámetro 'userId' es requerido.");
            return;
        }

        int userId;
        try {
            userId = Integer.parseInt(userIdParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El parámetro 'userId' debe ser un número válido.");
            return;
        }

        // Obtener el perfil del usuario desde la base de datos
        perfil userProfile = userService.getUserProfile(userId);
        if (userProfile == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuario no encontrado.");
            return;
        }

        // Configurar atributos para la vista
        request.setAttribute("user", userProfile);
        request.setAttribute("tab", tab);

        // Redirigir al JSP correspondiente
        request.getRequestDispatcher("/WEB-INF/perfil.jsp").forward(request, response);
    }
}
