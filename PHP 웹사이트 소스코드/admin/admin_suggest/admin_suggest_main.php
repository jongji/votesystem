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
?>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="suggest_board.css">
	<title>건의사항</title>
</head>
<body>
건의사항 관리 페이지<br />
		<br>
		<br>&nbsp;&nbsp;<a href="javascript:history.go(0)">[새로고침]</a>
		&nbsp;&nbsp;<a href="javascript:history.go(-1)">[뒤로가기]</a><br>
		<hr />
	<div>
		<table class="suggest_board">
			<thead>
			<tr id="info">
				<th scope="col" class="student_id">작성자</th>
				<th scope="col" class="suggest_content">건의사항</th>
			</tr>
			</thead>
			<tbody>
				<?php				
				$sql = 'select * from suggestbox';
				$result = mysqli_query($con, $sql);
				while($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
				?>
			<tr>
				<td class="student_id"><?php echo $row['studentid'] ?></td>
				<td class="suggest_content"><?= $row["suggest_content"] ?></a></td>
									
			<?php }?>
			</tr>
			</tbody>
		</table>
		<br>
	</div>
</body>
</html>