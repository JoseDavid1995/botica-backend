ALTER SESSION SET "_ORACLE_SCRIPT" = true;
CREATE USER botica IDENTIFIED BY 123456789;

GRANT CREATE SESSION TO botica;

GRANT CREATE TABLE TO botica;

GRANT UNLIMITED TABLESPACE TO botica;


CREATE TABLE BOTICA.USUARIO (
    id_usuario  NUMBER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    nombre      VARCHAR2(100) NOT NULL,
    apellidos   VARCHAR2(100) NOT NULL,
    correo      VARCHAR2(150) UNIQUE NOT NULL, -- El correo suele ser dato de logeo, se amplía tamaño
    contrasena  VARCHAR2(255) NOT NULL,        -- Reservado para el Hash de BCrypt/Argon2
    estado      NUMBER(1) DEFAULT 1,           -- 1: Activo, 0: Inactivo
    CONSTRAINT pk_usuario PRIMARY KEY (id_usuario)
);

CREATE TABLE BOTICA.MEDICAMENTOS (
    id_medicamento  NUMBER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    codigo_medicamento      VARCHAR2(50) UNIQUE NOT NULL,
    descripcion_medicamento VARCHAR2(255) NOT NULL,
    
    precio_operacion                NUMBER(10, 2) DEFAULT 0,
    saldo_anterior                  NUMBER(10, 0) DEFAULT 0,
    ingresos                        NUMBER(10, 0) DEFAULT 0,
    
    ventas_unidades                 NUMBER(10, 0) DEFAULT 0,
    sis_unidades                    NUMBER(10, 0) DEFAULT 0,
    intervencion_sanitaria_unidades NUMBER(10, 0) DEFAULT 0,
    
    ventas_valorizado               NUMBER(10, 2) DEFAULT 0,
    sis_valorizado                  NUMBER(10, 2) DEFAULT 0,
    intervencion_sanitaria_valorizado NUMBER(10, 2) DEFAULT 0,
    
    stock                           NUMBER(10, 0) DEFAULT 0,
    fecha_expiracion_medicamento    DATE,
    
    CONSTRAINT pk_medicamento PRIMARY KEY (id_medicamento)
);