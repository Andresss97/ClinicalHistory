package statistics;

import java.util.ArrayList;

public class StatsAppointments {
	private int nine;
	private int nine30;
	private int ten;
	private int ten30;
	private int eleven;
	private int eleven30;
	private int twelve;
	private int twelve30;
	private int one;
	private int one30;
	private int two;
	private int two30;
	private int three;
	private int three30;
	private int four;
	private int four30;
	private int five;
	private int five30;
	private int six;
	
	public int getNine() {
		return nine;
	}
	
	public void setNine(int nine) {
		this.nine = nine;
	}
	
	public int getNine30() {
		return nine30;
	}
	
	public void setNine30(int nine30) {
		this.nine30 = nine30;
	}
	
	public int getTen() {
		return ten;
	}
	
	public void setTen(int ten) {
		this.ten = ten;
	}
	
	public int getTen30() {
		return ten30;
	}
	
	public void setTen30(int ten30) {
		this.ten30 = ten30;
	}
	
	public int getEleven() {
		return eleven;
	}
	
	public void setEleven(int eleven) {
		this.eleven = eleven;
	}
	
	public int getEleven30() {
		return eleven30;
	}
	
	public void setEleven30(int eleven30) {
		this.eleven30 = eleven30;
	}
	
	public int getTwelve() {
		return twelve;
	}
	
	public void setTwelve(int twelve) {
		this.twelve = twelve;
	}
	
	public int getTwelve30() {
		return twelve30;
	}
	
	public void setTwelve30(int twelve30) {
		this.twelve30 = twelve30;
	}
	
	public int getOne() {
		return one;
	}
	
	public void setOne(int one) {
		this.one = one;
	}
	
	public int getOne30() {
		return one30;
	}
	
	public void setOne30(int one30) {
		this.one30 = one30;
	}
	
	public int getTwo() {
		return two;
	}
	
	public void setTwo(int two) {
		this.two = two;
	}
	
	public int getTwo30() {
		return two30;
	}
	
	public void setTwo30(int two30) {
		this.two30 = two30;
	}
	
	public int getThree() {
		return three;
	}
	
	public void setThree(int three) {
		this.three = three;
	}
	
	public int getThree30() {
		return three30;
	}
	
	public void setThree30(int three30) {
		this.three30 = three30;
	}
	
	public int getFour() {
		return four;
	}
	
	public void setFour(int four) {
		this.four = four;
	}
	
	public int getFour30() {
		return four30;
	}
	
	public void setFour30(int four30) {
		this.four30 = four30;
	}
	
	public int getFive() {
		return five;
	}
	
	public void setFive(int five) {
		this.five = five;
	}
	
	public int getFive30() {
		return five30;
	}
	
	public void setFive30(int five30) {
		this.five30 = five30;
	}
	
	public int getSix() {
		return six;
	}
	
	public void setSix(int six) {
		this.six = six;
	}
	
	public void generateStats(ArrayList<String> app) {
		for(int i = 0; i < app.size(); i++) {
			switch (app.get(i)) {
				case "9:00" : {
					this.nine++;
					break;
				}
				case "9:30" : {
					this.nine30++;
					break;
				}
				case "10:00" : {
					this.ten++;
					break;
				}
				case "10:30" : {
					this.ten30++;
					break;
				}
				case "11:00" : {
					this.eleven++;
					break;
				}
				case "11:30" : {
					this.eleven30++;
					break;
				}
				case "12:00" : {
					this.twelve++;
					break;
				}
				case "12:30" : {
					this.twelve30++;
					break;
				}
				case "13:00" : {
					this.one++;
					break;
				}
				case "13:30" : {
					this.one30++;
					break;
				}
				case "14:00" : {
					this.two++;
					break;
				}
				case "14:30" : {
					this.two30++;
					break;
				}
				case "15:00" : {
					this.three++;
					break;
				}
				case "15:30" : {
					this.three30++;
					break;
				}
				case "16:00" : {
					this.four++;
					break;
				}
				case "16:30" : {
					this.four30++;
					break;
				}
				case "17:00" : {
					this.five++;
					break;
				}
				case "17:30" : {
					this.five30++;
					break;
				}
				case "18:00" : {
					this.six++;
					break;
				}
				
				default : break;
			}
		}
	}
}
