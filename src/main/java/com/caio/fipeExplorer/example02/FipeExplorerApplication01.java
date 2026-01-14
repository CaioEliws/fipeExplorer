package com.caio.fipeExplorer.example02;

import com.caio.fipeExplorer.example02.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FipeExplorerApplication01 implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FipeExplorerApplication01.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal();
        principal.showMenu();
    }
}
