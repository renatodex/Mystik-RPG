// Mystik Map Editor v1

/**
TO-DO List

4. Tools -> Line Tool, Rectangle Tool

**/

$(document).ready(function()
{

$('.tilelink').tooltip({ 
    track: true, 
    delay: 0, 
    showURL: false, 
    showBody: " - ", 
    fade: 250 
});

$('.monsterlink').tooltip({ 
    track: true, 
    delay: 0, 
    showURL: false, 
    showBody: " - ", 
    fade: 250 
});

    	$("#create").fancybox({
				'transitionIn'		: 'fade',
                'titlePosition' 	: 'over',
				'transitionOut'		: 'fade'
			});
            
            
    	$("#monstertest").fancybox({
				'transitionIn'		: 'fade',
                'titlePosition' 	: 'over',
				'transitionOut'		: 'fade'
			}); 
            
    	$("#weapontest").fancybox({
				'transitionIn'		: 'fade',
                'titlePosition' 	: 'over',
				'transitionOut'		: 'fade'
			});
    	
        $("#entrancetest").fancybox({
				'transitionIn'		: 'fade',
                'titlePosition' 	: 'over',
				'transitionOut'		: 'fade'
			});
            
        $("#teleporttest").fancybox({
				'transitionIn'		: 'fade',
                'titlePosition' 	: 'over',
				'transitionOut'		: 'fade'
			});
            
$("#map_title").keyup(function() {
    if($('#map_title').val().length > 0 && $('#map_id').val().length > 0) {
            $('#create-disabled').attr("class", "sexybutton sexysimple sexygreen sexylarge"); 
            $('create-disabled').attr("href", "#inline3"); 
    } else {
            $('#create-disabled').attr("class", "sexybutton sexysimple sexyred sexylarge"); 
            $('#create-disabled').attr("href", "#inline3");            
    }
});
      $("#map_id").keyup(function() {
    if($('#map_title').val().length > 0 && $('#map_id').val().length > 0) {
            $('#create').attr("class", "sexybutton sexysimple sexygreen sexylarge"); 
            $('#create').attr("href", "#inline3"); 
    } else {
            $('#create').attr("class", "sexybutton sexysimple sexyred sexylarge"); 
            $('#create').attr("href", "#"); 
    }
});
            
    	$(function() {
		$( "#tabs" ).tabs();
	});
    var weapons = new Array(); // weapons array
    var monsters = new Array(); // monsters array
    var entrances = new Array(); // entrances array
    var teleports = new Array(); // tele array
    var signs = new Array(); // signs array
    
    var tdToggle = new Boolean();
    tdToggle = false;
    
    // WARNING: Huge Freaking array
    var blockedTile = new Array("1","2","3","4","5","6","7","8","9","19","20","21","22","23","24","25","26","27","28","29","30","37","39","41","43","45","55","56","57","58","59","60","61","62","63","64","65","66","67","68","69","70","71","72","73","76","79","81","90","91","92","93","94","95","96","97","98","99","106","107","108","115","116","117","118","119","120","121","122","123","124","125","126","127","128","129","130","131","132","133","134","135","136","139","140","141","142","143","144","145","146","147","148","149","150","151","152","153","154","155","156","157","158","159","160","161","162","163","164","165","169","170","171","172","173","174","175","176","177","178","179","180","181","183","185","187","190","191","192","196","197","198","199","200","201","202","203","204","205","206","207","211","212","213","214","217","220","222","223","225","226","227","228","232","233","234","235","236","237","238","239","240","241","242","243","244","246","247","249","250","252","259","260","261","262","263","264","271","272","273","274","275","276","277","278","279","280","283","285","288","319","320","321","322","328","329","330","331","332","333","334","335","336","337","338","339","340","341","342","349","350","351","352","353","354","355","361","362","363","364","365","366","367","368","369","370","371","372","373","374","375","376","377","378","379","380","381","388","389","390","391","392","393","394","397","398","399","400","401","402","403","404","405","406","407","408","409","410","411","412","413","414","415","416","417","418","419","420","421","422","423","430","431","432","433","434","435","436","437","438","439","440","441","442","443","444","445","446","447","448","449","450","451","452","453","454","455","456","469","470","471","472","473","474","475","476","477","478","479","480","481","482","483","484","485","486","487","488","489","490","491","492","493","494","495","496","499","500","501","502","503","504","505","506","507","508","509","510","511","512","513");

    var entranceTile = new Array("28","29","30","37","41","43","45","58","59","60","61","62","63","73","75","76","79","91","92","93","94","95","96","97","98","99","115","116","117","136","138","139","140","141","142","143","144","172","173","174","175","175","176","177","178","179","180","181","183","185","187","189","199","200","201","205","206","207","214","217","220","238","239","240","244","247","250","259","260","261","271","272","273","280","283","496","500","501","502","503","504");
    var teleportTile = new Array("38","40","42","44","74","75","77","80","109","110","111","112","113","114","137","182","184","186","188","189","215","216","218","219","221","222","224","225","245","248","249","251","253","254","255","256","257","258","281","283","284","286","287","356","357","393","497","522");
    
    var monsterName = new Array("Goblin","Arachnid","Bald Eagle","Orc Warrior","Water Beast","Sand Drake","Human Mage","Fiery Salamandar","Skeleton Warrior","Human Warrior","Captain","Taurus","Maggot","Catapillar","Wasp","Human Knight","Basilisk","Red Dragon");
    var monsterLevel = new Array("4","1","9","11","12","16","18","14","15","19","22","23","3","2","1","21","25","30");
    
    var currentTile = "tile";
    // Set up default values
    $('#current_tile').attr("class", "0");
    
    $("#create").live("click",function(){
        
     var mapName = $("#map_title").val();
     var mapID = $("#map_id").val();  
     var rightID = $("#right_id").val();  
     var leftID = $("#left_id").val();  
     var downID = $("#down_id").val();
     var upID = $("#up_id").val();    
     
     if (mapName == "") {
        alert('Before creating a map, please enter a map name.');
     }else if (mapID == "") {
        alert('Before creating a map, please enter a map ID.');
     }else{
        
     $("#output").text(""); 
     
     $("#output").append("{<br />");

     $("#output").append("'title': '"+ mapName+"',<br />");

     $("#output").append("'ids': [{ currentID: "+mapID+", leftMap: "+leftID+", rightMap: "+rightID+", upMap: "+upID+", downMap: "+downID+"}],<br />");
     
       // start signs
         var signsLgth = signs.length;
         
         if (signsLgth > 0) {
            
            $("#output").append("'signs': [");
            
                    for (var signs_final = 0, setsLen_ent = signs.length; signs_final < setsLen_ent; ++signs_final ) {
                
                 var searchSign = signs[signs_final].split("|");
                 var final_sign = signs_final+1;
                  $("#output").append("<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{ x: " + searchSign[1] + ", y: " + searchSign[0] + ", text: '" + searchSign[2] + "'}");  
                  if (final_sign == signsLgth) { $("#output").append("<br />"); }else{ $("#output").append(","); }
                  }
                    $("#output").append("],<br />");
                  }
      //end signs
  
       // start entrances
         var entrancesLgth = entrances.length;
         
         if (entrancesLgth > 0) {
            
            $("#output").append("'entrances': [");
            
                    for (var entrances_final = 0, setsLen_ent = entrances.length; entrances_final < setsLen_ent; ++entrances_final ) {
                
                 var searchEnt = entrances[entrances_final].split("|");
                 var final_ent = entrances_final+1;
                  $("#output").append("<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{ x: " + searchEnt[1] + ", y: " + searchEnt[0] + ", tile_after: " + searchEnt[2] + ", item_req: " + searchEnt[3] + "}");  
                  if (final_ent == entrancesLgth) { $("#output").append("<br />"); }else{ $("#output").append(","); }
                  }
                    $("#output").append("],<br />");
                  }
      //end entrances
  
  
         // start teleports
         var teleportsLgth = teleports.length;
         
         if (teleportsLgth > 0) {
            
            $("#output").append("'teleports': [");
            
                    for (var teleports_final = 0, setsLen_tele = teleports.length; teleports_final < setsLen_tele; ++teleports_final ) {
                
                 var searchTele = teleports[teleports_final].split("|");
                 var final_ent = teleports_final+1;
                  $("#output").append("<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{ fromX: " + searchTele[1] + ", fromY: " + searchTele[0] + ", fromMap: " + searchTele[2] + ", toX: " + searchTele[4] + ", toY: " + searchTele[3] + ", toMap: " + searchTele[5] + ", item_req: " + searchTele[6] + "}");  
                  if (final_ent == teleportsLgth) { $("#output").append("<br />"); }else{ $("#output").append(","); }
                  }
                    $("#output").append("],<br />");
                  }
      //end teleports
  
     
     // items
         var itemLgth_final = weapons.length;
         
         if (itemLgth_final > 0) {
            
            $("#output").append("'items': [");
            
                    for (var item_final = 0, setsLen_tem = weapons.length; item_final < setsLen_tem; ++item_final ) {
                
                 var searchItem_final = weapons[item_final].split("|");
                 var final_item = item_final+1;
                 var actualID = parseFloat(searchItem_final[0])+1;
                 
                  $("#output").append("<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{ id: '" + actualID + "', x: " + searchItem_final[2] + ", y: " + searchItem_final[1] + "}");  
                  if (final_item == itemLgth_final) { $("#output").append("<br />"); }else{ $("#output").append(","); }
                  }
                    $("#output").append("],<br />");
                  }
      //end items
      
           
     // monsters
         var monLgth_final = monsters.length;
         
         if (monLgth_final > 0) {
            
            $("#output").append("'monsters': [");
            
                    for (var i_final = 0, setsLen_final = monsters.length; i_final < setsLen_final; ++i_final ) {
                
                 var searchMonster_final = monsters[i_final].split("|");
                 var final_mon = i_final+1;
                 var acutalMon = parseInt(searchMonster_final[0])+1;                 

                  $("#output").append("<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{ id: '" +acutalMon + "', x: " + searchMonster_final[2] + ", y: " + searchMonster_final[1] + "}");  
                  if (final_mon == monLgth_final) { $("#output").append("<br />"); }else{ $("#output").append(","); }
                  }
                    $("#output").append("],<br />");
                  }
      //end monsters 
     
  $("#output").append("'map': [ ");
for (var q = 0; q <= 10; q++) {
    $("#output").append("<br />[ ");
    //$("#output").append("<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ ");
for (var xq = 0; xq <= 15; xq++) {
    var x_out = q;
    var getTileID =  $('#'+q+"x"+xq).attr("name");
    
if (xq == 15) { $("#output").append(getTileID); }else{ $("#output").append(getTileID +", "); }
  
}
$("#output").append("]");
if (q == 10) { $("#output").append(""); }else{ $("#output").append(","); }
$("#output").append("\n");
}
    $("#output").append("<br />]");    
      $("#output").append("<br />};");    
    }
        });
        
      // Tile dropper
    $("#tile_dropper").live("click",function(){
        
        
if (tdToggle == false) {
        currentTile = "dropper";
        tdToggle = true;
        $('#tile_dropper').attr("class", "sexybutton sexysimple sexygreen sexylarge");
        }else{
         currentTile = "tile"; 
         tdToggle = false;
         $('#tile_dropper').attr("class", "sexybutton sexysimple sexyred sexylarge");
        }
        
        
        });
    
    // Selected tile
    $(".tilelink").live("click",function(){
        
        var ID = $(this).attr("id");
        

        
        $('#current_tile').attr("src", "line_tile/t"+ID+".png"); 
        $('#current_tile').attr("class", ID);        
      
      

        $('.item').css("background-color", "#666");
        $('#item-'+ID).css("background-color", "red");
        currentTile = "tile";
        
        
if(jQuery.inArray(ID, blockedTile) != -1) {
            $('#tile_status').text("Blocked");
            $('#tile_status').attr("class", "sexybutton sexysimple sexyred sexylarge"); 
            }else{
             $('#tile_status').text("Walkable"); 
             $('#tile_status').attr("class", "sexybutton sexysimple sexygreen sexylarge"); 
            } 
        
        
        
        });
        
     // Selected weapon   
    $(".weaponlink").live("click",function(){
        var ID = $(this).attr("id");
        
        $('#current_tile').attr("src", "weapon/w"+ID+".gif"); 
        $('#current_tile').attr("class", ID);        
      
    

    $('#weapon-'+ID).effect("pulsate");
    currentTile = "weapon";

        
        });
     
    // Selected monsters  
    $(".monsterlink").live("click",function(){
        var ID = $(this).attr("id");
        
        $('#current_tile').attr("src", "monster/m"+ID+".gif"); 
        $('#current_tile').attr("class", ID);        
      
    

    $('#monster-'+ID).effect("pulsate");
    currentTile = "monster";

        
        });
        
        
      
        
// Delete weapons  
   $(".delete-weapon").live("click",function(){ 
    
    
    
    $('#current_tile').attr("src", "delete.png"); 
    $('#current_tile').attr("class", "delete-weapon");  
    $('.delete-weapon').effect("pulsate"); 
    currentTile = "delete-weapon";   
    
    });
    
   $(".delete-monster").live("click",function(){ 
    $('#current_tile').attr("src", "delete.png"); 
    $('#current_tile').attr("class", "delete-monster");  
    $('.delete-monster').effect("pulsate");
    currentTile = "delete-monster";  
    
    });
    
    $("#entrancetest").live("click",function(){
        
        $("#entrance_stat").text(""); 
        
        var entLgth_t = entrances.length;
         $("#entrance_stat").append('Showing entrances on Map - How many: ' + entLgth_t+'<br />');
   
         if (entLgth_t > 0) {
                    for (var i = 0, entsLens = entrances.length; i < entsLens; ++i ) {
                
                 var searchEnts = entrances[i].split("|");
                  $("#entrance_stat").append('['+i+'] >> Entrance Tile ID: ' + searchEnts[0] + ' | Y: ' + searchEnts[2] + ' | X: ' + searchEnts[1] + '<br />'); 
                  
                  }
                  }else{
                   $("#entrance_stat").append('\nNo entrances on the map!'); 
                  }
                  });
    
    $("#monstertest").live("click",function(){
        
        $("#stats").text(""); 
        
        var monLgth = monsters.length;
         $("#stats").append('Showing monsters on Map - How many: ' + monLgth+'<br />');
   
         if (monLgth > 0) {
                    for (var i = 0, monsLen = monsters.length; i < monsLen; ++i ) {
                
                 var searchMonster = monsters[i].split("|");
                  $("#stats").append('['+i+'] >> Monster ID: ' + searchMonster[0] + ' | Y: ' + searchMonster[1] + ' | X: ' + searchMonster[2] + '<br />'); 
                  
                  }
                  }else{
                   $("#stats").append('\nNo monsters on the map!'); 
                  }
                  });
    
    $("#weapontest").live("click",function(){
        
       
        $("#stats_weapon").text("");
        
        var wepLgth = weapons.length;
         $("#stats_weapon").append('Showing items on Map - How many: ' + wepLgth + '<br />');

         if (wepLgth > 0) {
                    for (var i = 0, setsLen = weapons.length; i < setsLen; ++i ) {
                
                 var searchWeapon = weapons[i].split("|");
                  $("#stats_weapon").append('['+i+'] >> Item ID: ' + searchWeapon[0] + ' | Y: ' + searchWeapon[1] + ' | X: ' + searchWeapon[2] + '<br />');  
                  
                  }
                  }else{
                   $("#stats_weapon").append('\nNo items on the map!'); 
                  }
                  });

        // Clicked on a monster
$(".monster-map").live("click",function(){ 
          var ID_map = $('#current_tile').attr("class");
        var ID_now = $(this).attr("id");
        var monID = $(this).attr("name");
        var monClass = $(this).attr("class");
        
       if (currentTile != "delete-weapon") {
                
        var tile = ID_now.split("x"); 
        var Y = tile[0];
        var X = tile[1];
        // tile[0] = y | tile[1] = x

       // We clicked on weapon
           if (ID_map == "delete-monster") {
            
            for (var i = 0, monsLen = monsters.length; i < monsLen; ++i ) {
                
                 var searchMonster = monsters[i].split("|");
                 // console.log('['+i+'] >> Weapon ID: ' + searchWeapon[0] + ' | Y: ' + searchWeapon[1] + ' | X: ' + searchWeapon[2]);    
                 if (searchMonster[1] == Y && searchMonster[2] == X) { 
                    monsters.splice(i,1);
                    //$("#"+Y+"x"+X+"."+wepClass).remove();
                    $(this).remove();
                    console.log("Removing monster image ID: #"+Y+"x"+X+"."+monClass);
                 }          
                }
        }else{
        
        if (currentTile == "tile") {
        $('#'+Y+"x"+X).attr("src", "line_tile/t"+ID_map+".png"); 
        $('#'+Y+"x"+X).attr("name", ID_map); 
        }else if (currentTile == "monster") {
            
           
        alert('Sorry, you cannot place a monster on top of another monster!');
        
            }else if (currentTile == "weapon") {
        
        alert('Sorry, you cannot place an item on top of a monster!');
            
            }
            
            }
            
            }else{
                alert('To delete a monster, please use the appropiate X.');
            }
       
        
          });
        // Clicked on a weapon
$(".weapon-map").live("click",function(){ 
          var ID_map = $('#current_tile').attr("class");
        var ID_now = $(this).attr("id");
        var wepID = $(this).attr("name");
        var wepClass = $(this).attr("class");
        
       if (currentTile != "delete-monster") {
                
        var tile = ID_now.split("x"); 
        var Y = tile[0];
        var X = tile[1];
        // tile[0] = y | tile[1] = x

       // We clicked on weapon
           if (currentTile == "weapon") {
            alert('You cannot put an item ontop of another item!');
            }else if (currentTile == "monster") {
            alert('You cannot put a monster ontop of an item!');
            }else if (ID_map == "delete-weapon") {
            
            for (var i = 0, setsLen = weapons.length; i < setsLen; ++i ) {
                
                 var searchWeapon = weapons[i].split("|");
                 // console.log('['+i+'] >> Weapon ID: ' + searchWeapon[0] + ' | Y: ' + searchWeapon[1] + ' | X: ' + searchWeapon[2]);    
                 if (searchWeapon[1] == Y && searchWeapon[2] == X) { 
                    weapons.splice(i,1);
                    //$("#"+Y+"x"+X+"."+wepClass).remove();
                    $(this).remove();
                    console.log("Removing image ID: #"+Y+"x"+X+"."+wepClass);
                 }          
                }
                
            for (var i = 0, signsLen = signs.length; i < signsLen; ++i ) {
                
                 var searchSign = signs[i].split("|");
                 // console.log('['+i+'] >> Weapon ID: ' + searchWeapon[0] + ' | Y: ' + searchWeapon[1] + ' | X: ' + searchWeapon[2]);    
                 if (searchSign[0] == X && searchSign[1] == Y) { 
                    signs.splice(i,1);
                    
                    $(this).remove();
                    console.log("Removing sign...");
                 }          
                }
        }else{
 
        if (currentTile == "tile") {
        $('#'+Y+"x"+X).attr("src", "line_tile/t"+ID_map+".png"); 
        $('#'+Y+"x"+X).attr("name", ID_map); 
        }else if (currentTile == "weapon") {
            
           
        $('#a-'+Y+"x"+X).append('<img style="z-index:2" id="weapon-'+ID_map+'" class="weapon-map" src="weapon/w'+ID_map+'.gif" />');
        $('#a-'+Y+"x"+X).attr("name", ID_map);
        
            }else if (currentTile == "monster") {
        
        alert('Sorry, you cannot place an item on top of a weapon!');
            
            }
            
            }
            
            }else{
                alert('To delete an item, please use the appropiate X.');
            }
       
        
          });
    
 /**
    // toggle grid  
    
$("#togglegrid").live("click",function(){
    
    $('#gamemap').append('<img alt="sup" title="titleddd" style="z-index:5;position:absolute;top:0;" class="weapon-map" src="outline.gif" />');

    });   **/     
          
          // clicked on a tile
$(".maptile").live("click",function(){
    

        var ID_map = $('#current_tile').attr("class");
        var ID_now = $(this).attr("id");
        var ID_name = $(this).attr("name");
        
                        
        var tile = ID_now.split("x"); 
        var Y = tile[0];
        var X = tile[1];
        
        
        
        
            if (currentTile == "dropper") {
        
        
        if(jQuery.inArray(ID_name, blockedTile) != -1) {
            $('#tile_status').text("Blocked");
            $('#tile_status').attr("class", "sexybutton sexysimple sexyred sexylarge"); 
            }else{
             $('#tile_status').text("Walkable"); 
             $('#tile_status').attr("class", "sexybutton sexysimple sexygreen sexylarge"); 
            } 
        
        $('#current_tile').attr("src", "line_tile/t"+ID_name+".png"); 
        $('#current_tile').attr("class", ID_name);        
      currentTile = "tile";
      tdToggle = false;
      $('#tile_dropper').attr("class", "sexybutton sexysimple sexyred sexylarge");
        
    }else if (ID_map == "delete-weapon") {
            alert("There's no weapon to delete!");
        }else if (ID_map == "delete-monster") {
            alert("There's no monster to delete!");
        }else{

        // tile[0] = y | tile[1] = x

        
        /**        
        // 0 X to Mouse X COVER
        for (var q = 0; q <= X; q++) {
         $('#'+Y+"x"+q).attr("src", "line_tile/t"+ID_map+".png");    
        }
        **/        
        
        // adding tile
        if (currentTile == "tile") {
            
          
        if(jQuery.inArray(ID_map, entranceTile) != -1) {
                
                
         for (var i = 0, entLen = entrances.length; i < entLen; ++i ) {
                        var searchEntx = entrances[i].split("|");
                 if (searchEntx[0] == Y && searchEntx[1] == X) {      
        entrances.splice(i,1);
        }
        }
        
        for (var i = 0, teleLen = teleports.length; i < teleLen; ++i ) {
            var searchTelex = teleports[i].split("|");
                 if (searchTelex[0] == Y && searchTelex[1] == X) {
                    teleports.splice(i,1);
                 }
        }
                
           var tileAfter_input = prompt ("Enter the tile ID that the door will be after it's opened.","0");
           var itemReq_input = prompt ("Enter the item ID required to have to open the door -- If none; type 0.","0");
                
         $('#'+Y+"x"+X).attr("src", "line_tile/t"+ID_map+".png"); 
        $('#'+Y+"x"+X).attr("name", ID_map); 

        entrances.push(Y+"|"+X+"|"+tileAfter_input+"|"+itemReq_input);
        
        }else if(jQuery.inArray(ID_map, teleportTile) != -1) {
           
           var mapID_tele = $("#map_id").val();
            
              if (mapID_tele == null) {
                alert("You need to give your map an ID using numbers, please.");
              }else{ 
                
        for (var i = 0, teleLen = teleports.length; i < teleLen; ++i ) {
            var searchTelex = teleports[i].split("|");
                 if (searchTelex[0] == Y && searchTelex[1] == X) {
                    teleports.splice(i,1);
                 }
        }
        
         for (var i = 0, entLen = entrances.length; i < entLen; ++i ) {
                        var searchEntx = entrances[i].split("|");
                 if (searchEntx[0] == Y && searchEntx[1] == X) {      
        entrances.splice(i,1);
        }
        }
                
           var teleX = prompt ("Enter the X coordinate to teleport too.","0");
           var teleY = prompt ("Enter the Y coordinate to teleport too.","0");
           var teleMap = prompt ("Enter the map ID to teleport too.","0");
           var teleItem = prompt ("Enter the item ID needed to teleport -- If none; type 0.","0");
    
        $('#'+Y+"x"+X).attr("src", "line_tile/t"+ID_map+".png"); 
        $('#'+Y+"x"+X).attr("name", ID_map); 

        teleports.push(Y+"|"+X+"|"+mapID_tele+"|"+teleY+"|"+teleX+"|"+teleMap+"|"+teleItem);
        }
        }else{
        $('#'+Y+"x"+X).attr("src", "line_tile/t"+ID_map+".png"); 
        $('#'+Y+"x"+X).attr("name", ID_map); 
        
        for (var i = 0, teleLen = teleports.length; i < teleLen; ++i ) {
            var searchTelex = teleports[i].split("|");
                 if (searchTelex[0] == Y && searchTelex[1] == X) teleports.splice(i,1);
        }
        
                    for (var i = 0, entLen = entrances.length; i < entLen; ++i ) {
                        var searchEntx = entrances[i].split("|");
                 if (searchEntx[0] == Y && searchEntx[1] == X) {      
        entrances.splice(i,1);
        }
        }
        }
        
        }else if (currentTile == "weapon") {
        // adding weapon
 
  if(ID_map == "6") {
     var signSays = prompt ("What would you like your sign to say?","");
     signs.push(X+"|"+Y+"|"+signSays+"|"+mapID_tele);
     
        $('#a-'+Y+"x"+X).append('<img title="monx" style="z-index:2" id="'+Y+'x'+X+'" name="'+ID_map+'" class="weapon-map" src="weapon/w'+ID_map+'.gif" />');
        $('#a-'+Y+"x"+X).attr("name", ID_map);
        
    }else if(jQuery.inArray(ID_name, teleportTile) != -1) {
     alert('You cannot place a weapon on a teleport tile!');
    }else if(jQuery.inArray(ID_name, blockedTile) != -1) {
            alert('You cannot place a weapon on a blocked tile!');
        }else{
        
        
        $('#a-'+Y+"x"+X).append('<img title="monx" style="z-index:2" id="'+Y+'x'+X+'" name="'+ID_map+'" class="weapon-map" src="weapon/w'+ID_map+'.gif" />');
        $('#a-'+Y+"x"+X).attr("name", ID_map);  
        weapons.push(ID_map+"|"+Y+"|"+X);
        
        for (var i = 0, setsLen = weapons.length; i < setsLen; ++i ) {
        //output each weapon
        //console.log("weapons["+i+"]" + weapons[i]);
        
        }
        }
            }else if (currentTile == "monster") {
        // adding weapon
         if(jQuery.inArray(ID_name, teleportTile) != -1) {
             alert('You cannot place a monster on a teleport tile!');
            }else if(jQuery.inArray(ID_name, blockedTile) != -1) {
            alert('You cannot place a monster on a blocked tile!');
        }else{
            
        $('#a-'+Y+"x"+X).append('<img style="z-index:2" id="'+Y+'x'+X+'" name="'+ID_map+'" class="monster-map" src="monster/m'+ID_map+'.gif" />');
        $('#a-'+Y+"x"+X).attr("name", ID_map);  
        monsters.push(ID_map+"|"+Y+"|"+X);
        
        for (var i = 0, monstersLen = monsters.length; i < monstersLen; ++i ) {
        //output each monster
        console.log("monsters["+i+"]" + monsters[i]);
        
        }
        }
            }else if (currentTile == "delete-monster" || currentTile == "delete-weapon") {
                alert('You cannot delete a tile!');
                }
            }
       
        });
        
        });