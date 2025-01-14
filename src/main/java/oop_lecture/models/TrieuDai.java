package oop_lecture.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrieuDai extends LichSuCoTen {
    private String quocHieu;
    private LocalDate batDau, ketThuc;
    private DiaDiem thuDo;

    private List<NhanVatLichSu> hoangDe = new ArrayList<>();
    private List<String> tenHoangDe = new ArrayList<>();

    private List<SuKienLichSu> suKien = new ArrayList<>();
    private List<String> tenSuKien = new ArrayList<>();


    public TrieuDai(String ten, String quocHieu, LocalDate batDau, LocalDate ketThuc, DiaDiem thuDo, List<String> tenHoangDe, List<String> tenSuKien) {
        super(ten);
        this.quocHieu = quocHieu;
        this.batDau = batDau;
        this.ketThuc = ketThuc;
        this.thuDo = thuDo;
        this.tenHoangDe = tenHoangDe;
        this.tenSuKien = tenSuKien;
    }

    public String getQuocHieu() {
        return quocHieu;
    }

    public LocalDate getBatDau() {
        return batDau;
    }

    public LocalDate getKetThuc() {
        return ketThuc;
    }

    public DiaDiem getThuDo() {
        return thuDo;
    }

    public List<NhanVatLichSu> getHoangDe() {
        return hoangDe;
    }

    public List<SuKienLichSu> getSuKien() {
        return suKien;
    }

    // TODO: 10/01/2023 link
}
