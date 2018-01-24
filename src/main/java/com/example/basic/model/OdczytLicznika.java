package com.example.basic.model;
// Generated 2018-01-24 14:30:52 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * OdczytLicznika generated by hbm2java
 */
@Entity
@Table(name="odczyt_licznika"
    ,schema="public"
)
public class OdczytLicznika  implements java.io.Serializable {


     private int idOdczytLicznika;
     private Dzialki dzialki;
     private int nrPomiaru;
     private Date data;
     private Integer stanLicznika;
     private Double naleznosc;

    public OdczytLicznika() {
    }

	
    public OdczytLicznika(int idOdczytLicznika, Dzialki dzialki, int nrPomiaru) {
        this.idOdczytLicznika = idOdczytLicznika;
        this.dzialki = dzialki;
        this.nrPomiaru = nrPomiaru;
    }
    public OdczytLicznika(int idOdczytLicznika, Dzialki dzialki, int nrPomiaru, Date data, Integer stanLicznika, Double naleznosc) {
       this.idOdczytLicznika = idOdczytLicznika;
       this.dzialki = dzialki;
       this.nrPomiaru = nrPomiaru;
       this.data = data;
       this.stanLicznika = stanLicznika;
       this.naleznosc = naleznosc;
    }
   
     @Id 

    
    @Column(name="id_odczyt_licznika", unique=true, nullable=false)
    public int getIdOdczytLicznika() {
        return this.idOdczytLicznika;
    }
    
    public void setIdOdczytLicznika(int idOdczytLicznika) {
        this.idOdczytLicznika = idOdczytLicznika;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="nr_dzialki", nullable=false)
    public Dzialki getDzialki() {
        return this.dzialki;
    }
    
    public void setDzialki(Dzialki dzialki) {
        this.dzialki = dzialki;
    }

    
    @Column(name="nr_pomiaru", nullable=false)
    public int getNrPomiaru() {
        return this.nrPomiaru;
    }
    
    public void setNrPomiaru(int nrPomiaru) {
        this.nrPomiaru = nrPomiaru;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="data", length=13)
    public Date getData() {
        return this.data;
    }
    
    public void setData(Date data) {
        this.data = data;
    }

    
    @Column(name="stan_licznika")
    public Integer getStanLicznika() {
        return this.stanLicznika;
    }
    
    public void setStanLicznika(Integer stanLicznika) {
        this.stanLicznika = stanLicznika;
    }

    
    @Column(name="naleznosc", precision=17, scale=17)
    public Double getNaleznosc() {
        return this.naleznosc;
    }
    
    public void setNaleznosc(Double naleznosc) {
        this.naleznosc = naleznosc;
    }




}

