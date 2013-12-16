package it.alessio.eliminacode.common.model;

public class ItalianMonthConverter {
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

}
