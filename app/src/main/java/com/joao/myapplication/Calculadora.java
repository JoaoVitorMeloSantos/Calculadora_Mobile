package com.joao.myapplication;

public class Calculadora {

    private static final int MODO_EDITANDO = 0;

    private static final int MODO_EXIBINDO = 1;

    private double numero;

    private Deque<Double> operandos;

    private int modo = MODO_EXIBINDO;,

    public Calculadora(){
        numero = 0;
        operandos = new LinkedList<>();
    }

    public double getNumero() {
        return numero;
    }

    public void setNumero(double numero) {
        this.numero = numero;
        modo = MODO_EDITANDO;
    }

    public void enter(){
        if(modo == MODO_EDITANDO){
            operandos.push(numero);
            modo=MODO_EXIBINDO;
        }
    }

    public void soma(){
        if(modo == MODO_EDITANDO){
            enter();
        }
        double op1 = operandos.pop();
        double op2 = operandos.pop();
        numero = op1 + op2;
        operandos.push(numero);
    }
}
