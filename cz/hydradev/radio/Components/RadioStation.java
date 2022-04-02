package cz.hydradev.radio.Components;

public class RadioStation {

	private String stationName, stationUrl;
	
	public RadioStation(String stationName, String stationUrl) {
		this.stationName = stationName;
		this.stationUrl = stationUrl;
	}

	public String getStationName() {
		return stationName;
	}

	public String getStationUrl() {
		return stationUrl;
	}
}