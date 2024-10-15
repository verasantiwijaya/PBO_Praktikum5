/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package perpustakaan.main;

import java.util.ArrayList;
import perpustakaan.member.Member;

/**
 *
 * @author HP
 */
public class Perpustakaan {

    public static void main(String[] args) {
        Member member = new Member();

        //tambah data
        member.nama = "Novan";
        member.email = "novan@gmail.com";
        member.nomorTelepon = "08123456";
        
        if (member.create()) {
            System.out.println("Member berhasil ditambahkan.");
        }
        
        // mengambil data member berdasarkan id   
        member.find(2);

        System.out.println(member.getId());
        System.out.println(member.nama);
        System.out.println(member.email);
        System.out.println(member.nomorTelepon);
        
        
        // mengupdate data member
        member.find(2);
        
        member.nama = "Marcelius";
        member.email = "marcelius@gmail.com";
        
        if (member.update()) {
            System.out.println("Member berhasil diupdate.");
       }
        
        //hapus data member
        member.find(7);
        
        if (member.delete()) {
            System.out.println("Member berhasil dihapus.");
        }
        
        
        //pakai method ALL untuk menampilkan smua
          Database db = new Database();        
          db.openConnection();        
          ArrayList<ArrayList> members = db.all("SELECT * FROM member");        
          System.out.println(members);        
          db.closeConnection();
    }

}
