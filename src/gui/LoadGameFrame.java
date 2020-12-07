package gui;

import service.client.LoadOneRecordClientService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class LoadGameFrame extends JFrame {
    private JPanel contentPane;
    private JTable table;

    public LoadGameFrame(){
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowActivated(WindowEvent e)
            {
                do_this_windowActivated(e);
            }
        });
        setTitle("Load Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,450,200);
        contentPane=new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(new BorderLayout(0,0));
        setContentPane(contentPane);
        JPanel panel=new JPanel();
        contentPane.add(panel,BorderLayout.SOUTH);
        JButton loadButton=new JButton("Load");
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e->{
            this.setVisible(false);
        });
        loadButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                do_button_actionPerformed(e);
            }
        });
        panel.add(loadButton);
        panel.add(cancelButton);
        JScrollPane scrollPane=new JScrollPane();
        contentPane.add(scrollPane,BorderLayout.CENTER);
        table=new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        scrollPane.setViewportView(table);
        this.setVisible(true);
    }

    private void do_this_windowActivated(WindowEvent e) {
        DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        tableModel.setColumnIdentifiers(new Object[]{"Player","Time"});

        for(int i=0;i<YahtzeeFrame.receivedAllRecord.size();i++){
            tableModel.addRow(new Object[]{YahtzeeFrame.receivedAllRecord.get(i).getPlayerName(),YahtzeeFrame.receivedAllRecord.get(i).getTime()});
        }
        table.setRowHeight(30);
        table.setModel(tableModel);
    }

    private void do_button_actionPerformed(ActionEvent e)
    {
        int rows = table.getRowCount();
        boolean checkIsSelected = false;
        for(int i=0;i<rows;i++){
            if(table.isRowSelected(i)){checkIsSelected=true;}
        }
        if(checkIsSelected){
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            int selectedRow=table.getSelectedRow();
            String name = table.getValueAt(selectedRow,0).toString();
            String time = table.getValueAt(selectedRow,1).toString();
            List<Object> tmp = new ArrayList<>();
            tmp.add("LoadOne");
            tmp.add(name);
            tmp.add(time);
            new Thread(new LoadOneRecordClientService(tmp)).run();
            this.setVisible(false);
        }else {
            JFrame messageFrame = new JFrame();
            JOptionPane.showMessageDialog(messageFrame,"please select a game record first!");
        }
    }

//    public static void main(String[] args) {
//        LoadGameFrame loadGameFrame = new LoadGameFrame();
//        loadGameFrame.setVisible(true);
//    }
}
