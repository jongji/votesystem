<?php
session_start();
    $user_id = $_SESSION['user_id'];
$con=mysqli_connect("localhost","root","jongji","votingsystem");  
//$con=mysqli_connect("localhost","root","920910","votingsystem");  
 
mysqli_set_charset($con,"utf8");

if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}
/*
if(!(isset(isset($_POST["survey_id"])))){
	echo "Invalid value input";
	exit();	
}
*/

/*
$wdate = time();	//설문 참여 시각?
$ip = getenv("REMOTE_ADDR");	//ip 받아서 중복 방지용으로 쓰는 모양. 모바일 유저는 못 쓸 것 같으니 학번이나 ID로 변경하는게 좋을 것으로 보인다.

//설문 중복참여 방지
ip주소가 같으면 중복 (이 코드에서 ip는 테이블에 따로 있는 모양. 다른 대상(ID 혹은 학번)으로 쓰는 편이 좋을 듯.)

$res1 = mysql_query("SELECT ip FROM $board WHERE ip='$ip'",$con);	//ip 안 쓸거 같으니 잊지말고 수정.

// 중복참여 체크
if(mysql_num_rows($res1)) {
        @mysql_close($con);
echo("<script>alert('이미 설문에 참여하셧습니다.nn참여해주셔서 감사합니다.');history.back();</script>"); exit; }       
*/

$survey_id = $_POST["survey_id"];

$choose1 = $_POST["choose1"];
$choose2 = $_POST["choose2"];
$choose3 = $_POST["choose3"];
$choose4 = $_POST["choose4"];

echo $choose1;

if($survey_id == 1) {
	if($choose1){
	$sql = "INSERT INTO user_survey (r1) VALUES ('$choose1')";	//id가 AI니까 여기서 따로 넣을 필요는 없을지도?
	}
	else if($choose2){
	$sql = "INSERT INTO user_survey (r1) VALUES ('$choose2')";	//id가 AI니까 여기서 따로 넣을 필요는 없을지도?
	}
	else if($choose3){
	$sql = "INSERT INTO user_survey (r1) VALUES ('$choose3')";	//id가 AI니까 여기서 따로 넣을 필요는 없을지도?
	}
	else if($choose4){
	$sql = "INSERT INTO user_survey (r1) VALUES ('$choose4')";	//id가 AI니까 여기서 따로 넣을 필요는 없을지도?
	}
}
else if($survey_id == 2) {
	if($choose1){
	$sql = "INSERT INTO user_survey (r2) VALUES ('$choose1')";	//id가 AI니까 여기서 따로 넣을 필요는 없을지도?
	}
	else if($choose2){
	$sql = "INSERT INTO user_survey (r2) VALUES ('$choose2')";	//id가 AI니까 여기서 따로 넣을 필요는 없을지도?
	}
	else if($choose3){
	$sql = "INSERT INTO user_survey (r2) VALUES ('$choose3')";	//id가 AI니까 여기서 따로 넣을 필요는 없을지도?
	}
	else if($choose4){
	$sql = "INSERT INTO user_survey (r2) VALUES ('$choose4')";	//id가 AI니까 여기서 따로 넣을 필요는 없을지도?
	}
}
else if($survey_id == 3) {
	if($choose1){
	$sql = "INSERT INTO user_survey (r3) VALUES ('$choose1')";	//id가 AI니까 여기서 따로 넣을 필요는 없을지도?
	}
	else if($choose2){
	$sql = "INSERT INTO user_survey (r3) VALUES ('$choose2')";	//id가 AI니까 여기서 따로 넣을 필요는 없을지도?
	}
	else if($choose3){
	$sql = "INSERT INTO user_survey (r3) VALUES ('$choose3')";	//id가 AI니까 여기서 따로 넣을 필요는 없을지도?
	}
	else if($choose4){
	$sql = "INSERT INTO user_survey (r3) VALUES ('$choose4')";	//id가 AI니까 여기서 따로 넣을 필요는 없을지도?
	}
}
else if($survey_id == 4) {
	if($choose1){
	$sql = "INSERT INTO user_survey (r4) VALUES ('$choose1')";	//id가 AI니까 여기서 따로 넣을 필요는 없을지도?
	}
	else if($choose2){
	$sql = "INSERT INTO user_survey (r4) VALUES ('$choose2')";	//id가 AI니까 여기서 따로 넣을 필요는 없을지도?
	}
	else if($choose3){
	$sql = "INSERT INTO user_survey (r4) VALUES ('$choose3')";	//id가 AI니까 여기서 따로 넣을 필요는 없을지도?
	}
	else if($choose4){
	$sql = "INSERT INTO user_survey (r4) VALUES ('$choose4')";	//id가 AI니까 여기서 따로 넣을 필요는 없을지도?
	}
}
else if($survey_id == 5) {
	if($choose1){
	$sql = "INSERT INTO user_survey (r5) VALUES ('$choose1')";	//id가 AI니까 여기서 따로 넣을 필요는 없을지도?
	}
	else if($choose2){
	$sql = "INSERT INTO user_survey (r5) VALUES ('$choose2')";	//id가 AI니까 여기서 따로 넣을 필요는 없을지도?
	}
	else if($choose3){
	$sql = "INSERT INTO user_survey (r5) VALUES ('$choose3')";	//id가 AI니까 여기서 따로 넣을 필요는 없을지도?
	}
	else if($choose4){
	$sql = "INSERT INTO user_survey (r5) VALUES ('$choose4')";	//id가 AI니까 여기서 따로 넣을 필요는 없을지도?
	}
}
else{
	echo "<script>alert('후보 선택에 관련하여 문제가 발생하였습니다.');</script>";
}

//$sql = "INSERT INTO user_survey (id, survey_result1, survey_result2, survey_result3, survey_result4) VALUES ('$survey_id','$choose1','$choose2','$choose3','$choose4')";
$insert = mysqli_query($con, $sql);

if ($insert)
	{
		echo "<script>alert('등록이 완료되었습니다!'); document.location.href='./survey_main.php'</script>";
	}
	else {
		echo "<script>alert('등록에 실패하였습니다.'); history.back(-1);</script>";
	}
?>