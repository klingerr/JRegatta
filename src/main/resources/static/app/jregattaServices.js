var jregattaServices = angular.module('jregattaServices', [ 'ngResource' ]);

jregattaServices.factory('RegattaService', function($resource) {
	return $resource('/regattas/:id', {
		id : '@_id'
	}, {
		update : {
			method : 'PUT'
		}
	});
});
