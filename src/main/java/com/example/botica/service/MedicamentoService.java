package com.example.botica.service;

import org.springframework.web.multipart.MultipartFile;

public interface MedicamentoService {
    void procesarExcel(MultipartFile file) throws Exception;

}
