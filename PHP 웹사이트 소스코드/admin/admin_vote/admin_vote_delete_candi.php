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

if($no == null) 
{
	echo "<script>alert('삭제할 후보 번호를 기입하세요.'); history.back(-1);</script>";
}
else 
{
$query = "delete from admin_vote where candi_number = '$no'";

$delete = mysqli_query($con, $query);

if($delete) {
	echo "<script>alert('후보가 삭제되었습니다.'); document.location.href='http://voteapp.iptime.org/web/admin/admin_vote/admin_vote.php' </script>";
}
else {
	echo "<script>alert('삭제에 실패하였습니다. 입력값 또는 DB를 확인하세요'); history.back(-1) </script>";
}
}
?>