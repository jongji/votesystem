<html>
<head>
<title>버튼 생성 테스트</title>
<script type="text/javascript">
	function createRadio(name) {	//
	var obj;

	try {
		obj = document.createElement("<input type='radio' name='"+name+" '/>");
		} catch(e) {
		obj = document.createElement("input");
		obj.type = "radio";
		obj.name = name;
		}
		
	document.forms[0].appendChild(obj);
	}
	
	function createTextbox(name) {
	var obj;

	try {
		obj = document.createElement("<input type='text' name='"+name+"'/>");
		} catch(e) {
		obj = document.createElement("input");
		obj.type = "text";
		obj.name = name;
		}
		
	document.forms[0].appendChild(obj);
	}
	
	function Create() {
		createRadio("radio");
		createTextbox("text");
	}
</script>
</head>
<body>
<form name="frm"></form>
<input type="button" onclick="Create()">생성</input>
<input type="button" onclick="alert(document.forms['frm'].innerHTML);">소스보기</input>
</body>
</html>