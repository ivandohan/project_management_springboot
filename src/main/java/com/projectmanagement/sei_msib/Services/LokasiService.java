package com.projectmanagement.sei_msib.Services;

import com.projectmanagement.sei_msib.Entities.Lokasi;
import com.projectmanagement.sei_msib.Repositories.LokasiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LokasiService {
    @Autowired
    private LokasiRepository lokasiRepository;

    public Lokasi saveLokasi(Lokasi lokasi) {
        return lokasiRepository.save(lokasi);
    }

    public List<Lokasi> getAllLokasi() {
        return lokasiRepository.findAll();
    }

    public Optional<Lokasi> getLokasiById(Long id) {
        return lokasiRepository.findById(id);
    }

    public Optional<Lokasi> updateLokasiById(Long id, Lokasi lokasiRequest) {
        Optional<Lokasi> lokasiOptional = this.getLokasiById(id);
        if (lokasiOptional.isPresent()) {
            Lokasi lokasi = lokasiOptional.get();
            lokasi.setNamaLokasi(lokasiRequest.getNamaLokasi());
            lokasi.setNegara(lokasiRequest.getNegara());
            lokasi.setProvinsi(lokasiRequest.getProvinsi());
            lokasi.setKota(lokasiRequest.getKota());

            return Optional.of(lokasiRepository.save(lokasi));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public boolean deleteLokasiById(Long id) {
        Optional<Lokasi> lokasi = this.getLokasiById(id);
        if (lokasi.isPresent()) {
            lokasiRepository.delete(lokasi.get());
            return true;
        }

        return false;
    }
}
