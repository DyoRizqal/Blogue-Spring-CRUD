angular.module('aplikasiBlogue', ['ngRoute'])
	.config(function($routeProvider){
		$routeProvider
			.when('/',{
				'templateUrl': 'views/home.html',
				'controller': 'blogueController'
			});
	});
