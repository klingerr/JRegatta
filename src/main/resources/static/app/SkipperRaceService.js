"use strict";

angular
    .module('jregatta')
    .factory('SkipperRaceService', SkipperRaceService);

function SkipperRaceService($resource) {
    return $resource('/regattas/:regattaId/skippers/races/:raceId', {
        regattaId: '@regattaId',
        skipperId: '@raceId'
    });
};

