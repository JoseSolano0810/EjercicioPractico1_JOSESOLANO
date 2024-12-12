/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ejerciciopractico2_josesolano.demo.service;

import ejerciciopractico2_josesolano.demo.domain.Evento;
import java.util.List;

public interface EventoService {

    // Se obtiene un listado de productos en un List
    public List<Evento> getEventos(boolean activos);

    // Se obtiene un Producto, a partir del id de un producto
    public Evento getProducto(Evento evento);

    // Se inserta un nuevo producto si el id del producto esta vacío
    // Se actualiza un producto si el id del producto NO esta vacío
    public void save(Evento evento);

    // Se elimina el producto que tiene el id pasado por parámetro
    public void delete(Evento evento);

    // Lista de productos con precio entre ordendados por descripción ConsultaAmpliada
    public List<Evento> findByTipoBetweenOrderByDescripcion(String eventoInf, String eventoSup);

    //Lista de productos utilizando consultas con JPQL    
    public List<Evento> metodoJPQL(String eventoInf, String eventoSup);

    //Lista de productos utilizando consultas con SQL Nativo
    public List<Evento> metodoNativo(String eventoInf, String eventoSup);

}