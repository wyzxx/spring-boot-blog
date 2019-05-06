$(document).ready(function () {

    var url = location.href;
    var patt1 = new RegExp("#[0-9]+");
    var id = patt1.exec(url)[0].substr(1);  //获取文章的值
    console.log(id);





    var host = "http://localhost:8080";


    //获取文章内容
    //Ajax调用处理
    $.ajax(
        {
            type: "get",
            url: host + "/api/articles/" + id,
            // async: true,
            success(result) {
                // console.log(result);
                if (result != "") {
                    createArticleInfo(result.blogArticleInfo.id, result.blogArticleInfo.title, result.category, result.blogArticleInfo.createBy.substr(0, 10), result.blogArticleInfo.imgUrl);
                    createArticleContent(result.blogArticleContent.content);
                }
            }
        });




    $.ajax(
        {
            type: "get",
            url: host + "/api/articles/"+id+"/comments",
            // async: true,
            success(result) {
                // console.log(result);
                if (result != "") {
                    result.forEach(k => {
                        createComment(k.id,k.name,k.email,k.content,k.reference);
                    });
                }
            }

        
        });



$('#post-comment').on('click',function(){
    var articleId = id;
    var name = $('#name').val();
    var email = $('#email').val();
    var content = $('#comment-textarea').val();
    var ref = 0;// to do
    // if(name==null | name==""|name.length==0){
    //     alert("用户名为空");
    //     return;
    // }
    if(content==null | content==""|content.length==0){
        alert("内容为空");
        return;
    }
    $.ajax(
        {
            type: "POST",
            url: host + "/api/articles/"+articleId+"/comments",
            data:{"name":name,"email":email,"content":content,"reference":ref},
            // async: true,
            success(result) {
                // console.log(result);
                if(result==true){
                    alert("评论成功");
                    location.reload();
                }
              
            }

        
        });
});










    var createArticleInfo = function (id, title, category, createBy, imgUrl) {
        var parentdiv = $('<article class="mt-5"></article>');        //创建一个父div
        parentdiv.attr('id', 'article' + id);        //给父div设置id
        var headerdiv = $('<header></header');
        var childdiv01 = $('<h1 class="text-dark">' + title + '</h1>');        //创建一个子div
        var childdiv02 = $('<span class="">发表于 <time>' + createBy + '</time> </span>');        //创建一个子div
        var childdiv03 = $('<span class="category"> &nbsp; | &nbsp; <span class="">分类: <strong>' + category + '</strong> </span> </span>');        //创建一个子div

        childdiv01.appendTo(headerdiv);
        childdiv02.appendTo(headerdiv);
        childdiv03.appendTo(headerdiv);

        headerdiv.appendTo(parentdiv);

        var childdiv04 = $('<div> <p><img src=' + imgUrl + ' class="img-responsive center-block"> <br><br> </p>  </div>');        //创建一个子div
        childdiv04.appendTo(parentdiv);

        parentdiv.appendTo('#articles');            //将父div添加到body中

    }

    var createArticleContent = function(content){
        var contentdiv = $('<div>'+marked(content)+'</div>');
        contentdiv.attr('id', 'contents'); 
        contentdiv.appendTo('#articles');
    }



    var createComment = function(id,name,email,content,ref){
        var commentdiv = $('<div class="card mt-1 mb-2"></div>')
        commentdiv.attr('id', 'comment'+id); 
        var childdiv01 = $('<div class="card-header">'+name+"  "+email+'</div>');
        var childdiv02 = $('<div class="card-body"> <blockquote class="blockquote mb-0"> <p>'+content+'</p> </blockquote> </div> </div>');
        childdiv01.appendTo(commentdiv);
        childdiv02.appendTo(commentdiv);
        commentdiv.appendTo($('#comments'));
    }
})