OBJ = BrightStarCatalog.class Cartesian.class City.class CityList.class EqualAreaProj.class Language.class Layout.class MoonPerturbations.class PlanetFinder.class Planet.class StaticText.class Text.class


PlanetFinder.jar: $(OBJ)
	jar cf PlanetFinder.jar *.class

dist:
	rm -Rf planetfinder
	mkdir planetfinder
	cp *.java planetfinder
	cp planetfinder.html planetfinder
	cp Makefile planetfinder
	tar -zcvf planetfinder.tar.gz planetfinder
	rm -Rf planetfinder

%.class : %.java
	javac -target 1.5 -source 1.3 -Xlint:deprecation $<

post:
	cp planetfinder.tar.gz ~/Lightandmatter/planetfinder/planetfinder.tar.gz
	cp PlanetFinder.jar ~/Lightandmatter/PlanetFinder.jar
