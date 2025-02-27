package oop_lecture.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LeHoiVanHoa extends LichSuCoTen {
    private String capDo;
    private boolean laDiSanQuocGia;
    private DiaDiem noiDienRa;
    private LocalDate thoiDiemToChuc;
    private String lanDauToChuc;

    private List<NhanVatLichSu> nhanVatLienQuan = new ArrayList<>();
    private List<String> tenNhanVatLienQuan = new ArrayList<>();


    public LeHoiVanHoa(String ten, String capDo, boolean laDiSanQuocGia, DiaDiem noiDienRa, LocalDate thoiDiemToChuc,String lanDauToChuc, List<String> tenNhanVatLienQuan) {
		super(ten);
		this.capDo = capDo;
		this.laDiSanQuocGia = laDiSanQuocGia;
		this.noiDienRa = noiDienRa;
		this.thoiDiemToChuc = thoiDiemToChuc;
		this.lanDauToChuc = lanDauToChuc;
		this.tenNhanVatLienQuan = tenNhanVatLienQuan;
	}


	public String getCapDo() {
		return capDo;
	}
	
	public boolean isLaDiSanQuocGia() {
		return laDiSanQuocGia;
	}

	public DiaDiem getNoiDienRa() {
		return noiDienRa;
	}

	public LocalDate getThoiDiemToChuc() {
		return thoiDiemToChuc;
	}

	public String getLanDauToChuc() {
		return lanDauToChuc;
	}

	public List<NhanVatLichSu> getNhanVatLienQuan() {
		return nhanVatLienQuan;
	}
	
}
