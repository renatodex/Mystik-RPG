<?php
// The file
$filename=$_GET['f'];
$crop_height=32*$_GET['x'];
$crop_width=32*$_GET['y'];




// Content type
header('Content-Type: image/gif');

// Get new dimensions
list($width, $height) = getimagesize($filename);

$new_width = 32;
$new_height = 32;

// Resample
$image_p = imagecreatetruecolor($new_width, $new_height);
$image = imagecreatefromgif($filename);

 imagecolortransparent($image_p, imagecolorallocatealpha($image_p , 0, 0, 0, 127));
    imagealphablending($image_p, false);
    imagesavealpha($image_p, true);

imagecopyresampled($image_p, $image, 0, $crop_width, $crop_height, 0, 32, 32, 32, 32);

// Output
imagegif($image_p, null, 100);
?>