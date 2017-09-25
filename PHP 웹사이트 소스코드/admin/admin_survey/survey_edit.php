<?php
session_start();
    $admin_id = $_SESSION['admin_id'];
$con=mysqli_connect("localhost","root","jongji","votingsystem");  
//$con=mysqli_connect("localhost","root","920910","votingsystem");  

mysqli_set_charset($con,"utf8");

if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}

$survey_id = $_POST["survey_id"];
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
?>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>수정</title>
</head>
<body>
	<div id="edit">
		<form action="survey_edit_update.php" method="post">
			<p>설문 번호 <input type="text" name="survey_id" size="40" value="<?= $row["survey_id"] ?>">※수정할 설문의 번호를 입력하세요</p>
			<p>설문 제목 <input type="text" name="survey_name" size="40" value="<?= $row["survey_name"] ?>"></p>
			<p>주제 <input type="text" name="survey_short_content" size="40"></p>
			<p>내용</p>
			<textarea cols="50" rows="20" name="survey_content"><?= $row["survey_content"] ?></textarea> <br>
			<p>선택지</p>
			<p>1. <input type="text" name="choose1" size="50" value = ""></p>
			<p>2. <input type="text" name="choose2" size="50" value = ""></p>
			<p>3. <input type="text" name="choose3" size="50" value = ""></p>
			<p>4. <input type="text" name="choose4" size="50" value = ""></p>
			<input type="submit" value="수정하기">
		</form>	
		<form action="admin_survey_main.php">
			<input type="submit" value="취소">
		</form>
	</div>
</body>
</html>