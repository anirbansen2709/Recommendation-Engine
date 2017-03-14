// CLASSYLOADER
// ----------------------------------- 

(function(window, document, $, undefined){

  $(function(){

    var $scroller       = $(window),
        imusicRecommendationFlagClass = 'js-is-imusicRecommendation'; // a classname to detect when a chart has been triggered after scroll

    $('[data-classyloader]').each(initClassyLoader);
    
    function initClassyLoader() {
    
      var $element = $(this),
          options  = $element.data();
      
      // At lease we need a data-percentage attribute
      if(options) {
        if( options.triggerImusicRecommendation ) {

          $scroller.scroll(function() {
            checkLoaderImusicRecommendation($element, options);
          });
          // if the element starts already in view
          checkLoaderImusicRecommendation($element, options);
        }
        else
          startLoader($element, options);
      }
    }
    function checkLoaderImusicRecommendation(element, options) {
      var offset = -20;
      if( ! element.hasClass(imusicRecommendationFlagClass) &&
          $.Utils.isImusicRecommendation(element, {topoffset: offset}) ) {
        startLoader(element, options);
      }
    }
    function startLoader(element, options) {
      element.ClassyLoader(options).addClass(imusicRecommendationFlagClass);
    }

  });

})(window, document, window.jQuery);
