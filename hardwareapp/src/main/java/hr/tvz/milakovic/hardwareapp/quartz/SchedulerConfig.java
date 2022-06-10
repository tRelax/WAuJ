package hr.tvz.milakovic.hardwareapp.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@Configuration
public class SchedulerConfig {


    @Bean
    public JobDetail hardwareJobDetail() {
        return JobBuilder.newJob(HardwareJob.class)
                .withIdentity("hardwareJob")
                .storeDurably()
                .build();
    }

    @Bean
    public CronTrigger hardwareJobTrigger() {
        return TriggerBuilder.newTrigger().forJob(hardwareJobDetail())
                .withIdentity("hardwareTriggerLiveExercise")
                .withSchedule(cronSchedule("0 55 23 ? * MON-FRI"))
                .build();
    }
    //  0   0   12  ?            *     MON-FRI
    //sec min hours day_of_month month day_of_week

    @Bean
    public Trigger objavaJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever();
        return TriggerBuilder.newTrigger()
                .forJob(hardwareJobDetail())
                .withIdentity("hardwareTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
