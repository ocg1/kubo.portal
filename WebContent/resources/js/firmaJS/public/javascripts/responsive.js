$(document).ready(function() {
var isiPad = /ipad/i.test(navigator.userAgent.toLowerCase());
if (isiPad)
{
  $("#tarjeta-login").removeClass("tarjeta-login");
  $("#tarjeta-login").addClass("tarjeta-login-ipad");

  $("#containerApp").removeClass("container");
  $("#containerApp").addClass("containerApp");

  $("#body").removeClass("body");
  $("#body").addClass("bodyApp");
  $("#canvas1").addClass("canvasMob");
  $("#canvas2").addClass("canvasMob");
  $("#canvas3").addClass("canvasMob");
  $("#canvas4").addClass("canvasMob");

  $("#formApp").removeClass("col-md-8");
  $("#formApp").removeClass("col-md-offset-2");

  $("#formApp").addClass("col-xs-8");
  $("#formApp").addClass("col-xs-offset-2");


}



var isiPhone = /iphone/i.test(navigator.userAgent.toLowerCase());
var isiPod = /ipod/i.test(navigator.userAgent.toLowerCase());
if (isiPhone  || isiPod)
{
    $("#tarjeta-login").removeClass("tarjeta-login");
    $("#tarjeta-login").addClass("tarjeta-login-ios");


    $("#tarjeta-modal").removeClass("tarjeta-login-modal");
    $("#tarjeta-modal").addClass("tarjeta-login-ios");


    $("#containerApp").removeClass("container");
    $("#containerApp").addClass("containerApp");

    $("#body").removeClass("body");
    $("#body").addClass("bodyApp");

    $("#playBtn").addClass("btn-100");
    // alert("hola");
    $("#canvas1").addClass("canvasMob");
    $("#canvas2").addClass("canvasMob");
    $("#canvas3").addClass("canvasMob");
    $("#canvas4").addClass("canvasMob");
    $(".img-firma").hide();
    // $(".img-logo-cont").hide();
    //  startScript("canvas1");
    $("#sizeScreen").val($( window ).height());
    $("#sizeScreenX").val($(window).width());


    $('#form-datos').on('keyup keypress', function(e) {
      var code = e.keyCode || e.which;
      if (code == 13) { 
        e.preventDefault();
        return false;
      }
    });
}

var isAndroid = /android/i.test(navigator.userAgent.toLowerCase());

if (isAndroid)
{
  $("#tarjeta-login").removeClass("tarjeta-login");
    $("#tarjeta-login").addClass("tarjeta-login-ios");


    $("#tarjeta-modal").removeClass("tarjeta-login-modal");
    $("#tarjeta-modal").addClass("tarjeta-login-ios");


    $("#containerApp").removeClass("container");
    $("#containerApp").addClass("containerApp");

    $("#body").removeClass("body");
    $("#body").addClass("bodyApp");

    $("#playBtn").addClass("btn-100");
    // alert("hola");
    $("#canvas1").addClass("canvasMob");
    $("#canvas2").addClass("canvasMob");
    $("#canvas3").addClass("canvasMob");
    $("#canvas4").addClass("canvasMob");
    $(".img-firma").hide();
    // $(".img-logo-cont").hide();
    //  startScript("canvas1");
    $("#sizeScreen").val($( window ).height());
    $("#sizeScreenX").val($(window).width());
    $('#form-datos').on('keyup keypress', function(e) {
      var code = e.keyCode || e.which;
      if (code == 13) { 
        e.preventDefault();
        return false;
      }
    });
}
});
