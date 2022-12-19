package com.example.demo;
import com.example.demo.ui.LoginView;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.spring.security.VaadinWebSecurityConfigurerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@SpringBootApplication
@Push
public class DemoApplication extends VaadinWebSecurityConfigurerAdapter implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() {
        return new InMemoryUserDetailsManager(
                User.withUsername("Luis")
                        .password("{noop}p")
                        .roles("ADMIN")
                        .build(),
                User.withUsername("Anghie")
                        .password("{noop}p")
                        .roles("ADMIN")
                        .build()
        );
    }

}
