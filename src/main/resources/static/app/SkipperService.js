"use strict";

angular
    .module('jregatta')
    .factory('SkipperService', SkipperService);

function SkipperService($resource) {
    return $resource('/regattas/:regattaId/skippers/:skipperId', {
        regattaId: '@regattaId',
        skipperId: '@skipperId'
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

