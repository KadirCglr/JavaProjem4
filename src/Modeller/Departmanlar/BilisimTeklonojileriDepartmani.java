package Modeller.Departmanlar;

public class BilisimTeklonojileriDepartmani implements Departman {

    final String departmanKodu = "BTD";

    // Zam oranini belirlemeyi unutmayalim.
    final int zamOrani = 25;


    @Override
    public int getZamOrani() {
        return zamOrani;
    }

    @Override
    public String getDepartmanKodu() {
        return departmanKodu;
    }
}
