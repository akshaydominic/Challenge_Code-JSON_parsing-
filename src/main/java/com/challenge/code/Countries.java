package com.challenge.code;

import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown=true)
public class Countries {
	
	String name;
	String capital;
	long population;
	Double[] latlng;
	
	public Double[] getLatlng() {
		return latlng;
	}
	public void setLatlng(Double[] latlng) {
		this.latlng = latlng;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public long getPopulation() {
		return population;
	}
	public void setPopulation(long population) {
		this.population = population;
	}
	
	public Countries(String name, String capital, long population, Double[] latlng) {
		this.name = name;
		this.capital = capital;
		this.population = population;
		this.latlng = latlng;
	}
	public Countries() {
	}
	
	@Override
	public String toString() {
		return "Countries [name=" + name + ", capital=" + capital + ", population=" + population + ", latlng="
				+ Arrays.toString(latlng) + "]";
	}
	
	
	
	
	
	
}