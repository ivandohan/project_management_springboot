package com.projectmanagement.sei_msib.Controllers;

import com.projectmanagement.sei_msib.DTOs.ProyekRequestDTO;
import com.projectmanagement.sei_msib.DTOs.ProyekResponseDTO;
import com.projectmanagement.sei_msib.Entities.Lokasi;
import com.projectmanagement.sei_msib.Entities.Proyek;
import com.projectmanagement.sei_msib.Entities.ProyekLokasi;
import com.projectmanagement.sei_msib.Repositories.LokasiRepository;
import com.projectmanagement.sei_msib.Repositories.ProyekLokasiRepository;
import com.projectmanagement.sei_msib.Repositories.ProyekRepository;
import com.projectmanagement.sei_msib.Services.ProyekService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private ProyekRepository proyekRepository;
    @Autowired
    private LokasiRepository lokasiRepository;
    @Autowired
    private ProyekLokasiRepository proyekLokasiRepository;

    @GetMapping
    public List<Proyek> getAllProyek() {
        return proyekService.getAllProyek();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProyekOnly(@PathVariable Long id) {
        System.out.println("LONGG : " + id);
        Optional<Proyek> proyekOptional = proyekService.getProyekById(id);
        if(proyekOptional.isPresent()) {
            Proyek proyek = proyekOptional.get();
            return ResponseEntity.ok().body(proyek);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/detail/{id}")
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
    public ResponseEntity<Proyek> createProyek(@RequestBody ProyekRequestDTO proyekRequestDto) {
        // Buat entitas proyek baru dari ProyekRequestDto
        Proyek proyek = new Proyek();
        proyek.setNamaProyek(proyekRequestDto.getNamaProyek());
        proyek.setClient(proyekRequestDto.getClient());
        proyek.setPimpinanProyek(proyekRequestDto.getPimpinanProyek());
        proyek.setKeterangan(proyekRequestDto.getKeterangan());
        proyek.setTglMulai(proyekRequestDto.getTglMulai());
        proyek.setTglSelesai(proyekRequestDto.getTglSelesai());
        // Simpan proyek baru
        Proyek savedProyek = proyekRepository.save(proyek);

        for(long idLokasi : proyekRequestDto.getIdLokasiList()) {
            Optional<Lokasi> lokasi = lokasiRepository.findById(idLokasi);
            if (lokasi.isPresent()) {
                Lokasi newLokasi = lokasiRepository.save(lokasi.get());

                ProyekLokasi proyekLokasi = new ProyekLokasi();
                proyekLokasi.setProyek(proyek);
                proyekLokasi.setLokasi(newLokasi);
                proyekLokasiRepository.save(proyekLokasi);
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProyek);
    }
//    public ResponseEntity<Proyek> createProyek(@RequestBody ProyekRequestDTO proyekRequestDTO) {
//        if(!proyekRequestDTO.getIdLokasiList().isEmpty()) {
//            Proyek proyek = proyekService.createProyekWithAvailableLokasi(proyekRequestDTO);
//            return ResponseEntity.ok().body(proyek);
//        } else {
//            return ResponseEntity.badRequest().body(null);
//        }
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProyek(@PathVariable Long id, @RequestBody Proyek proyekRequest) {
        Optional<Proyek> updatedProyek = proyekService.updateProyek(id, proyekRequest);
        if(updatedProyek.isPresent()) {
            return ResponseEntity.ok().body(updatedProyek.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<Proyek> updateProyekDetail(
            @PathVariable Long id,
            @RequestBody ProyekRequestDTO proyekRequestDto) {
        Optional<Proyek> proyekOptional = proyekRepository.findById(id);

        if (proyekOptional.isPresent()) {
            Proyek proyek = proyekOptional.get();
            proyek.setNamaProyek(proyekRequestDto.getNamaProyek());
            proyek.setClient(proyekRequestDto.getClient());
            proyek.setPimpinanProyek(proyekRequestDto.getPimpinanProyek());
            proyek.setKeterangan(proyekRequestDto.getKeterangan());
            proyek.setTglMulai(proyekRequestDto.getTglMulai());
            proyek.setTglSelesai(proyekRequestDto.getTglSelesai());

            // Hapus semua entitas ProyekLokasi yang terhubung
            proyekLokasiRepository.deleteByProyek(proyek);

            // Tambahkan entitas ProyekLokasi baru berdasarkan request
            List<Lokasi> lokasiList = lokasiRepository.findAllById(proyekRequestDto.getIdLokasiList());
            for (Lokasi lokasi : lokasiList) {
                ProyekLokasi proyekLokasi = new ProyekLokasi();
                proyekLokasi.setProyek(proyek);
                proyekLokasi.setLokasi(lokasi);
                proyekLokasiRepository.save(proyekLokasi);
            }

            proyekRepository.save(proyek);
            return ResponseEntity.ok(proyek);
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
