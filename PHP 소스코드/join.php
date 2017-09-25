<?php  
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con,"utf8");
  
if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}
$studentid = $_POST['studentid'];
$username = $_POST['username'];  
$password = $_POST['password'];

$hash = password_hash($password, PASSWORD_BCRYPT); # 비밀번호 암호화하기

$check = mysqli_query($con, "select studentID from user_check where studentID='$studentid'");
$row = mysqli_fetch_array($check, MYSQLI_ASSOC);
$check_db = $row["studentID"];

if($check_db == $studentid) {

$result = mysqli_query($con,"insert into user (studentID, name, passwd) values ('$studentid', '$username', '$hash')");  
  if($result){  
    echo 'success';  
  }  
  else{
	
    echo 'failed';  
  }  
}
else{
echo '컴퓨터 공학부에 등록되지 않은 학번입니다.';
}
  
mysqli_close($con);  
?>  