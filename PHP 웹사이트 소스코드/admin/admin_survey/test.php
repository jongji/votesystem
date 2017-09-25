<html>
<head>
<title>설문조사</title>
<script language=javascript>
        function chkAnswer()	//현재 단순하게 코드로 설문 모양새만 만들고 있음. 코드 수정 방식이 아닌, 작성 페이지에서 설문조사 내용을 수정하고 기입할 수 있게 바꾸어야 할 것.
        {						//이 페이지 내용은 관리자 페이지에서 작성하게끔 하고, 내용만 받아다 띄워줘야 됨. chkAnswer는 그대로 써도 되지 않을까 싶은데.
                var Form = eval(document.form1);
                var chk = 0;
                if(Form.answer[].checked == true) chk++; //답변 추가시 이곳에 번호순으로 추가. 제대로 먹히는지 테스트는 아직 안 해본 상태.
                if(chk == 0) {
                        alert('선택해주세요.');
                        return false;
                }
                Form.action = 'recieve.php';	//체크하고 링크를 타면 recieve.php로 넘어가서 결과값을 테이블에 저장.
                Form.submit();
        }

</script>
</head>
<body>
<form name=form1 method=post>
답변추가시 이곳에 value를 번호순으로 추가<br>

찬성 <input type=radio name=answer value=surv[1]> <br>
반대 <input type=radio name=answer value=surv[2]> <br>  

<br>
<a href=javascript:; onClick="chkAnswer(); return false;">설문 완료<a>

</form>
</body>
</html>