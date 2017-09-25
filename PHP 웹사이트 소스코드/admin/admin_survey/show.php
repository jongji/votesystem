<?
$board = "admin_survey";
$con=mysqli_connect("localhost","root","920910","votingsystem");   //테이블 수정, 출력할 내용 수정. 이 php는 설문 끝나고 결과 나오는 부분이니 설문조사 이후에 메인 대신(?)나오게 해 줄것.
mysqli_set_charset($con,"utf8");

/*
각답변에 해당하는 번호를 선택하는 것입니다.
만약 답변이 더 많으면 번호순으로 더 늘리시면 됩니다.
*/
$res1 = mysql_query("select * from $board where answer=1", $con);
$res2 = mysql_query("select * from $board where answer=2", $con);

//각각 선택된답변이 몇개인지를 출력
$num1 = mysql_num_rows($res1);
$num2 = mysql_num_rows($res2);

$sum = $num1 + $num2; //전체적으로 답변된것이 몇개인지 출력 (즉 설문참여자가 총몇분인지 출력한다.)
//각 선택된 답변 백분율 구하기
$img1 = (int)(($num1*100)/$sum); 
$img2 = (int)(($num2*100)/$sum);
echo("
<html>
<head>
<title>설문조사결과</title>
</head>
<table width=780 border=0 cellspacing=0 cellpadding=0 align=center>
        <tr><td>
                <table width=780 border=0 cellspacing=0 cellpadding=0 align=center><tr height=10><td></td></tr><tr height=1><td bgcolor=A4A4A4></td></tr><tr height=10><td></td></tr></table>
                <table width=780 border=0 cellspacing=0 cellpadding=0 align=center style='border:1px #999999 solid'>
                        <tr height=25><td colspan=4 bgcolor=666666 align=center><font color=FFFFFF><b> 결과  (총 : $sum 표)</b></font></td></tr>
                        <tr><td colspan=4 bgcolor=999999></td></tr>
                        <tr><td>찬성($num1)</td><td><img src='bar.gif' width='$img1' height='13'></td><td>$img1 %</td></tr>
                        <tr><td>반대($num2)</td><td><img src='bar.gif' width='$img2' height='13'></td><td>$img2 %</td></tr>
                </table>
        </td></tr>
</table>
</body>
</html>
");
?>
