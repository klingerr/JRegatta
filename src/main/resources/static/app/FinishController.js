"use strict";

var finishCounter = 0;

angular
    .module('jregatta')
    .controller('FinishController', FinishController);

function FinishController($q, $scope, $routeParams, uiGridConstants, RaceService, RaceResultService, SkipperService, RegattaService, $mdToast) {

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
    $scope.skippers = SkipperService.query({regattaId: $routeParams.regattaId});
    $scope.regatta = RegattaService.get({id: $routeParams.regattaId});
    $scope.race = RaceService.get({regattaId: $routeParams.regattaId, raceId: $routeParams.raceId});
    $q.all([
        $scope.skippers.$promise,
        $scope.regatta.$promise,
        $scope.race.$promise
    ]).then(function () {
        //CODE AFTER RESOURCES ARE LOADED 
        $scope.gridOptions.exporterPdfHeader = {text: $scope.race.number + ". Wettfahrt - Zieldurchgang (vorläufig)", style: 'headerStyle', alignment: 'center'};
        console.log("$scope.skippers: " + JSON.stringify($scope.skippers, null, 4));
        console.log("$scope.regatta: " + JSON.stringify($scope.regatta, null, 4));
        console.log("$scope.race: " + JSON.stringify($scope.race, null, 4));

    });

    $scope.setHeaderText = function(isOffiziell) {
        if (isOffiziell) {
            $scope.gridOptions.exporterPdfHeader = {text: $scope.race.number + ". Wettfahrt - Zieldurchgang", style: 'headerStyle', alignment: 'center'};
        } else {
            $scope.gridOptions.exporterPdfHeader = {text: $scope.race.number + ". Wettfahrt - Zieldurchgang (vorläufig)", style: 'headerStyle', alignment: 'center'};
        }
    };

    $scope.gridOptions = {
        enableFiltering: false,
        enableCellEditOnFocus: true,
        enablePaginationControls: false,
        enableSorting: true,
        enableRowSelection: true,
        enableRowHeaderSelection: false,
        enableColumnResizing: false,
        rowHeight: 45,
        enableGridMenu: true,
        enableSelectAll: true,
        exporterCsvFilename: 'teilnehmer.csv',
        exporterMenuPdf: true,
        exporterMenuCsv: false,
        exporterMenuAllData: false,
        expandableRowHeaderWidth: 60,
        exporterPdfDefaultStyle: {fontSize: 9},
        exporterPdfTableStyle: {margin: [100, 60, 0, 0]},
        exporterPdfTableHeaderStyle: {fontSize: 10, bold: true, italics: true, color: 'red'},
        exporterPdfHeader: {text: $scope.race.number + ". Wettfahrt - Zieldurchgang (vorläufig)", style: 'headerStyle', alignment: 'center'},
        exporterPdfFooter: function (currentPage, pageCount) {
//            return {text: currentPage.toString() + ' of ' + pageCount.toString(), style: 'footerStyle'};
            return {text: 'Org.Büro: _____________________      Wettfahrtleiter: _____________________      Schiedsrichter: _____________________\r\n\r\n' 
                        + $scope.regatta.name + ' - ' +  moment(new Date()).format('DD.MM.YYYY HH:mm'), style: 'footerStyle'};
        },
        exporterPdfCustomFormatter: function (docDefinition) {
            docDefinition.styles.headerStyle = {fontSize: 22, bold: true, margin: [0, 14, 0, 0]};
            docDefinition.styles.footerStyle = {fontSize: 10, bold: true, alignment: 'center'};
            return docDefinition;
        },
        exporterPdfOrientation: 'portrait',
        exporterPdfPageSize: 'A4',
        exporterPdfMaxGridWidth: 300,
        exporterCsvLinkElement: angular.element(document.querySelectorAll(".custom-csv-link-location")),
        onRegisterApi: function (gridApi) {
            $scope.gridApi = gridApi;
        }
    };

    $scope.gridOptions.columnDefs = [
        {
            field: 'placement',
            displayName: 'Platz',
            enableCellEdit: true,
            sort: {
                direction: uiGridConstants.ASC,
                ignoreSort: true,
                priority: 0
            }
        }, {
            field: 'skipper.sailNumber',
            displayName: 'Segelnummer',
            enableCellEdit: true,
            editableCellTemplate: 'ui-grid/dropdownEditor',
            editDropdownIdLabel: 'sailNumber',
            editDropdownValueLabel: 'sailNumber',
            editDropdownOptionsArray: $scope.skippers,
            cellFilter: 'griddropdown:this',
        }];

    $scope.gridOptions.data = 'finishs';
    $scope.finishs = RaceResultService.query({regattaId: $routeParams.regattaId, raceId: $routeParams.raceId}, {raceId: $routeParams.raceId});

    $scope.gridOptions.onRegisterApi = function (gridApi) {
        $scope.gridApi = gridApi;
        gridApi.edit.on.afterCellEdit($scope, function (rowEntity, colDef, newValue, oldValue) {
            console.log('Edited (#'
                + rowEntity.id + '), Column: ('
                + colDef.name + ') New Value: ('
                + newValue + ') Old Value: ('
                + oldValue + ")");
            RaceResultService.update({regattaId: $routeParams.regattaId, raceId: $routeParams.raceId, resultId: rowEntity.id}, rowEntity);
            $scope.$apply();
        });
    };

    $scope.newFinish = function () {
        console.log("newFinish(): " + $routeParams.regattaId);
        RaceResultService.save(
            {placement: "" + ++finishCounter,
            regattaId: $routeParams.regattaId,
            raceId: $routeParams.raceId, 
            race: {id: $routeParams.raceId}},
            function (savedFinish, headers) {
                //success callback
                console.log("success: " + JSON.stringify(savedFinish, null, 4));
                $scope.showSuccessToast('Neuer Zieleinlauf wurde hinzugefügt.');
                $scope.finishs.push(savedFinish);
            },
            function (err) {
                // error callback
                console.log("error: " + JSON.stringify(err, null, 4));
                $scope.showErrorToast('Fehler: Es konnte kein neuer Zieleinlauf hinzugefügt werden!');
            });
    };

    $scope.toggleOfficially = function(data) {
        $scope.setHeaderText(data);
    };
}
