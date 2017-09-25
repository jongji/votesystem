<?php
$con=mysqli_connect("localhost","root","jongji","votingsystem"); 
//$con=mysqli_connect("localhost","root","920910","votingsystem"); 
 
mysqli_set_charset($con,"utf8");

if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}

if(!(isset($_POST["notice_id"]) && isset($_POST["notice_name"]) && isset($_POST["notice_content"]))){
	echo "Invalid value input";
	exit();	
}

if((empty($_POST["notice_id"]) || empty($_POST["notice_name"]) || empty($_POST["notice_content"]))){
	echo "Invalid value input";
	exit();	
}

$notice_id = $_POST["notice_id"];
$notice_name = $_POST["notice_name"];
$notice_content = $_POST["notice_content"];

$sql = "UPDATE admin_notice SET notice_name = '$notice_name', notice_content = '$notice_content' WHERE notice_id = '$notice_id'";
$update = mysqli_query($con, $sql);

if ($update)
	{
		echo "<script>alert('수정 완료!'); document.location.href='http://voteapp.iptime.org/web/admin/admin_notice/admin_notice_manage.php'</script>";
	}
	else {
		echo "<script>alert('수정 실패: DB를 확인해보세요.'); history.back(-1);</script>";
	}
?>