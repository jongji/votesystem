<?php
 
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con, "utf8"); 

$survey_number = $_POST['survey_number'];

//echo $candi_number;
$q = "select choose1 from admin_survey where survey_id='$survey_number'";

$r = mysqli_query($con, $q)
  or die("Error: ".mysqli_error($con));


$row = mysqli_fetch_array($r);


//$data1 = $row[0];

echo $row['choose1']; 

 
mysqli_close($con);

?>