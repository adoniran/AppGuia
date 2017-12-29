package model;

/**
 * Created by adoniran on 28/11/17.
 */

public class Atividade {
    private String nome;
    private String descricao;
    private String data_ini;
    private String data_final;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
    private String pais;

    public Atividade(){}
    public Atividade(String nome ,String descricao,String dataI,String data_final,String rua,String bairro,String estado,String cidade,String pais){
        this.nome=nome;
        this.descricao=descricao;
        this.data_ini=dataI;
        this.data_final=data_final;
        this.logradouro=rua;
        this.bairro=bairro;
        this.localidade=cidade;
        this.uf=estado;
        this.pais=pais;

    }

    public String getBairro() {
        return bairro;
    }

    public String getData_ini() {
        return data_ini;
    }

    public String getData_final() {
        return data_final;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getUf() {
        return uf;
    }

    public String getPais() {
        return pais;
    }

    public String getLogradouro() {
        return logradouro;
    }
    public void setNome(String nome){
        this.nome=nome;
    }
    public void setLogradouro(String nome){
        this.logradouro=nome;
    }
    public void setBairro(String nome){
    this.bairro=nome;
    }
    public void setDescricao(String nome){
        this.descricao=nome;
    }
    public void setLocalidade(String nome){
        this.localidade=nome;
    }
    public void setUf(String nome){
        this.uf=nome;
    }
    public void setData_ini(String nome){
        this.data_ini=nome;
    }
    public void setData_final(String nome){
        this.data_final=nome;
    }
    public void setPais(String nome){
        this.pais=nome;
    }


    @Override
    public String toString(){
        String retorno="Atividade:"+this.nome+"Descrição:"+this.descricao+"Data de inicio:"+this.data_ini+"Data de Final:"+this.data_final+" Rua:"+this.logradouro+" Bairro:"+this.bairro+" Cidade:"+this.localidade+" Estado:"+this.uf;
        return retorno;
    }

}