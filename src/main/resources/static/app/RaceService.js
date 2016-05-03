"use strict";

angular
    .module('jregatta')
    .factory('RaceService', RaceService);

function RaceService($resource) {
    return $resource('/regattas/:regattaId/races/:raceId', {
        regattaId: '@regattaId',
        raceId: '@raceId'
    }, {
        update: {
            method: 'PUT'
        }
    });
};

