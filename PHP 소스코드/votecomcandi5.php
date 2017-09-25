<?php
 
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con, "utf8"); 

//echo $candi_number;
$q = "select count(*) from user_vote where vote_result = '5번'";

$r = mysqli_query($con, $q)
  or die("Error: ".mysqli_error($con));
  
$row = mysqli_fetch_array($r);

//$data1 = $row[0];

echo $row[0];

mysqli_close($con);

?>