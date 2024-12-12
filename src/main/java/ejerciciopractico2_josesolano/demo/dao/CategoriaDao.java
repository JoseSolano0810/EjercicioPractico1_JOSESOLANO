/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ejerciciopractico2_josesolano.demo.dao;

import ejerciciopractico2_josesolano.demo.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author adrianchavarriamora
 */
public interface CategoriaDao extends JpaRepository<Categoria,Long>{
    
}
