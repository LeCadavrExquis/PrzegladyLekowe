package pl.knab.Przeglad.Lekowy.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TemplateService {

    @Autowired
    private TemplateRepository templateRepository;

    public List<TemplateBasicInfo> getAllTemplatesBasicInfo(){          
        return templateRepository.findAllBy();
    }

    public TemplateEntity getById(String id){
        TemplateEntity template = templateRepository.findById(id).orElse(null);
        return template;
    }
}
