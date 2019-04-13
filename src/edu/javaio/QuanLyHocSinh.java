package edu.javaio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Integer;

public class QuanLyHocSinh {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		List<HocSinh> dshs = new ArrayList<HocSinh>();

		DataInputStream in = new DataInputStream(new FileInputStream("HocSinh.txt"));
		try {
			int count = 0;
			List<StringBuffer> argl = new ArrayList<StringBuffer>();
			while (true) {

				StringBuffer desc = new StringBuffer();
				char chr;
				char lineSep = ',';

				while ((chr = in.readChar()) != lineSep) {
					desc.append(chr);
				}
				argl.add(desc);
				count++;
				if (count % 6 == 0) {
					dshs.add(new HocSinh(argl.get(count - 6).toString(), argl.get(count - 5).toString(),
							argl.get(count - 4).toString(), argl.get(count - 3).toString(),
							argl.get(count - 2).toString(), argl.get(count - 1).toString()));
				}

			}
		} catch (EOFException e) {
		} // Using Try to catch EOF
		Scanner sc = new Scanner(System.in);
		int sel;
		do {
			
			sel = 8;
			System.out.println(" ------------------QUAN LY HOC SINH------------------");
			System.out.println("1.Them moi hoc sinh");
			System.out.println("2.Chinh sua thong tin hoc sinh");
			System.out.println("3.Xoa hoc sinh");
			System.out.println("4.Xem danh sach hoc sinh tang dan/giam dan theo ma so");
			System.out.println("5.Xem danh sach hoc sinh tang dan/giam dan theo diem");
			System.out.println("6.Export danh sach hoc sinh ra file CSV");
			System.out.println("7.Import danh sach hoc sinh tu file CSV");
			System.out.println("8.Thoat");
			System.out.print("Nhap yeu cau: ");
			// if (sc.hasNext()) {
			sel = sc.nextInt();
			// }
			if (sel == 1) {
				// Them hoc sinh
				System.out.println("---------THEM HOC SINH----------");
				System.out.print("Nhap ma so: ");
				sc.nextLine();
				String maSo = sc.nextLine();

				int flag = 0;
				for (HocSinh itr : dshs) {
					if (maSo.compareTo(itr.GetMaSo()) == 0) {
						flag = 1;
						break;
					}
				}
				if (flag == 0) {
					System.out.print("Nhap ten: ");
					String hoTen = sc.nextLine();

					System.out.print("Nhap diem: ");
					String diem = sc.nextLine();

					System.out.print("Them hinh anh: ");
					String hinhAnh = sc.nextLine();

					System.out.print("Nhap dia chi: ");
					String diaChi = sc.nextLine();

					System.out.print("Them ghi chu: ");
					String ghiChu = sc.nextLine();
					

					HocSinh mod = new HocSinh(maSo, hoTen, diem, hinhAnh, diaChi, ghiChu);
					dshs.add(mod);
				} else {
					System.out.println("HOC SINH DA TON TAI!!!");
				}
	
			} else if (sel == 2) {

				System.out.println("---------CAP NHAT THONG TIN HOC SINH-----------");
				System.out.print("Nhap ma so hoc sinh: ");

				String ms = sc.next();
				int flow = 0;

				for (HocSinh itr : dshs) {
					if (ms.compareTo(itr.GetMaSo()) == 0) {

						System.out.println("THONG TIN HOC SINH (MS = " + itr.GetMaSo().toString() + "):");
						System.out.println("Ma so, Ho va ten, Diem, Hinh anh, Dia chi, Ghi chu");
						itr.ListInformation();

						sc.nextLine();
						System.out.println("\nNHAP LAI THONG TIN HOC SINH");
						System.out.print("Nhap ma so: ");
						String maSo1 = sc.nextLine();

						System.out.print("Nhap ten: ");
						String tenHS1 = sc.nextLine();

						System.out.print("Nhap diem: ");
						String diem1 = sc.nextLine();

						System.out.print("Them hinh anh: ");
						String hinhAnh1 = sc.nextLine();

						System.out.print("Nhap dia chi: ");
						String diaChi1 = sc.nextLine();

						System.out.print("Them ghi chu: ");
						String ghiChu1 = sc.nextLine();

						HocSinh mod = new HocSinh(maSo1, tenHS1, diem1, hinhAnh1, diaChi1, ghiChu1);
						dshs.set(dshs.indexOf(itr), mod);
						System.out.println("CAP NHAT THANH CONG!!!");
						flow = 1;
						break;

					}
				}
				if (flow == 0) {
					System.out.println("KHONG TON TAI HOC SINH!!!");
				}

			} else if (sel == 3) {
				Scanner sc3 = new Scanner(System.in);
				System.out.println("------------XOA HOC SINH------------");
				System.out.print("Nhap ma so: ");

				String mshs = sc3.nextLine();
				int flow = 0;
				for (HocSinh itr : dshs) {
					if (mshs.compareTo(itr.GetMaSo()) == 0) {
						dshs.remove(itr);
						System.out.println("XOA THANH CONG!!!");
						flow = 1;
						break;
					}

				}
				if (flow == 0) {
					System.out.println("KHONG TON TAI HOC SINH!!!");
				}
			} else if (sel == 4) {
				int order;
				List<HocSinh> dshs1 = new ArrayList<HocSinh>(dshs);
				do {

					order = 3;
					System.out.println("-------XEM DANH SACH HOC SINH THEO MA SO--------");
					System.out.println("1.Xem theo thu tu giam dan");
					System.out.println("2.Xem theo thu tu tang dan");
					System.out.println("3.Tro ve menu chinh");
					System.out.print("Nhap yeu cau: ");
					order = sc.nextInt();
					// Giam dan
					if (order == 1) {
						for (int i = 0; i < dshs1.size() - 1; i++) {
							for (int j = i + 1; j < dshs1.size(); j++) {
								if (Integer.valueOf(dshs1.get(i).GetMaSo()) < Integer.valueOf(dshs1.get(j).GetMaSo())) {
									HocSinh temp = dshs1.get(i);
									dshs1.set(i, dshs1.get(j));
									dshs1.set(j, temp);
								}
							}
						}
						System.out.println("------GIAM DAN------");
						System.out.println("Ma so, Ho va ten, Diem, Hinh anh, Dia chi, Ghi chu");
						for (HocSinh itr : dshs1) {
							itr.ListInformation();
						}
					}
					// Tang dan
					else if (order == 2) {
						for (int i = 0; i < dshs1.size() - 1; i++) {
							for (int j = i + 1; j < dshs1.size(); j++) {
								if (Integer.valueOf(dshs1.get(i).GetMaSo()) > Integer.valueOf(dshs1.get(j).GetMaSo())) {
									HocSinh temp = dshs1.get(i);
									dshs1.set(i, dshs1.get(j));
									dshs1.set(j, temp);
								}
							}
						}
						System.out.println("------TANG DAN------");
						System.out.println("Ma so, Ho va ten, Diem, Hinh anh, Dia chi, Ghi chu");
						for (HocSinh itr : dshs1) {
							itr.ListInformation();
						}
					}
				} while (order != 3);

			} else if (sel == 5) {
				// Giam dan
				int order;
				List<HocSinh> dshs1 = new ArrayList<HocSinh>(dshs);

				do {

					order = 3;
					System.out.println("-------XEM DANH SACH HOC SINH THEO DIEM--------");
					System.out.println("1.Xem theo thu tu giam dan");
					System.out.println("2.Xem theo thu tu tang dan");
					System.out.println("3.Tro ve menu chinh");
					System.out.print("Nhap yeu cau: ");
					order = sc.nextInt();
					// Giam dan
					if (order == 1) {
						for (int i = 0; i < dshs1.size() - 1; i++) {
							for (int j = i + 1; j < dshs.size(); j++) {
								if (Integer.valueOf(dshs1.get(i).GetDiem()) < Integer.valueOf(dshs1.get(j).GetDiem())) {
									HocSinh temp = dshs1.get(i);
									dshs1.set(i, dshs1.get(j));
									dshs1.set(j, temp);
								}
							}
						}
						System.out.println("------GIAM DAN------");
						System.out.println("Ma so, Ho va ten, Diem, Hinh anh, Dia chi, Ghi chu");
						for (HocSinh itr : dshs1) {
							itr.ListInformation();
						}
					}
					// Tang dan
					else if (order == 2) {
						for (int i = 0; i < dshs1.size() - 1; i++) {
							for (int j = i; j < dshs1.size(); j++) {
								if (Integer.valueOf(dshs1.get(i).GetDiem()) > Integer.valueOf(dshs1.get(j).GetDiem())) {
									HocSinh temp = dshs1.get(i);
									dshs1.set(i, dshs1.get(j));
									dshs1.set(j, temp);
								}
							}
						}
						System.out.println("------TANG DAN------");
						System.out.println("Ma so, Ho va ten, Diem, Hinh anh, Dia chi, Ghi chu");
						for (HocSinh itr : dshs1) {
							itr.ListInformation();
						}
					}

				} while (order != 3);

			} else if (sel == 6) {

				String csvFile = "HocSinh.csv";
				FileWriter fileWriter = new FileWriter(csvFile);
				try {

					for (HocSinh itr : dshs) {
						fileWriter.append(itr.GetMaSo());
						fileWriter.append(',');
						fileWriter.append(itr.getHoTen());
						fileWriter.append(',');
						fileWriter.append(itr.GetDiem());
						fileWriter.append(',');
						fileWriter.append(itr.getHinhAnh());
						fileWriter.append(',');
						fileWriter.append(itr.getDiaChi());
						fileWriter.append(',');
						fileWriter.append(itr.getGhiChu());
						fileWriter.append('\n');
					}
					System.out.println("EXPORT DU LIEU THANH CONG!!!");

				} catch (Exception e) {
					System.out.println("EXPORT DU LIEU THAT BAI!!!");
					e.printStackTrace();
				} finally {
					try {
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e) {
						System.out.println("ERROR WHILE FLUSHING/CLOSING FILEWRITER !!!");
						e.printStackTrace();
					}
				}
				

			} else if (sel == 7) {

				try {
					System.out.print("Nhap ten file can import: ");
					sc.nextLine();
					String fileName = sc.nextLine();
					Scanner sc2 = new Scanner(new File(fileName));
					
					sc2.useDelimiter(",");
					dshs.removeAll(dshs);
					while (sc2.hasNext()) {
						String a1 = sc2.next();
						String a2 = sc2.next();
						String a3 = sc2.next();
						String a4 = sc2.next();
						String a5 = sc2.next();
						String a6 = sc2.nextLine();
						a6 = a6.substring(1, a6.length());

						dshs.add((new HocSinh(a1, a2, a3, a4, a5, a6)));
					}
					sc2.close();
					System.out.println("IMPORT DU LIEU THANH CONG!!!");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("KHONG MO DUOC FILE!!!");
				}
			}
			DataOutputStream dos;
			try {
				dos = new DataOutputStream(new FileOutputStream("HocSinh.txt"));
			} catch (IOException exc) {
				System.out.println("Error open file !");
				return;
			}

			for (HocSinh itr : dshs) {
				dos.writeChars(itr.GetMaSo());
				dos.writeChar(',');
				dos.writeChars(itr.getHoTen());
				dos.writeChar(',');
				dos.writeChars(itr.GetDiem());
				dos.writeChar(',');
				dos.writeChars(itr.getHinhAnh());
				dos.writeChar(',');
				dos.writeChars(itr.getDiaChi());
				dos.writeChar(',');
				dos.writeChars(itr.getGhiChu());
				dos.writeChar(',');
			}
			dos.close();
		} while (sel != 8);
		sc.close();
		in.close();
	}

}

class HocSinh {
	private String maSo;
	private String hoTen;
	private String diem;
	private String hinhAnh;
	private String diaChi;
	private String ghiChu;


	public HocSinh(String maSo, String hoTen, String diem, String hinhAnh, String diaChi, String ghiChu) {
		super();
		this.maSo = maSo;
		this.diem = diem;
		this.hoTen = hoTen;
		this.hinhAnh = hinhAnh;
		this.diaChi = diaChi;
		this.ghiChu = ghiChu;
	}

	public String GetMaSo() {
		return this.maSo;
	}

	public String GetDiem() {
		return this.diem;
	}

	public String getHoTen() {
		return hoTen;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void ListInformation() {
		System.out.println(maSo + "," + hoTen + "," + diem + "," + hinhAnh + "," + diaChi + "," + ghiChu);
	}
}