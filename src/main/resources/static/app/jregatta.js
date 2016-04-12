var jregatta = angular.module('jregatta', ['jregattaControllers', 'jregattaServices', 'ngResource', 'ngRoute', 'ui.bootstrap', 'ui.grid', 'ui.grid.selection', 'ui.grid.edit', 'ui.grid.cellNav', 'ui.grid.resizeColumns'  ]);

jregatta.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/regattas', {
		templateUrl : 'index.html',
		controller : 'RegattaListController'
	}).when('/regattas/:regattaId', {
		templateUrl : 'index.html',
		controller : 'RegattaDetailController'
	}).otherwise({
		redirectTo : '/regattas'
	});
} ]);
