"use strict";

angular
    .module('jregatta')
    .factory('ResultService', ResultService);

function ResultService($resource) {
    return $resource('/regattas/:regattaId/races/:raceId/results', {
        regattaId: '@regattaId',
        raceId: '@raceId'
    }, {
        update: {
            method: 'PUT'
        }
    });
};

