<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil - ${user.nombreUsuario}</title>
    <style>
        :root {
            --color1: #dacdbd;
            --color2: #f2b8a0;
            --color3: #ef97a3;
            --color4: #df5c7e;
            --color5: #d4486f;
        }

        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: var(--color1);
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
            width: 100%;
        }

        .profile-container {
            background-color: #fff;
            border: 5px solid var(--color5);
            border-radius: 15px;
            width: 80%;
            max-width: 1200px; /* Aumenta el tamaño máximo */
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            overflow: hidden;
            margin-top: 30px;
        }

        .profile-header {
            position: relative;
            background-color: var(--color2);
            padding: 30px;
            text-align: center;
            border-bottom: 3px solid var(--color3);
        }

        .profile-header .profile-picture {
            position: absolute;
            top: 50%;
            left: 5%;
            transform: translateY(-50%);
            width: 150px;
            height: 150px;
            border-radius: 50%;
            overflow: hidden;
            border: 3px solid var(--color1);
            background-color: var(--color4);
        }

        .profile-header .profile-picture img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        .profile-header .profile-details {
            margin-left: 200px;
            margin-top: 10px;
            text-align: left;
            color: var(--color4);
        }

        .profile-header .profile-details h1 {
            font-size: 30px;
            margin: 0;
        }

        .profile-header .profile-details p {
            font-size: 18px;
            color: var(--color5);
            margin: 5px 0;
        }

        .nav-tabs {
            background-color: var(--color1);
            padding: 15px;
            border-top: 3px solid var(--color3);
        }

        .nav-tabs ul {
            list-style: none;
            padding: 0;
            margin: 0;
            display: flex;
            gap: 30px;
            justify-content: center;
        }

        .nav-tabs ul li {
            display: inline-block;
            font-size: 18px;
        }

        .nav-tabs ul li a {
            text-decoration: none;
            color: var(--color5);
            padding: 15px 25px;
            border-radius: 5px;
            font-weight: bold;
        }

        .nav-tabs ul li a.active {
            background-color: var(--color4);
            color: #fff;
        }

        .content {
            padding: 30px;
            background-color: var(--color1);
            min-height: 400px; /* Aumenta la altura mínima del contenido */
        }

        .post {
            background-color: var(--color2);
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .post p {
            color: var(--color5);
            font-size: 16px;
        }

    </style>
</head>
<body>
    <div class="profile-container">
        <!-- Cabecera del perfil -->
        <div class="profile-header">
            <div class="profile-picture">
                <img src="path/to/profile-picture.jpg" alt="Foto de perfil">
            </div>
            <div class="profile-details">
                <h1>${user.nombreUsuario}</h1>
                <p>${user.amigos.size()} Juan Perez</p>
            </div>
        </div>

        <!-- Navegación -->
        <div class="nav-tabs">
            <ul>
                <li><a href="profile?userId=${user.idUsuario}&tab=publicaciones" class="${tab == 'publicaciones' ? 'active' : ''}">Publicaciones</a></li>
                <li><a href="profile?userId=${user.idUsuario}&tab=informacion" class="${tab == 'informacion' ? 'active' : ''}">Información</a></li>
                <li><a href="profile?userId=${user.idUsuario}&tab=amigos" class="${tab == 'amigos' ? 'active' : ''}">Amigos</a></li>
            </ul>
        </div>

        <!-- Contenido dinámico -->
        <div class="content">
            <c:choose>
                <c:when test="${tab == 'publicaciones'}">
                    <c:forEach var="post" items="${user.publicaciones}">
                        <div class="post">
                            <p>${post}</p>
                        </div>
                    </c:forEach>
                </c:when>
                <c:when test="${tab == 'informacion'}">
                    <p>Nombre: ${user.nombreUsuario}</p>
                    <p>Correo: ${user.correo}</p>
                    <p>Fecha de nacimiento: ${user.fechaNacimiento}</p>
                </c:when>
                <c:when test="${tab == 'amigos'}">
                    <ul>
                        <c:forEach var="amigo" items="${user.amigos}">
                            <li>${amigo}</li>
                        </c:forEach>
                    </ul>
                </c:when>
            </c:choose>
        </div>
    </div>
</body>
</html>

