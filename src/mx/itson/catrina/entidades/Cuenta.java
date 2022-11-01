/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.catrina.entidades;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import mx.itson.catrina.enumeradores.Tipo;

/**
 *
 * @author axelt
 */
public class Cuenta {

    private String producto;
    private String cuenta;
    private String clabe;
    private String moneda;
    private Cliente cliente;
    private List<Movimiento> movimientos;

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the producto
     */
    public String getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(String producto) {
        this.producto = producto;
    }

    /**
     * @return the cuenta
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the clabe
     */
    public String getClabe() {
        return clabe;
    }

    /**
     * @param clabe the clabe to set
     */
    public void setClabe(String clabe) {
        this.clabe = clabe;
    }

    /**
     * @return the moneda
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    /**
     * @param movimientos the movimientos to set
     */
    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
/**
 * 
 * @param json deselealiza un archivo con extensión JSON
 * @return retorna un string deserializado
 */
    public Cuenta deserializar(String json) {
        Cuenta cuenta = new Cuenta();
        try {
            cuenta = new Gson().fromJson(json, Cuenta.class);
        } catch (Exception ex) {
            System.err.print("Ocurrió un error: " + ex.getMessage());
        }
        return cuenta;
    }
    /**
 * 
 * @param mes obtiene los movimientos filtrandolos por mes
 * @return movimientosFiltrados
 */
    public List<Movimiento> filtrarMovimientos(int mes) {
        List<Movimiento> movimientosFiltrados = new ArrayList();

        for (Movimiento movimientosFiltrado : movimientos) {
            if (movimientosFiltrado.getFecha().getMonth() == mes) {
                movimientosFiltrados.add(movimientosFiltrado);
            }
        }
        movimientosFiltrados.sort((m1, m2) -> m1.getFecha().compareTo(m2.getFecha()));
        return movimientosFiltrados;
    }
/**
 * 
 * @param mes obtiene los depositos filtrandolos por mes y evaluando si la cantidad es de tipo retiro
 * @return depositos
 */
    public double obtenerDepositos(int mes) {
        double depositos = 0;
        for (Movimiento movimiento : filtrarMovimientos(mes)) {
            if (movimiento.getTipo() == Tipo.DEPOSITO) {
                depositos = depositos + movimiento.getCantidad();
            }
        }
        return depositos;

    }
/**
 * @param mes  obtiene los retiros filtrandolos por mes y evaluando si la cantidad es de tipo retiro
 * @return retiros
 */
    public double obtenerRetiros(int mes) {
        double retiros = 0;
        for (Movimiento movimiento : filtrarMovimientos(mes)) {
            if (movimiento.getTipo() == Tipo.RETIRO) {
                retiros = retiros + movimiento.getCantidad();
            }
        }
        return retiros;

    }
/**
 * @param mes obtiene el saldoInicial
 * @return saldoInicial
 */
    public double consultarSaldoInicial(int mes) {
        double saldoInicial = 0;
        for (Movimiento movimiento : movimientos) {
            for (int i = 0; i < mes; i++) {
                if (movimiento.getFecha().getMonth() == i && movimiento.getTipo() == Tipo.DEPOSITO) {
                    saldoInicial = saldoInicial + movimiento.getCantidad();
                } else if (movimiento.getFecha().getMonth() == i && movimiento.getTipo() == Tipo.RETIRO) {
                    saldoInicial = saldoInicial - movimiento.getCantidad();
                }
            }
        }
        return saldoInicial;
    }
/**
 * @param mes obtiene el saldo final sumando el saldo inicial más depósitos menos el total de retiros
 * @return saldoFinal
 */
    public double consultarSaldoFinal(int mes) {
        double saldoFinal = (consultarSaldoInicial(mes) + obtenerDepositos(mes)) - obtenerRetiros(mes);

        return saldoFinal;
    }

}
