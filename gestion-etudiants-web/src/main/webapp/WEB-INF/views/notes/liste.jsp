<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Notes - FST Tanger</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        :root {
            --primary: #2c3e50;
            --secondary: #34495e;
            --accent: #3498db;
            --success: #27ae60;
            --danger: #e74c3c;
            --warning: #f39c12;
            --light: #ecf0f1;
            --dark: #2c3e50;
            --text: #333333;
            --border: #bdc3c7;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            color: var(--text);
        }

        .container {
            max-width: 1400px;
            margin: 40px auto;
            background: rgba(255, 255, 255, 0.95);
            border-radius: 15px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
            backdrop-filter: blur(10px);
            overflow: hidden;
        }

        .header {
            background: var(--primary);
            color: white;
            padding: 40px;
            position: relative;
        }

        .header::after {
            content: '';
            position: absolute;
            bottom: 0;
            left: 50%;
            transform: translateX(-50%);
            width: 100px;
            height: 4px;
            background: var(--accent);
        }

        .header h1 {
            font-size: 2.2rem;
            margin-bottom: 10px;
            font-weight: 300;
        }

        .header p {
            opacity: 0.9;
            font-weight: 300;
        }

        .toolbar {
            background: white;
            padding: 25px 40px;
            border-bottom: 1px solid var(--border);
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-wrap: wrap;
            gap: 15px;
        }

        .btn {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            padding: 12px 25px;
            border-radius: 6px;
            text-decoration: none;
            font-weight: 500;
            transition: all 0.3s ease;
            border: none;
            cursor: pointer;
            font-size: 0.95rem;
        }

        .btn-success {
            background: var(--success);
            color: white;
        }

        .btn-secondary {
            background: #95a5a6;
            color: white;
        }

        .btn-warning {
            background: var(--warning);
            color: white;
        }

        .btn-danger {
            background: var(--danger);
            color: white;
        }

        .btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }

        .content {
            padding: 40px;
        }

        .table-container {
            background: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
            border: 1px solid var(--border);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
        }

        thead {
            background: var(--primary);
        }

        th {
            padding: 18px 20px;
            text-align: left;
            color: white;
            font-weight: 500;
            font-size: 0.9rem;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        tbody tr {
            transition: background-color 0.3s ease;
            border-bottom: 1px solid var(--border);
        }

        tbody tr:hover {
            background: #f8f9fa;
        }

        tbody tr:last-child {
            border-bottom: none;
        }

        td {
            padding: 16px 20px;
            color: #555;
        }

        .action-buttons {
            display: flex;
            gap: 8px;
        }

        .btn-sm {
            padding: 8px 16px;
            font-size: 0.85rem;
        }

        .empty-state {
            text-align: center;
            padding: 60px 40px;
            color: #7f8c8d;
        }

        .empty-state i {
            font-size: 3rem;
            margin-bottom: 20px;
            color: #bdc3c7;
        }

        .empty-state h3 {
            font-size: 1.4rem;
            margin-bottom: 10px;
            color: #2c3e50;
        }

        .note-badge {
            padding: 6px 12px;
            border-radius: 15px;
            font-size: 0.85rem;
            font-weight: 600;
            color: white;
        }

        .note-excellent { background: #27ae60; }
        .note-good { background: #3498db; }
        .note-average { background: #f39c12; }
        .note-poor { background: #e74c3c; }

        .search-box {
            display: flex;
            align-items: center;
            background: white;
            border: 1px solid var(--border);
            border-radius: 6px;
            padding: 10px 15px;
            gap: 10px;
        }

        .search-box input {
            border: none;
            outline: none;
            font-size: 0.95rem;
            width: 250px;
            background: transparent;
        }

        .search-box i {
            color: #7f8c8d;
        }

        @media (max-width: 768px) {
            .container {
                margin: 20px;
            }

            .toolbar {
                flex-direction: column;
                align-items: stretch;
            }

            .search-box {
                width: 100%;
            }

            .search-box input {
                width: 100%;
            }

            .action-buttons {
                flex-direction: column;
            }

            table {
                display: block;
                overflow-x: auto;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <header class="header">
        <h1><i class="fas fa-chart-bar"></i> Liste des Notes</h1>
        <p>Gestion des résultats académiques</p>
    </header>

    <div class="toolbar">
        <div class="search-box">
            <i class="fas fa-search"></i>
            <input type="text" id="searchInput" placeholder="Rechercher une note...">
        </div>
        <div>
            <a href="<%= request.getContextPath() %>/notes/ajouter" class="btn btn-success">
                <i class="fas fa-plus"></i>
                Nouvelle Note
            </a>
            <a href="<%= request.getContextPath() %>/" class="btn btn-secondary">
                <i class="fas fa-home"></i>
                Accueil
            </a>
        </div>
    </div>

    <div class="content">
        <c:choose>
            <c:when test="${empty notes}">
                <div class="empty-state">
                    <i class="fas fa-chart-line"></i>
                    <h3>Aucune note enregistrée</h3>
                    <p>Commencez par ajouter la première note</p>
                    <a href="<%= request.getContextPath() %>/notes/ajouter" class="btn btn-success" style="margin-top: 20px;">
                        <i class="fas fa-plus"></i>
                        Ajouter une note
                    </a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="table-container">
                    <table>
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Étudiant</th>
                            <th>Module</th>
                            <th>Note</th>
                            <th>Date</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${notes}" var="suivie">
                            <tr>
                                <td><strong>#${suivie.idSuivie}</strong></td>
                                <td>
                                    <strong>${suivie.etudiant.nom} ${suivie.etudiant.prenom}</strong>
                                </td>
                                <td>${suivie.module.nomModule}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${suivie.note >= 16}">
                                            <span class="note-badge note-excellent">${suivie.note}/20</span>
                                        </c:when>
                                        <c:when test="${suivie.note >= 14}">
                                            <span class="note-badge note-good">${suivie.note}/20</span>
                                        </c:when>
                                        <c:when test="${suivie.note >= 10}">
                                            <span class="note-badge note-average">${suivie.note}/20</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="note-badge note-poor">${suivie.note}/20</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><fmt:formatDate value="${suivie.dateNote}" pattern="dd/MM/yyyy"/></td>
                                <td>
                                    <div class="action-buttons">
                                        <a href="<%= request.getContextPath() %>/notes/modifier?id=${suivie.idSuivie}"
                                           class="btn btn-warning btn-sm">
                                            <i class="fas fa-edit"></i>
                                            Modifier
                                        </a>
                                        <a href="<%= request.getContextPath() %>/notes/supprimer?id=${suivie.idSuivie}"
                                           class="btn btn-danger btn-sm"
                                           onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette note ?')">
                                            <i class="fas fa-trash"></i>
                                            Supprimer
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<script>
    document.getElementById('searchInput').addEventListener('input', function(e) {
        const searchTerm = e.target.value.toLowerCase();
        const rows = document.querySelectorAll('tbody tr');

        rows.forEach(row => {
            const text = row.textContent.toLowerCase();
            row.style.display = text.includes(searchTerm) ? '' : 'none';
        });
    });
</script>
</body>
</html>