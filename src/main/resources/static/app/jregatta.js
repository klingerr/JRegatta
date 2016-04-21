// "use strict";

angular.module('jregatta', [
    // custom app modules
//    'RegattaController',
//    'RegattaService',
    // rest services
    'ngResource',
    // app routing
    'ngRoute',
    // layout
    'ui.bootstrap',
    // table widget and its options
    'ui.grid', 'ui.grid.selection', 'ui.grid.edit', 'ui.grid.cellNav',
    'ui.grid.resizeColumns',
    // user feedback
    'ngToast'
]);

angular
    .module('jregatta')
    .config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider.when('/regattas', {
                templateUrl: 'index.html',
                controller: 'RegattaController'
            }).otherwise({
                redirectTo: '/regattas'
            });
        }]);
