"use strict";

angular
    .module('jregatta')
    .factory('RaceResultService', RaceResultService);

function RaceResultService($resource) {
    return $resource('/regattas/:regattaId/races/:raceId/results/:resultId', {
        regattaId: '@regattaId',
        raceId: '@raceId',
        resultId: '@resultId'
    }, {
        update: {
            method: 'PUT',
            params: {regattaId: '@regattaId', raceId: '@raceId'}
        }
    }, {
        save: {
            method: 'POST',
            params: {regattaId: '@regattaId', raceId: '@raceId'}
        }
    });
}
