<?php
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con,"utf8");

if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}

if(!isset($_POST["notice_name"])){
	echo "Invalid value input";
	exit();	
}

if(empty($_POST["notice_name"])){
	echo "Invalid value input";
	exit();	
}

$notice_name = $_POST["notice_name"];
$notice_content = $_POST["notice_content"];

$sql = "INSERT INTO admin_notice (notice_name,notice_content) VALUES ('$notice_name','$notice_content')";
$insert = mysqli_query($con, $sql);

if ($insert)
	{
		echo "<script>alert('등록이 완료되었습니다!'); document.location.href='http://localhost/web/admin/admin_notice/admin_notice_manage.php'</script>";
	}
	else {
		echo "<script>alert('등록에 실패하였습니다. DB를 다시 확인해보세요.'); history.back(-1);</script>";
	}
?>