<?php
session_start();
$user_id = $_SESSION['user_id'];
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
//$con=mysqli_connect("localhost","root","920910","votingsystem");  
 
mysqli_set_charset($con,"utf8");

if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}

$hash = password_hash($user_id, PASSWORD_BCRYPT);
/*
if(!(isset($_POST["vote_kind"]))){
	echo "Invalid value input";
	exit();	
}
*/

/*
if(!(isset($_POST["CNo"]) && isset($_POST["vote_kind"]))){	//후보 번호는 좀 이상하고... 뭐로 받지?
	echo "Invalid value input";
	exit();	
}

if((empty($_POST["CNo"]) || empty($_POST["vote_kind"]))){
	echo "Invalid value input";
	exit();	
}
*/

$Vresult = $_POST["candi_no"];

$sql = " select studentID from user where studentID='$user_id'";
$sql2 = "select candi_get from admin_vote where candi_number='$Vresult'";

$q = mysqli_query($con, $sql);
$q2 = mysqli_query($con, $sql2);

$row = mysqli_fetch_array($q, MYSQLI_ASSOC);
$row2 = mysqli_fetch_array($q2, MYSQLI_ASSOC);

$get = $row2["candi_get"];
$aa = 1;
$bb = (int)$get + $aa;

$f = mysqli_query($con, "select vote from user where studentID='$user_id'");

$row2 = mysqli_fetch_array($f, MYSQLI_ASSOC);

$hash3 = $row2["vote"];

switch($hash3)
{

case 'yes' :
	echo "<script>alert('이미 투표하셨습니다.'); document.location.href='http://voteapp.iptime.org/web/user/vote/vote.php'</script>";
	break;
	
case 'not' :

	mysqli_query($con, "update user set vote='yes' where studentID=$user_id");
	$sql = "INSERT INTO user_vote (studentID, vote_result) VALUES ('$hash', '$Vresult')";
	mysqli_query($con, "update admin_vote set candi_get = '$bb' where candi_number='$Vresult'");

	$insert = mysqli_query($con, $sql);
	if($insert) 
		echo "<script>alert('투표에 성공하셨습니다.'); document.location.href='http://voteapp.iptime.org/web/user/vote/vote.php'</script>";
	
		break;
		
	
}
?>