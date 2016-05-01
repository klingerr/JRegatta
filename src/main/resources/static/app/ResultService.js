"use strict";

angular
    .module('jregatta')
    .factory('ResultService', ResultService);

function ResultService($resource) {
    return $resource('/results/:id', {
        id: '@_id'
    }, {
        update: {
            method: 'PUT'
        }
    });
};

