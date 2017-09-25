<?php
session_start();
    $admin_id = $_SESSION['admin_id'];
    
?>
<html>
    <head>
        <title>admin 관리 페이지</title>
        <meta charset="utf-8" >
    </head>
    <body>
        <h1>관리 페이지</h1><br />
		<a href="logout.php">로그아웃</a>
		
		<a href="./admin_notice/admin_notice_manage.php">공지사항</a>
		<br>
		<br>
        <hr />
	<button type="button" onclick="location.href='./admin_notice/admin_notice_manage.php'">공지사항</button>
	<button type="button" onclick="location.href='./admin_vote/admin_vote.php'">선거관리</button><BR>
	<button type="button" onclick="location.href='./admin_survey/admin_survey_main.php'">설문조사</button>
	<button type="button" onclick="location.href='./admin_suggest/admin_suggest_main.php'">건의사항</button><BR>
		
    </body>
</html> 