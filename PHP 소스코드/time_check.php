<?php
 
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con, "utf8"); 

$realtime = date("Y-m-d H:i:s");
$time_result = mysqli_query($con, "select end_date from admin_vote_time where number=1");

$time_row = mysqli_fetch_array($time_result, MYSQLI_ASSOC);

$check_time = $time_row["end_date"];
$check_time2 = strtotime($check_time);

$remain_time = $check_time2 - time();

$day = floor($remain_time / 60 / 60 / 24);
$hour = floor($remain_time / 60 / 60) % 24;
$minute = floor($remain_time / 60) % 60;
$second = floor($remain_time % 60);

echo $day.'일 '.$hour.'시간'.$minute.'분 '.$second.'초 남았습니다.';
 
mysqli_close($con);

?>