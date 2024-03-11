package com.hierarchy.project.controllers;

import com.hierarchy.project.enums.HierarchyEnum;
import com.hierarchy.project.enums.ScorePasswordEnum;
import com.hierarchy.project.models.StaffModel;
import com.hierarchy.project.repository.StaffRepository;
import com.hierarchy.project.util.CurrentDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/hierarchy")
public class StaffController {
    @Autowired
    private StaffRepository staffRepository;
    private List<HierarchyEnum> listTypesHierarchy;

    PasswordStrengthController passwordStrength = new PasswordStrengthController();

    @PostMapping("/staff")
    public StaffModel createStaffHierarchy(@RequestBody StaffModel staffModel) {
        return staffRepository.save(passwordStrength.passwordStrength(staffModel.getPassword(), staffModel));
    }

    @GetMapping("/staff/all")
    public List<StaffModel> getAllStaffs(){
        return staffRepository.findAll();
    }

    @GetMapping("/staff/{id}")
    public StaffModel getStaffById(@PathVariable Long id){
        return staffRepository.getStaff(id);
    }

    @DeleteMapping("/staff/{id}")
    public ResponseEntity<String> deleteStaffById(@PathVariable Long id) {
        if (!staffRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        staffRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public List<HierarchyEnum> getAllHierarchyTypes(){
        return Arrays.asList(HierarchyEnum.values());
    }

    @PutMapping("/staff/{id}")
    public ResponseEntity<StaffModel> updateStaffById(@PathVariable Long id, @RequestBody StaffModel staffModel) {
        StaffModel existingStaff = staffRepository.findById(id)
                .orElse(null);
        if (existingStaff == null) {
            return ResponseEntity.notFound().build();
        }
        existingStaff.setName(staffModel.getName());
        existingStaff.setPassword(staffModel.getPassword());
        existingStaff.setHierarchy(staffModel.getHierarchy());
        CurrentDateTime getCurrentDateTime = new CurrentDateTime();
        existingStaff.setUpdatedAt(getCurrentDateTime.getCurrentDateTime());//atualizando o updatedAt
        StaffModel updatedStaff = staffRepository.save(passwordStrength.passwordStrength(existingStaff.getPassword(), existingStaff));
        return ResponseEntity.ok(updatedStaff);
    }

}
