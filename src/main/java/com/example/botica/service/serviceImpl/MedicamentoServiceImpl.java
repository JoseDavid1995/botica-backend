package com.example.botica.service.serviceImpl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.botica.entity.Medicamento;
import com.example.botica.repository.MedicamentoRepository;
import com.example.botica.service.MedicamentoService;
import com.example.botica.util.Funciones;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicamentoServiceImpl implements MedicamentoService {

    private final MedicamentoRepository repository;

    @Transactional
    public void procesarExcel(MultipartFile file) throws Exception {
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter formatter = new DataFormatter();
        // Creamos el evaluador para manejar las celdas con fórmulas
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

        // FILA 20 en Excel = Índice 19 en base 0
        for (int i = 19; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null)
                continue;

            // Columna B (índice 1): CODIGO
            String codigo = obtenerValorCelda(row.getCell(1), formatter, evaluator).trim();
            if (codigo.contains("DEPOSITO")) {
                break; // Aquí sí detenemos el bucle porque llegamos al final
            }
            if (codigo.isEmpty())
                continue;

            // Columna C (índice 2): DESCRIPCION
            String descripcion = obtenerValorCelda(row.getCell(2), formatter, evaluator).trim();

            Medicamento med = repository.findByCodigoMedicamento(codigo)
                    .orElse(new Medicamento());

            med.setCodigoMedicamento(codigo);
            med.setDescripcionMedicamento(descripcion);

            // Mapeo de columnas usando el método unificado
            med.setPrecioOperacion(Funciones.parseBigDecimal(obtenerValorCelda(row.getCell(3), formatter, evaluator)));
            med.setSaldoAnterior(Funciones.parseLong(obtenerValorCelda(row.getCell(4), formatter, evaluator)));
            med.setIngresos(Funciones.parseLong(obtenerValorCelda(row.getCell(5), formatter, evaluator)));

            // Consumos
            med.setVentasUnidades(Funciones.parseLong(obtenerValorCelda(row.getCell(6), formatter, evaluator)));
            med.setVentasValorizado(Funciones.parseBigDecimal(obtenerValorCelda(row.getCell(7), formatter, evaluator)));
            med.setSisUnidades(Funciones.parseLong(obtenerValorCelda(row.getCell(8), formatter, evaluator)));
            med.setSisValorizado(Funciones.parseBigDecimal(obtenerValorCelda(row.getCell(9), formatter, evaluator)));
            med.setIntervencionSanitariaUnidades(
                    Funciones.parseLong(obtenerValorCelda(row.getCell(10), formatter, evaluator)));
            med.setIntervencionSanitariaValorizado(
                    Funciones.parseBigDecimal(obtenerValorCelda(row.getCell(11), formatter, evaluator)));

            // Stock y Fecha
            med.setStock(Funciones.parseLong(obtenerValorCelda(row.getCell(26), formatter, evaluator)));
            // Para la fecha, si no es fórmula, podemos pasar el objeto cell directamente
            med.setFechaExpiracionMedicamento(Funciones.parseFecha(row.getCell(27)));

            repository.save(med);
        }
        workbook.close();
    }

    /**
     * Método auxiliar para obtener el valor real de una celda,
     * evaluando fórmulas si existen.
     */
    private String obtenerValorCelda(Cell cell, DataFormatter formatter, FormulaEvaluator evaluator) {
        if (cell == null)
            return "";

        // Si es fórmula, la evaluamos primero
        if (cell.getCellType() == CellType.FORMULA) {
            return formatter.formatCellValue(cell, evaluator);
        }
        // Si no, devolvemos el valor directamente
        return formatter.formatCellValue(cell);
    }

}
