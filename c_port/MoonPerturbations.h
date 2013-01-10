/*  This file is a part of PlanetFinder. PlanetFinder is a Series 60 application
    for locating the planets in the sky. It is a porting of a Java Applet by 
	Benjamin Crowell to the Series 60 Developer Platform. See 
	http://www.lightandmatter.com/area2planet.shtml for the original version.
	Java Applet: Copyright (C) 2000, Benjamin Crowell
    Series 60 Version: Copyright (C) 2004, Kostas Giannakakis

    PlanetFinder is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    PlanetFinder is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with PlanetFinder; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

#ifndef __MOONPERTURBATIONS_DEF__
#define __MOONPERTURBATIONS_DEF__

//#include <e32def.h>
//#include <e32math.h>
#include <math.h>

//#define M_PI  3.14159265358979
//#define M_PI KPi

class MoonPerturbations 
{
public: 
	static float moonLongitudeCorrectionDegrees(float d) 
	{ 
		//==d=days since 1/1/2000
		float ns=0,ws=0,ms=0,nm=0,wm=0,mm=0;
		sunNwM(ns,ws,ms,d);
		moonNwM(nm,wm,mm,d);
		float ls = ms+ns+ws;
		float lm = mm+nm+wm;
		float bigD = lm-ls;
		//float f = lm-nm;
		return -1.274*sind(mm-2*bigD)+0.658*sind(2*bigD)-0.186*sind(ms);
	};

	static float moonLatitudeCorrectionDegrees(float d) 
	{ //==d=days since 1/1/2000
		float ns=0,ws=0,ms=0,nm=0,wm=0,mm=0;
		sunNwM(ns,ws,ms,d);
		moonNwM(nm,wm,mm,d);
		float ls = ms+ns+ws;
		float lm = mm+nm+wm;
		float bigD = lm-ls;
		float f = lm-nm;
		return -0.173*sind(f-2*bigD)-0.055*sind(mm-f-2*bigD)-0.046*sind(mm+f-2*bigD);
	}
private:
	static void sunNwM(float n, float w, float m,float d) 
	{
		n = 0.0;
		w = 282.9404 + 4.70935E-5 * d;
		m = 356.0470 + 0.9856002585 * d;
	};

	static void moonNwM(float n, float w, float m,float d) 
	{
		n = 125.1228 - 0.0529538083 * d;
		w = 318.0634 + 0.1643573223 * d;
		m = 115.3654 + 13.0649929509 * d;
	};

	static float sind(float x) 
	{
		float res;

		res = sin(x*M_PI/180);
		
		return(res);
		//return Math.sin(x*Math.PI/180.d);
	};
};

#endif //__MOONPERTURBATIONS_DEF__