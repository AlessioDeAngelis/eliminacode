package it.alessio.eliminacode.common.model;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class ItalianEnglishMonthConverter {
	public static String fromNumberToMonth(int number) {
		String month = "";
		switch (number) {
		case 0:
			month = "Gennaio";
			break;
		case 1:
			month = "Febbraio";
			break;
		case 2:
			month = "Marzo";
			break;
		case 3:
			month = "Aprile";
			break;
		case 4:
			month = "Maggio";
			break;
		case 5:
			month = "Giugno";
			break;
		case 6:
			month = "Luglio";
			break;
		case 7:
			month = "Agosto";
			break;
		case 8:
			month = "Settembre";
			break;
		case 9:
			month = "Ottobre";
			break;
		case 10:
			month = "Novembre";
			break;
		case 11:
			month = "Dicembre";
			break;
		default:
			month = "Gennaio";
			break;
		}

		return month;
	}

	public static int fromMonthToNumber(String month) {
		int number = 0;
		switch (month) {
		case "Gennaio":
			number = 0;
			break;
		case "Febbraio":
			number = 1;
			break;
		case "Marzo":
			number = 2;
			break;
		case "Aprile":
			number = 3;
			break;
		case "Maggio":
			number = 4;
			break;
		case "Giugno":
			number = 5;
			break;
		case "Luglio":
			number = 6;
			break;
		case "Agosto":
			number = 7;
			break;
		case "Settembre":
			number = 8;
			break;
		case "Ottobre":
			number = 9;
			break;
		case "Novembre":
			number = 10;
			break;
		case "Dicembre":
			number = 11;
			break;
		default:
			number = 11;
			break;
		}

		return number;
	}
	
	/**
	 * the english name are just with the first 3 letters since there are displayed this way n the date object
	 * */
	public static String english2italianMonth(String month, String language){
		String translatedMonth = "";
		BiMap<String,String> english2italian = HashBiMap.create();
		english2italian.put("Jan", "Gennaio");
		english2italian.put("Feb", "Febbraio");
		english2italian.put("Mar", "Marzo");
		english2italian.put("Apr", "Aprile");
		english2italian.put("May", "Maggio");
		english2italian.put("Jun", "Giugno");
		english2italian.put("Jul", "Luglio");
		english2italian.put("Aug", "Agosto");
		english2italian.put("Sep", "Settembre");
		english2italian.put("Oct", "Ottobre");
		english2italian.put("Nov", "Novembre");
		english2italian.put("Dec", "Dicembre");
		
		if(language.equals("english")){
			translatedMonth = english2italian.get(month);
		}else{
			translatedMonth = english2italian.inverse().get(month);
		}
		english2italian.clear();//we don't need it anymore
		return translatedMonth;
	}
	
	/**
	 * the english name are just with the first 3 letters since there are displayed this way n the date object
	 * */
	public static String english2italianDay(String day, String language){
		String translatedMonth = "";
		BiMap<String,String> english2italian = HashBiMap.create();
		english2italian.put("Mon", "Lunedì");
		english2italian.put("Tue", "Martedì");
		english2italian.put("Wed", "Mercoledì");
		english2italian.put("Thu", "Giovedì");
		english2italian.put("Fri", "Venerdì");
		english2italian.put("Sat", "Sabato");
		english2italian.put("Sun", "Domenica");
				
		if(language.equals("english")){
			translatedMonth = english2italian.get(day);
		}else{
			translatedMonth = english2italian.inverse().get(day);
		}
		english2italian.clear();//we don't need it anymore
		return translatedMonth;
	}

}
