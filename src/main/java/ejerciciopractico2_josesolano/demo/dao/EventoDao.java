/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ejerciciopractico2_josesolano.demo.dao;

import ejerciciopractico2_josesolano.demo.domain.Evento;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author adrianchavarriamora
 */
public interface EventoDao extends JpaRepository<Evento, Long> {

    //Ejemplo de método utilizando Métodos de Query
    public List<Evento> findByPrecioBetweenOrderByDescripcion(String tipoInf, String tipoSup);

    //Ejemplo de método utilizando Consultas con JPQL
    @Query(value = "SELECT a FROM Evento a where a.precio BETWEEN :precioInf AND :tipoSup ORDER BY a.descripcion ASC")
    public List<Evento> metodoJPQL(@Param("tipoInfo") String tipoInf, @Param("tipoSup") String tipoSup);

    //Ejemplo de método utilizando Consultas con SQL nativo
    @Query(nativeQuery = true,
            value = "SELECT * FROM Evento where evento.tipo BETWEEN :tipoInf AND :tipoSup ORDER BY tipo.descripcion ASC")
    public List<Evento> metodoNativo(@Param("tipoInf") String precioInf, @Param("tipoSup") String tipoSup);
}
