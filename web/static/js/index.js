$(document).ready(function () {

    var host = "http://localhost:8080";
    //获取所有文章信息  
    //Ajax调用处理
    $.ajax(
        {
            type: "get",
            url: host+"/api/articles",
            // async: true,
            success(result) {
                console.log(result);
                result.forEach(k => {
                    console.log(k);
                    createArticle(k.blogArticleInfo.id,k.blogArticleInfo.title,k.category,k.blogArticleInfo.createBy.substr(0,10),k.blogArticleInfo.imgUrl,k.blogArticleInfo.traffic);
                });
            }
        });


    var createArticle = function(id,title,category,createBy,imgUrl,traffic){
        var parentdiv = $('<article class="mt-5"></article>');        //创建一个父div
        parentdiv.attr('id', 'article' + id);        //给父div设置id
        var headerdiv = $('<header></header');
        var childdiv01 = $('<h1 class="text-dark">'+title+'</h1>');        //创建一个子div
        var childdiv02 = $('<span class="">发表于 <time>'+createBy+ '</time> </span>');        //创建一个子div
        var childdiv03 = $('<span class="category"> &nbsp; | &nbsp; <span class="">分类: <strong>'+category+'</strong> </span> </span>');        //创建一个子div
        var childdiv04 = $('<span class="traffic"> &nbsp; | &nbsp; <span class="">浏览量: '+traffic+' </span> </span>');        //创建一个子div

        childdiv01.appendTo(headerdiv);
        childdiv02.appendTo(headerdiv);
        childdiv03.appendTo(headerdiv);
        childdiv04.appendTo(headerdiv);
        headerdiv.appendTo(parentdiv);

        var childdiv04 = $('<div> <p><img src='+imgUrl+' class="img-responsive center-block"> <br><br> </p> <p class="more"><a href=article.html#'+id+' title='+title+'>阅读全文 »</a></p> </div>');        //创建一个子div
        childdiv04.appendTo(parentdiv);
    
        parentdiv.appendTo('#articles');            //将父div添加到body中
    
    }

})