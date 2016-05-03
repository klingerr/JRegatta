"use strict";

var regattaCounter = 0;

angular
    .module('jregatta')
    .controller('RegattaController', RegattaController);

function RegattaController($scope, $location, RegattaService, $mdToast) {
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

    $scope.gridOptions.columnDefs = [{
            field: 'id',
//		cellTemplate : '<div><button class="btn btn-primary" xng-click="getExternalScopes().onClick(row.entity.fullName)">Click Here</button></div>',
            cellTemplate : '<md-button ng-click="grid.appScope.goSkipper(row.entity.id)" class="md-raised">Teilnehmer</md-button>',
            enableCellEdit: false
        }, {
            field: 'name',
            enableCellEdit: true
        }, {
            field: 'shortName',
            displayName: 'Kurzname',
            enableCellEdit: true
        }, {
            field: 'buoyages',
            displayName: 'Bojen',
            enableCellEdit: true,
            type: 'number'
        }];

    $scope.gridOptions.data = 'regattas';
    $scope.regattas = RegattaService.query();

    $scope.goSkipper = function(path) {
        console.log("path: " + path);
        $location.path("/regattas/"  + path + "/skippers");
        console.log("url: " + "/regattas/"  + path + "/skippers");
    };

    $scope.msg = {}; // Message Area for Debug Info

    $scope.gridOptions.onRegisterApi = function (gridApi) {
        $scope.gridApi = gridApi;
        gridApi.edit.on.afterCellEdit($scope, function (rowEntity, colDef, newValue, oldValue) {
            $scope.msg.lastCellEdited = 'Edited (#'
                + rowEntity.id + '), Column: ('
                + colDef.name + ') New Value: ('
                + newValue + ') Old Value: ('
                + oldValue + ")";
            RegattaService.update({id: rowEntity.id}, rowEntity);
            $scope.$apply();
        });
    };

    $scope.newRegatta = function () {
        console.log("newRegatta()");
        RegattaService.save(
            {name: "Regatta-" + ++regattaCounter, shortName: "R" + regattaCounter},
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


    $scope.showMoreColumns = function() {
        console.log("$scope.gridOptions.columnDefs.length: " + $scope.gridOptions.columnDefs.length);

        if ($scope.gridOptions.columnDefs.length == GRID_DEFAULT_COLUMN_COUNT) {
            $scope.gridOptions.columnDefs.push({
                field: 'startDate',
                enableCellEdit: true,
                type: 'date',
                cellFilter: 'date:\'dd.MM.yyyy\''
            })
        } else {
            var deleteColumnCount = $scope.gridOptions.columnDefs.length - GRID_DEFAULT_COLUMN_COUNT;
            console.log("Trying to delete columns: " + deleteColumnCount);
            $scope.gridOptions.columnDefs.splice(GRID_DEFAULT_COLUMN_COUNT, deleteColumnCount);
        }
    }
}
