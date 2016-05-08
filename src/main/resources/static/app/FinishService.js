"use strict";

angular
    .module('jregatta')
    .factory('FinishService', FinishService);

function FinishService($resource) {
    return $resource('/regattas/:regattaId/races/:raceId/finishs/:resultId', {
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
