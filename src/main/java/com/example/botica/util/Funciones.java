package com.example.botica.util;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;

public class Funciones {

    /**
     * Convierte una cadena a Long, eliminando cualquier carácter no numérico.
     * Útil para limpiar valores que traen espacios o caracteres especiales.
     */
    public static Long parseLong(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0L;
        }
        try {
            // El regex [^0-9] elimina todo lo que no sea dígito
            return Long.parseLong(value.replaceAll("[^0-9]", ""));
        } catch (Exception e) {
            return 0L;
        }
    }

    /**
     * Convierte una cadena a BigDecimal, normalizando comas por puntos.
     * Útil para precios que vienen con formato de moneda.
     */
    public static BigDecimal parseBigDecimal(String value) {
        if (value == null || value.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }
        try {
            // Reemplazamos coma por punto para asegurar formato decimal estándar
            return new BigDecimal(value.replace(",", "."));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public static Date parseFecha(Cell cell) {
        if (cell == null)
            return null;
        try {
            return cell.getDateCellValue();
        } catch (Exception e) {
            return null; // Si no es fecha, retorna nulo (vacio)
        }
    }

}
