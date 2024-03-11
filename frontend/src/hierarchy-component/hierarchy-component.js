angular.module('staffHierarchy').component('hierarchyComponent', {
  templateUrl: 'hierarchy-component/hierarchy-component.html',
  controller: HierarchyComponentController
});

HierarchyComponentController.$inject = ['backendService', '$location'];

function HierarchyComponentController(backendService, $location) {
  var $ctrl = this;
  $ctrl.$location = $location;
  $ctrl.hierarchyTypes = [];
  $ctrl.allStaffs = [];

  $ctrl.$onInit = function() {
    getAllHierarchyType();
    getAllStaffs();
    clearObjectStaff();
  };

  getAllHierarchyType = function (){
    backendService.getAllHierarchyType().then(function(response) {
      $ctrl.hierarchyTypes = response.data;
      console.log('qeqweqwe', $ctrl.hierarchyTypes)
    });
  }

  $ctrl.saveStaff = function (){
    if($ctrl.objStaffToSave.hierarchy == ''){
      alert('É necessário selecionar uma hierarquia')
    }else{
      backendService.createStaff($ctrl.objStaffToSave).then(response => {
        if(response.status == 200){
          alert("Usuário cadastrado com sucesso")//daria pra implementar um toast
          getAllStaffs();
        }else{
          alert("erro ao cadastrar usuário")
        }
        clearObjectStaff();
      })
    }
  }

  getAllStaffs = function (){
    backendService.getAllStaffs().then(function(response) {
      $ctrl.allStaffs = response.data;
      console.log('qeqweqwe', $ctrl.allStaffs)
    });
  }

  $ctrl.deleteStaff = function (id){
    backendService.deleteStaff(id).then(response => {
      if(response.status == 200){
        alert("Usuário deletado com sucesso")//daria pra implementar um toast
        getAllStaffs();
      }else{
        alert("erro ao deletar usuário")
      }
    })
  }

  $ctrl.goToEditPage = function() {
    $ctrl.$location.path('/edit'); // Substitua '/edit' com o caminho da sua página de edição
  };

  clearObjectStaff = function (){
    $ctrl.objStaffToSave = {
      name: '',
      password: '',
      hierarchy: ''
    }
  }

  clearObjectStaffUpdate = function (){
    $ctrl.objStaffToUpdate = {
      nameStaff: '',
      passwordStaff: '',
      hierarchyStaff: '',
    }
  }

  $ctrl.openPopupEdit = function(staff) {
    document.getElementById("popup").style.display = "block";
    populatePopupEdit(staff)
  }

  // Function to close the popup
  $ctrl.closePopupEdit = function() {
    document.getElementById("popup").style.display = "none";
  }

  populatePopupEdit = function (staff){
    clearObjectStaffUpdate()
    $ctrl.objStaffToUpdate.name = staff.name;
    $ctrl.objStaffToUpdate.password = '';
    $ctrl.objStaffToUpdate.hierarchy = staff.hierarchy;
    $ctrl.idStaff = staff.id
    console.log('olha o staff pra editar', staff)
  }

  $ctrl.updateStaffProps = function (){
    backendService.updateStaff($ctrl.idStaff, $ctrl.objStaffToUpdate).then(response => {
      if(response.status == 200){
        alert("Usuário alterado com sucesso")//daria pra implementar um toast
        $ctrl.closePopupEdit();
        clearObjectStaffUpdate();
        getAllStaffs();
      }else{
        alert("erro ao alterar usuário")
      }
      clearObjectStaff();
    })
  }
  
  
  
}
