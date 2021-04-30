package com.challenge.code;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Precision;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {
	private static List<Double> lattitude = new ArrayList<>();
	private static List<Double> longitude = new ArrayList<>();
	private static List<Double>Result= new ArrayList<>();
	
	private static double population=65110000;
	private static int noOfCountries = 20;
	private static int i=0,j=0;
	private static double solution=0;
	private static double lat1,long1,lat2,long2;
	

	public static void main(String[] args) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true);
			// read JSON file and map/convert to Java POJO:
			List<Countries> myObjects = mapper.readValue(new File("data/countriesV2.json"),
					new TypeReference<List<Countries>>() {
					});
			List<Countries> top_countries = new ArrayList<>();

			// choosing the 20 countries according to the population
			for (Countries country : myObjects) {
				if (country.getPopulation() >= population && i < noOfCountries) {
					top_countries.add(country);
					i++;
				}
			}

			// Selecting the latitude , longitude of the 20 countries
			for (Countries count : top_countries) {
				j = 0;
				for (Double d : count.getLatlng()) {
					if (j == 0) {
						lattitude.add(d);
					} else {
						longitude.add(d);
					}
					j++;
				}
			}

			for (i = 0, j = 1; i < lattitude.size(); i++, j++) {
				// condition - checking for array is not out of bound
				if (j < lattitude.size()) {
					lat1 = lattitude.get(i);
					long1 = longitude.get(i);
					lat2 = lattitude.get(j);
					long2 = longitude.get(j);
					// Call to Haversine method
					double result1 = compute_distance(lat1, long1, lat2, long2);
					// Rounding the line distance 2 decimal points for each value calculated
					Result.add(Precision.round(result1, 2));
				} else {
					break;
				}

			}
			// Ignore it !{printing to console to test}
//		System.out.println("Result set ");
//		int counter=0;
//		for(double values:Result) {
//			System.out.println(counter+" :"+"Distance: "+values);
//			counter++;
//		}
//		System.out.println("-----------------------");

			for (double b : Result) {
				solution += b;
			}
			// Rounding the solution to 2 decimal points
			System.out.println("the solution is " + Precision.round(solution, 2));

//		Ignore it !{printing to console to test}
//		System.out.println("---------------------------");
//		for( i=0;i<top_countries.size();i++) {
//			Countries co = top_countries.get(i);
//			System.out.print("Name "+top_countries.get(i).getName()+"| Captial: "+top_countries.get(i).getCapital()
//					+"| Population:  "+top_countries.get(i).getPopulation());
//			for(Double h : co.getLatlng()) {
//				System.out.print("| LatitudeLongitude:  "+h);
//			}
//			System.out.println();
//		}
//		for(double latt:lattitude) {
//			System.out.println("lat:"+latt);
//		}
//		for(double longg:longitude) {
//			System.out.println("long :"+longg);
//			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	// Haversine formula to calculate distance
	public static double compute_distance(double lat1, double long1, double lat2, double long2) {
		long1 = Math.toRadians(long1);
		long2 = Math.toRadians(long2);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);
		double dlon = long2 - long1;
		double dlat = lat2 - lat1;
		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
		double c = 2 * Math.asin(Math.sqrt(a));
		double r = 6371;
		return (c * r);

	}

	// Ignore it !{For testing 2 dimensions}
	// calculate distance cartesian
	public static double calDis(double x1, double y1, double x2, double y2) {
		return (Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)));
	}
}
