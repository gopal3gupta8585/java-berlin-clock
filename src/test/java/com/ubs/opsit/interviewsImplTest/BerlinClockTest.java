package com.ubs.opsit.interviewsImplTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ubs.opsit.interviews.TimeConverter;
import com.ubs.opsit.interviewsImpl.BerlinClock;

public class BerlinClockTest {

	private TimeConverter berlinClock;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
    	berlinClock = new BerlinClock();
    }
    
    
    /*
     * - There is no method created to test second' lamp. So it should be failed. TRUE.
     * - Method created: Returning Null value, while expecting either "Y" for Lamp on or "O" for Lamp off.
     * - Put if condition to produce modulo of passed parameter.If its 0 then "Y" else "O".
     * - re-factored to in-line condition.;
     * - Some test cases are commented out to run other scenarios successfully.
     * */
    @Test
    public void getSecondsResultTest(){
    	//assertThat(berlinClock.getSecondsResult()).isEqualTo("Y"); 
    	assertThat(berlinClock.getSecondsResult(0)).isEqualTo("Y"); //eOpected when pass 0 , it should be Y as 0%2==0. (Boundary condition)
        assertThat(berlinClock.getSecondsResult(1)).isEqualTo("O"); //eOpected when pass 1, it should be O as 1%2==1.
        assertThat(berlinClock.getSecondsResult(2)).isEqualTo("Y");
       // assertThat(berlinClock.getSecondsResult(32)).isEqualTo("O"); //Failed when pass 32 and eOpecting O, as 32%2==0.So Lamp should be on
       // assertThat(berlinClock.getSecondsResult(41)).isEqualTo("Y"); // Failed when pass 41 and eOpecting Y, as 41%2==1.So Lamp should be off.
        assertThat(berlinClock.getSecondsResult(59)).isEqualTo("O"); // Boundary condition
    }
    
    @Test
    public void getSecondResultTest_ForON(){
    	assertThat(berlinClock.getSecondsResult(0)).isEqualTo("Y"); //expected when pass 0 , it should be Y as 0%2==0. (Boundary condition)
    }
    
    @Test
    public void getSecondResultTest_ForOFF(){
    	assertThat(berlinClock.getSecondsResult(1)).isEqualTo("O"); //expected when pass 1 , it should be Y as 1%2==1. (Boundary condition)
    	assertThat(berlinClock.getSecondsResult(11)).isEqualTo("O");
    	assertThat(berlinClock.getSecondsResult(25)).isEqualTo("O");
    	assertThat(berlinClock.getSecondsResult(59)).isEqualTo("O");
    }
    
    @Test
    public void getSecondResultTest_For2Second(){
    	assertThat(berlinClock.getSecondsResult(2)).isEqualTo("Y"); //expected when pass 2 , it should be Y as 2%2==0. (Boundary condition)
    	assertThat(berlinClock.getSecondsResult(32)).isEqualTo("Y");
    	assertThat(berlinClock.getSecondsResult(41)).isEqualTo("O");
    	assertThat(berlinClock.getSecondsResult(59)).isEqualTo("O");
    }
    
    @Test(expected = NullPointerException.class)
    public void getSecondResultTest_NullParameter(){
    	assertThat(berlinClock.getSecondsResult(null)).isEqualTo("Y");//Failed: eOpecting a parameter.
    }
    
    /*@Test
    public void getSecondResultTest_FailConditions(){
    	assertThat(berlinClock.getSecondsResult(32)).isEqualTo("O");//Failed.
    	assertThat(berlinClock.getSecondsResult(31)).isEqualTo("Y");//Failed
    }*/
    
    /*
     * - There is no method created to test hour's lamp. So it should be failed. TRUE.
     * - Method created- Expecting one parameter, for Hour value as Integer, as there are two lines for Hours.
     * - Write condition for first line- count of turned on lamp depends on (number of hour/5) and turned off depends on (4-number of hour/5)
     * - Write condition for second line- count of turned on lamp depends on (number of hour%5) and turned off depends on (4-number of hour%5)
     * */
    
    @Test
    public void getHourResultForHour5Test_ForBoundary(){
    	 assertThat(berlinClock.getFiveHourLampResult(0)).isEqualTo("OOOO");
    	 assertThat(berlinClock.getFiveHourLampResult(23)).isEqualTo("RRRR");
    	 assertThat(berlinClock.getFiveHourLampResult(24)).isEqualTo("RRRR");
    }
    
    @Test
    public void getHourResultForHour5Test_ForNoneLampON(){
    	assertThat(berlinClock.getFiveHourLampResult(0)).isEqualTo("OOOO"); 
        assertThat(berlinClock.getFiveHourLampResult(1)).isEqualTo("OOOO"); 
        assertThat(berlinClock.getFiveHourLampResult(2)).isEqualTo("OOOO");
        assertThat(berlinClock.getFiveHourLampResult(3)).isEqualTo("OOOO");
        assertThat(berlinClock.getFiveHourLampResult(4)).isEqualTo("OOOO");
    }
    
    @Test
    public void getHourResultForHour5Test_ForOneLampON(){
    	  assertThat(berlinClock.getFiveHourLampResult(5)).isEqualTo("ROOO");
          assertThat(berlinClock.getFiveHourLampResult(6)).isEqualTo("ROOO");
          assertThat(berlinClock.getFiveHourLampResult(7)).isEqualTo("ROOO");
          assertThat(berlinClock.getFiveHourLampResult(8)).isEqualTo("ROOO");
          assertThat(berlinClock.getFiveHourLampResult(9)).isEqualTo("ROOO");
    }
    
    @Test
    public void getHourResultForHour5Test_ForTwoLampON(){
    	 assertThat(berlinClock.getFiveHourLampResult(10)).isEqualTo("RROO");
         assertThat(berlinClock.getFiveHourLampResult(11)).isEqualTo("RROO");
         assertThat(berlinClock.getFiveHourLampResult(12)).isEqualTo("RROO");
         assertThat(berlinClock.getFiveHourLampResult(13)).isEqualTo("RROO");
         assertThat(berlinClock.getFiveHourLampResult(14)).isEqualTo("RROO");
    }
    
    @Test
    public void getHourResultForHour5Test_ForThreeLampON(){
    	assertThat(berlinClock.getFiveHourLampResult(15)).isEqualTo("RRRO");
    	assertThat(berlinClock.getFiveHourLampResult(16)).isEqualTo("RRRO");
    	assertThat(berlinClock.getFiveHourLampResult(17)).isEqualTo("RRRO");
    	assertThat(berlinClock.getFiveHourLampResult(18)).isEqualTo("RRRO");
    	assertThat(berlinClock.getFiveHourLampResult(19)).isEqualTo("RRRO");
    }
    
    @Test
    public void getHourResultForHour5Test_ForAllLampON(){
    	assertThat(berlinClock.getFiveHourLampResult(20)).isEqualTo("RRRR");
    	assertThat(berlinClock.getFiveHourLampResult(21)).isEqualTo("RRRR");
    	assertThat(berlinClock.getFiveHourLampResult(22)).isEqualTo("RRRR");
    	assertThat(berlinClock.getFiveHourLampResult(23)).isEqualTo("RRRR");
    	assertThat(berlinClock.getFiveHourLampResult(24)).isEqualTo("RRRR");
    }
    
   /* @Test
    public void getHourResultForHour5Test_FailConditions(){
    	assertThat(berlinClock.getFiveHourLampResult(1)).isEqualTo("ROOO");
    	assertThat(berlinClock.getFiveHourLampResult(6)).isEqualTo("ROOO");
    	assertThat(berlinClock.getFiveHourLampResult(11)).isEqualTo("RRRO");
    	assertThat(berlinClock.getFiveHourLampResult(17)).isEqualTo("RRRR");
    }*/
    
    @Test(expected = NullPointerException.class)
    public void getHourResultForHour5Test_NullParameter(){
    	assertThat(berlinClock.getFiveHourLampResult(null)).isEqualTo("ROOO");
    }
    
    
    @Test
    public void getHourResultForHour1Test_ForBoundary(){
    	 assertThat(berlinClock.getOneHourLampResult(0)).isEqualTo("OOOO");
    	 assertThat(berlinClock.getOneHourLampResult(1)).isEqualTo("ROOO");
    	 assertThat(berlinClock.getOneHourLampResult(23)).isEqualTo("RRRO");
    	 assertThat(berlinClock.getOneHourLampResult(24)).isEqualTo("RRRR");
    }
    
    @Test
    public void getHourResultForHour1Test_ForNoneLampON(){
    	assertThat(berlinClock.getOneHourLampResult(0)).isEqualTo("OOOO"); 
        assertThat(berlinClock.getOneHourLampResult(5)).isEqualTo("OOOO"); 
        assertThat(berlinClock.getOneHourLampResult(10)).isEqualTo("OOOO");
        assertThat(berlinClock.getOneHourLampResult(15)).isEqualTo("OOOO");
        assertThat(berlinClock.getOneHourLampResult(20)).isEqualTo("OOOO");
    }
    
    @Test
    public void getHourResultForHour1Test_ForOneLampON(){
    	  assertThat(berlinClock.getOneHourLampResult(1)).isEqualTo("ROOO");
          assertThat(berlinClock.getOneHourLampResult(6)).isEqualTo("ROOO");
          assertThat(berlinClock.getOneHourLampResult(11)).isEqualTo("ROOO");
          assertThat(berlinClock.getOneHourLampResult(21)).isEqualTo("ROOO");
    }
    
    @Test
    public void getHourResultForHour1Test_ForTwoLampON(){
    	 assertThat(berlinClock.getOneHourLampResult(2)).isEqualTo("RROO");
         assertThat(berlinClock.getOneHourLampResult(7)).isEqualTo("RROO");
         assertThat(berlinClock.getOneHourLampResult(12)).isEqualTo("RROO");
         assertThat(berlinClock.getOneHourLampResult(17)).isEqualTo("RROO");
         assertThat(berlinClock.getOneHourLampResult(22)).isEqualTo("RROO");
    }
    
    @Test
    public void getHourResultForHour1Test_ForThreeLampON(){
    	assertThat(berlinClock.getOneHourLampResult(3)).isEqualTo("RRRO");
    	assertThat(berlinClock.getOneHourLampResult(8)).isEqualTo("RRRO");
    	assertThat(berlinClock.getOneHourLampResult(13)).isEqualTo("RRRO");
    	assertThat(berlinClock.getOneHourLampResult(18)).isEqualTo("RRRO");
    	assertThat(berlinClock.getOneHourLampResult(23)).isEqualTo("RRRO");
    }
    
    @Test
    public void getHourResultForHour1Test_ForAllLampON(){
    	assertThat(berlinClock.getOneHourLampResult(4)).isEqualTo("RRRR");
    	assertThat(berlinClock.getOneHourLampResult(9)).isEqualTo("RRRR");
    	assertThat(berlinClock.getOneHourLampResult(14)).isEqualTo("RRRR");
    	assertThat(berlinClock.getOneHourLampResult(19)).isEqualTo("RRRR");
    	assertThat(berlinClock.getOneHourLampResult(24)).isEqualTo("RRRR");
    }
    
    /*@Test
    public void getHourResultForHour1Test_FailConditions(){
    	assertThat(berlinClock.getOneHourLampResult(0)).isEqualTo("ROOO");
    	assertThat(berlinClock.getOneHourLampResult(5)).isEqualTo("ROOO");
    	assertThat(berlinClock.getOneHourLampResult(10)).isEqualTo("RRRO");
    	assertThat(berlinClock.getOneHourLampResult(15)).isEqualTo("ROOO");
    }*/
    
    @Test(expected = NullPointerException.class)
    public void getHourResultForHour1Test_NullParameter(){
    	assertThat(berlinClock.getOneHourLampResult(null)).isEqualTo("ROOO");
    }
    
    
    /*
     * - There is no method created to test Minutes' lamp. So it should be failed. TRUE.
     * - Method created- Expecting one parameters, first for Min value as Integer , as there are two lines for Minutes.
     * - Write condition for first line- count of turned on lamp depends on (number of min/5) and turned off depends on (11-number of min/5)
     * - Write condition for second line- count of turned on lamp depends on (number of min%5) and turned off depends on (4-number of min%5)
     * - return the resultant minutes string after putting condition to replace "YYY" to "YYR" for 11 Lamps line.
     * */
    
    @Test
    public void getResultMinFor11LampsTest_ForBoundary(){
    	 assertThat(berlinClock.getElevenLampMinResult(0)).isEqualTo("OOOOOOOOOOO");
    	 //assertThat(berlinClock.getElevenLampMinResult(1)).isEqualTo("YOOOOOOOOOO"); FAILED
    	// assertThat(berlinClock.getElevenLampMinResult(2)).isEqualTo("YYOOOOOOOOO");
    	// assertThat(berlinClock.getElevenLampMinResult(3)).isEqualTo("YYROOOOOOOO");
    	 
    	 assertThat(berlinClock.getElevenLampMinResult(5)).isEqualTo("YOOOOOOOOOO");
     	assertThat(berlinClock.getElevenLampMinResult(10)).isEqualTo("YYOOOOOOOOO");
     	 assertThat(berlinClock.getElevenLampMinResult(15)).isEqualTo("YYROOOOOOOO");
    	 
    	  assertThat(berlinClock.getElevenLampMinResult(30)).isEqualTo("YYRYYROOOOO");//2nd Quarter
          assertThat(berlinClock.getElevenLampMinResult(34)).isEqualTo("YYRYYROOOOO");
          
          assertThat(berlinClock.getElevenLampMinResult(45)).isEqualTo("YYRYYRYYROO");//3rd Quarter
          assertThat(berlinClock.getElevenLampMinResult(49)).isEqualTo("YYRYYRYYROO");
          
          assertThat(berlinClock.getElevenLampMinResult(55)).isEqualTo("YYRYYRYYRYY");
      	  assertThat(berlinClock.getElevenLampMinResult(59)).isEqualTo("YYRYYRYYRYY"); //Boundary condition	
    }
    
    @Test
    public void getResultMinFor11LampsTest_ForNoneLampON(){
    	assertThat(berlinClock.getElevenLampMinResult(0)).isEqualTo("OOOOOOOOOOO");
    }
    
    @Test
    public void getResultMinFor11LampsTest_ForOneLampON(){
    	  assertThat(berlinClock.getElevenLampMinResult(5)).isEqualTo("YOOOOOOOOOO");
    }
    
    @Test
    public void getResultMinFor11LampsTest_ForTwoLampON(){
    	 assertThat(berlinClock.getElevenLampMinResult(10)).isEqualTo("YYOOOOOOOOO");
    }
    
    //1st Quarter
    @Test 
    public void getResultMinFor11LampsTest_ForThreeLampON(){
    	assertThat(berlinClock.getElevenLampMinResult(15)).isEqualTo("YYROOOOOOOO");
    }
    
    @Test
    public void getResultMinFor11LampsTest_ForFourLampON(){
    	assertThat(berlinClock.getElevenLampMinResult(20)).isEqualTo("YYRYOOOOOOO");
    }
    
    @Test
    public void getResultMinFor11LampsTest_ForFiveLampON(){
    	assertThat(berlinClock.getElevenLampMinResult(25)).isEqualTo("YYRYYOOOOOO");
    }
    
    //2nd Quarter
    @Test
    public void getResultMinFor11LampsTest_ForSixLampON(){
    	assertThat(berlinClock.getElevenLampMinResult(30)).isEqualTo("YYRYYROOOOO");
    }
    
    @Test
    public void getResultMinFor11LampsTest_ForSevenLampON(){
    	assertThat(berlinClock.getElevenLampMinResult(35)).isEqualTo("YYRYYRYOOOO");
    }
    
    @Test
    public void getResultMinFor11LampsTest_ForEightLampON(){
    	assertThat(berlinClock.getElevenLampMinResult(40)).isEqualTo("YYRYYRYYOOO");
    }
    

    //3rd Quarter
    @Test
    public void getResultMinFor11LampsTest_ForNineLampON(){
    	assertThat(berlinClock.getElevenLampMinResult(45)).isEqualTo("YYRYYRYYROO");
    }
    
    @Test
    public void getResultMinFor11LampsTest_ForTenLampON(){
    	assertThat(berlinClock.getElevenLampMinResult(50)).isEqualTo("YYRYYRYYRYO");
    }
    
    
    @Test
    public void getResultMinFor11LampsTest_ForAllLampON(){
    	assertThat(berlinClock.getElevenLampMinResult(55)).isEqualTo("YYRYYRYYRYY");
    }
    
   /* @Test
    public void getResultMinFor11LampsTest_FailConditions(){
    	assertThat(berlinClock.getElevenLampMinResult(1)).isEqualTo("YOOOOOOOOOO");
    	assertThat(berlinClock.getElevenLampMinResult(16)).isEqualTo("YYRYOOOOOOO");
    }*/
    
    @Test(expected = NullPointerException.class)
    public void getResultMinFor11LampsTest_NullParameter(){
    	assertThat(berlinClock.getElevenLampMinResult(null)).isEqualTo("OOOOOOOOOOO");
    }
    
    @Test
    public void getResultMinFor4LampsTest(){
    	
    	assertThat(berlinClock.getOneMinLampResult(0)).isEqualTo("OOOO"); //Boundart condition
    	assertThat(berlinClock.getOneMinLampResult(1)).isEqualTo("YOOO");
    	assertThat(berlinClock.getOneMinLampResult(4)).isEqualTo("YYYY");

        assertThat(berlinClock.getOneMinLampResult(5)).isEqualTo("OOOO");
        assertThat(berlinClock.getOneMinLampResult(9)).isEqualTo("YYYY");

        assertThat(berlinClock.getOneMinLampResult(10)).isEqualTo("OOOO");
        assertThat(berlinClock.getOneMinLampResult(14)).isEqualTo("YYYY");

        assertThat(berlinClock.getOneMinLampResult(15)).isEqualTo("OOOO");
        assertThat(berlinClock.getOneMinLampResult(17)).isEqualTo("YYOO");

        assertThat(berlinClock.getOneMinLampResult(20)).isEqualTo("OOOO");
        assertThat(berlinClock.getOneMinLampResult(28)).isEqualTo("YYYO");

        assertThat(berlinClock.getOneMinLampResult(55)).isEqualTo("OOOO");
    	assertThat(berlinClock.getOneMinLampResult(59)).isEqualTo("YYYY"); //Boundary condition
    }
    
    @Test
    public void getResultMinFor4LampsTest_ForBoundary(){
    	 assertThat(berlinClock.getOneMinLampResult(0)).isEqualTo("OOOO");
    	 assertThat(berlinClock.getOneMinLampResult(1)).isEqualTo("YOOO");
    	 assertThat(berlinClock.getOneMinLampResult(23)).isEqualTo("YYYO");
    	 assertThat(berlinClock.getOneMinLampResult(43)).isEqualTo("YYYO");
    	 assertThat(berlinClock.getOneMinLampResult(59)).isEqualTo("YYYY");
    }
    
    @Test
    public void getResultMinFor4LampsTest_ForNoneLampON(){
    	assertThat(berlinClock.getOneMinLampResult(0)).isEqualTo("OOOO"); 
        assertThat(berlinClock.getOneMinLampResult(5)).isEqualTo("OOOO"); 
        assertThat(berlinClock.getOneMinLampResult(10)).isEqualTo("OOOO");
        assertThat(berlinClock.getOneMinLampResult(15)).isEqualTo("OOOO");
        assertThat(berlinClock.getOneMinLampResult(20)).isEqualTo("OOOO");
        assertThat(berlinClock.getOneMinLampResult(25)).isEqualTo("OOOO");
        assertThat(berlinClock.getOneMinLampResult(30)).isEqualTo("OOOO");
        assertThat(berlinClock.getOneMinLampResult(35)).isEqualTo("OOOO");
        assertThat(berlinClock.getOneMinLampResult(40)).isEqualTo("OOOO");
        assertThat(berlinClock.getOneMinLampResult(45)).isEqualTo("OOOO");
        assertThat(berlinClock.getOneMinLampResult(50)).isEqualTo("OOOO");
        assertThat(berlinClock.getOneMinLampResult(55)).isEqualTo("OOOO");
    }
    
    @Test
    public void getResultMinFor4LampsTest_ForOneLampON(){
    	  assertThat(berlinClock.getOneMinLampResult(1)).isEqualTo("YOOO");
          assertThat(berlinClock.getOneMinLampResult(6)).isEqualTo("YOOO");
          assertThat(berlinClock.getOneMinLampResult(11)).isEqualTo("YOOO");
          assertThat(berlinClock.getOneMinLampResult(16)).isEqualTo("YOOO");
          assertThat(berlinClock.getOneMinLampResult(21)).isEqualTo("YOOO");
          assertThat(berlinClock.getOneMinLampResult(26)).isEqualTo("YOOO");
          assertThat(berlinClock.getOneMinLampResult(31)).isEqualTo("YOOO");
          assertThat(berlinClock.getOneMinLampResult(36)).isEqualTo("YOOO");
          assertThat(berlinClock.getOneMinLampResult(41)).isEqualTo("YOOO");
          assertThat(berlinClock.getOneMinLampResult(46)).isEqualTo("YOOO");
          assertThat(berlinClock.getOneMinLampResult(51)).isEqualTo("YOOO");
          assertThat(berlinClock.getOneMinLampResult(56)).isEqualTo("YOOO");
    }
    
    @Test
    public void getResultMinFor4LampsTest_ForTwoLampON(){
    	 assertThat(berlinClock.getOneMinLampResult(2)).isEqualTo("YYOO");
         assertThat(berlinClock.getOneMinLampResult(7)).isEqualTo("YYOO");
         assertThat(berlinClock.getOneMinLampResult(12)).isEqualTo("YYOO");
         assertThat(berlinClock.getOneMinLampResult(17)).isEqualTo("YYOO");
         assertThat(berlinClock.getOneMinLampResult(22)).isEqualTo("YYOO");
         assertThat(berlinClock.getOneMinLampResult(27)).isEqualTo("YYOO");
         assertThat(berlinClock.getOneMinLampResult(32)).isEqualTo("YYOO");
         assertThat(berlinClock.getOneMinLampResult(37)).isEqualTo("YYOO");
         assertThat(berlinClock.getOneMinLampResult(42)).isEqualTo("YYOO");
         assertThat(berlinClock.getOneMinLampResult(47)).isEqualTo("YYOO");
         assertThat(berlinClock.getOneMinLampResult(52)).isEqualTo("YYOO");
         assertThat(berlinClock.getOneMinLampResult(57)).isEqualTo("YYOO");
    }
    
    @Test
    public void getResultMinFor4LampsTest_ForThreeLampON(){
    	assertThat(berlinClock.getOneMinLampResult(3)).isEqualTo("YYYO");
    	assertThat(berlinClock.getOneMinLampResult(8)).isEqualTo("YYYO");
    	assertThat(berlinClock.getOneMinLampResult(13)).isEqualTo("YYYO");
    	assertThat(berlinClock.getOneMinLampResult(18)).isEqualTo("YYYO");
    	assertThat(berlinClock.getOneMinLampResult(23)).isEqualTo("YYYO");
    	assertThat(berlinClock.getOneMinLampResult(28)).isEqualTo("YYYO");
    	assertThat(berlinClock.getOneMinLampResult(33)).isEqualTo("YYYO");
    	assertThat(berlinClock.getOneMinLampResult(38)).isEqualTo("YYYO");
    	assertThat(berlinClock.getOneMinLampResult(43)).isEqualTo("YYYO");
    	assertThat(berlinClock.getOneMinLampResult(48)).isEqualTo("YYYO");
    	assertThat(berlinClock.getOneMinLampResult(53)).isEqualTo("YYYO");
    	assertThat(berlinClock.getOneMinLampResult(58)).isEqualTo("YYYO");
    }
    
    @Test
    public void getResultMinFor4LampsTest_ForAllLampON(){
    	assertThat(berlinClock.getOneMinLampResult(4)).isEqualTo("YYYY");
    	assertThat(berlinClock.getOneMinLampResult(9)).isEqualTo("YYYY");
    	assertThat(berlinClock.getOneMinLampResult(14)).isEqualTo("YYYY");
    	assertThat(berlinClock.getOneMinLampResult(19)).isEqualTo("YYYY");
    	assertThat(berlinClock.getOneMinLampResult(24)).isEqualTo("YYYY");
    	assertThat(berlinClock.getOneMinLampResult(29)).isEqualTo("YYYY");
    	assertThat(berlinClock.getOneMinLampResult(34)).isEqualTo("YYYY");
    	assertThat(berlinClock.getOneMinLampResult(39)).isEqualTo("YYYY");
    	assertThat(berlinClock.getOneMinLampResult(44)).isEqualTo("YYYY");
    	assertThat(berlinClock.getOneMinLampResult(49)).isEqualTo("YYYY");
    	assertThat(berlinClock.getOneMinLampResult(54)).isEqualTo("YYYY");
    	assertThat(berlinClock.getOneMinLampResult(59)).isEqualTo("YYYY");
    }
    
    /*@Test
    public void getResultMinFor4LampsTest_FailConditions(){
    	assertThat(berlinClock.getOneMinLampResult(0)).isEqualTo("YOOO");
    	assertThat(berlinClock.getOneMinLampResult(5)).isEqualTo("YOOO");
    	assertThat(berlinClock.getOneMinLampResult(10)).isEqualTo("YYYO");
    	assertThat(berlinClock.getOneMinLampResult(15)).isEqualTo("YOOO");
    }*/
    
    @Test(expected = NullPointerException.class)
    public void getResultMinFor4LampsTest_NullParameter(){
    	assertThat(berlinClock.getOneMinLampResult(null)).isEqualTo("YOOO");
    }
    
    //---------------------------------------------------------------------------------------------
    //						ACCEPTANCE TEST SCENARIOS FOR STORIES
    //---------------------------------------------------------------------------------------------
    
	@Test
	public void whenConvertTimeTestForMidnight_0_0_0() {
		assertThat(berlinClock.convertTime("0:0:0")).isEqualTo(
                "Y\r\n" +
                "OOOO\r\n"+
                "OOOO\r\n"+
                "OOOOOOOOOOO\r\n"+
                "OOOO");
	}
	
	@Test
	public void whenConvertTimeTestForMiddleOfTheAfternoon_13_17_01() {
		assertThat(berlinClock.convertTime("13:17:01")).isEqualTo(
                "O\r\n" +
                "RROO\r\n"+
                "RRRO\r\n"+
                "YYROOOOOOOO\r\n"+
                "YYOO");
	}
	
	@Test
	public void whenConvertTimeTestJustBeforeMidnight_23_59_59() {
		assertThat(berlinClock.convertTime("23:59:59")).isEqualTo(
                "O\r\n" +
                "RRRR\r\n"+
                "RRRO\r\n"+
                "YYRYYRYYRYY\r\n"+
                "YYYY");
	}
	
	@Test
	public void whenConvertTimeTestForMidnight_24_0_0() {
		assertThat(berlinClock.convertTime("24:0:0")).isEqualTo(
                "Y\r\n" +
                "RRRR\r\n"+
                "RRRR\r\n"+
                "OOOOOOOOOOO\r\n"+
                "OOOO");
	}

}
