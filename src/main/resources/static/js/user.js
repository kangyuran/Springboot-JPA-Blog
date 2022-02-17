let index = {
    init: function (){
        $("#btn-save").on("click", ()=>{ // function(){} 을 사용하지않고 ()=>{} 이걸 사용하는 이유는 this를 바인딩하기 위해서이다.
            this.save();
        });
        // $("#btn-login").on("click", ()=>{ // function(){} 을 사용하지않고 ()=>{} 이걸 사용하는 이유는 this를 바인딩하기 위해서이다.
        //     this.login();
        // });
    },

    save: function (){
        // alert("click");
        let data = {
            username:$("#username").val(),
            password:$("#password").val(),
            email:$("#email").val()
        };

        // ajax호출 시 default가 비동기 호출이다. ( 비동기로 수행되다가 수행이 완료되면 아래 로직 수행중이더라도 돌아와 callback을 수행한다.
        // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌.
        $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), // Json String으로 변환. ( 그냥 위 data 형식으루 전달하면 object 형식으로 전달됨. )
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤타입인지 (MIME)
            // 지정하지 않아도 디폴트로 json으로 응답을 줌.
            dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든것이 문자열 ( 생긴게 JSON이라면 ) => Javascript object로 전환시켜줌.
        // ajax 호출 성공시
        }).done(function(resp){
            alert("회원가입이 완료되었습니다.");
            // console.log(resp);
            location.href = "/";
        // ajax 호출 성공시
        }).fail(function(error){
            alert(JSON.stringify(error));

        }); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청.!!
        //
        // 회원가입시 Ajax를 사용하는 2가지 이유
        // 1. 요청에 대한 응답을 html이 아닌 Data(Json)를 받기 위하여 .. 브라우저가 웹이 아닌 다른 매체가 될수 있기에 Json으로 통신하여 Client에서 제어하도록 처리.
        // 2. 비동기 통신을 하기 위해서이다.
    },
    //
    // login: function (){
    //     let data = {
    //         username:$("#username").val(),
    //         password:$("#password").val()
    //     };
    //
    //     $.ajax({
    //         type: "POST",
    //         url: "/api/user/login",
    //         data: JSON.stringify(data),
    //         contentType: "application/json; charset=utf-8",
    //         dataType: "json"
    //     }).done(function(resp){
    //         alert("로그인이 완료되었습니다.");
    //         location.href = "/";
    //     }).fail(function(error){
    //         alert(JSON.stringify(error));
    //     });
    // }
}

index.init();