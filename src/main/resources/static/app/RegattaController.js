"use strict";

angular
    .module('jregatta')
    .controller('RegattaController', RegattaController);

function RegattaController($scope, $location, RegattaService, $mdToast) {

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
            field: 'name',
            enableCellEdit: true
        }, {
            field: 'startDate',
            displayName: 'Von',
            enableCellEdit: true,
            type: 'date',
            cellFilter: 'date:\'dd.MM.yyyy\''
        }, {
            field: 'endDate',
            displayName: 'Bis',
            enableCellEdit: true,
            type: 'date',
            cellFilter: 'date:\'dd.MM.yyyy\''
        }, {
            field: 'buoyages',
            displayName: 'Wettfahrten',
            enableCellEdit: true,
            type: 'number'
        }, {
            field: 'id',
            displayName: '',
            cellTemplate : '<div id="row.entity.id">'
                        + '<md-button ng-click="grid.appScope.goSkippers(row.entity.id)" class="md-primary" style="margin:0px, padding:0px">Teilnehmer</md-button>'
                        + '<md-button ng-click="grid.appScope.goRaces(row.entity.id)" class="md-primary" style="margin:0px, padding:0px">Wettfahrten</md-button>'
                        + '<md-button ng-click="grid.appScope.goResults(row.entity.id)" class="md-primary" style="margin:0px, padding:0px">Ergebnis</md-button>'
                        + '</div>',
            enableCellEdit: false,
            minWidth: 350
        }
    ];

    $scope.gridOptions.data = 'regattas';
    $scope.regattas = RegattaService.query();

    $scope.goSkippers = function(path) {
        $location.path("/regattas/"  + path + "/skippers");
        console.log("url: " + "/regattas/"  + path + "/skippers");
    };

    $scope.goRaces = function(path) {
        $location.path("/regattas/"  + path + "/races");
        console.log("url: " + "/regattas/"  + path + "/races");
    };

    $scope.goResults = function(path) {
        $location.path("/regattas/"  + path + "/results");
        console.log("url: " + "/regattas/"  + path + "/results");
    };

    $scope.gridOptions.onRegisterApi = function (gridApi) {
        $scope.gridApi = gridApi;
        gridApi.edit.on.afterCellEdit($scope, function (rowEntity, colDef, newValue, oldValue) {
            console.log('Edited (#'
                + rowEntity.id + '), Column: ('
                + colDef.name + ') New Value: ('
                + newValue + ') Old Value: ('
                + oldValue + ")");
            RegattaService.update({id: rowEntity.id}, rowEntity);
            $scope.$apply();
        });
    };

    $scope.newRegatta = function () {
        console.log("newRegatta()");
        RegattaService.save(
            {name: "Regatta-" + ($scope.gridApi.core.getVisibleRows().length + 1), 
            shortName: "R" + ($scope.gridApi.core.getVisibleRows().length + 1)},
            function (savedRegatta, headers) {
                //success callback
                console.log("success: " + JSON.stringify(savedRegatta, null, 4));
                $scope.showSuccessToast('Neue Regatta wurde hinzugefügt.');
                $scope.regattas.push(savedRegatta);
            },
            function (err) {
                // error callback
                console.log("error: " + JSON.stringify(err, null, 4));
                $scope.showErrorToast('Fehler: Es konnte keine neue Regatta hinzugefügt werden!');
            });
    };

}
