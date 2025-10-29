<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Étudiants - FST Tanger</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        :root {
            --primary: #2c3e50;
            --secondary: #34495e;
            --accent: #3498db;
            --success: #27ae60;
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
            line-height: 1.6;
        }

        .container {
            max-width: 1200px;
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
            padding: 50px 40px;
            text-align: center;
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
            font-size: 2.5rem;
            margin-bottom: 15px;
            font-weight: 300;
            letter-spacing: 1px;
        }

        .header p {
            font-size: 1.1rem;
            opacity: 0.9;
            font-weight: 300;
        }

        .main-content {
            padding: 50px 40px;
        }

        .features-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 30px;
            margin-bottom: 40px;
        }

        .feature-card {
            background: white;
            border-radius: 10px;
            padding: 35px 30px;
            text-align: center;
            transition: all 0.3s ease;
            border: 1px solid var(--border);
            position: relative;
        }

        .feature-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
            border-color: var(--accent);
        }

        .feature-icon {
            width: 70px;
            height: 70px;
            background: var(--accent);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 20px;
            color: white;
            font-size: 1.8rem;
        }

        .feature-card h3 {
            font-size: 1.4rem;
            margin-bottom: 15px;
            color: var(--primary);
            font-weight: 600;
        }

        .feature-card p {
            color: #666;
            margin-bottom: 20px;
            font-size: 0.95rem;
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
            border: none;
            cursor: pointer;
            font-size: 0.95rem;
        }

        .btn:hover {
            background: #2980b9;
            transform: translateY(-2px);
        }

        .stats {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            margin-top: 40px;
        }

        .stat-item {
            text-align: center;
            padding: 25px;
            background: white;
            border-radius: 8px;
            border: 1px solid var(--border);
        }

        .stat-number {
            display: block;
            font-size: 2.2rem;
            font-weight: 700;
            color: var(--accent);
            margin-bottom: 5px;
        }

        .stat-label {
            color: #666;
            font-size: 0.9rem;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        .footer {
            background: var(--primary);
            color: white;
            text-align: center;
            padding: 30px;
            margin-top: 40px;
        }

        .footer p {
            margin-bottom: 8px;
            opacity: 0.8;
            font-size: 0.9rem;
        }

        @media (max-width: 768px) {
            .container {
                margin: 20px;
            }

            .header {
                padding: 40px 20px;
            }

            .header h1 {
                font-size: 2rem;
            }

            .main-content {
                padding: 30px 20px;
            }

            .features-grid {
                grid-template-columns: 1fr;
            }
        }

        .accent-text {
            color: var(--accent);
            font-weight: 600;
        }
    </style>
</head>
<body>
<div class="container">
    <header class="header">
        <h1>Gestion des Étudiants</h1>
        <p>Faculté des Sciences et Techniques - Tanger</p>
        <p>Système de gestion académique intégré</p>
    </header>

    <div class="main-content">
        <div class="features-grid">
            <div class="feature-card">
                <div class="feature-icon">
                    <i class="fas fa-users"></i>
                </div>
                <h3>Gestion des Étudiants</h3>
                <p>Gérez les inscriptions, profils et informations personnelles des étudiants de manière centralisée et sécurisée.</p>
                <a href="<%= request.getContextPath() %>/etudiants/liste" class="btn">
                    <i class="fas fa-arrow-right"></i>
                    Accéder
                </a>
            </div>

            <div class="feature-card">
                <div class="feature-icon">
                    <i class="fas fa-book"></i>
                </div>
                <h3>Modules d'Enseignement</h3>
                <p>Administrez les modules, coefficients et programmes de formation avec une interface intuitive et complète.</p>
                <a href="<%= request.getContextPath() %>/modules/liste" class="btn">
                    <i class="fas fa-arrow-right"></i>
                    Accéder
                </a>
            </div>

            <div class="feature-card">
                <div class="feature-icon">
                    <i class="fas fa-chart-bar"></i>
                </div>
                <h3>Suivi Académique</h3>
                <p>Enregistrez et analysez les résultats avec des outils de reporting avancés pour un suivi optimal.</p>
                <a href="<%= request.getContextPath() %>/notes/liste" class="btn">
                    <i class="fas fa-arrow-right"></i>
                    Accéder
                </a>
            </div>
        </div>

        <div class="stats">
            <div class="stat-item">
                <span class="stat-number" id="studentsCount">0</span>
                <span class="stat-label">Étudiants</span>
            </div>
            <div class="stat-item">
                <span class="stat-number" id="modulesCount">0</span>
                <span class="stat-label">Modules</span>
            </div>
            <div class="stat-item">
                <span class="stat-number" id="notesCount">0</span>
                <span class="stat-label">Notes</span>
            </div>
            <div class="stat-item">
                <span class="stat-number">3</span>
                <span class="stat-label">Modules Actifs</span>
            </div>
        </div>
    </div>

    <footer class="footer">
        <p>&copy; 2024 FST Tanger - Département Génie Informatique</p>
        <p>Architecture Three-Tier - EJB3 / JPA / WildFly</p>
        <p>Système développé pour la gestion académique</p>
    </footer>
</div>

<script>
    // Animation simple des compteurs
    function animateCounter(element, target) {
        let count = 0;
        const increment = target / 50;
        const timer = setInterval(() => {
            count += increment;
            if (count >= target) {
                count = target;
                clearInterval(timer);
            }
            element.textContent = Math.floor(count);
        }, 40);
    }

    // Initialisation après le chargement
    setTimeout(() => {
        animateCounter(document.getElementById('studentsCount'), 127);
        animateCounter(document.getElementById('modulesCount'), 18);
        animateCounter(document.getElementById('notesCount'), 543);
    }, 500);
</script>
</body>
</html>