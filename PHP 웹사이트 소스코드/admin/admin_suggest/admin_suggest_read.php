<!DOCTYPE>
<?php
$con=mysqli_connect("localhost","root","jongji","votingsystem"); 
//$con=mysqli_connect("localhost","root","920910","votingsystem");   
mysqli_set_charset($con,"utf8");

if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}

if(!isset($_GET["student_id"])){
	echo "Invalid value input";
	exit();
}

$student_id = $_GET["student_id"];
$sql = "SELECT * FROM suggestbox WHERE student_id = '$student_id'";

$result = mysqli_query($con, $sql) or die(mysqli_error($con));
$row = mysqli_fetch_array($result, MYSQLI_ASSOC);

$value0 = $row["student_id"]; // student_id
$value1 = $row["suggest_content"]; // suggest_content
?>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>건의사항</title>
</head>
<body>
	<div id="read">
		<div id='suggest_view'>
			<p>작성자 : <?php echo $value0; ?></p>
			<p>내용 : </p>
			<p id="content"><?php echo $value1; ?></p>
			<form action="admin_suggest_main.php">
				<input type="submit" value="목록">
			</form>
		</div>
	</div>
</body>
</html>