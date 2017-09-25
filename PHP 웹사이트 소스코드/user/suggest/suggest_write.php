<?php
session_start();
$user_id = $_SESSION['user_id'];
$con=mysqli_connect("localhost","root","jongji","votingsystem");  
//$con=mysqli_connect("localhost","root","920910","votingsystem");  
 
mysqli_set_charset($con,"utf8");

if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}

$user_id = $_POST['user_id'];
$suggest_content = $_POST["suggest_content"];

$sql = "INSERT INTO suggestbox (studentid, suggest_content) VALUES ('$user_id', '$suggest_content')";
$insert = mysqli_query($con, $sql);

if ($insert)
	{
		echo "<script>alert('등록이 완료되었습니다!'); history.back(-3);</script>";
	}
	else {
		echo "<script>alert('등록에 실패하였습니다. DB를 다시 확인해보세요.'); history.back(-1);</script>";
	}
?>