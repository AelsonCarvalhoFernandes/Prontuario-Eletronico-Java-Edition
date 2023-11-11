package com.pi.ProntuarioEletronico.services.DataServices;

import com.pi.ProntuarioEletronico.models.prontuario.FileModel;
import com.pi.ProntuarioEletronico.models.prontuario.LaudoModel;
import com.pi.ProntuarioEletronico.models.user.typeUsers.PacientModel;
import com.pi.ProntuarioEletronico.repositories.laudoRepository.IFileRepository;
import com.pi.ProntuarioEletronico.repositories.laudoRepository.ILaudoRepository;
import com.pi.ProntuarioEletronico.resources.dtos.LaudoDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaudoDataService {

    @Autowired
    private ILaudoRepository laudoRepository;
    @Autowired
    private IFileRepository fileRepository;
    @Autowired
    private PacientDataService pacientDataService;
    @Autowired
    private DoctorDataService doctorDataService;

    /*
    * Crud dos laudos
    * */

    public LaudoModel findById(Long id){
        try{

            Optional<LaudoModel> laudo = laudoRepository.findById(id);

            if(laudo.isPresent()){
                return laudo.get();
            }

            return null;

        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public List<LaudoModel> findByPacient(PacientModel pacient){
        try{
            return laudoRepository.findByPacient(pacient);
        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public LaudoModel create(LaudoDto dto){
        try{

            LaudoModel laudo = new LaudoModel();
            BeanUtils.copyProperties(dto, laudo);

            laudo.setPacient(pacientDataService.findByCns(dto.cnsPacient()));
            laudo.setDoctor(doctorDataService.findByCrm(dto.crmMedico()));

            return laudoRepository.save(laudo);

        }catch (Exception ex){

            System.out.println("Error: " + ex.getMessage());
            return null;

        }
    }

    public FileModel createFile(Long laudoId, String path){
        FileModel file = new FileModel();

        file.setFileurl(path);
        file.setLaudo(this.findById(laudoId));

        return fileRepository.save(file);
    }

    public LaudoModel update(LaudoModel model){
        try{

            LaudoModel laudo = this.findById(model.getId());

            laudo.setDescription(model.getDescription());
            laudo.setNote(model.getNote());

            return laudo;

        }catch (Exception ex){

            System.out.println("Error: " + ex.getMessage());
            return null;

        }
    }

    /*
    * Crud dos arquivos
    * */

}
