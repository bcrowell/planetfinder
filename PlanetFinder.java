/*

	Copyright (c) 2000 Benjamin Crowell. All rights reserved.
	
	About the GPL License
		This program is free software; you can redistribute it and/or modify
	    it under the terms of the GNU General Public License as published by
	    the Free Software Foundation; either version 2 of the License, or
	    (at your option) any later version.

	    This program is distributed in the hope that it will be useful,
	    but WITHOUT ANY WARRANTY; without even the implied warranty of
	    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	    GNU General Public License for more details.

	    You should have received a copy of the GNU General Public License
	    along with this program; if not, write to the Free Software
	    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/



import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;

/*

Cartesian coordinates: Unless otherwise stated, the coordinate system has x pointing to the vernal
equinox, z pointing to the north celestial pole.

Testing: Roughly reproduced configuration of outer planets in Solar System Live for 1/1/1970 and 1/1/2000.
Perihelion of Pluto occurs at roughly the right date.
Adjusted phase of earth's rotation to reproduce Cybersky's prediction of time when Saturn sets on 1/1/2000
for an observer at 0 lat & 0 long.
Can then reproduce rise of Jupiter at Long Beach on 27 Dec 98 fairly well -- 3 deg off on altitude.
...Is this error due to oblateness of earth?
Seemed to work roughly correctly on predicting positions of Jup & Sat in tonight's evening sky.
Correctly predicted today's sunrise & sunset.

Moon's data found by fitting to data from US Naval Obs, except for mean long, which I had to get by fitting
to the newspaper's data for time of setting moon.
This did not correctly reproduce occultation of Regulus on 5/21/99. After much fiddling around, I realized that
the moon was not moving across the sky at quite the right rate in any given part of its orbit, i.e. my
long of perigee was wrong. I think I know why this was happening: when I first coded the program, I was
incorrectly remembering the meaning of "vernal." I convinced myself that everything had worked correctly,
since I could correctly predict sunrise & sunset, etc. However, I think that meant that my internal coordinates
were rotated 180 deg compared to the right ones.
Changed long of perigee of moon by 180 deg and readjusted its mean long. This gave perfect agreement with
Cybersky on moon's position for 9 Jan 2050, 6 Jul 2050, and 28 Jan 99.

Summary of testing:
	It now seems to pass a fairly rich set of tests. However, I should work up a good test quite that can be
	run automatically. Also, if I add orbital elements for other objects in the future, I think I need to
	add 180 deg to all longitudes.The moon data could improved a lot.

Moon parameters used as of 11 Jan 99:	
	Satellite moon = new Satellite(earth,"Moon",27.322,2.569519e-03,0.0549,5.145,168.5327,347.6917-180.,224.0);
	This agrees well with Cybersky for the following dates to within about 1 moon radius:
		21 May 1999, 19:30 pst, Fullerton, (ignoring dst)
		28 Jan 1999, 22:00 UT, 34 N, 30 W
		9 Jan 2050, 00:01 pst, Fullerton
	Does not work as well for
		6 Jul 2050, 00:01 pst (ignoring dst), Fullerton
Email from Dave X at NASA, 14 Jan 99:
        Ben,
		I don't have a longitude of perigee.  For lunar mean longitude I find the
		expression:

		218 deg. 18' 59.956" + 1732564372.8326 T arc seconds plus higher order
		terms, where T is Julian centuries from J2000.  I hope this is helpful.
		I'm not sure where you'd look for the longitude of perigee.
         Dave
But Schlyter's data give 198...???

For testing purposes:
	LA times sunrise and sunset for 1/19/99: 6:55, 17:10

15 Jan 99 - Added correction for parallax due to earth's rotation. For moon, added Schlyter's highest-
		order corrections as a self-contained unit, without adopting his elements, which seem to be
		set up in a way that's completely inconsistent with the definitions I've been using, i.e. he
		seems to subsume into his N's, M's, and w's some time-variation that I've probably been taking
		into account elsewhere....???? I am now at least pretty close on the moon stuff, since I still
		reproduce the eclipse of Regulus to within a few hours or so, and I guess if I do any further
		fitting I'll be in better shape because I won't be trying to fit nonkeplerian effects with
		keplerian equations.
16 Jan 1999 - 
		Oops -- looks like I was misinterpreting def of mean longitude...my calcs will all be off because
		I thought it was actual long at J2000, not long by linear extrapolation from previous perihelion.
		Fixed this.
		Changed to Schlyter's nonkeplerian model of the moon, but his mean long at 2000 of 198 degrees
		is drastically off compared to Dave's 218, or the 215 that I'd previously fitted. What's up???
		Changed to an ad hoc mixture of Dave's mean long at 2000 with Schlyter's other elements.
		I am now pretty darn close to CyberSky's prediction of moon's position for 2000.
		Reproduces Cybersky's prediction of 5/21/99 occultation of Regulus to within one lunar radius.
17 Jan 1999 -
		Now reads time zone from browser.
		Made daylight savings time automatic.
		Removed +- buttons, since they just made the control panel more formidable, and didn't work on MacOS.
		There is a bug in DST, which occurs only in Metrowerks VM, not in IE on the Mac. In MW, DST is set to
		-9 for California. Also, MW is off by 1 hour on setting the current time, in addition.
		Fixed AM/PM bug.
18 Jan 1999
		Changed event handling to Java 1.0 for compatibility with all broswers.
		Fixed bug in DST controls.
20 Jan 1999
		Put in sun's motion relative to solar system's center of mass. Readjusted phase angle of earth's rotation
		to reproduce mean of sunrise and sunset given by LA Times (in agreement with Cybersky) for 1/19/99.
22 Jan 1999
		Moved layout code to a separate class. Magnitudes of planets shown.
23 Jan 1999
		Internationalization. Still to do: (1) Make names of planets and months get updated when you change
		language on the fly. (2) Why doesn't changing language in IE Preferences have any effect?
29 Jan 1999
		Tracked down the reason why Netscape 4.5 on MacOS was crashing: it crashed on certain Java 1.1 constructors
		for Checkbox. Fixed that, and changed another couple of 1.1-isms to 1.0, but now it gives the following
		error message:
				# Applet exception: exception: java.lang.NullPointerException
				java.lang.NullPointerException
		  		at PlanetFinder.<init>(Compiled Code)
		  		at netscape.applet.DerivedAppletFrame.run(Compiled Code)
		 	 	at java.lang.Thread.run(Compiled Code)
30 Jan 99
		Moved initialization of almost all variables (all but some arrays and constants) into a subroutine, so
		that I can read the language choice parameter before initializing the text. Got rid of on-the-fly language
		choice, since I could never get the panels to resize appropriately.
		Worked more on Mac Netscape compatibility. It was getting a null pointer exception on statements like
		mercury = new Planet(...). I could fix that problem by doing Planet blah = new Planet(...); mercury = blah; ,
		but unfortunately Netscape then crashes mysteriously on some later part of the program. Left it the way it
		was, so that Netscape would halt execution but not crash.
31 Jan 99
		Improved code for recognizing language codes to be more robust. It now tries to find a city in your time
		zone where your language is spoken.
29 Sep 99
		Augh!!!!!! For today, predicts sunset and moonrise at 5:35 and 9:30 pm, but the real values are
		6:39 and 10:19 (newspaper), i.e. 1 hour later. I don't think it's a DST problem, because DST is supposed
		to be in effect.
		OK, Let's start being a little more systematic about building a test suite:
			date		LA times			LA times 	LA times			PF				PF			PF day
						sunrise			sunset		day len			sunrise		sunset	length
			1/19/99						17:10								8:00			18:00		10 hrs
			7/19																5:10			18:50		13.5 hrs
			9/29/99	6:39				18:39			12					5:55			17:30		11:35
		The problem seems to be that PF predicts an hour too late during non-DST, and
		an hour too early during DST. In other words, the DST correction has the wrong sign.
		Reversed sign of DST, added 1 hour to earthRotationPhase . Now predicts:
			date		LA times			LA times 	LA times			PF				PF			PF day
						sunrise			sunset		day len			sunrise		sunset	length
			1/19/99						17:10												17:00
			7/19																
			9/29/99	6:39				18:39			12									18:30
			
22 Oct 99
		Added html parameters to allow latitude and longitude to be set. Bug: doesn't accept
		non-integer values.

5 Mar 00
		No!!!!!!!! For today, predicts sunset at 4:45 pm, should be 5:52.
			date				LA times			LA times 	LA times			PF				PF			PF day
						dst	sunrise			sunset		day len			sunrise		sunset	length
			1/19/99								17:10												16:00
			9/29/99		x	6:39				18:39			12					6:53			18:30
			3/5/00			6:14				17:52								5:20			16:45
			10/28/00		x															7:20			17:55
			10/29/00																	5:20			15:55
		Why did prediction for 1/19 change since I last tested it???? I didn't change  that part of
		the code, did I? Is it different runtimes?
		IE predicts 16:00 1/19/99, and so does CW. OK, CW was setting time zone to -9. It was
		agreeing with IE because I changed it by hand ot -8.
		Conclusions:
		(1) Only use IE to test from now on.
		(2) Day length is correct.
		(3) Messes up when it's not DST, ok during DST.
		(4) DST correction has the right sign, but is amounting to a 2-hour correction, when it
			should be 1 (cf. 10/28, 10/29). I can only find one place in the code where I apply
			the correction, so apparently it is being applied somewhere in the runtime, and my correction
			is superfluous.
		Eliminated DST correction in my code. Changed earthRotationPhase back to what it was before
		29 Sep 99.
			date				LA times			LA times 	LA times			PF				PF			PF day
						dst	sunrise			sunset		day len			sunrise		sunset	length
			1/19/99								17:10												
			9/29/99		x	6:39				18:39			12					7:00			18:35
			3/5/00			6:14				17:52								6:25			17:45
			10/28/00		x															7:20			17:55
			10/29/00																	6:20			16:55												
		
Future improvements:
(4) Optionally trace constellations.
(5) Let them set orientation of sky.
(6) If I ever reactivate the +- buttons, I need to take into account the AM/PM behavior that I hadn't understood
	before.
(7)		While writing Variable Star Calendar, noticed that when I created a gregorian calendar object and
		the printed it back out, it was off by an hour. This occurs both in MW and in IE. Compensated for it
		in VarCal, but haven't done anything about it here. If I compensate for it here, have to readjust rotation
		phase angle of earth, since I fit that to data before.
(9) I predict the days to be 10 minutes too short in Fullerton (sun rises 5 minutes late and sets 5 minutes early).
This happens year-round, vis a vis CyberSky. Cybersky agrees with LA Times today (19 jan 99).
Even if they're defining it from the limb rather than the center, this would only account
for 2 minutes, not 10. Does this have to do with the geoid? But I would think that would give a correction of the
opposite sign. For comparison, Cybersky predicts sunrise at 6:00 and sunset at 18:07 for lat=0, lon=0, 1 Jan 99.
Earth has equatorial radius/polar radius = 1.0034. This is way too small to account for the discrepancy, and it
would have the effect of changing latitude to lat' = atan(1.00346*tan(lat)), which would make the days shorter,
not longer in winter. I think it must be a refraction effect. If so, then it would probably be a bigger effect at
northern latitudes when the sun rises or sets at a strong angle.
(11) Need to show error messages.
*/

public class PlanetFinder extends Applet
{
	Language lang;
	Text txt;
   long tzOffset;
	Panel sky,dstPanel,controls,controls2,timePanel,locationPanel,latPanel,lonPanel,viewCheckboxPanel;
	StaticText localTimeStaticText,latitudeStaticText,longitudeStaticText,timeZoneLabel,homePageAndAuthorStaticText,
				homePageAndAuthor2StaticText;
	Checkbox dstCheckbox,dstAutoCheckbox,dstManualCheckbox,latNCheckbox,latSCheckbox,lonECheckbox,lonWCheckbox,
				skyCheckbox,innerSolarSystemCheckbox,outerSolarSystemCheckbox,nakedEyeOnlyCheckbox;
	CheckboxGroup dstCheckboxGroup,latNSCheckboxGroup,lonEWCheckboxGroup,viewCheckboxGroup;
	Choice cityChoice,monthChoice;
	TextField hourText,minuteText,dayText,yearText,yourLatText,yourLongText,timeZoneText;
	Button dayPlusButton,dayMinusButton,monthPlusButton,monthMinusButton,yearPlusButton,yearMinusButton,updateButton;
	long J2000EpochMinus1970Epoch;
	boolean validDate,validLatLon,validTimeZoneText;
	Color skyBlue,duskSkyColor,sunColor,moonIlluminatedColor,moonUnilluminatedColor;
	double optionDimmestMagnitude;
	boolean optionDisplayPlanets,optionBlackAndWhiteOnly,optionShowNSEW,optionShowMagnitudes,optionGiveLanguageChoice;
	GregorianCalendar gcalendar,gCalendar2000;
	double longitude,latitude;

	Cartesian earthBasisX; //== same as xHat
	Cartesian earthBasisY;
	Cartesian earthBasisZ; //== earth's axis
	Planet mercury,venus,earth,mars,jupiter,saturn,uranus,neptune,pluto,moon;
	Layout theLayout;
	final double earthRotationTilt = 0.4092797; // radians
	final double earthRotationPeriod = 0.9972708; // days
	//final double earthRotationPhase = -1.8070; // derived by fitting to predictions from CyberSky
	//final double earthRotationPhase = -1.747; // adjusted 1/20/99 because of new calculation of sun's position
	//														// relative to center of mass of solar system
	//final double earthRotationPhase = -1.747-2.*3.14159/24.;
						//subtracted an hour 9/29/99 as part of fix to DST bug; see comments at top of code
	final double earthRotationPhase = -1.747;
						//changed back, 3/5/00; see comments at top of code
	
	final double sunMass = 3.33e+05; // in units of the earth's mass
	Planet planets[] = new Planet[9];
	boolean nakedEyePlanet[] = {true,true,true,true,true,true,false,false,false};
	Color planetColors[] = {Color.orange,Color.white,Color.green,Color.red,new Color(15*16+10,10*15+10,10*15+12),
										new Color(10*16+10,13*15+14,10*15+13),Color.cyan,Color.blue,Color.white};
	
	//===============================================================
	//				init
	//===============================================================
	public void init() {
	  double paramLat,paramLon;

	  try {
	    lang = new Language(getParameter("language"));
	  }
	  catch (Exception ee) {
	    lang = new Language(Locale.getDefault().getDisplayLanguage());
	  }
	  
	  txt = new Text(lang);
	  
	  initVariables();
	
	  nakedEyeOnlyCheckbox.setState(true);
	  //nakedEyeOnlyCheckbox.setState(false);
	  
	  dstCheckbox.setState((TimeZone.getDefault()).inDaylightTime(new GregorianCalendar().getTime()));
	  
	  // When tzOffset is first declared, it is initialized to what the browser thinks the local time zone
	  // is. Then when we get here, we check if the html supplied a value, and if so, we override it.
	  // The user can also change it at any later time.
	  try {
	    double glub = (Double.valueOf(getParameter("timezone"))).doubleValue();
	    tzOffset=(new Long(Math.round(glub*3600000))).longValue();
	  }
	  catch (Exception ee) {}
	  
	  setTimeZoneText(tzOffset);

	  
	  respondToSelectionOfTimeZone(true);

	  paramLat = -999.d;
	  paramLon = -999.d;

	  try {paramLat = (Double.valueOf(getParameter("latitude"))).doubleValue();}
	  catch (Exception ee) {}
	  
	  try {paramLon = (Double.valueOf(getParameter("longitude"))).doubleValue();}
	  catch (Exception ee) {}
	  
	  if (paramLat>-900.d && paramLon>-900.d) {
	    selectNoCity();
	    setLatLon(paramLat,paramLon);
	  }
	  
	  try {optionDisplayPlanets = getParameter("displayplanets").equals("y");}
	  catch (Exception ee) {}
	  
	  try {optionBlackAndWhiteOnly = getParameter("blackandwhiteonly").equals("y");}
	  catch (Exception ee) {}
	  
	  try {optionShowNSEW = getParameter("shownsew").equals("y");}
	  catch (Exception ee) {}
	  
	  try {optionDimmestMagnitude = (Double.valueOf(getParameter("dimmestmagnitude"))).doubleValue();}
	  catch (Exception ee) {}
	  
	  try {
	  initApplet(!(paramLat>-900.d && paramLon>-900.d));
	  } catch(Exception ee) {}
	  
	  repaint();
	}
	//===============================================================
	//				initVariables
	//===============================================================

   private void initVariables() {
     initVariablesA();
     initVariablesB();
     initVariablesC1();
     initVariablesC2();
     initVariablesD();
   }
   	 
   private void initVariablesA() {
		 
		 
	    tzOffset = (TimeZone.getDefault()).getRawOffset(); //-- in milliseconds!!!!!
		 sky = new Panel() {
		  public void paint(Graphics g) {
		    updateSky();
	     }
		};

		 localTimeStaticText = new StaticText(txt.localTime);
		 latitudeStaticText = new StaticText(txt.latitude);
		 longitudeStaticText = new StaticText(txt.longitude);
		 homePageAndAuthorStaticText = new StaticText("Planet Finder applet by B. Crowell, www.lightandmatter.com, (c) 1999.");
		 homePageAndAuthor2StaticText = new StaticText("The source code is open, and I welcome help with translating the applet into new languages!");
		
		 dstPanel = new Panel();
		 dstCheckbox = new Checkbox(txt.daylightSavings);	
		 dstCheckboxGroup = new CheckboxGroup();
		 dstAutoCheckbox = new Checkbox(txt.auto,dstCheckboxGroup,true);
		 dstManualCheckbox = new Checkbox(txt.manual,dstCheckboxGroup,false);
		
		 latNSCheckboxGroup = new CheckboxGroup();
		 latNCheckbox = new Checkbox(txt.northLetter,latNSCheckboxGroup,true);
		 latSCheckbox = new Checkbox(txt.southLetter,latNSCheckboxGroup,false);
		
		 lonEWCheckboxGroup = new CheckboxGroup();
		 lonECheckbox = new Checkbox(txt.eastLetter,lonEWCheckboxGroup,false);
		 lonWCheckbox = new Checkbox(txt.westLetter,lonEWCheckboxGroup,true);
		
		 cityChoice = new Choice();
		
   }
   private void initVariablesB() {
		 viewCheckboxGroup = new CheckboxGroup();
		 skyCheckbox = new Checkbox(txt.sky,viewCheckboxGroup,true);
		 innerSolarSystemCheckbox = new Checkbox(txt.innerSolarSystem,viewCheckboxGroup,false);
		 outerSolarSystemCheckbox = new Checkbox(txt.outerSolarSystem,viewCheckboxGroup,false);
		 monthChoice = new Choice();
		 hourText = new TextField("    ");
		 minuteText = new TextField("    ");
		 dayText = new TextField("    ");
		 yearText = new TextField("       ");
		 dayPlusButton = new Button("+");
		 dayMinusButton = new Button("-");
		 monthPlusButton = new Button("+");
		 monthMinusButton = new Button("-");
		 yearPlusButton = new Button("+");
		 yearMinusButton = new Button("-");
		 yourLatText = new TextField("34.00");
		 yourLongText = new TextField("-118.00");
		 updateButton = new Button(txt.update);
		 validDate = true;
		 validLatLon = true;
		 nakedEyeOnlyCheckbox = new Checkbox(txt.nakedEyePlanetsOnly);
		 timeZoneLabel = new StaticText(txt.timeZone);
		 timeZoneText = new TextField("    "); //-- time zone actually initialized by init(), below
		 validTimeZoneText = false;
		 skyBlue = new Color(.5f,.7f,1.f);
		 duskSkyColor = Color.blue.brighter();
		 optionDisplayPlanets = true;
		 optionDimmestMagnitude = 4.d;
		 optionBlackAndWhiteOnly = false;
		 optionShowNSEW = true;
		 optionShowMagnitudes = true;
		 optionGiveLanguageChoice = false;
	}	
   private void initVariablesC1() {
		 gcalendar = new GregorianCalendar();//-- sets it to the current date by default
		 gCalendar2000 = new GregorianCalendar(2000,0,1,0,0,0);
		
		 controls = new Panel();
		 controls2 = new Panel();
		 timePanel = new Panel();
		 locationPanel = new Panel();
		 latPanel = new Panel();
		 lonPanel = new Panel();
		 viewCheckboxPanel = new Panel();
	   // elements of orbits, taken from planetary fact sheets, NASA web site
		//Planet name = new Planet("name",mass,siderealperiod,a,e,incl,longascnode,longperi,meanlong);
	/*
	    Mercury:   -0.36 + 5*log10(r*R) + 0.027 * FV + 2.2E-13 * FV**6
	    Venus:     -4.34 + 5*log10(r*R) + 0.013 * FV + 4.2E-7  * FV**3
	    Mars:      -1.51 + 5*log10(r*R) + 0.016 * FV
	    Jupiter:   -9.25 + 5*log10(r*R) + 0.014 * FV
	    Saturn:    -9.0  + 5*log10(r*R) + 0.044 * FV + ring_magn
	    Uranus:    -7.15 + 5*log10(r*R) + 0.001 * FV
	    Neptune:   -6.90 + 5*log10(r*R) + 0.001 * FV

	*/
	}	
   private void initVariablesC2() {
		 mercury = new Planet(txt.planetNames[0],	0.0558,87.969d,0.38709893d,0.20563069d,7.00487d,48.33167d,77.45645d,252.25084d,
																		true,-0.36,0.027,2.2e-13,6.);
		 venus = new Planet(txt.planetNames[1],		0.815,224.701d,0.72333199,0.00677323,3.39471,76.68069,131.53298,181.97973,
																		true,-4.34,0.013,4.2e-7,3.);
		 earth = new Planet(txt.planetNames[2],		1.,365.256d,1.00000011d,0.01671022d,0.00005d,-11.26064d,102.94719d,100.46435d,
																		false,0.,0.,0.,0.);
		 mars = new Planet(txt.planetNames[3],			0.1075,686.980,1.52366231,0.09341233,1.85061,49.57854,336.04084,355.45332,
																		true,-1.51,0.016,0.,1.);
		 jupiter = new Planet(txt.planetNames[4],	317.83,4332.589,5.20336301,0.04839266,1.30530,100.55615,14.75385,34.40438,
																		true,-9.25,0.014,0.,1.);
		 saturn = new Planet(txt.planetNames[5],	95.147,10759.22,9.53707032,0.05415060,2.48446,113.71504,92.43194,49.94432,
																		true,-9.0,0.044,0.,1.);
		 uranus = new Planet(txt.planetNames[6],	14.54,30685.4,19.19126393,0.04716771,0.76986,74.22988,170.96424,313.23218,
																		true,-7.15,0.001,0.,1.);
		 neptune = new Planet(txt.planetNames[7],	17.23,60189.,30.06896348,0.00858587,1.76917,131.72169,44.97135,304.88003,
																		true,-6.90,0.001,0.,1.);
		 pluto = new Planet(txt.planetNames[8],		0.0022,90465.,39.48168677,0.24880766,17.14175,110.30347,224.06676,238.92881d,
																		false,0.,0.,0.,0.);
   }
   private void initVariablesD() {
		
		 planets[0] = mercury; planets[1] = venus; planets[2] = earth; planets[3] = mars; planets[4] = jupiter;
		 planets[5] = saturn; planets[6] = uranus; planets[7] = neptune; planets[8] = pluto;
		 //planets[9] = moon; ///----- Why did I do this? Do I really need this here?
		 sunColor = Color.yellow;
		 moonIlluminatedColor = Color.white;
		 moonUnilluminatedColor = Color.gray;
		 

   }

	//===============================================================
	//				initApplet
	//===============================================================
	private void initApplet(boolean guessCity) {

	  hourText.setEditable(true);
	  minuteText.setEditable(true);
	  dayText.setEditable(true);
	  yearText.setEditable(true);
	  yourLatText.setEditable(true);
	  yourLongText.setEditable(true);

	  J2000EpochMinus1970Epoch = gCalendar2000.getTime().getTime();
	  
	  
	  setMonthNames(txt);
	  
	  cityChoice.addItem("?");
	  for (int i=0; i<CityList.cityList.length; i++) {
	    cityChoice.addItem(CityList.cityList[i].name);
	  }
	  
	  
	  makeTextReflectDate();
	  
	  earthBasisX = Cartesian.xHat();
	  earthBasisZ = new Cartesian(0.d,Math.sin(earthRotationTilt),Math.cos(earthRotationTilt));
	  earthBasisY = earthBasisZ.crossProduct(earthBasisX);

	  

	  theLayout = new Layout(this);

	
	  respondToSelectionOfTimeZone(guessCity);
	  respondToChangeInInputs();
	  updateTimeZone();
	  updateSky();
	  
	}
		
	//===============================================================
	//				Java 1.0 event handling
	//===============================================================
	  public boolean handleEvent(Event ee) {
	    boolean handledSuccessfully;
	    switch (ee.id) {
	      /*
	      case Event.KEY_RELEASE:
	        if (ee.target==yourLatText || ee.target==yourLongText) selectNoCity();
	        respondToChangeInInputs();
	        handledSuccessfully = true;
	        break;
	      */
	      case Event.ACTION_EVENT:
	        if (ee.target==dstCheckbox) dstManualCheckbox.setState(true);
	        if (ee.target==cityChoice) respondToSelectionOfCity(cityChoice.getSelectedItem());
	        if (ee.target==timeZoneText) respondToSelectionOfTimeZone(true);
	        if (ee.target==lonECheckbox || ee.target==lonWCheckbox || ee.target==latNCheckbox || ee.target==latSCheckbox) selectNoCity();
	        if (ee.target==yourLatText || ee.target==yourLongText) selectNoCity();
	        respondToChangeInInputs();
	        handledSuccessfully = true;
	        break;
	      default:
	        handledSuccessfully = false;
	        break;
	    }
	    return handledSuccessfully;
	  }
	
	//===============================================================
	//				setMonthNames
	//===============================================================
	private void setMonthNames(Text txt) {
	  monthChoice.removeAll();
	  for (int month=0; month<=11; month++) {
	    monthChoice.addItem(txt.monthNames[month]);
	  }
	}
	
	//===============================================================
	//				setPlanetNames
	//===============================================================
	private void setPlanetNames(Text txt) {
	  for (int i= 0; i<planets.length; i++) {
	    planets[i].name = txt.planetNames[i];
	  }
	  moon.name = txt.moon; 
	}

	
	//===============================================================
	//				selectNoCity
	//===============================================================
	private void selectNoCity() {
	  cityChoice.select("?");
	}
	
	//===============================================================
	//				respondToSelectionOfTimeZone
	//===============================================================
	private void respondToSelectionOfTimeZone(boolean guessCity) {
	  double tzHours;
	  try {
	    tzHours = Double.valueOf(timeZoneText.getText()).doubleValue();
	  }
	  catch (Exception ee) {
	    selectNoCity();
		  return;
	  }
	  if (guessCity) {
		  try {
		    String n;
		    n = CityList.biggestCityInTimeZone(tzHours,lang).name;
		    cityChoice.select(n);
		    respondToSelectionOfCity(n);
		  }
		  catch (Exception ee) {
		    selectNoCity();
		    setLatLon(0.d,tzHours*15.d);
		  }
	  }
	}
	
	//===============================================================
	//				respondToSelectionOfCity
	//===============================================================
	private void respondToSelectionOfCity(String name) {
	  City c = null;
	  for (int i=0; i<CityList.cityList.length; i++) {
	    if (CityList.cityList[i].name.equals(name))
	      c = CityList.cityList[i];
	  }
	  if (c==null) return;
	  setLatLon(c.latDegrees,c.lonDegrees);
	  setTimeZoneText(c.timeZone*3600000.d);
	}
	
	//===============================================================
	//				setLatLon
	//===============================================================
	private void setLatLon(double lat,double lon) {
	  // --latitude
	  if (Math.abs(lat)>1.e-20)
	    yourLatText.setText(String.valueOf(Math.abs(lat)));
	  else
	    yourLatText.setText("0");   // workaround for bug in String.valueOf
	  if (lat<0)
	    latSCheckbox.setState(true);
	  else
	    latNCheckbox.setState(true);
	  // --longitude
	  if (Math.abs(lon)>1.e-20)
	    yourLongText.setText(String.valueOf(Math.abs(lon)));
	  else
	    yourLongText.setText("0");  // workaround for bug in String.valueOf
	  if (lon<0)
	    lonWCheckbox.setState(true);
	  else
	    lonECheckbox.setState(true);
	}
	//===============================================================
	//				setTimeZoneText
	//===============================================================
	private void setTimeZoneText(double tzOffset) {
	  if (Math.abs(tzOffset)>1.e-20) {
		  if ( Math.abs(Math.round(tzOffset/3600000.d)-tzOffset/3600000.d)<0.1d)
		    timeZoneText.setText(String.valueOf(Math.round(tzOffset/3600000.d)));
		  else
		    timeZoneText.setText(String.valueOf(0.1d*Math.round(10.d*tzOffset/3600000.d)));
	  }
	  else
	    timeZoneText.setText("0");  // workaround for bug in String.valueOf
	  
	  validTimeZoneText = true;

	}
	
	//===============================================================
	//				makeTextReflectDate
	//===============================================================
	private void makeTextReflectDate() {
	  yearText.setText(String.valueOf(gcalendar.get(Calendar.YEAR)));
	  monthChoice.select(gcalendar.get(Calendar.MONTH));
	  dayText.setText(String.valueOf(gcalendar.get(Calendar.DATE)));
	  int hr = gcalendar.get(Calendar.HOUR);
	  if (gcalendar.get(Calendar.AM_PM)==Calendar.PM) hr = hr+12;
	  hourText.setText(String.valueOf(hr));
	  int minutes = gcalendar.get(Calendar.MINUTE);
	  if (minutes>=10)
	    minuteText.setText(String.valueOf(minutes));
	  else
	    minuteText.setText("0"+String.valueOf(minutes));
	}
	

	//===============================================================
	//				respondToChangeInInputs
	//===============================================================
	private void respondToChangeInInputs() {
	  try {
	    gcalendar.set(
		    Integer.parseInt(yearText.getText()),
		    monthChoice.getSelectedIndex(),
		    Integer.parseInt(dayText.getText()),
		    Integer.parseInt(hourText.getText()),
		    Integer.parseInt(minuteText.getText())
	    );
	    validDate = true;
	  }
	  catch (NumberFormatException ee) {
	    validDate = false;
	  }
	  try {
	    longitude = Math.abs(Double.valueOf(yourLongText.getText()).doubleValue()*Math.PI/180.d);
	    if (lonWCheckbox.getState())
	      longitude = -longitude;
	    latitude = Math.abs(Double.valueOf(yourLatText.getText()).doubleValue()*Math.PI/180.d);
	    if (latSCheckbox.getState())
	      latitude = -latitude;
	    validLatLon = true;
	  }
	  catch (NumberFormatException ee) {
	    validLatLon = false;
	  }
	  updateTimeZone();
	  if (validDate && dstAutoCheckbox.getState())
	    dstCheckbox.setState((TimeZone.getDefault()).inDaylightTime(gcalendar.getTime()));
	  updateSky();
	}
	
	//===============================================================
	//				updateTimeZone
	//===============================================================
	private void updateTimeZone() {
	  try {
	    tzOffset=(new Long(Math.round(Double.valueOf(timeZoneText.getText()).doubleValue()*3600000.d))).longValue();
	    validTimeZoneText = true;
	  }
	  catch (NumberFormatException ee) {
	    validTimeZoneText = false;
	  }
	}

	//===============================================================
	//				updateSky
	//===============================================================
	private void updateSky() {
	  updateTimeZone();
	  if (validDate && validLatLon && validTimeZoneText) {
		  Graphics g = sky.getGraphics();
		  Rectangle skyPanelRect = sky.getBounds();
		  Point skyPanelCenter = new Point(skyPanelRect.width/2,skyPanelRect.height/2);
		  int skySide = skyPanelRect.width;
		  if (skyPanelRect.height<skySide) skySide=skyPanelRect.height;
		  Rectangle skyRect = new Rectangle(skyPanelCenter.x-skySide/2,0,skySide,skySide);
		  Point skyCenter = new Point(skyRect.x+skyRect.width/2,skyRect.y+skyRect.height/2);
		  
		  boolean dst = dstCheckbox.getState();
		  long dstMillis = 0;
		  //if (dst) dstMillis = -1000*3600;
		  // See comments at top of code, 3/5/00.
		  
		  long millisSince2000 = gcalendar.getTime().getTime() - J2000EpochMinus1970Epoch
		  		- tzOffset
		  		//-dstMillis;
		  		+dstMillis; //needs to be + sign? 9/29/99
		  double daysSince2000 = ((new Long(millisSince2000)).doubleValue())/(24.d*3600.d*1000.d);

	     // Kludge: update the moon's rapidly varying elements every time the user changes the time.
	     //Planet name = new Planet("name",siderealperiod,a,e,incl,longascnode,longperi,meanlong);
		  /* Schlyter's elements:
		    N = 125.1228 - 0.0529538083 * d
		    i = 5.1454
		    w = 318.0634 + 0.1643573223 * d
		    a = 60.2666  (Earth radii)
		    e = 0.054900
		    M = 115.3654 + 13.0649929509 * d
		  */
		  double moonN0 = 125.1228;
		  double moonw0 = 318.0634;
		  double moonM0 = 115.3654;
		  double moonN = 	moonN0 - 0.0529538083 * daysSince2000;
		  double moonw =  moonw0 + 0.1643573223 * daysSince2000;
		  //double MoonM = 	moonM0 + 13.0649929509 * daysSince2000;
	     Planet blah = new Planet(txt.moon,1.23e-02,27.322,2.569519e-03,0.0549,5.145,
	     			moonN,
	     			moonN+moonw, //-- lon of peri = N + w
	     			/*moonN0+moonw0+moonM0*/ // meanlong2000 = N+w+M
	     			218.32d,	//use Dave's data instead
	     			false,0.,0.,0.,0.
			);
			moon = blah;
		  
		  if (skyCheckbox.getState()) {
			  //================================================================================
			  //================================================================================
			  //					sky
			  //================================================================================
			  //================================================================================
			  
			  final int nsewMatrixXE = 0;
			  final int nsewMatrixYE = -1;
			  final int nsewMatrixXN = 1;
			  final int nsewMatrixYN = 0;
			  
			  
			  boolean err = false;
			  			  
			  Cartesian zenithAt0Lat0Lon,eastAt0Lat0Lon,northAt0Lat0Lon;
			  double tEarthRot = daysSince2000; //== time since Greenwich's plane of long contained xHat
			  double phaseEarthRot = (tEarthRot/earthRotationPeriod)*2.*Math.PI - earthRotationPhase;
			  zenithAt0Lat0Lon = earthBasisX.scalarMult(Math.cos(phaseEarthRot)).plus(earthBasisY.scalarMult(Math.sin(phaseEarthRot)));
			  eastAt0Lat0Lon = earthBasisZ.crossProduct(zenithAt0Lat0Lon);
			  northAt0Lat0Lon = zenithAt0Lat0Lon.crossProduct(eastAt0Lat0Lon);
			  Cartesian zenithAtEquator,eastAtEquator,northAtEquator;
			  zenithAtEquator = zenithAt0Lat0Lon.scalarMult(Math.cos(longitude)).plus(eastAt0Lat0Lon.scalarMult(Math.sin(longitude)));
			  eastAtEquator = eastAt0Lat0Lon.scalarMult(Math.cos(longitude)).plus(zenithAt0Lat0Lon.scalarMult(-Math.sin(longitude)));
			  northAtEquator = northAt0Lat0Lon;
			  Cartesian zenith,east,north;
			  zenith = zenithAtEquator.scalarMult(Math.cos(latitude)).plus(northAtEquator.scalarMult(Math.sin(latitude)));
			  east = eastAtEquator;
			  north = zenith.crossProduct(east);

			  Cartesian earthPosition = earth.position(daysSince2000,0.000001d,err)
			  										.plus(zenith.scalarMult(4.3e-05d)); // This is the correction for parallax due to the
			  																				// earth's rotation.
			  Cartesian earthToMoon = moon.position(daysSince2000,0.000001d,err);
			  //--- Nonkeplerian perturbations for the moon:
			  //First convert vector to spherical coords:
			  double moonRad = earthToMoon.magnitude();
			  double moonLat = Math.acos(-earthToMoon.z/moonRad)-Math.PI/2.d;
			  double moonLon = Math.atan(earthToMoon.y/earthToMoon.x);
			  if (moonLon< -Math.PI/2.) moonLon = moonLon+Math.PI;
			  if (moonLon> Math.PI/2.) moonLon = moonLon-Math.PI;
			  if (earthToMoon.x<0.) moonLon = moonLon+Math.PI;
			  if (moonLon<0.) moonLon = moonLon + 2*Math.PI;
			  if (moonLon>2.*Math.PI) moonLon = moonLon - 2*Math.PI;
			  
			  moonLon = moonLon + MoonPerturbations.moonLongitudeCorrectionDegrees(daysSince2000)*Math.PI/180.;
			  moonLat = moonLat + MoonPerturbations.moonLatitudeCorrectionDegrees(daysSince2000)*Math.PI/180.;
			  
			  earthToMoon = Cartesian.latLongToUnitVector(moonLat,moonLon).scalarMult(moonRad);

			  
			  Cartesian moonPosition = earthToMoon.plus(earthPosition);
			  
			  double sunAngleFromZenithDegrees = (Math.PI-zenith.angleBetween(earthPosition))*180./Math.PI;
			  boolean nightTime = sunAngleFromZenithDegrees>90.;
			  boolean dusk = sunAngleFromZenithDegrees<90. && sunAngleFromZenithDegrees>80.;
			  
			  g.setColor(Color.white);
			  g.fillRect(skyRect.x,skyRect.y,skyRect.width,skyRect.height);
			  
			  Color skyColor;
			  Color textOnSkyColor;
			  if (nightTime || optionBlackAndWhiteOnly) {
			    skyColor = Color.black;
			    textOnSkyColor = Color.blue;
			  }
			  else {
			    if (!dusk)
			      skyColor = skyBlue;
			    else
			      skyColor = duskSkyColor;
			    textOnSkyColor = Color.black;
			  }
			  g.setColor(skyColor);
			  g.fillOval(skyRect.x,skyRect.y,skyRect.width,skyRect.height);
			  if (optionShowNSEW) {
				  g.setColor(textOnSkyColor);
				  g.drawString(txt.northLetter,skyCenter.x+skySide/2-12,skyCenter.y);
				  g.drawString(txt.southLetter,skyCenter.x-skySide/2+12,skyCenter.y);
				  g.drawString(txt.eastLetter,skyCenter.x,skyCenter.y-skySide/2+12);
				  g.drawString(txt.westLetter,skyCenter.x,skyCenter.y+skySide/2-12);
			  }
			  
			  //================================================================================
			  //		Show stars
			  //================================================================================
			  for (int i=0; i<BrightStarCatalog.mag.length; i++) {
			    double ma = BrightStarCatalog.mag[i];
			    if (ma<optionDimmestMagnitude) {
			      double ra = BrightStarCatalog.rightAscensionInRadians[i];
			      double dec = BrightStarCatalog.declinationInRadians[i];
			      double cd = Math.cos(dec);
			      double earthBasisXComp = cd*Math.cos(ra);
			      double earthBasisYComp = cd*Math.sin(ra);
			      double earthBasisZComp = Math.sin(dec);
			      Cartesian directionToStar = earthBasisX.scalarMult(earthBasisXComp)
			      									.plus(earthBasisY.scalarMult(earthBasisYComp))
			      									.plus(earthBasisZ.scalarMult(earthBasisZComp));
			      
			      double zenithComp = directionToStar.dotProduct(zenith);
				   if (zenithComp>0.) {
			          double angleDownFromZenith = Math.acos(zenithComp);
			          double xx = directionToStar.dotProduct(east);
			          double yy = directionToStar.dotProduct(north);
			          double rescaleForEqualAreaProj = 1.414214*Math.sin(angleDownFromZenith/2.)/Math.sqrt(xx*xx+yy*yy);
			          xx = xx*rescaleForEqualAreaProj;
			          yy = yy*rescaleForEqualAreaProj;
					    xx = .5 * xx * ((new Integer(skySide)).doubleValue());
					    yy = .5 * yy * ((new Integer(skySide)).doubleValue());
						 int ee = (new Long(Math.round(xx))).intValue();
						 int nn = (new Long(Math.round(yy))).intValue();
					    int xxx = skyCenter.x + nsewMatrixXE*ee + nsewMatrixXN*nn;
					    int yyy = skyCenter.y + nsewMatrixYE*ee + nsewMatrixYN*nn;
					    drawDot(g,magToDotColor(ma),xxx,yyy,magToDotDiam(ma));
				    }
				    }
			  }
			  
			  //================================================================================
			  //		Show planets, moon & sun
			  //================================================================================
			  Cartesian planetsCenterOfMass = new Cartesian(0.,0.,0.);
			  Cartesian currentPosition[] = new Cartesian[planets.length];
			  //Cartesian currentPosition[] = new Cartesian[9];
			  for (int i= 0; i<planets.length; i++) {
			     currentPosition[i] = planets[i].position(daysSince2000,0.000001d,err);
				  planetsCenterOfMass = planetsCenterOfMass.plus(currentPosition[i].scalarMult(planets[i].mass));
			  }
			  Cartesian sunPosition =  planetsCenterOfMass.scalarMult(-1.d/sunMass);
			  for (int i= -1; i<planets.length; i++) { //==moon is when you do earth, sun is i== -1
			    Cartesian directionFromEarth;
			    Color c;
			    String name;
			    boolean isSun,isMoon,omitBecauseNotNakedEye;
			    isSun=false;
			    isMoon=false;
			    omitBecauseNotNakedEye = false;
			     
				  if (i!= -1 && planets[i]!=earth) {
				    // a planet other than earth
				    directionFromEarth = (currentPosition[i].plus(earthPosition.scalarMult(-1.d))).makeUnitLength();
				    c = planetColors[i];
				    name = planets[i].name;
				    omitBecauseNotNakedEye = nakedEyeOnlyCheckbox.getState() && !nakedEyePlanet[i];
				  }
				  else {
				    if (i== -1) {
					    //-- sun
					    directionFromEarth = (sunPosition.plus(earthPosition.scalarMult(-1.d))).makeUnitLength();
					    c = sunColor;
					    name = txt.sun;
					    isSun = true;
				    }
				    else {
				    	 //-- moon
				       directionFromEarth = (moonPosition.plus(earthPosition.scalarMult(-1.d))).makeUnitLength();
				    	 name = txt.moon;
				    	 isMoon=true;
				    	 c = moonIlluminatedColor;
				    }
				  }
				  if (!err) {
				    double zenithComp = directionFromEarth.dotProduct(zenith);
				    if (zenithComp>0. && !omitBecauseNotNakedEye) {
			          double angleDownFromZenith = Math.acos(zenithComp);
			          double xx = directionFromEarth.dotProduct(east);
			          double yy = directionFromEarth.dotProduct(north);
			          double rescaleForEqualAreaProj = 1.414214*Math.sin(angleDownFromZenith/2.)/Math.sqrt(xx*xx+yy*yy);
			          xx = xx*rescaleForEqualAreaProj;
			          yy = yy*rescaleForEqualAreaProj;
					    xx = .5 * xx * ((new Integer(skySide)).doubleValue());
					    yy = .5 * yy * ((new Integer(skySide)).doubleValue());
						 int ee = (new Long(Math.round(xx))).intValue();
						 int nn = (new Long(Math.round(yy))).intValue();
					    int xxx = skyCenter.x + nsewMatrixXE*ee + nsewMatrixXN*nn;
					    int yyy = skyCenter.y + nsewMatrixYE*ee + nsewMatrixYN*nn;
						 /*
			  		    if (i==4) { //jup
			  				   g.setColor(Color.red);
			  				   g.drawString(String.valueOf(Math.asin(zenithComp)*180./Math.PI),skyCenter.x-30,skyCenter.y-(skySide*2)/5);
			  		    }
			  		    */
					    int dotRadius;
					    if (isSun || isMoon) dotRadius = 7; else dotRadius = 4;
					    if (optionDisplayPlanets) {
						    if (!isMoon) {
						      g.setColor(c);
						      g.fillOval(xxx-dotRadius,yyy-dotRadius,dotRadius*2,dotRadius*2);
						      if (!nightTime) {
						        //-- Planet colors may not contrast sufficiently with sky, so outline them in black.
						        g.setColor(Color.black);
						        g.drawOval(xxx-dotRadius,yyy-dotRadius,dotRadius*2,dotRadius*2);
						      }
						    }
						    else {
						      //-- Draw the moon with the correct phase.
						      // The moon's terminator is in the plane perpendicular to the vector pointing from
						      // the sun to the moon, which is the vector moonPosition.
						      // Construct a unit vector moonVertex pointing from the center of the moon to either
						      // visible edge of the terminator:
						      Cartesian moonVertex = (earthToMoon.crossProduct(moonPosition)).makeUnitLength();
						      // Construct a unit vector centerTerminator pointing from the center of the moon to the
						      // center of the visible part of the terminator.
						      Cartesian centerTerminator = (moonPosition.crossProduct(moonVertex)).makeUnitLength();;
						      if (centerTerminator.dotProduct(earthToMoon)>0.)
						        centerTerminator = centerTerminator.scalarMult(-1.d);
						      // Construct a vector moonEdge pointing from the center of the moon to the center of the
						      // visible edge of the moon.
						      Cartesian moonEdge = (earthToMoon.crossProduct(moonVertex)).makeUnitLength();
						      if (moonEdge.dotProduct(moonPosition)>0.)
						        moonEdge = moonEdge.scalarMult(-1.d);
						      // Project the moon onto the sky:
						      Cartesian projOnSkyEast = (north.crossProduct(earthToMoon)).makeUnitLength();
						      Cartesian projOnSkyNorth = (earthToMoon.crossProduct(projOnSkyEast)).makeUnitLength();
						      // Create a polygon representing the illuminated part of the moon. Start
						      // with the terminator.
						      final int halfNPoly = 8;
						      Polygon poly = new Polygon(new int[halfNPoly*2],new int[halfNPoly*2],halfNPoly*2);
						      for (int j=0; j<=halfNPoly-1; j++) {
						        double theta = Math.PI*j;
						        theta = theta / (halfNPoly-1);
						        Cartesian v = moonVertex.scalarMult(Math.cos(theta)).plus(centerTerminator.scalarMult(Math.sin(theta)));
						        double xp = nsewMatrixXE*v.dotProduct(projOnSkyEast)+nsewMatrixXN*v.dotProduct(projOnSkyNorth);
						        xp = xp*dotRadius;
						        poly.xpoints[j] = xxx+(new Long(Math.round(xp))).intValue();
						        double yp = nsewMatrixYE*v.dotProduct(projOnSkyEast)+nsewMatrixYN*v.dotProduct(projOnSkyNorth);
						        yp = yp*dotRadius;
						        poly.ypoints[j] = yyy+(new Long(Math.round(yp))).intValue();
						      }
						      for (int j=0; j<=halfNPoly-1; j++) {
						        double theta = Math.PI*j;
						        theta = theta / (halfNPoly-1);
						        Cartesian v = moonVertex.scalarMult(-Math.cos(theta)).plus(moonEdge.scalarMult(Math.sin(theta)));
					           double xp = nsewMatrixXE*v.dotProduct(projOnSkyEast)+nsewMatrixXN*v.dotProduct(projOnSkyNorth);
						        xp = xp*dotRadius;
						        poly.xpoints[j+halfNPoly] = xxx+(new Long(Math.round(xp))).intValue();
						        double yp = nsewMatrixYE*v.dotProduct(projOnSkyEast)+nsewMatrixYN*v.dotProduct(projOnSkyNorth);
						        yp = yp*dotRadius;
						        poly.ypoints[j+halfNPoly] = yyy+(new Long(Math.round(yp))).intValue();
						      }
						      if (true) {
							      g.setColor(moonUnilluminatedColor);
							      g.fillOval(xxx-dotRadius,yyy-dotRadius,dotRadius*2,dotRadius*2);
							      g.setColor(moonIlluminatedColor);
							      g.fillPolygon(poly);
						      }
						      else { //-- draw outline of moon for debugging purposes
							      g.setColor(Color.white);
							      g.drawOval(xxx-dotRadius,yyy-dotRadius,dotRadius*2,dotRadius*2);
						      }
						    }
					    }
					    // Label planet with name. Normally we use the same color as the planet, but in the daytime that
					    // may not contrast sufficiently with the sky.
					    if (optionDisplayPlanets) {
						    if (!nightTime)
						      g.setColor(textOnSkyColor);
						    String magString = "";
						    if (!isMoon && !isSun && optionShowMagnitudes && planets[i].mag) {
						      double distanceFromSunAU = currentPosition[i].magnitude();
						      Cartesian vectorFromEarthToPlanet = currentPosition[i].plus(earthPosition.scalarMult(-1.d));
						      double distanceFromEarthAU = vectorFromEarthToPlanet.magnitude();
						      double fv = currentPosition[i].angleBetween(vectorFromEarthToPlanet);
						      magString = String.valueOf(
						        Math.round(10.*planets[i].magnitude(distanceFromSunAU,distanceFromEarthAU,fv))/10.
						      );
						    }
						    g.drawString(name+" "+magString,xxx+dotRadius+3,yyy);
					    }
				    }
				  }
			  }

		  }//--- end if sky
		  else { //--- not sky, must be solar system
			  //================================================================================
			  //================================================================================
			  //					solar system
			  //================================================================================
			  //================================================================================
		     boolean innerSystem = innerSolarSystemCheckbox.getState();
		     double scaleAU;
		     int dotRadius = 4;
		     Color c;
		     Cartesian rr;
		     String name;
		     boolean err = false;
		     if (innerSystem) scaleAU = 1.8; else scaleAU=45.;
			  g.setColor(Color.black);
			  g.fillRect(skyRect.x,skyRect.y,skyRect.width,skyRect.height);
			  for (int i= -1; i<planets.length; i++) {
			     boolean displayIt;
			     displayIt = (innerSystem && i<=3) || ((!innerSystem) && i!=0 && i!=1 && i!=3);
			     		// Avoid Mercury, Venus, & Mars if doing outer solar system.
			     if (displayIt) {
				     if (i == -1) {
				       c = sunColor;
				       dotRadius = 5;
				       rr = new Cartesian(0.d,0.d,0.d);
				       name = txt.sun;
				     }
				       else {
					    rr = planets[i].position(daysSince2000,0.000001d,err);
					    c = planetColors[i];
					    name = planets[i].name;
					  }
					  if (!err) {
				          double xx = rr.x;
				          double yy = rr.y;
				          xx = xx/scaleAU;
				          yy = yy/scaleAU;
						    xx = .5 * xx * ((new Integer(skySide)).doubleValue());
						    yy = .5 * yy * ((new Integer(skySide)).doubleValue());
						    int xxx = skyCenter.x + (new Long(Math.round(xx))).intValue();
						    int yyy = skyCenter.y - (new Long(Math.round(yy))).intValue();
						    g.setColor(c);
						    g.fillOval(xxx-dotRadius,yyy-dotRadius,dotRadius*2,dotRadius*2);
						    g.drawString(name,xxx+dotRadius+3,yyy);
					  }
				  }
			  }
		    
		    
		    
		    
		  }
	  }
	}

	//===============================================================
	//				drawDot
	//===============================================================
	private void drawDot(Graphics g,Color c,int x,int y,int diam) {
	  g.setColor(c);
	  g.fillOval(x-diam/2,y-diam/2,diam,diam);
	}
		
	//===============================================================
	//				magToDotDiam
	//===============================================================
	private int magToDotDiam(double mag) {
	  int d;
	  d = 1;
	  if (mag<2.2) d=2;
	  if (mag<1.4) d=3;
	  if (mag<0.6) d=4;
	  if (mag<-0.2) d=5;
	  return d;
	}
	
	//===============================================================
	//				magToDotColor
	//===============================================================
	private Color magToDotColor(double mag) {
	  if (mag<2.8)
	    return Color.white;
	  else
	    return Color.gray;
	}
		
}
