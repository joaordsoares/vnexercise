package com.visualnuts.vnexercise;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.visualnuts.vnexercise.model.Country;
import com.visualnuts.vnexercise.model.Lang;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	String json = "[{\"country\":\"US\", \"languages\":[\"en\"]},"
			+ "{\"country\":\"BE\", \"languages\":[\"nl\", \"fr\", \"de\"]},"
			+ "{\"country\":\"KK\", \"languages\":[\"nl\", \"fr\", \"fy\", \"es\"]},"
			+ "{\"country\":\"NL\", \"languages\":[\"nl\", \"fy\"]},"
			+ "{\"country\":\"DE\", \"languages\":[\"de\"]},"
			+ "{\"country\":\"ES\", \"languages\":[\"es\"]}]";
	
    @Test
    public void testGetNumCountries()
    {
    	List<Country> countries = App.deserialize(json);
        int numCountries = App.getNumCountries(countries);
        assertEquals(6, numCountries);
    }
    
    @Test
    public void testGetCountryWithMostLangAndDE() {
    	List<Country> countries = App.deserialize(json);
    	Country expectedCountry = new Country("BE", new ArrayList<Lang>(Arrays.asList(new Lang("nl"), new Lang("fr"), new Lang("de"))));
    	Country country = App.getCountryWithMostLang(countries, new Lang("de"));
    	assertEquals(expectedCountry, country);
    }
    
    @Test
    public void testGetCountryWithMostLang() {
    	List<Country> countries = App.deserialize(json);
    	Country expectedCountry = new Country("KK", new ArrayList<Lang>(Arrays.asList(new Lang("nl"), new Lang("fr"), new Lang("fy"), new Lang("es"))));
    	Country country = App.getCountryWithMostLang(countries, null);
    	assertEquals(expectedCountry, country);
    }
    
    
    @Test
    public void testGetAllOfficialLanguages() {
    	List<Country> countries = App.deserialize(json);
    	long allOfficialLang = App.getAllOfficialLanguages(countries);
    	assertEquals(6, allOfficialLang);
    }
    
    @Test
    public void testGetMostCommonLanguage() {
    	List<Country> countries = App.deserialize(json);
    	List<Lang> mostCommonLang = App.getMostCommonLanguage(countries);
    	List<Lang> expected = new ArrayList<Lang>(Arrays.asList(new Lang("nl")));
    	assertEquals(expected, mostCommonLang);
    }
    
}
