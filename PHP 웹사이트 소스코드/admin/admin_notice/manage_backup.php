<?
session_start();
    $admin_id = $_SESSION['admin_id'];
	
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con,"utf8");

if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}

// 한 페이지에 보여줄 리스트 수
$record_per_page = 15;
// 한 블럭에 보여줄 페이지 수
$page_per_block = 10;
// 현재 페이지
$now_page = ($_GET['page']) ? $_GET['page'] : 1; 
// 현재 블럭
$now_block = ceil($now_page / $page_per_block);

$sql = "SELECT notice_id, notice_name FROM admin_notice ORDER BY notice_id";
$result = mysqli_query($sql, $bd) or die("SQL 에러");
// 전체 리스트 수
$total_record = mysqli_num_rows($result);

$sql = "SELECT notice_id, notice_name FROM admin_notice ORDER BY notice_id desc LIMIT ". $record_per_page * ($now_page - 1) .",". $record_per_page * $now_page;

//$result = mysqli_query($sql, $bd) or die("SQL 에러");

?>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="notice_board.css">
	<title>공지사항</title>
</head>
<body>
	<div>
		<table class="notice_board">
			<thead>
			<tr id="info">
				<th scope="cols" class="notice_no">공지 번호</th>
				<th scope="cols" class="notice_title">공지 제목</th>
				<!-- <td class="writer">작성자</td> 필요 없을지도? -->
				<!-- <td class="date">날짜</td> -->
			</tr>
			</thead>
			<tbody>
				<?
				for ($i = 0; $i < $total_record; $i++) {
				// 데이터 가져오기
				mysqli_data_seek($result, $i);
				$row = mysqli_fetch_array($result);
				
				$sql = 'select * from admin_notice';
				$result = mysqli_query($con, $sql);
				while($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
				?>
			<tr>
				<td class="notice_no"><? echo $row["notice_id"] ?></td>
				<td class="notice_title"><a href="notice_read.php?num=<?=$row["notice_id"]?>"> <?= $row["notice_name"] ?></a></td>
				<!-- <td class="writer"><?//= $row["name"] ?></td> -->
			</tr>
			<? }?>
			</tbody>
		</table>
		<form action="notice_write_form.html">
			<input type="submit" value="공지 작성">
		</form>
	</div>
	<div>
	    <tr>
			<?php
                        // 전체 페이지 수
			$total_page = ceil($total_record / $record_per_page);
                        // 전체 블럭 수
			$total_block = ceil($total_page / $page_per_block);

                        // 현재 블럭이 1보다 클 경우
			if(1 < $now_block ) {
			  $pre_page = ($now_block - 1) * $page_per_block;
			  echo '<a href="admin_notice.php?page='.$pre_page.'">이전</a>';

			}

			$start_page = ($now_block - 1) * $page_per_block + 1;
			$end_page = $now_block * $page_per_block;

                        // 총 페이지와 마지막 페이지를 같게 하기, 즉 글이 있는 페이지까지만 설정
			if($end_page > $total_page) {
			  $end_page = $total_page;
			}

			?>
			    
			<?php for($i = $start_page; $i <= $end_page; $i++) {?>
			    <td><a href="admin_notice.php?page=<?= $i ?>"><?= $i ?></a></td>
			<?php }?>
			
			<?php
                        // 현재 블럭이 총 블럭 수 보다 작을 경우
			if($now_block < $total_block) {
			  $post_page = $now_block * $page_per_block + 1;
			  echo '<a href="admin_notice.php?page='.$post_page.'">다음</a>';
			}

			?>
		</tr>
	</div>
</body>
</html>