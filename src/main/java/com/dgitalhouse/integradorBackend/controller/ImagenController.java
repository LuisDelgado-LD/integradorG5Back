package com.dgitalhouse.integradorBackend.controller;

import com.dgitalhouse.integradorBackend.entity.Imagen;
import com.dgitalhouse.integradorBackend.service.IImagenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/imagenes")
public class ImagenController {
    private IImagenService imagenService;

    public ImagenController(IImagenService imagenService) {
        this.imagenService = imagenService;
    }

    // 🟢 Subir una imagen a Cloudinary y guardarla en la BD
    @PostMapping("/{id}")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("imagen") MultipartFile file, @PathVariable Long id) {
        try {
            Imagen imagen = imagenService.uploadImage(id, file);
            return ResponseEntity.ok(Map.of("url", imagen.getUrl()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("Error al subir la imagen", e.getMessage() ));
        }
    }

    // 🟢 Obtener una imagen por ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, String>> getImageById(@PathVariable Long id) {
        Imagen imagen = imagenService.findById(id);
        if (imagen != null) {
            return ResponseEntity.ok(Map.of("url", imagen.getUrl()));
        } else {
            return ResponseEntity.status(404).body(Map.of("error", "Imagen no encontrada"));
        }
    }

    // 🟢 Obtener todas las imágenes
    @GetMapping("/todas")
    public ResponseEntity<List<Map<String, String>>> getAllImages() {
        List<Map<String, String>> images = imagenService.findAll()
                .stream()
                .map(imagen -> Map.of("url", imagen.getUrl()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(images);
    }


    }
