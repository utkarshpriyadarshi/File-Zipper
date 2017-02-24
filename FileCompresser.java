//Minor Project : File Compressioning and decompressing using java
import java.util.Scanner;
import java.lang.String;
import java.io.File;
import mypack.project.ZipFormat;
/**----Main function class----*/
class FileCompresser
{
	/**----To check name avialabilty----*/
	public static String checkFileName(String fileName)
	{
		int i;
		File temp = new File(fileName);
		for(i=0;temp.exists();i++)
			temp = new File(fileName+String.valueOf(i));
		fileName=fileName+String.valueOf(i);
		return fileName;
	}		
	/**----File operation input method----*/
	public static  String getOperation()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("\tOperation : Compress or Decompress\n\tEnter Operation : ");
		return sc.nextLine();
	}
	/**----Compressing File input method----*/
	public static  String getFileName()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("\tNote : Use comma ( , ) to use separate files.");
		System.out.print("\tEnter file(s) name :  ");
		return sc.nextLine();
	}
	/**----Compressed file name input method for decompressing----*/
	public static String getZippedFileName()
	{
		System.out.print("\tEnter the Zip file name : ");
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}
	/**----To determine the files intended for compressing----*/
	public static String[] extractFileNames(String fileInputLine)
	{
		String fileNameArray[];
		String tempArray[]=fileInputLine.split(",");
		for(int i=0;i<tempArray.length;i++)
		{
			tempArray[i]=tempArray[i].trim();
		}
		return tempArray;
	}
	/**----To determine the compressed technique used for compressing----*/
	public static  String checkFileType(String fileName)
	{
		if(fileName.endsWith(".zip"))
			return "zip";
		else
			return "\tError : File type NOT SUPPORTED";
	}		
	/**----Main Method----*/	
	public static void main(String args[])
	{
		try
		{
			System.out.println("\t\t----Program Starts----");
			ZipFormat zf = new ZipFormat();
			
			String op=getOperation();
			
			switch(op)
			{
				case "COMPRESS":
				case "Compress":
				case "compress":
								zf.compress(extractFileNames(getFileName()));
								break;
				case "DECOMPRESS":
				case "Decompress":
				case "decompress":
						String zippedFile = getZippedFileName();
						if(checkFileType(zippedFile)=="zip")
							zf.decompress(zippedFile);
						else
							System.out.println(checkFileType(zippedFile));
						break;
				default: System.out.println("\tError : Operation INVALID");
			}
		}
		catch(Exception e)
		{
		
		}
		finally
		{
			System.out.println("\t\t----Program ends----");		
		}
		
	}
}
