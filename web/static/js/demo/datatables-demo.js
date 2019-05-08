// Call the dataTables jQuery plugin
$(document).ready(function () {


  var article =   Object();
  var textarea = null;
  var comment = Object();
  var content = Object();








  var id = $.cookie('JSESSIONID');
  console.log(id);
  // var url = location.href;
  // var patt1 = new RegExp("#([0-9]|[A-Za-z])+");
  // console.log(patt1.exec(url));
  // if (patt1.exec(url) == null) {
  //   alert("请先登陆!");
  //   window.location.href = "login.html";
  //   return;
  // }
  // var id = patt1.exec(url)[0].substr(1);  //获取session的值

  // // document.cookie="JSESSIONID="+id;
  // console.log(id);
  // //检查是否登陆

  //Ajax调用处理
  $.ajax(
    {
      type: "GET",
      url: "http://localhost:8080/admin/articles",
      xhrFields: { withCredentials: true },

      // async: true,
      success(result) {

        // console.log(result);


        result.forEach(k => {
          creatediv1(k.blogArticleInfo.id, k.blogArticleInfo.title, k.category, k.blogArticleInfo.traffic, k.blogArticleInfo.createBy, k.blogArticleInfo.modifiedBy, k.blogArticleInfo.imgUrl, k.blogArticleInfo.isEffective)
        });


        $('#dataTable').DataTable();
      },
       error:function(error){
        alert("请先登陆!");
        window.location.href = "login.html";
        return false;
      }
      // console.log(result);
      
    });






  $.ajax(
    {
      type: "get",
      url: "http://localhost:8080/admin/comments",
      xhrFields: { withCredentials: true },

      // async: true,
      success(result) {

        // console.log(result);

        result.forEach(k => {
          creatediv2(k.id, k.name, k.email, k.content, k.reference, k.isExpected, k.isEffective)
        });
        $('#dataTable2').DataTable();
      }
    });


  // $('#articles')






  $('#article-modify').on('click',function(e) {
    var articleId = article.id;
    var title = $("#inputTitle1").val();
    var category = $("#inputCategory1").val();
    var isCategoryChanged = $('input[name="categoryChange"]:checked').val();
    var imgUrl = $("#inputImgUrl1").val();
    var isEffective = $('input[name="gridRadios"]:checked').val();
    var data = null

    $.ajax(
      {
        type: "PUT",
        url: "http://localhost:8080/admin/articles/"+articleId,
        contentType:"application/x-www-form-urlencoded",
        // contentType:"application/x-www-form-urlencoded",
        // contentType:"json",
        data:{"title":title,"category":category,"isCategoryChanged":isCategoryChanged,"imgUrl":imgUrl,"isEffective":isEffective,"data":data
        },
        xhrFields:{withCredentials:true},
        success(result) {
        // console.log(result);
        }
      });
  
  });



  $('#article-delete').on('click',function(e) {
    var articleId = article.id;
    $.ajax(
      {
        type: "DELETE",
        url: "http://localhost:8080/admin/articles/"+articleId,
        // contentType:"application/x-www-form-urlencoded",
        // contentType:"json",
        xhrFields:{withCredentials:true},
        success(result) {
        // console.log(result);
        }
      });
  
  });






  $('#comment-delete').on('click',function(e) {
    var commentId = comment.id;
    $.ajax(
      {
        type: "DELETE",
        url: "http://localhost:8080/admin/comments/"+commentId,
        // contentType:"application/x-www-form-urlencoded",
        // contentType:"json",
        xhrFields:{withCredentials:true},
        success(result) {
        // console.log(result);
        }
      });
  
  });


$('#article-add').on('click',function(e){
  var title2 = $("#inputTitle2").val();
  var category2 = $("#inputCategory2").val();
  var imgUrl2 = $("#inputImgUrl2").val();
  var isEffective2 = $('input[name="gridRadios2"]:checked').val();
  var data =  $('#textarea').val();

  $.ajax(
    {
      type: "POST",
      url: "http://localhost:8080/admin/articles",
      // contentType:"application/x-www-form-urlencoded",
      // contentType:"json",
      xhrFields:{withCredentials:true},
      data:{"title":title2,"category":category2,"imgUrl":imgUrl2,"isEffective":isEffective2,"data":data
      },
      success(result) {
      // console.log(result);
      }
    });

})


$('#articleContentView').on('click',function(e){
  $.ajax(
    {
      type: "get",
      url: "http://localhost:8080/admin/articles/"+article.id,
      // async: true,
      xhrFields:{withCredentials:true},
      success(result) {
        // console.log(result);
        content.id = result.blogArticleContent.id
        content.content = result.blogArticleContent.content
      $('#textarea2').val(content.content);
      }
    })
})





$('#articleContentModify').on('click',function(e) {
  var contentId = content.id;
  var data = $('#textarea2').val();
  $.ajax(
    {
      type: "PUT",
      url: "http://localhost:8080/admin/content/"+contentId,
      data:{"data":data},
      // contentType:"application/x-www-form-urlencoded",
      // contentType:"json",
      xhrFields:{withCredentials:true},
      success(result) {
      console.log(result);
      }
    });

});







$('#comment-modify').on('click',function(e) {
  var commentId = comment.id;
  var isEffective = $('input[name="gridRadios3"]:checked').val();
  console.log(isEffective);
  $.ajax(
    {
      type: "PUT",
      url: "http://localhost:8080/admin/comments/"+commentId,
      // contentType:"application/x-www-form-urlencoded",
      // contentType:"json",
      xhrFields:{withCredentials:true},
      data:{"isEffective":isEffective},
      success(result) {
      console.log(result);
      }
    });

});



$('body').on('click','.article-change',function() {
  var tr = $(this).parent().parent().parent().parent().parent();
  var tds = $(tr[0]).children('td');
  article.id =  $(tds[0]).html();
  article.title =  $(tds[1]).html();
  article.category = $(tds[2]).html();
  article.traffic = $(tds[3]).html();
  article.createBy = $(tds[4]).html();
  article.modifiedBy = $(tds[5]).html();
  article.imgUrl = $(tds[6]).html();
  article.isEffective = $(tds[7]).html();
  
  $("#inputTitle1").val(article.title)
  $("#inputCategory1").val(article.category);
  $("#inputImgUrl1").val(article.imgUrl);
  

});

$('body').on('click','.article-del',function() {
  var tr = $(this).parent().parent().parent().parent().parent();
  var tds = $(tr[0]).children('td');
  
  article.id =  $(tds[0]).html();
});

$('body').on('click','.comment-del',function() {
  var tr = $(this).parent().parent().parent().parent().parent();
  var tds = $(tr[0]).children('td');
  
  comment.id =  $(tds[0]).html();
});

$('body').on('click','.comment-change',function() {

  var tr_c= $(this).parent().parent().parent().parent().parent();
  var tds_c = $(tr_c[0]).children('td');
  console.log(tds_c);
  comment.id =  $(tds_c[0]).html();
  comment.isEffective = $(tds_c[6]).html();
// console.log(1);
  // var tr = $(this).parent().parent().parent().parent().parent();
  // var tds = $(tr[0]).children('td');
  
  // article.id =  $(tds[0]).html();
  // article.title =  $(tds[1]).html();
  // article.category = $(tds[2]).html();
  // article.traffic = $(tds[3]).html();
  // article.createBy = $(tds[4]).html();
  // article.modifiedBy = $(tds[5]).html();
  // article.imgUrl = $(tds[6]).html();
  // article.isEffective = $(tds[7]).html();
  
  // $("#inputTitle1").val(article.title)
  // $("#inputCategory1").val(article.category);
  // $("#inputImgUrl1").val(article.imgUrl);
  

});



























  var creatediv1 = function (id, title, category, traffic, createBy, modifiedBy, imgUrl, isEffective) {
    var parentdiv = $('<tr></tr>');        //创建一个父div
    parentdiv.attr('id', 'article' + id);        //给父div设置id

    var childdiv01 = $('<td>' + id + '</td>');        //创建一个子div
    var childdiv02 = $('<td>' + title + '</td>');        //创建一个子div
    var childdiv03 = $('<td>' + category + '</td>');        //创建一个子div
    var childdiv04 = $('<td>' + traffic + '</td>');        //创建一个子div
    var childdiv05 = $('<td>' + createBy + '</td>');        //创建一个子div
    var childdiv06 = $('<td>' + modifiedBy + '</td>');        //创建一个子div
    var childdiv07 = $('<td>' + imgUrl + '</td>');        //创建一个子div
    var childdiv08 = $('<td>' + isEffective + '</td>');        //创建一个子div
    var childdiv09 = $('<td> <ul class="navbar-nav ml-auto"> <li class="nav-item dropdown"> <a class="nav-link dropdown-toggle" href="#"  role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 操作 </a> <div class="dropdown-menu dropdown-menu-right " aria-labelledby="navbarDropdown"> <a class="article-change dropdown-item" data-toggle="modal" data-target="#articleModal" href="#">修改</a> <a class="article-del dropdown-item" data-toggle="modal" data-target="#articleDelModal" href="#">删除</a> </div> </li> </ul> </td>');        //创建一个子div


    childdiv01.appendTo(parentdiv);        //将子div添加到父div中
    childdiv02.appendTo(parentdiv);        //将子div添加到父div中
    childdiv03.appendTo(parentdiv);        //将子div添加到父div中
    childdiv04.appendTo(parentdiv);        //将子div添加到父div中
    childdiv05.appendTo(parentdiv);        //将子div添加到父div中
    childdiv06.appendTo(parentdiv);        //将子div添加到父div中
    childdiv07.appendTo(parentdiv);        //将子div添加到父div中
    childdiv08.appendTo(parentdiv);        //将子div添加到父div中
    childdiv09.appendTo(parentdiv);        //将子div添加到父div中

    parentdiv.appendTo('#articles');            //将父div添加到body中

    // location.reload();
  }



  var creatediv2 = function (id, name, email, content, reference, isExpected, isEffective) {
    var parentdiv2 = $('<tr></tr>');        //创建一个父div
    parentdiv2.attr('id', 'article' + id);        //给父div设置id

    var childdiv012 = $('<td>' + id + '</td>');        //创建一个子div
    var childdiv022 = $('<td>' + name + '</td>');        //创建一个子div
    var childdiv032 = $('<td>' + email + '</td>');        //创建一个子div
    var childdiv042 = $('<td>' + content + '</td>');        //创建一个子div
    var childdiv052 = $('<td>' + reference + '</td>');        //创建一个子div
    var childdiv062 = $('<td>' + isExpected + '</td>');        //创建一个子div
    var childdiv072 = $('<td>' + isEffective + '</td>');        //创建一个子div
    var childdiv082 = $('<td> <ul class="navbar-nav ml-auto"> <li class="nav-item dropdown"> <a class="nav-link dropdown-toggle" href="#"  role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 操作 </a> <div class="dropdown-menu dropdown-menu-right " aria-labelledby="navbarDropdown"> <a class="comment-change dropdown-item" data-toggle="modal" data-target="#commentModal" href="#">修改</a> <a class="comment-del dropdown-item" data-toggle="modal" data-target="#commentDelModal" href="#">删除</a> </div> </li> </ul> </td>');        //创建一个子div


    childdiv012.appendTo(parentdiv2);        //将子div添加到父div中
    childdiv022.appendTo(parentdiv2);        //将子div添加到父div中
    childdiv032.appendTo(parentdiv2);        //将子div添加到父div中
    childdiv042.appendTo(parentdiv2);        //将子div添加到父div中
    childdiv052.appendTo(parentdiv2);        //将子div添加到父div中
    childdiv062.appendTo(parentdiv2);        //将子div添加到父div中
    childdiv072.appendTo(parentdiv2);        //将子div添加到父div中
    childdiv082.appendTo(parentdiv2);        //将子div添加到父div中

    parentdiv2.appendTo('#comments');            //将父div添加到body中

    // location.reload();
  }






});



