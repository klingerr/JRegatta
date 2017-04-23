"use strict";

angular
    .module('jregatta')
    .controller('FinishController', FinishController);

function FinishController($q, $scope, $routeParams, $location, RaceService, FinishService, SkipperService, SkipperRaceService, RegattaService, $mdToast) {

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

    $scope.goRaces = function() {
    	$location.path("/regattas/" + $routeParams.regattaId + "/races");
    };
    
    $scope.goResults = function() {
    	$location.path("/regattas/" + $routeParams.regattaId + "/races/" + $routeParams.raceId + "/results");
    };
    
    var removeByAttr = function(arr, attr, value) {
        var i = arr.length;
        while(i--){
           if( arr[i] 
               && arr[i].hasOwnProperty(attr) 
               && (arguments.length > 2 && arr[i][attr] === value ) ){ 
               return arr.splice(i,1)[0];
           }
        }
    };
    
    // get dropdown content before creating the grid
//    $scope.skippers = SkipperService.query({regattaId: $routeParams.regattaId});
    $scope.skippers = SkipperRaceService.query({regattaId: $routeParams.regattaId, raceId: $routeParams.raceId});
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
            displayName: 'Zieleinlauf',
            enableCellEdit: true
//            type: 'number'
//            enableSorting: false,
//            sortingAlgorithm: mySort
//            sort: {
//                direction: uiGridConstants.ASC,
//                ignoreSort: true,
//                priority: 0
//            }
        }, {
            field: 'skipper.sailNumber',
            displayName: 'Segelnummer',
            enableCellEdit: true,
            editableCellTemplate: 'ui-grid/dropdownEditor',
            editDropdownIdLabel: 'sailNumber',
            editDropdownValueLabel: 'sailNumber',
            editDropdownOptionsArray: $scope.skippers,
            cellFilter: 'griddropdown:this'
        }];

    $scope.gridOptions.data = 'finishs';
    $scope.finishs = FinishService.query({regattaId: $routeParams.regattaId, raceId: $routeParams.raceId}, {raceId: $routeParams.raceId});

    $scope.gridOptions.onRegisterApi = function (gridApi) {
        $scope.gridApi = gridApi;
        
        gridApi.edit.on.afterCellEdit($scope, function (rowEntity, colDef, newValue, oldValue) {
            console.log('Edited (#'
                + rowEntity.id + '), Column: ('
                + colDef.name + ') New Value: ('
                + newValue + ') Old Value: ('
                + oldValue + ")");
            console.log('rowEntity: ' + JSON.stringify(rowEntity));
            console.log('skippers: ' + JSON.stringify($scope.skippers));
            
            var skipper = removeByAttr($scope.skippers, 'sailNumber', newValue);
            
            console.log('skipper: ' + JSON.stringify(skipper));
            console.log('skippers: ' + JSON.stringify($scope.skippers));
            
            rowEntity.skipper = skipper;
            console.log('rowEntity: ' + JSON.stringify(rowEntity));
            
            if (newValue != oldValue) {
	            FinishService.update({regattaId: $routeParams.regattaId, raceId: $routeParams.raceId, resultId: rowEntity.id}, rowEntity,
	            function (savedFinish, headers) {
	                //success callback
	                console.log("success: " + JSON.stringify(savedFinish, null, 4));
	                $scope.showSuccessToast('Zieleinlauf wurde aktualisiert.');
//	                $scope.finishs.push(savedFinish);
	            },
	            function (err) {
	                // error callback
	                console.log("$scope.finishCounter: " + ($scope.gridApi.core.getVisibleRows().length + 1));
	                console.log("error: " + JSON.stringify(err, null, 4));
	                $scope.showErrorToast('Fehler: Zieleinlauf konnte nicht aktualsiert werden!');
	            });
	            $scope.$apply();
            }
        });
    };

    $scope.newFinish = function () {
        console.log("newFinish(): " + $routeParams.regattaId);
        FinishService.save(
            {placement: "" + ($scope.gridApi.core.getVisibleRows().length + 1),
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
                console.log("$scope.finishCounter: " + ($scope.gridApi.core.getVisibleRows().length + 1));
                console.log("error: " + JSON.stringify(err, null, 4));
                $scope.showErrorToast('Fehler: Es konnte kein neuer Zieleinlauf hinzugefügt werden!');
            });
    };

    $scope.toggleOfficially = function(data) {
        $scope.setHeaderText(data);
    };
    
}
