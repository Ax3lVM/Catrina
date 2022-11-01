/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.catrina.enumeradores;

import com.google.gson.annotations.SerializedName;

/**
 * Interpreta el valor del tipo a un enumerador
 * @author axelt
 */
public enum Tipo {
    @SerializedName("1")
    DEPOSITO,
    @SerializedName("2")
    RETIRO,
    
}