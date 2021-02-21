package com.WeatherApplication.Service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherService {
	private OkHttpClient client;
	private Response response;
	private String cityName;
	private String unit;
	private String APIkey = "1e2251421888e962483847c3e0621a49";

	// Getting Data from OpenWeather API
	
	public JSONObject getWeather() {
		client = new OkHttpClient();
		Request request = new Request.Builder().url("http://api.openweathermap.org/data/2.5/weather?q=" + getCityName()
				+ "&units=" + getUnit() + "&appid=" + APIkey).build();

		try {
			response = client.newCall(request).execute();
			return new JSONObject(response.body().string());
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Getting data from Weather JSON API JSON Objects and JSON Arrays
	
	public JSONArray returnWeatherArray() throws JSONException {
		JSONArray weatherJsonArray = getWeather().getJSONArray("weather");
		return weatherJsonArray;
	}

	public JSONObject returnMainObject() throws JSONException {
		JSONObject mainObject = getWeather().getJSONObject("main");
		return mainObject;
	}

	public JSONObject returnWindObject() throws JSONException {
		JSONObject wind = getWeather().getJSONObject("wind");
		return wind;
	}

	public JSONObject returnSysObject() throws JSONException {
		JSONObject sys = getWeather().getJSONObject("sys");
		return sys;
	}

	// Getters and Setters for CityName and Unit

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
