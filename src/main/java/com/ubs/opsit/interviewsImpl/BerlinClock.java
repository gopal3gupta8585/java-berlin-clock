package com.ubs.opsit.interviewsImpl;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubs.opsit.interviews.TimeConverter;

public class BerlinClock implements TimeConverter {

	private static final Logger LOG = LoggerFactory.getLogger(BerlinClock.class);
	
	public static final String LAMP_OFF = "O";
	public static final String LAMP_ON = "Y";
	
	public static final String RED_LAMP = "R";
	public static final String YELLOW_LAMP = "Y";
	
	public static void main(String[] args) {
       
		BerlinClock berlinClock = new BerlinClock();
        int hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int min = Calendar.getInstance().get(Calendar.MINUTE);;
        int sec = Calendar.getInstance().get(Calendar.SECOND);
       
        String currentTime = hours+":"+min+":"+sec;
        
        LOG.info("CURRENT TIME IN BERLIN CLOCK");
        LOG.info("Current Digital Timing==> " + currentTime);
        LOG.info("Current Berlin Clock Timing==> " +berlinClock.convertTime(currentTime));
        
    }
	
	@Override
	public String convertTime(String aTime) {
		
		String[] timingFactor = aTime.split(":");
		
		String resultView = getSecondsResult(Integer.valueOf(timingFactor[2]))+"\r\n"+
							getFiveHourLampResult(Integer.valueOf(timingFactor[0]))+"\r\n"+
							getOneHourLampResult(Integer.valueOf(timingFactor[0]))+"\r\n"+
							getElevenLampMinResult(Integer.valueOf(timingFactor[1]))+"\r\n"+
							getOneMinLampResult(Integer.valueOf(timingFactor[1]));
							
		return resultView.trim();
	}
	
	/*
	 * Method for second Lamp -
	 *  
	 * */
	@Override
	public String getSecondsResult(Integer second) throws NullPointerException{
		String secondView = second%2==1?LAMP_OFF:LAMP_ON;
		return secondView;
	}
	
	/*
	 * Method for 5 Hour 4 Lamps -
	 *  
	 * */
	@Override
	public String getFiveHourLampResult(Integer hour)throws NullPointerException{
		String resultHour="";
		int count = 0;
		int countOfTurnOnLamp = 0;
		
		countOfTurnOnLamp = hour/5;
		count = 4-hour/5;
		
		for(int i=1;i<=countOfTurnOnLamp;i++){
			resultHour += RED_LAMP;
		}
		
		for(int i=1;i<=count;i++){
			resultHour += LAMP_OFF;
		}
		return resultHour.trim();
	}

	/*
	 * Method for One Hour 4 Lamps -
	 *  
	 * */
	@Override
	public String getOneHourLampResult(Integer hour)throws NullPointerException {
		String resultHour="";
		int count = 0;
		int countOfTurnOnLamp = 0;
		countOfTurnOnLamp = hour%5;
		count = 4-hour%5;
		
		for(int i=1;i<=countOfTurnOnLamp;i++){
			resultHour += RED_LAMP;
		}
		
		for(int i=1;i<=count;i++){
			resultHour += LAMP_OFF;
		}
		return resultHour.trim();
	}

	/*
	 * Method for One Minute 11 Lamps -
	 *  
	 * */
	@Override
	public String getElevenLampMinResult(Integer min)throws NullPointerException {
		String resultMin="";
		int count = 0;
		int countOfTurnOnLamp = 0;
		countOfTurnOnLamp = min/5;
		count = 11-min/5;
		
		
		for(int i=1;i<=countOfTurnOnLamp;i++){
			resultMin += YELLOW_LAMP;
		}
		
		for(int i=1;i<=count;i++){
			resultMin += LAMP_OFF;
		}
		return resultMin.trim().replaceAll("YYY", "YYR");
	}

	/*
	 * Method for One Minute 4 Lamps -
	 *  
	 * */
	@Override
	public String getOneMinLampResult(Integer min) throws NullPointerException{
		String resultMin="";
		int count = 0;
		int countOfTurnOnLamp = 0;
		countOfTurnOnLamp = min%5;
		count = 4-min%5;
		
		for(int i=1;i<=countOfTurnOnLamp;i++){
			resultMin += YELLOW_LAMP;
		}
		
		for(int i=1;i<=count;i++){
			resultMin += LAMP_OFF;
		}
		return resultMin.trim();
	}
}
