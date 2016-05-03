"use strict";

angular
    .module('jregatta')
    .factory('SkipperService', SkipperService);

function SkipperService($resource) {
    return $resource('/regattas/:regattaId/skippers/:id', {
        regattaId: '@regattaId',
        skipperId: '@skipperId'
    }, {
        update: {
            method: 'PUT'
        },
        search: {
            method: 'GET', isArray:true,
            params: {
                regattaId: '@regattaId'
            }
        }
    });
};

