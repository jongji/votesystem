<?php
$con=mysqli_connect("localhost","root","jongji","votingsystem");  
 
mysqli_set_charset($con,"utf8");

if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}

extract($_POST);

$check = mysqli_query($con, "SELECT survey_id FROM admin_survey");
$row = mysqli_fetch_array($check, MYSQLI_ASSOC);
	
if($survey_id == $row['survey_id'])
{
	echo "<script>alert('중복된 설문번호 입니다.'); document.location.href='javascript:history.go(-1)' </script>";
}
else {
	$sql = "INSERT INTO admin_survey 
(survey_id, survey_name, survey_short_content, survey_content, choose1, choose2, choose3, choose4)
 VALUES 
 ('$survey_id','$survey_name','$survey_short_content','$survey_content','$choose1','$choose2','$choose3','$choose4')";
$insert = mysqli_query($con, $sql);
if ($insert){
	echo "<script>alert('설문조사 등록이 완료되었습니다.'); document.location.href='http://voteapp.iptime.org/web/admin/admin_survey/admin_survey_main.php'</script>";
}
else {
	echo "<script>alert('설문조사 등록에 실패하였습니다. DB를 다시 확인해보세요.'); history.back(-1);</script>";
}
}
?>