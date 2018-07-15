package com;

public class Arithmetic {
	private int left;
	private int right;
	private int op;
	private int answer;
	
	private int judge = 0;
	
	public Arithmetic(int left, int right, int op) {
		this.left = left;
		this.right = right;
		this.op = op;
	}

	public static Arithmetic createArithmetic() {
		int left = (int)(Math.random() * 90) + 10;
		int right = (int)(Math.random() * 90) + 10;
		int op = (int)(Math.random() * 4);
		if(op == 1 || op == 3) {
			if(left < right) {
				int temp = left;
				left = right;
				right = temp;
			}
			if(op == 3) {
				left = left - left % right;
			}
		}
		return new Arithmetic(left,right, op);
	}

	public static String convert(int op) {
		String ch = " + ";
		switch(op) {
		case 0:
			ch = " + ";
			break;
		case 1:
			ch = " - ";
			break;
		case 2:
			ch = " * ";
			break;
		case 3:
			ch = " / ";
			break;
		}
		return ch;
	}
	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
		
	}

	public int getLeft() {
		return left;
	}

	public int getRight() {
		return right;
	}

	public int getOp() {
		return op;
	}

	public int getJudge() {
		return judge;
	}
	
	public int caculate(int op) {
		int answer = this.getAnswer();
		switch(op) {
		case 0:
			if(left + right == answer) {
				judge = 1;
			}
			break;
		case 1:
			if(left - right == answer) {
				judge = 1;
			}
			break;
		case 2:
			if(left * right == answer) {
				judge = 1;
			}
			break;
		case 3:
			if(left / right == answer) {
				judge = 1;
			}
			break;
		}
		return judge;
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String msg = left + convert(op) + right + " = " + answer;
		if(judge == 1) {
			msg += "\tÕýÈ·";
		}else {
			msg += "\t´íÎó";
		}
		return msg;
	}
	
	public String showQuestion() {
		String msg = left + convert(op) + right + " = ";
		return msg;
	}

}
