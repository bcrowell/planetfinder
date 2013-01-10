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


/*

  The 518 brightest stars, from the Yale Bright Star Catalog, BSC5.
  These are all the stars brighter than 4th magnitude.
  public float rightAscensionInRadians[]
  public float declinationInRadians[]
  public float mag[]
*/

//#include <eikenv.h>
//#include "f32file.h"
#include "BrightStarCatalog.h"
#include "r3/filesystem.h"
#include <stdio.h>


using namespace r3;
using namespace std;

bool 
BrightStarCatalog::Initialized()
{
	if ( stars.size() > 0 ) {
		return true;
	}
	
	File *file = FileOpenForRead( "stars" );
	if (file == NULL )
	{
		return false;
	}

	int len = file->Size() / (8 * 3);
	for ( int i = 0; i < len; i++ ) {
		double vals[3]; 
		file->Read( vals, 8, 3 );
		Star s;
		s.ra = float( vals[0] );
		s.dec = float( vals[1] );
		s.mag = float( vals[2] );
		stars.push_back( s );
	}
	delete file;
	return true;
}

float
BrightStarCatalog::rightAscensionInRadians(int i)
{
	return stars[ i ].ra;
}

float
BrightStarCatalog::declinationInRadians(int i)
{
	return stars[ i ].dec;
}

float
BrightStarCatalog::mag(int i)
{
	return stars[ i ].mag;
}

