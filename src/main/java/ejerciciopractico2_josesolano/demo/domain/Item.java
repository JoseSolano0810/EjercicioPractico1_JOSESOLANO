/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciopractico2_josesolano.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Item extends Evento {
    private int cantidad; //Almacenar la cantidad de items de un evento

    public Item() {
    }

    public Item(Evento evento) {
        super.setIdEvento(evento.getIdEvento());
        super.setCategoria(evento.getCategoria());
        super.setDescripcion(evento.getDescripcion());
        super.setTipo(evento.getTipo());
        super.setCapacidad(evento.getCapacidad());
        super.setFecha_inicio(evento.getFecha_inicio());
        super.setFecha_fin(evento.getFecha_fin());
        super.setActivo(evento.isActivo());
        super.setRutaImagen(evento.getRutaImagen());
        this.cantidad = 0;
    }
}