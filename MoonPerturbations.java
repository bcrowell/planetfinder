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



import java.lang.Math;

/*
Courtesy of PaulSchlyter.
*/
class MoonPerturbations {
	public static double moonLongitudeCorrectionDegrees(double d) { //==d=days since 1/1/2000
	  double ns=0,ws=0,ms=0,nm=0,wm=0,mm=0;
	  sunNwM(ns,ws,ms,d);
	  moonNwM(nm,wm,mm,d);
	  double ls = ms+ns+ws;
	  double lm = mm+nm+wm;
	  double bigD = lm-ls;
	  double f = lm-nm;
	  return -1.274*sind(mm-2*bigD)+0.658*sind(2*bigD)-0.186*sind(ms);
	}
	public static double moonLatitudeCorrectionDegrees(double d) { //==d=days since 1/1/2000
	  double ns=0,ws=0,ms=0,nm=0,wm=0,mm=0;
	  sunNwM(ns,ws,ms,d);
	  moonNwM(nm,wm,mm,d);
	  double ls = ms+ns+ws;
	  double lm = mm+nm+wm;
	  double bigD = lm-ls;
	  double f = lm-nm;
	  return -0.173*sind(f-2*bigD)-0.055*sind(mm-f-2*bigD)-0.046*sind(mm+f-2*bigD);
	}
	private static void sunNwM(double n, double w, double m,double d) {
	  n = 0.0d;
	  w = 282.9404d + 4.70935E-5d * d;
	  m = 356.0470d + 0.9856002585d * d;
	}
	private static void moonNwM(double n, double w, double m,double d) {
	  n = 125.1228d - 0.0529538083d * d;
	  w = 318.0634d + 0.1643573223d * d;
	  m = 115.3654 + 13.0649929509 * d;
	}
	private static double sind(double x) {
	  return Math.sin(x*Math.PI/180.d);
	}
}
