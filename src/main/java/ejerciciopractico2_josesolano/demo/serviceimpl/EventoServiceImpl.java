/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciopractico2_josesolano.demo.serviceimpl;

import ejerciciopractico2_josesolano.demo.dao.EventoDao;
import ejerciciopractico2_josesolano.demo.domain.Evento;
import ejerciciopractico2_josesolano.demo.service.EventoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoDao eventoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Evento> geteventos(boolean activos) {
        var lista = eventoDao.findAll();
        if (activos) {
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Evento getProducto(Evento producto) {
        return eventoDao.findById(producto.getIdEvento()).orElse(null);

    }

    @Override
    @Transactional
    public void save(Evento evento) {
        eventoDao.save(evento);
    }

    @Override
    @Transactional
    public void delete(Evento evento) {
        eventoDao.delete(evento);
    }

    // Lista de productos con precio entre ordenados por descripción ConsultaAmpliada
    @Override
    @Transactional(readOnly = true)
    public List<Evento> findByPrecioBetweenOrderByDescripcion(String tipoInf, String tipoSup) {
        return eventoDao.findByTipoBetweenOrderByDescripcion(tipoInf, tipoSup);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Evento> metodoJPQL(String eventoInf, String eventoSup) {
        return eventoDao.metodoJPQL(eventoInf, eventoSup);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Evento> metodoNativo(String tipoInf, String tipoSup) {
        return eventoDao.metodoNativo(tipoInf, tipoSup);
    }
}
