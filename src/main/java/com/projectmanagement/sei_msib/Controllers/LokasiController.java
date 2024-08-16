package com.projectmanagement.sei_msib.Controllers;

import com.projectmanagement.sei_msib.Entities.Lokasi;
import com.projectmanagement.sei_msib.Services.LokasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
