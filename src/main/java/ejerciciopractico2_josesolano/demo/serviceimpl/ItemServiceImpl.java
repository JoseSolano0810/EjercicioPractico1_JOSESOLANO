/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciopractico2_josesolano.demo.serviceimpl;


import ejerciciopractico2_josesolano.demo.domain.Evento;
import ejerciciopractico2_josesolano.demo.domain.Usuario;
import ejerciciopractico2_josesolano.demo.domain.Item;
import ejerciciopractico2_josesolano.demo.service.UsuarioService;
import ejerciciopractico2_josesolano.demo.service.ItemService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ejerciciopractico2_josesolano.demo.dao.EventoDao;
import ejerciciopractico2_josesolano.demo.dao.FacturaDao;
import ejerciciopractico2_josesolano.demo.dao.VentaDao;
import ejerciciopractico2_josesolano.demo.domain.Factura;
import ejerciciopractico2_josesolano.demo.domain.Venta;

@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public List<Item> gets() {
        return listaItems;
    }

    //Se usa en el addCarrito... agrega un elemento
    @Override
    public void save(Item item) {
        boolean existe = false;
        for (Item i : listaItems) {
            //Busca si ya existe el evento en el carrito
            if (Objects.equals(i.getIdEvento(), item.getIdEvento())) {
                //Valida si aún puede colocar un item adicional -segun existencias-
                if (i.getCapacidad() < item.getCapacidad()) {
                    //Incrementa en 1 la cantidad de elementos
                    i.setCapacidad(i.getCapacidad() + 1);
                }
                existe = true;
                break;
            }
        }
        if (!existe) {//Si no está el evento en el carrito se agrega cantidad =1.            
            item.setCapacidad(1);
            listaItems.add(item);
        }
    }

    //Se usa para eliminar un evento del carrito
    @Override
    public void delete(Item item) {
        var posicion = -1;
        var existe = false;
        for (Item i : listaItems) {
            ++posicion;
            if (Objects.equals(i.getIdEvento(), item.getIdEvento())) {
                existe = true;
                break;
            }
        }
        if (existe) {
            listaItems.remove(posicion);
        }
    }

    //Se obtiene la información de un producto del carrito... para modificarlo
    @Override
    public Item get(Item item) {
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdEvento(), item.getIdEvento())) {
                return i;
            }
        }
        return null;
    }

    //Se usa en la página para actualizar la cantidad de eventos
    @Override
    public void actualiza(Item item) {
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdEvento(), item.getIdEvento())) {
                i.setCapacidad(item.getCapacidad());
                break;
            }
        }
    }

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FacturaDao facturaDao;
    @Autowired
    private VentaDao ventaDao;
    @Autowired
    private EventoDao eventoDao;

    @Override
    public void facturar() {
        System.out.println("Facturando");

        //Se obtiene el usuario autenticado
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }

        if (username.isBlank()) {
            return;
        }

        Usuario usuario = usuarioService.getUsuarioPorUsername(username);

        if (usuario == null) {
            return;
        }

        Factura factura = new Factura(usuario.getIdUsuario());
        factura = facturaDao.save(factura);

        double total = 0;
        for (Item i : listaItems) {
            System.out.println("Evento: " + i.getDescripcion()
                    + " Capacidad: " + i.getCapacidad()
                    + " Total: " + i.getTipo() * i.getCapacidad());
            Venta venta = new Venta(factura.getIdFactura(), i.getIdEvento(), i.getTipo(), i.getCantidad());
            ventaDao.save(venta);
            Evento evento = eventoDao.getReferenceById(i.getIdEvento());
            evento.setCapacidad(evento.getCapacidad()-i.getCapacidad());
            eventoDao.save(evento);
            total += i.getTipo() * i.getCapacidad();
        }
        
        factura.setTotal(total);
        facturaDao.save(factura);
        listaItems.clear();
    }
}