<?php
session_start();
$user_id = $_SESSION['user_id'];
$con=mysqli_connect("localhost","root","jongji","votingsystem");  
//$con=mysqli_connect("localhost","root","920910","votingsystem");  

mysqli_set_charset($con,"utf8");

if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}
?>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="survey_board.css">
	<title>설문조사</title>
</head>
<body>
		<br>&nbsp;&nbsp;<a href="javascript:history.go(0)">[새로고침]</a>
		&nbsp;&nbsp;<a href="http://voteapp.iptime.org/web/user/main.php">[홈으로]</a><br> <br>
	<div>
		<table class="survey_board">
			<thead>
			<tr id="info">
				<th scope="col" class="survey_no">번호</th>
				<th scope="col" class="survey_title">설문 제목</th>
			</tr>
			</thead>
			<tbody>
				<?php				
				$sql = 'select * from admin_survey';
				$result = mysqli_query($con, $sql);
				while($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
				?>
			<tr>
				<td class="survey_no"><?php echo $row['survey_id'] ?></td>
				<td class="survey_title"><a href="survey_read.php?survey_id=<?=$row["survey_id"]?>"><?= $row["survey_name"] ?></a></td>
									
			<?php }?>
			</tr>
			</tbody>
		</table>
		<br>
	</div>
</body>
</html>