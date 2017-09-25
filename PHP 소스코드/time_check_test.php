<?php
$remain_time = strtotime('2018-04-20 11:10:09') - time();
$day = floor($remain_time / 60 / 60 / 24);
$hour = floor($remain_time / 60 / 60) % 24;
$minute = floor($remain_time / 60) % 60;
$second = floor($remain_time % 60);
echo $day.'일 '.$hour.'시 '.$minute.'분 '.$second.'초 남음';// : 남은시간 문자로 표현하기
?>