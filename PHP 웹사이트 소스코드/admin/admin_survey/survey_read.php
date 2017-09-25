<!DOCTYPE>
<?php
$con=mysqli_connect("localhost","root","jongji","votingsystem"); 
//$con=mysqli_connect("localhost","root","920910","votingsystem");   
mysqli_set_charset($con,"utf8");

if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}

if(!isset($_GET["survey_id"])){
	echo "Invalid value input";
	exit();
}

$survey_id = $_GET["survey_id"];
$sql = "SELECT * FROM admin_survey WHERE survey_id = '$survey_id'";


$result = mysqli_query($con, $sql) or die(mysqli_error($con));
$row = mysqli_fetch_array($result, MYSQLI_ASSOC);

$value0 = $row["survey_id"]; // survey_id
$value1 = $row["survey_name"]; // survey_name
$value2 = $row["survey_short_content"]; // survey_shortcontent
$value3 = $row["survey_content"]; // survey_content
$choose1 = $row["choose1"];
$choose2 = $row["choose2"];
$choose3 = $row["choose3"];
$choose4 = $row["choose4"];

	$q1 = "select count(*) from user_survey where r$value0 = '$choose1'";
	$q2 = "select count(*) from user_survey where r$value0 = '$choose2'";
	$q3 = "select count(*) from user_survey where r$value0 = '$choose3'";
	$q4 = "select count(*) from user_survey where r$value0 = '$choose4'";




$q1_check = mysqli_query($con, $q1) or die(mysqli_error($con));
$q2_check = mysqli_query($con, $q2) or die(mysqli_error($con));
$q3_check = mysqli_query($con, $q3) or die(mysqli_error($con));
$q4_check = mysqli_query($con, $q4) or die(mysqli_error($con));

$r1 = mysqli_fetch_array($q1_check);
$r2 = mysqli_fetch_array($q2_check);
$r3 = mysqli_fetch_array($q3_check);
$r4 = mysqli_fetch_array($q4_check);

(int)$v1 = (int)$r1[0];
(int)$v2 = (int)$r2[0];
(int)$v3 = (int)$r3[0];
(int)$v4 = (int)$r4[0];
 

?>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title><?php echo $value1; ?></title>
</head>
<body>
	<div id="read">
		<h2 id="No">글 번호 : <?php echo $value0; ?></h2>
		<h3 id="title">제목 : <?php echo $value1; ?></h3>
		<h3 id="scontent">주제 : <?php echo $value2; ?></h3>
		<h3>내용</h3>
		<p id="content"><?php echo $value3	; ?></p>
		<h2>설문 답변내용</h2>
		<h3 id="response">1번 : <?php echo $choose1; ?></h3>	
		(득표 수 : <?php echo $v1; ?>)
		<h3 id="response">2번 : <?php echo $choose2; ?></h3>
		(득표 수 : <?php echo $v2; ?>)
		<h3 id="response">3번 : <?php echo $choose3; ?></h3>
		(득표 수 : <?php echo $v3; ?>)
		<h3 id="response">4번 : <?php echo $choose4; ?></h3>
		(득표 수 : <?php echo $v4; ?>)
		
		<form action="survey_edit.php" method="POST">
			<input type="hidden" name="survey_id" value="<?php= $survey_id ?>">
			<input type="submit" value="수정">
		</form>
		<form action="survey_remove.php" method="POST">
			<input type="hidden" name="survey_id" value="<?php= $survey_id ?>">
			<input type="submit" value="삭제">
		</form>
		<form action="admin_survey_main.php">
			<input type="submit" value="목록">
		</form>
	</div>
</body>
</html>