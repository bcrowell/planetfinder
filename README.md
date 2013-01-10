Planet Finder
=============

Introductory Notes
------------------

(1) The program needs to know your location in order to predict what the
sky will look like. Initially, it just guesses the biggest city in your
time zone. If this is incorrect, you can set your location either by
typing in your latitude and longitude or by choosing a city from the
list (which only includes those with populations greater than 3
million).  
 (2) Use 24-hour time for setting the time, e.g. 13:00 means 1 pm.  
 (3) In the U.S., daylight savings time lasts from 2 am on the first
sunday of April until 2 am on the last sunday of October. The program
handles this by default. If you are not in the U.S., you may need to set
daylight savings time manually.  
 (4) The compass directions may look wrong, but that is because the
screen represents the way the sky looks when you look straight up. The
compass directions are therefore a mirror image of the compass
directions on a map, which represents a view of the land looking down
from above.  
 (5) The spherical sky has to be projected onto the flat screen. This
projection produces distortion, just as a map of the earth inevitably
has some distortion. The greatest distortion occurs near the horizon.  
 (6) The disks of the sun, moon, and planets are not drawn to scale.
Their brightnesses are given as magnitudes to the right of their names.
A more negative magnitude means a brighter planet. The magnitudes given
for Saturn do not include the brightness of the rings, so Saturn will
usually be brighter than indicated.  
 (7) This program is only designed to have a limited degree of accuracy,
sufficient for most naked-eye astronomy applications. More detailed
information is given below.  

Including Planet Finder in Your Own Page
----------------------------------------

You are welcome either to link to my Planet Finder page or to have the
applet appear in your own page. A possible advantage of the latter is
that in your html code you can set an appropriate language, latitude,
and longitude for the people who visit your page. The following html
example shows how you would include the Danish-language version ("da")
in your page, with the default latitude and longitude set for
Copenhagen:

                <applet archive="http://www.lightandmatter.com/PlanetFinder.jar"
                  code="PlanetFinder.class"
                  width=600 height=400
                  alt="Sorry, your browser does not support Java.">
                <param name="language" value="da">
                <param name="latitude" value="56">
                <param name="longitude" value="12">
                </applet>

Note that, due to a bug, the latitude and longitude as set in the html
code must be integers. (The user can type in noninteger values in the
applet, and that works fine.)

Translating the Program into Your Language
------------------------------------------

If you would like to do this, please [e-mail](area4author.html) me your
translation of the following words and phrases. First comes a list of
phrases to translate that occur on the web page. After that is some
computer code, in which you only need to translate what's inside the
quotes. You can just indicate accents in any way that's convenient for
you (e.g., "e'" and "o\`", or with Unicode), and I'll try to code them
correctly for the computer. Of course you should change the references
to Italian so they refer to your own language. Thanks!!

       
    Planet Finder [can be translated as something like "Planets Now"
            or "Planets Today" -- I leave it up to your judgment to pick whatever sounds
            best in your language]
    "Light and Matter" web page [You can decide whether quotes are appropriate in your language]
    an applet that shows the current locations of the stars and planets in the night sky
    Italian version of Planet Finder
    Sorry, but I am unable to respond to e-mail in Italian.
    Light and Matter home page (in English)
    Thanks to (your name) for the Italian translation.
        

          daylightSavings = "Daylight savings";
          auto = "Auto";
          manual = "Manual";
          northLetter = "N";
          southLetter = "S";
          eastLetter = "E";
          westLetter = "W";
          sky = "Sky";
          innerSolarSystem = "Inner solar system";
          outerSolarSystem = "Outer solar system";
          update = "Update";
          nakedEyePlanetsOnly = "Naked-eye planets only";
          timeZone = "Time zone";
         monthNames[0] = "Jan"; monthNames[3] = "Apr";  monthNames[6] = "Jul";  monthNames[9] = "Oct";  
         monthNames[1] = "Feb"; monthNames[4] = "May";  monthNames[7] = "Aug";  monthNames[10] = "Nov"; 
         monthNames[2] = "Mar"; monthNames[5] = "Jun";  monthNames[8] = "Sep";  monthNames[11] = "Dec"; 
          planetNames[0] = "Mercury";   planetNames[3] = "Mars";    planetNames[6] = "Uranus";  
          planetNames[1] = "Venus"; planetNames[4] = "Jupiter"; planetNames[7] = "Neptune"; 
          planetNames[2] = "Earth"; planetNames[5] = "Saturn";  planetNames[8] = "Pluto";
          sun = "Sun";
          moon = "Moon";
          latitude = "Latitude";
          longitude = "Longitude";
          localTime = "Local time";

  

Known Bugs
----------

(1) The applet does not quit gracefully, so when you reload the page you
get a bunch of Java errors.  
 (2) If you program the default latitude and longitude into your html,
you must use integers.  

Open Source Stuff
-----------------

The source code is subject to the GPL open source license. Source code:
[here](planetfinder.tar.gz).

Kostas Giannakakis has created a C++-language [port of
PlanetFinder](http://www.newlc.com/article.php3?id_article=799) for the
Symbian operating system that runs on cell phones.

Cass Everitt has in turn ported Kostas's app to the iPhone. He calls it
star3map, and it's [available via the iTunes
store.](http://itunes.apple.com/us/app/star3map/id353613186?mt=8)

Technical Notes on Precision
----------------------------

Planet Finder calculates everything based almost entirely on Kepler's
laws, so it is no good for extremely high- precision work or for
projections many centuries into the past or future. The only
nonkeplerian behavior incorporated into the program is the precession of
the moon's orbit and the first few correction terms for the moon's
motion given by [Paul
Schlyter](http://hotel04.ausys.se/pausch/english.htm%0A). The parallax
due to the rotation of the earth is taken into account, but the program
does not calculate the effect of the earth's oblateness or of the time
taken for light to travel from the planets to us. The sun's motion
relative to the solar system's center of mass is taken into account.
Refraction in the earth's atmosphere is not taken into account, which is
why the times of sunrise and sunset will not agree exactly with what is
listed in the newspaper.   
For high-precision calculations, you may want to check out [Solar System
Live](http://www.fourmilab.ch/cgi-bin/uncgi/Solar?date=0&utc=1995%2F03%2F05+12%3A00%3A00&jd=2449782.00000&img=-k0&sys=-Sf&imgsize=500&eyes=0&orb=-b1&lat=33%B050%27&ns=North&lon=118&ew=West&hlat=90%B0&hns=North&hlon=0%B0&elements=),
a web page that displays a map of the locations of the planets in the
solar system (not a map of the sky), or [Your
Sky](http://www.fourmilab.ch/yoursky/), which is a planetarium applet
like this one but fancier and not quite as easy to use.

Acknowledgments
---------------

I am very grateful to Paul Schlyter and Dave Williams for their help
with the celestial mechanics. Thanks to all the folks who contributed
translations.
