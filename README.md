# 📌 Conducta de Entrada – API REST con Spring Boot

## 📖 Descripción

Este proyecto consiste en el desarrollo de una API REST utilizando Spring Boot, implementando una arquitectura desacoplada basada en Controllers, Services, DTOs y Entities, junto con persistencia en base de datos relacional.

La aplicación incluye autenticación con Spring Security y control de acceso a los diferentes endpoints según el nivel de seguridad requerido.

El servidor está configurado para ejecutarse en el puerto **9000**.

---

## 🚀 Funcionalidades

### 🔐 1. Crear Usuario (Requiere Autenticación)

Permite registrar un usuario con los siguientes campos:

- id  
- nombre  
- apellido  
- username  
- password  

**Condiciones:**

- Solo puede crear usuarios quien esté autenticado.
- La respuesta devuelve únicamente:
  - nombre
  - apellido
- Ambos campos se retornan en MAYÚSCULAS.

---

### 🌍 2. Obtener todos los usuarios (Sin Autenticación)

Endpoint público que permite consultar la lista completa de usuarios registrados en la base de datos.

---

### 🔎 3. Obtener usuario por ID (Requiere Autenticación)

Permite consultar un usuario específico mediante su ID.  
Solo puede acceder quien esté autenticado.

---

### 🧾 4. Endpoint con Parámetros en URL (Sin Autenticación)

Permite enviar parámetros por URL y obtener un JSON con el nombre completo.

**Ejemplo:**
http://localhost:9000/api/params?nombre=xxx&apellido=yyy


**Respuesta:**

```json
{
  "nombreCompleto": "xxx yyy"
}
```
## ⚙️ Tecnologías Utilizadas

- Spring Boot  
- Spring Security  
- Spring Data JPA  
- Hibernate  
- Base de datos relacional (MySQL / H2 según configuración)  
- Maven  

---

## 🏗️ Arquitectura

El proyecto fue desarrollado siguiendo principios de:

- Separación de responsabilidades  
- Desacoplamiento en capas  
- Buenas prácticas de desarrollo backend  
- Seguridad en endpoints  

### 📂 Estructura organizada en:

- Controller  
- Service  
- DTO  
- Entity  
- Repository  

---

## ▶️ Ejecución del Proyecto

1. Clonar el repositorio.  
2. Configurar la base de datos en `application.properties`.  
3. Ejecutar la aplicación.  
4. Acceder desde:
http://localhost:9000


---

## 👨‍💻 Autor

**Víctor José Castillo Castro**  
Ingeniería de Sistemas
