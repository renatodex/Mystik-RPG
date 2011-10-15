<!DOCTYPE html>
<html>
<head>
  <style>img{ height: 100px; float: left; }</style>
  <script src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>
  <div id="images">

</div>
<script>
$.getJSON("http://mystikrpg.com/main.txt",
  {
    tags: "title",
    tagmode: "any",
    format: "json"
  },
  function(data) {
    $.each(data.items, function(i,item){
	 $("#images").append(item.media.m);
      if ( i == 3 ) return false;
    });
  });</script>

</body>
</html>