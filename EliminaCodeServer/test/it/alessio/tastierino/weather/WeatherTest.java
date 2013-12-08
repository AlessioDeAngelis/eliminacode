package it.alessio.tastierino.weather;

import it.alessio.tabellone.weather.WeatherWatcher;

import java.util.Map;

public class WeatherTest {
	public static void main(String[] args) {

		Map<String, String> weather = WeatherWatcher.getWeather(41.442726, 12.392578);

		System.out.println("weather " + weather.get("weather") + " temperature " + weather.get("temperature"));
	}

}