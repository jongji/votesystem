<!DOCTYPE>
<?php 
session_start();
	$user_id = $_SESSION['user_id'];
?>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>건의사항 작성</title>
	<h1> 건의사항 </h1>
	<br>&nbsp;&nbsp;<a href="javascript:history.go(0)">[새로고침]</a>
		&nbsp;&nbsp;<a href="http://voteapp.iptime.org/web/user/main.php">[홈으로]</a><br> <br>
</head>
<body>
	<div id="write">
		<form method='post' action='suggest_write.php'>
			<p>학번 : <?php echo $user_id; ?></p>
			<input type="hidden" name="user_id" value="<?php echo $user_id;?>" ></input>
			<p>건의사항</p>
			<textarea cols="50" rows="20" name="suggest_content"></textarea> <br>
			<input type="submit" value="작성 완료">
		</form>
	</div>
</body>
</html>