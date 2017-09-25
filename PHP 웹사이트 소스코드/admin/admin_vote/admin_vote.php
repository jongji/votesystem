<?php
session_start();
$admin_id = $_SESSION['admin_id'];
	
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con,"utf8");
  
if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}

extract($_POST);

$check_time = mysqli_query($con, "SELECT end_date from admin_vote_time where number=1");
$row = mysqli_fetch_array($check_time, MYSQLI_ASSOC);
$value = $row['end_date'];


$q0 = "select count(*) from user_vote where vote_result = '기권'";
$q1 = "select count(*) from user_vote where vote_result = '1번'";
$q2 = "select count(*) from user_vote where vote_result = '2번'";
$q3 = "select count(*) from user_vote where vote_result = '3번'";
$q4 = "select count(*) from user_vote where vote_result = '4번'";
$q5 = "select count(*) from user_vote where vote_result = '5번'";
$q6 = "select count(*) from admin_vote";

$q7 = "select count(*) from user_check";

$q0_check = mysqli_query($con, $q0);
$q1_check = mysqli_query($con, $q1);
$q2_check = mysqli_query($con, $q2);
$q3_check = mysqli_query($con, $q3);
$q4_check = mysqli_query($con, $q4);
$q5_check = mysqli_query($con, $q5);
$q6_check = mysqli_query($con, $q6);

$q7_check = mysqli_query($con, $q7);

$row0 = mysqli_fetch_array($q0_check);
$row1 = mysqli_fetch_array($q1_check);
$row2 = mysqli_fetch_array($q2_check);
$row3 = mysqli_fetch_array($q3_check);
$row4 = mysqli_fetch_array($q4_check);
$row5 = mysqli_fetch_array($q5_check);
$row6 = mysqli_fetch_array($q6_check);

$row7 = mysqli_fetch_array($q7_check);


(int)$value0 = (int)$row0[0];
(int)$value1 = (int)$row1[0];
(int)$value2 = (int)$row2[0];
(int)$value3 = (int)$row3[0];
(int)$value4 = (int)$row4[0];
(int)$value5 = (int)$row5[0];
(int)$value6 = (int)$row6[0];

(int) $value_a = (int)$row7[0];

(int)$value_all = (int)$value0 + (int)$value1 + (int)$value2 + (int)$value3 + (int)$value4 + (int)$value5;

(double)$vote_percent = (double) ($value_all / $value_a) * 100;

@(double)$value0_percent = (double)($value0 / $value_all) * 100;
@(double)$value1_percent = (double)($value1 / $value_all) * 100;
@(double)$value2_percent = (double)($value2 / $value_all) * 100;
@(double)$value3_percent = (double)($value3 / $value_all) * 100;
@(double)$value4_percent = (double)($value4 / $value_all) * 100;
@(double)$value5_percent = (double)($value5 / $value_all) * 100;

?>
<html>
    <head>
        <title>admin 관리 페이지</title>
        <meta charset="utf-8" >
		<link rel="stylesheet" href="normalize.css" />
		<link rel="stylesheet" href="board.css" />
    </head>
    <body>
	<article class="boardArticle">
        <h1>투표 관리 페이지</h1><br />
		<br>
		<br>&nbsp;&nbsp;<a href="javascript:history.go(0)">[새로고침]</a>
		&nbsp;&nbsp;<a href="javascript:history.go(-1)">[뒤로가기]</a><br> <br>
		&nbsp;&nbsp;<button type="button" onclick="location.href='./admin_vote_time_service.php'">시간대 별 투표 통계</button><BR>
		<table>
			<caption class="readHide">투표 현황</caption>
			<thead>
				<tr>
					<th scope="col" class="vote_kind">유권자 수(명)</th>
					<th scope="col" class="no">투표자 수(명)</th>
					<th scope="col" class="candi1">투표율(%)</th>
				</tr>
			</thead>
			<tbody>
			<hr />
					
				<tr>
					<td class="vote_kind"><?php echo $row7[0]?></td>
					<td class="no"><?php echo $value_all?></td>
					<td class="candi1"><?php echo round($vote_percent, 2);?></td>
					
				</tr>
				
					
			</tbody>
		</table>
		
		<br>
		<br>
		<br>
		
		<hr />
		<form method='post' action='admin_vote_time_check.php'>
			&nbsp;&nbsp;투표 시작시간 : <input type="datetime-local" name="start_date"/> <br /> <br>
            &nbsp;&nbsp;투표 종료시간 : <input type="datetime-local" name="end_date"/> <br /> <br>
            &nbsp;&nbsp;<input type="submit" value="시간 설정하기" />
		<br> <br>
		&nbsp;&nbsp;투표 종료일시 : <?php echo $value ?>

		<br>
		<br>
		<br> 
		
		&nbsp; <a href="./admin_add_candi.php">후보 추가</a> 
		&nbsp; &nbsp; <a href="./admin_delete_candi.php">후보 삭제</a> 
        <hr />
		<table>
			<caption class="readHide">후보 리스트</caption>
			<thead>
				<tr>
					<th scope="col" class="vote_kind">투표 분류</th>
					<th scope="col" class="no">기호</th>
					<th scope="col" class="candi1">정후보</th>
					<th scope="col" class="candi2">부후보</th>
				</tr>
			</thead>
			<tbody>
					<?php
						$sql = 'select * from admin_vote';
						$result = mysqli_query($con, $sql);
						while($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
						
					?>
				<tr>
					<td class="vote_kind"><?php echo $row['vote_name']?></td>
					<td class="no"><?php echo $row['candi_number']?></td>
					<td class="candi1"><?php echo $row['vote_candi1']?></td>
					<td class="candi2"><?php echo $row['vote_candi2']?></td>
				</tr>
				<?php
						}	
				?>
					
			</tbody>
		</table>
		<br><br><br>
		<table>
			<caption class="readHide">득표 현황</caption>
			<thead>
				<tr>
					<th scope="col" class="vote_kind">기호</th>
					<th scope="col" class="no">득표 수</th>
				</tr>
			</thead>
			<tbody>
			<?php
				$sql = 'select * from admin_vote';
				$result = mysqli_query($con, $sql);
				while($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
				?>	
				<tr>
				<td class="vote_kind"><?php echo $row['candi_number']?></td>
					<td class="no"><?php echo $row['candi_get']?></td>
				<?php
						}	
				?>
				</tr>
				
					
			</tbody>
		</table>
		
    </body>
</html>
<?php
$data = 

array(
	array('기호', '득표율'),
	array('1번', round($value1_percent,2)),
	array('2번', round($value2_percent,2)),
	array('3번', round($value3_percent,2)),
	array('4번', round($value4_percent,2)),
	array('5번', round($value5_percent,2)),
	array('기권', round($value0_percent,2)),
);
$options = array(
	'title' => '실시간 득표율 (%)',
	'width' => 1000, 'height' => 500
);
?>

<script src="//www.google.com/jsapi"></script>
<script>
var data = <?= json_encode($data) ?>;
var options = <?= json_encode($options) ?>;
google.load('visualization', '1.0', {'packages':['corechart']});
google.setOnLoadCallback(function() {
  var chart = new google.visualization.ColumnChart(document.querySelector('#chart_div'));
  chart.draw(google.visualization.arrayToDataTable(data), options);
});
</script>
<div id="chart_div"></div>
