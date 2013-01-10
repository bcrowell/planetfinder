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


import java.util.*;

/*
da_DK Danish Denmark 
  DE_AT German Austria 
  DE_CH German Switzerland 
  DE_DE German Germany 
  el_GR Greek Greece 
  en_CA English Canada 
  en_GB English United Kingdom 
  en_IE English Ireland 
  en_US English United States 
  es_ES Spanish Spain 
  fi_FI Finnish Finland 
  fr_BE French Belgium 
  fr_CA French Canada 
  fr_CH French Switzerland 
  fr_FR French France 
  it_CH Italian Switzerland 
  it_IT Italian Italy 
  ja_JP Japanese Japan 
  ko_KR Korean Korea 
  nl_BE Dutch Belgium 
  nl_NL Dutch Netherlands 
  no_NO Norwegian (Nynorsk) Norway 
  no_NO_B Norwegian (Bokmal) Norway 
  pt_PT Portuguese Portugal 
  sv_SE Swedish Sweden 
  tr_TR Turkish Turkey 
  zh_CN Chinese (Simplified) China 
  zh_TW Chinese (Traditional) Taiwan 

*/

class Text {

  public Language lang;
  public String daylightSavings;
  public String auto;
  public String manual;
  public String northLetter;
  public String southLetter;
  public String eastLetter;
  public String westLetter;
  public String sky;
  public String innerSolarSystem;
  public String outerSolarSystem;
  public String update;
  public String nakedEyePlanetsOnly;
  public String timeZone;
  public String monthNames[] = new String[12];
  public String planetNames[] = new String[9];
  public String sun;
  public String moon;
  public String latitude;
  public String longitude;
  public String localTime;

  private static final String aGrave = "\u00e0";
  private static final String eGrave = "\u00e8";
  private static final String aAcute = "\u00e1";
  private static final String eAcute = "\u00e9";
  private static final String iAcute = "\u00ed";
  private static final String uAcute = "\u00fa";
  private static final String oAcute = "\u00f3";
  private static final String aTilde = "\u00e3";
  private static final String aUmlaut = "\u00e4";
  private static final String oUmlaut = "\u00f6";
  private static final String uUmlaut = "\u00fc";
  private static final String aUmlautUC = "\u00c4";
  private static final String oUmlautUC = "\u00d6";
  private static final String uUmlautUC = "\u00dc";
  private static final String aRing = "\u00e5";

  public Text(Language lang) {
    this.lang = lang;

            //-- Hebrew
    if (lang.equals(new Language("he"))) {
   daylightSavings = "שעון קיץ";
auto = "אוטומאטי";
manual = "ידני";
northLetter = "צ";
southLetter = "ד";
eastLetter = "מז";
westLetter = "מע";
sky = "Sky";
innerSolarSystem = "כוכבי לכת פנימיים";
outerSolarSystem = "כוכבי לכת חיצוניים";
update = "Update";
nakedEyePlanetsOnly = "מטרות לעין בלתי מזויינת";
timeZone = "אזור זמן";
monthNames[0] = "Jan"; monthNames[3] = "Apr"; monthNames[6] = "Jul"; 
monthNames[9] = "Oct";
monthNames[1] = "Feb"; monthNames[4] = "May"; monthNames[7] = "Aug"; 
monthNames[10] = "Nov";
monthNames[2] = "Mar"; monthNames[5] = "Jun"; monthNames[8] = "Sep"; 
monthNames[11] = "Dec";
planetNames[0] = "חמה"; planetNames[3] = "מאדים"; planetNames[6] = 
"אוראנוס";
planetNames[1] = "נגה"; planetNames[4] = "צדק"; planetNames[7] = "נפטון";
planetNames[2] = "ארץ"; planetNames[5] = "שבתאי"; planetNames[8] = "פלוטו";
sun = "שמש";
moon = "ירח";
latitude = "קו רוחב";
longitude = "קו אורך";
localTime = "שעה מקומית";

          return;
    }
       
           //-- Basque
    if (lang.equals(new Language("eu"))) {
            daylightSavings = "Udako ordua";
            //daylightSavings = Locale.getDefault().getDisplayLanguage();
            auto = "Auto";
            manual = "Manual";
            northLetter = "I";
            southLetter = "H";
            eastLetter = "E";
            westLetter = "M";
            sky = "Zerua";
   innerSolarSystem = "Kanpoko planeten mapa";
   outerSolarSystem = "Barneko planeten mapa";
   update = "Eguneratu";
   nakedEyePlanetsOnly = "Teleskopio barik ikusten diren planetak baino ez"
;
     timeZone = "Ordu-zona";
      monthNames[0] = "Urt"; monthNames[3] = "Apr";  monthNames[6] = "Uzt";
    monthNames[9] = "Urr";
      monthNames[1] = "Ots"; monthNames[4] = "Mai";  monthNames[7] = "Abu";
    monthNames[10] = "Aza";
      monthNames[2] = "Mar"; monthNames[5] = "Eka";  monthNames[8] = "Ira";
    monthNames[11] = "Abe";
   planetNames[0] = "Merkurio";       planetNames[3] = "Marte";        planetNames[6] = "Urano";
   planetNames[1] = "Artizarra"; planetNames[4] = "Jupiter";     planetNames[7] = "Neptuno";
   planetNames[2] = "Lurra"; planetNames[5] = "Saturno";      planetNames[8] = "Pluton";
   sun = "Eguzkia";
   moon = "Ilargia";
   latitude = "Latitudea";
   longitude = "Longitudea";
   localTime = "Bertako ordua";
      return;
    }
    
    //-- French
    if (lang.equals(new Language("fr"))) {
            daylightSavings = "Heure d'\u00e9t\u00e9";
            //daylightSavings = Locale.getDefault().getDisplayLanguage();
            auto = "Auto";
            manual = "Manuel";
            northLetter = "N";
            southLetter = "S";
            eastLetter = "E";
            westLetter = "O";
            sky = "Ciel";
            innerSolarSystem = "Vue des plan"+eGrave+"tes int"+eAcute+"rieures";
            outerSolarSystem =  "Vue des plan"+eGrave+"tes ext"+eAcute+"rieures";
            update = "Mettre "+aGrave+" jour";
            nakedEyePlanetsOnly = "Plan"+eGrave+"tes visibles "+aGrave+" l'oeil nu";
            timeZone = "Fuseau horaire";
                   monthNames[0] = "Jan";        monthNames[3] = "Avr";        monthNames[6] = "Juil";        monthNames[9] = "Oct";        
                   monthNames[1] = "F"+eAcute+"v";        monthNames[4] = "Mai";        monthNames[7] = "Aug";        monthNames[10] = "Nov";        
                   monthNames[2] = "Mar";        monthNames[5] = "Juin";        monthNames[8] = "Sep";        monthNames[11] = "D"+eAcute+"c";        
            planetNames[0] = "Mercure";        planetNames[3] = "Mars";        planetNames[6] = "Uranus";        
            planetNames[1] = "V"+eAcute+"nus";        planetNames[4] = "Jupiter";        planetNames[7] = "Neptune";        
            planetNames[2] = "Terre";        planetNames[5] = "Saturne";        planetNames[8] = "Pluton";
            sun = "Soleil";
            moon = "Lune";
       latitude = "Latitude";
       longitude = "Longitude";
       localTime = "Temps local";
      return;
    }
    
    //-- Malay
    if (lang.equals(new Language("ms"))) {
 daylightSavings = "Penyelamatan waktu siang"; auto = "Auto"; manual = "Manual";
 northLetter ="U"; southLetter = "S"; eastLetter = "T"; westLetter = "B"; sky = "Langit"; innerSolarSystem = "Inti sistem suria"; outerSolarSystem = "Luar sistem suria";
 update = "Kemas kini"; nakedEyePlanetsOnly = "Memandang planet dengan hanya pandangan mata kasar"; timeZone = "Zon waktu";
  monthNames[0] = "Jan"; monthNames[3] = "Apr"; monthNames[6] = "Jul"; monthNames[9] = "Okt";   monthNames[1] = "Feb";
  monthNames[4] = "Mei"; monthNames[7] = "Ogo"; monthNames[10] = "Nov";   monthNames[2] = "Mac"; monthNames[5] = "Jun"; monthNames[8] = "Sep";
  monthNames[11] = "Dis";
  planetNames[0] = "Utarid"; planetNames[3] = " Marikh"; planetNames[6] = "Uranus";  planetNames[1] = "Zuhrah";
  planetNames[4] = " Musytari"; planetNames[7] = "Neptun";  planetNames[2] = "Bumi"; planetNames[5] = "Zuhal"; planetNames[8] = "Pluto";
  sun = "Matahari"; moon = "Bulan"; latitude = " Garis lintang"; longitude = " Garis bujur"; localTime ="Waktu Tempatan";
      return;
    }
    
    //-- Indonesian
    if (lang.equals(new Language("id"))) {
 daylightSavings = "Penyelamatan waktu siang"; auto = "Oto"; manual = "Manual";
 northLetter= "U"; southLetter = "S"; eastLetter = "T"; westLetter = "B"; sky = "Langit";
 innerSolarSystem = "Inti sistem surya"; outerSolarSystem = "Luar sistem surya"; update = "Pembaruan";
 nakedEyePlanetsOnly = "Memandang planet dengan hanya pandangan mata biasa"; timeZone = "Zona waktu";
  monthNames[0] = "Jan"; monthNames[3] = "Apr"; monthNames[6] = "Jul"; monthNames[9] = "Okt";   monthNames[1] = "Feb"; monthNames[4] = "Mei";
  monthNames[7] = "Aug"; monthNames[10] = "Nov";   monthNames[2] = "Mar"; monthNames[5] = "Jun"; monthNames[8] = "Sep";
  monthNames[11] = "Des";  planetNames[0] = " Merkurius "; planetNames[3] = "Mars"; planetNames[6] = "Uranus";
  planetNames[1] = "Venus"; planetNames[4] = "Yupiter"; planetNames[7] = "Neptunus";  planetNames[2] = "Bumi"; 
  planetNames[5] = "Saturnus"; planetNames[8] = "Pluto"; sun = "Matahari"; moon = "Bulan"; latitude = "Lintang"; longitude = "Bujur"; localTime = "Waktu Setempat";
      return;
    }
    

    //-- German
    if (lang.equals(new Language("de"))) {
    daylightSavings = "Sommerzeit";
    auto = "automatisch";
    manual = "manuell";
    northLetter = "N";
    southLetter = "S";
    eastLetter = "O";
    westLetter = "W";
    sky = "Himmel";
    innerSolarSystem = "Inneres Sonnensystem";
    outerSolarSystem = aUmlautUC+"usseres Sonnensystem";
    update = "Aktualisieren";
    nakedEyePlanetsOnly = "Planeten welche mit blossem Auge sichtbar sind";
    timeZone = "Zeitzone";
         monthNames[0] = "Jan"; monthNames[3] = "Apr";  monthNames[6] = "Jul";  monthNames[9] = "Okt";
         monthNames[1] = "Feb"; monthNames[4] = "Mai";  monthNames[7] = "Aug";  monthNames[10] = "Nov";
         monthNames[2] = "M"+aUmlaut+"r"; monthNames[5] = "Jun";  monthNames[8] = "Sep";  monthNames[11] = "Dez";
    planetNames[0] = "Merkur"; planetNames[3] = "Mars"; planetNames[6] = "Uranus";
    planetNames[1] = "Venus";   planetNames[4] = "Jupiter"; planetNames[7] = "Neptun";
    planetNames[2] = "Erde";   planetNames[5] = "Saturn"; planetNames[8] = "Pluto";
    sun = "Sonne";
    moon = "Mond";
    latitude = "Breite";
    longitude = "L"+aUmlaut+"nge";
    localTime = "Lokalzeit";
    return;
    }


    //-- Spanish
    if (lang.equals(new Language("es"))) {
            daylightSavings = "Hora de verano";
            //daylightSavings = Locale.getDefault().getDisplayLanguage();
            auto = "Auto";
            manual = "Manual";
            northLetter = "N";
            southLetter = "S";
            eastLetter = "E";
            westLetter = "O";
            sky = "Cielo";
            innerSolarSystem = "Mapa de los planetas interiores";
            outerSolarSystem =  "Mapa de los planetas exteriores";
            update = "Actualizar";
            nakedEyePlanetsOnly = "S"+oAcute+"lo los planetas visibles sin telescopio";
            timeZone = "Zona horaria";
                   monthNames[0] = "Ene";        monthNames[3] = "Abr";        monthNames[6] = "Jul";        monthNames[9] = "Oct";        
                   monthNames[1] = "Feb";        monthNames[4] = "May";        monthNames[7] = "Ago";        monthNames[10] = "Nov";        
                   monthNames[2] = "Mar";        monthNames[5] = "Jun";        monthNames[8] = "Sep";        monthNames[11] = "Dic";        
            planetNames[0] = "Mercurio";        planetNames[3] = "Marte";        planetNames[6] = "Urano";        
            planetNames[1] = "Venus";        planetNames[4] = "J"+uAcute+"piter";        planetNames[7] = "Neptuno";        
            planetNames[2] = "Terra";        planetNames[5] = "Saturno";        planetNames[8] = "Plut"+oAcute+"n";
            sun = "Sol";
            moon = "Luna";
       latitude = "Latitud";
       longitude = "Longitud";
       localTime = "Hora local";
      return;
    }

    //-- Arabic
    if (lang.equals(new Language("ar"))) {
            daylightSavings = "\u062a\u0648\u0642\u064a\u062a \u0635\u064a\u0641\u064a";
            auto = "\u0622\u0644\u064a";
            manual = "\u064a\u062f\u0648\u064a";
            northLetter = "\u0634";
            southLetter = "\u062c";
            eastLetter = "\u0642";
            westLetter = "\u063a";
            sky = "\u0627\u0644\u0633\u0645\u0627\u0621";
            innerSolarSystem = "\u0627\u0644\u0646\u0638\u0627\u0645 \u0627\u0644\u0634\u0645\u0633\u064a \u0627\u0644\u062f\u0627\u062e\u0644\u064a";
            outerSolarSystem = "\u0627\u0644\u0646\u0638\u0627\u0645 \u0627\u0644\u0634\u0645\u0633\u064a \u0627\u0644\u062e\u0627\u0631\u062c\u064a";
            update = "\u062a\u062d\u062f\u064a\u062b";
            nakedEyePlanetsOnly = "\u0643\u0648\u0627\u0643\u0628 \u062a\u0631\u0649 \u0628\u0627\u0644\u0639\u064a\u0646 \u0627\u0644\u0645\u062c\u0631\u062f\u0629 \u0641\u0642\u0637";
            timeZone = "\u0627\u0644\u062a\u0648\u0642\u064a\u062a";
            monthNames[0] = "\u064a\u0646\u0627\u064a\u0631";
            monthNames[1] = "\u0641\u0628\u0631\u0627\u064a\u0631";
            monthNames[2] = "\u0645\u0627\u0631\u0633";
            monthNames[3] = "\u0625\u0628\u0631\u064a\u0644";
            monthNames[4] = "\u0645\u0627\u064a\u0648";
            monthNames[5] = "\u064a\u0648\u0646\u064a\u0648";
            monthNames[6] = "\u064a\u0648\u0644";
            monthNames[7] = "\u0623\u063a\u0633\u0637\u0633";
            monthNames[8] = "\u0633\u0628\u062a\u0645\u0628\u0631";
            monthNames[9] = "\u0623\u0643\u062a\u0648\u0628\u0631";
            monthNames[10] = "\u0646\u0648\u0641\u0645\u0628\u0631";
            monthNames[11] = "\u062f\u064a\u0633\u0645\u0628\u0631";
            planetNames[0] = "\u0639\u0637\u0627\u0631\u062f";
            planetNames[1] = "\u0627\u0644\u0632\u0647\u0631\u0629";
            planetNames[2] = "\u0627\u0644\u0623\u0631\u0636";
            planetNames[3] = "\u0627\u0644\u0645\u0631\u064a\u062e";
            planetNames[4] = "\u0627\u0644\u0645\u0634\u062a\u0631\u064a";
            planetNames[5] = "\u0632\u062d\u0644";
            planetNames[6] = "\u064a\u0648\u0631\u0627\u0646\u0648\u0633";
            planetNames[7] = "\u0646\u0628\u062a\u0648\u0646";
            planetNames[8] = "\u0628\u0644\u0648\u062a\u0648";
            sun = "\u0627\u0644\u0634\u0645\u0633";
            moon = "\u0627\u0644\u0642\u0645\u0631";
            latitude = "\u0627\u0644\u0637\u0648\u0644";
            longitude = "\u0627\u0644\u0639\u0631\u0636";
            localTime = "\u0627\u0644\u062a\u0648\u0642\u064a\u062a \u0627\u0644\u0645\u062d\u0644\u064a";
      return;
    }

    //-- Japanese
    if (lang.equals(new Language("ja"))) {
        daylightSavings = "\u590f\u6642\u9593\u8abf\u6574";
        auto = "\u81ea\u52d5";
        manual = "\u624b\u52d5";
        northLetter = "\u5317";
        southLetter = "\u5357";
        eastLetter = "\u6771";
        westLetter = "\u897f";
        sky = "\u7a7a";
        innerSolarSystem = "\u5185\u5074\u306e\u592a\u967d\u7cfb";
        outerSolarSystem = "\u5916\u5074\u306e\u592a\u967d\u7cfb";
        update = "\u66f4\u65b0";
        nakedEyePlanetsOnly = "\u8089\u773c\u60d1\u661f\u306e\u307f";
        timeZone = "\u6642\u9593\u5e2f";
        monthNames[0] = "\uff11\u6708";	monthNames[3] = "\uff14\u6708";	monthNames[6] = "\uff17\u6708";	monthNames[9] = "\uff11\uff10\u6708";	
        monthNames[1] = "\uff12\u6708";	monthNames[4] = "\uff15\u6708";	monthNames[7] = "\uff18\u6708";	monthNames[10] = "\uff11\uff11\u6708";	
        monthNames[2] = "\uff13\u6708";	monthNames[5] = "\uff16\u6708";	monthNames[8] = "\uff19\u6708";	monthNames[11] = "\uff11\uff12\u6708";	
        planetNames[0] = "\u6c34\u661f";	planetNames[3] = "\u706b\u661f";	planetNames[6] = "\u5929\u738b\u661f";	
        planetNames[1] = "\u91d1\u661f";	planetNames[4] = "\u6728\u661f";	planetNames[7] = "\u6d77\u738b\u661f";	
        planetNames[2] = "\u5730\u7403";	planetNames[5] = "\u571f\u661f";	planetNames[8] = "\u51a5\u738b\u661f";
        sun = "\u592a\u967d";
        moon = "\u6708";
        latitude = "\u7def\u5ea6";
        longitude = "\u7d4c\u5ea6";
        localTime = "\u73fe\u5730\u6642\u523b";
        return;
    }
    
    //-- Greek
    if (lang.equals(new Language("el"))) {
            daylightSavings = "\u03a9\u03c1\u03b1 \u03ba\u03b1\u03bb\u03bf\u03ba\u03b1\u03b9'\u03c1\u03b7\u03c3";
            auto = "\u0391\u03c5\u03c4\u03bf";
            manual = "\u03a7\u03b5\u03b9\u03c1\u03c9\u03bd\u03b1\u03ba\u03c4\u03b9\u03ba"+oAcute+"\u03c2";
            northLetter = "\u0392";
            southLetter = "\u039d";
            eastLetter = "\u0391";
            westLetter = "\u0394";
            sky = "\u039f\u03c5\u03c1\u03b1\u03bd"+oAcute+"\u03c2";
            innerSolarSystem = "\u03a7"+aAcute+"\u03c1\u03c4\u03b7\u03c2 \u03c4\u03c9\u03bd"
                                                    +" \u03c0\u03bb\u03b1\u03bd\u03b7\u03c4\u03c9\u03bd"
                                                    +" \u03b5\u03c3\u03c9\u03c4\u03b5\u03c1\u03b9\u03ba\u03c9\u03bd";
            outerSolarSystem = "\u03a7"+aAcute+"\u03c1\u03c4\u03b7\u03c2 \u03c4\u03c9\u03bd"
                                                    +" \u03c0\u03bb\u03b1\u03bd\u03b7\u03c4\u03c9\u03bd"
                                                    +" \u03b5\u03be\u03c9\u03c4\u03b5\u03c1\u03b9\u03ba\u03c9\u03bd";
            update = "\u0395\u03bd\u03b7\u03bc\u03b5'\u03c1\u03c9\u03bd\u03b5";
            nakedEyePlanetsOnly = "\u03a0\u03bb\u03b1\u03bd\u03b7'\u03c4\u03b5\u03c2 \u03bf\u03c1\u03b1\u03c4\u03b5'\u03c2 \u03c7\u03bf\u03c1\u03b9'\u03c2"
                                                                            +" \u03c4\u03b7\u03bb\u03b5\u03c3\u03ba\u03bf'\u03c0\u03b9\u03bf";
            timeZone = "\u0391\u03c4\u03c1\u03b1\u03ba\u03c4\u03bf\u03c2 \u03c7\u03c1\u03bf\u03bd\u03bf\u03c5";
                   monthNames[0] = "\u0399\u03b1\u03bd";        monthNames[3] = "\u0391\u03c0\u03c1";        monthNames[6] = "\u0399\u03c5\u03bb";        monthNames[9] = "\u039f\u03ba\u03c4";        
                   monthNames[1] = "\u03a6\u03b5\u03b2";        monthNames[4] = "\u039c"+aAcute+"\u03b9";        monthNames[7] = "\u0391\u03c5\u03b3";        monthNames[10] = "\u039d\u03bf\u03b5";        
                   monthNames[2] = "\u039c\u03b1\u03c1";        monthNames[5] = "\u0399\u03c5\u03bd";        monthNames[8] = "\u03a3\u03b5\u03c0";        monthNames[11] = "\u0394\u03b5\u03ba";        
            planetNames[0] = "\u0397\u03c1\u03bc\u03b7\u03c3";        planetNames[3] = "\u0391\u03c1\u03b7\u03c2";        planetNames[6] = "\u039f\u03c5\u03c1\u03b1\u03bd\u03bf\u03c2";        
            planetNames[1] = "\u0391\u03c6\u03c1\u03bf\u03b4\u03b9\u03c4\u03b7\u03c2";        planetNames[4] = "\u0396\u03b5\u03c5\u03c3";        planetNames[7] = "\u03a0\u03bf\u03c3\u03b5\u03b9\u03b4\u03bf\u03bd";        
            planetNames[2] = "\u0393\u03b7";        planetNames[5] = "K\u03c1\u03bf\u03bd\u03bf\u03c2";        planetNames[8] = "\u03a0\u03bb\u03c5\u03c4\u03bf";
            sun = "\u0397\u03bb\u03b9\u03bf\u03c2";
            moon = "\u03a3\u03b5\u03bb\u03b7'\u03bd\u03b7";
       latitude = "\u0393\u03b5\u03c9\u03b3\u03c1\u03b1\u03c6\u03b9\u03ba"+oAcute+
                                               " \u03b7\u03bb"+aAcute+"\u03c4\u03bf\u03c2";
       longitude = "\u039c\u03b7'\u03ba\u03bf\u03c2";
       localTime = "\u03a4\u03bf\u03c0\u03b9\u03ba\u03b7' \u03c9'\u03c1\u03b1";
      return;
    }
    
    //-- Danish
    if (lang.equals(new Language("da"))) {

    daylightSavings = "Sommertid";
    auto = "Auto";
    manual = "Manuelt";
    northLetter = "N";
    southLetter = "S";
    eastLetter = "\u00d8";
    westLetter = "V";
    sky = "Stjernehimlen";
    innerSolarSystem = "Indre solsystem";
    outerSolarSystem = "Ydre solsystem";
    update = "Opdater";
    nakedEyePlanetsOnly = "Kun planeter synlige med blotte \u00f8je";
    timeZone = "Tidszone";
    monthNames[0] = "Jan"; 
    monthNames[3] = "Apr"; 
    monthNames[6] = "Jul";
    monthNames[9] = "Okt";
    monthNames[1] = "Feb"; 
    monthNames[4] = "Maj"; 
    monthNames[7] = "Aug";
    monthNames[10] = "Nov";
    monthNames[2] = "Mar"; 
    monthNames[5] = "Jun"; 
    monthNames[8] = "Sep";
    monthNames[11] = "Dec";
    planetNames[0] = "Merkur"; 
    planetNames[3] = "Mars"; 
    planetNames[6] = "Uranus";
    planetNames[1] = "Venus"; 
    planetNames[4] = "Jupiter"; 
    planetNames[7] = "Neptun";
    planetNames[2] = "Jorden"; 
    planetNames[5] = "Saturn"; 
    planetNames[8] = "Pluto";
    sun = "Solen";
    moon = "M"+aRing+"nen";
    latitude = "Breddegrad";
    longitude = "L\u00e6ngdegrad";
    localTime = "Lokal tid";
      return;
    }
    
    //-- Swedish
    if (lang.equals(new Language("sv"))) {

    daylightSavings = "Sommartid";
    auto = "Auto";
    manual = "Manuell";
    northLetter = "N";
    southLetter = "S";
    eastLetter = oUmlautUC;
    westLetter = "V";
    sky = "Stj"+aUmlaut+"rnhimlen";
    innerSolarSystem = "Inre solsystemet";
    outerSolarSystem = "Yttre solsystemet";
    update = "Opdater";
    nakedEyePlanetsOnly = "Endast planeter synliga med blotta "+oUmlaut+"gat";
    timeZone = "Tidzon";
    monthNames[0] = "Jan";   monthNames[3] = "Apr";   monthNames[6] = "Jul";
    monthNames[9] = "Okt";  monthNames[1] = "Feb";  monthNames[4] = "Maj";  
    monthNames[7] = "Aug";   monthNames[10] = "Nov";
    monthNames[2] = "Mar"; 
    monthNames[5] = "Jun"; 
    monthNames[8] = "Sep";
    monthNames[11] = "Dec";
    planetNames[0] = "Merkurius"; 
    planetNames[3] = "Mars"; 
    planetNames[6] = "Uranus";
    planetNames[1] = "Venus"; 
    planetNames[4] = "Jupiter"; 
    planetNames[7] = "Neptunus";
    planetNames[2] = "Jorden"; 
    planetNames[5] = "Saturnus"; 
    planetNames[8] = "Pluto";
    sun = "Solen";
    moon = "M"+aRing+"nen";
    latitude = "Latitud";
    longitude = "Longitud";
    localTime = "Lokaltid";
      return;
    }
    
    
    //-- Italian
    if (lang.equals(new Language("it"))) {

            daylightSavings = "Ora legale";
            auto = "Auto";
            manual = "Manuale";
            northLetter = "N";
            southLetter = "S";
            eastLetter = "E";
            westLetter = "O";
            sky = "Cielo";
            innerSolarSystem = "Sistema solare interno ";
            outerSolarSystem = "Sistema solare esterno";
            update = "Aggiorna";
            nakedEyePlanetsOnly = "Solo i pianeti visibili a occhio nudo";
            timeZone = "Fuso orario";
            monthNames[0] = "Gen"; monthNames[3] = "Apr"; monthNames[6] = "Lug"; monthNames[9] = "Ott";
            monthNames[1] = "Feb"; monthNames[4] = "Mag"; monthNames[7] = "Ago"; monthNames[10] = "Nov";
            monthNames[2] = "Mar"; monthNames[5] = "Giu"; monthNames[8] = "Set"; monthNames[11] = "Dic";
            planetNames[0] = "Mercurio"; planetNames[3] = "Marte"; planetNames[6] = "Urano";
            planetNames[1] = "Venere"; planetNames[4] = "Giove"; planetNames[7] = "Nettuno";
            planetNames[2] = "Terra"; planetNames[5] = "Saturno"; planetNames[8] = "Plutone";
            sun = "Sole";
            moon = "Luna";
            latitude = "Latitudine";
            longitude = "Longitudine";
            localTime = "Ora locale";
              return;
    }

    //-- Nederlands - Belgium
    if (lang.equals(new Language("nl"))) {
    
             daylightSavings = "Zomertijd";
            auto = "Automatisch";
            manual = "Niet Automatisch";
            northLetter = "N";
            southLetter = "Z";
            eastLetter = "O";
            westLetter = "W";
            sky = "Hemel";
            innerSolarSystem = "Binnenzonnestelsel";
            outerSolarSystem = "Buitenzonnestelsel";
            update = "Aanpassing";
            nakedEyePlanetsOnly = "Enkel blote-oog planeten";
            timeZone = "Tijdszone";
                 monthNames[0] = "jan"; monthNames[3] = "apr";  monthNames[6] = "jul"; monthNames[9] = "okt";
                 monthNames[1] = "feb"; monthNames[4] = "mei";  monthNames[7] = "aug";  monthNames[10] = "nov";
                 monthNames[2] = "maa"; monthNames[5] = "jun";  monthNames[8] = "sep";  monthNames[11] = "dec";
            planetNames[0] = "Mercurius"; planetNames[3] = "Mars";        planetNames[6] = "Uranus";
            planetNames[1] = "Venus";   planetNames[4] = "Jupiter";     planetNames[7] = "Neptunus";
            planetNames[2] = "aarde";   planetNames[5] = "Saturnus";      planetNames[8] = "Pluto";
            sun = "zon";
            moon = "maan";
            latitude = "Breedtegraad";
            longitude = "Lengtegraad";
            localTime = "Lokale tijd"; 
            return;
            }

    //-- Afrikaans
    if (lang.equals(new Language("af"))) {
    
             daylightSavings = "Daglig besparing";
            auto = "Outomaties";
            manual = "Nie Outomaties";
            northLetter = "N";
            southLetter = "S";
            eastLetter = "O";
            westLetter = "W";
            sky = "Lug";
            innerSolarSystem = "Binne sonnestelsel";
            outerSolarSystem = "Buite sonnestelsel";
            update = "Opdateer";
            nakedEyePlanetsOnly = "Blote oog planete aleenlik";
            timeZone = "Tyd sone";
                 monthNames[0] = "Jan"; monthNames[3] = "Apr";  monthNames[6] = "Jul"; monthNames[9] = "Okt";
                 monthNames[1] = "Feb"; monthNames[4] = "Mei";  monthNames[7] = "Aug";  monthNames[10] = "Nov";
                 monthNames[2] = "Ma"; monthNames[5] = "Jun";  monthNames[8] = "Sep";  monthNames[11] = "Des";
            planetNames[0] = "Merkerius"; planetNames[3] = "Mars";        planetNames[6] = "Uranus";
            planetNames[1] = "Venus";   planetNames[4] = "Jupiter";     planetNames[7] = "Neptunus";
            planetNames[2] = "Aarde";   planetNames[5] = "Saturnus";      planetNames[8] = "Pluto";
            sun = "Son";
            moon = "Maan";
            latitude = "breedtegraad";
            longitude = "Lengtegraad";
            localTime = "Lokale tyd"; 
            return;
            }
        
        
        
    //-- Portuguese
    if (lang.equals(new Language("pt"))) {
        daylightSavings = "Hor"+aAcute+"rio de Ver"+aTilde+"o";
                        auto = "Auto";
                        manual = "Manual";
                        northLetter = "N";
                        southLetter = "S";
                        eastLetter = "L";
                        westLetter = "O";
                        sky = "C"+eAcute+"u";
                        innerSolarSystem = "Sistema Solar Interno";
                        outerSolarSystem = "Sistema Solar Externo";
                        update = "Atualizar";
                        nakedEyePlanetsOnly = "Somente Planetas Vis"+iAcute+"veis a Olho N"+uAcute+"";
                        timeZone = "Fuso Hor"+aAcute+"rio";
                        monthNames[0] = "Jan"; monthNames[3] = "Abr";  monthNames[6] = "Jul";
                        monthNames[9] = "Out";
                        monthNames[1] = "Fev"; monthNames[4] = "Mai";  monthNames[7] = "Ago";
                        monthNames[10] = "Nov";
                        monthNames[2] = "Mar"; monthNames[5] = "Jun";  monthNames[8] = "Set";
                        monthNames[11] = "Dez";

                        planetNames[0] = "Merc"+uAcute+"rio"; planetNames[3] = "Marte";
                        planetNames[6] = "Urano";
                        planetNames[1] = "Venus";     planetNames[4] = "J"+uAcute+"piter";
                        planetNames[7] = "Netuno";
                        planetNames[2] = "Terra";     planetNames[5] = "Saturno";
                        planetNames[8] = "Plut"+aTilde+"o";
                        sun = "Sol";
                        moon = "Lua";
                        latitude = "Latitude";
                        longitude = "Longitude";
                        localTime = "Hora Local";
                        return;
        }
        
    //-- Polish
    if (lang.equals(new Language("pl"))) {
       daylightSavings = "Czas letni";
       auto = "Automatycznie";
       manual = "R\u0119cznie";
       northLetter = "N";
       southLetter = "S";
       eastLetter = "E";
       westLetter = "W";
       sky = "Niebo";
       innerSolarSystem = "Wewn\u0119trzny Uk\u0142ad S\u0142oneczny";
       outerSolarSystem = "Zewn\u0119trzny Uk\u0142ad S\u0142oneczny";
       update = "Update";
       nakedEyePlanetsOnly = "jedynie planety widoczne go\u0142ym okiem";
       timeZone = "Strefa czasowa";
   	 monthNames[0] = "Sty";	monthNames[3] = "Kwi";	monthNames[6] = "Lip";	monthNames[9] = "Pa\u017a";	
   	 monthNames[1] = "Lut";	monthNames[4] = "Maj";	monthNames[7] = "Sie";	monthNames[10] = "Lis";	
   	 monthNames[2] = "Mar";	monthNames[5] = "Cze";	monthNames[8] = "Wrz";	monthNames[11] = "Gru";	
       planetNames[0] = "Merkury";	planetNames[3] = "Mars"; planetNames[6] = "Uran";	
       planetNames[1] = "Wenus";	planetNames[4] = "Jowisz"; planetNames[7] = "Neptun";	
       planetNames[2] = "Ziemia";	planetNames[5] = "Saturn"; planetNames[8] = "Pluton";
       sun = "S\u0142o\u0144ce";
       moon = "Ksi\u0119\u017cyc";
       latitude = "Szeroko\u015b\u0107";
       longitude = "D\u0142ugo\u015b\u0107";
       localTime = "Czas lokalny";
       return;
    }

    //-- Chinese (simplified)
    if (lang.equals(new Language("zh"))) {
      daylightSavings = "\u590f\u65f6\u5236\u8c03\u6574";
      auto = "\u81ea\u52a8";
      manual = "\u624b\u52a8";
      northLetter = "\u5317";
      southLetter = "\u5357";
      eastLetter = "\u4e1c";
      westLetter = "\u897f";
      sky = "\u5929\u7a7a";
      innerSolarSystem = "\u5185\u884c\u661f";
      outerSolarSystem = "\u5916\u884c\u661f";
      update = "\u66f4\u65b0";
      nakedEyePlanetsOnly = "\u53ea\u663e\u793a\u8089\u773c\u53ef\u89c1\u7684\u884c\u661f";
      timeZone = "\u65f6\u533a";
  	 monthNames[0] = "\u4e00\u6708";	monthNames[3] = "\u56db\u6708";	monthNames[6] = "\u4e03\u6708";	monthNames[9] = "\u5341\u6708";	
  	 monthNames[1] = "\u4e8c\u6708";	monthNames[4] = "\u4e94\u6708";	monthNames[7] = "\u516b\u6708";	monthNames[10] = "\u5341\u4e00\u6708";	
  	 monthNames[2] = "\u4e09\u6708";	monthNames[5] = "\u516d\u6708";	monthNames[8] = "\u4e5d\u6708";	monthNames[11] = "\u5341\u4e8c\u6708";	
      planetNames[0] = "\u6c34\u661f";	planetNames[3] = "\u706b\u661f";	planetNames[6] = "\u5929\u738b\u661f";	
      planetNames[1] = "\u91d1\u661f";	planetNames[4] = "\u6728\u661f";	planetNames[7] = "\u6d77\u738b\u661f";	
      planetNames[2] = "\u5730\u7403";	planetNames[5] = "\u571f\u661f";	planetNames[8] = "\u51a5\u738b\u661f";
      sun = "\u592a\u9633";
      moon = "\u6708\u7403";
      latitude = "\u7eac\u5ea6";
      longitude = "\u7ecf\u5ea6";
      localTime = "\u5f53\u5730\u65f6\u95f4";
       return;
    }

    //-- fall through to default: English
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
           monthNames[0] = "Jan";        monthNames[3] = "Apr";        monthNames[6] = "Jul";        monthNames[9] = "Oct";        
           monthNames[1] = "Feb";        monthNames[4] = "May";        monthNames[7] = "Aug";        monthNames[10] = "Nov";        
           monthNames[2] = "Mar";        monthNames[5] = "Jun";        monthNames[8] = "Sep";        monthNames[11] = "Dec";        
    planetNames[0] = "Mercury";        planetNames[3] = "Mars";        planetNames[6] = "Uranus";        
    planetNames[1] = "Venus";        planetNames[4] = "Jupiter";        planetNames[7] = "Neptune";        
    planetNames[2] = "Earth";        planetNames[5] = "Saturn";        planetNames[8] = "Pluto";
    sun = "Sun";
    moon = "Moon";
    latitude = "Latitude";
    longitude = "Longitude";
    localTime = "Local time";
    return;
  }
  

}




/*

Sample e-mail re translation:

The following is a list of phrases to translate
that occur on the web page. After that is some computer code, in which you only need
to translate what's inside the quotes. You can just indicate accents in any way that's
convenient for you (e.g. "e'" or "o`"), and I'll try to code them correctly for the
computer. Thanks!!

                Ben Crowell
        
        Planet Finder [can be translated as something like "Planets Now"
                or "Planets Today" -- I leave it up to your judgment to pick whatever sounds
                best in Italian]
        "Light and Matter" web page [You can decide whether quotes are appropriate in Italian]
        an applet that shows the current locations of the stars and planets in the night
                sky
        Italian version of Planet Finder
        This applet does not work with the combination of MacOS and Netscape.
        Sorry, but I am unable to respond to e-mail in Italian.
        Light and Matter home page (in English)
        Thanks to Claudio Olmeda for the Italian translation.
        
        ---------

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
           monthNames[0] = "Jan";        monthNames[3] = "Apr";        monthNames[6] = "Jul";        monthNames[9] = "Oct";        
           monthNames[1] = "Feb";        monthNames[4] = "May";        monthNames[7] = "Aug";        monthNames[10] = "Nov";        
           monthNames[2] = "Mar";        monthNames[5] = "Jun";        monthNames[8] = "Sep";        monthNames[11] = "Dec";        
    planetNames[0] = "Mercury";        planetNames[3] = "Mars";        planetNames[6] = "Uranus";        
    planetNames[1] = "Venus";        planetNames[4] = "Jupiter";        planetNames[7] = "Neptune";        
    planetNames[2] = "Earth";        planetNames[5] = "Saturn";        planetNames[8] = "Pluto";
    sun = "Sun";
    moon = "Moon";
    latitude = "Latitude";
    longitude = "Longitude";
    localTime = "Local time";
*/
