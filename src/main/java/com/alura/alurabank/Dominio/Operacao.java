package com.alura.alurabank.Dominio;

import javax.swing.plaf.basic.BasicIconFactory;
import java.math.BigDecimal;

public enum Operacao {

    DEPOSITO{
        @Override
        public BigDecimal executar(BigDecimal saldo, BigDecimal valor) {
            return saldo.add(valor);
        }
    }, SAQUE {
        @Override
        public BigDecimal executar(BigDecimal saldo, BigDecimal valor) {
            return saldo.subtract(valor);
        }
    };

    public abstract BigDecimal executar(BigDecimal saldo, BigDecimal valor);

}
