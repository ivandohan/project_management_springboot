package com.projectmanagement.sei_msib.Controllers;

import com.projectmanagement.sei_msib.Entities.Proyek;
import com.projectmanagement.sei_msib.Services.ProyekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyek")
public class ProyekController {
    @Autowired
    private ProyekService proyekService;

    @PostMapping
    public Proyek createProyek(@RequestBody Proyek proyek) {
        return proyekService.saveProyek(proyek);
    }

    @GetMapping
    public List<Proyek> getAllProyek() {
        return proyekService.getAllProyek();
    }

}
