package com.example;

import java.security.SecureRandom;
import java.util.Base64;

public class KeyGenerator {
    public static void main(String[] args) {
        // Creamos un generador de números aleatorios criptográficamente seguro
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[64]; // 64 bytes = 512 bits
        secureRandom.nextBytes(key);
        
        // Convertimos a formato Base64 para que sea una cadena de texto segura
        String secretKey = Base64.getEncoder().encodeToString(key);
        
        System.out.println("Tu nueva JWT_SECRET_KEY es:");
        System.out.println("--------------------------------------------------");
        System.out.println(secretKey);
        System.out.println("--------------------------------------------------");
    }
}