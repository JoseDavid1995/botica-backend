package com.example.botica.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "MEDICAMENTOS")
@Data
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medicamento")
    private Long id_medicamento;

    @Column(name = "codigo_medicamento", unique = true, nullable = false)
    private String codigoMedicamento;

    @Column(name = "descripcion_medicamento", nullable = false)
    private String descripcionMedicamento;

    @Column(name = "precio_operacion")
    private BigDecimal precioOperacion;

    @Column(name = "saldo_anterior")
    private Long saldoAnterior;

    @Column(name = "ingresos")
    private Long ingresos;

    // --- UNIDADES ---
    @Column(name = "ventas_unidades")
    private Long ventasUnidades;

    @Column(name = "sis_unidades")
    private Long sisUnidades;

    @Column(name = "intervencion_sanitaria_unidades")
    private Long intervencionSanitariaUnidades;

    // --- VALORIZADOS ---
    @Column(name = "ventas_valorizado")
    private BigDecimal ventasValorizado;

    @Column(name = "sis_valorizado")
    private BigDecimal sisValorizado;

    @Column(name = "intervencion_sanitaria_valorizado")
    private BigDecimal intervencionSanitariaValorizado;

    @Column(name = "stock")
    private Long stock;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_expiracion_medicamento")
    private Date fechaExpiracionMedicamento;
}