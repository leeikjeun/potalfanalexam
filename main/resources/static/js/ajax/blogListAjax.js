
    var blogsBox = document.querySelector(".blogs");
    $('.catalogs li a').click(function(e){

    var data = {};
     data["catalogId"] = e.target.getAttribute("data");

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
                 var aTag = document.createElement('a');
                 aTag.setAttribute("href","/blog/details?id="+list[index].id);
                 aTag.innerHTML = list[index].title;
                 liTag.appendChild(aTag);
                 blogsBox.appendChild(liTag);
             }

             },
         });
});
