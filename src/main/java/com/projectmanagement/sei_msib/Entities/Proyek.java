package com.projectmanagement.sei_msib.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
public class Proyek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaProyek;
    private String client;
    private String pimpinanProyek;
    private String keterangan;
    private Timestamp tglMulai;
    private Timestamp tglSelesai;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "proyek", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ProyekLokasi> proyekLokasiList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public List<ProyekLokasi> getProyekLokasiList() {
        return proyekLokasiList;
    }

    public void setProyekLokasiList(List<ProyekLokasi> proyekLokasiList) {
        this.proyekLokasiList = proyekLokasiList;
    }
}
