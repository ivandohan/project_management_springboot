package com.projectmanagement.sei_msib.Services;

import com.projectmanagement.sei_msib.DTOs.ProyekRequestDTO;
import com.projectmanagement.sei_msib.Entities.Lokasi;
import com.projectmanagement.sei_msib.Entities.Proyek;
import com.projectmanagement.sei_msib.Entities.ProyekLokasi;
import com.projectmanagement.sei_msib.Repositories.LokasiRepository;
import com.projectmanagement.sei_msib.Repositories.ProyekLokasiRepository;
import com.projectmanagement.sei_msib.Repositories.ProyekRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyekService {

    @Autowired
    private ProyekRepository proyekRepository;

    @Autowired
    private LokasiRepository lokasiRepository;

    @Autowired
    private ProyekLokasiRepository proyekLokasiRepository;

    public Proyek saveProyek(Proyek proyek) {
        return proyekRepository.save(proyek);
    }

    public List<Proyek> getAllProyek() {
        return proyekRepository.findAll();
    }

    public Optional<Proyek> getProyekById(Long id) {
        return proyekRepository.findById(id);
    }

    public List<ProyekLokasi> getLokasiByProyekId(Long id) {
        return proyekLokasiRepository.findByProyekId(id);
    }

    public Proyek createProyekWithAvailableLokasi(ProyekRequestDTO proyekRequestDTO) {
        Proyek proyek = proyekRepository.save(proyekRequestDTO.getProyek());

        for(long idLokasi : proyekRequestDTO.getIdLokasiList()) {
            Optional<Lokasi> lokasi = lokasiRepository.findById(idLokasi);
            if (lokasi.isPresent()) {
                Lokasi newLokasi = lokasiRepository.save(lokasi.get());

                ProyekLokasi proyekLokasi = new ProyekLokasi();
                proyekLokasi.setProyek(proyek);
                proyekLokasi.setLokasi(newLokasi);
                proyekLokasiRepository.save(proyekLokasi);
            }
        }

        return proyek;
    }

    public Optional<Proyek> updateProyek(Long id, Proyek proyekRequest)  {
        Optional<Proyek> proyekOptional = proyekRepository.findById(id);
        if (proyekOptional.isPresent()) {
            Proyek proyek = proyekOptional.get();
            proyek.setNamaProyek(proyekRequest.getNamaProyek());
            proyek.setPimpinanProyek(proyekRequest.getPimpinanProyek());
            proyek.setClient(proyekRequest.getClient());
            proyek.setTglMulai(proyekRequest.getTglMulai());
            proyek.setTglSelesai(proyekRequest.getTglSelesai());
            proyek.setKeterangan(proyekRequest.getKeterangan());

            return Optional.of(proyekRepository.save(proyek));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public boolean deleteProyek(Long id) {
        Optional<Proyek> proyekOptional = proyekRepository.findById(id);
        if (proyekOptional.isPresent()) {
            proyekLokasiRepository.deleteByProyekId(id);
            proyekRepository.deleteById(id);
            return true;
        }

        return false;
    }
}