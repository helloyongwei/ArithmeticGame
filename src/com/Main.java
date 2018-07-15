package com;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.Arithmetic;

public class Main {
	public static void main(String[] args) throws IOException {
		int value = 0;
		do {
			showMenu();
			value = inputNumber();
			if (value < 1 || value > 5 ) {
				showSpace();
				System.out.println("-ѡ��Խ��");
			} else if (value >= 1 && value <= 4) {
				excute(value);
			}
			
		} while (value != 5);
	}
	
	public static void excute(int value) throws IOException {
		switch (value) {
		case 1: 
			quantity();
			break;
		case 2:
			timing();
			break;
		case 3:
			save();
		case 4: 
			show();
			break;
		}
	}
	
	public static void save() throws IOException {
		OutputStream out = new FileOutputStream("records.text", true);
		String msg = "Hi";
		byte[] bs = msg.getBytes();
		out.write(bs);
		out.close();
	}
	
	public static void show() throws IOException {
		byte[] bs = new byte[1024];
		InputStream is = new FileInputStream("records.text");
		int len = is.read(bs);
		String msg = new String(bs, 0, len);
		System.out.println(msg);
		is.close();
	}
	
	public static void timing() throws IOException {
		
		ArrayList<Arithmetic> arithmetics = new ArrayList<Arithmetic>();
		System.out.print("������ʱ�䣨���룩: ");
		int time = inputNumber();
		int[] a = computeByTiming(arithmetics, time);
		showResult(arithmetics);
		System.out.println("The score is : " + (100* (double)a[0]/a[1]));
	}
	
	public static int[] computeByTiming(ArrayList<Arithmetic> arithmetics, int time) throws IOException {
		int[] a = new int[2];
		int correct = a[0];
		int count = a[1];
		long start = System.currentTimeMillis();
		for (int index = 0; index < 1000; index++) {
			Arithmetic arithmetic = Arithmetic.createArithmetic();
			String msg = arithmetic.showQuestion();
			System.out.print(msg);
			int answer = inputNumber();
			arithmetic.setAnswer(answer);
			arithmetic.caculate(arithmetic.getOp());
			arithmetics.add(arithmetic);
			
			correct += arithmetic.getJudge();
			
			count = index;
			
			long end = System.currentTimeMillis();
			if ((end - start) >= time) {
				break;
			}
		}
		
		
		a[0] = correct;
		a[1] = count;
		return a;
		
	}
	
	public static void quantity() throws IOException {
		ArrayList<Arithmetic> arithmetics = new ArrayList<Arithmetic>();
		int count = 0;
		System.out.print("�����������: ");
		count = inputNumber();
		int correct = computeByQuantity(arithmetics, count);
		showResult(arithmetics);
		System.out.println("��ĵ÷�Ϊ: " + (100* (double)correct/count));
	}
	
	public static int computeByQuantity(ArrayList<Arithmetic> arithmetics, int count) throws IOException {
		int correct = 0;
		for (int index = 0; index < count; index++) {
			Arithmetic arithmetic = Arithmetic.createArithmetic();
			String msg = arithmetic.showQuestion();
			System.out.print(msg);
			int answer = inputNumber();
			arithmetic.setAnswer(answer);
			arithmetic.caculate(arithmetic.getOp());
			arithmetics.add(arithmetic);
			correct += arithmetic.getJudge();
		}
		return correct;
	}
	
	public static void showResult(ArrayList<Arithmetic> arithmetics) {
		for (int index = 0; index < arithmetics.size(); index++) {
			Arithmetic arithmetic = arithmetics.get(index);
			System.out.println(arithmetic);
		}
	}
	
	public static void showMenu(){
		showHeader();
		showMenuItem();
		showFooter();
	}
	
	public static void showSpace() {
		System.out.print("\t\t\t");
	}
	
	public static void showHeader() {
		showSpace();
		System.out.println("==========������ϰС��Ϸ==========v1.0");
		showSpace();
		System.out.println("=================================v1.0");
	}
	
	public static void showMenuItem() {
		showSpace();
		System.out.println("- ��������������������1");
		showSpace();
		System.out.println("- ��ʱ����������������2");
		showSpace();
		System.out.println("- ���档��������������3");
		showSpace();
		System.out.println("- ��ʾ����������������4");
		showSpace();
		System.out.println("- �˳�����������������5");
	}
	
	public static void showFooter() {
		showSpace();
		System.out.println("=================================");
		showSpace();
		System.out.print("- ������ѡ��(1-5): ");
	}
	
	public static int inputItem() throws IOException {
		int value ;
		value = inputNumber();
		return value;
	}
	
	public static int inputNumber() throws IOException {
		int value = 0;
		InputStream is = System.in;
		int ch;
		while ((ch = is.read()) != 13) {
			int digit = ch-48;
			if (ch >= 48 && ch <= 57) {
				value = value*10 + digit;
			}
		}
		return value;
	}
}
