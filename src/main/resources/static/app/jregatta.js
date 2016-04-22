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
//        $mdThemingProvider.theme('default')
//            .primaryPalette('green')
//            .accentPalette('orange');
        $mdThemingProvider.theme("success-toast");
        $mdThemingProvider.theme("error-toast");
    })
    .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/regattas', {
                templateUrl: 'index.html',
                controller: 'RegattaController',
                breadcrumbs : [ home, page1 ]
            }).otherwise({
                redirectTo: '/regattas'
            });
        }]);

// static breadcrumb definition - routeProvider and constants have to be matching
const home = { href : '#/', label : 'Home' };
const page1 = { href : '#/page1', label : 'Page 1' };

angular
    .module('jregatta')
    .controller('BreadcrumbsController', BreadcrumbsController);

function BreadcrumbsController($scope, $route) {
  $scope.route = $route;
}

