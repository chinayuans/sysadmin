/*
 ______________________________________________________
/¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\
|                TabStrip 1.1 by EAE                   |
|                                                      |
|   Feel free to copy, use and change this script as   |
|   long as this part remains unchanged.               |
|                                                      |
|   If you have any questions and or comments please   |
|   E-mail me 'eae@eae.net'. If you're looking for     |
|   more JavaScripts etc, please check out my webpage  |
|                   'webfx.eae.net'                    |
|                                                      |
|                Created: October 18, 1998             |
|              Last Updated: August 13, 2000           |
\______________________________________________________/
 ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
*/

document.onmouseover = tabOver;
document.onmouseout  = tabOut;
document.onmousedown = tabDown;
document.onmouseup   = tabUp;

var eOld = null;

function tabOver() {
 var eSrc = window.event.srcElement;
	if (eSrc.tagName == "TH") {
		eSrc.style.color = 'blue';
	}
}

function tabOut() {
 var eSrc = window.event.srcElement;
	if (eSrc.tagName == "TH") {
		eSrc.style.color = 'black';
	}
}

function tabDown() {
 var eSrc = window.event.srcElement;
  if (eSrc.className == "tab-over") {
    eSrc.className = "tab-selected";
  }
}
function tabUp() {
 var eSrc = window.event.srcElement;
  if (eSrc.className == "tab-button") {
    if (eOld != null) {
      eOld.className = "tab-button";
      var eTxt2 = eval("txt" + eOld.id)
      eTxt2.style.display = "none";
    }
    eOld = eSrc;
    var eTxt = eval("txt" + eSrc.id)
    eSrc.className = "tab-selected";
    eTxt.style.display = "block";
  }
}
function setActiveTab(eSrc) {
  eOld = eSrc;
  eOld.className = "tab-selected"
  var eTxt = eval("txt" + eOld.id)
  eTxt.style.display = "block";
}