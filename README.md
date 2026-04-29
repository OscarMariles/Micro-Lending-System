# 💰 Micro Lending System

Backend API para la gestión de microcréditos, desarrollado con **Java y Spring Boot**.
Permite la creación, evaluación, aprobación y seguimiento de préstamos, aplicando arquitectura por capas y buenas prácticas de desarrollo.

---

## 🚀 Funcionalidades

* 👤 Gestión de clientes
* 💸 Creación de préstamos
* ✅ Aprobación de préstamos
* ❌ Rechazo de préstamos
* 📊 Consulta de préstamos por cliente
* 🔄 Manejo de estados (PENDING, APPROVED, REJECTED)

---

## 📌 Endpoints principales

| Método | Endpoint              | Descripción                   |
| ------ | --------------------- | ----------------------------- |
| POST   | `/loans`              | Crear préstamo                |
| GET    | `/loans`              | Listar préstamos              |
| GET    | `/clients/{id}/loans` | Obtener préstamos por cliente |
| PUT    | `/loans/{id}/approve` | Aprobar préstamo              |
| PUT    | `/loans/{id}/reject`  | Rechazar préstamo             |

---

## 🧱 Arquitectura

El proyecto sigue una arquitectura por capas:

* **Controller** → Manejo de endpoints
* **Service** → Lógica de negocio
* **Repository** → Acceso a datos
* **DTO** → Transferencia de datos

---

## 🛠️ Tecnologías

* ☕ Java 17+
* 🌱 Spring Boot
* 🗄️ MySQL
* 🔗 JPA / Hibernate
* 🧰 Git & GitHub
* 📬 Postman (testing)

---

## ▶️ Cómo ejecutar el proyecto

1. Clonar repositorio:

```bash
git clone https://github.com/OscarMariles/micro-lending-system.git
```

2. Configurar base de datos en `application.properties`

3. Ejecutar la aplicación:

```bash
mvn spring-boot:run
```

4. Probar endpoints con Postman

---

## 📸 Ejemplo de flujo

1. Crear cliente
2. Crear préstamo
3. Aprobar o rechazar préstamo
4. Consultar estado del préstamo

---

## 🧠 Decisiones técnicas

* Uso de **DTOs** para separar la capa de presentación
* Implementación de **arquitectura por capas** para escalabilidad
* Manejo de lógica de negocio en servicios
* Uso de **JPA/Hibernate** para persistencia

---

## ⚡ Mejoras futuras

* 🔐 Autenticación con JWT
* 📄 Documentación con Swagger
* 🧪 Tests unitarios
* 📦 Dockerización

---

## 👨‍💻 Autor

**Oscar Altamirano**
Backend Developer Jr

📧 [oaltamirano64@gmail.com](mailto:oaltamirano64@gmail.com)
🔗 https://www.linkedin.com/in/oscar-alejandro-altamirano-mariles-948b78210

---

