<?php
 
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con, "utf8"); 

$studentid = $_POST['user_name'];

$realtime = date("Y-m-d H:i:s");



$result = mysqli_query($con, "select vote from user where studentID='$studentid'");
$time_result = mysqli_query($con, "select end_date from admin_vote_time where number=1");
$start = mysqli_query($con, "select start_date from admin_vote_time where number=1");

$row = mysqli_fetch_array($result, MYSQLI_ASSOC);
$time_row = mysqli_fetch_array($time_result, MYSQLI_ASSOC);
$start_row = mysqli_fetch_array($start, MYSQLI_ASSOC);

$check_vote = $row["vote"];
$check_time = $time_row["end_date"];
$start_time = $start_row["start_date"];

if ($check_vote == "not") {
	if(time() < strtotime($check_time) && time() > strtotime($start_time))
	{
		echo 'vote_no';
	}
	else {
	echo 'overtime';
	}
} else 
{
	echo 'vote_yes';
}

	
 


 
mysqli_close($con);

?>