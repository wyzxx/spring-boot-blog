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
})