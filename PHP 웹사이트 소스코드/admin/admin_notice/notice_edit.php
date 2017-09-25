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

$notice_id = $_GET["notice_id"];
$sql = "SELECT * FROM admin_notice WHERE notice_id = '$notice_id'";

$result = mysqli_query($con, $sql) or die(mysqli_error($con));
$row = mysqli_fetch_array($result, MYSQLI_ASSOC);

$ID = $row["notice_id"]; // notice_id
$NAME = $row["notice_name"]; // notice_name
$CONTENT = $row["notice_content"]; // notice_content
?>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>글 수정</title>
</head>
<body>
	<div id="edit">
		<form action="notice_edit_update.php" method="post">
			<p>공지 번호 <input type="text" name="notice_id" size="40" value="<?= $ID; ?>"></p>
			<p>공지 제목 <input type="text" name="notice_name" size="40" value="<?= $NAME; ?>"></p>
			<p>공지 사항</p>
			<textarea cols="50" rows="20" name="notice_content"><?= $CONTENT; ?></textarea> <br>
			<input type="submit" value="수정하기">
		</form>	
		<form action="admin_notice_manage.php">
			<input type="submit" value="취소">
		</form>
	</div>
</body>
</html>