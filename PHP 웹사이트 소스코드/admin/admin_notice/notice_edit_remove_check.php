<?php
if(!(isset($_GET["notice_id"]) && isset($_GET["type"]))){
	echo "Invalid value input";
	exit();
}

$notice_id = $_GET["notice_id"];
$type = $_GET["type"];
?>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>삭제</title>
</head>
<body>
	<?php
	if($type === "remove"){
	?>
	<form action="notice_remove.php" method="POST">
		<p>비밀번호 입력</p>
		<input type="password" name="pw">
		<input type="hidden" name="notice_id" value="<?= $notice_id ?>">
		<input type="submit">
	</form>
	<?php
	} else if($type === "edit"){
	?>
	<form action="notice_edit.php" method="POST">
		<p>비밀번호 입력</p>
		<input type="password" name="pw">
		<input type="hidden" name="notice_id" value="<?= $notice_id ?>">
		<input type="submit">
	</form>
	<?php }?>
</body>
</html>