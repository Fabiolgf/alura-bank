package com.alura.alurabank.Controller;

import com.alura.alurabank.Dominio.Investimento;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/investimento")
@RequiredArgsConstructor
public class InvestimentoController {

    @Autowired
    private Investimento investir;

    //http://localhost:8080/investimento
    @PostMapping
    public ResponseEntity<?> criarInvestimento(@RequestBody Investimento investimento) {

        Map<String, Double> invest = new HashMap<>();
        invest.put("POUPANCA", 0.5);
        invest.put("CDI", 1.6);
        invest.put("FUNDO", 1.5);


        var percentual = invest.get(investimento.getTipoConta());

        if (percentual == null){
            String message = "A chave especificada não existe no map";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        BigDecimal resultado = investir.render(investimento.getValor(), percentual);
        investimento.setValorRendeu(resultado);
        investimento.setPercentual(percentual);

        return ResponseEntity.status(HttpStatus.CREATED).body(investimento);
    }

}
