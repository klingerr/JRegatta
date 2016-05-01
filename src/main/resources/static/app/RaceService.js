"use strict";

angular
    .module('jregatta')
    .factory('RaceService', RaceService);

function RaceService($resource) {
    return $resource('/races/:id', {
        id: '@_id'
    }, {
        update: {
            method: 'PUT'
        }
    });
};

