<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<!--<link rel="stylesheet" type="text/css" href="write.css">-->
	<title>글 쓰기</title>
</head>
<body>
	<div id="write">
		<form method='post' action='admin_notice_write.php'>
			<p>제목 <input type="text" name="notice_name" size="40"></p>
			<p>내용</p>
			<textarea cols="50" rows="20" name="notice_content"></textarea> <br>
			<input type="submit" value="보내기">
		</form>
	</div>
</body>
</html>