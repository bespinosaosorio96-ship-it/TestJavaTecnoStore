package controlador;

import servicio.GestorCelulares;
import servicio.GestorVentas;
import servicio.GestorCreditos_test;

import test.ReporteGlobalTXT;

import util.ReporteUtils;
import util.ArchivoUtils;

public class ReporteController {

    private final GestorCelulares gestorCelulares;
    private final GestorVentas gestorVentas;
    private final GestorCreditos_test gestorCreditos;

    private final ReporteGlobalTXT reporteGlobalTXT;

    public ReporteController(
            GestorCelulares gestorCelulares,
            GestorVentas gestorVentas,
            GestorCreditos_test gestorCreditos) {

        this.gestorCelulares = gestorCelulares;
        this.gestorVentas = gestorVentas;
        this.gestorCreditos = gestorCreditos;

        this.reporteGlobalTXT =
                new ReporteGlobalTXT(
                        gestorVentas,
                        gestorCelulares,
                        gestorCreditos);
    }

    public void mostrarStockBajo() {
        ReporteUtils.mostrarStockBajo(
                gestorCelulares.getCelulares());
    }

    public void mostrarTop3CelularesMasVendidos() {
        ReporteUtils.mostrarTop3CelularesMasVendidos(
                gestorVentas.getVentas());
    }

    public void mostrarVentasTotalesPorMes() {
        ReporteUtils.mostrarVentasTotalesPorMes(
                gestorVentas.getVentas());
    }

    public void generarReporteVentasArchivo() {
        ArchivoUtils.generarReporteVentas(
                gestorVentas.getVentas());
    }

    public void generarReporteGlobal() {
        reporteGlobalTXT.generar();
    }
    public ReporteController(
        GestorCelulares gestorCelulares,
        GestorVentas gestorVentas,
        GestorCreditos_test gestorCreditos)
}