package Modeller.Calisanlar;

import Modeller.Departmanlar.Departman;
import Veritabani.Calisanlar;
import Veritabani.Departmanlar;

import java.util.ArrayList;

public class Calisan {

    private String calisanId;
    private String adSoyad;
    private int maas;
    private Departman Departman;
    private String isimKodu = "";

    public Calisan(String adSoyad, int maas, String departmanKodu) {
        this.adSoyad = adSoyad;
        this.maas = maas;
        this.setDepartman(departmanKodu);
        this.setCalisanId();
        Calisanlar.addACalisan(this);
    }

    // Constructor calistiktan sonra kullaniciyi departmanına kaydetmek için
    private void setDepartman(String departmanKodu) {
        for (Departman d : Departmanlar.getDepartmanList()) {
            if (d.getDepartmanKodu().equals(departmanKodu)) {
                this.Departman = d;
            }
        }
    }

    // Calisanin id sini isteğe bağlı yollar ile set etmek için
    private void setCalisanId() {
        this.calisanId = this.Departman.getDepartmanKodu() + (Calisanlar.getCalisanList().size() + 1) + this.getCalisanIsimKodu();
    }

    // Calisanin id sinin sonuna isim kodu eklenmesi için, ismi parçalayan bir method
    private String getCalisanIsimKodu() {
        this.isimKodu += this.adSoyad.charAt(0);

        for (int i = 0; i < this.adSoyad.length(); i++) {
            if (adSoyad.charAt(i) == ' ') {
                this.isimKodu += adSoyad.charAt(i + 1);
            }
        }
        return this.isimKodu;
    }

    // Calisanin id sini almak icin basit getter method
    public String getCalisanId() {
        return this.calisanId;
    }

    // Calisanin departmanini sini almak icin basit getter method
    public Departman getDepartman() {
        return this.Departman;
    }

    // Departman adini verebilmek için
    public String getDepartmanAdi() {
        String departman = "";
        if (this.Departman.getDepartmanKodu().equals("YD")) {
            departman = "Yönetim Departmanı";
        } else if (this.Departman.getDepartmanKodu().equals("BTD")) {
            departman = "Bilişim Teklonojileri Departmani";
        } else if (this.Departman.getDepartmanKodu().equals("IKD")) {
            departman = "Insan Kaynaklari Departmani";
        }
        return departman;
    }

    // Calisanin departmanindaki zam orani ne ise, ona göre maaşına zam yapilmasi icin
    public static void zamYap(String calisanId) {
        ArrayList<Calisan> calisanList = Calisanlar.getCalisanList();
        for (int i = 0; i < calisanList.size(); i++) {
            if (calisanList.get(i).getCalisanId().equals(calisanId)) {
                calisanList.get(i).maas += (calisanList.get(i).maas * calisanList.get(i).Departman.getZamOrani()) / 100;
            }
        }
    }

    @Override
    public String toString() {
        return "Calisan ID : '" + calisanId + '\'' +
                ", Isim Soyisim : '" + adSoyad + '\'' +
                ", Maas : '" + maas + '\'' +
                ", Departman : '" + getDepartmanAdi() + '\'';
    }
}