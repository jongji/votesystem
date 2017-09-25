<!DOCTYPE>
<?php
//include 'config.php';
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con,"utf8");

if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}

if(!isset($_GET["notice_id"])){
	echo "Invalid value input";
	exit();
}

$notice_id = $_GET["notice_id"];

$sql = "SELECT notice_id, notice_name, notice_content FROM admin_notice WHERE notice_id = " . $notice_id;

// 데이터 가져오기
$result = mysql_query($sql, $bd) or die("SQL 에러");
$row = mysql_fetch_array($result);   

?>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<!-- <link rel="stylesheet" type="text/css" href="read.css"> -->
	<title><?= $row["notice_name"] ?></title>
</head>
<body>
	<div id="read">
		<p>글 번호 : <?= $row["notice_id"] ?></p>
		<p>제목 : <?= $row["notice_name"] ?></p>
		<p>내용</p>
		<p id="content"><?= $row["notice_content"] ?></p>
		<form action="notice_edit_remove_check.php" methoid="GET">
			<input type="hidden" name="notice_id" value="<?= $notice_id ?>">
			<input type="hidden" name="type" value="edit">
			<input type="submit" value="수정">
		</form>
		<form action="notice_edit_remove_check.php" methoid="GET">
			<input type="hidden" name="notice_id" value="<?= $notice_id ?>">
			<input type="hidden" name="type" value="remove">
			<input type="submit" value="삭제">
		</form>
		<form action="admin_notice_manage.php">
			<input type="submit" value="목록가기">
		</form>
	</div>
</body>
</html>