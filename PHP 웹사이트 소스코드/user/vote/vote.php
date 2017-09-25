<?php
session_start();
$user_id = $_SESSION['user_id'];
	
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem"); 
//$con=mysqli_connect("localhost","root","920910","votingsystem");  
mysqli_set_charset($con,"utf8");
  
if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}

?>
<html>
    <head>
        <title>선거 후보 목록</title>
        <meta charset="utf-8" >
		<link rel="stylesheet" href="normalize.css" />
		<link rel="stylesheet" href="board.css" />
    </head>
    <body>
	<h1>투표하기</h1><br />
		<br>
		<br>&nbsp;&nbsp;<a href="javascript:history.go(0)">[새로고침]</a>
		&nbsp;&nbsp;<a href="javascript:history.go(-1)">[뒤로가기]</a><br> <br>
		<hr/>
	<article class="boardArticle">
		<br>
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
						//Header("Content-type: image");	//이미지 보여주는 코드에서 다른 부분은 다 문제없는데 이 부분을 넣으면 보여주는게 아니라 vote.php를 저장해버림. 뭐가 문제지
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
		</table><br>
		<button type="button" onclick="location.href='select.php'">투표 페이지로 이동</button>
    </body>
</html>

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
