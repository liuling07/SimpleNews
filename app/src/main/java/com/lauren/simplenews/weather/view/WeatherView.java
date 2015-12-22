package com.lauren.simplenews.weather.view;

import com.lauren.simplenews.beans.WeatherBean;

import java.util.List;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 2015/12/22
 */
public interface WeatherView {

    void showProgress();
    void hideProgress();
    void showWeatherLayout();

    void setToday(String data);
    void setTemperature(String temperature);
    void setWind(String wind);
    void setWeather(String weather);
    void setWeatherImage(int res);
    void setWeatherData(List<WeatherBean> lists);

    void showErrorToast(String msg);


}
