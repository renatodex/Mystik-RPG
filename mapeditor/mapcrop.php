<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />

    <script type="text/javascript" src="js/jQuery-1.8.js"></script>
    <script src="js/jquery-ui.min.js"></script>

<style type="text/css">
body {
    background:#6babc8;
}
#content {
    width:600px;
    background:#c9eab9;
    border:5px solid #a2cc8d;
    padding:10px;
    color:#727d6c;
    margin:0 auto;
    clear:both;
}
#content h1 {
    margin:0 0 10px 0;
    padding:0;
    font:22pt Georgia;
    border-bottom:1px solid #fff;
}
#content a {
    color:#1B8EE0;
}
#show {
    float:left;
    width:32px;
    background:#dad49e;
    padding:15px;
    border:2px solid #aba67f;
}
#find {
    margin-bottom:5px;
}
#find input {
    margin:0; padding:0;
    background:#a7bbd8;
    padding:5px;
    border:2px solid #6f83a1;
    color:#3f4e64;    
}
#input {
    float:left;
    margin-right:10px;
}
#input input {
    margin:0; padding:0;
    background:#d4a9a9;
    padding:3px;
    border:2px solid #ab7f7f;
    outline:none;
    color:#663535;
}
.y-int, .x-int {
    margin-bottom:5px !important;
}
#chooser {
    margin-top:5px;
}
#pick {
    position:relative;
    top:0;
    left:0;
    clear:both;
}
#sel {
    position:absolute;
    background:url("monster.gif") no-repeat;
    width:576px;
    height:32px;
    margin-bottom:5px;
    clear:both;
}
#whole {
    margin-top:10px;
}
#file-now {
    float:left;
    margin-left:5px;
    font:12px monospace;
}
#file {
    color:red;
}
#file-x, #file-y {
    color:blue;
}
</style>

<script type="text/javascript">
	$(document).ready(function()
{
   $(".submit").live("click",function(){
     var yint = $(".y-int").val();    
     var xint = $(".x-int").val();
     
     if (xint > 18 || xint < 0) {
        alert('Must be between 0 through 18.');
     }else if (yint > 0 || xint < 0) {
        alert('Must be between 0 through 0.');
     }else{
     $('.selector').attr("src", "crop.php?f=monster.gif&y=0&x="+xint);
     var goFar = xint*32;
     $('#pick').animate({"margin-left": goFar+"px"}, 500);
     $('#file-y').effect("pulsate");
     $('#file-x').effect("pulsate");
     $("#file-x").text(xint);
     $("#file-y").text(yint);
     }
    });
    
    
function textReplacement(input){
   var originalvalue = input.val();
   input.focus( function(){
      if( $.trim(input.val()) === originalvalue ){ input.val(""); }
   });
   input.blur( function(){
      if( $.trim(input.val()) === "" ){ input.val(originalvalue); }
   });
}
textReplacement($('.x-int'));
});
</script>

	<title>Monster Image Cropping via PHP</title>
</head>
<body>
<div id="content">
<h1>Monster Image Cropping via PHP</h1>
<div id="input">
<form>
<input disabled="disabled" type="text" class="y-int" title="Y Position" value="0" /> <br />
<input type="text" class="x-int" title="X" value="X Position (0-18)" /> <br />
</form></div>
<div id="show"><img alt="Monster" title="Still the same as 'monster.gif' but cropped." class="selector" src="crop.php?f=monster.gif&y=0&x=0"  /></div>
<div id="file-now">&lt;img src=&quot;<span id="file">crop.php?f=monster.gif&amp;y=<span id="file-y">0</span>&amp;x=<span id="file-x">0</span></span>&quot; /&gt;<br /><br />(to be added to the <a href="http://mystikrpg.com/mapeditor.php">online map editor</a>)</div>
<div style="clear: both;"></div>
<div id="find"><input class="submit" value="Crop image via PHP!" type="button"  /></div>

<div id="whole"><strong>file url:</strong> monster.gif | <strong>file size:</strong> 6,408 KB | <strong>calls:</strong> 1
<div id="sel"><img id="pick" alt="monster!" title="One image; cropped; one call." src="pick.gif" /></div></div>
<div style="margin-top: 10px;">&nbsp;</div>
</div>
</body>
</html>