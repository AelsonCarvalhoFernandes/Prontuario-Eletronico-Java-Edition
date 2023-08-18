package com.PI.ProntuarioEletronico.models;

import com.PI.ProntuarioEletronico.resources.enums.Roles;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class UserModel implements UserDetails {


    // Atributos dos usuarios
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String telefone;
    private String endereco;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String crm;
    private String dataEmissaoCrm;
    private String cns;
    private String cpf;
    private String rg;
    private LocalDateTime dataNascimento;
    private Roles roles;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
    @OneToMany(mappedBy = "users")
    private List<ReceitaModel> receitas;
    @OneToMany(mappedBy = "medico")
    private List<ReceitaModel> receitasMedico;


    // Getters e Setters dos atributos

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<ReceitaModel> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<ReceitaModel> receitas) {
        this.receitas = receitas;
    }

    public List<ReceitaModel> getReceitasMedico() {
        return receitasMedico;
    }

    public void setReceitasMedico(List<ReceitaModel> receitasMedico) {
        this.receitasMedico = receitasMedico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getDataEmissaoCrm() {
        return dataEmissaoCrm;
    }

    public void setDataEmissaoCrm(String dataEmissaoCrm) {
        this.dataEmissaoCrm = dataEmissaoCrm;
    }

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    // Spring Security

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if(this.roles == Roles.administrador){
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_MEDICO"),
                    new SimpleGrantedAuthority("ROLE_COLABORADOR"),
                    new SimpleGrantedAuthority("ROLE_USUARIO")
            );
        }else if(this.roles == Roles.medico){
            return List.of(
                    new SimpleGrantedAuthority("ROLE_MEDICO"),
                    new SimpleGrantedAuthority("ROLE_COLABORADOR"),
                    new SimpleGrantedAuthority("ROLE_USUARIO")
            );
        }else if(this.roles == Roles.colaborador){
            return List.of(
                    new SimpleGrantedAuthority("ROLE_COLABORADOR"),
                    new SimpleGrantedAuthority("ROLE_USUARIO")
            );
        }else{
            return List.of(
                    new SimpleGrantedAuthority("ROLE_USUARIO")
            );
        }
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
