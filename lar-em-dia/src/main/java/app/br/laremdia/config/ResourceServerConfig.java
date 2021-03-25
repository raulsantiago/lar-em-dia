package app.br.laremdia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    // Gerenciamento de permissão de acesso aos usuário para as APIs
//     .permitAll() - permite a todos os usuário e não usuários, não necessita de autenticação
//     .hasAnyRole() - permite varios usuários especificando criados na classe SecurityConfig
//     .hasRole() - permite um usuário criados na classe SecurityConfig
//     API coringa aceita tudo depois do **  "/api/clientes/**"
//     .authenticated() - só para usuário autenticados.
//     .anyRequest().denyAll() - negae acesso a todas as outras sem configiração

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/loginprofissional/**").permitAll()
                .antMatchers("/gerenciarprofissional/**").authenticated()
                .anyRequest().denyAll();
        ;
    }
}