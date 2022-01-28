import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;

//this class contains the race details gui ,that contains the race number date number of first places , second places and third places according to the date ascending order
public class RaceDetailsGUI {
    RaceDetailsGUI(){
        JTable table =new JTable();//table
        Object [] columns ={"Race Number","Date","First","Second","Third"};//columns
        DefaultTableModel model=new DefaultTableModel();

        JFrame frame= new JFrame("Race Details");//header of the tab
        frame.getContentPane().setBackground(new Color(0,0,0));//frame background color
        frame.getContentPane().setForeground(Color.WHITE);//frame foreground color
        frame.setBounds(100,100,1000,500);//frame bounds

        try {//changed the app icon
            frame.setIconImage(ImageIO.read(new File("src/images/icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.getContentPane().setLayout(null);//setting layout to null
        frame.setLocationRelativeTo(null);//setting location relative to null
        model.setColumnIdentifiers(columns);//set columns for model
        table.getTableHeader().setBackground(new Color(21,41,71));//column header color
        table.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,19));//column header
        table.getTableHeader().setForeground(Color.WHITE);//table column name colors
        table.setModel(model);//set the model for the table

        table.setBackground(new Color(139, 144, 196));//set table background color
        table.setForeground(Color.black);//set table foreground color
        table.setSelectionBackground(new Color(21,41,71));//selection background color
        table.setGridColor(new Color(21,41,71));//table grid color setting
        table.setSelectionForeground(Color.white);//selection foreground
        table.setFont(new Font("Tahoma",Font.PLAIN,17));//font
        table.setRowHeight(30);//setting row height
        table.setAutoCreateRowSorter(true);//table row sorter activated

        JScrollPane pane= new JScrollPane(table);//scroll pane
        pane.setForeground(Color.BLUE);//pane foreground color
        pane.setBackground(Color.WHITE);//pane background color
        pane.setBounds(0,0,1000,610);//table bound setting
        frame.getContentPane().add(pane);//adding pane to the frame

        Object[] row =new Object[5];//a row
        Collections.sort(Formula1ChampionshipManager.racesList,new DateComparator());//sort races according to the ascending order by date
        int i=1;//race number
        for (Race race: Formula1ChampionshipManager.racesList) {
            row[0]=i;//match number
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");//formatted date
            row[1]=simpleDateFormat.format(race.getRaceDay());//race date
            row[2]=race.getFirst().getName();//firstPlace
            row[3]=race.getSecond().getName();//second
            row[4]=race.getThird().getName();//third place

            model.addRow(row);// add the row to the table
            i++;//increase race number by 1
        }

        frame.setVisible(true);//setting visible

    }

}
