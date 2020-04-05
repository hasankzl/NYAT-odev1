/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odev1.domain;

import java.util.Random;

/**
 *
 * @author Hasan
 */
public class SingletonAkilliCihaz implements Observer, SicaklikInterface, SogutucuInterface {

    private static SingletonAkilliCihaz instange = null;
    private Integer sicaklik;
    private Boolean durum;

    private SingletonAkilliCihaz() {
        sicaklikOku();
        durum = false;
    }

    public static SingletonAkilliCihaz getInstance() {
        if (getInstange() == null) {
            setInstange(new SingletonAkilliCihaz());
        }

        return getInstange();
    }

    @Override
    public void update(Boolean durum) {
        if (durum) {
            this.sogutucuAc();
        } else {
            this.sogutucuKapat();
        }

    }

    @Override
    public void sicaklikOku() {
        Random r = new Random(); //random sınıfı
        sicaklik = r.nextInt(50);
    }

    @Override
    public Integer sicaklikGonder() {
        return sicaklik;
    }

    @Override
    public void sogutucuAc() {
        System.out.println("Soğutucu açıldı");
        this.durum = true;
    }

    @Override
    public void sogutucuKapat() {
        System.out.println("Soğutucu kapatıldı");
        this.durum = false;
    }

    /**
     * @return the instange
     */
    public static SingletonAkilliCihaz getInstange() {
        return instange;
    }

    /**
     * @param aInstange the instange to set
     */
    public static void setInstange(SingletonAkilliCihaz aInstange) {
        instange = aInstange;
    }

    /**
     * @return the sicaklik
     */
    public Integer getSicaklik() {
        return sicaklik;
    }

    /**
     * @param sicaklik the sicaklik to set
     */
    public void setSicaklik(Integer sicaklik) {
        this.sicaklik = sicaklik;
    }

    /**
     * @return the durum
     */
    public Boolean getDurum() {
        return durum;
    }

    /**
     * @param durum the durum to set
     */
}
