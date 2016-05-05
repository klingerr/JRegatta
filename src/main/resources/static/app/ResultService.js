"use strict";

angular
        .module('jregatta')
        .factory('ResultService', ResultService);

function ResultService($resource) {
    return $resource('/regattas/:regattaId/results/:resultId', {
        regattaId: '@regattaId',
        resultId: '@resultId'
    }, {
        update: {
            method: 'PUT',
            params: {regattaId: '@regattaId'}
        }
    });
}
