
    var blogsBox = document.querySelector(".blogs");
    var blogAddBox = document.querySelector(".blog-add-box");
    $('.catalogs li a').click(function(e){

        var data = {};
        data["catalogId"] = e.target.getAttribute("data");
        blogAddBox.innerHTML = "";
        var toUploadBtn = document.createElement("a");
        toUploadBtn.setAttribute("href","/blog/upload?id="+e.target.getAttribute("data"));
        toUploadBtn.innerHTML = "블러그 추가하기";
        blogAddBox.appendChild(toUploadBtn);
         $.ajax({
             contentType:'application/json',
             dataType:'json',
             data:JSON.stringify(data),
             url:'/index/blogajax',
             type:'POST',
             success:function(response){
                 var list = response.blogs;
                 blogsBox.innerHTML = "";
                 for(var index in list){
                     console.log();
                     var liTag = document.createElement('li');
                     liTag.setAttribute("class","blog-content");
                     var aTag = document.createElement('a');
                     aTag.setAttribute("href","/blog/details?id="+list[index].id);
                     aTag.innerHTML = list[index].title;
                     liTag.appendChild(aTag);
                     blogsBox.appendChild(liTag);
                 }

                 },
             });
});


    var signUpId = document.querySelector('.sign-up-id');
    var signUpPass = document.querySelector('.sign-up-pass');
    var signUpPassRe = document.querySelector('.sign-up-pass-re');
    var signUpName = document.querySelector('.name');

    var flagSignUp = true;

    var checkOverlapBtn = document.querySelector(".check-overlap");
    var submitBtn = document.querySelector('.submit-btn');

    checkOverlapBtn.addEventListener("click",function(e){
        if(signUpId.value == ""){
            alert("아이디가 빈칸이면 안됍니다.");
        }else{
            var data = {};
            data['signUpId'] = signUpId.value;
            $.ajax({
                contentType:'application/json',
                dataType:'json',
                data:JSON.stringify(data),
                url:'/index/checksignupajax',
                type:'POST',
                success:function(response){
                    alert(response.message);
                    if(response.flag == "true"){
                        flagSignUp = false;
                    }else {
                        flagSignUp = true;
                    }
                },
            });
            e.stopPropagation();
        }

    });

    submitBtn.addEventListener('click',function(e){
        console.log(flagSignUp);
        if(flagSignUp == true){
            console.log("아이디 중간 검사");
            alert("아이디 중복검사를 안했습니다");
        }else{
            if(signUpId.value == "" || signUpPass.value == "" || signUpPassRe.value == "" || signUpName.value == ""){
                console.log("빈칸검사")
                alert("빈칸이 있으면 안됍니다");
            }else if(signUpPass.value != signUpPassRe.value){
                alert("비밀번호가 중복돼지 않았습니다");
            }else{
                console.log("ajax run");
                var data = {};
                data['signUp'] = signUpId.value;
                data['signPass'] = signUpPass.value;
                data['name'] = signUpName.value;

                $.ajax({
                    contentType:'application/json',
                    dataType:'json',
                    data:JSON.stringify(data),
                    url:'/index/signup',
                    type:'POST',
                    success:function(response){
                        alert(response.messge);
                        location.href = "/index";
                    },
                });
            }
        }
        e.stopPropagation();
    });

    var catalogSubmitBtn = document.querySelector(".catalog-submit-btn");
    var getTitle = document.querySelector(".catalogInput");
    var catalogs = document.querySelector(".catalogs");

    catalogSubmitBtn.addEventListener("click",function (e) {
        if(getTitle.value != ""){
            var data = {};
            data['catalogTitle'] = getTitle.value;
            $.ajax({
                contentType:'application/json',
                dataType:'json',
                data:JSON.stringify(data),
                url:'/index/catalogajax',
                type:'POST',
                success:function(response){
                    alert(response.catalogMsg);
                    var liTag = document.createElement('li');
                    var aTag = document.createElement('a');
                    aTag.innerHTML = getTitle.value;
                    aTag.setAttribute('data',response.catalogId);
                    liTag.appendChild(aTag);
                    catalogs.appendChild(liTag);
                    location.href = "/index";
                },
            });
        }else {
            alert("제목이 빈칸입니다.");
        }

    });
