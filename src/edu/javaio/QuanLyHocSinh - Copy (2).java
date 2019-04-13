package edu.javaio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sound.midi.Synthesizer;
import javax.swing.plaf.synth.SynthSpinnerUI;

public class QuanLyHocSinh {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		// Scanner sc = new Scanner(System.in);
		List<HocSinh> dshs = new ArrayList<HocSinh>();

		DataInputStream in = new DataInputStream(new FileInputStream("HocSinh.txt"));
		try {
			int count = 0;
			List<StringBuffer> argl = new ArrayList<StringBuffer>();
			while (true) {

				StringBuffer desc = new StringBuffer();
				// String desc;
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

		int sel;
		do {
			Scanner sc = new Scanner(System.in);
			sel = 6;
			System.out.println(" ------------------QUAN LY HOC SINH------------------");
			System.out.println("1.Them moi hoc sinh");
			System.out.println("2.Chinh sua thong tin hoc sinh");
			System.out.println("3.Xoa hoc sinh");
			System.out.println("4.Xem danh sach hoc sinh tang dan/giam dan theo ma so");
			System.out.println("5.Xem danh sach hoc sinh tang dan/giam dan theo diem");
			System.out.println("6.Thoat");
			System.out.print("Nhap yeu cau: ");
			// if (sc.hasNext()) {
			sel = sc.nextInt();
			// }
			if (sel == 1) {
				// Them hoc sinh

				// char[] maSo = new char[10];
				// char[] hoTen = new char[10];
				// char[] diem = new char[10];
				// char[] hinhAnh = new char[10];
				// char[] diaChi = new char[10];
				// char[] ghiChu = new char[10];

				System.out.println("---------THEM HOC SINH----------");
				System.out.print("Nhap ma so: ");
				sc.nextLine();
				String maSo = sc.nextLine();
				//dos.writeChars(maSo);
				//dos.writeChar(',');
				// char[] maSo = sc.nextLine().toCharArray();
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
				//dos.writeChars(hoTen);
				//dos.writeChar(',');
				// char[] hoTen = sc.nextLine().toCharArray();

				System.out.print("Nhap diem: ");
				String diem = sc.nextLine();
				// char[] diem = sc.nextLine().toCharArray();
				//dos.writeChars(diem);
				//dos.writeChar(',');

				System.out.print("Them hinh anh: ");
				String hinhAnh = sc.nextLine();
				//dos.writeChars(hinhAnh);
				//dos.writeChar(',');
				// char[] hinhAnh = sc.nextLine().toCharArray();

				System.out.print("Nhap dia chi: ");
				String diaChi = sc.nextLine();
				//dos.writeChars(diaChi);
				//dos.writeChar(',');
				// char[] diaChi = sc.nextLine().toCharArray();

				System.out.print("Them ghi chu: ");
				String ghiChu = sc.nextLine();
				//dos.writeChars(ghiChu);
				//dos.writeChar(',');
				// char[] ghiChu = sc.nextLine().toCharArray();
				//
				// dos.writeChars(maSo +","
				// + hoTen + ","
				// + diem + ","
				// + hinhAnh + ","
				// + diaChi + ","
				// + ghiChu + ",");
				//dos.writeChars("1612585,Nguyen Xuan Tam,1,656565,65656,6565,");
				
				
				HocSinh mod = new HocSinh(maSo, hoTen, diem, hinhAnh, diaChi, ghiChu);
				dshs.add(mod);
				}
				else {
					System.out.println("HOC SINH DA TON TAI");
				}
				// sc.close();
			} else if (sel == 2) {

				System.out.println("---------CAP NHAT THONG TIN HOC SINH-----------");
				System.out.print("Nhap ma so hoc sinh: ");

				String ms = sc.next();
				int flow = 0;

				for (HocSinh itr : dshs) {
					if (ms.compareTo(itr.GetMaSo()) == 0) {

						System.out.println("THONG TIN HOC SINH (MS = " + itr.GetMaSo().toString() + "):");
						itr.PrintInformation();

						sc.nextLine();
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
						System.out.println("CAP NHAT THANH CONG");
						flow = 1;
						break;

					}
				}
				if (flow == 0) {
					System.out.println("KHONG TON TAI HOC SINH");
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
					System.out.println("HOC SINH KHONG TON TAI");
				}
			} else if (sel == 4) {
				// for (HocSinh itr1 : dshs) {
				// for (HocSinh itr2 : dshs) {
				// if (itr1.GetMaSo()..compareTo(itr2.GetMaSo().toString()) < 0) {
				// HocSinh temp = dshs.get(dshs.indexOf(itr1));
				// dshs.set(dshs.indexOf(itr1), dshs.get(dshs.indexOf(itr2)));
				// dshs.set(dshs.indexOf(itr2), temp);
				// }
				// }
				// }
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
								if (dshs1.get(i).GetMaSo().compareTo(dshs1.get(j).GetMaSo()) < 0) {
									HocSinh temp = dshs1.get(i);
									dshs1.set(i, dshs1.get(j));
									dshs1.set(j, temp);
								}
							}
						}
						System.out.println("------GIAM DAN------");
						for (HocSinh itr : dshs1) {
							System.out.println(itr.GetMaSo());
						}
					}
					// Tang dan
					else if (order == 2) {
						for (int i = 0; i < dshs1.size() - 1; i++) {
							for (int j = i + 1; j < dshs1.size(); j++) {
								if (dshs1.get(i).GetMaSo().compareTo(dshs1.get(j).GetMaSo()) > 0) {
									HocSinh temp = dshs1.get(i);
									dshs1.set(i, dshs1.get(j));
									dshs1.set(j, temp);
								}
							}
						}
						System.out.println("------TANG DAN------");
						for (HocSinh itr : dshs1) {
							System.out.println(itr.GetMaSo());
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
								if (dshs1.get(i).GetDiem().compareTo(dshs1.get(j).GetDiem()) < 0) {
									HocSinh temp = dshs1.get(i);
									dshs1.set(i, dshs1.get(j));
									dshs1.set(j, temp);
								}
							}
						}
						System.out.println("------GIAM DAN------");
						for (HocSinh itr : dshs1) {
							System.out.println(itr.GetDiem());
						}
					}
					// Tang dan
					else if (order == 2) {
						for (int i = 0; i < dshs1.size() - 1; i++) {
							for (int j = i; j < dshs1.size(); j++) {

								// int length = (dshs.get(i).GetDiem().length() <
								// dshs.get(j).GetDiem().length())
								// ? dshs.get(i).GetDiem().length()
								// : dshs.get(j).GetDiem().length();
								if (dshs.get(i).GetDiem().compareTo(dshs.get(j).GetDiem()) > 0) {
									HocSinh temp = dshs1.get(i);
									dshs1.set(i, dshs1.get(j));
									dshs1.set(j, temp);
								}
							}
						}
						System.out.println("------TANG DAN------");
						for (HocSinh itr : dshs1) {
							System.out.println(itr.GetDiem());
						}
					}
				} while (order != 3);

			}
			DataOutputStream dos;
			try {
				dos = new DataOutputStream(new FileOutputStream("HocSinh.txt"));
			} catch (IOException exc) {
				System.out.println("Error open file !");
				return;
			}
			
			for (HocSinh itr : dshs) {
				itr.ListInformation();
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

		} while (sel != 6);

		// dos.close();

		// DataInputStream dis;
		// try
		// {
		// dis=new DataInputStream(
		// new FileInputStream("HocSinh.txt"));
		// }
		// catch(IOException exc)
		// {
		// System.out.println("Error open file !");
		// return;
		// }
		//
		// String maSo1 = dis.readUTF();
		// System.out.println(maSo1);

		// String maSo1 = sc.nextLine();
		// dos.writeChars(maSo1);
		// System.out.println(maSo1);
		//
		// String maSo1 = sc.nextLine();
		// dos.writeChars(maSo1);
		// System.out.println(maSo1);
		//
		// String maSo1 = sc.nextLine();
		// dos.writeChars(maSo1);
		// System.out.println(maSo1);
		//
		// String maSo1 = sc.nextLine();
		// dos.writeChars(maSo1);
		// System.out.println(maSo1);

		// Xoa hoc sinh
		// try {
		// Scanner sc2 = new Scanner(new DataInputStream(new
		// FileInputStream("HocSinh.txt")));
		// //DataInputStream dis = new DataInputStream(new
		// FileInputStream("HocSinh.txt"));
		// sc2.useDelimiter(",");
		//
		// while (sc2.hasNext()) {
		// //String str = sc2.nextLine();
		// //String a =
		// String a1 = sc2.next();
		// String a2 = sc2.next();
		// String a3 = sc2.next();
		// String a4 = sc2.next();
		// String a5 = sc2.next();
		// String a6 = sc2.next();
		// dshs.add((new HocSinh(a1,a2,a3,a4,a5,a6)));
		// //System.out.println(str);
		// }
		// sc2.close();
		//
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// for(HocSinh itr:dshs) {
		// System.out.println(itr.GetMaSo());
		// }

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

	// private char[] maSo;
	// private char[] diem;
	// private char[] hoTen;
	// private char[] hinhAnh;
	// private char[] diaChi;
	// private char[] ghiChu;

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

	public void PrintInformation() {
		System.out.println(maSo);
		System.out.println(hoTen);
		System.out.println(diem);
		System.out.println(hinhAnh);
		System.out.println(diaChi);
		System.out.println(ghiChu);
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