$(document).ready(function () {

    var host = "http://localhost:8080";
    // 获取所有文章信息  
    // Ajax调用处理
    $.ajax(
        {
            type: "get",
            url: host + "/api/categories",
            // async: true,
            success(result) {
                console.log(result);
                result.forEach(k => {
                    createCategory(k.id, k.name);
                    $.ajax(
                        {
                            type: "get",
                            url: host + "/api/categories/" + k.id + "/articles",
                            success(result) {
                                if (result != "") {
                                    result.forEach(m => {
                                        console.log(m);
                                        url = "article.html#"+m.blogArticleInfo.id;
                                        createSimpleArticle(k.id, m.blogArticleInfo.createBy.substr(0, 10), url, m.blogArticleInfo.title);
                                    })
                                }
                            }
                        });
                });
            }
        });


    var createCategory = function (id, name) {
        var childdiv01 = $('<div class="mt-5 mb-5" id="category' + id + '"> <h2 class="text-dark"><strong>' + name + '</strong></h2>  <hr class="my-4"> </div>');
        childdiv01.appendTo('#articles');
    }

    var createSimpleArticle = function (id, time, url, title) {
        var childdiv01 = $('<div> <time>' + time + '</time> </div> <h5 class=""> <a class="" href="' + url + '"> <span>' + title + '</span> </a> </h5>')
        childdiv01.appendTo($('#category' + id));
    }


})