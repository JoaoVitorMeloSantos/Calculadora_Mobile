package com.joao.myapplication;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.BiFunction;

public class Calculadora {

    public static final int MODO_EDITANDO = 0;
    public static final int MODO_EXIBINDO = 1;
    public static final int MODO_ERRO = 2;
    private double numero;
    private Deque<Double> operandos;
    private int modo = MODO_EXIBINDO;
    private double pv = 0;
    private double i = 0;
    private int n = 0;
    private double pmt = 0;

    public Calculadora() {
        numero = 0;
        operandos = new LinkedList<>();
        pv = 0;
        i = 0;
        n = 0;
        pmt = 0;
    }

    public void setNumero(double numero) {
        this.numero = numero;
        modo = MODO_EDITANDO;
    }

    public double getNumero(){ return numero; }

    public void setModo(int modo){ this.modo = modo; }

    public int getModo(){ return modo; }

    public double getPv() { return pv; }

    public double getI() { return i; }

    public int getN() { return n; }

    public double getPmt() { return pmt; }

    public void setPV(double pv){ this.pv = pv; }

    public void setI(double i){ this.i = i; }

    public void setN(int n){ this.n = n; }

    public void setPMT(double pmt){ this.pmt = pmt; }

    public void enter() {
        if (modo == MODO_ERRO) {
            modo = MODO_EXIBINDO;
        }
        if (modo == MODO_EDITANDO) {
            operandos.push(numero);
            modo = MODO_EXIBINDO;
        }
    }

    protected void executarOperacao(BiFunction<Double, Double, Double> operacao) {
        if (modo == MODO_EDITANDO || modo == MODO_ERRO) {
            enter();
        }
        double op2 = Optional.ofNullable(operandos.pollFirst()).orElse(0.0);
        double op1 = Optional.ofNullable(operandos.pollFirst()).orElse(0.0);
        numero = operacao.apply(op1, op2);
        operandos.push(numero);
    }

    public void soma() {
        executarOperacao((op1, op2) -> op1 + op2);
    }

    public void subtracao() {
        executarOperacao((op1, op2) -> op1 - op2);
    }

    public void multiplicacao() {
        executarOperacao((op1, op2) -> op1 * op2);
    }

    public void divisao() {
        if (modo == MODO_EDITANDO) {
            enter();
        }
        double denominador = Optional.ofNullable(operandos.peek()).orElse(0.0);
        if (denominador == 0) {
            modo = MODO_ERRO;
            return;
        }
        executarOperacao((op1, op2) -> op2 / op1);
    }

    public void limpar() {
        numero = 0;
        operandos.clear();
        modo = MODO_EXIBINDO;
    }

    public double calcularFV(double pv, double i, int n, double pmt) {
        double fv = pv * Math.pow((1 + i / 100), n) + pmt * ((Math.pow((1 + i / 100), n) - 1) / (i / 100)) * (1 + i / 100);
        return fv;
    }

}