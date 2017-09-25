<?php
session_start();
    $admin_id = $_SESSION['admin_id'];
	
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con,"utf8");
  
if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}


extract($_POST);

$check_time = mysqli_query($con, "SELECT number from admin_vote_time where number=1");
$row = mysqli_fetch_array($check_time, MYSQLI_ASSOC);
$value = $row['number'];

if($value == 1) {
	$query_update = "UPDATE admin_vote_time SET start_date = '$start_date', end_date = '$end_date' WHERE number = 1";
	$update = mysqli_query($con, $query_update);
	if ($update)
	{
		echo "<script>alert('투표 시간 등록이 완료되었습니다.'); history.back(-1);</script>";
	}
	else {
		echo "<script>alert('시간 등록에 실패하였습니다. DB를 다시 확인해보세요.'); history.back(-1);</script>";
	}
}

else {
$query = "INSERT INTO admin_vote_time (start_date, end_date) VALUES ('$start_date', '$end_date')";

    $insert = mysqli_query($con, $query);
	
	if ($insert)
	{
		echo "<script>alert('투표 시간 등록이 완료되었습니다.'); history.back(-1);</script>";
	}
	else {
		echo "<script>alert('시간 등록에 실패하였습니다. DB를 다시 확인해보세요.'); history.back(-1);</script>";
	}
}
?>