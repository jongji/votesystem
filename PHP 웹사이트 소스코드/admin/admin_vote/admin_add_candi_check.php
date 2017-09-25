<?php
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con,"utf8");
  
if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}

extract($_POST);

if($vote_name == null)
{
	echo "<script>alert('투표 분류를 입력해주세요.'); history.back(-1) </script>";
}
else if($candi_no == null)
{
	echo "<script>alert('기호를 입력해주세요.'); history.back(-1) </script>";
}
else if($candi1 == null)
{
	echo "<script>alert('정후보를 입력해주세요.'); history.back(-1) </script>";
}
else if($candi1_major == null)
{
	echo "<script>alert('정후보 전공을 입력해주세요.'); history.back(-1) </script>";
}
else if($candi1_info == null)
{
	echo "<script>alert('정후보 정보를 입력해주세요.'); history.back(-1) </script>";
}
else if($candi2 == null)
{
	echo "<script>alert('부후보를 입력해주세요.'); history.back(-1) </script>";
}
else if($candi2_major == null)
{
	echo "<script>alert('부후보 전공을 입력해주세요.'); history.back(-1) </script>";
}
else if($candi2_info == null)
{
	echo "<script>alert('부후보 정보를 입력해주세요.'); history.back(-1) </script>";
}
else if($promise == null)
{
	echo "<script>alert('핵심공약을 입력해주세요.'); history.back(-1) </script>";
}

else {
	$check = mysqli_query($con, "SELECT candi_number FROM admin_vote");
	
	while($row = mysqli_fetch_array($check, MYSQLI_ASSOC)) 
	{
		if($candi_no == $row['candi_number'])
		{
			echo "<script>alert('중복된 후보번호 입니다.'); document.location.href='http://voteapp.iptime.org/web/admin/admin_vote/admin_add_candi.php' </script>";
		}
		else {
		}
	}
	
	$query = 
	"INSERT INTO admin_vote (vote_name, candi_number, vote_candi1, candi1_major, candi1_info, vote_candi2, candi2_major, candi2_info, candi_promise)
	VALUES ('$vote_name', '$candi_no', '$candi1', '$candi1_major', '$candi1_info', '$candi2', '$candi2_major', '$candi2_info', '$promise')";
	$insert = mysqli_query($con, $query);
	
	if ($insert)
	{
		echo "<script>alert('후보 등록이 완료되었습니다!'); document.location.href='http://voteapp.iptime.org/web/admin/admin_main.php'</script>";
	}
	else {
		echo "<script>alert('후보등록에 실패하였습니다. DB를 다시 확인해보세요.'); history.back(-1);</script>";
	}
}
?>