package com.example.demo;

import com.example.demo.pick.Pick;
import com.example.demo.user.Picker;
import com.example.demo.user.PickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final PickerRepository pickerRepository;

    public DemoApplication(PickerRepository pickerRepository) {
        this.pickerRepository = pickerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        pickerRepository.save(Picker.builder().name("Kum").build());
        pickerRepository.save(Picker.builder().name("Kim").build());
        pickerRepository.save(Picker.builder().name("Lee").build());
        pickerRepository.save(Picker.builder().name("Park").build());
    }
}
