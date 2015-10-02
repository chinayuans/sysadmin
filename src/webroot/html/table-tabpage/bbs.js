/*
** getCookie(name)
*/
var wwwparams        = 'WWWPARAMS';
var defaultStyleFile = 'default.css';
var midStyleFile     = 'default-mf.css';
var bigStyleFile     = 'default-bf.css';
var smlStyleFile     = 'default-sf.css';
var bbsStyle;



function getCookie(name)
{
	var cookieFound = false;
	var start = 0;
	var end   = 0;
	var cookieString = document.cookie;
	var i = 0;
	while(i <= cookieString.length){
		start = i;
		end   = start + name.length;
		if(cookieString.substring(start,end)==name){
			cookieFound = true;
			break;
		}
		i++;
	}
	if(cookieFound){
		start = end + 1;
		end = cookieString.indexOf(";",start);
		if(end < start)
			end = cookieString.length;
		return unescape(cookieString.substring(start,end));
	}
	return false;
}
/*
** getBbsStyle()
*/
function getBbsStyleFile()
{
	var bbsStyle = getCookie(wwwparams);
	if(!bbsStyle)
		return defaultStyleFile;
	var styleString = new String(bbsStyle);
	switch(styleString.substring(0,1))
	{
		case "1":
			return smlStyleFile;
			break;
		case "2":
			return midStyleFile;
			break;
		case "3":
			return bigStyleFile;
			break;
		default:
	}
	return defaultStyleFile;
}
