<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier une Note - FST Tanger</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        :root {
            --primary: #2c3e50;
            --secondary: #34495e;
            --accent: #3498db;
            --warning: #f39c12;
            --light: #ecf0f1;
            --dark: #2c3e50;
            --text: #333333;
            --border: #bdc3c7;
            --error: #e74c3c;
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
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }

        .container {
            max-width: 800px;
            width: 100%;
            background: rgba(255, 255, 255, 0.95);
            border-radius: 15px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
            backdrop-filter: blur(10px);
            overflow: hidden;
        }

        .header {
            background: var(--primary);
            color: white;
            padding: 35px 40px;
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
            background: var(--warning);
        }

        .header h1 {
            font-size: 2rem;
            font-weight: 300;
        }

        .content {
            padding: 40px;
        }

        .form-container {
            background: white;
            border-radius: 10px;
            padding: 35px;
            border: 1px solid var(--border);
        }

        .form-group {
            margin-bottom: 25px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: var(--primary);
            font-weight: 500;
            font-size: 0.95rem;
        }

        input, select {
            width: 100%;
            padding: 12px 15px;
            border: 1px solid var(--border);
            border-radius: 6px;
            font-size: 1rem;
            transition: all 0.3s ease;
            background: white;
        }

        input:focus, select:focus {
            outline: none;
            border-color: var(--warning);
            box-shadow: 0 0 0 3px rgba(243, 156, 18, 0.1);
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

        .btn-warning {
            background: var(--warning);
            color: white;
        }

        .btn-secondary {
            background: #95a5a6;
            color: white;
        }

        .btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }

        .actions {
            display: flex;
            gap: 15px;
            margin-top: 30px;
            flex-wrap: wrap;
        }

        .error-message {
            background: #f8d7da;
            color: var(--error);
            padding: 15px;
            border-radius: 6px;
            margin-bottom: 25px;
            border: 1px solid #f5c6cb;
            font-size: 0.95rem;
        }

        .required::after {
            content: ' *';
            color: var(--error);
        }

        @media (max-width: 768px) {
            .container {
                margin: 0;
            }

            .content {
                padding: 25px;
            }

            .form-container {
                padding: 25px;
            }

            .actions {
                flex-direction: column;
            }

            .btn {
                width: 100%;
                justify-content: center;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <header class="header">
        <h1><i class="fas fa-edit"></i> Modifier une Note</h1>
    </header>

    <div class="content">
        <c:if test="${not empty error}">
            <div class="error-message">
                <i class="fas fa-exclamation-circle"></i> ${error}
            </div>
        </c:if>

        <fmt:formatDate value="${suivie.dateNote}" pattern="yyyy-MM-dd" var="formattedDate"/>

        <div class="form-container">
            <form method="post" action="<%= request.getContextPath() %>/notes/modifier">
                <input type="hidden" name="id" value="${suivie.idSuivie}">

                <div class="form-group">
                    <label for="idEtudiant" class="required">Étudiant</label>
                    <select id="idEtudiant" name="idEtudiant" required>
                        <option value="">Sélectionner un étudiant</option>
                        <c:forEach items="${etudiants}" var="etudiant">
                            <option value="${etudiant.idEtudiant}"
                                ${etudiant.idEtudiant == suivie.etudiant.idEtudiant ? 'selected' : ''}>
                                    ${etudiant.nom} ${etudiant.prenom} (${etudiant.cne})
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="idModule" class="required">Module</label>
                    <select id="idModule" name="idModule" required>
                        <option value="">Sélectionner un module</option>
                        <c:forEach items="${modules}" var="module">
                            <option value="${module.idModule}"
                                ${module.idModule == suivie.module.idModule ? 'selected' : ''}>
                                    ${module.nomModule} (${module.codeModule})
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="note" class="required">Note (sur 20)</label>
                    <input type="number" step="0.5" min="0" max="20" id="note" name="note" value="${suivie.note}" required>
                </div>

                <div class="form-group">
                    <label for="dateNote" class="required">Date</label>
                    <input type="date" id="dateNote" name="dateNote" value="${formattedDate}" required>
                </div>

                <div class="actions">
                    <button type="submit" class="btn btn-warning">
                        <i class="fas fa-save"></i>
                        Mettre à jour
                    </button>
                    <a href="<%= request.getContextPath() %>/notes/liste" class="btn btn-secondary">
                        <i class="fas fa-times"></i>
                        Annuler
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>