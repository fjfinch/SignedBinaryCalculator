import java.util.Scanner;

public class SignedBinaryCalculator
{
	private static long decimal;
	private static int amountBits;

	public static void main(String[] args)
	{
		try(Scanner scanner = new Scanner(System.in))
		{
			while(true)
			{
				try
				{
					System.out.print("Decimal number:\t ");
					decimal = scanner.nextLong();
					break;
				}
				catch(java.util.InputMismatchException e)
				{
					System.out.println("Give a decimal within range [-2^63 - 2^63-1]");
					scanner.nextLine();
				}
			}
			
			System.out.print("Minimum bits:\t ");
			amountBits = scanner.nextInt();

			System.out.println();
			System.out.printf("%-20s%s\n",	"Unsigned", 		unsigned());
			System.out.printf("%-20s%s\n",	"Sign-Magnitude",	signMagnitude());
			System.out.printf("%-20s%s\n",	"One's Complement",	onesComplement());
			System.out.printf("%-20s%s\n",	"Two's Complement",	twosComplement());
			System.out.printf("%-20s%s\n",	"Offset Binary",	offsetBinary());
		}
	}

	public static String unsigned()
	{
		if(decimal<0)
		{
			return "-";
		}
		return addBits(Long.toBinaryString(decimal),'0');
	}

	public static String signMagnitude()
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
	
	public static String onesComplement()
	{
		if(decimal==0)
		{
			return addBits("0",'0')+" or "+addBits("1",'1');
		}
		else if(decimal>0)
		{
			return addBits('0'+Long.toBinaryString(decimal),'0');
		}
		StringBuilder binary = new StringBuilder(Long.toBinaryString(~Math.abs(decimal)));
		binary.delete(0,binary.length()-Long.toBinaryString(Math.abs(decimal)).length()-1);
		return addBits(binary.toString(),'1');
	}
	
	public static String twosComplement()
	{
		if(decimal==0)
		{
			return addBits("0",'0');
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

	public static String offsetBinary()
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