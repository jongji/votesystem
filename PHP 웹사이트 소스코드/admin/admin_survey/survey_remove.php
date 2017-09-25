<?php
//include 'config.php';
//$con=mysqli_connect("votingapp.iptime.org","root","jongji","votingsystem");   
$con=mysqli_connect("localhost","root","920910","votingsystem");
mysqli_set_charset($con,"utf8");

if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$survey_id = $_POST["survey_id"];
$row = mysql_fetch_array($result);

$sql = "DELETE FROM admin_survey WHERE survey_id = " . $survey_id;
$result = mysql_query($con, $sql) or die("SQL 에러");

if($result == true){
	header('Location: ./admin_survey_main.php');
}
else{
	echo "Failed to delete";
}
?>