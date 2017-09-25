<?php
 
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con, "utf8"); 
 
$studentid = $_POST['studentid'];
$password = $_POST['password'];

$q = "select studentID, passwd from user where studentID='$studentid'";
$result = mysqli_query($con, $q);
$row = mysqli_fetch_array($result, MYSQLI_ASSOC);

$hash = $row["passwd"];

if (password_verify ($password, $hash)) {
	echo "success";
} 
else {
	echo "wrong password";
}

 
mysqli_close($con);

?>