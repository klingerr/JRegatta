"use strict";

var jregattaControllers = angular.module('jregattaControllers', ['ngToast']);


jregattaControllers.controller('RegattaListController', [ '$scope', 'RegattaService', function($scope, RegattaService, ngToast) {
	ngToast.create('a toast message...');
	
	$scope.gridOptions = {
		enableFiltering : false,
		enableCellEditOnFocus : true,
		enablePaginationControls : false,
		enableSorting : true,
		enableRowSelection : true,
		enableRowHeaderSelection : false,
		enableColumnResizing : false,
		rowHeight: 45,
	};

	$scope.gridOptions.columnDefs = [ {
		field : 'id',
//		cellTemplate : '<div><button class="btn btn-primary" xng-click="getExternalScopes().onClick(row.entity.fullName)">Click Here</button></div>',
		enableCellEdit : false
	}, {
		field : 'name',
		enableCellEdit : true
	}, {
		field : 'shortName',
		displayName : 'Kurzname',
		enableCellEdit : true
	}, {
		field : 'buoyages',
		displayName : 'Bojen',
		enableCellEdit : true,
		type : 'number'
	} ];

	$scope.gridOptions.data = 'regattas';
	$scope.regattas = RegattaService.query();
	
	$scope.msg = {}; // Message Area for Debug Info
	
	$scope.gridOptions.onRegisterApi = function(gridApi) {
		$scope.gridApi = gridApi;
		gridApi.edit.on.afterCellEdit($scope, function(rowEntity, colDef, newValue, oldValue) {
			$scope.msg.lastCellEdited = 'Edited (#'
					+ rowEntity.id + '), Column: ('
					+ colDef.name + ') New Value: ('
					+ newValue + ') Old Value: ('
					+ oldValue + ")";
			RegattaService.update({id : rowEntity.id}, rowEntity);
			$scope.$apply();
		});
	};
	
	$scope.newRegatta = function () {
		console.log("newRegatta()");
		RegattaService.save(
			{name: "Neue Regatta", shortName: "Neu"}, 
			function(savedRegatta, headers) {
		      //success callback
		      console.log("success: " + JSON.stringify(savedRegatta, null, 4));
		      ngToast.create('a toast message...');
		      $scope.regattas.push(savedRegatta);		      
		    },
		    function(err) {
		      // error callback
		      console.log("error: " + err);
		    });
	}
	
} ]);
