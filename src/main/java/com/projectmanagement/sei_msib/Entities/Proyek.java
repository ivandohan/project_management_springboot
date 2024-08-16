package com.projectmanagement.sei_msib.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Proyek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaProyek;

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
}
