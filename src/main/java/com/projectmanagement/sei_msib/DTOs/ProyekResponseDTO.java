package com.projectmanagement.sei_msib.DTOs;

import com.projectmanagement.sei_msib.Entities.Lokasi;
import com.projectmanagement.sei_msib.Entities.Proyek;

import java.util.List;

public class ProyekResponseDTO {
    private Proyek proyek;
    private List<Lokasi> lokasiList;

    public ProyekResponseDTO(Proyek proyek, List<Lokasi> lokasiList) {
        this.proyek = proyek;
        this.lokasiList = lokasiList;
    }

    public Proyek getProyek() {
        return proyek;
    }

    public List<Lokasi> getLokasiList() {
        return lokasiList;
    }
}
