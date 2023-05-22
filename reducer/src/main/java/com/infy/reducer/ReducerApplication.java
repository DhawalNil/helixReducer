package com.infy.reducer;



import org.springframework.boot.SpringApplication;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.infy.reducer.datacompressor.DataCompressor;
import com.infy.reducer.datacompressor.DataCompressorImpl;
import com.infy.reducer.dataconverter.DataConverter;
import com.infy.reducer.dataconverter.DataConverterImpl;
import com.infy.reducer.entity.Person;
import com.infy.reducer.file.CompressedFile;

@SpringBootApplication
public class ReducerApplication {
	
	
	public static void main(String[] args) throws Exception {
		
		
		SpringApplication.run(ReducerApplication.class, args);
		String file = "src/main/resources/Person.json" ;
		String json = readFileAsString(file) ;
		
		DataConverter<Person> dataConverter = new DataConverterImpl() ;
		DataCompressor<Person> dataCompressor = new DataCompressorImpl() ;
		
		try {
			
			Person person = dataConverter.jsonToJavaObject(json) ;
			System.out.println(person) ;
			
			
			byte[] compressedData = dataCompressor.compress(person) ;
			
//			for(int i = 0 ; i<compressedData.length; i++)
//			{
//				System.out.print(compressedData[i] + " ");
//			}
//			System.out.println();
			
			CompressedFile.bytetoFile(compressedData) ;
			
//			for(int i = 0 ; i<compressedData.length; i++)
//			{
//				System.out.print(compressedData[i] + " ");
//			}
//			System.out.println();
			
			Person decompressedPerson = dataCompressor.decompress(compressedData) ;
			
			String s = dataConverter.javaObjectToJson(decompressedPerson) ;
			
			System.out.println(s) ;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String readFileAsString(String file) throws Exception{	
		return new String(Files.readAllBytes(Paths.get(file))) ;
	}

}
