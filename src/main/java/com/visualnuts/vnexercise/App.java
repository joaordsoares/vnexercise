package com.visualnuts.vnexercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.visualnuts.vnexercise.model.Country;
import com.visualnuts.vnexercise.model.Lang;

public class App {
	public static void main( String[] args ){
		int start = 1;
		int end = 10000;
		//App.printSequence(start, end);

		String data = "[{\"country\":\"US\", \"languages\":[\"en\"]},"
				+ "{\"country\":\"BE\", \"languages\":[\"nl\", \"fr\", \"de\"]},"
				+ "{\"country\":\"NL\", \"languages\":[\"nl\", \"fy\"]},"
				+ "{\"country\":\"DE\", \"languages\":[\"de\"]},"
				+ "{\"country\":\"ES\", \"languages\":[\"es\"]}]";
		//Deserialize
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			List<Country> countries = Arrays.asList(mapper.readValue(data, Country[].class));
			
			System.out.println("# Countries: "+App.getNumCountries(countries));
			System.out.println("Country with most languages including 'de': "+ App.getCountryWithMostLangAndDE(countries, new Lang("de")));
			System.out.println("# Oficial Languages: " + App.getAllOfficialLanguages(countries));
			System.out.println("Country with most languages: " + App.getCountryWithMostLangAndDE(countries,null));
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//System.out.println("Country with most languages including 'de': "+ App.getCountryWithMostLangAndDE(array));
	}

	/*================================================================================
	 * NAME: getNumCountries
	 * PURPOSE: returns the number of countries in the world
	 *================================================================================*/
	public static int getNumCountries(List<Country> countries) {
		return countries==null ? 0 : countries.size();
	}
	/*================================================================================
	 * NAME: getCountryWithMostLangAndDE
	 * PURPOSE: finds the country with the most official languages with the possibility to
	 * 			filter the result only to countries where the language specified by param2
	 * 			is spoken. If param2 is <null> the result is not filtered.
	 *================================================================================*/
	public static Country getCountryWithMostLangAndDE(List<Country> countries, Lang containsLang) {
		Country mostLangCountry = null;
		for(Country country : countries) {
			if(mostLangCountry == null)
				mostLangCountry = country;
			
			if(containsLang != null && country.getLanguages().contains(containsLang)) {
				if(country.getLanguages().size() > mostLangCountry.getLanguages().size())
					mostLangCountry = country;
			}else {
				if(country.getLanguages().size() > mostLangCountry.getLanguages().size())
					mostLangCountry = country;
			}
		}
		
		return mostLangCountry;
	}
	/*================================================================================
	 * NAME: getAllOfficialLanguages
	 * PURPOSE: that counts all the official languages spoken in the listed countries.
	 *================================================================================*/
	public static long getAllOfficialLanguages(List<Country> countries){
		List<Lang> languages = new ArrayList<Lang>();
		for(Country country : countries) {
			languages.addAll(country.getLanguages());
		}
		
		return languages.stream().distinct().count();
		//If we want to return wich languages are
		//return languages.stream().distinct().collect(Collectors.toList());
	}

	
	/*================================================================================
	 * NAME: getCountryWithMostLangAndDE
	 * PURPOSE: to find the most common official language(s), of all countries.
	 *================================================================================*/
	
	
	/*
	- returns the number of countries in the world
	- finds the country with the most official languages, where they officially speak German (de).
	- that counts all the official languages spoken in the listed countries.
	- to find the country with the highest number of official languages.
	- to find the most common official language(s), of all countries.
	 * 
	 */
	public static void printSequence(int start, int end) {
		final long startTime = System.currentTimeMillis();

		for(int i=start; i<=end; i++) {

			if(i%3 == 0) {	
				System.out.print("Visual");
				if(i%5 == 0)
					System.out.print(" Nuts");
				System.out.println();
				continue;
			}else if(i%5 == 0)
				System.out.println("Nuts");
			else
				System.out.println(i);
		}
		final long endTime = System.currentTimeMillis();
		System.out.println("Time: "+(endTime - startTime));
	}
}
