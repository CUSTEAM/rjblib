package model;

import java.util.Date;

public class WeatherHist {
	private Date ftime;
	private float temp_c,feelslike_c, precip_mm, wind_kph;
	private int humidity, cloud, chance_of_rain;
	private String wind_dir, code, text, icon;
	public Date getFtime() {
		return ftime;
	}
	public void setFtime(Date ftime) {
		this.ftime = ftime;
	}
	public float getWind_kph() {
		return wind_kph;
	}
	public void setWind_kph(float wind_kph) {
		this.wind_kph = wind_kph;
	}
	public float getTemp_c() {
		return temp_c;
	}
	public void setTemp_c(float temp_c) {
		this.temp_c = temp_c;
	}
	public float getFeelslike_c() {
		return feelslike_c;
	}
	public void setFeelslike_c(float feelslike_c) {
		this.feelslike_c = feelslike_c;
	}
	public float getPrecip_mm() {
		return precip_mm;
	}
	public void setPrecip_mm(float precip_mm) {
		this.precip_mm = precip_mm;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public int getCloud() {
		return cloud;
	}
	public void setCloud(int cloud) {
		this.cloud = cloud;
	}
	public int getChance_of_rain() {
		return chance_of_rain;
	}
	public void setChance_of_rain(int chance_of_rain) {
		this.chance_of_rain = chance_of_rain;
	}
	public String getWind_dir() {
		return wind_dir;
	}
	public void setWind_dir(String wind_dir) {
		this.wind_dir = wind_dir;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

}
