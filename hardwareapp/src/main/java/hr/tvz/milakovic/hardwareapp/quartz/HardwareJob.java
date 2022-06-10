package hr.tvz.milakovic.hardwareapp.quartz;

import hr.tvz.milakovic.hardwareapp.DTO.HardwareDTO;
import hr.tvz.milakovic.hardwareapp.service.HardwareService;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class HardwareJob extends QuartzJobBean {

    private final Logger log = LoggerFactory.getLogger(HardwareJob.class);

    private final HardwareService hardwareService;

    public HardwareJob(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context){
        final List<HardwareDTO> hardwareList = hardwareService.findAll();

        if(!hardwareList.isEmpty()){
            log.info("Ovo su trenutno dostupni hardveri");
            log.info("------------------------------");
            hardwareList.forEach(
                    v -> log.info(v.toString())
            );
            log.info("------------------------------");
        } else {
            log.info("Trenutno nema hardvera");
        }
    }
}
