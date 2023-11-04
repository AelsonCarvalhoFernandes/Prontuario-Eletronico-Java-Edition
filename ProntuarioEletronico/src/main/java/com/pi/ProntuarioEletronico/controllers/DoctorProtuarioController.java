package com.pi.ProntuarioEletronico.controllers;

import com.pi.ProntuarioEletronico.configurations.FileStorageProperties;
import com.pi.ProntuarioEletronico.models.prontuario.LaudoModel;
import com.pi.ProntuarioEletronico.resources.dtos.LaudoDto;
import com.pi.ProntuarioEletronico.services.DataServices.LaudoDataService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequestMapping("doctorProntuario")
public class DoctorProtuarioController {

    private final Path fileLocation;

    public DoctorProtuarioController(FileStorageProperties fileStorage){
        this.fileLocation = Paths.get(fileStorage.getUploadDir()).toAbsolutePath().normalize();
    }

    @Autowired
    private LaudoDataService laudoDataService;

    @GetMapping("createLaudo")
    public ModelAndView createLaudo(){
        ModelAndView mv = new ModelAndView("prontuario/CreateLaudo");
        return mv;
    }

    @PostMapping("createLaudo/saveLaudo")
    public ModelAndView saveLaudo(LaudoDto dto){
        System.out.println("Chegou aqui");

        LaudoModel laudo = laudoDataService.create(dto);

        return new ModelAndView("redirect:createLaudo");
    }

    @GetMapping("savefile/{id}")
    public ModelAndView upload(){
        ModelAndView mv = new ModelAndView("prontuario/SaveFile");

        return mv;
    }

    @PostMapping("savefile/{id}")
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile file, @PathVariable(name = "id") Long id){

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try{

            Path target = fileLocation.resolve(fileName);
            System.out.println(target.toString());
            file.transferTo(target);

            String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("file/")
                        .path(fileName)
                        .toUriString();

            laudoDataService.createFile(id, fileUrl);

            return new ModelAndView("redirect:index");

        }catch (IOException ex){
            return new ModelAndView("redirect:createLaudo");
        }
    }    

}
