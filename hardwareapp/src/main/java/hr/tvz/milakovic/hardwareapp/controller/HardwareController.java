package hr.tvz.milakovic.hardwareapp.controller;

import hr.tvz.milakovic.hardwareapp.command.HardwareCommand;
import hr.tvz.milakovic.hardwareapp.entity.HardwareDTO;
import hr.tvz.milakovic.hardwareapp.service.HardwareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hardware")
@CrossOrigin(origins = "http://localhost:4200")
public class HardwareController {
    private final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @GetMapping()
    public List<HardwareDTO> findAllHardware(){
        return hardwareService.findAll();
    }

//    @GetMapping(params = "code")
//    public Optional<HardwareDTO> findAllHardwareByCode(@RequestParam String code){
//        return hardwareService.findByCode(code);
//    }

    @GetMapping("/{code}")
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
    public void deleteHardwareByCode(@PathVariable String code){
        hardwareService.deleteHardwareByCode(code);
    }
}
