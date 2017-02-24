package mypack.project;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
/**----To use ZIP compress technique----*/

public class ZipFormat 
{
/**----To compress given set of files----*/
	public void compress(String files[])
	{
		FileInputStream fis = null;
		FileOutputStream fos = null;
		ZipOutputStream zos =  null;
		
		try
		{
			System.out.println("\n\t\t----Compression Started----");
			fos = new FileOutputStream(files[0]+".zip");
			zos = new ZipOutputStream( new BufferedOutputStream(fos));
			for(String filePath : files)
			{
				File f = new File(filePath);
				fis = new FileInputStream(f);
				ZipEntry  ze = new ZipEntry(f.getName());
				System.out.println("\tZipping file : "+f.getName());
				zos.putNextEntry(ze);
				byte[] tmp = new byte[4*1024];
	                	int size = 0;
				while((size = fis.read(tmp)) != -1)
				{
					zos.write(tmp, 0, size);
				}
				zos.flush();
				fis.close();
			}
			zos.close();
			System.out.println("\n\t\t----Compression Successful----");
		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("\tError : File(s) Missing");
   		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(fos!= null)
					fos.close();
			}
			catch(Exception ex)
			{
			}
		}
	}
	
/**----To Decompress given compressed file----*/
	public void decompress(String filePath)
	{
		FileInputStream fisnew = null;
		ZipInputStream zipIs = null;
		ZipEntry zEntry = null;
		try
		{
			fisnew = new FileInputStream(filePath);
			zipIs = new ZipInputStream(new BufferedInputStream(fisnew));
			System.out.println("\n\t\t----Decompression Started----");
			while((zEntry = zipIs.getNextEntry()) != null)
			{
			
				try
				{
					byte[] tmp = new byte[4*1024];
					FileOutputStream fosnew = null;
					String opFilePath = zEntry.getName();
					System.out.println("\tExtracting file : "+opFilePath);
					fosnew = new FileOutputStream(opFilePath);
					int size = 0;
					while((size = zipIs.read(tmp)) != -1)
					{
						fosnew.write(tmp, 0 , size);
					}
				fosnew.flush();
				fosnew.close();
				}
				catch(Exception ex)
				{
				}
			}
			zipIs.close();
			System.out.println("\n\t\t----Decompression Successful----");
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("\tError : File Missing");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		catch (IOException e) 
 		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

