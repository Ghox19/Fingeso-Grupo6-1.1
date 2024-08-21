package com.example.BackendHabitatDigital.controllers;

import com.example.BackendHabitatDigital.entities.CorredorEntity;
import com.example.BackendHabitatDigital.entities.InmuebleEntity;
import com.example.BackendHabitatDigital.services.CorredorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
    Descripcion: Esta clase es un controlador REST que maneja las solicitudes HTTP
    relacionadas con los corredores (agentes) y sus propiedades. Define los
    endpoints para obtener, crear y explorar corredores e inmuebles asociados.
    Utiliza `CorredorService` para interactuar con la capa de servicios.
 */
@RestController
@RequestMapping("/corredor")
public class CorredorController {

    private final CorredorService corredorService;

    /*
        Descripcion: Constructor de la clase `CorredorController` que inyecta la
        dependencia `CorredorService` para manejar la lógica de negocio relacionada
        con los corredores.
     */
    @Autowired
    public CorredorController(CorredorService corredorService) {
        this.corredorService = corredorService;
    }

     /*
        Descripcion: Este método maneja las solicitudes GET para obtener la información
        de un corredor específico por su ID. Si se encuentra, devuelve la entidad
        del corredor; de lo contrario, devuelve una respuesta 404 (Not Found).
      */
    @GetMapping("/{id}")
    public ResponseEntity<CorredorEntity> getCorredor(@PathVariable Long id) {
        Optional<CorredorEntity> corredor = corredorService.getCorredorById(id);
        return corredor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*
        Descripcion: Este método maneja las solicitudes GET para obtener una lista
        de todos los corredores registrados en el sistema.
     */
    @GetMapping
    public List<CorredorEntity> getAllCorredores() {
        return this.corredorService.getAllCorredores();
    }

    /*
        Descripcion: Este método maneja las solicitudes POST para crear un nuevo
        corredor en el sistema utilizando un correo electrónico. La creación del
        corredor es gestionada por el servicio `CorredorService`.
     */
    @PostMapping
    public ResponseEntity<Object> createCorredor(@RequestBody String email) {
        return this.corredorService.addCorredor(email);
    }

    /*
        Descripcion: Este método maneja las solicitudes GET para obtener las propiedades
        asociadas al corredor autenticado. Devuelve una lista de inmuebles que
        pertenecen al corredor autenticado.
    */
    @GetMapping("/propiedades")
    public ResponseEntity<List<InmuebleEntity>> findInmueblesByAuthenticatedCorredor() {
        List<InmuebleEntity> inmuebles = corredorService.getInmueblesByAuthenticatedCorredor();
        return ResponseEntity.ok(inmuebles);
    }

    /*
        Descripcion: Este método maneja las solicitudes GET para explorar los inmuebles
        que no están asociados con ningún corredor y son visibles para el corredor
        autenticado. Devuelve una lista de estos inmuebles.
     */
    @GetMapping("/explorar")
    public ResponseEntity<List<InmuebleEntity>> findInmueblesWithoutCorredor() {
        List<InmuebleEntity> inmuebles = corredorService.getInmueblesWithoutCorredorForAuthenticatedCorredor();
        return ResponseEntity.ok(inmuebles);
    }

}
