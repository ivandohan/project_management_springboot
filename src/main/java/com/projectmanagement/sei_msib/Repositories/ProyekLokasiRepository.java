package com.projectmanagement.sei_msib.Repositories;

import com.projectmanagement.sei_msib.Entities.Proyek;
import com.projectmanagement.sei_msib.Entities.ProyekLokasi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProyekLokasiRepository extends JpaRepository<ProyekLokasi, Long> {
    List<ProyekLokasi> findByProyekId(Long proyekId);
    void deleteByProyekId(Long proyekId);
    void deleteByProyek(Proyek proyek);
    List<ProyekLokasi> findByProyek(Proyek proyek);
}