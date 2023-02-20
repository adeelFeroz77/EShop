package com.EShop.EShop.service;

import com.EShop.EShop.domain.Model;
import com.EShop.EShop.dto.ModelDto;
import com.EShop.EShop.exception.RecordAlreadyExistException;
import com.EShop.EShop.exception.RecordNotFoundException;
import com.EShop.EShop.repository.ModelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModelService{
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    public ModelService(ModelRepository modelRepository, ModelMapper modelMapper){
        this.modelRepository =modelRepository;
        this.modelMapper=modelMapper;
    }

    public List<ModelDto> getAllModels(){
        List<Model> models= modelRepository.findAllByIsActive(Boolean.TRUE);
        return models.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ModelDto getById(Long id){
        return toDto(findById(id));
    }

    public ModelDto saveModel(ModelDto modelDto){
        Model model = toDo(modelDto);
        Optional<Model> m = modelRepository.findByName(model.getName());
        if(m.isEmpty()){
            return toDto(modelRepository.save(model));
        } else if (!m.get().getIsActive()) {
            m.get().setIsActive(Boolean.TRUE);
            return toDto(modelRepository.save(m.get()));
        }
        throw new RecordAlreadyExistException("Record Already Exists!");
    }

    public ModelDto updateModel(Long id,ModelDto modelDto){
        Model model = toDo(modelDto);
        Model c = findById(id);
        c.setIsActive(model.getIsActive());
        c.setName((model.getName()));
        return toDto(modelRepository.save(c));
    }

    public ModelDto updateByField(Long id, Map<String, Object> fields){
        Model m = findById(id);
        fields.forEach((key,value)->{
            Field field = ReflectionUtils.findField(Model.class,key);
            field.setAccessible(true);
            ReflectionUtils.setField(field,m,value);
        });
        return toDto(modelRepository.save(m));
    }

    public String deleteById(Long id){
        Model c = findById(id);
        c.setIsActive(Boolean.FALSE);
        modelRepository.save(c);
        return String.format("Model With ID:%d Deleted Successfully",id);
    }

    private Model findById(Long id){
        return modelRepository.findById(id).orElseThrow(
                ()->new RecordNotFoundException(String.format("Record not found on id:%d",id))
        );
    }


    public Model toDo(ModelDto modelDto){
        return modelMapper.map(modelDto,Model.class);
    }

    public ModelDto toDto(Model model){
        return modelMapper.map(model,ModelDto.class);
    }
}
