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

		DataOutputStream dos;
		try {
			dos = new DataOutputStream(new FileOutputStream("HocSinh.txt", true));
		} catch (IOException exc) {
			System.out.println("Error open file !");
			return;
		}

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
					dshs.add(new HocSinh(argl.get(count - 6).toString().toCharArray(),
							argl.get(count - 5).toString().toCharArray(), argl.get(count - 4).toString().toCharArray(),
							argl.get(count - 3).toString().toCharArray(), argl.get(count - 2).toString().toCharArray(),
							argl.get(count - 1).toString().toCharArray()));
				}

			}
		} catch (EOFException e) {
		} // Using Try to catch EOF

		int sel;
		do {
			Scanner sc = new Scanner(System.in);
			sel = 6;
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

//				char[] maSo = new char[10];
//				char[] hoTen = new char[10];
//				char[] diem = new char[10];
//				char[] hinhAnh = new char[10];
//				char[] diaChi = new char[10];
//				char[] ghiChu = new char[10];

				 System.out.println("---------THEM HOC SINH----------");
				 System.out.print("Nhap ma so: ");
				 sc.nextLine();
				 String maSo = sc.nextLine();
				 //sc.nextLine();
				 //char[] maSo = sc.nextLine().toCharArray();
				
				 System.out.print("Nhap ten: ");
				 String hoTen = sc.nextLine();
				 //dos.writeChars(hoTen);
				// char[] hoTen = sc.nextLine().toCharArray();
				
				 System.out.print("Nhap diem: ");
				 //String diem = sc.nextLine();
				 char[] diem = sc.nextLine().toCharArray();
				 dos.writeChars(diem.toString());
				
				 System.out.print("Them hinh anh: ");
				 String hinhAnh = sc.nextLine();
				 //dos.writeChars(hinhAnh);
				 //char[] hinhAnh = sc.nextLine().toCharArray();
				
				 System.out.print("Nhap dia chi: ");
				 String diaChi = sc.nextLine();
				 //dos.writeChars(diaChi);
				 //char[] diaChi = sc.nextLine().toCharArray();
				
				 System.out.print("Them ghi chu: ");
				 String ghiChu = sc.nextLine();
				 //dos.writeChars(ghiChu);
				 //char[] ghiChu = sc.nextLine().toCharArray();
				
				 dos.writeChars(maSo +","
				 + hoTen + ","
				 + diem + ","
				 + hinhAnh + ","
				 + diaChi + ","
				 + ghiChu + ",");
				dos.writeChars("1612885,Nguyen Xuan Tam,1,656565,65656,6565,");
				// sc.close();
			} else if (sel == 2) {
				char[] ms;
				System.out.println("---------CAP NHAT THONG TIN HOC SINH-----------");
				System.out.print("Nhap ma so hoc sinh: ");

				ms = sc.next().toCharArray();

				int flow = 0;
				
				for (HocSinh itr : dshs) {
					int flag = 0;
					if (ms.length != itr.GetMaSo().length){
						break;
					}
					for (int i = 0; i < itr.GetMaSo().length; i++) {
						if (ms[i] != itr.GetMaSo()[i]) {
							flag = 1;
							break;
						}
					}
					if (flag == 0) {
						
						System.out.println("THONG TIN HOC SINH (MS = " + itr.GetMaSo().toString() + "):");
						itr.PrintInformation();

						sc.nextLine();
						System.out.print("Nhap ma so: ");
						char[] maSo1 = sc.nextLine().toCharArray();

						System.out.print("Nhap ten: ");
						char[] tenHS1 = sc.nextLine().toCharArray();

						System.out.print("Nhap diem: ");
						char[] diem1 = sc.nextLine().toCharArray();

						System.out.print("Them hinh anh: ");
						char[] hinhAnh1 = sc.nextLine().toCharArray();

						System.out.print("Nhap dia chi: ");
						char[] diaChi1 = sc.nextLine().toCharArray();

						System.out.print("Them ghi chu: ");
						char[] ghiChu1 = sc.nextLine().toCharArray();

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
				for (HocSinh itr : dshs) {
					System.out.println(itr.GetMaSo());
				}

			} else if (sel == 3) {
				Scanner sc3 = new Scanner(System.in);
				System.out.println("------------XOA HOC SINH------------");
				System.out.print("Nhap ma so: ");

				char[] mshs = sc3.nextLine().toCharArray();
				int flow = 0;
				for (HocSinh itr : dshs) {
					int flag = 0;
					for (int i = 0; i < itr.GetMaSo().length; i++) {
						if (mshs[i] != itr.GetMaSo()[i]) {
							flag = 1;
							break;
						}
					}
					if (flag == 0) {
						dshs.remove(itr);
						System.out.println("XOA THANH CONG!!!");
						flow = 1;
						break;
					}

				}
				if (flow == 0) {
					System.out.println("HOC SINH KHONG TON TAI");
				}
				for (HocSinh itr : dshs) {
					System.out.println(itr.GetMaSo());
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
						for (int i = 0; i < dshs.size() - 1; i++) {
							for (int j = i + 1; j < dshs.size(); j++) {
								int flag = 0;
								int length = (dshs.get(i).GetMaSo().length < dshs.get(j).GetMaSo().length)
										? dshs.get(i).GetMaSo().length
										: dshs.get(j).GetMaSo().length;
								for (int ia = 0; ia < length; ia++) {
									if (dshs.get(i).GetMaSo()[ia] < dshs.get(j).GetMaSo()[ia]) {
										flag = 1;
										break;
									} else if (dshs.get(i).GetMaSo()[ia] > dshs.get(j).GetMaSo()[ia]) {
										flag = -1;
										break;
									}
								}
								if (flag == 0) {
									if (dshs.get(i).GetMaSo().length < dshs.get(j).GetMaSo().length) {
										flag = 1;
									}
								}
								if (flag == 1) {
									HocSinh temp = dshs.get(i);
									dshs.set(i, dshs.get(j));
									dshs.set(j, temp);
								}
							}
						}
						for (HocSinh itr : dshs) {
							System.out.println(itr.GetMaSo());
						}
					}
					// Tang dan
					else if (order == 2) {
						for (int i = 0; i < dshs.size() - 1; i++) {
							for (int j = i + 1; j < dshs.size(); j++) {
								int flag = 0;
								int length = (dshs.get(i).GetMaSo().length < dshs.get(j).GetMaSo().length)
										? dshs.get(i).GetMaSo().length
										: dshs.get(j).GetMaSo().length;
								for (int ia = 0; ia < length; ia++) {
									if (dshs.get(i).GetMaSo()[ia] > dshs.get(j).GetMaSo()[ia]) {
										flag = 1;
										break;
									} else if (dshs.get(i).GetMaSo()[ia] < dshs.get(j).GetMaSo()[ia]) {
										flag = -1;
										break;
									}
								}
								if (flag == 0) {
									if (dshs.get(i).GetMaSo().length > dshs.get(j).GetMaSo().length) {
										flag = 1;
									}
								}
								if (flag == 1) {
									HocSinh temp = dshs.get(i);
									dshs.set(i, dshs.get(j));
									dshs.set(j, temp);
								}
							}
						}
						System.out.println("Tang dan");
						for (HocSinh itr : dshs) {
							System.out.println(itr.GetMaSo());
						}
					}
				} while (order != 3);

			} else if (sel == 5) {
				// Giam dan
				int order;
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
						for (int i = 0; i < dshs.size() - 1; i++) {
							for (int j = i + 1; j < dshs.size(); j++) {
								int flag = 0;
								int length = (dshs.get(i).GetDiem().length < dshs.get(j).GetDiem().length)
										? dshs.get(i).GetDiem().length
										: dshs.get(j).GetDiem().length;
								for (int ia = 0; ia < length; ia++) {
									if (dshs.get(i).GetDiem()[ia] < dshs.get(j).GetDiem()[ia]) {
										flag = 1;
										break;
									} else if (dshs.get(i).GetDiem()[ia] > dshs.get(j).GetDiem()[ia]) {
										flag = -1;
										break;
									}
								}
								if (flag == 0) {
									if (dshs.get(i).GetDiem().length < dshs.get(j).GetDiem().length) {
										flag = 1;
									}
								}
								if (flag == 1) {
									HocSinh temp = dshs.get(i);
									dshs.set(i, dshs.get(j));
									dshs.set(j, temp);
								}
							}
						}
						for (HocSinh itr : dshs) {
							System.out.println(itr.GetDiem());
						}
					}
					// Tang dan
					else if (order == 2) {
						for (int i = 0; i < dshs.size() - 1; i++) {
							for (int j = i + 1; j < dshs.size(); j++) {
								int flag = 0;
								int length = (dshs.get(i).GetDiem().length < dshs.get(j).GetDiem().length)
										? dshs.get(i).GetDiem().length
										: dshs.get(j).GetDiem().length;
								for (int ia = 0; ia < length; ia++) {
									if (dshs.get(i).GetDiem()[ia] > dshs.get(j).GetDiem()[ia]) {
										flag = 1;
										break;
									} else if (dshs.get(i).GetDiem()[ia] < dshs.get(j).GetDiem()[ia]) {
										flag = -1;
										break;
									}
								}
								if (flag == 0) {
									if (dshs.get(i).GetDiem().length > dshs.get(j).GetDiem().length) {
										flag = 1;
									}
								}
								if (flag == 1) {
									HocSinh temp = dshs.get(i);
									dshs.set(i, dshs.get(j));
									dshs.set(j, temp);
								}
							}
						}
						System.out.println("Tang dan");
						for (HocSinh itr : dshs) {
							System.out.println(itr.GetDiem());
						}
					}
				} while (order != 3);

			}
			for (HocSinh itr : dshs) {
				itr.PrintInformation();
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
	// private String maSo;
	// private String diem;
	// private String hoTen;
	// private String hinhAnh;
	// private String diaChi;
	// private String ghiChu;

	private char[] maSo;
	private char[] diem;
	private char[] hoTen;
	private char[] hinhAnh;
	private char[] diaChi;
	private char[] ghiChu;

	public HocSinh(char[] maSo, char[] hoTen, char[] diem, char[] hinhAnh, char[] diaChi, char[] ghiChu) {
		this.maSo = maSo.clone();
		this.hoTen = hoTen.clone();
		this.diem = diem.clone();
		this.hinhAnh = hinhAnh.clone();
		this.diaChi = diaChi.clone();
		this.ghiChu = ghiChu.clone();
	}

	public char[] GetMaSo() {
		return this.maSo.clone();
	}

	public char[] GetDiem() {
		return this.diem.clone();
	}

	public void PrintInformation() {
		System.out.println(maSo);
		System.out.println(hoTen);
		System.out.println(diem);
		System.out.println(hinhAnh);
		System.out.println(diaChi);
		System.out.println(ghiChu);
	}
	public void ListInformation() {
		System.out.println(maSo + "," + hoTen + "," + diem
								+ "," + hinhAnh + "," + diaChi + "," + ghiChu);
	}
}