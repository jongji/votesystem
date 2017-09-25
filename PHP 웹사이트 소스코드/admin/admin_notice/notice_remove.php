<?php
//include 'config.php';
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con,"utf8");

if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}

/*
if(!(isset($_POST["pw"]) && isset($_POST["notice_id"]))){
	echo "Invalid password";
	exit();
}
*/
//$pw = $_POST["pw"];
$notice_id = $_POST["notice_id"];

//$sql = "SELECT pw FROM admin_notice WHERE notice_id = " . $notice_id;
//$result = mysql_query($sql, $bd) or die("SQL 에러");

$row = mysql_fetch_array($result);   

// 입력한 비밀번호와 데이터베이스에 있는 비밀번호 확인
/*
if(!($pw === $row["pw"])){
	echo "Incorrect password";
	exit();
}
*/
$sql = "DELETE FROM admin_notice WHERE notice_id = " . $notice_id . /*" AND pw = '" . $pw . "'"*/;
$result = mysql_query($sql, $bd) or die("SQL 에러");

if($result == true){
	header('Location: ./admin_notice_manage.php');
}
else{
	echo "Failed to delete";
}
?>