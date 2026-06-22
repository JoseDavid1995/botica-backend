package com.example.botica.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.botica.response.ApiResponse;
import com.example.botica.service.MedicamentoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/medicamentos")
@RequiredArgsConstructor
public class MedicamentoController {

    private final MedicamentoService service;

    @PostMapping("/subir-medicamentos")
    public ResponseEntity<ApiResponse<?>> subirExcel(@RequestParam("file") MultipartFile file) {
        try {
            service.procesarExcel(file);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(ApiResponse.ok("Archivo procesado exitosamente", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error al procesar el archivo: " + e.getMessage()));
        }
    }
}