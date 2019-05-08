(function($) {
  "use strict"; // Start of use strict
  var article =   Object();
  var textarea = null;
  var comment = Object();
  var content = Object();

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
        // contentType:"application/x-www-form-urlencoded",
        // contentType:"json",
        // xhrFields:{withCredentials:true},
        data:{"title":title,"category":category,"isCategoryChanged":isCategoryChanged,"imgUrl":imgUrl,"isEffective":isEffective,"data":data
        },
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
        // xhrFields:{withCredentials:true},
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
        // xhrFields:{withCredentials:true},
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
      // xhrFields:{withCredentials:true},
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
      // xhrFields:{withCredentials:true},
      success(result) {
      console.log(result);
      }
    });

});







$('#comment-modify').on('click',function(e) {
  var commentId = comment.id;
  var isEffective = $('input[name="gridRadios3"]:checked').val();
  $.ajax(
    {
      type: "PUT",
      url: "http://localhost:8080/admin/comments/"+commentId,
      // contentType:"application/x-www-form-urlencoded",
      // contentType:"json",
      // xhrFields:{withCredentials:true},
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


  // Toggle the side navigation
  $("#sidebarToggle, #sidebarToggleTop").on('click', function(e) {
    $("body").toggleClass("sidebar-toggled");
    $(".sidebar").toggleClass("toggled");
    if ($(".sidebar").hasClass("toggled")) {
      $('.sidebar .collapse').collapse('hide');
    };
  });

  // Close any open menu accordions when window is resized below 768px
  $(window).resize(function() {
    if ($(window).width() < 768) {
      $('.sidebar .collapse').collapse('hide');
    };
  });

  // Prevent the content wrapper from scrolling when the fixed side navigation hovered over
  $('body.fixed-nav .sidebar').on('mousewheel DOMMouseScroll wheel', function(e) {
    if ($(window).width() > 768) {
      var e0 = e.originalEvent,
        delta = e0.wheelDelta || -e0.detail;
      this.scrollTop += (delta < 0 ? 1 : -1) * 30;
      e.preventDefault();
    }
  });

  // Scroll to top button appear
  $(document).on('scroll', function() {
    var scrollDistance = $(this).scrollTop();
    if (scrollDistance > 100) {
      $('.scroll-to-top').fadeIn();
    } else {
      $('.scroll-to-top').fadeOut();
    }
  });

  // Smooth scrolling using jQuery easing
  $(document).on('click', 'a.scroll-to-top', function(e) {
    var $anchor = $(this);
    $('html, body').stop().animate({
      scrollTop: ($($anchor.attr('href')).offset().top)
    }, 1000, 'easeInOutExpo');
    e.preventDefault();
  });

})(jQuery); // End of use strict
