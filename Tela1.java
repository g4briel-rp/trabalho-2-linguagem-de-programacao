/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2_lprog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gabriel
 */
public class Tela1 extends javax.swing.JFrame {

    /**
     * Creates new form index
     */
    
    ArrayList<Cliente> Lista_C;
    int indice_global;
    
    public int calculaIdade(String data, String padrao){
        DateFormat formato = new SimpleDateFormat(padrao);
        Date nasc = null;
        
        try{
            nasc = formato.parse(data);
        }catch(Exception e){}
        
        Calendar aniv = new GregorianCalendar();
        aniv.setTime(nasc);
        
        Calendar hoje = Calendar.getInstance();
        
        int idade = hoje.get(Calendar.YEAR) - aniv.get(Calendar.YEAR);
        
        aniv.add(Calendar.YEAR, idade);
        
        if(hoje.before(aniv) && idade != 0){--idade;}
        
        return idade;
    }
    
    public void addTable(){
        DefaultTableModel modelo_tab = new DefaultTableModel(new Object[] {"NOME", "CPF", "DATA DE NASC.", "IDADE", "CIDADE", "BAIRRO", "RUA", "NUMERO", "COMPLEMENTO"}, 0);
        
        int tamanho = Lista_C.size() - 1;
        String data;
        
        data = Lista_C.get(tamanho).getDia() + "/" + Lista_C.get(tamanho).getMes() + "/" + Lista_C.get(tamanho).getAno();
        
        int idade = calculaIdade(txt_data_cad.getText(), "dd/MM/yyyy");
            
        Object linha[] = new Object[]{Lista_C.get(tamanho).getNome(), Lista_C.get(tamanho).getCpf(), data,
                                      idade, Lista_C.get(tamanho).getCidade(), Lista_C.get(tamanho).getBairro(), 
                                      Lista_C.get(tamanho).getRua(), Lista_C.get(tamanho).getNumero(),
                                      Lista_C.get(tamanho).getComplemento()};
        
        modelo_tab.addRow(linha);
        
        tab_dados_cad.setModel(modelo_tab);
    }
    
    public void searchTableNome(String nome){
        DefaultTableModel modelo_tab = new DefaultTableModel(new Object[] {"NOME", "CPF", "DATA DE NASC.", "IDADE", "CIDADE", "BAIRRO", "RUA", "NUMERO", "COMPLEMENTO"}, 0);
        
        int tamanho = Lista_C.size(), auxiliar = 0;
        String data;
        
        if(Lista_C.isEmpty()){
            JOptionPane.showMessageDialog(null, "NENHUM CLIENTE CADASTRADO", "CONFIRMAÇÃO DE BUSCA", JOptionPane.INFORMATION_MESSAGE);
            tab_dados_bus.setModel(modelo_tab);
        }else{
        
            for(int i = 0; i < tamanho; i++){

                if(nome.equalsIgnoreCase(Lista_C.get(i).getNome())){

                    data = Lista_C.get(i).getDia() + "/" + Lista_C.get(i).getMes() + "/" + Lista_C.get(i).getAno();

                    Object linha[] = new Object[]{Lista_C.get(i).getNome(), Lista_C.get(i).getCpf(), data,
                                                  Lista_C.get(i).getIdade(), Lista_C.get(i).getCidade(), 
                                                  Lista_C.get(i).getBairro(), Lista_C.get(i).getRua(), Lista_C.get(i).getNumero(),
                                                  Lista_C.get(i).getComplemento()};
                    auxiliar = 1;
                    modelo_tab.addRow(linha);
                }
            }

            if(auxiliar != 0){
                tab_dados_bus.setModel(modelo_tab);
            }else{
                JOptionPane.showMessageDialog(null, "NENHUM CLIENTE ENCONTRADO", "CONFIRMAÇÃO DE BUSCA", JOptionPane.INFORMATION_MESSAGE);
            } 
        }
    }
    
    public void searchTableCPF(String cpf){
        DefaultTableModel modelo_tab = new DefaultTableModel(new Object[] {"NOME", "CPF", "DATA DE NASC.", "IDADE", "CIDADE", "BAIRRO", "RUA", "NUMERO", "COMPLEMENTO"}, 0);
        
        int tamanho = Lista_C.size(), auxiliar = 0;
        String data;
        
        if(Lista_C.isEmpty()){
            JOptionPane.showMessageDialog(null, "NENHUM CLIENTE CADASTRADO", "CONFIRMAÇÃO DE BUSCA", JOptionPane.INFORMATION_MESSAGE);
            tab_dados_rem.setModel(modelo_tab);
        }else{
            for(int i = 0; i < tamanho; i++){
        
                if(cpf.equals(Lista_C.get(i).getCpf())){

                    data = Lista_C.get(i).getDia() + "/" + Lista_C.get(i).getMes() + "/" + Lista_C.get(i).getAno();

                    Object linha[] = new Object[]{Lista_C.get(i).getNome(), Lista_C.get(i).getCpf(), data,
                                                  Lista_C.get(i).getIdade(), Lista_C.get(i).getCidade(), 
                                                  Lista_C.get(i).getBairro(), Lista_C.get(i).getRua(), Lista_C.get(i).getNumero(),
                                                  Lista_C.get(i).getComplemento()};

                    auxiliar = 1;
                    modelo_tab.addRow(linha);
                }
            }
            
            if(auxiliar != 0){
                tab_dados_rem.setModel(modelo_tab);
            }else{
                JOptionPane.showMessageDialog(null, "NENHUM CLIENTE ENCONTRADO", "CONFIRMAÇÃO DE BUSCA", JOptionPane.INFORMATION_MESSAGE);
            }    
        }   
    }
    
    public String verifica_txt(String str) throws Exception{
        if(str.isEmpty()) {throw new Exception("PREENCHA OS CAMPOS VAZIOS");}
        else if(!str.matches("[a-zA-Z ]*")) {throw new IllegalArgumentException("CAMPO DE TEXTO NÃO PODE CONTER NÚMEROS");}
       
        return str;
    }
    
    public String verifica_numero(String str){
        if(!str.matches("[0-9]*")) {throw new IllegalArgumentException("APENAS NÚMEROS POSITIVOS");}
        
        return str;
    }
    
    public String verifica_data(String data){
        Calendar hoje = Calendar.getInstance();
        String data_dividida[] = data.split("/");
        
        if(Integer.parseInt(data_dividida[2]) == hoje.get(Calendar.YEAR)){
            if(Integer.parseInt(data_dividida[1]) <= hoje.get(Calendar.MONTH) + 1){
                if(Integer.parseInt(data_dividida[0]) <= hoje.get(Calendar.DATE)){
                    return data;
                }else{
                    throw new IllegalArgumentException("DIA INVÁLIDO !!");
                }
            }else{
                throw new IllegalArgumentException("MÊS INVÁLIDO !!");
            } 
        }else if(Integer.parseInt(data_dividida[2]) > 0 && Integer.parseInt(data_dividida[2]) < hoje.get(Calendar.YEAR)){
            if(Integer.parseInt(data_dividida[1]) > 0 && Integer.parseInt(data_dividida[1]) < 13){
                if(Integer.parseInt(data_dividida[0]) > 0 && Integer.parseInt(data_dividida[0]) < 32){
                    return data;
                }else{
                    throw new IllegalArgumentException("DIA INVÁLIDO !!");
                }
            }else{
                throw new IllegalArgumentException("MÊS INVÁLIDO !!");
            }
        }else{
            throw new IllegalArgumentException("ANO INVÁLIDO !!");
        }
    }
    
    public String verifica_cpf(String cpf) throws Exception{
        for(Cliente c : Lista_C){
            if(cpf.equals(c.getCpf())) {throw new Exception("CPF JÁ CADASTRADO");}
        }
        
        return cpf;
    }
    
    public Tela1() {
        initComponents();
        
        jTabbedPane1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                DefaultTableModel modelo_tab = new DefaultTableModel(new Object[] {"NOME", "CPF", "DATA DE NASC.", "IDADE", "CIDADE", "BAIRRO", "RUA", "NUMERO", "COMPLEMENTO"}, 0);
        
                txt_nome_cad.setText("");
                txt_cpf_cad.setText("");
                txt_data_cad.setText("");
                txt_cidade_cad.setText("");
                txt_bairro_cad.setText("");
                txt_rua_cad.setText("");
                txt_numero_cad.setText("");
                txt_comp_cad.setText("");

                tab_dados_cad.setModel(modelo_tab);

                txt_nome_bus.setText("");
                txt_nome_alt.setText("");
                txt_cpf_alt.setText("");
                txt_data_alt.setText("");
                txt_cidade_alt.setText("");
                txt_bairro_alt.setText("");
                txt_rua_alt.setText("");
                txt_numero_alt.setText("");
                txt_comp_alt.setText("");

                tab_dados_bus.setModel(modelo_tab);

                txt_cpf_rem.setText("");

                tab_dados_rem.setModel(modelo_tab);
            }
        });
        
        Lista_C = new ArrayList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lab_nome_cad = new javax.swing.JLabel();
        txt_nome_cad = new javax.swing.JTextField();
        lab_cpf_cad = new javax.swing.JLabel();
        lab_data_cad = new javax.swing.JLabel();
        txt_cpf_cad = new javax.swing.JFormattedTextField();
        txt_data_cad = new javax.swing.JFormattedTextField();
        jPanel8 = new javax.swing.JPanel();
        lab_cidade_cad = new javax.swing.JLabel();
        txt_cidade_cad = new javax.swing.JTextField();
        txt_bairro_cad = new javax.swing.JTextField();
        lab_bairro_cad = new javax.swing.JLabel();
        lab_rua_cad = new javax.swing.JLabel();
        txt_rua_cad = new javax.swing.JTextField();
        lab_numero_cad = new javax.swing.JLabel();
        txt_numero_cad = new javax.swing.JTextField();
        lab_comp_cad = new javax.swing.JLabel();
        txt_comp_cad = new javax.swing.JTextField();
        bot_cadastrar_cad = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab_dados_cad = new javax.swing.JTable();
        bot_sair_cad = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lab_nome_bus = new javax.swing.JLabel();
        txt_nome_bus = new javax.swing.JTextField();
        bot_buscar_alt = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tab_dados_bus = new javax.swing.JTable();
        bot_editar_bus = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        lab_nome_alt = new javax.swing.JLabel();
        txt_nome_alt = new javax.swing.JTextField();
        lab_cpf_alt = new javax.swing.JLabel();
        lab_data_alt = new javax.swing.JLabel();
        txt_cpf_alt = new javax.swing.JFormattedTextField();
        txt_data_alt = new javax.swing.JFormattedTextField();
        bot_salvar_alt = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        lab_cidade_alt = new javax.swing.JLabel();
        txt_cidade_alt = new javax.swing.JTextField();
        txt_bairro_alt = new javax.swing.JTextField();
        lab_bairro_alt = new javax.swing.JLabel();
        lab_rua_alt = new javax.swing.JLabel();
        txt_rua_alt = new javax.swing.JTextField();
        lab_numero_alt = new javax.swing.JLabel();
        txt_numero_alt = new javax.swing.JTextField();
        lab_comp_alt = new javax.swing.JLabel();
        txt_comp_alt = new javax.swing.JTextField();
        bot_sair_alt = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        lab_cpf_rem = new javax.swing.JLabel();
        txt_cpf_rem = new javax.swing.JFormattedTextField();
        bot_buscar_rem = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tab_dados_rem = new javax.swing.JTable();
        bot_remover_rem = new javax.swing.JButton();
        bot_sair_rem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "CADASTRO"));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "INFORMAÇÕES PESSOAIS"));

        lab_nome_cad.setText("NOME");

        lab_cpf_cad.setText("CPF");

        lab_data_cad.setText("DATA DE NASC.");

        try {
            txt_cpf_cad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_cpf_cad.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        try {
            txt_data_cad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lab_nome_cad)
                    .addComponent(lab_cpf_cad)
                    .addComponent(lab_data_cad))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_nome_cad)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_cpf_cad, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                            .addComponent(txt_data_cad))
                        .addGap(0, 220, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_nome_cad)
                    .addComponent(txt_nome_cad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lab_cpf_cad)
                    .addComponent(txt_cpf_cad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lab_data_cad)
                    .addComponent(txt_data_cad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "ENDEREÇO"));

        lab_cidade_cad.setText("CIDADE");

        lab_bairro_cad.setText("BAIRRO");

        lab_rua_cad.setText("RUA");

        lab_numero_cad.setText("NÚMERO");

        lab_comp_cad.setText("COMPLEMENTO");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lab_cidade_cad)
                    .addComponent(lab_bairro_cad)
                    .addComponent(lab_rua_cad)
                    .addComponent(lab_numero_cad)
                    .addComponent(lab_comp_cad))
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_comp_cad, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                    .addComponent(txt_bairro_cad, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_rua_cad, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_cidade_cad)
                    .addComponent(txt_numero_cad, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lab_cidade_cad)
                            .addComponent(txt_cidade_cad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lab_bairro_cad))
                    .addComponent(txt_bairro_cad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_rua_cad)
                    .addComponent(txt_rua_cad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_numero_cad)
                    .addComponent(txt_numero_cad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_comp_cad)
                    .addComponent(txt_comp_cad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bot_cadastrar_cad.setText("CADASTRAR");
        bot_cadastrar_cad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bot_cadastrar_cadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bot_cadastrar_cad, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bot_cadastrar_cad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "DADOS"));

        tab_dados_cad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOME", "CPF", "DATA DE NASC.", "IDADE", "CIDADE", "BAIRRO", "RUA", "NÚMERO", "COMPLEMENTO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tab_dados_cad);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        bot_sair_cad.setText("SAIR");
        bot_sair_cad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bot_sair_cadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bot_sair_cad)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(bot_sair_cad)
                .addContainerGap())
        );

        jTabbedPane1.addTab("CADASTRAR", jPanel1);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "BUSCAR"));

        lab_nome_bus.setText("NOME");

        bot_buscar_alt.setText("BUSCAR");
        bot_buscar_alt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bot_buscar_altActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lab_nome_bus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nome_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bot_buscar_alt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_nome_bus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bot_buscar_alt))
                    .addComponent(lab_nome_bus))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "RESULTADOS"));

        tab_dados_bus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOME", "CPF", "DATA DE NASC.", "IDADE", "CIDADE", "BAIRRO", "RUA", "NÚMERO", "COMPLEMENTO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab_dados_bus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_dados_busMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tab_dados_bus);

        bot_editar_bus.setText("EDITAR DADOS");
        bot_editar_bus.setEnabled(false);
        bot_editar_bus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bot_editar_busActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(bot_editar_bus)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bot_editar_bus)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "ALTERAR DADOS"));

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "INFORMAÇÕES PESSOAIS"));

        lab_nome_alt.setText("NOME");

        txt_nome_alt.setEnabled(false);

        lab_cpf_alt.setText("CPF");

        lab_data_alt.setText("DATA DE NASC.");

        try {
            txt_cpf_alt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_cpf_alt.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_cpf_alt.setEnabled(false);

        try {
            txt_data_alt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_data_alt.setEnabled(false);

        bot_salvar_alt.setText("SALVAR ALTERAÇÕES");
        bot_salvar_alt.setEnabled(false);
        bot_salvar_alt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bot_salvar_altActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lab_nome_alt)
                            .addComponent(lab_cpf_alt)
                            .addComponent(lab_data_alt))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nome_alt)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_cpf_alt, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                    .addComponent(txt_data_alt))
                                .addGap(0, 220, Short.MAX_VALUE))))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(bot_salvar_alt)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_nome_alt)
                    .addComponent(txt_nome_alt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lab_cpf_alt)
                    .addComponent(txt_cpf_alt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lab_data_alt)
                    .addComponent(txt_data_alt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(bot_salvar_alt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "ENDEREÇO"));

        lab_cidade_alt.setText("CIDADE");

        txt_cidade_alt.setEnabled(false);

        txt_bairro_alt.setEnabled(false);

        lab_bairro_alt.setText("BAIRRO");

        lab_rua_alt.setText("RUA");

        txt_rua_alt.setEnabled(false);

        lab_numero_alt.setText("NÚMERO");

        txt_numero_alt.setEnabled(false);

        lab_comp_alt.setText("COMPLEMENTO");

        txt_comp_alt.setEnabled(false);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lab_cidade_alt)
                    .addComponent(lab_bairro_alt)
                    .addComponent(lab_rua_alt)
                    .addComponent(lab_numero_alt)
                    .addComponent(lab_comp_alt))
                .addGap(20, 20, 20)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_comp_alt, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                    .addComponent(txt_bairro_alt, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_rua_alt, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_cidade_alt)
                    .addComponent(txt_numero_alt, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lab_cidade_alt)
                            .addComponent(txt_cidade_alt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lab_bairro_alt))
                    .addComponent(txt_bairro_alt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_rua_alt)
                    .addComponent(txt_rua_alt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_numero_alt)
                    .addComponent(txt_numero_alt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_comp_alt)
                    .addComponent(txt_comp_alt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bot_sair_alt.setText("SAIR");
        bot_sair_alt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bot_sair_altActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(bot_sair_alt)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(bot_sair_alt)
                .addContainerGap())
        );

        jTabbedPane1.addTab("ALTERAR/BUSCAR", jPanel2);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "BUSCAR CPF"));

        lab_cpf_rem.setText("CPF");

        try {
            txt_cpf_rem.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_cpf_rem.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        bot_buscar_rem.setText("BUSCAR");
        bot_buscar_rem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bot_buscar_remActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lab_cpf_rem)
                .addGap(18, 18, 18)
                .addComponent(txt_cpf_rem, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bot_buscar_rem)
                .addGap(821, 821, 821))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_cpf_rem)
                    .addComponent(txt_cpf_rem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bot_buscar_rem))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "RESULTADOS"));

        tab_dados_rem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOME", "CPF", "DATA DE NASC.", "IDADE", "CIDADE", "BAIRRO", "RUA", "NÚMERO", "COMPLEMENTO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab_dados_rem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_dados_remMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tab_dados_rem);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addContainerGap())
        );

        bot_remover_rem.setText("REMOVER");
        bot_remover_rem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bot_remover_remActionPerformed(evt);
            }
        });

        bot_sair_rem.setText("SAIR");
        bot_sair_rem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bot_sair_remActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(bot_sair_rem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bot_remover_rem)
                        .addGap(496, 496, 496))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bot_remover_rem)
                    .addComponent(bot_sair_rem))
                .addContainerGap())
        );

        jTabbedPane1.addTab("REMOVER", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bot_cadastrar_cadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bot_cadastrar_cadActionPerformed
        // TODO add your handling code here:
        
        try{
            
            String nome = verifica_txt(txt_nome_cad.getText());
            String cpf = verifica_cpf(txt_cpf_cad.getText());
            String data = verifica_data(txt_data_cad.getText());
            String data_dividida[] = data.split("/");
            int dia = Integer.parseInt(data_dividida[0]);
            int mes = Integer.parseInt(data_dividida[1]);
            int ano = Integer.parseInt(data_dividida[2]);
            int idade = calculaIdade(txt_data_cad.getText(), "dd/MM/yyyy");
            String cidade = verifica_txt(txt_cidade_cad.getText());
            String bairro = verifica_txt(txt_bairro_cad.getText());
            String rua = verifica_txt(txt_rua_cad.getText());
            String numero = verifica_numero(txt_numero_cad.getText());

            Cliente c = new Cliente(nome, cpf, dia, mes, ano, idade, cidade, bairro, rua, Integer.parseInt(numero), txt_comp_cad.getText());

            Lista_C.add(c);

            addTable();

            JOptionPane.showMessageDialog(null, "CLIENTE " + txt_nome_cad.getText() + " CADASTRADO COM SUCESSO !!", "CONFIRMAÇÃO DE CADASTRO", JOptionPane.INFORMATION_MESSAGE);

            txt_nome_cad.setText("");
            txt_cpf_cad.setText("");
            txt_data_cad.setText("");
            txt_cidade_cad.setText("");
            txt_bairro_cad.setText("");
            txt_rua_cad.setText("");
            txt_numero_cad.setText("");
            txt_comp_cad.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "VERIFICAÇÃO DOS CAMPOS DE TEXTO", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_bot_cadastrar_cadActionPerformed

    private void bot_sair_cadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bot_sair_cadActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_bot_sair_cadActionPerformed

    private void bot_sair_altActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bot_sair_altActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_bot_sair_altActionPerformed

    private void bot_sair_remActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bot_sair_remActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_bot_sair_remActionPerformed

    private void bot_buscar_altActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bot_buscar_altActionPerformed
        // TODO add your handling code here:
        
        String nome = txt_nome_bus.getText();
        
        searchTableNome(nome);
    }//GEN-LAST:event_bot_buscar_altActionPerformed

    private void bot_salvar_altActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bot_salvar_altActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelo_tab = new DefaultTableModel(new Object[] {"NOME", "CPF", "DATA DE NASC.", "IDADE", "CIDADE", "BAIRRO", "RUA", "NUMERO", "COMPLEMENTO"}, 0);
        
        try {
            String nome = verifica_txt(txt_nome_alt.getText());
            String cpf = txt_cpf_alt.getText();
            String data = verifica_data(txt_data_alt.getText());
            String data_dividida[] = data.split("/");
            int dia = Integer.parseInt(data_dividida[0]);
            int mes = Integer.parseInt(data_dividida[1]);
            int ano = Integer.parseInt(data_dividida[2]);
            int idade = calculaIdade(txt_data_alt.getText(), "dd/MM/yyyy");
            String cidade = verifica_txt(txt_cidade_alt.getText());
            String bairro = verifica_txt(txt_bairro_alt.getText());
            String rua = verifica_txt(txt_rua_alt.getText());
            String numero = verifica_numero(txt_numero_alt.getText());
            
            Lista_C.get(indice_global).setNome(nome);
            Lista_C.get(indice_global).setCpf(cpf);
            Lista_C.get(indice_global).setDia(dia);
            Lista_C.get(indice_global).setMes(mes);
            Lista_C.get(indice_global).setAno(ano);
            Lista_C.get(indice_global).setIdade(idade);
            Lista_C.get(indice_global).setCidade(cidade);
            Lista_C.get(indice_global).setBairro(bairro);
            Lista_C.get(indice_global).setRua(rua);
            Lista_C.get(indice_global).setNumero(Integer.parseInt(numero));
            Lista_C.get(indice_global).setComplemento(txt_comp_alt.getText());

            txt_nome_alt.setText("");
            txt_cpf_alt.setText("");
            txt_data_alt.setText("");
            txt_cidade_alt.setText("");
            txt_bairro_alt.setText("");
            txt_rua_alt.setText("");
            txt_numero_alt.setText("");
            txt_comp_alt.setText("");

            txt_nome_alt.setEnabled(false);
            txt_cpf_alt.setEnabled(false);
            txt_data_alt.setEnabled(false);
            txt_cidade_alt.setEnabled(false);
            txt_bairro_alt.setEnabled(false);
            txt_rua_alt.setEnabled(false);
            txt_numero_alt.setEnabled(false);
            txt_comp_alt.setEnabled(false);

            bot_buscar_alt.setEnabled(true);
            bot_salvar_alt.setEnabled(false);

            tab_dados_bus.setModel(modelo_tab);

            JOptionPane.showMessageDialog(null, "ALTERAÇÃO REALIZDA COM SUCESSO !!", "COMFIRMAÇÃO DE ALTERAÇÃO", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "VERIFICAÇÃO DOS CAMPOS DE TEXTO", JOptionPane.INFORMATION_MESSAGE);
        }  
    }//GEN-LAST:event_bot_salvar_altActionPerformed

    private void bot_editar_busActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bot_editar_busActionPerformed
        // TODO add your handling code here:
        
        bot_salvar_alt.setEnabled(true);
        bot_buscar_alt.setEnabled(false);
        bot_editar_bus.setEnabled(false);
        txt_nome_bus.setText("");
        
        txt_nome_alt.setEnabled(true);
        txt_cpf_alt.setEnabled(true);
        txt_data_alt.setEnabled(true);
        txt_cidade_alt.setEnabled(true);
        txt_bairro_alt.setEnabled(true);
        txt_rua_alt.setEnabled(true);
        txt_numero_alt.setEnabled(true);
        txt_comp_alt.setEnabled(true);
    }//GEN-LAST:event_bot_editar_busActionPerformed

    private void tab_dados_busMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_dados_busMouseClicked
        // TODO add your handling code here:
        bot_editar_bus.setEnabled(true);
        
        String data, dia = "", mes = "", ano = "";
        int indice = tab_dados_bus.getSelectedRow();
        
        if(Lista_C.get(indice).getDia() < 10){
            dia = "0" + Lista_C.get(indice).getDia();
        }else{
            dia = Integer.toString(Lista_C.get(indice).getDia());
        }
        
        if(Lista_C.get(indice).getMes()< 10){
            mes = "0" + Lista_C.get(indice).getMes();
        }else{
            mes = Integer.toString(Lista_C.get(indice).getMes());
        }
        
        data = dia + "/" + mes + "/" + Lista_C.get(indice).getAno();

        txt_nome_alt.setText(Lista_C.get(indice).getNome());
        txt_cpf_alt.setText(Lista_C.get(indice).getCpf());
        txt_data_alt.setText(data);
        txt_cidade_alt.setText(Lista_C.get(indice).getCidade());
        txt_bairro_alt.setText(Lista_C.get(indice).getBairro());
        txt_rua_alt.setText(Lista_C.get(indice).getRua());
        txt_numero_alt.setText(Integer.toString(Lista_C.get(indice).getNumero()));
        txt_comp_alt.setText(Lista_C.get(indice).getComplemento());
        
        indice_global = indice;
    }//GEN-LAST:event_tab_dados_busMouseClicked

    private void bot_remover_remActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bot_remover_remActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelo_tab = new DefaultTableModel(new Object[] {"NOME", "CPF", "DATA DE NASC.", "IDADE", "CIDADE", "BAIRRO", "RUA", "NUMERO", "COMPLEMENTO"}, 0);
        
        Lista_C.remove(indice_global);
        
        txt_cpf_rem.setText("");
        
        tab_dados_rem.setModel(modelo_tab);
        
        JOptionPane.showMessageDialog(null, "CLIENTE REMOVIDO COM SUCESSO !!", "COMFIRMAÇÃO DE REMOÇÃO", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_bot_remover_remActionPerformed

    private void bot_buscar_remActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bot_buscar_remActionPerformed
        // TODO add your handling code here:
        String cpf = txt_cpf_rem.getText();
        
        searchTableCPF(cpf);
    }//GEN-LAST:event_bot_buscar_remActionPerformed

    private void tab_dados_remMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_dados_remMouseClicked
        // TODO add your handling code here:
        
        int indice = tab_dados_rem.getSelectedRow();
        
        indice_global = indice;
    }//GEN-LAST:event_tab_dados_remMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tela1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() { 
               new Tela1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bot_buscar_alt;
    private javax.swing.JButton bot_buscar_rem;
    private javax.swing.JButton bot_cadastrar_cad;
    private javax.swing.JButton bot_editar_bus;
    private javax.swing.JButton bot_remover_rem;
    private javax.swing.JButton bot_sair_alt;
    private javax.swing.JButton bot_sair_cad;
    private javax.swing.JButton bot_sair_rem;
    private javax.swing.JButton bot_salvar_alt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lab_bairro_alt;
    private javax.swing.JLabel lab_bairro_cad;
    private javax.swing.JLabel lab_cidade_alt;
    private javax.swing.JLabel lab_cidade_cad;
    private javax.swing.JLabel lab_comp_alt;
    private javax.swing.JLabel lab_comp_cad;
    private javax.swing.JLabel lab_cpf_alt;
    private javax.swing.JLabel lab_cpf_cad;
    private javax.swing.JLabel lab_cpf_rem;
    private javax.swing.JLabel lab_data_alt;
    private javax.swing.JLabel lab_data_cad;
    private javax.swing.JLabel lab_nome_alt;
    private javax.swing.JLabel lab_nome_bus;
    private javax.swing.JLabel lab_nome_cad;
    private javax.swing.JLabel lab_numero_alt;
    private javax.swing.JLabel lab_numero_cad;
    private javax.swing.JLabel lab_rua_alt;
    private javax.swing.JLabel lab_rua_cad;
    private javax.swing.JTable tab_dados_bus;
    private javax.swing.JTable tab_dados_cad;
    private javax.swing.JTable tab_dados_rem;
    private javax.swing.JTextField txt_bairro_alt;
    private javax.swing.JTextField txt_bairro_cad;
    private javax.swing.JTextField txt_cidade_alt;
    private javax.swing.JTextField txt_cidade_cad;
    private javax.swing.JTextField txt_comp_alt;
    private javax.swing.JTextField txt_comp_cad;
    private javax.swing.JFormattedTextField txt_cpf_alt;
    private javax.swing.JFormattedTextField txt_cpf_cad;
    private javax.swing.JFormattedTextField txt_cpf_rem;
    private javax.swing.JFormattedTextField txt_data_alt;
    private javax.swing.JFormattedTextField txt_data_cad;
    private javax.swing.JTextField txt_nome_alt;
    private javax.swing.JTextField txt_nome_bus;
    private javax.swing.JTextField txt_nome_cad;
    private javax.swing.JTextField txt_numero_alt;
    private javax.swing.JTextField txt_numero_cad;
    private javax.swing.JTextField txt_rua_alt;
    private javax.swing.JTextField txt_rua_cad;
    // End of variables declaration//GEN-END:variables
}
