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


class Layout {

	 GridBagLayout appletGBLayout = new GridBagLayout();
    GridBagConstraints appletGBConstr = new GridBagConstraints();
	 GridBagLayout controlsGBLayout = new GridBagLayout();
    GridBagConstraints controlsGBConstr = new GridBagConstraints();
	 GridBagLayout timeGBLayout = new GridBagLayout();
    GridBagConstraints timeGBConstr = new GridBagConstraints();
	 GridBagLayout latGBLayout = new GridBagLayout();
    GridBagConstraints latGBConstr = new GridBagConstraints();
	 GridBagLayout lonGBLayout = new GridBagLayout();
    GridBagConstraints lonGBConstr = new GridBagConstraints();
	 GridBagLayout controls2GBLayout = new GridBagLayout();
    GridBagConstraints controls2GBConstr = new GridBagConstraints();
    GridBagLayout viewCheckboxPanelLayout = new GridBagLayout();
    GridBagConstraints viewCheckboxPanelGBConstr = new GridBagConstraints();
    GridBagLayout  dstLayout = new GridBagLayout();
    GridBagConstraints dstGBConstr = new GridBagConstraints();
    GridBagLayout locationLayout = new GridBagLayout();
    GridBagConstraints locationGBConstr = new GridBagConstraints();
	

  public Layout(PlanetFinder applet) {
     applet.removeAll();
     applet.controls.removeAll();
     applet.controls2.removeAll();
     applet.timePanel.removeAll();
     applet.dstPanel.removeAll();
     applet.locationPanel.removeAll();



  

	  //======= Lay out applet. ========
	  applet.setLayout(appletGBLayout);
	  appletGBConstr.insets = new Insets(2,2,2,2);
	  
	  //-- sky
	  appletGBConstr.fill = GridBagConstraints.BOTH;
	  appletGBConstr.gridx = 1;
	  appletGBConstr.gridy = 0;
	  appletGBConstr.gridwidth = 1;
	  appletGBConstr.gridheight = 1;
	  appletGBConstr.weightx = 1.d;
	  appletGBConstr.weighty = 1.d;	  
	  applet.add(applet.sky,appletGBConstr);
	  //-- controls panel
	  appletGBConstr.fill = GridBagConstraints.NONE;
	  appletGBConstr.gridx = 0;
	  appletGBConstr.gridy = 0;
	  appletGBConstr.gridwidth = 1;
	  appletGBConstr.gridheight = 1;
	  appletGBConstr.weightx = 0.d;
	  appletGBConstr.weighty = 0.d;
	  applet.add(applet.controls,appletGBConstr);
	  //-- controls2 panel 
	  appletGBConstr.fill = GridBagConstraints.HORIZONTAL;
	  appletGBConstr.gridx = 0;
	  appletGBConstr.gridy = 1;
	  appletGBConstr.gridwidth = 2;
	  appletGBConstr.gridheight = 1;
	  appletGBConstr.weightx = 0.d;
	  appletGBConstr.weighty = 0.d;
	  appletGBConstr.weightx = 1;
	  applet.add(applet.controls2,appletGBConstr);

	  //======= Lay out controls. ========
	  applet.controls.setLayout(controlsGBLayout);
	  //-- timePanel
	  controlsGBConstr.fill = GridBagConstraints.NONE;
	  controlsGBConstr.gridx = 0;
	  controlsGBConstr.gridy = 0;
	  controlsGBConstr.gridwidth = 3;
	  controlsGBConstr.gridheight = 1;
	  applet.controls.add(applet.timePanel,controlsGBConstr);
	  //-- dstPanel
	  controlsGBConstr.fill = GridBagConstraints.NONE;
	  controlsGBConstr.gridx = 0;
	  controlsGBConstr.gridy = 1;
	  controlsGBConstr.gridwidth = 3;
	  controlsGBConstr.gridheight = 1;
	  applet.controls.add(applet.dstPanel,controlsGBConstr);
	  //-- dayText
	  controlsGBConstr.fill = GridBagConstraints.NONE;
	  controlsGBConstr.gridx = 0;
	  controlsGBConstr.gridy = 2;
	  controlsGBConstr.gridwidth = 1;
	  controlsGBConstr.gridheight = 1;
	  applet.controls.add(applet.dayText,controlsGBConstr);
	  //-- monthChoice
	  controlsGBConstr.fill = GridBagConstraints.NONE;
	  controlsGBConstr.gridx = 1;
	  controlsGBConstr.gridy = 2;
	  controlsGBConstr.gridwidth = 1;
	  controlsGBConstr.gridheight = 1;
	  applet.controls.add(applet.monthChoice,controlsGBConstr);
	  //-- yearText
	  controlsGBConstr.fill = GridBagConstraints.NONE;
	  controlsGBConstr.gridx = 2;
	  controlsGBConstr.gridy = 2;
	  controlsGBConstr.gridwidth = 1;
	  controlsGBConstr.gridheight = 1;
	  applet.controls.add(applet.yearText,controlsGBConstr);
	  //-- plus and minus buttons
	  /*
	  controlsGBConstr.fill = GridBagConstraints.NONE;
	  controlsGBConstr.gridy = 3;
	  controlsGBConstr.gridwidth = 1;
	  controlsGBConstr.gridheight = 1;
	  controlsGBConstr.gridx = 0;
	  controls.add(dayPlusButton,controlsGBConstr);
	  controlsGBConstr.gridx = 1;
	  controls.add(monthPlusButton,controlsGBConstr);
	  controlsGBConstr.gridx = 2;
	  controls.add(yearPlusButton,controlsGBConstr);
	  controlsGBConstr.gridy = 4;
	  controlsGBConstr.gridx = 0;
	  controls.add(dayMinusButton,controlsGBConstr);
	  controlsGBConstr.gridx = 1;
	  controls.add(monthMinusButton,controlsGBConstr);
	  controlsGBConstr.gridx = 2;
	  controls.add(yearMinusButton,controlsGBConstr);
	  */
	  //-- locationPanel
	  controlsGBConstr.fill = GridBagConstraints.NONE;
	  controlsGBConstr.gridx = 0;
	  controlsGBConstr.gridy = 6;
	  controlsGBConstr.gridwidth = 3;
	  controlsGBConstr.gridheight = 1;
	  applet.controls.add(applet.locationPanel,controlsGBConstr);
	  //-- updateButton
	  /*
	  controlsGBConstr.fill = GridBagConstraints.NONE;
	  controlsGBConstr.gridx = 1;
	  controlsGBConstr.gridy = 6;
	  controlsGBConstr.gridwidth = 1;
	  controlsGBConstr.gridheight = 1;
	  controls.add(updateButton,controlsGBConstr);
	  */
	  //-- nakedEyeOnlyCheckbox
	  controlsGBConstr.fill = GridBagConstraints.NONE;
	  controlsGBConstr.gridx = 0;
	  controlsGBConstr.gridy = 7;
	  controlsGBConstr.gridwidth = 2;
	  controlsGBConstr.gridheight = 1;
	  applet.controls.add(applet.nakedEyeOnlyCheckbox,controlsGBConstr);
	  
	  //===== Lay out controls2
	  applet.controls2.setLayout(controls2GBLayout);
	  //-- viewCheckboxPanel
	  controls2GBConstr.fill = GridBagConstraints.HORIZONTAL;
	  controls2GBConstr.gridx = 0;
	  controls2GBConstr.gridy = 0;
	  controls2GBConstr.gridwidth = 1;
	  controls2GBConstr.gridheight = 1;
	  controls2GBConstr.weightx = 1;
	  applet.controls2.add(applet.viewCheckboxPanel,controls2GBConstr);
	  //-- homePageAndAuthorStaticText
	  controls2GBConstr.fill = GridBagConstraints.HORIZONTAL;
	  controls2GBConstr.gridx = 0;
	  controls2GBConstr.gridy = 1;
	  controls2GBConstr.gridwidth = 1;
	  controls2GBConstr.gridheight = 1;
	  controls2GBConstr.weightx = 1;
	  applet.controls2.add(applet.homePageAndAuthorStaticText,controls2GBConstr);
	  //-- homePageAndAuthorStaticText
	  controls2GBConstr.fill = GridBagConstraints.HORIZONTAL;
	  controls2GBConstr.gridx = 0;
	  controls2GBConstr.gridy = 3;
	  controls2GBConstr.gridwidth = 1;
	  controls2GBConstr.gridheight = 1;
	  controls2GBConstr.weightx = 1;
	  applet.controls2.add(applet.homePageAndAuthor2StaticText,controls2GBConstr);

	  //===== Lay out locationPanel
	  applet.locationPanel.setLayout(locationLayout);
	  //-- cityChoice
	  locationGBConstr.fill = GridBagConstraints.NONE;
	  locationGBConstr.gridx = 0;
	  locationGBConstr.gridy = 0;
	  locationGBConstr.gridwidth = 1;
	  locationGBConstr.gridheight = 1;
	  locationGBConstr.anchor = GridBagConstraints.WEST;
	  applet.locationPanel.add(applet.cityChoice,locationGBConstr);
	  //-- latPanel
	  locationGBConstr.fill = GridBagConstraints.NONE;
	  locationGBConstr.gridx = 0;
	  locationGBConstr.gridy = 1;
	  locationGBConstr.gridwidth = 1;
	  locationGBConstr.gridheight = 1;
	  locationGBConstr.anchor = GridBagConstraints.WEST;
	  applet.locationPanel.add(applet.latPanel,locationGBConstr);
	  //-- lonPanel
	  locationGBConstr.fill = GridBagConstraints.NONE;
	  locationGBConstr.gridx = 0;
	  locationGBConstr.gridy = 2;
	  locationGBConstr.gridwidth = 1;
	  locationGBConstr.gridheight = 1;
	  locationGBConstr.anchor = GridBagConstraints.WEST;
	  applet.locationPanel.add(applet.lonPanel,locationGBConstr);

	  
	  //======= Lay out viewCheckboxPanel ======
	  applet.viewCheckboxPanel.setLayout(viewCheckboxPanelLayout);
	  //--- skyCheckbox
	  viewCheckboxPanelGBConstr.fill = GridBagConstraints.NONE;
	  viewCheckboxPanelGBConstr.gridx = 0;
	  viewCheckboxPanelGBConstr.gridy = 0;
	  viewCheckboxPanelGBConstr.gridwidth = 1;
	  viewCheckboxPanelGBConstr.gridheight = 1;
	  applet.viewCheckboxPanel.add(applet.skyCheckbox,viewCheckboxPanelGBConstr);
	  //--- innerSolarSystemCheckbox
	  viewCheckboxPanelGBConstr.fill = GridBagConstraints.NONE;
	  viewCheckboxPanelGBConstr.gridx = 1;
	  viewCheckboxPanelGBConstr.gridy = 0;
	  viewCheckboxPanelGBConstr.gridwidth = 1;
	  viewCheckboxPanelGBConstr.gridheight = 1;
	  applet.viewCheckboxPanel.add(applet.innerSolarSystemCheckbox,viewCheckboxPanelGBConstr);
	  //--- outerSolarSystemCheckbox
	  viewCheckboxPanelGBConstr.fill = GridBagConstraints.NONE;
	  viewCheckboxPanelGBConstr.gridx = 2;
	  viewCheckboxPanelGBConstr.gridy = 0;
	  viewCheckboxPanelGBConstr.gridwidth = 1;
	  viewCheckboxPanelGBConstr.gridheight = 1;
	  applet.viewCheckboxPanel.add(applet.outerSolarSystemCheckbox,viewCheckboxPanelGBConstr);
	  
	  
	  //======= Lay out timePanel. ========
	  applet.timePanel.setLayout(timeGBLayout);
	  //-- Label
	  timeGBConstr.fill = GridBagConstraints.NONE;
	  timeGBConstr.gridx = 0;
	  timeGBConstr.gridy = 0;
	  timeGBConstr.gridwidth = 1;
	  timeGBConstr.gridheight = 1;
	  applet.timePanel.add(applet.localTimeStaticText,timeGBConstr);
	  //-- hourText
	  timeGBConstr.fill = GridBagConstraints.NONE;
	  timeGBConstr.gridx = 1;
	  timeGBConstr.gridy = 0;
	  timeGBConstr.gridwidth = 1;
	  timeGBConstr.gridheight = 1;
	  applet.timePanel.add(applet.hourText,timeGBConstr);
	  //-- colon
	  timeGBConstr.fill = GridBagConstraints.NONE;
	  timeGBConstr.gridx = 2;
	  timeGBConstr.gridy = 0;
	  timeGBConstr.gridwidth = 1;
	  timeGBConstr.gridheight = 1;
	  applet.timePanel.add(new StaticText(":"),timeGBConstr);
	  //-- minuteText
	  timeGBConstr.fill = GridBagConstraints.NONE;
	  timeGBConstr.gridx = 3;
	  timeGBConstr.gridy = 0;
	  timeGBConstr.gridwidth = 1;
	  timeGBConstr.gridheight = 1;
	  applet.timePanel.add(applet.minuteText,timeGBConstr);

	  //-- timeZoneLabel
	  timeGBConstr.fill = GridBagConstraints.NONE;
	  timeGBConstr.gridx = 0;
	  timeGBConstr.gridy = 1;
	  timeGBConstr.gridwidth = 2;
	  timeGBConstr.gridheight = 1;
	  applet.timePanel.add(applet.timeZoneLabel,timeGBConstr);
	  //-- timeZoneText
	  timeGBConstr.fill = GridBagConstraints.NONE;
	  timeGBConstr.gridx = 3;
	  timeGBConstr.gridy = 1;
	  timeGBConstr.gridwidth = 2;
	  timeGBConstr.gridheight = 1;
	  applet.timePanel.add(applet.timeZoneText,timeGBConstr);



	  //======= Lay out dstPanel. ========
	  applet.dstPanel.setLayout(dstLayout);
	  //-- dstCheckbox
	  dstGBConstr.fill = GridBagConstraints.NONE;
	  dstGBConstr.gridx = 0;
	  dstGBConstr.gridy = 0;
	  dstGBConstr.gridwidth = 1;
	  dstGBConstr.gridheight = 1;
	  applet.dstPanel.add(applet.dstCheckbox,dstGBConstr);
	  //-- dstAutoCheckbox
	  dstGBConstr.fill = GridBagConstraints.NONE;
	  dstGBConstr.gridx = 1;
	  dstGBConstr.gridy = 0;
	  dstGBConstr.gridwidth = 1;
	  dstGBConstr.gridheight = 1;
	  applet.dstPanel.add(applet.dstAutoCheckbox,dstGBConstr);
	  //-- dstManualCheckbox
	  dstGBConstr.fill = GridBagConstraints.NONE;
	  dstGBConstr.gridx = 2;
	  dstGBConstr.gridy = 0;
	  dstGBConstr.gridwidth = 1;
	  dstGBConstr.gridheight = 1;
	  applet.dstPanel.add(applet.dstManualCheckbox,dstGBConstr);

	  //======= Lay out latPanel. ========
	  applet.latPanel.setLayout(latGBLayout);
	  //--- label
	  latGBConstr.fill = GridBagConstraints.NONE;
	  latGBConstr.gridx = 0;
	  latGBConstr.gridy = 0;
	  latGBConstr.gridwidth = 1;
	  latGBConstr.gridheight = 1;
	  applet.latPanel.add(applet.latitudeStaticText,latGBConstr);
	  //--- latitude
	  latGBConstr.fill = GridBagConstraints.NONE;
	  latGBConstr.gridx = 1;
	  latGBConstr.gridy = 0;
	  latGBConstr.gridwidth = 1;
	  latGBConstr.gridheight = 1;
	  applet.latPanel.add(applet.yourLatText,latGBConstr);
	  //--- latitude north
	  latGBConstr.fill = GridBagConstraints.NONE;
	  latGBConstr.gridx = 2;
	  latGBConstr.gridy = 0;
	  latGBConstr.gridwidth = 1;
	  latGBConstr.gridheight = 1;
	  applet.latPanel.add(applet.latNCheckbox,latGBConstr);
	  //--- latitude south
	  latGBConstr.fill = GridBagConstraints.NONE;
	  latGBConstr.gridx = 3;
	  latGBConstr.gridy = 0;
	  latGBConstr.gridwidth = 1;
	  latGBConstr.gridheight = 1;
	  applet.latPanel.add(applet.latSCheckbox,latGBConstr);
	
	  
	  //======= Lay out lonPanel. ========
	  applet.lonPanel.setLayout(lonGBLayout);
	  //--- label
	  lonGBConstr.fill = GridBagConstraints.NONE;
	  lonGBConstr.gridx = 0;
	  lonGBConstr.gridy = 0;
	  lonGBConstr.gridwidth = 1;
	  lonGBConstr.gridheight = 1;
	  applet.lonPanel.add(applet.longitudeStaticText);
	  //--- longitude
	  lonGBConstr.fill = GridBagConstraints.NONE;
	  lonGBConstr.gridx = 1;
	  lonGBConstr.gridy = 0;
	  lonGBConstr.gridwidth = 1;
	  lonGBConstr.gridheight = 1;
	  applet.lonPanel.add(applet.yourLongText,lonGBConstr); 
	  //--- longitude east
	  lonGBConstr.fill = GridBagConstraints.NONE;
	  lonGBConstr.gridx = 2;
	  lonGBConstr.gridy = 0;
	  lonGBConstr.gridwidth = 1;
	  lonGBConstr.gridheight = 1;
	  applet.lonPanel.add(applet.lonECheckbox,lonGBConstr);
	  //--- longitude west
	  lonGBConstr.fill = GridBagConstraints.NONE;
	  lonGBConstr.gridx = 3;
	  lonGBConstr.gridy = 0;
	  lonGBConstr.gridwidth = 1;
	  lonGBConstr.gridheight = 1;
	  applet.lonPanel.add(applet.lonWCheckbox,lonGBConstr);


	  }


}

