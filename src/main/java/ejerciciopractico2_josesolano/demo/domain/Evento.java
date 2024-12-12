/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciopractico2_josesolano.demo.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "evento")
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long idevento;
    private String descripcion;
    private String tipo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_inicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_fin;
    private Long capacidad;
    @Column(name = "ruta_imagen")
    private String rutaImagen;
    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    Categoria categoria;

    public Evento() {
    }

    public Evento(String descripcion, String detalle, String tipo, Date fecha_inicio, Long capacidad, Date fecha_fin, String imagen, boolean activo) {
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.fecha_fin = fecha_fin;
        this.fecha_inicio = fecha_inicio;
        this.capacidad =capacidad;
        this.rutaImagen = imagen;
        this.activo = activo;
    }
}
