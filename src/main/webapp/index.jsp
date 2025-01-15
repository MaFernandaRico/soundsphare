<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Red Social</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #FFE4E4;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .container {
            background-color: #fff;
            width: 90%;
            max-width: 1400px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            padding: 20px;
        }
        .navbar {
            background-color: #A30000;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            color: white;
            border-radius: 10px 10px 0 0;
        }
        .navbar .logo {
            font-size: 24px;
            font-weight: bold;
        }
        .navbar .icons button {
            background: none;
            border: none;
            color: white;
            font-size: 20px;
            cursor: pointer;
        }
        .content {
            display: flex;
            gap: 20px;
            margin-top: 20px;
        }
        .left-panel, .right-panel {
            background-color: #FFD3D3;
            padding: 20px;
            border-radius: 10px;
            width: 25%;
            display: flex;
            flex-direction: column;
            gap: 20px;
        }
        .right-panel .box, .left-panel .box {
            background-color: #FFF;
            padding: 15px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .right-panel ul, .left-panel ul {
            list-style: none;
            padding: 0;
            margin: 0;
        }
        .right-panel li, .left-panel li {
            background-color: #FFC6C6;
            margin-bottom: 10px;
            padding: 10px;
            border-radius: 5px;
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
            font-size: 14px;
        }
        .main-panel {
            background-color: #FFE4E4;
            padding: 20px;
            border-radius: 10px;
            width: 50%;
        }
        .post-box {
            background-color: #FFC6C6;
            padding: 15px;
            border-radius: 10px;
            margin-bottom: 20px;
        }
        .post-box textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #CCC;
            border-radius: 5px;
            margin-bottom: 10px;
        }
        .post-box button {
            background-color: #A30000;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
        }
        .post {
            background-color: #FFC6C6;
            padding: 15px;
            border-radius: 10px;
            margin-bottom: 20px;
        }
        .post img {
            max-width: 100%;
            border-radius: 10px;
            margin: 10px 0;
        }
        .post .actions {
            display: flex;
            justify-content: space-between;
            margin-top: 10px;
        }
        .post .actions button {
            background-color: transparent;
            border: none;
            color: #A30000;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="navbar">
            <div class="logo">S</div>
            <div class="search-bar">
                <input type="text" placeholder="Buscar..." style="padding: 5px; border-radius: 5px;">
            </div>
            <div class="icons">
                <button>🎵</button>
                <button>📽️</button>
                <button>🏠</button>
                <button onclick="window.location.href='perfil.jsp';">☺︎</button>
            </div>
        </div>
        <div class="content">
            <!-- Left Panel -->
            <div class="left-panel">
                <div class="box">
                    <h3>Chats</h3>
                    <ul>
                        <li>Chat 1</li>
                        <li>Chat 2</li>
                        <li>Chat 3</li>
                    </ul>
                </div>
                <div class="box">
                    <h3>Canales</h3>
                    <ul>
                        <li>Canal 1</li>
                        <li>Canal 2</li>
                        <li>Canal 3</li>
                    </ul>
                </div>
            </div>
            <!-- Main Panel -->
            <div class="main-panel">
                <div class="post-box">
                    <form method="post" action="publicar">
                        <textarea name="content" placeholder="¿Qué estás pensando?" rows="3" required></textarea>
                        <button type="submit">Publicar</button>
                    </form>
                </div>
                <c:forEach var="post" items="${posts}">
                    <div class="post">
                        <p><strong>${post.username}</strong> - ${post.date}</p>
                        <p>${post.content}</p>
                        <div class="actions">
                            <button>❤️</button>
                            <button>🔁</button>
                            <button>💬</button>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <!-- Right Panel -->
            <div class="right-panel">
                <div class="box">
                    <h3>Notificaciones</h3>
                    <ul>
                        <li>Notificación 1</li>
                        <li>Notificación 2</li>
                        <li>Notificación 3</li>
                    </ul>
                </div>
                <div class="box">
                    <h3>Eventos</h3>
                    <ul>
                        <li>Evento 1</li>
                        <li>Evento 2</li>
                        <li>Evento 3</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
