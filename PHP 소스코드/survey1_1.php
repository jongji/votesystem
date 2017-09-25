<?php
 
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con, "utf8"); 

$survey_number = $_POST['survey_number'];

//echo $candi_number;
$q = "select survey_short_content from admin_survey where survey_id='$survey_number'";
//$q2 = "select candi1_info from admin_survey where survey_id='$survey_number'";
//$q3 = "select candi_promise from admin_survey where survey_id='$survey_number'";

$r = mysqli_query($con, $q)
  or die("Error: ".mysqli_error($con));
  
//$r2 = mysqli_query($con, $q2)
//  or die("Error: ".mysqli_error($con));
  
//$r3 = mysqli_query($con, $q3)
//  or die("Error: ".mysqli_error($con)); 
$row = mysqli_fetch_array($r);
//$row2 = mysqli_fetch_array($r2);
//$row3 = mysqli_fetch_array($r3);


//$data1 = $row[0];

echo $row['survey_short_content']; 
//echo $row2[0];
//echo $row3[0];

 
mysqli_close($con);

?>