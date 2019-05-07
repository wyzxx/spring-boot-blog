// Call the dataTables jQuery plugin
$(document).ready(function () {

  var id = $.cookie('JSESSIONID');
 
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
  $.ajax(
    {
      type: "post",
      url: "http://localhost:8080/admin/logintest/" + id,
      // async: true,
      success(result) {
        if (result != true) {
          alert("请先登陆!");
          window.location.href = "login.html";
          return;
        }
        // console.log(result);
      }
    });



  //Ajax调用处理
  $.ajax(
    {
      type: "get",
      url: "http://localhost:8080/admin/articles",
      // async: true,
      success(result) {

        // console.log(result);


        result.forEach(k => {
          creatediv1(k.blogArticleInfo.id, k.blogArticleInfo.title, k.category, k.blogArticleInfo.traffic, k.blogArticleInfo.createBy, k.blogArticleInfo.modifiedBy, k.blogArticleInfo.imgUrl, k.blogArticleInfo.isEffective)
        });


        $('#dataTable').DataTable();
      }
    });






  $.ajax(
    {
      type: "get",
      url: "http://localhost:8080/admin/comments",
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



