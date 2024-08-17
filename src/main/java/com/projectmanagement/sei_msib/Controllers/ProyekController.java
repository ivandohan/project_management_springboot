package com.projectmanagement.sei_msib.Controllers;

import com.projectmanagement.sei_msib.DTOs.ProyekRequestDTO;
import com.projectmanagement.sei_msib.DTOs.ProyekResponseDTO;
import com.projectmanagement.sei_msib.Entities.Lokasi;
import com.projectmanagement.sei_msib.Entities.Proyek;
import com.projectmanagement.sei_msib.Entities.ProyekLokasi;
import com.projectmanagement.sei_msib.Services.ProyekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/proyek")
public class ProyekController {
    @Autowired
    private ProyekService proyekService;

    @GetMapping
    public List<Proyek> getAllProyek() {
        return proyekService.getAllProyek();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProyekDetail(@PathVariable Long id) {
        Optional<Proyek> proyekOptional = proyekService.getProyekById(id);

        if(proyekOptional.isPresent()) {
            Proyek proyek = proyekOptional.get();

            List<ProyekLokasi> proyekLokasiList = proyekService.getLokasiByProyekId(id);
            List<Lokasi> lokasiList = proyekLokasiList.stream()
                    .map(ProyekLokasi::getLokasi)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new ProyekResponseDTO(proyek, lokasiList));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Proyek> createProyek(@RequestBody ProyekRequestDTO proyekRequestDTO) {
        if(!proyekRequestDTO.getIdLokasiList().isEmpty()) {
            Proyek proyek = proyekService.createProyekWithAvailableLokasi(proyekRequestDTO);
            return ResponseEntity.ok().body(proyek);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProyek(@PathVariable Long id, @RequestBody Proyek proyekRequest) {
        Optional<Proyek> updatedProyek = proyekService.updateProyek(id, proyekRequest);
        if(updatedProyek.isPresent()) {
            return ResponseEntity.ok().body(updatedProyek.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProyek(@PathVariable Long id) {
        if(proyekService.deleteProyek(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
