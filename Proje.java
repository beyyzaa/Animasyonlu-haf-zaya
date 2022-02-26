package proje;

/**
 * @file Proje
 * @descripiton Kullanıcıdan girilmiş olan karmaşık metin ve sözlük , harflerin
 * büyüklüğü yerleri değiştirilerek metin düzenlenir.Metin ile sözlük metni
 * karşılaştırılır ve hedef metin elde edilir. Hedef metin ve sözlük
 * karşılaştırılması sonucu kelimelerin kaç kere geçtiği sırasıyla yazdırılır.
 * @assigment Proje Ödevi 1
 * @date.05.12.21
 * @author Beyza Aktaş - beyza.aktas@stu.fsm.edu.tr
 */
import java.util.Scanner;

public class Proje {

    public static void main(String[] args) {

        // kullanıcıdan karışık metin ve sözlük istenir
        Scanner scan = new Scanner(System.in);

        System.out.println("İstediğiniz Karışık Metni Giriniz : ");
        String karmasıkmetin = scan.nextLine();
        String[] karmasıkDizi = DiziAtama(karmasıkmetin);
        String[] yeniKarmaşıkDizi = new String[karmasıkDizi.length];
        yeniKarmaşıkDizi = KelimeDüzeltme(karmasıkDizi);

        System.out.println("Girilen Karışık Metnin Sözlüğünü Giriniz : ");
        String sözlük = scan.nextLine();
        String[] sozlukDizi = DiziAtama(sözlük);
        String[] yeniSözlükDizi = new String[sozlukDizi.length];

        // metodlar ile yapılan tüm düzenlemeler ile hedef metin çıktısı alınır
        String[] hedefMetinDizi = new String[karmasıkDizi.length];
        hedefMetinDizi = sozlukKarsılastır(yeniKarmaşıkDizi, sozlukDizi);
        System.out.println("Hedef Metin (düzenlenmiş) Çıktı : ");
        for (int i = 0; i < hedefMetinDizi.length; i++) {
            System.out.print(hedefMetinDizi[i] + " ");
        }
        System.out.println("");

        // her bir kelimenin kaç kere geçtiği metod sayesinde yazdırılır
        int[] kacKere = new int[sözlük.length()];
        kacKere = kacKereCıktı(hedefMetinDizi, sozlukDizi);
        System.out.println("Kelime Tekrarları : ");
        for (int i = 0; i < kacKere.length; i++) {
            System.out.print(kacKere[i] + " ");
        }

    }

    public static String[] DiziAtama(String karmasıkmetin) {

        // bu metod ile kullanıcıdan istenilen string metinler diziye dönüştürülür 
        int sayac = 1;
        for (int i = 0; i < karmasıkmetin.length(); i++) {
            if (karmasıkmetin.charAt(i) == ' ') {
                sayac++;
            }

        }

        String[] kelimeDizi = new String[sayac];

        String kelimesay = "";
        int sayac1 = 0;

        for (int j = 0; j < karmasıkmetin.length(); j++) {
            if (karmasıkmetin.charAt(j) != ' ' && j != (karmasıkmetin.length() - 1)) {
                kelimesay += karmasıkmetin.charAt(j);  
            } else if (karmasıkmetin.charAt(j) == ' ') {
                kelimeDizi[sayac1] = kelimesay;

                kelimesay = "";

                sayac1++;

            } else if (j == (karmasıkmetin.length() - 1)) {
                kelimesay += karmasıkmetin.charAt(j);
                kelimeDizi[sayac1] = kelimesay;
            }

        }

        return kelimeDizi;

    }

    public static String[] KelimeDüzeltme(String[] metin) {

        // kullanıcıdan alıp diziye dönüştürdüğümüz metindeki bazı harfler büyüktür ve bu metod ile bu harfler küçültülür 
        for (int i = 0; i < metin.length; i++) {

            String kelime = metin[i];
            String yeniKelime = "";
            int fark = 'A' - 'a';

            for (int j = 0; j < kelime.length(); j++) {
                if ((kelime.charAt(j)) >= (char) ('A') && (char) ('Z') >= (kelime.charAt(j))) {

                    yeniKelime += (char) (kelime.charAt(j) - fark);

                } else {
                    yeniKelime += (char) (kelime.charAt(j));
                }
                
            }
            metin[i] = yeniKelime;
        }

        return metin;

    }

    public static char[] DiziyeCevir(String metin) {

        // bu metod ile girilen karakterler diziye atanır 
        char[] dizi = new char[metin.length()];

        for (int i = 0; i < metin.length(); i++) {
            dizi[i] = metin.charAt(i);
        }

        return dizi;

    }

    public static String SagDondur(String metin) {

        // bu metod ile karışık halde olan harfler döndürülerek düzgün hale getirilir
        String yeniMetin = "";
        char[] karakter = DiziyeCevir(metin);

        char temp = karakter[0];
        for (int i = 1; i < karakter.length; i++) {
            karakter[i - 1] = karakter[i];
            yeniMetin += karakter[i];
        }

        yeniMetin += temp;

        return yeniMetin;

    }

    public static String[] sozlukKarsılastır(String[] metinDizi, String[] sözlükDizi) {

        // bu metod ile girilen karmaşık metin ve sözlük karşılaştırılır,ortak kelimeler alınır
        String yeniKelime = "";
        for (int i = 0; i < metinDizi.length; i++) {
            yeniKelime = metinDizi[i];
            for (int j = 0; j < sözlükDizi.length; j++) {
                if (metinDizi[i].equals(sözlükDizi[j])) {
                    metinDizi[i] = (sözlükDizi[j]);
                } else {

                    for (int k = 0; k < metinDizi[i].length(); k++) {
                        metinDizi[i] = SagDondur(metinDizi[i]);
                        if (metinDizi[i].equals(sözlükDizi[j])) {
                            metinDizi[i] = sözlükDizi[j];

                            break;
                        } else {
                            continue;
                        }
                    }
                }
            }
        }

        return metinDizi;

    }

    public static int[] kacKereCıktı(String[] hedefMetinDizi, String[] sözlükDizi) {

        // hedef metni oluşturduktan sonra bu metinde, sözlükteki her bir kelimenin kaç defa geçtiğini bulup sözlükteki kelime sırasına göre yazdırılır
        int[] kacKere = new int[sözlükDizi.length];
        for (int i = 0; i < sözlükDizi.length; i++) {

            for (int j = 0; j < hedefMetinDizi.length; j++) {
                if ((sözlükDizi[i]).equals(hedefMetinDizi[j])) {
                    kacKere[i]++;
                } else {
                    continue;
                }

            }
        }

        return kacKere;

    }
}
