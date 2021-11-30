package com.visualnuts.vnexercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.visualnuts.vnexercise.model.Country;
import com.visualnuts.vnexercise.model.Lang;

public class App {
	public static void main( String[] args ){

		//EXERCISE 1
		int start = 1;
		int end = 10000;
		App.printSequence(start, end);

		//EXERCISE 2
		String json = "[{\"country\":\"US\", \"languages\":[\"en\"]},"
				+ "{\"country\":\"BE\", \"languages\":[\"nl\", \"fr\", \"de\"]},"
				+ "{\"country\":\"KK\", \"languages\":[\"nl\", \"fr\", \"fy\", \"es\"]},"
				+ "{\"country\":\"NL\", \"languages\":[\"nl\", \"fy\"]},"
				+ "{\"country\":\"DE\", \"languages\":[\"de\"]},"
				+ "{\"country\":\"ES\", \"languages\":[\"es\"]}]";

		//Deserealize
		List<Country> countries = App.deserialize(json);

		System.out.println("# Countries: "+App.getNumCountries(countries));
		System.out.println("Country with most languages including 'de': "+ App.getCountryWithMostLang(countries, new Lang("de")));
		System.out.println("# Oficial Languages: " + App.getAllOfficialLanguages(countries));
		System.out.println("Country with most languages: " + App.getCountryWithMostLang(countries, null));
		System.out.println("Most common language: " + App.getMostCommonLanguage(countries));


	}

	//EXERCISE 2
	/*================================================================================
	 * NAME: deserialize
	 * PURPOSE: deserialize json
	 *================================================================================*/
	public static List<Country> deserialize(String json){
		ObjectMapper mapper = new ObjectMapper();
		List<Country> countries = null;
		try {
			countries = Arrays.asList(mapper.readValue(json, Country[].class));
		} catch (JsonProcessingException e) {
			System.out.println("Error trying to process JSON");
		}
		return countries;
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
	public static Country getCountryWithMostLang(List<Country> countries, Lang containsLang) {
		if(!isValidList(countries))
			return null;

		Country mostLangCountry = null;
		for(Country country : countries) {
			//first iteration
			if(mostLangCountry == null)
				mostLangCountry = country;
			//if a Lang was a parameter, check if it exists in the current country
			if(containsLang != null) {
				if(country.getLanguages().contains(containsLang) && country.getLanguages().size() > mostLangCountry.getLanguages().size())
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
		if(!isValidList(countries))
			return 0;

		List<Lang> languages = new ArrayList<Lang>();
		//Create List with all languages of each country
		for(Country country : countries) {
			languages.addAll(country.getLanguages());
		}

		//return the count of only distinct languages
		return languages.stream().distinct().count();
		//If we want to return wich languages are
		//return languages.stream().distinct().collect(Collectors.toList());
	}


	/*================================================================================
	 * NAME: getMostCommonLanguage
	 * PURPOSE: to find the most common official language(s), of all countries.
	 *================================================================================*/
	public static List<Lang> getMostCommonLanguage(List<Country> countries) {
		if(!isValidList(countries))
			return null;

		//Fill hashmap with distinct languages and their respective count in the list
		HashMap<Lang, Integer> langCountMap = new HashMap<Lang, Integer>();
		for(Country country : countries) {
			for(Lang lang : country.getLanguages()) {
				if(!langCountMap.containsKey(lang))
					langCountMap.put(lang, new Integer(1));
				else {
					langCountMap.merge(lang, 1, Integer::sum);
				}

			}
		}
		List<Lang> languages = new ArrayList<Lang>();
		//Find the language with the highest value on the map
		int max = Collections.max(langCountMap.values());
		//Find the language(s) with max appearances
		for (Entry<Lang, Integer> entry : langCountMap.entrySet()) {
			if (entry.getValue()==max) {
				languages.add(entry.getKey());
			}
		}
		return languages;
	}

	/*================================================================================
	 * NAME: isValidList
	 * PURPOSE: check if list is null or empty
	 *================================================================================*/
	public static boolean isValidList(List<Country> countries) {
		if(countries == null || countries.isEmpty())
			return false;

		return true;
	}

	//EXERCISE 1
	public static void printSequence(int start, int end) {

		for(int i=start; i<=end; i++) {

			if(i%3 == 0) {	
				System.out.print("Visual");
				if(i%5 == 0)
					System.out.print(" Nuts");
				System.out.println();
			}else if(i%5 == 0)
				System.out.println("Nuts");
			else
				System.out.println(i);
		}
	}
}
