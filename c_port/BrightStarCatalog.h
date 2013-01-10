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


#ifndef __BRIGHTSTARCATALOG_DEF__
#define __BRIGHTSTARCATALOG_DEF__

#include <vector>

/*
  The 518 brightest stars, from the Yale Bright Star Catalog, BSC5.
  These are all the stars brighter than 4th magnitude.
*/

class BrightStarCatalog 
{
public:
	bool Initialized();
	int GetSize() {
		return (int)stars.size();
	}
  	float rightAscensionInRadians(int i);
  	float declinationInRadians(int i);
  	float mag(int i);
private:
	struct Star {
		float ra;
		float dec;
		float mag;
	};
	std::vector<Star> stars;
};

#endif //__BRIGHTSTARCATALOG_DEF__