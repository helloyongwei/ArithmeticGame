package com;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.Arithmetic;

public class Main {
	public static void main(String[] args) throws IOException {
	
		while (true) {
			showMenu();
			int value = inputItem();
			switch (value) {
			case 1: 
				quantity();
				break;
			case 2:
				timing();
				break;
			}
		}
		
		
			
	}
	
	public static void timing() throws IOException {
		
		ArrayList<Arithmetic> arithmetics = new ArrayList<Arithmetic>();
		System.out.print("Please input a time: ");
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
		System.out.print("Please input a number: ");
		count = inputNumber();
		int correct = computeByQuantity(arithmetics, count);
		showResult(arithmetics);
		System.out.println("The score is : " + (100* (double)correct/count));
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
		System.out.println("==========速算练习小游戏==========v1.0");
		showSpace();
		System.out.println("=================================v1.0");
	}
	
	public static void showMenuItem() {
		showSpace();
		System.out.println("- 定量。。。。。。。。1");
		showSpace();
		System.out.println("- 定时。。。。。。。。2");
		showSpace();
		System.out.println("- 保存。。。。。。。。3");
		showSpace();
		System.out.println("- 显示。。。。。。。。4");
		showSpace();
		System.out.println("- 退出。。。。。。。。5");
	}
	
	public static void showFooter() {
		showSpace();
		System.out.println("=================================");
		showSpace();
		System.out.print("- 请输入选项(1-5): ");
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
