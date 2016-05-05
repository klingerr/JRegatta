"use strict";

angular
    .module('jregatta')
    .factory('RaceService', RaceService);

function RaceService($resource) {
    return $resource('/regattas/:regattaId/races/:raceId', {
        regattaId: '@regattaId',
        skipperId: '@raceId'
    }, {
        update: {
            method: 'PUT',
            params: {regattaId: '@regattaId'}
        }
    }, {
        save: {
            method: 'POST',
            params: {regattaId: '@regattaId'}
        }
    });
};
