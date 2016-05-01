"use strict";

angular.module('jregatta', [
    // rest services
    'ngResource',
    // app routing
    'ngRoute',
    // layout
    'ngMaterial',
//    'ui.bootstrap',
    // table widget and its options
    'ui.grid', 'ui.grid.selection', 'ui.grid.edit', 'ui.grid.cellNav',
    'ui.grid.resizeColumns',
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
    .config(['$routeProvider', function ($routeProvider) {
	        $routeProvider.when('/regatta', {
	            templateUrl: 'partials/regatta.html',
	            controller: 'RegattaController',
	            breadcrumbs : [ home, regatta ]
	        }).when('/skipper', {
	            templateUrl: 'partials/skipper.html',
	            controller: 'SkipperController',
	            breadcrumbs : [ home, skipper ]
	        }).when('/club', {
	            templateUrl: 'partials/club.html',
	            controller: 'ClubController',
	            breadcrumbs : [ home, club ]
	        }).when('/race', {
	            templateUrl: 'partials/race.html',
	            controller: 'RaceController',
	            breadcrumbs : [ home, race ]
	        }).when('/result', {
	            templateUrl: 'partials/result.html',
	            controller: 'ResultController',
	            breadcrumbs : [ home, result ]
	        }).when('/certificate', {
	            templateUrl: 'partials/certificate.html',
	            controller: 'CertificateController',
	            breadcrumbs : [ home, certificate ]
            }).otherwise({
                redirectTo: '/regatta'
            });
        }]);

// static breadcrumb definition - routeProvider and constants have to be matching
const home = { href : '#/', label : 'Home' };
const regatta = { href : '#/regatta', label : 'Regatten' };
const skipper = { href : '#/skipper', label : 'Teilnehmer' };
const club = { href : '#/club', label : 'Verein' };
const race = { href : '#/race', label : 'Wettfahrt' };
const result = { href : '#/result', label : 'Ergebnis' };
const certificate = { href : '#/certificate', label : 'Urkunde' };

angular
    .module('jregatta')
    .controller('BreadcrumbsController', BreadcrumbsController);

function BreadcrumbsController($scope, $route) {
  $scope.route = $route;
}

