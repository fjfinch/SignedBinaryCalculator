package fun;

import java.util.Scanner;

public class SignedBinaryCalculator 
{
	static int amountBits = 8;
	
	public static void main(String[] args) 
	{
		try(Scanner scanner = new Scanner(System.in))
		{
			System.out.print("Gimme number:\t");
			long decimal = scanner.nextLong();
			
			System.out.print("Amount bits:\t");
			amountBits = scanner.nextInt();

			System.out.println();
			System.out.printf("%-20s%s\n",	"Unsigned", 		unsigned(decimal));
			System.out.printf("%-20s%s\n",	"Sign-Magnitude",	signMagnitude(decimal));
			System.out.printf("%-20s%s\n",	"One's complement",	onesComplement(decimal));
			System.out.printf("%-20s%s\n",	"Two's complement",	twosComplement(decimal));
			System.out.printf("%-20s%s\n",	"Offset Binary",	offsetBinary(decimal));
		}
	}

	public static String unsigned(long decimal)
	{
		if(decimal<0)
		{
			return "-";
		}
		return addBits(Long.toBinaryString(decimal),'0');
	}
	
	public static String signMagnitude(long decimal)
	{
		if(decimal==0)
		{
			String binary = addBits("00",'0');
			return binary+" or "+binary.replaceFirst("0","1");
		}
		StringBuilder binary = new StringBuilder(Long.toBinaryString(Math.abs(decimal))); 
		while(binary.length()<amountBits-1)
		{
			binary.insert(0,'0');
		}
		char msb = decimal<0 ? '1' : '0';
		return msb+binary.toString();
	}
	
	public static String onesComplement(long decimal)
	{
		if(decimal==0)
		{
			return addBits("00",'0')+" or "+addBits("11",'1');
		}
		else if(decimal>0)
		{
			return addBits('0'+Long.toBinaryString(decimal),'0');
		}
		StringBuilder binary = new StringBuilder(Long.toBinaryString(~Math.abs(decimal)));
		binary.delete(0,binary.length()-Long.toBinaryString(Math.abs(decimal)).length()-1);
		return addBits(binary.toString(),'1');
	}
	
	public static String twosComplement(long decimal)
	{
		if(decimal==0)
		{
			return addBits("00",'0');
		}
		else if(decimal>0)
		{
			return addBits('0'+Long.toBinaryString(decimal),'0');
		}
		StringBuilder binary = new StringBuilder(Long.toBinaryString(decimal));
		binary.delete(0,binary.length()-Long.toBinaryString(Math.abs(decimal)).length()-1);
		if(binary.charAt(0)=='1'&&binary.charAt(1)=='1')
		{
			binary.delete(0,1);
		}
		return addBits(binary.toString(),'1');
	}

	public static String offsetBinary(long decimal)
	{
		if(amountBits>=64) //LONG RANGE -> 63bits
		{
			return "Overflow";
		}
		long k = (long)Math.pow(2,amountBits-1);
		if(decimal<-k || decimal>k-1)
		{
			return "-";
		}
		return addBits(Long.toBinaryString(decimal+k),'0');
	}
	
	public static String addBits(String binary, char bit)
	{
		StringBuilder binaryNew = new StringBuilder(binary);
		while(binaryNew.length()<amountBits)
		{
			binaryNew.insert(0,bit);
		}
		return binaryNew.toString();
	}
}