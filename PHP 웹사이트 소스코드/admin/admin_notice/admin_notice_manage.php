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
	<link rel="stylesheet" type="text/css" href="notice_board.css">
	<title>공지사항</title>
</head>
<body>
투표 관리 페이지<br />
		<br>
		<br>&nbsp;&nbsp;<a href="javascript:history.go(0)">[새로고침]</a>
		&nbsp;&nbsp;<a href="http://voteapp.iptime.org/web/admin/admin_main.php">[뒤로가기]</a><br>
	<div>
		<table class="notice_board">
			<thead>
			<tr id="info">
				<th scope="col" class="notice_no">공지 번호</th>
				<th scope="col" class="notice_title">공지 제목</th>
				<th scope="col" class="notice_content">내용</th>
			</tr>
			</thead>
			<tbody>
				<?php				
				$sql = 'select * from admin_notice';
				$result = mysqli_query($con, $sql);
				while($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
				?>
			<tr>
				<td class="notice_no"><?php echo $row['notice_id'] ?></td>
				<td class="notice_title"><?php echo $row['notice_name'] ?></td>
				<td class="notice_content"><a href="notice_edit.php?notice_id=<?=$row["notice_id"]?>"><?= $row["notice_content"] ?></a></td>
			<?php }?>
			</tr>
			</tbody>
		</table>
		<br>
		<form action="notice_write_form.php">
			<input type="submit" value="공지 작성">
		</form>
	</div>
</body>
</html>