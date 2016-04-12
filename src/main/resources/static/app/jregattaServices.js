var jregattaServices = angular.module('jregattaServices', [ 'ngResource' ]);

jregattaServices.factory('Regatta', function($resource) {
	return $resource('/regattas/:regattaId', {
		regatta : "@regatta"
	});
});