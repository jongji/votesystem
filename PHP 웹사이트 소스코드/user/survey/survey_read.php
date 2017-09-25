<!DOCTYPE>
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

if(!isset($_GET["survey_id"])){
	echo "Invalid value input";
	exit();
}

$survey_id = $_GET["survey_id"];
$sql = "SELECT * FROM admin_survey WHERE survey_id = '$survey_id'";

$result = mysqli_query($con, $sql) or die(mysqli_error($con));
$row = mysqli_fetch_array($result, MYSQLI_ASSOC);

$value0 = $row["survey_id"]; // survey_id
$value1 = $row["survey_name"]; // survey_name
$value2 = $row["survey_short_content"]; // survey_shortcontent
$value3 = $row["survey_content"]; // survey_content
$choose1 = $row["choose1"];
$choose2 = $row["choose2"];
$choose3 = $row["choose3"];
$choose4 = $row["choose4"];
?>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>설문조사</title>
</head>
<body>
	<div id="read">
		<div id='survey_view'>
		<h3 id='title'>제목 : <?php echo $value1; ?></h3>
			<p>글 번호 : <?php echo $value0; ?></p>
			<p>주제 : <?php echo $value2; ?></p>
			<p>내용 : </p>
			<p id="content"><?php echo $row['survey_content'] ?></p>
			<p>답변</p>
			<form action="survey_response.php" method="POST">
			<input type="hidden" name="survey_id" value="<?php echo $value0;?>" ></input>
			<input type="radio" name="choose1" value="<?php echo $choose1; ?>" >1. <?php echo $choose1; ?></input>
			<input type="radio" name="choose2" value=<?php echo $choose2; ?>>2. <?php echo $choose2; ?></input>
			<input type="radio" name="choose3" value=<?php echo $choose3; ?>>3. <?php echo $choose3; ?></input>
			<input type="radio" name="choose4" value=<?php echo $choose4; ?>>4. <?php echo $choose4; ?></input> <br>
				<input type="submit" value="답변 제출">
			</form>
			<form action="survey_main.php">
				<input type="submit" value="목록">
			</form>
		</div>
	</div>
</body>
</html>