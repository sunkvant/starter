<!doctype html>
<html ng-app>
<head>
    <meta charset="utf-8">
</head>
<body>
<label>Введите имя:</label>
<input type="text" ng-model="name" placeholder="Введите имя">

<h1>Добро пожаловать {{name}}!</h1>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
</body>
</html>