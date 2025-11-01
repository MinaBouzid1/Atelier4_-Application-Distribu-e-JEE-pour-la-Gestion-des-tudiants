# 🧩 Application Distribuée JEE – Gestion des Étudiants

## 📖 Introduction
Ce projet illustre la mise en œuvre d’une **application distribuée Java EE (Jakarta EE)** permettant la gestion des étudiants, modules et notes.  

---


## 🏗️ Architecture Globale
L’application est divisée en **deux modules Maven** :

### 1️⃣ `gestion-etudiants-ejb` (JAR)
- Contient la **logique métier** et la **couche de persistance**
- Implémente les **entités JPA** et les **Session Beans (EJB)**  
- Configure la persistance via `persistence.xml`  
- Expose des **interfaces Remote** accessibles depuis le module web

### 2️⃣ `gestion-etudiants-web` (WAR)
- Fournit l’**interface utilisateur (JSP)** et les **servlets**
- Suit le **pattern MVC2**
- Consomme les services distants du module EJB via **JNDI / @EJB **

---

## 🔗 Communication entre les Modules
- Le module web appelle les beans EJB distants via **JNDI**
- Les transactions sont **gérées automatiquement** par le conteneur EJB
- Les données sont échangées sous forme d’objets **JPA**

---

## 🧠 Technologies Utilisées
| Technologie | Rôle |
|--------------|------|
| **Java / Jakarta EE** | Langage et framework principal |
| **EJB** | Logique métier distribuée |
| **JPA / Hibernate** | Persistance et ORM |
| **Servlets / JSP / JSTL** | Interface utilisateur (MVC2) |
| **MySQL** | Base de données |
| **WildFly** | Serveur d’application |
| **Maven** | Gestion du projet et des dépendances |
| **HTML5 / CSS3** | Présentation et design |

---

## ⚙️ Fonctionnalités Principales
### 👩‍🎓 Gestion des Étudiants
- Ajouter, modifier, supprimer et lister les étudiants  
- Formulaires dynamiques avec validation  

### 📚 Gestion des Modules
- CRUD complet sur les modules d’enseignement  

### 🧾 Gestion des Notes
- Attribution de notes à chaque étudiant et module  
- Affichage coloré selon la performance  

---

## 🖥️ Déploiement
1. Compiler le projet avec Maven  
2. Déployer les deux modules (`.jar` et `.war`) sur **WildFly**  
3. Accéder à l’application via :
    http://localhost:8080/gestion-etudiants-web
