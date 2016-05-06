"use strict";


angular.module('gridFilters', [])
.filter('griddropdown', function() {
  return function (input, context) {
    
    try {
        //For some reason the text "this" is occasionally directly being
        //passed here
        if (typeof context === 'undefined' || context === 'this')
          return 0;

        //Workaround for bug in ui-grid
        if (typeof context.col === 'undefined') {
          var sortCols = context.grid.getColumnSorting();
          if (sortCols.length <= 0)
            return 0;

          context = { col: sortCols[0], row: context };
        }
      
        var map = context.col.colDef.editDropdownOptionsArray;
        var idField = context.col.colDef.editDropdownIdLabel;
        var valueField = context.col.colDef.editDropdownValueLabel;
        var initial = context.row.entity[context.col.field];
        if (typeof map !== "undefined") {
          for (var i = 0; i < map.length; i++) {
            if (map[i][idField] == input) {
              return map[i][valueField];
            }
          }
        } else if (initial) {
          return initial;
        }
        return input;
      
  } catch (e) {
    context.grid.appScope.log("Error: " + e);
  };
};
});


angular.module('jregatta', [
    // rest services
    'ngResource',
    // app routing
    'ngRoute',
    // layout
    'ngMaterial',
//    'ui.bootstrap',
    // table widget and its options
    'ngAnimate',
    'ui.grid', 'ui.grid.selection', 'ui.grid.exporter', 'ui.grid.edit', 'ui.grid.cellNav',
    'ui.grid.resizeColumns', 'gridFilters', 'angularMoment', 'ui.grid.grouping' 
]);

angular
    .module('jregatta')
    .config(function ($mdThemingProvider) {
    	  // Extend the red theme with a few different colors
    	  var adwGreen = $mdThemingProvider.extendPalette('blue', {
    	    '500': '087B00'
    	  });
    	  // Register the new color palette map with the name <code>neonRed</code>
    	  $mdThemingProvider.definePalette('adwGreen', adwGreen);
    	  // Use that theme for the primary intentions
    	  $mdThemingProvider.theme('default').primaryPalette('adwGreen')
    	  
        $mdThemingProvider.theme("success-toast");
        $mdThemingProvider.theme("error-toast");
    })
    .config(['$routeProvider', '$locationProvider', 
        function ($routeProvider, $locationProvider) {
	        $routeProvider.when('/clubs', {
	            templateUrl: 'partials/club.html',
	            controller: 'ClubController',
	            breadcrumbs : [ home, club ]
	        }).when('/regattas', {
	            templateUrl: 'partials/regatta.html',
	            controller: 'RegattaController',
	            breadcrumbs : [ home, regatta ]
	        }).when('/regattas/:regattaId/skippers', {
	            templateUrl: 'partials/skipper.html',
	            controller: 'SkipperController',
	            breadcrumbs : [ home, skipper ]
	        }).when('/regattas/:regattaId/races', {
	            templateUrl: 'partials/race.html',
	            controller: 'RaceController',
	            breadcrumbs : [ home, race ]
	        }).when('/regattas/:regattaId/races/:raceId/finish', {
	            templateUrl: 'partials/finish.html',
	            controller: 'FinishController',
	            breadcrumbs : [ home, finish ]
	        }).when('/regattas/:regattaId/races/:raceId/results', {
	            templateUrl: 'partials/raceResult.html',
	            controller: 'RaceResultController',
	            breadcrumbs : [ home, raceResult ]
	        }).when('/regattas/:regattaId/results', {
	            templateUrl: 'partials/result.html',
	            controller: 'ResultController',
	            breadcrumbs : [ home, result ]
	        }).when('/regattas/:regattaId/certificates', {
	            templateUrl: 'partials/certificate.html',
	            controller: 'CertificateController',
	            breadcrumbs : [ home, certificate ]
            }).otherwise({
                redirectTo: '/regattas'
            });
            
            // enable HTML5mode to disable hashbang urls
//            $locationProvider.html5Mode(true);
        }]);

// static breadcrumb definition - routeProvider and constants have to be matching
const home = { href : '#/', label : 'Home' };
const club = { href : '#/club', label : 'Verein' };
const regatta = { href : '#/regatta', label : 'Regatten' };
const skipper = { href : '#/skipper', label : 'Teilnehmer' };
const race = { href : '#/race', label : 'Wettfahrt' };
const finish = { href : '#/finish', label : 'Zieldurchgang' };
const raceResult = { href : '#/result', label : 'Wettfahrtergebnis' };
const result = { href : '#/result', label : 'Gesamtergebnis' };
const certificate = { href : '#/certificate', label : 'Urkunde' };

angular
    .module('jregatta')
    .controller('BreadcrumbsController', BreadcrumbsController);

function BreadcrumbsController($scope, $route) {
  $scope.route = $route;
}

