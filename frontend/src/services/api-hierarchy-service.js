angular.module('staffHierarchy', []).service('backendService', function($http) {
    var baseUrl = 'http://localhost:8080/api/hierarchy';

    // Método para pegar todos os tipos de hierarquia cadastrados
    this.getAllHierarchyType = function() {
        var urlEndpoint = baseUrl + '/all';
        return $http.get(urlEndpoint);
    };

    // Método para pegar todos os funcionários
    this.getAllStaffs = function() {
        var urlEndpoint = baseUrl + '/staff/all';
        return $http.get(urlEndpoint);
    };

    // Método para pegar um funcionário específico
    this.getStaffById = function(id) {
        var urlEndpoint = baseUrl + '/staff/' + id;
        return $http.get(urlEndpoint);
    };

    // Método para cadastrar um funcionário
    this.createStaff = function(data) {
        var urlEndpoint = baseUrl + '/staff';
        return $http.post(urlEndpoint, data);
    };

    // Método para atualizar um funcionário
    this.updateStaff = function(id, data) {
        var urlEndpoint = baseUrl + '/staff/' + id;
        return $http.put(urlEndpoint, data);
    };

    // Método para deletar um funcionário específico
    this.deleteStaff = function(id) {
        var urlEndpoint = baseUrl + '/staff/' + id;
        return $http.delete(urlEndpoint);
    };
});
