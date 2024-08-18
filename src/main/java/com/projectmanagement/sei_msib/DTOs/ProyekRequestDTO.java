package com.projectmanagement.sei_msib.DTOs;

import com.projectmanagement.sei_msib.Entities.Lokasi;
import com.projectmanagement.sei_msib.Entities.Proyek;

import java.sql.Timestamp;
import java.util.List;

public class ProyekRequestDTO {
    private String namaProyek;
    private String client;
    private String pimpinanProyek;
    private String keterangan;
    private Timestamp tglMulai;
    private Timestamp tglSelesai;
    private List<Long> idLokasiList;

    public String getNamaProyek() {
        return namaProyek;
    }

    public void setNamaProyek(String namaProyek) {
        this.namaProyek = namaProyek;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getPimpinanProyek() {
        return pimpinanProyek;
    }

    public void setPimpinanProyek(String pimpinanProyek) {
        this.pimpinanProyek = pimpinanProyek;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Timestamp getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(Timestamp tglMulai) {
        this.tglMulai = tglMulai;
    }

    public Timestamp getTglSelesai() {
        return tglSelesai;
    }

    public void setTglSelesai(Timestamp tglSelesai) {
        this.tglSelesai = tglSelesai;
    }

    public List<Long> getIdLokasiList() {
        return idLokasiList;
    }

    public void setIdLokasiList(List<Long> idLokasiList) {
        this.idLokasiList = idLokasiList;
    }
}
