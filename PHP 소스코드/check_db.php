<?php  
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con,"utf8");
  
if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}
$studentid = $_POST['studentid'];
$username = $_POST['username'];  
$email = $_POST['email'];

$check = mysqli_query($con, "select studentID, user_name, mail from user_check 
where studentID='$studentid'");
$row = mysqli_fetch_array($check, MYSQLI_ASSOC);
$check_db1 = $row["studentID"];
$check_db2 = $row["user_name"];
$check_db3 = $row["mail"];

if($check_db1 != $studentid || $check_db2 != $username || $check_db3 != $email)
{
	echo 'failed';
}
else{
	if($check_db1 != null || $check_db2 != null || $check_db3 != null) {
		echo 'success';
	}
}
  
mysqli_close($con);  
?>  