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