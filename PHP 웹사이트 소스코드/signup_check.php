<?php
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con,"utf8");
  
if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}
extract($_POST);

if($user_pass != $user_pass2) {
	echo "<script>alert('비밀번호를 다시 확인해주세요!'); history.back(-1);</script>";
}
else {
	

	$hash = password_hash($user_pass, PASSWORD_BCRYPT); # 비밀번호 암호화하기

	$check = mysqli_query($con, "select studentID from user_check where studentID='$user_id'");
	$row = mysqli_fetch_array($check, MYSQLI_ASSOC);
	$check_db = $row["studentID"];

	if($check_db == $user_id) {
		$result = mysqli_query($con, "INSERT INTO user ( name, studentID, passwd ) VALUES ( '$name', '$user_id', '$hash')");
		if($result) {
			echo "<script>alert('회원가입이 완료되었습니다!'); document.location.href='http://voteapp.iptime.org/web_new2/index.php'</script>";
		}
		else {
			echo "<script>alert('회원가입에 실패하였습니다!'); history.back(-1);</script>";
		}
	}
	else {
		echo "<script>alert('컴퓨터 공학부에 등록되지 않은 학번입니다.'); history.back(-1);</script>";
	}
}


mysqli_close($con);  
?> 