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

#ifndef __CARTESIAN_DEF__
#define __CARTESIAN_DEF__

#include "r3/linear.h"
#include <math.h>

inline r3::Vec3f xHat() {
	return r3::Vec3f(1.0, 0.0, 0.0);
}

inline r3::Vec3f yHat() {
	return r3::Vec3f(0.0, 1.0, 0.0);
}

inline r3::Vec3f zHat() {
	return r3::Vec3f(0.0, 0.0, 1.0);
}

inline float angleInPlane( const r3::Vec3f & vec , const r3::Vec3f & u, const r3::Vec3f & v ) {
	return atan2( vec.Dot(v), vec.Dot(u) );
}
 
inline r3::Vec3f latLongToUnitVector(float lat, float lon) {
	return r3::Vec3f( cos(lat)*cos(lon), cos(lat)*sin(lon), sin(lat) );
}

		
inline float angleBetween(const r3::Vec3f & u, const r3::Vec3f & v) {
	r3::Vec3f a = u / u.Length();
	r3::Vec3f b = v / v.Length();
	return acos( a.Dot(b) );
}

#endif //__CARTESIAN_DEF__