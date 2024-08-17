package com.projectmanagement.sei_msib.DTOs;

import com.projectmanagement.sei_msib.Entities.Lokasi;
import com.projectmanagement.sei_msib.Entities.Proyek;

import java.util.List;

public class ProyekRequestDTO {
    private Proyek proyek;
    private List<Lokasi> lokasiList;
    private List<Long> idLokasiList;

    public Proyek getProyek() {
        return proyek;
    }

    public void setProyek(Proyek proyek) {
        this.proyek = proyek;
    }

    public List<Lokasi> getLokasiList() {
        return lokasiList;
    }

    public void setLokasiList(List<Lokasi> lokasiList) {
        this.lokasiList = lokasiList;
    }

    public List<Long> getIdLokasiList() {
        return idLokasiList;
    }

    public void setIdLokasiList(List<Long> idLokasiList) {
        this.idLokasiList = idLokasiList;
    }
}
