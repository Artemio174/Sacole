/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import dao.DaoSacole;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import modelo.Sacole;
import tela.manutencao.ManutencaoSacole;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class ControladorSacole {

    public static void inserir(ManutencaoSacole man){
        Sacole objeto = new Sacole();
        objeto.setNr_serie(Integer.parseInt(man.jtfnr_serie.getText()));  
        objeto.setPreco(Double.parseDouble(man.jtfpreco.getText()));
        objeto.setData_validade(LocalDate.parse(man.jtfdata_validade.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setSabor(man.jtfsabor.getText());
        boolean resultado = DaoSacole.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        
        {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
}
    public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        modelo.addColumn("Código");
        modelo.addColumn("Número de Série");
        modelo.addColumn("Preço");
        modelo.addColumn("Data_validade");
        modelo.addColumn("Sabor");
        List<Sacole> resultados = DaoSacole.consultar();
        for (Sacole objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getNr_serie());
            linha.add(objeto.getPreco());
            linha.add(objeto.getData_validade());
            linha.add(objeto.getSabor());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }

    public static void alterar(ManutencaoSacole man){
        Sacole objeto = new Sacole();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfcodigo.getText()));
        objeto.setNr_serie(Integer.parseInt(man.jtfnr_serie.getText()));
        objeto.setPreco(Double.parseDouble(man.jtfpreco.getText()));
        objeto.setData_validade(LocalDate.parse(man.jtfdata_validade.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setSabor(man.jtfsabor.getText());
        
        boolean resultado = DaoSacole.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } else if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção 
        {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }

   public static void excluir(ManutencaoSacole man){
        Sacole objeto = new Sacole();
        objeto.setCodigo(Integer.parseInt(man.jtfcodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoSacole.excluir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } else if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        
        {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
     public static void atualizaCampos(ManutencaoSacole man, int pk){ 
        Sacole objeto = DaoSacole.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfcodigo.setText(objeto.getCodigo().toString());
        man.jtfnr_serie.setText(objeto.getNr_serie().toString());
        man.jtfpreco.setText(objeto.getPreco().toString());
        man.jtfdata_validade.setText(objeto.getData_validade().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        man.jtfsabor.setText(objeto.getSabor());
        
        man.jtfcodigo.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
}
