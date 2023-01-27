// (1) 회원정보 수정
function update(userId, event) {
	event.preventDefault()//form태그의 action을 비활성화 한다
	//serialize() => 태그가 가지고 있는 모든 input값을 가져온다
	
	let data = $("#profileUpdate").serialize();
	console.log(data)
	$.ajax({
		type: "put",
		url: `/api/user/${userId}`,
		data: data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "json"
	}).done(res => {
		console.log("update성공", res)
		alert("회원정보가 성공적으로 수정 되었습니다")
		location.href = `/user/${userId}`;
	}).fail(error => {// HttpStatus 코드가 200번대가 아닐때 실행
		console.log("update실패", error)
		if(error.data == null){
			alert(error.responseJSON.meassage);
		}else{
			alert(JSON.stringify(error.responseJSON.data));	
		}	
	})
	}
	