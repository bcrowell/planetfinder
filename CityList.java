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


//City ( name, country, popMillions, latDegrees, lonDegrees,timeZone,languageCode)
// Cutoff is 3 million pop, but I've also included some that are smaller than that, because,
// e.g., I want the Polish version to include Warsaw.

class CityList {
  static public City cityList[] = {
  
new City("Alexandria",	"Egypt",					3.38,	31.,	30.,	2.),
new City("Ankara",	"Turkey",					3.03,	40.,	33.,	2.,"tr"),
new City("Athens",	"Greece",					3.10,	38.,	23.,	2.,"el"),
new City("Atlanta",	"United States",			3.14,	34.,	-84.,	-5.,"en"),

new City("Baghdad",	"Iraq",						3.84,	33.,	44.,	3.),
new City("Bangalore",	"India",					4.09,	13.,	78.,	5.5,"en"),
new City("Bangkok",	"Thailand",					5.88,	13.,	100.,	7.),
new City("Beijing",	"China",						12.4,	40.,	116.,	8.,"zh"),
new City("Berlin",	"Germany",					3.48,	52.,	13.,	1.,"de"),
new City("Bogota",	"Colombia",					5.03,	4.6,	-74.,	-5.,"es"),
new City("Boston",	"United States",			3.21,	42.,	-71.,	-5.,"en"),
new City("Bombay",	"India",						15.1,	19.,	73.,	5.5,"en"),
new City("Buenos Aires",	"Argentina",		11.0,	-34.,	-58.,	-3.,"es"),

new City("Cairo",	"Egypt",							9.66,	30.,	31.,	2.),
new City("Calcutta",	"India",						11.7,	23.,	88.,	5.5,"en"),
new City("Chicago",	"United States",			7.56,	42.,	-87.,	-6.,"en"),
new City("Chongqing",	"China",					3.87,	29.,	106.,	8.,"zh"),
new City("Copenhagen",	"Denmark",				1.4,	56.,	12.,	1.,"da"),

new City("Delhi",	"India",							9.88,	29.,	77.,	5.5,"en"),
new City("Detroit",	"United States",			4.31,	42.,	-83.,	-5.,"en"),
new City("Dhaka",	"Bangladesh",					7.83,	24.,	90.,	6.),

new City("Guangzhou",	"China",					3.75,	23.,	113.,	8.,"zh"),

new City("Harbin",	"China",						3.12,	46.,	127.,	8.,"zh"),
new City("Hanoi",	"Vietnam",						3.06,	21.,	106.,	7.),
new City("Ho Chi Minh City",	"Vietnam",		3.92,	11.,	107.,	7.),
new City("Hong Kong",	"China",					6.20,	22.,	114.,	8.,"zh"),
new City("Houston",	"United States",			3.53,	30.,	-95.,	-6.,"en"),
new City("Hyderabad",	"India",					4.28,	17.,	79.,	5.5,"en"),

new City("Istanbul",	"Turkey",					7.49,	41.,	28.,	2.),

new City("Jakarta",	"Indonesia",				11.5,	-6.,	107.,	7.,"id"),

new City("Karachi",	"Pakistan",					9.86,	25.,	67.,	5.),
new City("Kinshasa",	"Zaire",					3.80,	-4.,	15.,	1.),
new City("Kuala Lumpur","Malaysia",				        1.6,	3.,	101.,	8.,"ms"),

new City("Lagos",	"Nigeria",						10.3,	6.,	7.,	1.),
new City("Lahore",	"Pakistan",					5.08,	32.,	74.,	5.),
new City("Lima",	"Peru",							6.6,	-12.,	-77.,	-5.,"es"),
new City("London",	"United Kingdom",			6.97,	51.,	0.,	0.,"en"),
new City("Los Angeles",	"United States",		12.41,	34.,	-118.,	-8.,"en"),

new City("Madras",	"India",						5.36,	13.,	80.,	5.5,"en"),
new City("Madrid",	"Spain",						3.04,	40.,	-3.,	1.,"es"),
new City("Melbourne",	"Australia",			3.19,	-37.,	145.,	10.,"en"),
new City("Manila",	"Philippines",				9.28,	15.,	121.,	8.),
new City("Mexico City",	"Mexico",				15.6,	19.,	-99.,	-6.,"es"),
new City("Montreal",	"Canada",					3.34,	43.,	-73.,	-5.),
new City("Moscow",	"Russia",					9.23,	56.,	38.,	3.),

new City("New York",	"United States",			16.3,	41.,	-74.,	-5.,"en"),


new City("Osaka",	"Japan",							10.6,	35.,	136.,	9.,"ja"),

new City("Paris",	"France",						9.47,	49.,	2.,	1.,"fr"),
new City("Philadelphia",	"United States",	4.94,	40.,	-75.,	-5.,"en"),
new City("Pusan",	"South Korea",					3.80,	35.,	129.,	9.,"ko"),

new City("Rio de Janeiro",	"Brazil",			9.89,	-23.,	-43.,	-3.,"pt"),
new City("Rome",	"Italy",							2.7,	42.,	12.5,	1.,"it"),

new City("San Francisco",	"United States",	3.87,	37.,	-122.,	-8.,"en"),
new City("Santiago",	"Chile",						4.63,	-33.,	-70.,	-4.,"es"),
new City("S\u00e3o Paolo",	"Brazil",				16.4,	-23.,	-46.,	-3.,"pt"),
new City("Seoul",	"South Korea",					11.6,	38.,	127.,	9.,"ko"),
new City("Shanghai",	"China",						15.1,	31.,	121.,	8.,"zh"),
new City("Shenyang",	"China",						4.05,	42.,	123.,	8.,"zh"),
new City("St. Petersburg",	"Russia",			4.88,	60.,	30.,	3.),
new City("Sydney",	"Australia",				3.71,	-33.,	152.,	10.,"en"),


new City("Tehran",	"Iran",						6.75,	36.,	51.,	3.5),
new City("Tianjin",	"China",						10.7,	39.,	117.,	8.,"zh"),
new City("Tokyo",	"Japan",							26.8,	36.,	140.,	9.,"ja"),
new City("Toronto",	"Canada",					4.34,	43.,	-79.,	-5.,"en","fr"),

new City("Warszawa",    "Poland",                       1.7,    52.,   21.,    1,"pl"),

new City("Washington, DC",	"United States",	4.36,	39.,	-77.,	-5.,"en"),
new City("Wuhan",	"China",							3.87,	31.,	114.,	8.,"zh"),

  
  
  
  };

  static public City biggestCityInTimeZone(double timeZone,Language lang) {
    double biggest = 0.;
    boolean biggestSoFarMatchedLanguage = false;
    City result = null;
    for (int i=0; i<cityList.length; i++) {
      City c = cityList[i];
      boolean matchesLanguage = c.isInLanguageList(lang);
      if (Math.abs(c.timeZone-timeZone)<0.1) {
         if (c.popMillions>biggest || (matchesLanguage && !biggestSoFarMatchedLanguage)) {
            result = c;
            biggest = c.popMillions;
            biggestSoFarMatchedLanguage = biggestSoFarMatchedLanguage || matchesLanguage;
         }
      }
    }
    return result;
  }

}
