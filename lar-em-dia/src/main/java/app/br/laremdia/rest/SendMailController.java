package app.br.laremdia.rest;

import app.br.laremdia.model.dto.LoginClienteDTO;
import app.br.laremdia.model.dto.LoginProfissionalDTO;
import app.br.laremdia.service.GerenciarClienteService;
import app.br.laremdia.service.GerenciarProfissionalService;
import app.br.laremdia.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/mail")
@RequiredArgsConstructor
public class SendMailController {

    @Autowired
    private MailService mailService;

    private final GerenciarClienteService gerenciarClienteService;

    private final GerenciarProfissionalService gerenciarProfissionalService;

    @GetMapping()
    public String index(){
        return "send_mail_view";
    }

    @PostMapping("/{mail}/send")
    public void sendMail(@PathVariable("mail") String mail){
        LoginClienteDTO loginClienteDTO  = gerenciarClienteService.consultarEmail(mail);
        String name = loginClienteDTO.getNome();
        String senha = loginClienteDTO.getSenha();
        String email = loginClienteDTO.getEmail();
        String message = "\n\nPrezado(a) Cliente " + name + "\n\n\nSua senha é " + senha +  "\n\nSugerimos fazer o login e alterar a mesma." + "\n\n\n\nAtenciosamente," + "\n\nLar Em Dia";
        mailService.sendMail("raulfsantiago@gmail.com", email, "Senha do login", message);
    }

    @PostMapping("/{mail}/sendprof")
    public void sendMailProf(@PathVariable("mail") String mail){
        LoginProfissionalDTO loginProfissionalDTO  = gerenciarProfissionalService.consultarEmail(mail);
        String name = loginProfissionalDTO.getNome();
        String senha = loginProfissionalDTO.getSenha();
        String email = loginProfissionalDTO.getEmail();
        String message = "\n\nPrezado(a) Profissional " + name + "\n\n\nSua senha é " + senha +  "\n\nSugerimos fazer o login e alterar a mesma." + "\n\n\n\nAtenciosamente," + "\n\nLar Em Dia";
        mailService.sendMail("raulfsantiago@gmail.com", email, "Senha do login", message);
    }




}
