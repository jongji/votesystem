<html>
	<head>
		<title>후보 추가</title>
<h4>[ 후보 추가 ]</h4>
<hr/>
	</head>

	<body>
<table border=1 cellpadding=5 cellspacing=3 bgcolor=#FFFFFF align='center' id='write_table'>
<form method='post' action='admin_add_candi_check.php'>
<tr>
<td>투표  분류</td>
<td><input type = "text" name = "vote_name" size = "20" maxlength = "20" style = "ime-mode:disabled" value = "컴공 학회 투표"/></td>
</tr>
<tr>
<td>기호</td>
<td><select name = "candi_no" tabindex = "10">
	<option selected value = "1번">1번</option>
	<option value = "2번">2번</option>
	<option value = "3번">3번</option>
	<option value = "4번">4번</option>
	<option value = "5번">5번</option>
 </td>
</tr>
<tr>
<td>정후보</td>
<td><input type = "text" name = "candi1" size = "10" maxlength = "10" value = ""/></td>
</tr>
<tr>
<td>정후보 전공</td>
<td><input type = "text" name = "candi1_major" size = "20" maxlength = "20" value = ""/></td>
</tr>
<tr>
<td>정후보 정보</td>
<td><input type = "text" name = "candi1_info" size = "30" maxlength = "30" value = ""/></td>
</tr>
<tr>
<td>부후보</td>
<td><input type = "text" name = "candi2" size = "10" maxlength = "15" value = ""/></td>
</tr>
<tr>
<td>부후보 전공</td>
<td><input type = "text" name = "candi2_major" size = "20" maxlength = "20" value = ""/></td>
</tr>
<tr>
<td>부후보 정보</td>
<td><input type = "text" name = "candi2_info" size = "30" maxlength = "30" value = ""/></td>
</tr>
<tr>
<td>핵심공약</td>
<td><textarea name='promise' cols='50' rows='3'></textarea></td>
</tr>
<tr>
<td colspan = "2"><input type = "submit" value = "작성 완료" /></td>
</tr>
</form>
</table>
</body>
</html>