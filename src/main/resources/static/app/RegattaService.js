"use strict";

angular
    .module('jregatta')
    .factory('RegattaService', RegattaService);

function RegattaService($resource) {
    return $resource('/regattas/:id', {
        id: '@_id'
    }, {
        update: {
            method: 'PUT'
        }
    });
};

