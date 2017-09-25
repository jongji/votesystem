<?php  
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con,"utf8");
  
if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}
$username = $_POST['username'];  

$q = mysqli_query($con, " select studentID from user where studentID=$username ");
$f = mysqli_query($con, "select vote from user where studentID=$username");

if($q = $username && $f = yes)
{
	echo 'failure';
}
else if($q = $username && $f = not);
{
	echo 'success';  
}
  
mysqli_close($con);  
?> 