package com.ubs.opsit.interviews;

public interface TimeConverter {

    String convertTime(String aTime);

	String getSecondsResult(Integer second) throws NullPointerException;

	String getFiveHourLampResult(Integer hour) throws NullPointerException;

	String getOneHourLampResult(Integer hour) throws NullPointerException;

	String getElevenLampMinResult(Integer min) throws NullPointerException;

	String getOneMinLampResult(Integer min) throws NullPointerException;

}
