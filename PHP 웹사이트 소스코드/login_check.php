<?php

extract($_POST);
echo $_POST['user_id'];

session_start();

$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con,"utf8");
  
if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}


if($user_id == null)
{
	echo "<script>alert('학번을 입력해주세요.'); history.back(-1) </script>";
}
else if($user_pass == null)
{
	echo "<script>alert('비밀번호를 입력해주세요.'); history.back(-1) </script>";
}
else {
	if($user_id == 'root')
{
	$admin_result = mysqli_query($con, "SELECT passwd FROM admin WHERE adminID='$user_id'");
	$admin_row = mysqli_fetch_array($admin_result, MYSQLI_ASSOC);
	$admin_pass = $admin_row["passwd"];
	
	if($user_pass == $admin_pass)
	{
		$_SESSION['admin_id'] = $user_id;
		echo "<script>alert('관리자로 로그인합니다.'); document.location.href='http://voteapp.iptime.org/web/admin/admin_main.php'</script>";
	}
	
	else
	{
		echo "<script>alert('비밀번호를 다시 확인하세요.'); history.back(-1) </script>";	
	}
}

else 
{
	$q = "SELECT studentID, passwd FROM user WHERE studentID='$user_id'";
	$result = mysqli_query($con, $q);
	$row = mysqli_fetch_array($result, MYSQLI_ASSOC);

	$hash = $row["passwd"];

	if (password_verify ($user_pass, $hash)) {
		$_SESSION['user_id'] = $user_id;

		echo "<script>alert('로그인에 성공하였습니다!'); document.location.href='http://voteapp.iptime.org/web/user/main.php'</script>";
		exit();
	}
	else {
		echo "<script>alert('비밀번호를 다시 확인하세요.'); history.back(-1) </script>";	

	}
}


}


?>