"use strict";

angular
    .module('jregatta')
    .factory('ClubService', ClubService);

function ClubService($resource) {
    return $resource('/clubs/:id', {
        id: '@_id'
    }, {
        update: {
            method: 'PUT'
        }
    });
};

