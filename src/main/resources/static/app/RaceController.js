"use strict";

angular
    .module('jregatta')
    .controller('RaceController', RaceController);

function RaceController($q, $scope, $location, $routeParams, RaceService, RegattaService, $mdToast) {
    const GRID_DEFAULT_COLUMN_COUNT = 4;

    $scope.showSuccessToast = function (message) {
        $mdToast.show($mdToast.simple()
            .content(message)
            .position('top right')
            .theme("success-toast"));
    };

    $scope.showErrorToast = function (message) {
        $mdToast.show($mdToast.simple()
            .content(message)
            .position('top right')
            .theme("error-toast"));
    };

 // get dropdown content before creating the grid
    $scope.regatta = RegattaService.get({id: $routeParams.regattaId});
    $q.all([
        $scope.regatta.$promise
    ]).then(function () {
        //CODE AFTER RESOURCES ARE LOADED 
        console.log("$scope.regatta: " + JSON.stringify($scope.regatta, null, 4));
    });
    
    
    $scope.gridOptions = {
        enableFiltering: false,
        enableCellEditOnFocus: true,
        enablePaginationControls: false,
        enableSorting: true,
        enableRowSelection: true,
        enableRowHeaderSelection: false,
        enableColumnResizing: false,
        rowHeight: 45
    };

    $scope.gridOptions.columnDefs = [
        {
            field: 'number',
            enableCellEdit: true,
            type: 'number'
        }, {
            field: 'startTime',
            displayName: 'Start',
            enableCellEdit: true
        }, {
            field: 'endTime',
            displayName: 'Ende',
            enableCellEdit: true,
        }, {
            field: 'id',
            displayName: '',
            cellTemplate : '<md-button ng-click="grid.appScope.goFinish(row.entity.id)" class="md-primary" style="margin:0px, padding:0px">Zieldurchgang</md-button>'
                        + '<md-button ng-click="grid.appScope.goRaceResults(row.entity.id)" class="md-primary" style="margin:0px, padding:0px">Ergebnis</md-button>',
            enableCellEdit: false,
            width: '**'
        }];

    $scope.gridOptions.data = 'races';
    $scope.races = RaceService.query({regattaId: $routeParams.regattaId});

    $scope.goFinish = function(raceId) {
        $location.path("/regattas/" + $routeParams.regattaId + "/races/" + raceId + "/finish");
        console.log("url: " + "/regattas/"  + $routeParams.regattaId + "/races/"+ raceId + "/finish");
    };

    $scope.goRaceResults = function(raceId) {
        $location.path("/regattas/" + $routeParams.regattaId + "/races/" + raceId + "/results");
        console.log("url: " + "/regattas/"  + $routeParams.regattaId + "/races/"+ raceId + "/results");
    };
    
    $scope.gridOptions.onRegisterApi = function (gridApi) {
        $scope.gridApi = gridApi;
        gridApi.edit.on.afterCellEdit($scope, function (rowEntity, colDef, newValue, oldValue) {
            console.log('Edited (#'
                + rowEntity.id + '), Column: ('
                + colDef.name + ') New Value: ('
                + newValue + ') Old Value: ('
                + oldValue + ")");
            RaceService.update({raceId: rowEntity.id, regattaId: $routeParams.regattaId}, rowEntity);
            $scope.$apply();
        });
    };

    $scope.newRace = function () {
        console.log("newRace()");
        RaceService.save(
            {number: ($scope.gridApi.core.getVisibleRows().length + 1),
             regattaId: $routeParams.regattaId,
             regatta: {id: $routeParams.regattaId}},
            function (savedRace, headers) {
                //success callback
                console.log("success: " + JSON.stringify(savedRace, null, 4));
                $scope.showSuccessToast('Neue Wettfahrt wurde hinzugefügt.');
                $scope.races.push(savedRace);
            },
            function (err) {
                // error callback
                console.log("error: " + JSON.stringify(err, null, 4));
                $scope.showErrorToast('Fehler: Es konnte keine neue Wettfahrt hinzugefügt werden!');
            });
    };

}
