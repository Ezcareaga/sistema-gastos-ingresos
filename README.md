# Sistema de Control de Gastos e Ingresos

Sistema contable para registro y categorización de transacciones financieras desarrollado con JHipster.

## Tecnologías

- **Backend:** Java 21, Spring Boot, Maven, PostgreSQL
- **Frontend:** Angular, Bootstrap
- **Herramientas:** JHipster 8.11.0, Docker Desktop, WSL2

## Descripción

Aplicación fullstack para gestión contable que incluye:

- Registro de transacciones (ingresos/gastos)
- Categorización de movimientos
- Gestión de cuentas bancarias
- Administración de contactos/terceros
- Dashboard con resumen financiero en tiempo real

## Entidades del Sistema (JDL)

- **Transaction:** Transacciones con monto, tipo, fecha y descripción
- **Category:** Categorías de ingresos y gastos
- **BankAccount:** Cuentas bancarias con saldo
- **Contact:** Contactos/terceros asociados a transacciones

## Instalaciones Realizadas

### Backend

- Java 21 (via SDKMAN)
- Maven 3.9.11

### Frontend

- Node.js LTS (via NVM)
- Angular CLI

### Contenedores

- Docker Desktop + WSL2
- PostgreSQL en contenedor

**Ventajas:**

- **SDKMAN:** Gestión de múltiples versiones de JDK y herramientas JVM
- **NVM:** Gestión de múltiples versiones de Node.js por proyecto
- **Docker:** Entornos reproducibles e independientes del sistema operativo

## Backend Personalizado - Proceso de Transacciones Rápidas

### Componentes Implementados

**DTOs:**

- `TransaccionRapidaRequestDTO` - Request con validaciones (@NotNull, @Positive)
- `TransaccionRapidaResponseDTO` - Response con datos y mensaje
- `ResumenFinancieroDTO` - Totales de ingresos, gastos y balance

**Service:**

- `TransaccionRapidaService` - Lógica de negocio para registro y cálculo de resumen

**Controller:**

- `TransaccionRapidaResource` - API REST endpoints

### Endpoints

#### POST /api/transacciones-rapidas

Registra nueva transacción contable.

**Request:**

```json
{
  "tipo": "INCOME",
  "monto": 5000,
  "categoria": "Ventas",
  "cuenta": "Caja",
  "descripcion": "Venta de producto",
  "fecha": "2024-12-18"
}
```

**Response 200:**

```json
{
  "id": 1,
  "tipo": "INCOME",
  "monto": 5000,
  "categoria": "Ventas",
  "cuenta": "Caja",
  "descripcion": "Venta de producto",
  "fecha": "2024-12-18",
  "mensaje": "Transacción registrada exitosamente"
}
```

**Response 400 (Validación):**

```json
{
  "type": "constraint-violation",
  "title": "Method argument not valid",
  "status": 400,
  "detail": "El monto debe ser positivo",
  "fieldErrors": [
    {
      "field": "monto",
      "message": "must not be null"
    }
  ]
}
```

#### GET /api/transacciones-rapidas/resumen

Obtiene resumen financiero consolidado.

**Response 200:**

```json
{
  "totalIngresos": 5000.0,
  "totalGastos": 1200.0,
  "balance": 3800.0
}
```

## Frontend Angular - Integración

### Service: TransaccionRapidaService

Servicio Angular con HttpClient que consume los endpoints del backend.

**Ubicación:** `src/main/webapp/app/entities/transaccion-rapida.service.ts`

**Métodos:**

- `registrar(request)` - POST a /api/transacciones-rapidas
- `obtenerResumen()` - GET a /api/transacciones-rapidas/resumen

### Componente: Dashboard Financiero (proceso-principal)

**Ruta:** `/proceso-principal`

**Funcionalidades:**

- Muestra resumen financiero en tiempo real (3 cards: Ingresos, Gastos, Balance)
- Formulario de registro rápido de transacciones
- Validación de campos requeridos
- Mensajes de éxito/error
- Actualización automática del resumen tras registro

**Ubicación:** `src/main/webapp/app/proceso-principal/`

## Flujo Completo de Integración Frontend ↔ Backend

1. **Carga inicial:**

   - Usuario accede a `/proceso-principal`
   - `ngOnInit()` ejecuta `cargarResumen()`
   - Service Angular llama GET `/api/transacciones-rapidas/resumen`
   - Backend consulta BD y calcula totales
   - Dashboard muestra valores reales en cards

2. **Registro de transacción:**

   - Usuario completa formulario (tipo, monto, categoría, cuenta, fecha, descripción)
   - Click en "Registrar Transacción"
   - `registrarTransaccion()` captura datos del formulario
   - Service envía POST con `TransaccionRapidaRequestDTO`
   - Backend valida datos con Bean Validation
   - Backend busca Category y BankAccount en BD
   - Backend crea y guarda Transaction
   - Backend retorna `TransaccionRapidaResponseDTO`

3. **Respuesta exitosa:**

   - Angular muestra mensaje verde de éxito
   - Llama automáticamente `cargarResumen()` para actualizar totales
   - Limpia formulario para nueva transacción

4. **Manejo de errores:**
   - Si faltan campos requeridos → Backend retorna 400 con detalles
   - Si categoría/cuenta no existe → Backend retorna 500
   - Angular captura error en `subscribe({ error })`
   - Muestra mensaje rojo con descripción del error

## Componentes Adicionales

### Volver Inicio

- **Ruta:** `/volver`
- **Funcionalidad:** Componente de prueba con botón de navegación al home
- **Ubicación:** `src/main/webapp/app/volver-inicio/`

## Ejecución del Proyecto

### Requisitos previos

```bash
# Instalar herramientas base
sdk install java 21.0.1-tem
sdk install maven
nvm install --lts
npm install -g @angular/cli
```

### Levantar aplicación

```bash
# 1. Clonar repositorio
git clone https://github.com/TU_USUARIO/sistema-gastos-ingresos.git
cd sistema-gastos-ingresos

# 2. Levantar PostgreSQL (Docker)
docker-compose -f src/main/docker/postgresql.yml up -d

# 3. Terminal 1 - Backend
./mvnw

# 4. Terminal 2 - Frontend (cuando backend esté listo)
npm start

# 5. Acceder a la aplicación
# Frontend: http://localhost:9000
# Swagger: http://localhost:8080/admin/docs
# Login: admin / admin
```

### Primeros pasos

1. Login con `admin / admin`
2. Crear al menos 1 Category (Entities → Category)
3. Crear al menos 1 Bank Account (Entities → Bank Account)
4. Ir a Dashboard (`/proceso-principal`)
5. Registrar transacciones

### Pruebas con Swagger

1. Acceder: http://localhost:8080/admin/docs
2. Buscar `transaccion-rapida-resource`
3. Probar POST `/api/transacciones-rapidas` con JSON de ejemplo
4. Probar GET `/api/transacciones-rapidas/resumen`

## Evidencias

Carpeta `/evidencias` contiene capturas de:

- Instalaciones (Java, Maven, Node, Angular, Docker)
- Docker hello-world
- Entidades JHipster funcionando
- Swagger endpoints (request/response)
- Dashboard Angular integrado
- Formulario registrando transacciones
- Mensajes de éxito/error

## Estructura del Proyecto

```
sistema-gastos-ingresos/
├── src/main/java/com/contable/
│   ├── domain/              # Entidades JPA
│   ├── repository/          # Repositorios Spring Data
│   ├── service/             # TransaccionRapidaService
│   └── web/rest/
│       ├── dto/             # DTOs personalizados
│       └── TransaccionRapidaResource.java
├── src/main/webapp/app/
│   ├── entities/
│   │   └── transaccion-rapida.service.ts
│   ├── proceso-principal/   # Dashboard integrado
│   └── volver-inicio/
├── evidencias/              # Capturas del proyecto
└── modelo.jdl              # Definición de entidades
```

## Autor

Alberto Careaga - Proyecto académico de desarrollo fullstack con JHipster
