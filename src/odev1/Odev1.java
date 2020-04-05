/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odev1;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Scanner;
import odev1.domain.ObserverEkran;
import odev1.domain.SingletonAkilliCihaz;
import odev1.domain.SingletonConnect;
import odev1.domain.Users;

public class Odev1 {

    public static void main(String[] args) throws SQLException {
        // database bağlantısı yapılır
        SingletonConnect db = SingletonConnect.getInstance();
        Connection conn = db.getConnection();
        PreparedStatement pat;
        Integer loginTry = 0;
        boolean login = false;
        String username = "";
        String password = "";

        // databaseden kullanıcı bilgilerini alıcak olan database kodu
        String sql = "SELECT * FROM public.\"Users\"";
        // sql çalıştırıldı
        pat = conn.prepareStatement(sql);
        Scanner girdi = new Scanner(System.in);
        Users user = new Users();
        //kullanıcı adı doğrulaması yapılmadığı sürece devam eder
        while (login == false) {
            System.out.print("Lütfen kullanıcı adı giriniz : ");
            username = girdi.nextLine();
            System.out.print("Lütfen şifre giriniz : ");
            password = girdi.nextLine();
            ResultSet rs = pat.executeQuery();
            while (rs.next()) {
                user.setId(Integer.parseInt(rs.getString("id")));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    login = true;
                    System.out.println("giriş başarılı hoşgeldiniz : " + rs.getString("name"));
                } else {
                    loginTry++;
                }
            }
            if (login == false) {
                System.out.println("Giriş başarısız lütfen tekrar giriniz");

                if (loginTry == 5) {
                    System.out.println("5 kere başarısız giriş tespit edildi lütfen daha sonra deneyin");
                    System.exit(0);
                }
            }
        }
        boolean close = false;
        int secim;

        ObserverEkran observerEkran = new ObserverEkran();
        SingletonAkilliCihaz AkilliCihaz = SingletonAkilliCihaz.getInstance();
        observerEkran.addObserver(AkilliCihaz);

        //İşlem ekranını gösterir
        while (close == false) {
            System.out.println("---------------------------");
            System.out.println("Lütfen bir işlem seçiniz :");
            System.out.println("1 - Sıcaklık Görüntüle");
            System.out.println("2 - Soğutucu Aç");
            System.out.println("3 - Soğutucu Kapat");
            System.out.println("0 - Çıkış yap");
            System.out.println("---------------------------");
            secim = girdi.nextInt();
            switch (secim) {
                case 1:
                    System.out.println("Güncel sıcaklık değeri: " + AkilliCihaz.getSicaklik() + " C");
                    break;
                case 2:
                    observerEkran.setDurum(true);
                    break;
                case 3:
                    observerEkran.setDurum(false);
                    break;
                case 0:
                    close = true;
                    System.out.println("Başarıyla çıkış yapıldı");
                    break;
                default:
                    System.out.println("Lütfen geçerli bir değer girin");
                    break;
            }
        }

    }

}
