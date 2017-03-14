$(function() {

  $(".numbers-row").append('<div class="dec button">-</div><div class="inc button">+</div>');
  $(".button").on("click", function() {
    var newVal;
    var oldValue = $(this).parent().find("input").val();
    if ($(this).text() == "+") {

      if(oldValue == "hours") {newVal="days";}
      else if(oldValue == "mins") {newVal="hours";}
      else if(oldValue == "days") {newVal="weeks";}
      else if(oldValue == "weeks") {newVal="months";}
      else if(oldValue == "months") {newVal="months";}

      else if(oldValue == "KB") {newVal="MB";}
      else if(oldValue == "MB") {newVal="GB";}
      else if(oldValue == "GB") {newVal="GB";}
      else {newVal = parseFloat(oldValue) + 1;}


    }
    else {
      // Don't allow decrementing below zero
      if (oldValue == "mins") {newVal = "mins";}
      else if (oldValue == "days") {newVal = "hours";}
      else if (oldValue == "hours") {newVal = "mins";}
      else if (oldValue == "weeks") {newVal = "days";}
      else if (oldValue == "months") {newVal = "weeks";}
      else if (oldValue == "GB") {newVal = "MB";}
      else if (oldValue == "MB") {newVal = "KB";}
      else if (oldValue == "KB") {newVal = "KB";}
      else {
        if (oldValue > 0) {
          var newVal = parseFloat(oldValue) - 1;
        } else {
          newVal = 0;
        }
      }
    }
    $(this).parent().find("input").val(newVal);

  });

});