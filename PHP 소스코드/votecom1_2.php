<?php
 
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con, "utf8"); 

//echo $candi_number;
$q = "select count(*) from user where vote = 'yes'";
$q1 = "select count(*) from user_check";

$r = mysqli_query($con, $q)
  or die("Error: ".mysqli_error($con));
  
$r1 = mysqli_query($con, $q1)
  or die("Error: ".mysqli_error($con));
  
$row = mysqli_fetch_array($r);
$row1 = mysqli_fetch_array($r1);

//$data1 = $row[0];

$percent = $row[0] / $row1[0];

$percent = $percent * 100;

echo sprintf("%.1f" ,$percent);

mysqli_close($con);

?>