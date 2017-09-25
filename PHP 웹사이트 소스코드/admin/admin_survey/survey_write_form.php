<!DOCTYPE>


<h4>[ 설문 추가 ]</h4>

<table border=1 cellpadding=4 cellspacing=2 bgcolor=#FFFFFF align='center' id='write_table'>
<form method='post' action='./admin_survey_write.php'>

<tr>
<td>설문 번호</td>
<td><select name = "survey_id" tabindex = "10">
	<option selected value = "1">1번</option>
	<option value = "2">2번</option>
	<option value = "3">3번</option>
	<option value = "4">4번</option>
	<option value = "5">5번</option>
 </td>
</tr>

<tr>
<td>제목</td>
<td><input type = "text" name = "survey_name" size = "40" maxlength = "40" value = ""/></td>
</tr>

<tr>
<td>주제</td>
<td><input type = "text" name = "survey_short_content" size = "40" value = ""/></td>
</tr>

<tr>
<td>내용</td>
<td><textarea cols="50" rows="20" name="survey_content"></textarea></td>
</tr>

<tr>
<td>선택 1</td>
<td><input type="text" name="choose1" size="50" value = ""></td>
</tr>

<tr>
<td>선택 2</td>
<td><input type="text" name="choose2" size="50" value = ""></td>
</tr>

<tr>
<td>선택 3</td>
<td><input type="text" name="choose3" size="50" value = ""></td>
</tr>

<tr>
<td>선택 4</td>
<td><input type="text" name="choose4" size="50" value = ""></td>
</tr>

<tr>
<td colspan = "2"><input type = "submit" value = "작성 완료" /></td>
</tr>

</form>
</table>