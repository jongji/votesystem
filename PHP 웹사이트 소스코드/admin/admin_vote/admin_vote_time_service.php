<?php

// 다른 파일의 사용자 정의 함수 "makeChartParts ()"를 읽어들입니다.

require_once './make_chart_parts.php';

session_start();
$admin_id = $_SESSION['admin_id'];
	
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con,"utf8");
  
if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}
$q0 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=0";
$q1 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=1";
$q2 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=2";
$q3 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=3";
$q4 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=4";
$q5 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=5";
$q6 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=6";
$q7 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=7";
$q8 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=8";
$q9 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=9";
$q10 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=10";
$q11 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=11";
$q12 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=12";
$q13 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=13";
$q14 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=14";
$q15 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=15";
$q16 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=16";
$q17 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=17";
$q18 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=18";
$q19 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=19";
$q20 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=20";
$q21 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=21";
$q22 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=22";
$q23 = "select count(HOUR(insert_time)) from user_vote where HOUR(insert_time)=23";

$q0_check = mysqli_query($con, $q0);
$q1_check = mysqli_query($con, $q1);
$q2_check = mysqli_query($con, $q2);
$q3_check = mysqli_query($con, $q3);
$q4_check = mysqli_query($con, $q4);
$q5_check = mysqli_query($con, $q5);
$q6_check = mysqli_query($con, $q6);
$q7_check = mysqli_query($con, $q7);
$q8_check = mysqli_query($con, $q8);
$q9_check = mysqli_query($con, $q9);
$q10_check = mysqli_query($con, $q10);
$q11_check = mysqli_query($con, $q11);
$q12_check = mysqli_query($con, $q12);
$q13_check = mysqli_query($con, $q13);
$q14_check = mysqli_query($con, $q14);
$q15_check = mysqli_query($con, $q15);
$q16_check = mysqli_query($con, $q16);
$q17_check = mysqli_query($con, $q17);
$q18_check = mysqli_query($con, $q18);
$q19_check = mysqli_query($con, $q19);
$q20_check = mysqli_query($con, $q20);
$q21_check = mysqli_query($con, $q21);
$q22_check = mysqli_query($con, $q22);
$q23_check = mysqli_query($con, $q23);

$row0 = mysqli_fetch_array($q0_check);
$row1 = mysqli_fetch_array($q1_check);
$row2 = mysqli_fetch_array($q2_check);
$row3 = mysqli_fetch_array($q3_check);
$row4 = mysqli_fetch_array($q4_check);
$row5 = mysqli_fetch_array($q5_check);
$row6 = mysqli_fetch_array($q6_check);
$row7 = mysqli_fetch_array($q7_check);
$row8 = mysqli_fetch_array($q8_check);
$row9 = mysqli_fetch_array($q9_check);
$row10 = mysqli_fetch_array($q10_check);
$row11 = mysqli_fetch_array($q11_check);
$row12 = mysqli_fetch_array($q12_check);
$row13 = mysqli_fetch_array($q13_check);
$row14 = mysqli_fetch_array($q14_check);
$row15 = mysqli_fetch_array($q15_check);
$row16 = mysqli_fetch_array($q16_check);
$row17 = mysqli_fetch_array($q17_check);
$row18 = mysqli_fetch_array($q18_check);
$row19 = mysqli_fetch_array($q19_check);
$row20 = mysqli_fetch_array($q20_check);
$row21 = mysqli_fetch_array($q21_check);
$row22 = mysqli_fetch_array($q22_check);
$row23 = mysqli_fetch_array($q23_check);

(int)$value0 = (int)$row0[0];
(int)$value1 = (int)$row1[0];
(int)$value2 = (int)$row2[0];
(int)$value3 = (int)$row3[0];
(int)$value4 = (int)$row4[0];
(int)$value5 = (int)$row5[0];
(int)$value6 = (int)$row6[0];
(int)$value7 = (int)$row7[0];
(int)$value8 = (int)$row8[0];
(int)$value9 = (int)$row9[0];
(int)$value10 = (int)$row10[0];
(int)$value11 = (int)$row11[0];
(int)$value12 = (int)$row12[0];
(int)$value13 = (int)$row13[0];
(int)$value14 = (int)$row14[0];
(int)$value15 = (int)$row15[0];
(int)$value16 = (int)$row16[0];
(int)$value17 = (int)$row17[0];
(int)$value18 = (int)$row18[0];
(int)$value19 = (int)$row19[0];
(int)$value20 = (int)$row20[0];
(int)$value21 = (int)$row21[0];
(int)$value22 = (int)$row22[0];
(int)$value23 = (int)$row23[0];

$data = array(

  array('', '투표 수'),

  array('0시',   $value0), array('1시',  $value1), array('2시',  $value2),

  array('3시',  $value3), array('4시',  $value4), array('5시',  $value5),

  array('6시',  $value6), array('7시',  $value7), array('8시',  $value8),

  array('9시', $value9), array('10시', $value10), array('11시',  $value11),
  
  array('12시', $value12), array('13시', $value13), array('14시', $value14),
  
  array('15시', $value15), array('16시',$value16), array('17시',  $value17),
  
  array('18시', $value18), array('19시', $value19), array('20시',  $value20),
  
  array('21시', $value21), array('22시', $value22), array('23시',  $value23));



// 그래프 옵션

$options = array(

  'title'  => '시간대별 투표 통계',  // 그래프 제목

  'titleTextStyle' => array('fontSize' => 20), // 제목 스타일

  'series' => array(1 => array('targetAxisIndex' => 1)),  // 세로축을 2 축화

  'vAxes'  => array(0 => array('title'    => '투표 수',  // 세로축 왼쪽

                               'minValue' =>  0, 'minValue' => 30),

                    1 => array('title'    => '시간',  // 세로축 오른쪽

                               'minValue' => 30, 'maxValue' => 100)),

  'width'  => 1800, 'height' => 500,  // 폭, 높이

  'chartArea' => array('width' => 1500, 'height' => 300),  // 차트 영역

  'legend' => array('position' => 'in', 'alignment' => 'start'));  // 범례



// 그래프 유형 (선 그래프)

$type = 'LineChart';

// 그래프 그림의 JavaScript 함수, 표시할 <div> 태그의 생성

list($chart, $chart_div) = makeChartParts($data, $options, $type);



// 두 번째 그래프는 첫 번째 그래프의 데이터를 테이블로 하기

$options = array('width' => 200);  // 그래프 (테이블 세트) 옵션

$type = 'Table';  // 그래프 유형 (테이블 세트)

list($table, $table_div) = makeChartParts($data, $options, $type);

?>

<!DOCTYPE html>

<html lang="ko">

<head>

<meta charset="UTF-8">

<title>투표 시간대별 통계</title>
 <h1>투표 관리 페이지</h1><br />
		<br>
		<br>&nbsp;&nbsp;<a href="javascript:history.go(0)">[새로고침]</a>
		&nbsp;&nbsp;<a href="javascript:history.go(-1)">[뒤로가기]</a><br> <br>

<script src="https://www.google.com/jsapi"></script>

<script>

<?php

// 그래프 그리기 함수를 표시합니다.

echo $chart, $table;

?>

</script>
</head>
<body>
<div>
<?php
// 차트를 표시 할 <div> 태그를 적당한 위치에 배치합니다.
echo $chart_div, $table_div;
?>
</div>
</body>
</html>