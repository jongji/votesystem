<?php
 
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con, "utf8"); 

$candi_number = '1번';

//echo $candi_number;
$q = "select vote_candi1, vote_candi2 from admin_vote where candi_number='$candi_number'";
$q2 = "select candi1_info from admin_vote where candi_number='$candi_number'";
$q3 = "select candi_promise from admin_vote where candi_number='$candi_number'";

$r = mysqli_query($con, $q)
  or die("Error: ".mysqli_error($con));
  
$r2 = mysqli_query($con, $q2)
  or die("Error: ".mysqli_error($con));
  
$r3 = mysqli_query($con, $q3)
  or die("Error: ".mysqli_error($con)); 
$row = mysqli_fetch_array($r);
$row2 = mysqli_fetch_array($r2);
$row3 = mysqli_fetch_array($r3);


//$data1 = $row[0];

echo $row['vote_candi1']; 
//echo $row2[0];
//echo $row3[0];

 
mysqli_close($con);

?>