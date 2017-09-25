<?php
//$con=mysqli_connect("votingapp.iptime.org","root","jongji","votingsystem"); 
$con=mysqli_connect("localhost","root","920910","votingsystem");   
mysqli_set_charset($con,"utf8");

if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}

if(!isset($_GET["survey_id"])){
	echo "Invalid value input";
	exit();
}

$wdate = time();	//설문 참여 시각?
$ip = getenv("REMOTE_ADDR");	//ip 받아서 중복 방지용으로 쓰는 모양. 모바일 유저는 못 쓸 것 같으니 학번이나 ID로 변경하는게 좋을 것으로 보인다.

//설문 중복참여 방지
//ip주소가 같으면 중복 (이 코드에서 ip는 테이블에 따로 있는 모양. 다른 대상(ID 혹은 학번)으로 쓰는 편이 좋을 듯.)

/*
$res1 = mysql_query("SELECT ip FROM $board WHERE ip='$ip'",$con);	//ip 안 쓸거 같으니 잊지말고 수정.

// 중복참여 체크
if(mysql_num_rows($res1)) {
        @mysql_close($con);
echo("<script>alert('이미 설문에 참여하셧습니다.nn참여해주셔서 감사합니다.');history.back();</script>"); exit; }       
*/

// 설문 참여 성공 부분. test.php에서 넘어온 값 테이블에 저장.
$res2 = mysql_query("INSERT INTO $board (id,answer,wdate,ip) VALUES ('','$answer','$wdate','$ip')",$con);	//넣는 값, 테이블 수정 할 것.

if(!$res2) dbError();

@mysql_close($con);

echo("<script>alert('참여해주셔서 감사합니다.');self.location.href='survey_main.php'</script>"); 
?>