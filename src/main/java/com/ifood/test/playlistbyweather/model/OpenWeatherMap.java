package com.ifood.test.playlistbyweather.model;

public class OpenWeatherMap {
    private Main main;

    public Double getTemperature() {
        return this.main.getTemp();
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}

class Main{
    private Double temp;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }
}