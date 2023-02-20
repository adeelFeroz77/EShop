package com.EShop.EShop.controller;

import com.EShop.EShop.domain.Model;
import com.EShop.EShop.dto.ModelDto;
import com.EShop.EShop.service.ModelService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ModelController {
    private final ModelService modelService;

    public ModelController(ModelService modelService){
        this.modelService = modelService;
    }

    @GetMapping("/model/{id}")
    private ResponseEntity<ModelDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(modelService.getById(id));
    }

    @GetMapping("/model")
    private ResponseEntity<List<ModelDto>> getAllModels(){
        return ResponseEntity.ok(modelService.getAllModels());
    }

    @PostMapping("/model")
    private ResponseEntity<ModelDto> saveModel(@Valid @RequestBody ModelDto modelDto){
        return ResponseEntity.ok(modelService.saveModel(modelDto));
    }

//    @PutMapping("/model/{id}")
//    private ResponseEntity<ModelDto> updateModel(@PathVariable Long id,@Valid @RequestBody ModelDto modelDto){
//        return ResponseEntity.ok(modelService.updateModel(id,modelDto));
//    }
    @PatchMapping("/model/{id}")
    private ResponseEntity<ModelDto> updateModelByFields(@PathVariable Long id, @RequestBody Map<String,Object> fields){
        return ResponseEntity.ok(modelService.updateByField(id,fields));
    }


    @DeleteMapping("/model/{id}")
    private ResponseEntity<String> deleteModel(@PathVariable Long id){
        return ResponseEntity.ok(modelService.deleteById(id));
    }
}
