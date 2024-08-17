package com.projectmanagement.sei_msib.Controllers;

import com.projectmanagement.sei_msib.Entities.Lokasi;
import com.projectmanagement.sei_msib.Services.LokasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lokasi")
public class LokasiController {
    @Autowired
    private LokasiService lokasiService;

    @PostMapping
    public Lokasi createLokasi(@RequestBody Lokasi lokasi) {
        return lokasiService.saveLokasi(lokasi);
    }

    @GetMapping
    public List<Lokasi> getAllLokasi() {
        return lokasiService.getAllLokasi();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lokasi> updateLokasi(@PathVariable Long id, @RequestBody Lokasi lokasiRequest) {
        Optional<Lokasi> lokasiTerbaru = lokasiService.updateLokasiById(id, lokasiRequest);

        if(lokasiTerbaru.isPresent()) {
            return ResponseEntity.ok(lokasiTerbaru.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Lokasi> deleteLokasi(@PathVariable Long id) {
        boolean isDeleted = lokasiService.deleteLokasiById(id);
        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
