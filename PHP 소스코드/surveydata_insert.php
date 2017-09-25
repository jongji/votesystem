<?php
 
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con, "utf8"); 

$survey_number = $_POST['survey_number'];
$survey_data = $_POST['survey_data'];

//echo $candi_number;
if($survey_number == '1') {
	$q = "insert into user_survey (r1) values ('$survey_data')";
	$r = mysqli_query($con, $q);
}
else if($survey_number == '2') {
	$q = "insert into user_survey (r2) values ('$survey_data')";
	$r = mysqli_query($con, $q);
}
else if($survey_number == '3') {
	$q = "insert into user_survey (r3) values ('$survey_data')";
	$r = mysqli_query($con, $q);
}
else if($survey_number == '4') {
	$q = "insert into user_survey (r4) values ('$survey_data')";
	$r = mysqli_query($con, $q);
}
else if($survey_number == '5') {
	$q = "insert into user_survey (r5) values ('$survey_data')";
	$r = mysqli_query($con, $q);
}


//$data1 = $row[0];

if($r){
	echo 'success'; 
} else {
	echo 'fail';
}

 
mysqli_close($con);

?>