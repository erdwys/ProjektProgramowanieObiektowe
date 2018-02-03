package com.example.basic.model;
// Generated 2018-02-02 20:24:56 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
     private String data;
     private Integer stanLicznika;
     private Double naleznosc;

    public OdczytLicznika() {
    }

	
    public OdczytLicznika(int idOdczytLicznika, Dzialki dzialki, int nrPomiaru) {
        this.idOdczytLicznika = idOdczytLicznika;
        this.dzialki = dzialki;
        this.nrPomiaru = nrPomiaru;
    }
    public OdczytLicznika(int idOdczytLicznika, Dzialki dzialki, int nrPomiaru, String data, Integer stanLicznika, Double naleznosc) {
       this.idOdczytLicznika = idOdczytLicznika;
       this.dzialki = dzialki;
       this.nrPomiaru = nrPomiaru;
       this.data = data;
       this.stanLicznika = stanLicznika;
       this.naleznosc = naleznosc;
    }
   
     @Id 

    

 @SequenceGenerator(name="Odczyt_licznika_id_seq",
                       sequenceName="Odczyt_licznika_id_seq",
                       allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator="Odczyt_licznika_id_seq")


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

    
    @Column(name="data")
    public String getData() {
        return this.data;
    }
    
    public void setData(String data) {
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


