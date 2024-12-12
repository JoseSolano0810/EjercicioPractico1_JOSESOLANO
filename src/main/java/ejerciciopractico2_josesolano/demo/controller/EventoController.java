/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciopractico2_josesolano.demo.controller;

import ejerciciopractico2_josesolano.demo.domain.Evento;
import ejerciciopractico2_josesolano.demo.service.EventoService;
import ejerciciopractico2_josesolano.demo.service.CategoriaService;
import ejerciciopractico2_josesolano.demo.service.impl.FirebaseStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/Evento")
public class EventoController {

    @Autowired
    private EventoService productoService;
    
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = productoService.getEventos(false);
        model.addAttribute("productos", lista);
        
        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias",categorias);
        
        model.addAttribute("totalProductos", lista.size());
        return "/producto/listado";
    }

    @GetMapping("/nuevo")
    public String productoNuevo(Evento evento) {
        return "/producto/modifica";
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;

    @PostMapping("/guardar")
    public String productoGuardar(Evento evento,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            productoService.save(evento);
            evento.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile,
                            "evento",
                            evento.getIdEvento()));
        }
        productoService.save(evento);
        return "redirect:/evento/listado";
    }

    @GetMapping("/eliminar/{idEvento}")
    public String productoEliminar(Evento evento) {
        productoService.delete(evento);
        return "redirect:/evento/listado";
    }

    @GetMapping("/modificar/{idEvento}")
    public String productoModificar(Evento evento, Model model) {
        evento = productoService.getEvento(evento);
        model.addAttribute("producto", evento);
        
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias",categorias);
        
        return "/evento/modifica";
    }
}
