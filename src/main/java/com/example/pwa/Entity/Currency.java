package com.example.pwa.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String abbreviation;

    @OneToMany(mappedBy = "currency", cascade = CascadeType.ALL)
    private List<Wallet> wallets;

    public Currency() {
    }

    public Currency(int id, String abbreviation, String name, List<Wallet> wallets) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.name = name;
        this.wallets = wallets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public List<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets;
    }
}
