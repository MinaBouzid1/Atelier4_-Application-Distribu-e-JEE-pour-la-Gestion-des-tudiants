<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Erreur - FST Tanger</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        :root {
            --primary: #2c3e50;
            --secondary: #34495e;
            --accent: #3498db;
            --error: #e74c3c;
            --light: #ecf0f1;
            --text: #333333;
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

        .error-container {
            max-width: 500px;
            width: 100%;
            background: rgba(255, 255, 255, 0.95);
            border-radius: 15px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
            backdrop-filter: blur(10px);
            padding: 50px 40px;
            text-align: center;
        }

        .error-icon {
            font-size: 4rem;
            color: var(--error);
            margin-bottom: 25px;
        }

        h1 {
            color: var(--primary);
            margin-bottom: 20px;
            font-weight: 300;
            font-size: 1.8rem;
        }

        p {
            color: #666;
            margin-bottom: 30px;
            line-height: 1.6;
        }

        .btn {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            padding: 12px 25px;
            background: var(--accent);
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-weight: 500;
            transition: all 0.3s ease;
        }

        .btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }

        .error-details {
            background: #f8f9fa;
            border-radius: 6px;
            padding: 15px;
            margin-top: 20px;
            font-family: monospace;
            font-size: 0.9rem;
            text-align: left;
            border-left: 4px solid var(--error);
        }
    </style>
</head>
<body>
<div class="error-container">
    <div class="error-icon">
        <i class="fas fa-exclamation-triangle"></i>
    </div>
    <h1>Une erreur s'est produite</h1>
    <p>${error}</p>
    <a href="<%= request.getContextPath() %>/" class="btn">
        <i class="fas fa-home"></i>
        Retour à l'accueil
    </a>

    <c:if test="${not empty pageContext.exception}">
        <div class="error-details">
            <strong>Détails techniques :</strong><br>
                ${pageContext.exception.message}
        </div>
    </c:if>
</div>
</body>
</html>