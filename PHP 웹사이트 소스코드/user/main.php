<?php
session_start();
    $user_id = $_SESSION['user_id'];
    $message = $user_id.' 님, 로그인 했습니다.';

?>
<!doctype html>
<html>
   <head>
	<meta charset="utf-8" />
    <title>메인 페이지(사용자)</title>
        <meta charset="utf-8" >
    </head>
    <body>
	<!--<h1>Main Page<?php //echo $login_session; ?></h1>-->
        
<br>
<?php

    echo $message;
?>
<br>
        <hr />
		<br />
		<br>
		<button type="button" onclick="location.href='./notice/notice.php'">공지사항</button>
		<button type="button" onclick="location.href='./vote/vote.php'">투표하기</button><BR>
		<button type="button" onclick="location.href='./survey/survey_main.php'">설문조사</button>
		<button type="button" onclick="location.href='./suggest/suggest_write_form.php'">건의사항</button><BR>
		<h><a href="logout.php">로그아웃</a></h>
		<br>
		<br>
	</body>
</html> 