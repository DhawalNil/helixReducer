package com.infy.reducer.dataconverter;

import java.io.File;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.reducer.entity.Person;

@Service
public class DataConverterImpl implements DataConverter<Person>  {
	public static final ObjectMapper objectMapper = new ObjectMapper() ;
	
	public Person jsonToJavaObject(String jsonData) throws Exception{	
		return objectMapper.readValue(jsonData, Person.class) ;	
	}
	public String javaObjectToJson(Person person) throws Exception{
		System.out.println(person) ;
		objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/check.json"), person);
		return objectMapper.writeValueAsString(person) ;
	}
}
