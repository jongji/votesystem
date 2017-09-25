<?php  
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con,"utf8");
  
if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}
$username = $_POST['user_name']; 
$vote_data = $_POST['data'];

$sql = " select studentID from user where studentID='$username'";
$sql2 = "select candi_get from admin_vote where candi_number='$vote_data'";

$q = mysqli_query($con, $sql);
$q2 = mysqli_query($con, $sql2);

$row = mysqli_fetch_array($q, MYSQLI_ASSOC);
$row2 = mysqli_fetch_array($q2, MYSQLI_ASSOC);

$get = $row2["candi_get"];
$aa = 1;
$bb = (int)$get + $aa;

mysqli_query($con, "update admin_vote set candi_get = '$bb' where candi_number='$vote_data'");

$hash2 = $row["studentID"];

$hash = password_hash($username, PASSWORD_BCRYPT); # 학번 암호화하기

$f = mysqli_query($con, "select vote from user where studentID='$username'");

$row2 = mysqli_fetch_array($f, MYSQLI_ASSOC);

$hash3 = $row2["vote"];

switch($hash3)
{

case 'yes' :
	echo '이미 투표하셨습니다.';
	break;
	
case 'not' :

	mysqli_query($con, "update user set vote='yes' where studentID=$username");
	$result = mysqli_query($con, "insert into user_vote (studentid ,vote_result) values ('$hash', '$vote_data')"); 
	if($result) 
		echo '투표를 완료하였습니다.';
	
		break;
		
	
}

mysqli_close($con);  

?> 