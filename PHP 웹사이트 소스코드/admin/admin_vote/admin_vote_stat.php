<?php
session_start();
$admin_id = $_SESSION['admin_id'];
	
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con,"utf8");
  
if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}


?>
<html>
    <head>
        <title>admin 관리 페이지</title>
        <meta charset="utf-8" >
		
    </head>
    <body>
	<article class="boardArticle">
        투표결과 통계 페이지<br />
		<br>
		<br>
		
		
		
        <hr />
		
    </body>
</html>