# pruebaClaro - Microservicio de Gestión de Clientes

Microservicio REST desarrollado con **Java 17 y Spring Boot** como solución de referencia para la gestión de clientes.

El proyecto implementa persistencia con **Oracle Database**, documentación mediante **Swagger/OpenAPI**, contenerización con **Docker**, despliegue en **Kubernetes**, CI mediante **GitHub Actions**, Infrastructure as Code con **Terraform** y un flujo GitOps automatizado utilizando **Argo CD**.

---

## Tecnologías

- Java 17
- Spring Boot
- Maven
- Spring Data JPA
- Oracle Database
- Swagger / OpenAPI
- Docker
- Kubernetes
- Terraform
- Git / GitHub
- GitHub Actions
- Argo CD

---

## Arquitectura

La aplicación sigue una arquitectura por capas:

```text
Cliente HTTP
     |
     v
ClienteController
     |
     v
ClienteService
     |
     v
ClienteServiceImpl
     |
     v
ClienteRepository
     |
     v
Oracle Database
```

Las responsabilidades se encuentran separadas en:

- **Controller:** exposición de endpoints REST.
- **DTO:** entrada y salida de información.
- **Service:** definición de operaciones de negocio.
- **ServiceImpl:** implementación de reglas de negocio.
- **Repository:** acceso y persistencia de datos.
- **Entity:** representación de entidades JPA.
- **Exception:** manejo centralizado de excepciones.

---

## Estructura del proyecto

```text
pruebaClaro/
|
├── src/
│   ├── main/
│   │   ├── java/com/prueba/claro/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── entity/
│   │   │   ├── exception/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── ClaroApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│
├── k8s/
│   ├── deployment.yaml
│   └── service.yaml
│
├── argocd/
│   └── application.yaml
│
├── infra/
│   ├── main.tf
│   ├── variables.tf
│   └── outputs.tf
│
├── .github/
│   └── workflows/
│       └── ci.yml
│
├── Dockerfile
├── .dockerignore
├── .gitignore
├── pom.xml
└── README.md
```

---

## API REST

El microservicio expone las siguientes operaciones:

| Método | Endpoint | Descripción |
|---|---|---|
| POST | `/api/clientes` | Crear cliente |
| GET | `/api/clientes` | Listar clientes |
| GET | `/api/clientes/{id}` | Buscar cliente por ID |
| PUT | `/api/clientes/{id}` | Actualizar cliente |
| DELETE | `/api/clientes/{id}` | Eliminar cliente |

### Ejemplo de creación

```json
{
  "nombre": "Juan Perez",
  "correo": "juan@example.com",
  "telefono": "55555555"
}
```

---

## Reglas de negocio y validaciones

El microservicio implementa validaciones como:

- Nombre obligatorio.
- Correo obligatorio.
- Formato válido de correo electrónico.
- Correo único por cliente.
- Longitud máxima de campos.
- Validación de existencia del cliente antes de actualizar, consultar o eliminar.
- Manejo centralizado de errores mediante `GlobalExceptionHandler`.

---

## Oracle Database

La aplicación utiliza Oracle Database como sistema de persistencia.

Configuración mediante variables de entorno:

```properties
spring.datasource.url=${DB_URL:jdbc:oracle:thin:@localhost:1521/FREE}
spring.datasource.username=${DB_USERNAME:SYSTEM}
spring.datasource.password=${DB_PASSWORD}
```

Esto permite desacoplar la configuración de la base de datos del código fuente.

En el entorno local utilizado para este proyecto, Oracle se ejecuta fuera del clúster de Kubernetes.

---

## Swagger / OpenAPI

La documentación interactiva de la API se encuentra disponible en:

```text
http://localhost:8080/swagger-ui.html
```

Swagger permite visualizar y probar los endpoints REST del microservicio.

---

## Compilación con Maven

Para compilar el proyecto:

```bash
mvn clean package
```

El artefacto generado se encuentra en:

```text
target/
```

---

## Docker

Construir la aplicación:

```bash
mvn clean package
```

Construir la imagen:

```bash
docker build -t clientes-service:latest .
```

Ejecutar el contenedor:

```bash
docker run --name clientes-container \
  -p 8080:8080 \
  -e DB_PASSWORD="PASSWORD_ORACLE" \
  clientes-service:latest
```

Cuando Oracle se ejecuta en la máquina host, el contenedor puede utilizar:

```text
jdbc:oracle:thin:@host.docker.internal:1521/FREE
```

---

## Kubernetes

Los manifiestos declarativos se encuentran en:

```text
k8s/
```

Aplicar los recursos manualmente:

```bash
kubectl apply -f k8s/
```

Consultar el Deployment:

```bash
kubectl get deployments
```

Consultar los Pods:

```bash
kubectl get pods
```

Consultar los Services:

```bash
kubectl get services
```

La aplicación utiliza un `Deployment` para administrar las réplicas del microservicio y un `Service` para exponerlo dentro del clúster.

---

## CI - GitHub Actions

El pipeline se encuentra definido en:

```text
.github/workflows/ci.yml
```

El pipeline se ejecuta automáticamente ante cambios enviados a la rama `main`.

Flujo:

```text
Git Push
   |
   v
GitHub Actions
   |
   +--> Checkout
   |
   +--> Java 17
   |
   +--> Maven Build
   |
   +--> Docker Build
```

Esto permite validar automáticamente que la aplicación pueda compilarse y que su imagen Docker pueda construirse correctamente.

> Los tests que requieren una instancia Oracle externa no se ejecutan actualmente dentro del runner de CI.

---

## Infrastructure as Code - Terraform

La definición IaC se encuentra en:

```text
infra/
```

Archivos principales:

```text
main.tf
variables.tf
outputs.tf
```

Inicializar Terraform:

```bash
cd infra
terraform init
```

Validar:

```bash
terraform validate
```

Visualizar los cambios:

```bash
terraform plan
```

Aplicar:

```bash
terraform apply
```

Consultar outputs:

```bash
terraform output
```

Terraform permite definir recursos de manera declarativa y mantener un estado reproducible e idempotente.

Si el estado actual ya coincide con la configuración declarada, una nueva ejecución de `terraform apply` no realiza cambios innecesarios.

---

## GitOps con Argo CD

El proyecto implementa sincronización automática entre Git y Kubernetes mediante **Argo CD**.

La aplicación de Argo CD se define en:

```text
argocd/application.yaml
```

Argo CD monitorea:

```text
Rama: main
Ruta: k8s/
```

Flujo GitOps:

```text
Developer
    |
    v
Git Push
    |
    v
GitHub Repository
    |
    v
Argo CD
    |
    v
Kubernetes
```

Los manifiestos almacenados en Git representan el **estado deseado** de la aplicación.

La política utilizada incluye:

```yaml
syncPolicy:
  automated:
    enabled: true
    prune: true
    selfHeal: true
```

Esto permite:

- **Automated Sync:** sincronizar automáticamente cambios provenientes de Git.
- **Prune:** eliminar recursos que ya no formen parte del estado deseado.
- **Self Heal:** corregir diferencias entre Kubernetes y lo declarado en Git.

El estado puede verificarse mediante:

```bash
kubectl get applications -n argocd
```

Un estado correcto se muestra como:

```text
NAME               SYNC STATUS   HEALTH STATUS
clientes-service   Synced        Healthy
```

Por lo tanto, los cambios de infraestructura de Kubernetes pueden gestionarse desde Git sin necesidad de ejecutar manualmente `kubectl apply` después de cada cambio.

---

## Flujo completo de entrega

```text
Developer
    |
    v
Git
    |
    v
GitHub
    |
    +--------------------+
    |                    |
    v                    v
GitHub Actions         Argo CD
    |                    |
    v                    v
Maven Build          Kubernetes
    |
    v
Docker Build
```

El proyecto separa así:

- **CI:** GitHub Actions.
- **Contenerización:** Docker.
- **Orquestación:** Kubernetes.
- **IaC:** Terraform.
- **GitOps:** Argo CD.
- **Persistencia:** Oracle Database.

---

## Consideraciones de seguridad

Las credenciales sensibles no deben almacenarse directamente en el repositorio.

Para ambientes productivos se recomienda utilizar:

- Kubernetes Secrets.
- GitHub Secrets.
- Variables de entorno.
- Gestores externos de secretos.

El archivo `.env` y los estados locales de Terraform que puedan contener información sensible deben excluirse del control de versiones.

---

## Autor

**Tiffany Contreras**