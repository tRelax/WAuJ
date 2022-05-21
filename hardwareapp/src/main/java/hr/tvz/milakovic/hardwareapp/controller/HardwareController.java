package hr.tvz.milakovic.hardwareapp.controller;

import hr.tvz.milakovic.hardwareapp.command.HardwareCommand;
import hr.tvz.milakovic.hardwareapp.DTO.HardwareDTO;
import hr.tvz.milakovic.hardwareapp.service.HardwareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hardware")
@CrossOrigin(origins = "http://localhost:4200")
public class HardwareController {
    private final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @GetMapping()
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_DELETER"})
    public List<HardwareDTO> findAllHardware(){
        return hardwareService.findAll();
    }

//    @GetMapping(params = "code")
//    public Optional<HardwareDTO> findAllHardwareByCode(@RequestParam String code){
//        return hardwareService.findByCode(code);
//    }

    @GetMapping("/{code}")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_DELETER"})
    public ResponseEntity<HardwareDTO> findAllHardwareByCode(@PathVariable final String code){
        return hardwareService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity
                                .notFound()
                                .build()
                );
    }
    @GetMapping(params = "id")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_DELETER"})
    public ResponseEntity<HardwareDTO> findHardwareById(@RequestParam final Long id){

        return hardwareService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity
                                .notFound()
                                .build()
                );
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<HardwareDTO> save(@Valid @RequestBody final HardwareCommand command){
        return hardwareService.save(command)
                .map(
                        hDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(hDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<HardwareDTO> update(@PathVariable Long id, @Valid @RequestBody final HardwareCommand command){
        return hardwareService.update(id, command)
                .map(ResponseEntity::ok
                )
                .orElseGet(
                        () -> ResponseEntity
                                .notFound()
                                .build()
                );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    @Secured({"ROLE_ADMIN", "ROLE_DELETER"})
    public void deleteHardwareByCode(@PathVariable String code){
        hardwareService.deleteHardwareByCode(code);
    }
}
