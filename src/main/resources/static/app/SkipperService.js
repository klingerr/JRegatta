"use strict";

angular
    .module('jregatta')
    .factory('SkipperService', SkipperService);

function SkipperService($resource) {
    return $resource('/skippers/:id', {
        id: '@_id'
    }, {
        update: {
            method: 'PUT'
        }
    });
};

