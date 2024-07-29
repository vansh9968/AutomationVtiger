package Api_Restassured;

import org.apache.commons.lang3.RandomUtils;

public class Random_Class {
	
	public static void main(String[] args) {
		
	int a=7+RandomUtils.nextInt();
	System.out.println(a);
	long b=RandomUtils.nextLong();
	System.out.println(b);
	Boolean c= RandomUtils.nextBoolean();
	System.out.println(c);
	int d=RandomUtils.nextInt(1, 4);
	System.out.println(d);
		
	}

}
