let index = {
    init: function (){
        $("#btn-save").on("click", ()=>{ // function(){} 을 사용하지않고 ()=>{} 이걸 사용하는 이유는 this를 바인딩하기 위해서이다.
            this.save();
        });

        $("#btn-delete").on("click", ()=>{ // function(){} 을 사용하지않고 ()=>{} 이걸 사용하는 이유는 this를 바인딩하기 위해서이다.
            this.deleteById();
        });

        $("#btn-update").on("click", ()=>{ // function(){} 을 사용하지않고 ()=>{} 이걸 사용하는 이유는 this를 바인딩하기 위해서이다.
            this.update();
        });
    },

    save: function (){
        let data = {
            title:$("#title").val(),
            content:$("#content").val()
        };

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data), // Json String으로 변환. ( 그냥 위 data 형식으루 전달하면 object 형식으로 전달됨. )
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤타입인지 (MIME)
            // 지정하지 않아도 디폴트로 json으로 응답을 줌.
            dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든것이 문자열 ( 생긴게 JSON이라면 ) => Javascript object로 전환시켜줌.
        // ajax 호출 성공시
        }).done(function(resp){
            alert("글쓰기가 완료되었습니다.");
            // console.log(resp);
            location.href = "/";
        // ajax 호출 성공시
        }).fail(function(error){
            alert(JSON.stringify(error));

        });
    },

    deleteById: function (){
        let id = $("#id").text();

        $.ajax({
            type: "DELETE",
            url: "/api/board/" + id,
            dataType: "json"
        }).done(function(resp){
            alert("삭제가 완료되었습니다.");
            location.href = "/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    update: function (){
        let id = $("#id").val();

        let data = {
            title:$("#title").val(),
            content:$("#content").val()
        };

        $.ajax({
            type: "PUT",
            url: "/api/board/"+id,
            data: JSON.stringify(data), // Json String으로 변환. ( 그냥 위 data 형식으루 전달하면 object 형식으로 전달됨. )
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤타입인지 (MIME)
            // 지정하지 않아도 디폴트로 json으로 응답을 줌.
            dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든것이 문자열 ( 생긴게 JSON이라면 ) => Javascript object로 전환시켜줌.
            // ajax 호출 성공시
        }).done(function(resp){
            alert("글 수정이 완료되었습니다.");
            // console.log(resp);
            location.href = "/";
            // ajax 호출 성공시
        }).fail(function(error){
            alert(JSON.stringify(error));

        });
    }
}

index.init();