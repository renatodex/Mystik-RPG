<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
    <meta name="keywords" content="java, 2d, tile, map, editor, browser, based, text, rpg, mmorpg, mystik rpg, free, MUD, multi, user, dungeon, jtxtrpg, c++, c#" />
    <meta name="description" content="A map editor, created using PHP, jQuery, and JavaScript, that is used for Mystik RPG. " />
	<title>Mystik RPG Tile Map Editor v1.2</title>
    
    <script type="text/javascript" src="js/jQuery-1.8.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    
    <style>
    body {
        font-size:12px !important;
    }
    .tilelink img {
        padding:3px;
        background:#D6D6D6;
        margin-right:5px;
        margin-bottom:5px;
    }
    .monsterlink img {
        padding:3px;
        background:#C1F090;
        margin-right:5px;
        margin-bottom:5px;
    }
    .tile_map {
        position:relative;
    }
    .weaponlink img, #delete img {
        padding:3px;
        background:#E39F9F;
        margin-right:5px;
        margin-bottom:5px;
    }
    .weapon-map, .monster-map {
        position:absolute !important;
        top:-20px !important; /* -30 for IE */
        left:0 !important;
    }
    #current_tile {
        padding:3px;
        background:#D6D6D6;
    }
    #selected-tile {
        background:#D6D6D6;
    }
    button {
        padding:5px;
        font-weight:bold;
    }
    #map img {
        border:0;
    }
    #warn {
        margin-top:5px;
        width:500px;
        font:12px verdana;
    }      
    
.mapform {
font:12px georgia;
width:500px;
background:#F2F2F2;
padding:5px;
margin-bottom:5px;
}
.in {
background: url("css/input-bg.png") repeat-x left top;
color:#292929;
font:12px verdana;
outline:none;
padding:5px;
border: 1px solid #858585;
width:100px;
}
    </style>
    
        <script type="text/javascript" src="mapEditor.js"></script>
        
        <link rel="stylesheet" href="css/jquery.tooltip.css" />

<script src="js/jquery.tooltip.js" type="text/javascript"></script>

        
        <link rel="stylesheet" href="http://jqueryui.com/themes/base/jquery.ui.all.css" /> 
        	<script src="js/jquery.ui.core.js"></script> 
	<script src="js/jquery.ui.widget.js"></script> 
         <script src="js/jquery.ui.tabs.js"></script>  
        <link rel="stylesheet" href="http://jqueryui.com/demos/demos.css" />
       	<script type="text/javascript" src="./fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="./fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="./fancybox/jquery.fancybox-1.3.4.css" media="screen" />
    
    <link rel="stylesheet" href="sexybuttons.css" type="text/css" />
              

    
    
</head>

<body>
<span style="font:12px tahoma;font-weight:bold;">The Map</span>
<div style="clear: both;"></div>
<div id="map" style="float: left;width:512px;">
<?php
// START MAP
for ($i = 0; $i <= 10; $i++) {
for ($xi = 0; $xi <= 15; $xi++) {
    
    echo "<a href='#' id='a-".$i."x".$xi."'class='tile_map'><img id='".$i."x".$xi."' style='z-index:1;'  name='343' title='[y: ".$i.", x: ".$xi."]' class='maptile'  src='line_tile/t343.png' /></a>";
}
 echo "<br />";
}
// END MAP
?>


<div style="float: left;margin-bottom:10px;">
<br />
<div class="mapform">
map title: <input type="text" class="in" id="map_title" value="" title="a-z & A-Z ONLY." /></div>

<div class="mapform">

map id: <input type="text" class="in" id="map_id" style="width: 30px;margin-right:5px;" title="numbers only -- DO NOT PUT ZERO (0). Go from ... like 1 onwards." value="0" />
left id: <input type="text" class="in" id="left_id" style="width: 30px;margin-right:5px;" title="numbers only -- must refer to a map id" value="0" />
right id: <input type="text" class="in" id="right_id" style="width: 30px;margin-right:5px;" title="numbers only -- must refer to a map id" value="0" />
up id: <input type="text" class="in" id="up_id" style="width: 30px;;margin-right:5px;" title="numbers only -- must refer to a map id" value="0" />
down id: <input type="text" class="in" id="down_id" style="width: 30px;" title="numbers only -- must refer to a map id" value="0" />

</div>
</div><div style="clear: both;"></div>

<div style="float:left;margin-bottom:5px;">
<a href="#inline3" type="reset" id="create" class="sexybutton sexysimple sexygreen sexylarge">Create Map</a>


<a href="#inline1" type="reset" title="Tool: Select tile ID by selecting on the current map!" id="tile_dropper" class="sexybutton sexysimple sexyred sexylarge">Tile Dropper</a>

<!--
<a href="#inline1" type="reset" id="monstertest" class="sexybutton sexysimple sexyred sexylarge">List Monsters</a>
<a href="#inline2" type="reset" id="weapontest" class="sexybutton sexysimple sexyorange sexylarge">List Items</a>
<a href="#inline4" type="reset" id="entrancetest" class="sexybutton sexysimple sexyblue sexylarge">List Entrances</a> -->
</div>
<div style="clear: both;"></div>
<div>
<img style="position:relative;top:15px;margin-right:5px;" id="current_tile" title="Current tile" class="none" src="line_tile/t0.png" /> 
<button type="reset" id="tile_status" style="margin: 0;" class="sexybutton sexysimple sexygreen sexylarge">Walkable</button>
<br /><br />
Need help? Email weka - mystikrpg - com.
</div>
</div>
<div style="float: left;margin-left:10px;">

<div style="width: 338px;" id="tabs">
	<ul>
    	<li><a href="#tabs-1">Tiles</a></li>
		<li><a href="#tabs-2">Monsters</a></li>
		<li><a href="#tabs-3">Items</a></li>
	</ul>
	<div id="tabs-1">
	<div id="tile-div" style="height: 440px;overflow:auto;">
<?php
// TILE LIST
for ($i = 0; $i <= 522; $i++) {
    echo "<a id='".$i."' title='ID: ".$i."/522' href='#' name='item' class='tilelink'><img id='item-".$i."' style='background:#666'; class='item' src='line_tile/t".$i.".png' /></a>";
}

?>
</div>
     </div>
	<div id="tabs-2">
    	 <div id="monster-div" style="height: 170px;overflow:auto;">
<?php

$monsterName = array("Goblin","Arachnid","Bald Eagle","Orc Warrior","Water Beast","Sand Drake","Human Mage","Fiery Salamandar","Skeleton Warrior","Human Warrior","Captain","Taurus","Maggot","Catapillar","Wasp","Human Knight","Basilisk","Red Dragon");
$monsterLevel = array("4","1","9","11","12","16","18","14","15","19","22","23","3","2","1","21","25","30");
    

// MONSTER LIST
for ($i = 0; $i <= 17; $i++) {
    echo "<a id='".$i."' href='#' name='monster' title='".$monsterName[$i]." - Lvl ".$monsterLevel[$i]."' class='monsterlink'><img id='monster-".$i."' class='monster' src='monster/m".$i.".gif' /></a>";
}

?>
<a href="#" title="Delete monsters from map" class="delete" id="delete"><img src="delete.png" class="delete-monster" /></a>
</div>

    
    </div>
    	<div id="tabs-3">
	<div id="weapon-div" style="height:150px;overflow:auto;">
<?php
// WEAPON LIST
for ($i = 0; $i <= 6; $i++) {
    $ekid = $i+1;
    echo "<a id='".$i."' href='#' name='weapon' class='weaponlink'><img title='".$ekid."' id='weapon-".$i."' style='background:#D6D6D6'; class='weapon' src='weapon/w".$i.".gif' /></a>";
}

?>
<a href="#" title="Delete weapons from map" class="delete" id="delete"><img src="delete.png" class="delete-weapon" /></a>
</div></div>
</div>

</div>


       
	<div style="display: none;">
		<div id="inline1" style="width:500px;height:600px;overflow:auto;">
      <div id="stats" style="font-size:14px;font-family:lucida console, verdana, tahoma;"></div>
   
		
        </div>
        
		<div id="inline2" style="width:500px;height:600px;overflow:auto;">
      <div id="stats_weapon" style="font-size:14px;font-family:lucida console, verdana, tahoma;"></div>
        </div>
        
       <div id="inline5" style="width:500px;height:600px;overflow:auto;">
      <div id="stat_tele" style="font-size:14px;font-family:lucida console, verdana, tahoma;"></div>
        </div>
        
        
		<div id="inline3" style="width:600px;height:600px;overflow:auto;">
      <div id="output" style="font-size:12px;font-family:'Lucida Console', verdana, tahoma;"></div>
        </div>
        
		<div id="inline4" style="width:500px;height:600px;overflow:auto;">
      <div id="entrance_stat" style="font-size:14px;font-family:'Lucida Console', verdana, tahoma;"></div>
        </div>
	</div>


</body>
</html>