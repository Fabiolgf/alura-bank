package com.alura.alurabank.Controller;

import com.alura.alurabank.Controller.Form.ContaCorrenteForm;
import com.alura.alurabank.Controller.Form.CorrentistaForm;
import com.alura.alurabank.Dominio.ContaCorrente;
import com.alura.alurabank.Dominio.Correntista;
import com.alura.alurabank.Dominio.MovimentacaoDeConta;
import com.alura.alurabank.repositorio.RepositorioContasCorrente;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contas")
@AllArgsConstructor
public class ContaCorrenteController {

    private RepositorioContasCorrente repositorioContasCorrente;

    private Validator validator;
    // requisacao usando query string: http://localhost:8080/contas?banco=888&agencia=1111&numero=3333
    @GetMapping
    public String consultarSaldo(
            @RequestParam(name = "banco") String banco,
            @RequestParam(name = "agencia") String agencia,
            @RequestParam(name = "numero") String numero){

        ContaCorrente contaCorrente =
                repositorioContasCorrente.buscar(banco, agencia, numero)
                        .orElse(new ContaCorrente());

        return String.format("Banco: %s, Agencia: %s, Conta: %s, Saldo: %s",
                banco, agencia, numero, contaCorrente.lerSaldo());
    }

    //http://localhost:8080/contas
    //{"nome":"Fabio Luis",	"cpf":"12345678911"}
    @PostMapping
    public ResponseEntity<?> criarNovaConta(@RequestBody CorrentistaForm correntistaForm){
        Map<Path, String> violacoeToMap = validar(correntistaForm);
        if(!violacoeToMap.isEmpty()){
            return ResponseEntity.badRequest().body(violacoeToMap);
        }
        Correntista correntista = correntistaForm.toCorrestista();
        String banco = "111";
        String agencia = "1234";
        String numero = Integer.toString(new Random().nextInt(Integer.MAX_VALUE));
        ContaCorrente conta = new ContaCorrente(banco, agencia, numero,correntista);
        repositorioContasCorrente.salvar(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }

    private <T> Map<Path, String> validar(T  form) {
        Set<ConstraintViolation<T>> violacoes =
                validator.validate(form);
        Map<Path, String> violacoeToMap = violacoes.stream()
                .collect(Collectors.toMap(
                violacao -> violacao.getPropertyPath(), violacao -> violacao.getMessage()));
        return violacoeToMap;
    }

    @DeleteMapping
    public ResponseEntity<?> fecharConta(@RequestBody ContaCorrenteForm contaForm) {
        Map<Path, String> violacoeToMap = validar(contaForm);
        if(!violacoeToMap.isEmpty()){
            return ResponseEntity.badRequest().body(violacoeToMap);
        }

        ContaCorrente conta = new ContaCorrente(contaForm.getBanco(), contaForm.getAgencia(), contaForm.getNumero());
        repositorioContasCorrente.fechar(conta);
        return ResponseEntity.ok("Conta Fechada com  Sucesso");

        //        Optional<ContaCorrente> opContaCorrente =
//                repositorioContasCorrente.buscar(conta.getBanco(),
//                        conta.getAgencia(),
//                        conta.getNumero());
//        if (opContaCorrente.isEmpty()) {
//            return "Conta não existe";
//        } else {
//            repositorioContasCorrente.fechar(conta);
//            return "Conta Fechada com  Sucesso";
//        }
    }

    @PutMapping
    public ResponseEntity<String> movimentarConta(@RequestBody MovimentacaoDeConta movimentacao){
        Optional<ContaCorrente> opContaCorrente =
                repositorioContasCorrente.buscar(movimentacao.getBanco(),
                movimentacao.getAgencia(),
                movimentacao.getNumero());
        if (opContaCorrente.isEmpty()){
            return ResponseEntity.badRequest().body("Conta Corrente não existe");
        } else {
            ContaCorrente contaCorrente = opContaCorrente.get();
            movimentacao.executarEm(contaCorrente);
            repositorioContasCorrente.salvar((contaCorrente));
            return ResponseEntity.ok("Movimentação realizada com sucesso");
        }
    }
}
