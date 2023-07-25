
package javaapplication9;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import java.sql.*;

import net.proteanit.sql.DbUtils;

public class viewEmployee extends JFrame implements ActionListener{
    
    JTable table;
    Choice cemployeeID ;
;
    
    JButton search,print,back,update;
    
    
    viewEmployee(){
           
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
         ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/view.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1120,630, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,1120,630);
        add(image);
        
        JLabel searchlbl = new JLabel("Search by Employee id");
        searchlbl.setBounds(20,20,150,20);
        image.add(searchlbl);
        
        search = new JButton("Search");
        search.setBounds(20,70,150,20);
        search.addActionListener(this);
        image.add(search);
        
         print = new JButton("print");
        print.setBounds(120,70,150,20);
        print.addActionListener(this);
        image.add(print);
        
         update = new JButton("update");
        update.setBounds(220,70,150,20);
                update.addActionListener(this);
        image.add(update);
        
         back = new JButton("Back");
        back.setBounds(320,70,150,20);
        back.addActionListener(this);
        image.add(back);
        
        cemployeeID = new Choice();
        cemployeeID.setBounds(180,20,150,20);
        
        
        
      
        
        
        
        
        table = new JTable();
        try{
             Conn c = new Conn();
            ResultSet rs=c.s.executeQuery("select *from employee");
            while(rs.next())
            {
                cemployeeID.add(rs.getString("emp_id"));
                
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        image.add(cemployeeID )
;        
        
        try{
            
            Conn c = new Conn();
            ResultSet rs=c.s.executeQuery("select *from employee");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0,100,900,600);
        image.add(jsp);
        
        
        
        setSize(900,700);
        setLocation(300,100);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==search){
            
                String query = "select *from employee where emp_id = '"+cemployeeID.getSelectedItem()+"';";
                
               try{ 
                   Conn c = new Conn();
                   ResultSet rs = c.s.executeQuery(query);
                   
                   table.setModel(DbUtils.resultSetToTableModel(rs));
                   
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource()==print){
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
        }else if(ae.getSource()==update){
            setVisible(false);
            new updateEmp(cemployeeID.getSelectedItem());
        }else{
        setVisible(false);
            new Home();
        }
    }
    
    
    public static void main(String args[]){
        new viewEmployee();
    }
}