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

    @PutMapping("/{code}")
    public ResponseEntity<HardwareDTO> update(@PathVariable String code, @Valid @RequestBody final HardwareCommand command){
        return hardwareService.update(code, command)
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

    @GetMapping(params = {"min", "max"})
    public List<HardwareDTO> findBetweenPrices(@RequestParam("min") final Double min, @RequestParam("max") final Double max){
        return hardwareService.findBetweenPrices(min, max);
    }
    @GetMapping(params = {"pattern"})
    public List<HardwareDTO> findWithString(@RequestParam("pattern") final String pattern){
        return hardwareService.findWithString(pattern);
    }
}
