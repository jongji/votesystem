<?php
$con=mysqli_connect("localhost","root","jongji","votingsystem"); 
//$con=mysqli_connect("localhost","root","920910","votingsystem");
mysqli_set_charset($con,"utf8");

if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}

if(!(isset($_POST["survey_id"]) && isset($_POST["survey_name"]) && isset($_POST["survey_short_content"]) && isset($_POST["survey_content"]))){
	echo "Invalid value input";
	exit();	
}

if((empty($_POST["survey_id"]) || empty($_POST["survey_name"]) || empty($_POST["survey_short_content"]) || empty($_POST["survey_content"]))){
	echo "Invalid value input";
	exit();	
}

$survey_id = $_POST["survey_id"];
$survey_name = $_POST["survey_name"];
$survey_short_content = $_POST["survey_short_content"];
$survey_content = $_POST["survey_content"];
$choose1 = $_POST["choose1"];
$choose2 = $_POST["choose2"];
$choose3 = $_POST["choose3"];
$choose4 = $_POST["choose4"];

$sql = "UPDATE admin_survey SET survey_name = '$survey_name', survey_short_content = '$survey_short_content', survey_content = '$survey_content', choose1 = '$choose1', choose2 = '$choose2', choose3 = '$choose3', choose4 = '$choose4' WHERE survey_id = '$survey_id'";
$update = mysqli_query($con, $sql);

if ($update)
	{
		echo "<script>alert('수정 완료!'); document.location.href='http://localhost/web/admin/admin_survey/admin_survey_main.php'</script>";
	}
	else {
		echo "<script>alert('수정 실패: DB를 확인해보세요.'); history.back(-1);</script>";
	}
?>