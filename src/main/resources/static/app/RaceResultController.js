"use strict";

var resultCounter = 0;

angular
    .module('jregatta')
    .controller('RaceResultController', RaceResultController);

function RaceResultController($q, $scope, $routeParams, $location, uiGridConstants, RaceService, RaceResultService, SkipperService, RegattaService, $mdToast) {

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
        $scope.gridOptions.exporterPdfHeader = {text: $scope.race.number + ". Wettfahrt - Ergebnis (vorläufig)", style: 'headerStyle', alignment: 'center'};
        console.log("$scope.skippers: " + JSON.stringify($scope.skippers, null, 4));
        console.log("$scope.regatta: " + JSON.stringify($scope.regatta, null, 4));
        console.log("$scope.race: " + JSON.stringify($scope.race, null, 4));

    });

    $scope.setHeaderText = function(isOffiziell) {
        if (isOffiziell) {
            $scope.gridOptions.exporterPdfHeader = {text: $scope.race.number + ". Wettfahrt - Ergebnis", style: 'headerStyle', alignment: 'center'};
        } else {
            $scope.gridOptions.exporterPdfHeader = {text: $scope.race.number + ". Wettfahrt - Ergebnis (vorläufig)", style: 'headerStyle', alignment: 'center'};
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
        exporterPdfTableStyle: {margin: [50, 60, 0, 0]},
        exporterPdfTableHeaderStyle: {fontSize: 10, bold: true, italics: true, color: 'red'},
        exporterPdfHeader: {text: $scope.race.number + ". Wettfahrt - Ergebnis (vorläufig)", style: 'headerStyle', alignment: 'center'},
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
        exporterPdfOrientation: 'landscape',
        exporterPdfPageSize: 'A4',
        exporterPdfMaxGridWidth: 600,
        exporterCsvLinkElement: angular.element(document.querySelectorAll(".custom-csv-link-location")),
        onRegisterApi: function (gridApi) {
            $scope.gridApi = gridApi;
        }
    };

    $scope.gridOptions.columnDefs = [{
            field: 'skipper.ageGroup',
            displayName: 'Altersklasse',
            enableCellEdit: false,
            sort: {
                direction: uiGridConstants.ASC,
                ignoreSort: true,
                priority: 0
            }
        }, {
            field: 'skipper.sailNumber',
            displayName: 'Vorname',
            enableCellEdit: false
        }, {
            field: 'skipper.firstName',
            displayName: 'Vorname',
            enableCellEdit: false
        }, {
            field: 'skipper.lastName',
            displayName: 'Nachname',
            enableCellEdit: false
        }, {
            field: 'skipper.club.shortName',
            displayName: 'Verein',
            enableCellEdit: false
        }, {
            field: 'placement',
            displayName: 'Zieleinlauf',
            enableCellEdit: true,
            type: 'number',
            sort: {
                direction: uiGridConstants.ASC,
                ignoreSort: true,
                priority: 1
            }
        }, {
            field: 'points',
            displayName: 'Punkte',
            type: 'number',
            enableCellEdit: true
        }, {
            field: 'result',
            displayName: 'Platz',
            type: 'number',
            enableCellEdit: true
        }];

    $scope.gridOptions.data = 'raceResults';
    $scope.raceResults = RaceResultService.query({regattaId: $routeParams.regattaId}, {raceId: $routeParams.raceId});

    $scope.goCertificates = function() {
        $location.path("/regattas/" + $routeParams.regattaId + "/certificates");
        console.log("url: " + "/regattas/" + $routeParams.regattaId + "/certificates");
    };

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

    $scope.toggleOfficially = function(data) {
        $scope.setHeaderText(data);
    };
}
